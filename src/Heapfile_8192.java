import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;


public class Heapfile_8192 {
    public LinkedList<ArrayList<String[]>> datastorage = new LinkedList<ArrayList<String[]>>();
    ArrayList<String[]> Page = new ArrayList<String[]>();
    ;
    String[] item;
    BufferedReader reader;
    long pageSize = 0, allSize = 0;

    public void establishHeap(){
        String line = null;
        try {
            //start to read the file
            reader = new BufferedReader(new FileReader("src/2009.csv"));
            reader.readLine();
            //the first line was column titles so skip first line
            line = reader.readLine();
            item = line.split(",");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //store the record in page if page is not full
        while (line != null) {
            if (pageSize < 8192) {
                Page.add(item);
                //update page size
                for (int i = 0; i < item.length; i++) {
                    pageSize = pageSize + item[i].toString().getBytes().length;
                }
                allSize = allSize + pageSize;
                //read next record
                try {
                    if((line = reader.readLine())!=null)
                        item = line.split(",");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //when the page is full, put it in the heap
            else {
                addPage(datastorage,Page);
                //System.out.println("There are " + Page.size() + " records in this page when it is full");
                pageSize = 0;
                //System.out.println("Number of pages for now：" + datastorage.size());
                Page = new ArrayList<String[]>();
            }
        }
        System.out.println("The last page");
        addPage(datastorage,Page);
        System.out.println("There are " + Page.size() + " records in this page when it is full");
        //clear the page buffer
        pageSize = 0;
        System.out.println("Number of pages for now：" + datastorage.size());
        System.out.println("The final size is ：" + allSize);
    }
    public void addPage(LinkedList<ArrayList<String[]>> datastorage,ArrayList<String[]> page){
        ArrayList<String[]> pageBuffer = new ArrayList<String[]>();
        pageBuffer = page;
        datastorage.add(pageBuffer);
    }

    public LinkedList<ArrayList<String[]>> getHeap(){
        return datastorage;
    }

/*    public String[] getTestRecord() {
        try {
            reader = new BufferedReader(new FileReader("src/sample.csv"));
            reader.readLine();
            //the first line was column titles so skip first line
            String line = reader.readLine();
            item = line.split(",");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public static void main(String[] args) {
        try {
            Heapfile_4096 heapfile4096 = new Heapfile_4096();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}



