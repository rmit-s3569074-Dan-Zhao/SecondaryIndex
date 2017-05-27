import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/27.
 */
public class queryEvaluate extends Heapfile {
    B_Tree Index;
    Heapfile hf;
    LinkedList<ArrayList<int[]>> groupHC;

    public queryEvaluate() throws Exception {

        Index = new B_Tree();
        hf = new Heapfile();
        hf.establishHeap();
        //save grouped data in Index

        int[] buffer = {Integer.parseInt(hf.getHeap().get(0).get(0)[9]), 0, 0};
        Index.add(buffer);
        for (int i = 0; i < hf.getHeap().size(); i++) {
            for (int j = 0; j < hf.getHeap().get(i).size(); j++) {
                //i is page number, j is line number
                int[] bufferr = {Integer.parseInt(hf.getHeap().get(i).get(j)[9]), i, j};
                Index.add(Index.root, bufferr);
            }
        }
        System.out.println("B-tree established");
//        System.out.println("get root:"+Index.getRoot().keys.get()[0]+", root:"+Index.root.keys.get(1)[0]);
    }

    //the function to do the query
    public void findData(int Hourly_Counts) throws Exception {
        LinkedList<int[]> HCfound = Index.searchNode(Index, Hourly_Counts, Index.getPosition());
        ArrayList<String[]> record = null;
        System.out.println();
        for (int k = 0; k < HCfound.size(); k++) {
            String a, b, c, d, e, f, g, h, l, m;
            a = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[0];
            b = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[1];
            c = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[2];
            d = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[3];
            e = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[4];
            f = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[5];
            g = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[6];
            h = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[7];
            l = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[8];
            m = hf.getHeap().get(HCfound.get(k)[1]).get(HCfound.get(k)[2])[9];
            System.out.println("终于你妈找见了：" + a + "," + b + "," + c + "," + d + "," + e + "," + f + "," + g + "," + h + "," + l + "," + m + "!!!");
        }
    }

    public B_Tree getIndex() {
        return Index;
    }

    public static void main(String[] args) throws Exception {
        //Queries!! How exciting!!
        queryEvaluate qe = new queryEvaluate();
        long startTime = System.currentTimeMillis();   //start time
        qe.findData(3);
        long endTime = System.currentTimeMillis(); //finish time
        System.out.println("Time used: " + (endTime - startTime) + "ms");
    }

}
