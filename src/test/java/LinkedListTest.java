package test.java;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

	public static void main(String[] args) {
		LinkedListTest obj = new LinkedListTest();
		obj.performConversion("string");
	}

	public void performConversion(String str) {
		List<String> list = new LinkedList<>();

		for (int i = 0; i < 15; i++) {
			list.add(str + i);
		}

		// Iterator<String> it = list.listIterator();

		// while (it.hasNext()) {
		for (int i = 0; i < list.size(); i++) {
			// String s = it.next();
			String s = list.get(i);
			if ("string1".equals(s)) {
				list.add(4, "string10");
			}
			System.out.println(i + ": " + s);
		}
	}

}
