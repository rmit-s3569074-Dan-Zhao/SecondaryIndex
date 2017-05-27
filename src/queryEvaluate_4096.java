import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/27.
 */
public class queryEvaluate_4096 {
    B_Tree_4096 Index4096;
    Heapfile_4096 heapfile4096;
    LinkedList<ArrayList<int[]>> groupHC;

    public queryEvaluate_4096() throws Exception {

        Index4096 = new B_Tree_4096();
        heapfile4096 = new Heapfile_4096();
        long startTime = System.currentTimeMillis();   //start time
        heapfile4096.establishHeap();
        long endTime = System.currentTimeMillis(); //finish time
        System.out.println("Establish time: " + (endTime - startTime) + "ms");

        int[] buffer = {Integer.parseInt(heapfile4096.getHeap().get(0).get(0)[9]), 0, 0};
        Index4096.add(buffer);
        for (int i = 0; i < heapfile4096.getHeap().size(); i++) {
            for (int j = 0; j < heapfile4096.getHeap().get(i).size(); j++) {
                //i is page number, j is line number
                int[] bufferr = {Integer.parseInt(heapfile4096.getHeap().get(i).get(j)[9]), i, j};
                Index4096.add(Index4096.root, bufferr);
            }
        }
        System.out.println("B-tree established");
//        System.out.println("get root:"+Index4096.getRoot().keys.get()[0]+", root:"+Index4096.root.keys.get(1)[0]);
    }

    //the function to do the query
    public void findData(int Hourly_Counts) throws Exception {
        LinkedList<int[]> HCfound = Index4096.searchNode(Index4096, Hourly_Counts, Index4096.getPosition());
        ArrayList<String[]> record = null;
        System.out.println();
        for (int k = 0; k < HCfound.size(); k++) {
            String a, b, c, d, e, f, g, h, l, m;
            a = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[0];
            b = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[1];
            c = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[2];
            d = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[3];
            e = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[4];
            f = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[5];
            g = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[6];
            h = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[7];
            l = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[8];
            m = heapfile4096.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[9];
            //System.out.println("Found it：" + a + "," + b + "," + c + "," + d + "," + e + "," + f + "," + g + "," + h + "," + l + "," + m + "!!!");
        }
    }

    public static void main(String[] args) throws Exception {
        //Queries!! How exciting!!
        queryEvaluate_4096 qe = new queryEvaluate_4096();
        long startTime = System.currentTimeMillis();   //start time
        qe.findData(3);
        long endTime = System.currentTimeMillis(); //finish time
        System.out.println("Time used in 4096: " + (endTime - startTime) + "ms");
    }

}
