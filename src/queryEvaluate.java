import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/27.
 */
public class queryEvaluate extends Heapfile{
    B_Tree Index;
    Heapfile hf;
    LinkedList<ArrayList<int[]>> groupHC;

    public queryEvaluate() throws Exception {

        Index = new B_Tree();
        hf = new Heapfile();
        hf.establishHeap();
        groupHC = new LinkedList<ArrayList<int[]>>();
        for (int i = 0; i < 12000; i++) {
            ArrayList<int[]> buffer = new ArrayList<int[]>();
            groupHC.add(buffer);
        }
        //save grouped data in Index
        Node root = new Node();
        System.out.println("wtf怎么回事"+hf.getHeap().get(0).get(0)[0]);
        int[] buffer = {Integer.parseInt(hf.getHeap().get(0).get(0)[9]), 0, 0};
        Index.add(buffer);
        for (int i = 0; i < hf.getHeap().size(); i++) {
            for (int j = 0; j < hf.getHeap().get(i).size(); j++) {
                //i is page number, j is line number
                int[] bufferr = {Integer.parseInt(hf.getHeap().get(i).get(j)[9]), i, j};
                Index.add(Index.getRoot(),bufferr);
            }
        }
        System.out.println("B-tree established");
    }

    //the function to do the query
    public ArrayList<String[]> findData(int Hourly_Counts) throws Exception{
        int[] HCfound = Index.searchNode(Index, Hourly_Counts, Index.getPosition());
        ArrayList<String[]> record = null;
        for (int i = 0; i < 3; i++) {
            record.add(hf.getHeap().get(HCfound[1]).get(HCfound[2]));
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
        ArrayList<String[]> record = qe.findData(3);
        long endTime = System.currentTimeMillis(); //finish time
        System.out.println("Time used: " + (endTime - startTime) + "ms");
        for (int i = 0; i < record.size(); i++) {
            System.out.print("Data number " + i + ":");
            for (int j = 0; j < record.get(i).length; j++) {
                System.out.print(record.get(i)[j]);
            }
            System.out.println();
        }

        System.out.println("求求你了运行起来千万别报错我愿意一年不出ssr");

    }

}
