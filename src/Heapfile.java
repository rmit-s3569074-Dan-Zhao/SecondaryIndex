import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;


public class Heapfile {
    private LinkedList<ArrayList<String[]>> datastorage = new LinkedList();
    ArrayList<String[]> Page;
    String[] item;
    BufferedReader reader;
    long pageSize=0, allSize=0;

    // Ctrl + Alt + L    formating
    public Heapfile() {
        Page = new ArrayList<String[]>();
        try {
            //start to read the file
            reader = new BufferedReader(new FileReader("src/sample.csv"));
            reader.readLine();
            //the first line was column titles so skip first line
            String line = reader.readLine();
            item = line.split(",");

            //store the record in page if page is not full
            while (line != null) {
                if (pageSize < 4096) {
                    Page.add(item);
                    //update page size
                    for (int i = 0; i < item.length; i++) {
                        pageSize = pageSize + item[i].toString().length();
                    }
                    allSize = allSize + pageSize;
                    //read next record
                    line = reader.readLine();
                    item = line.split(",");
                }
                //when the page is full, put it in the heap
                else {
                    datastorage.add(Page);
                    System.out.println("There are " + Page.size() + " records in this page when it is full");
                    //clear the page buffer
                    Page.clear();
                    pageSize = 0;
                    System.out.println("Number of pages for now："+datastorage.size());
                }
            }
            System.out.println("The final size is ：" + allSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Heapfile hf = new Heapfile();
    }
}

