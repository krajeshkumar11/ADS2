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
      length(v, w);
      return this.answer;
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w) {
      Iterator v_iterator = v.iterator();
      Iterator w_iterator = null;
      while (v_iterator.hasNext()) {
         int v_1 = (Integer)v_iterator.next();
         w_iterator = w.iterator();
         while (w_iterator.hasNext()) {
            int w_1 = (Integer)w_iterator.next();
            distance = length(v_1, w_1);
         }
      }
      return distance;
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
      Iterator v_iterator = v.iterator();
      Iterator w_iterator = null;
      while (v_iterator.hasNext()) {
         int v_1 = (Integer)v_iterator.next();
         w_iterator = w.iterator();
         while (w_iterator.hasNext()) {
            int w_1 = (Integer)w_iterator.next();
            answer = ancestor(v_1, w_1);
         }
      }
      return answer;
   }

   // do unit testing of this class
   // public static void main(String[] args) {

   // }
}
