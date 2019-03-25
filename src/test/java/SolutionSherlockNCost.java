package test.java;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SolutionSherlockNCost {

	static int[] v;
	static int max = 0;

	static int cost(int[] arr) {
		int returnVal = 0;

		// Complete this function
		int len = arr.length;
		v = new int[len + 1];

		max = Math.max(max, arr[0]);
		for (int i = 1; i < len; i++) {
			max = Math.max(max, arr[i]);
			int k = 0;
			int[] a = new int[i + 1];
			while (k <= i - 1) {
				a[k++] = max;
				a[k++] = 1;
			}
			if (i % 2 == 0)
				a[k] = max;
			int t = max(a, i);
			v[i] = t;
			returnVal = Math.max(returnVal, t);
		}

		return returnVal;
	}

	private static int max(int[] a, int i) {
		return Math.abs(a[i] - a[i - 1]) + v[i - 1];
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		File f = new File("SolutionSherlockNCost_2.txt");
		in = new Scanner(f);
		int t = in.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			int n = in.nextInt();
			int[] arr = new int[n];
			max = 0;
			for (int arr_i = 0; arr_i < n; arr_i++) {
				arr[arr_i] = in.nextInt();
				max = Math.max(max, arr[arr_i]);
			}
			int result = cost(arr);
			System.out.println(result);
		}
		in.close();
	}
}