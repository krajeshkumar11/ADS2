import java.util.*;
class PageRank {
	Double[] PR;
	Double[] tempPR;
	Digraph graph = null;
	Digraph reverse = null;
	PageRank(Digraph g){
		//System.out.println(5.0/8.0);
		graph = g;

		PR = new Double[g.V()];
		for(int j=0; j<g.V();j++){
			if(graph.outdegree(j) == 0){
				for(int k = 0; k < g.V(); k++){
					if(k != j){
						graph.addEdge(j,k);
					}
				}
			}
		}
		reverse = graph.reverse();
		for(int i =0;i< g.V();i++){
			PR[i]= (double)1.0/g.V();
		}

		tempPR= new Double[g.V()];
		int iterations =1000;
		while(iterations>0){
			for(int j=0;j<g.V();j++){
				tempPR[j] = PR[j];
			}
			for(int i =0;i< g.V();i++){
				PR[i]= getPR(i);
			}
			if(Arrays.equals(tempPR, PR)) {
				break;
			}
			iterations--;
		}
	}

	public double getPR(int v){
		if(graph.indegree(v) == 0){
			return 0.0;
		}
		double sum=0.0;
			int indegreeCount  = graph.indegree(v);
			for(int adjs :  reverse.adj(v)){
					sum+=(tempPR[adjs]/graph.outdegree(adjs));

			}

		return sum;

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
