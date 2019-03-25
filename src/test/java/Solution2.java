package test.java;
import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.*;

public class Solution2 {

	static String solve(int[] a) {		
		String returnVal = "NO";

		// Complete this function
		//Comparator<Integer> comp = (o1, o2) -> o1.compareTo(o2);
		int len = a.length;
        int hLen = len / 2;
		long sumPre = 0;
		long sumPos = 0;
		int i = hLen, k = 0;
		int max = 0;
		//Map<Integer, Integer> m = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		for (; i >= 0 && i < len && k <= hLen && max <2; k++) {
			if(set.contains(i)) {
				max++;
			} else {
				set.add(i);
			}
			
			sumPre = Arrays.stream(a, 0, i).sum();
			sumPos = Arrays.stream(a, i + 1, len).sum();

			if (sumPre == sumPos) {
				returnVal = "YES";
				break;
			}
			if (sumPre > sumPos) {
				i--;
			} else { // sumPre < sumPos
				i++;
			}

			/*m.computeIfPresent(i, (key, val) -> val + 1);
			m.computeIfAbsent(i, key -> 1);
            if(k % 10 == 0)
            	max = m.values().stream().distinct().max(comp).get();*/
		}

		return returnVal;
	}

	static String solve1(int[] a) {
		String returnVal = "NO";

		// Complete this function
		int len = a.length;
		int hLen = len / 2;
		long sumPre = 0;
		long sumPos = 0;
		int i = hLen, k = 0;
		int max = 0;
		Set<Integer> set = new HashSet<>();

		sumPre = Arrays.stream(a, 0, i).sum();
		sumPos = Arrays.stream(a, i + 1, len).sum();
		for (; i >= 0 && i < len && k <= hLen && max < 3; k++) {

			if (set.contains(i)) {
				max++;
			} else {
				set.add(i);
			}

			if (sumPre == sumPos) {
				returnVal = "YES";
				break;
			}
			if (sumPre > sumPos) {
				sumPos += a[i];
				i--;
				sumPre -= a[i];
			} else { // sumPre < sumPos
				sumPre += a[i];
				i++;
				sumPos -= a[i];
			}
		}

		return returnVal;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		File f = new File("input_Sherlock_and_Array_2.txt");
		in = new Scanner(f);
		int T = in.nextInt();
		for (int a0 = 0; a0 < T; a0++) {
			int n = in.nextInt();
			int[] a = new int[n];
			for (int a_i = 0; a_i < n; a_i++) {
				a[a_i] = in.nextInt();
			}
			String result = solve(a);
			System.out.print(result);
			System.out.print(" " + solve1(a));

			System.out.println();
		}
	}
}
