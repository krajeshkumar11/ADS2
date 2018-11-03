import java.util.*;
public class SAP {

   int distance = Integer.MAX_VALUE;
   int answer = -1;
   Digraph digraph = null;
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G){
      this.digraph = G;
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w){
      distance = Integer.MAX_VALUE;
      answer = -1;
      BreadthFirstPaths v1 = new BreadthFirstPaths(digraph, v);
      BreadthFirstPaths w1 = new BreadthFirstPaths(digraph, w);
      for (int i = 0; i < this.digraph.V(); i++) {
         if(v1.hasPathTo(i) && w1.hasPathTo(i)) {
            int temp = v1.distTo(i) + w1.distTo(i);
            if(distance > temp) {
               distance = temp;
               answer = i;
            }
         }
      }
      return distance;
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w){
      distance = Integer.MAX_VALUE;
      answer = -1;
      BreadthFirstPaths v1 = new BreadthFirstPaths(digraph, v);
      BreadthFirstPaths w1 = new BreadthFirstPaths(digraph, w);
      for (int i = 0; i < this.digraph.V(); i++) {
         if(v1.hasPathTo(i) && w1.hasPathTo(i)) {
            int temp = v1.distTo(i) + w1.distTo(i);
            if(distance > temp) {
               distance = temp;
               answer = i;
            }
         }
      }
      return answer;
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w) {
      distance = Integer.MAX_VALUE;
      for(Integer v1 :v){
         for(Integer w1 : w){
            distance = length(v1,w1);
                    //System.out.println("DDDD"+distance);
         }
      }
      return distance;
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
      this.answer = -1;
      for(Integer v1 :v){
         for(Integer w1 : w){
            answer = ancestor(v1,w1);
         }
      }
      return answer;
   }

   // do unit testing of this class
   // public static void main(String[] args) {

   // }
}
