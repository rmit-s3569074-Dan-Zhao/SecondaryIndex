import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/27.
 */
public class queryEvaluate_8192 {
    B_Tree_8192 Index8192;
    Heapfile_8192 heapfile8192;
    LinkedList<ArrayList<int[]>> groupHC;

    public queryEvaluate_8192() throws Exception {

        Index8192 = new B_Tree_8192();
        heapfile8192 = new Heapfile_8192();
        long startTime = System.currentTimeMillis();   //start time
        heapfile8192.establishHeap();
        long endTime = System.currentTimeMillis(); //finish time
        System.out.println("Establish time: " + (endTime - startTime) + "ms");

        int[] buffer = {Integer.parseInt(heapfile8192.getHeap().get(0).get(0)[9]), 0, 0};
        Index8192.add(buffer);
        for (int i = 0; i < heapfile8192.getHeap().size(); i++) {
            for (int j = 0; j < heapfile8192.getHeap().get(i).size(); j++) {
                //i is page number, j is line number
                int[] bufferr = {Integer.parseInt(heapfile8192.getHeap().get(i).get(j)[9]), i, j};
                Index8192.add(Index8192.root, bufferr);
            }
        }
        System.out.println("B-tree established");
//        System.out.println("get root:"+Index4096.getRoot().keys.get()[0]+", root:"+Index4096.root.keys.get(1)[0]);
    }

    //the function to do the query
    public void findData(int Hourly_Counts) throws Exception {
        LinkedList<int[]> HCfound = Index8192.searchNode(Index8192, Hourly_Counts, Index8192.getPosition());
        ArrayList<String[]> record = null;
        System.out.println();
        for (int k = 0; k < HCfound.size(); k++) {
            String a, b, c, d, e, f, g, h, l, m;
            a = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[0];
            b = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[1];
            c = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[2];
            d = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[3];
            e = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[4];
            f = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[5];
            g = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[6];
            h = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[7];
            l = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[8];
            m = heapfile8192.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[9];
            System.out.println("Found it：" + a + "," + b + "," + c + "," + d + "," + e + "," + f + "," + g + "," + h + "," + l + "," + m + "!!!");
        }
    }

    public static void main(String[] args) throws Exception {
        //Queries!! How exciting!!
        queryEvaluate_8192 qe = new queryEvaluate_8192();
        long startTime = System.currentTimeMillis();   //start time
        qe.findData(3);
        long endTime = System.currentTimeMillis(); //finish time
        System.out.println("Time used in 8192: " + (endTime - startTime) + "ms");
    }

}
