import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/26.
 */
public class Node{
    public Node root;
    public Node father;
    public LinkedList<Node> children = new LinkedList<Node>();
    public LinkedList<Integer> keys = new LinkedList<Integer>();
    public boolean isleaf;
    public int position;

    public Node(){}
    public Node(int key){keys.add(key);}
    public Node(LinkedList<Integer> keys){this.keys = keys;}
}