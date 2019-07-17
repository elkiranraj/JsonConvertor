package test.java;

import java.util.HashMap;

public class PalindromePermutation {

	public static void main(String[] args) {
		PalindromePermutation p = new PalindromePermutation();
		System.out.println(p.canPermutePalindrome("baacdaab"));
	}

	public boolean canPermutePalindrome(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < s.length(); i++) {
			String str = s.charAt(i) + "";
			if (!map.containsKey(str)) {
				map.put(str, 1);
			} else {
				map.put(str, map.get(str) + 1);
			}
		} // ENd for
		int countOdd = 0;
		for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() % 2 == 1) {
				countOdd++;
			}
			if (countOdd > 1) {
				return false;
			}
		} // END for
		return true;
	}
}
