import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner sc = new Scanner(System.in);
		int V = Integer.parseInt(sc.nextLine());
		int E = Integer.parseInt(sc.nextLine());
		EdgeWeightedGraph EWG = new EdgeWeightedGraph(V);
		for (int i = 0; i < E; i++) {
		    String[] input = sc.nextLine().split(" ");
		    Edge edge = new Edge(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Double.parseDouble(input[2]));
		    EWG.addEdge(edge);
		}
		String caseToGo = sc.nextLine();
		switch (caseToGo) {
		case "Graph":
			System.out.println(EWG);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}
