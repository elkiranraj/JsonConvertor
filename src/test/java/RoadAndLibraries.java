package test.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RoadAndLibraries {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		File f = new File("road_n_libraries_1.txt");
		in = new Scanner(f);
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int noOfCities = in.nextInt();
			int noOfRoads = in.nextInt();
			int costOfLib = in.nextInt(); // library cost
			int costOfRoad = in.nextInt();

			Map<Integer, ArrayList<Integer>> graph = new HashMap<>();

			for (int a1 = 0; a1 < noOfRoads; a1++) {
				int city1 = in.nextInt();
				int city2 = in.nextInt();
				graph = buildAdjacencyList(graph, city1, city2, true);
			}
			System.out.println(
					computeCost(graph, Long.valueOf(noOfCities), Long.valueOf(costOfLib), Long.valueOf(costOfRoad)));
		}
		in.close();
	}

	private static Map<Integer, ArrayList<Integer>> buildAdjacencyList(Map<Integer, ArrayList<Integer>> graph,
			Integer node1, Integer node2, Boolean isBiDirectional) {

		Function<Integer, ArrayList<Integer>> ifAbsent = key -> new ArrayList<>();
		BiFunction<Integer, ArrayList<Integer>, ArrayList<Integer>> ifPresent = (key, list) -> list;

		graph.computeIfAbsent(node1, ifAbsent).add(node2);
		graph.computeIfPresent(node1, ifPresent).add(node2);

		if (isBiDirectional) {
			graph.computeIfAbsent(node2, ifAbsent).add(node1);
			graph.computeIfPresent(node2, ifPresent).add(node1);
		}

		return graph;
	}

	// for counting disconnected components
	private static Long visitNodeDFS(Map<Integer, ArrayList<Integer>> g, boolean visited[], int city) {
		visited[city] = true;
		Long connectedComponents = 1L;
		if (g.containsKey(city))
			connectedComponents += g.computeIfPresent(city, (key, list) -> list).stream().filter(a -> !visited[a])
					.mapToLong(a -> visitNodeDFS(g, visited, a)).sum();
		return connectedComponents;
	}

	private static Long computeCost(Map<Integer, ArrayList<Integer>> g, Long noOfCities, Long costOfLib,
			Long costOfRoad) {
		if (costOfRoad > costOfLib) {
			return Long.valueOf(costOfLib) * Long.valueOf(noOfCities);
		}
		boolean visited[] = new boolean[noOfCities.intValue() + 1];
		Long cost = 0L;
		for (int i = 1; i <= noOfCities; i++) {
			if (!visited[i]) {
				Long x = visitNodeDFS(g, visited, i);
				cost += (x - 1) * costOfRoad;
				cost += costOfLib;
			}
		}
		return cost;
	}

}