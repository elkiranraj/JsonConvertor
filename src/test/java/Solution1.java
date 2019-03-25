package test.java;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution1 {

	static long maximumSum1(long[] a, long m) {
		Long max = 0L;
		// System.out.println(459441747%2104194685);
		// Complete this function
		max = IntStream.rangeClosed(0, a.length).boxed().mapToLong(i -> IntStream.rangeClosed(i, a.length).boxed()
				.mapToLong(j -> Arrays.stream(a, i, j).sum() % m).max().orElse(0)).max().orElse(0);
		// Arrays.stream(a, i, a.length).sum() % m
		return max;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		File f = new File("input2.txt");
		in = new Scanner(f);
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int n = in.nextInt();
			long m = in.nextLong();
			long[] a = new long[n];
			long curr = 0;
			TreeSet<Long> t = new TreeSet<>();
			Long[] l = new Long[n];
			for (int a_i = 0; a_i < n; a_i++) {
				a[a_i] = in.nextLong();
				curr = (a[a_i] % m + curr) % m;
				t.add(curr);
				l[a_i] = curr;
			}
			//long result = maximumSum(a, m);
			long result = maximumSum(a, t.toArray(new Long[n]), m);
			// long result = maximumSum(l, m);
			System.out.println(result);
			//long result1 = maximumSum(t, m);
			//System.out.println(result1);
		}
		in.close();
	}

	private static long maximumSum(long[] a, long m) {
		long ret = 0;
		Long[] l = new Long[a.length];
		long curr = 0;
		for (int i = 0; i < a.length; i++) {
			curr = (a[i] % m + curr) % m;
			l[i] = curr;
		}

		for (int i = 0; i < l.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				ret = Math.max(ret, (l[i] - l[j] + m) % m);
			}
			ret = Math.max(ret, l[i]);
		}

		return ret;
	}

	private static long maximumSum(TreeSet<Long> t, long m) {
		Long ret = 0L;

		Iterator<Long> it = t.iterator();
		while (it.hasNext()) {
			long temp = it.next();
			SortedSet<Long> set = t.tailSet(temp);
			Iterator<Long> itSet = set.iterator();
			if (itSet.hasNext())
				ret = Math.max(ret, (temp - itSet.next() - m) % m);
			ret = Math.max(ret, temp);
		}

		return ret;
	}

	private static long maximumSum(long[] a, Long[] t, long m) {
		long ret = 0;

		for (int i = 0; i < t.length-1; i++) {
			//for (int j = i + 1; j < t.length; j++) {
				int j = i + 1;
				ret = Math.max(ret, ((t[i] - t[j] + m) % m));
			//}
			ret = Math.max(ret, t[i]);
		}
		return ret;
	}
}
