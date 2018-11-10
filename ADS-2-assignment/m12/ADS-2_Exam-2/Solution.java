import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner sc = new Scanner(System.in);
		int V = Integer.parseInt(sc.nextLine());
		int E = Integer.parseInt(sc.nextLine());
		EdgeWeightedDigraph EWDG = new EdgeWeightedDigraph(V);
		for (int i = 0; i < E; i++) {
		    String[] input = sc.nextLine().split(" ");
		    DirectedEdge edge = new DirectedEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Double.parseDouble(input[2]));
		    EWDG.addEdge(edge);
		}
		String caseToGo = sc.nextLine();
		switch (caseToGo) {
		case "Graph":
			System.out.println(EWDG);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] input = sc.nextLine().split(" ");
			int S = Integer.parseInt(input[0]);
			int D = Integer.parseInt(input[1]);
			DijkstraSP DSP = new DijkstraSP(EWDG, S);
			// System.out.println(EWDG);
			// AcyclicSP ASP = new AcyclicSP(EWDG, S);
			Iterable<DirectedEdge> path = DSP.pathTo(D);
			if (path == null) {
				System.out.println("No Path Found.");
			} else {
				double sum = 0;
				for (DirectedEdge dEdge: path) {
					// System.out.print(dEdge + " ");
					sum = sum + dEdge.weight();
				}
				System.out.println(sum);
			}
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
