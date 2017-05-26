import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/26.
 */
public class B_Tree {
    Heapfile hf;
    public static Integer M;

    public B_Tree(){
        //using simulated data to test how many key value pairs can be stored in one node
        Node test = new Node();
        Random random = new Random();
        if(test.sizeOfNode(test)<4096){
            //testValue: page,line
            //According to the heapfile, the last page is page 31207
            //Approximately 60 records in each page
            int[] testValue = {random.nextInt(31207),random.nextInt(60)};
            test.keys.add(testValue);
        } else {
            System.out.println("According to simulate test, one node(4096 Byte) can have " + test.keys.size() + "key value pairs in it.");
            M = test.keys.size();
        }
    }
    private Node root;
    public void add(int[] key) {
        if (root == null) {
            root = new Node(key);
            root.position = 0;
            return;
        }
    }

    public void add(Node node, int[] key) {
        if (node.keys.indexOf(key) != -1)
            //if the key is already there...SO STH HAPPENDS
            return;
        //if the node is full, split it
        if (node.keys.size() >= (2 * B_Tree.M - 1)) {
            node = split(node);
        }
        //use binary search to check the index of new node
        int index = binarySearch(node, key);

        if (node.children.size() > 0) {
            if (node.children.get(index) != null) {
                add(node.children.get(index), key);
            } else {
                node.keys.add(index, key);
            }
        } else {
            node.keys.add(index, key);
        }
    }

    //分裂的方法！！
    public Node split(Node node) {
        if (node.keys.size() < (2 * B_Tree.M - 1)) {
            return node;
        }
        int n1 = B_Tree.M - 1 - 1;
        int n2 = n1 + 1;
        int n3 = 2 * B_Tree.M - 1 - 1;

        Node fatherNode = node.father;
        LinkedList<int[]> newNodeKeys = new LinkedList<>();
        newNodeKeys.addAll(node.keys.subList(n2 + 1, n3 + 1));
        Node newNode = new Node(newNodeKeys);
        newNode.position = node.position + 1;

        List<int[]> lists = new LinkedList<>();
        lists.addAll(node.keys.subList(0, n1 + 1));

        if (fatherNode == null) {
            fatherNode = new Node();
            fatherNode.keys.add(node.keys.get(n2));
            node.father = fatherNode;
            fatherNode.children.add(0, node);
            newNode.father = fatherNode;
            fatherNode.children.add(1, newNode);
            fatherNode.position = 0;
            root = fatherNode;
        } else {
            fatherNode.keys.add(node.position, node.keys.get(n2));
            newNode.father = fatherNode;
            fatherNode.children.add(node.position + 1, newNode);
            //没听懂下头是在干啥
            for (int i = node.position + 2; i < fatherNode.children.size() - 1; i++) {
                fatherNode.children.get(i).position = i;
            }
        }

        if (node.children.size() > 0) {
            LinkedList<Node> newChildren = new LinkedList<Node>();
            LinkedList<Node> children = new LinkedList<Node>();

            newChildren.addAll(node.children.subList(B_Tree.M, 2 * B_Tree.M));
            for (int i = 0; i < newChildren.size() - 1; i++) {
                newChildren.get(i).position = i;
                newChildren.get(i).father = newNode;
            }
            children.addAll(node.children.subList(0, B_Tree.M));
            newNode.children = newChildren;
            node.children.clear();
            ;
            node.children.addAll(children);
        }

        node.keys.clear();
        ;
        node.keys.addAll(lists);

        return split(fatherNode);
    }

    //the binary search
    public int binarySearch(Node node, int[] key) {
        int index = 0;

        return index;
    }
}
