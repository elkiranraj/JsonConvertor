package test.java;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution3 {

	static boolean[][] v;

	static int connectedCell(int[][] a, int n, int m) {
		int returnVal = 0;
		v = new boolean[n][m];

		// Complete this function
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int depth = 0;
				if (!v[i][j] && a[i][j] == 1) {
					depth += visit(a, i, j, n, m);
				}
				returnVal = Math.max(returnVal, depth);
			}
		}

		return returnVal;
	}

	private static int visit(int[][] a, int i, int j, int n, int m) {
		int returnVal = 1;
		v[i][j] = true;

		int x = i - 1; // i - 1 :: Row above i
		int y;
		if (x >= 0 && x < n) {
			y = j - 1;
			returnVal += verify(a, x, y, n, m);
			y = j;
			returnVal += verify(a, x, y, n, m);
			y = j + 1;
			returnVal += verify(a, x, y, n, m);
		}
		x = i; // i :: Row of i
		if (x >= 0 && x < n) {
			y = j - 1;
			returnVal += verify(a, x, y, n, m);
			y = j;
			returnVal += verify(a, x, y, n, m);
			y = j + 1;
			returnVal += verify(a, x, y, n, m);
		}
		x = i + 1; // i :: Row below i
		if (x >= 0 && x < n) {
			y = j - 1;
			returnVal += verify(a, x, y, n, m);
			y = j;
			returnVal += verify(a, x, y, n, m);
			y = j + 1;
			returnVal += verify(a, x, y, n, m);
		}
		return returnVal;
	}

	private static int verify(int[][] a, int x, int y, int n, int m) {
		return (y >= 0 && y < m && !v[x][y] && a[x][y] == 1) ? visit(a, x, y, n, m) : 0;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		File f = new File("Collected_Cells_2.txt");
		in = new Scanner(f);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] matrix = new int[n][m];
		for (int matrix_i = 0; matrix_i < n; matrix_i++) {
			for (int matrix_j = 0; matrix_j < m; matrix_j++) {
				matrix[matrix_i][matrix_j] = in.nextInt();
			}
		}
		int result = connectedCell(matrix, n, m);
		System.out.println(result);
		in.close();
	}
}
