import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/27.
 */
public class queryEvaluate {
    B_Tree Index;
    Node root = new Node();
    Heapfile hf;
    LinkedList<ArrayList<String[]>> theHeap = new LinkedList<ArrayList<String[]>>();
    LinkedList<ArrayList<int[]>> groupHC;

    public queryEvaluate() throws Exception {

        Index = new B_Tree();
        Index.add(root,null);
        hf = new Heapfile();
        theHeap = hf.getHeap();
        groupHC = new LinkedList<ArrayList<int[]>>();
        for (int i = 0; i < 12000; i++) {
            ArrayList<int[]> buffer = new ArrayList<int[]>();
            groupHC.add(buffer);
        }
        //group records by hourly_counts, which is the 10th value in the record array
        for (int i = 0; i < theHeap.size(); i++) {
            for (int j = 0; j < theHeap.get(i).size(); j++) {
                //i is the page number, j is the line number
                int[] ridBuffer = {i, j};
                int HCBuffer = Integer.parseInt(theHeap.get(i).get(j)[9]);
                System.out.println(theHeap.get(i).get(j)[9]);
                if (HCBuffer <= groupHC.size())
                    groupHC.get(HCBuffer).add(ridBuffer);
                else {
                    ArrayList<int[]> buffer = new ArrayList<int[]>();
                    buffer.add(ridBuffer);
                    groupHC.add(HCBuffer, buffer);
                }
            }
        }
        System.out.println("Hourly_Counts grouped");

        //save grouped data in Index
        for (int i = 0; i < groupHC.size(); i++) {
            if (groupHC.get(i)!=null) {
                Node newNode = new Node(groupHC.get(i));
                Index.add(root, groupHC.get(i));
            }
        }
        System.out.println("B-tree established");
    }

    //the function to do the query
    public String[] findData(int Hourly_Counts) {
        int HCfound = Index.searchNode(Index, Hourly_Counts, Index.getPosition());
        ArrayList<String[]> record = null;
        for (int i = 0; i < groupHC.size(); i++) {
            if(i == HCfound){
            record.add(hf.getHeap().get());}
        }
        return record;
    }

    public B_Tree getIndex() {
        return Index;
    }

    public static void main(String[] args) throws Exception {
        //Queries!! How exciting!!
        queryEvaluate qe = new queryEvaluate();
        long startTime = System.currentTimeMillis();   //start time
        String[] record = qe.findData(3);
        long endTime = System.currentTimeMillis(); //finish time
        System.out.println("Time used: " + (endTime - startTime) + "ms");
        for (int j = 0; j < record.length; j++) {
            System.out.println(record[j]);
        }

        System.out.println("求求你了运行起来千万别报错我愿意一年不出ssr");

    }

}
