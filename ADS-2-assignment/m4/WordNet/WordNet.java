import java.util.*;
import java.io.File;
import java.util.*;
public class WordNet {
   // HashMap<String, ArrayList<Integer>> hm = null;
   HashMap<String, ArrayList<Integer>> hm = null;
   HashMap<Integer, String> synsetshm = null;
   public DirectedCycle dc = null;
   public CC cc = null;
   Digraph graph = null;
   SAP sap = null;
   // public boolean multiple_root = false;
   // constructor takes the name of the two input files
   public WordNet(String hypernyms, String synsets) {
      // hm = new HashMap<String, ArrayList<Integer>>();
      hm = new HashMap<String, ArrayList<Integer>>();
      synsetshm = new HashMap<Integer, String>();
      try {
         File file = new File(synsets);
         Scanner sc = new Scanner(file);
         int count = 0;
         while(sc.hasNext()) {
            String[] stlist= sc.nextLine().split(",");
            String[] nouns_list = stlist[1].split(" ");
            count += nouns_list.length;
            synsetshm.put(Integer.parseInt(stlist[0]), stlist[1]);
            ArrayList<Integer> al = new ArrayList<Integer>();
            for(int i = 0; i < nouns_list.length; i++) {
               if(!hm.containsKey(nouns_list[i])) {
                  // al = hm.get(nouns_list[i]);

               }
               al.add(Integer.parseInt(stlist[0]));
               hm.put(nouns_list[i], al);
            }
         }
         // Graph
         File file_graph = new File(hypernyms);
         Scanner sc_graph = new Scanner(file_graph);
         graph = new Digraph(count);
         while(sc_graph.hasNext()) {
            String[] conn = sc_graph.nextLine().split(",");
            for (int i = 1; i < conn.length; i++) {
               graph.addEdge(Integer.parseInt(conn[0]), Integer.parseInt(conn[i]));
            }
         }
         sap = new SAP(graph);
         dc = new DirectedCycle(graph);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public String toString() {
      return graph.toString();
   }

   // returns all WordNet nouns
   public Iterable<String> nouns(){
      return hm.keySet();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word){
      return hm.containsKey(word);
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB){
      if(isNoun(nounA) && isNoun(nounB)) {
         return sap.length(hm.get(nounA), hm.get(nounB));
      }
      return -1;
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB){
      if(isNoun(nounA) && isNoun(nounB)) {
         int answer = sap.ancestor(hm.get(nounA), hm.get(nounB));
         return synsetshm.get(answer);
      }
      return null;
   }

   // do unit testing of this class
   // public static void main(String[] args){}
}
