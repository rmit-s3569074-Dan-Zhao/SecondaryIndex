import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/26.
 */
public class Node{
    public Node root;
    public Node father;
    public LinkedList<Node> children = new LinkedList<Node>();
    public LinkedList<ArrayList<int[]>> keys = new LinkedList<ArrayList<int[]>>();
    public boolean isleaf;
    public int position;

    public Node(){}
    public Node(ArrayList<int[]> key){keys.add(key);}
    public Node(LinkedList<ArrayList<int[]>> keys){this.keys = keys;}

    public int sizeOfNode(Node node){
        int size = 0;
        for(int i = 0; i < node.keys.size(); i++){
            for(int j = 0; j < node.keys.get(i).size(); j++) {
                size = size + j * 4 + node.keys.get(i).get(j)[0] * 4 + node.keys.get(i).get(j)[1] * 4;
            }
            size = size + i * 4;
        }
        return size;
    }
}