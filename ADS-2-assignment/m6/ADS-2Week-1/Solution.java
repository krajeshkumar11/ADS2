import java.util.Scanner;
class PageRank {
	Double[] PR;
	Digraph G = null;
	PageRank(Digraph G) {
		G = G;
		PR = new Double[G.V()];
		for (int i = 0; i < PR.length; i++) {
		    PR[i] = 1/4d;
		}

		for (int j = 1; j < 1000; j++) {
		    for (int i = 0; i < PR.length; i++) {
				Double total_rank = 0d;
				Iterable<Integer> adjecents = G.adj(i);
				for (Integer in : adjecents) {

					total_rank += getPR(in) / G.outdegree(in);
					// System.out.print(in + " " + PR[in] + " " + G.outdegree(in) + ", ");
				}
				PR[i] = total_rank;
				// System.out.println(j + " HI");
			}
		}
	}

	public double getPR(int v) {
		return PR[v];
	}

	public String toString() {
		String st = "";
		for (int i = 0; i < PR.length; i++) {
		    st += i + " - " + PR[i];
		    if(i < PR.length - 1) {
		    	st += "\n";
		    }
		}
		return st;
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// read the first line of the input to get the number of vertices
		int n = Integer.parseInt(sc.nextLine());
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph
		Digraph G = new Digraph(n);
		for (int i = 0; i < n; i++) {
			String[] edges = sc.nextLine().split(" ");
			for (int j = 1; j < edges.length; j++) {
				G.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[j]));
			}
		}
		System.out.println(G);


		// Create page rank object and pass the graph object to the constructor
		PageRank pr = new PageRank(G);
		// print the page rank object
		System.out.println(pr);
		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
