import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/27.
 */
public class queryEvaluate {
    public static void main(String[] args) {
        B_Tree Index = new B_Tree();
        Node root = new Node();
        Heapfile hf = new Heapfile();
        LinkedList<ArrayList<String[]>> theHeap = hf.getHeap();
        LinkedList<ArrayList<int[]>> groupHC = new LinkedList<ArrayList<int[]>>();

        //group records by hourly_counts, which is the 10th value in the record array
        for (int i = 0; i < theHeap.size(); i++) {
            for (int j = 0; j < theHeap.get(i).size(); j++) {
                //i is the page number, j is the line number
                int[] ridBuffer = {i, j};
                int HCBuffer = Integer.parseInt(theHeap.get(i).get(j)[9]);
                groupHC.get(HCBuffer).add(ridBuffer);
            }
        }

        //save grouped data in Index
        for (int i = 0; i < groupHC.size(); i++) {
            Node newNode = new Node(groupHC.get(i));
            Index.add(root,groupHC.get(i));
        }

        //Queries!! How exciting!!
        long startTime=System.currentTimeMillis();   //start time
        //do queries!!
        long endTime=System.currentTimeMillis(); //finishi time
        System.out.println("Time used: "+(endTime-startTime)+"ms");
    }
}
