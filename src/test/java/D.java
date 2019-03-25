package test.java;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class D {
	InputStream is;
	PrintWriter out;
	String INPUT = "";

	void solve() {
		int n = ni();
		int x = ni(), y = ni();
		int[][] co = new int[n][];
		for (int i = 0; i < n; i++) {
			co[i] = new int[] { ni(), ni(), ni(), ni() };
		}
		// Arrays.sort(co, new Comparator<int[]>() {
		// public int compare(int[] a, int[] b) {
		// return a[2] - b[2];
		// }
		// });
		StaticRangeTreeRMQ2 st = new StaticRangeTreeRMQ2(co, 200005);
		// for(int i = 0;i < n;i++){
		// st.update(co[i][0], co[i][1], Long.MAX_VALUE / 2);
		// }
		mergesort(co, 0, n);
		long max = Long.MIN_VALUE;
		long[] dp = new long[n];
		for (int i = 0; i < n; i++) {
			long min = -st.min(co[i][0] - x, co[i][0] + x + 1, co[i][1] - y, co[i][1] + y + 1);
			long val = co[i][3] + Math.max(min, 0);
			dp[i] = val;
			st.update(co[i][0], co[i][1], -dp[i]);
			max = Math.max(max, dp[i]);
		}
		out.println(max);
	}

	private static int[][] stemp = new int[200005][];

	public static void mergesort(int[][] a, int s, int e) {
		if (e - s <= 1)
			return;
		int h = s + e >> 1;
		mergesort(a, s, h);
		mergesort(a, h, e);
		int p = 0;
		int i = s, j = h;
		for (; i < h && j < e;)
			stemp[p++] = a[i][2] < a[j][2] ? a[i++] : a[j++];
		while (i < h)
			stemp[p++] = a[i++];
		while (j < e)
			stemp[p++] = a[j++];
		System.arraycopy(stemp, 0, a, s, e - s);
	}

	public static void mergesort0(int[][] a, int s, int e) {
		if (e - s <= 1)
			return;
		int h = s + e >> 1;
		mergesort0(a, s, h);
		mergesort0(a, h, e);
		int p = 0;
		int i = s, j = h;
		for (; i < h && j < e;)
			stemp[p++] = a[i][0] < a[j][0] || a[i][0] == a[j][0] && a[i][1] < a[j][1] ? a[i++] : a[j++];
		while (i < h)
			stemp[p++] = a[i++];
		while (j < e)
			stemp[p++] = a[j++];
		System.arraycopy(stemp, 0, a, s, e - s);
	}

	public static class StaticRangeTreeRMQ2 {
		public int M, H, N;
		public SegmentTreeRMQL[] st;
		public int[][] maps;
		public long[][] vals;
		public int[] count;
		public long I = Long.MAX_VALUE;

		public StaticRangeTreeRMQ2(int[][] co, int n) {
			N = n;
			M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
			H = M >>> 1;

			mergesort0(co, 0, co.length);
			// Arrays.sort(co, new Comparator<int[]>() { // x asc
			// public int compare(int[] a, int[] b) {
			// if(a[0] != b[0])return a[0] - b[0];
			// return a[1] - b[1];
			// }
			// });
			maps = new int[M][];
			vals = new long[M][];
			st = new SegmentTreeRMQL[M];
			count = new int[M];
			for (int i = 0; i < co.length; i++) {
				count[H + co[i][0]]++;
			}
			int off = 0;
			for (int i = 0; i < n; i++) {
				maps[H + i] = new int[count[H + i]];
				for (int j = 0; j < count[H + i]; j++, off++) {
					maps[H + i][j] = co[off][1];
				}
				st[H + i] = new SegmentTreeRMQL(count[H + i]);
			}

			for (int i = H - 1; i >= 1; i--) {
				if (maps[2 * i + 1] == null) {
					maps[i] = maps[2 * i];
					count[i] = count[2 * i];
				} else {
					count[i] = count[2 * i] + count[2 * i + 1];
					maps[i] = new int[count[i]];
					int l = 0;
					for (int j = 0, k = 0; j < count[2 * i] || k < count[2 * i + 1]; l++) {
						if (j == count[2 * i]) {
							maps[i][l] = maps[2 * i + 1][k++];
						} else if (k == count[2 * i + 1]) {
							maps[i][l] = maps[2 * i][j++];
						} else if (maps[2 * i][j] < maps[2 * i + 1][k]) {
							maps[i][l] = maps[2 * i][j++];
						} else if (maps[2 * i][j] > maps[2 * i + 1][k]) {
							maps[i][l] = maps[2 * i + 1][k++];
						} else {
							maps[i][l] = maps[2 * i][j++];
							k++;
						}
					}
					if (l != count[i]) { // uniq
						count[i] = l;
						maps[i] = Arrays.copyOf(maps[i], l);
					}
				}
				if (count[i] <= 25) { // 10% faster
					vals[i] = new long[count[i]];
					Arrays.fill(vals[i], Long.MAX_VALUE / 2);
				} else {
					st[i] = new SegmentTreeRMQL(count[i]);
				}
			}
		}

		public void update(int x, int y, long v) {
			outer: for (int pos = H + x; pos >= 1; pos >>>= 1) {
				if (st[pos] != null) {
					int ind = Arrays.binarySearch(maps[pos], y);
					if (ind >= 0) {
						st[pos].update(ind, v);
						continue;
					}
				} else {
					for (int i = 0; i < count[pos]; i++) {
						if (maps[pos][i] == y) {
							vals[pos][i] = v;
							continue outer;
						}
					}
				}
				throw new RuntimeException(String.format("access to non-existing point : (%d,%d):%d", x, y, v));
			}
		}

		public long min(int xl, int xr, int yl, int yr) {
			return min(xl, xr, yl, yr, 0, H, 1);
		}

		public long min(int xl, int xr, int yl, int yr, int cl, int cr, int cur) {
			if (xl <= cl && cr <= xr) {
				if (st[cur] != null) {
					int indl = Arrays.binarySearch(maps[cur], yl);
					int indr = Arrays.binarySearch(maps[cur], yr);
					if (indl < 0)
						indl = -indl - 1;
					if (indr < 0)
						indr = -indr - 1;
					return st[cur].minx(indl, indr);
				} else {
					long min = I;
					for (int i = 0; i < count[cur] && maps[cur][i] < yr; i++) {
						if (maps[cur][i] >= yl && vals[cur][i] < min)
							min = vals[cur][i];
					}
					return min;
				}
			} else {
				int mid = cl + cr >>> 1;
				long ret = I;
				if (cl < xr && xl < mid)
					ret = Math.min(ret, min(xl, xr, yl, yr, cl, mid, 2 * cur));
				if (mid < xr && xl < cr)
					ret = Math.min(ret, min(xl, xr, yl, yr, mid, cr, 2 * cur + 1));
				return ret;
			}
		}
	}

	public static class SegmentTreeRMQL {
		public int M, H, N;
		public long[] st;

		public SegmentTreeRMQL(int n) {
			N = n;
			M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
			H = M >>> 1;
			st = new long[M];
			Arrays.fill(st, 0, M, Long.MAX_VALUE / 2);
		}

		public SegmentTreeRMQL(long[] a) {
			N = a.length;
			M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
			H = M >>> 1;
			st = new long[M];
			for (int i = 0; i < N; i++) {
				st[H + i] = a[i];
			}
			Arrays.fill(st, H + N, M, Long.MAX_VALUE);
			for (int i = H - 1; i >= 1; i--)
				propagate(i);
		}

		public void update(int pos, long x) {
			st[H + pos] = x;
			for (int i = (H + pos) >>> 1; i >= 1; i >>>= 1)
				propagate(i);
		}

		private void propagate(int i) {
			st[i] = Math.min(st[2 * i], st[2 * i + 1]);
		}

		public long minx(int l, int r) {
			long min = Long.MAX_VALUE;
			if (l >= r)
				return min;
			while (l != 0) {
				int f = l & -l;
				if (l + f > r)
					break;
				long v = st[(H + l) / f];
				if (v < min)
					min = v;
				l += f;
			}

			while (l < r) {
				int f = r & -r;
				long v = st[(H + r) / f - 1];
				if (v < min)
					min = v;
				r -= f;
			}
			return min;
		}

		public long min(int l, int r) {
			return l >= r ? 0 : min(l, r, 0, H, 1);
		}

		private long min(int l, int r, int cl, int cr, int cur) {
			if (l <= cl && cr <= r) {
				return st[cur];
			} else {
				int mid = cl + cr >>> 1;
				long ret = Long.MAX_VALUE;
				if (cl < r && l < mid) {
					ret = Math.min(ret, min(l, r, cl, mid, 2 * cur));
				}
				if (mid < r && l < cr) {
					ret = Math.min(ret, min(l, r, mid, cr, 2 * cur + 1));
				}
				return ret;
			}
		}

		public int firstle(int l, long v) {
			int cur = H + l;
			while (true) {
				if (st[cur] <= v) {
					if (cur < H) {
						cur = 2 * cur;
					} else {
						return cur - H;
					}
				} else {
					cur++;
					if ((cur & cur - 1) == 0)
						return -1;
					if ((cur & 1) == 0)
						cur >>>= 1;
				}
			}
		}

		public int lastle(int l, long v) {
			int cur = H + l;
			while (true) {
				if (st[cur] <= v) {
					if (cur < H) {
						cur = 2 * cur + 1;
					} else {
						return cur - H;
					}
				} else {
					if ((cur & cur - 1) == 0)
						return -1;
					cur--;
					if ((cur & 1) == 1)
						cur >>>= 1;
				}
			}
		}
	}

	void run() throws Exception {
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!INPUT.isEmpty())
			tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new D().run();
	}

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private static void tr(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
}
