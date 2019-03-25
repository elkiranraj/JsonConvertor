package test.java;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SolutionMaxMission {

	class Node {
		Integer latitude;
		Integer longitude;
		Integer height;
		Integer points;
		Node[] children;  // reference to array of children

		public Node(Integer latitude, Integer longitude, Integer height, Integer points) {
			super();
			this.latitude = latitude;
			this.longitude = longitude;
			this.height = height;
			this.points = points;
		}

		public Integer getLatitude() {
			return latitude;
		}

		public void setLatitude(Integer latitude) {
			this.latitude = latitude;
		}

		public Integer getLongitude() {
			return longitude;
		}

		public void setLongitude(Integer longitude) {
			this.longitude = longitude;
		}

		public Integer getHeight() {
			return height;
		}

		public void setHeight(Integer height) {
			this.height = height;
		}

		public Integer getPoints() {
			return points;
		}

		public void setPoints(Integer points) {
			this.points = points;
		}

		@Override
		public String toString() {
			return "Node [latitude=" + latitude + ", longitude=" + longitude + ", height=" + height + ", points=" + points + "]";
		}

	}

	private int n;
	private int d_lat;
	private int d_long;
	private Comparator<Node> nodeComparator1 = Comparator.comparing(Node::getHeight).thenComparing(Node::getLatitude).thenComparing(Node::getLongitude);

	private Set<Node> set = new TreeSet<>(nodeComparator1);

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		File f = new File("SolutionMaxMission_1.txt");
		in = new Scanner(f);
		SolutionMaxMission s = new SolutionMaxMission();
		s.n = in.nextInt();
		s.d_lat = in.nextInt();
		s.d_long = in.nextInt();
		for (int a0 = 0; a0 < s.n; a0++) {
			int latitude = in.nextInt();
			int longitude = in.nextInt();
			int height = in.nextInt();
			int points = in.nextInt();
			// Write Your Code Here

			s.addNode(latitude, longitude, height, points);
		}
		s.processNodes();
		in.close();
		System.out.println(s.set);
	}

	private void processNodes() {
		for (Node n : set) {

		}
	}

	private void addNode(int latitude, int longitude, int height, int points) {
		set.add(new Node(latitude, longitude, height, points));
	}
}