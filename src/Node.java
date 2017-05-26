import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/26.
 */
public class Node{
    public Node root;
    public Node father;
    public LinkedList<Node> children = new LinkedList<Node>();
    public LinkedList<int[]> keys = new LinkedList<int[]>();
    public boolean isleaf;
    public int position;

    public Node(){}
    public Node(int[] key){keys.add(key);}
    public Node(LinkedList<int[]> keys){this.keys = keys;}

    public int sizeOfNode(Node node){
        int size = 0;
        for(int i = 0;i<node.keys.size();i++){
            size = i * 4 + node.keys.get(i)[0] * 4 + node.keys.get(i)[1] * 4;
        }
        return size;
    }
}