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
		EdgeWeightedGraph EWG = new EdgeWeightedGraph(V);
		for (int i = 0; i < E; i++) {
		    String[] input = sc.nextLine().split(" ");
		    Edge edge = new Edge(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Double.parseDouble(input[2]));
		    EWG.addEdge(edge);
		    DirectedEdge dDdge = new DirectedEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Double.parseDouble(input[2]));
		    EWDG.addEdge(dDdge);
		    dDdge = new DirectedEdge(Integer.parseInt(input[1]), Integer.parseInt(input[0]), Double.parseDouble(input[2]));
		    EWDG.addEdge(dDdge);
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
			input = sc.nextLine().split(" ");
			S = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			D = Integer.parseInt(input[2]);
			DSP = new DijkstraSP(EWDG, S);
			DijkstraSP DSP1 = new DijkstraSP(EWDG, M);
			// System.out.println(EWDG);
			// AcyclicSP ASP = new AcyclicSP(EWDG, S);
			path = DSP.pathTo(M);
			Iterable<DirectedEdge>  path1 = DSP1.pathTo(D);

			if (!DSP.hasPathTo(M) || !DSP1.hasPathTo(D)) {
				System.out.println("No Path Found.");
			} else {
				String st = "";
				double sum = 0;
				for (DirectedEdge dEdge: path) {
					// System.out.print(dEdge + " ");
					if (st.equals("")) {
						st =  "" + dEdge.from();
					} else {
						st = st + " " + dEdge.from();
					}
					sum = sum + dEdge.weight();
				}
				for (DirectedEdge dEdge: path1) {
					// System.out.print(dEdge + " ");
					st = st + " " +dEdge.from();
					sum = sum + dEdge.weight();
				}
				st = st + " " + D;
				System.out.println(sum);
				System.out.println(st);
			}
			break;

		default:
			break;
		}
	}
}
