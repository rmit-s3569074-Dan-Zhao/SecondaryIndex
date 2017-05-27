import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/26.
 */
public class B_Tree {
    public static Integer M = 4096;
    public int position;

    private Node root;

    public void add(int[] key) {
        if (root == null) {
            root = new Node(key);
            root.position = 0;
            return;
        }
    }
    public Node getRoot(){
        return root;
    }

    public void add(Node node, int[] key) {
        if (node.keys.indexOf(key) != -1)
            return;
        //if the node is full, split it
        if (node.keys.size() >= M) {
            node = split(node);
        }
        if (node.children.size() > 0) {
            for (int i = 0; i < node.children.size(); i++) {
                if (node.children.get(i) != null) {
                    add(node.children.get(i), key);
                    System.out.println("New Node added");
                } else {
                    node.keys.add(i, key);
                    System.out.println("New Node added");
                }
            }
        } else {
            for (int i = 0; i < node.keys.size(); i++) {
                if (node.keys.get(i) == null) {
                    node.keys.add(i, key);
                    System.out.println("New Node added");
                }
            }
        }
    }

    //分裂的方法！！
    public Node split(Node node) {
        position++;
        if (node.keys.size() < (2 * M - 1)) {
            return node;
        }
        int n1 = M - 1 - 1;
        int n2 = n1 + 1;
        int n3 = 2 * M - 1 - 1;

        Node fatherNode = node.father;
        LinkedList<int[]> newNodeKeys = new LinkedList<int[]>();
        newNodeKeys.addAll(node.keys.subList(n2 + 1, n3 + 1));
        Node newNode = new Node(newNodeKeys);
        newNode.position = node.position + 1;

        List<int[]> lists = new LinkedList<int[]>();
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

            newChildren.addAll(node.children.subList(M, 2 * M));
            for (int i = 0; i < newChildren.size() - 1; i++) {
                newChildren.get(i).position = i;
                newChildren.get(i).father = newNode;
            }
            children.addAll(node.children.subList(0, M));
            newNode.children = newChildren;
            node.children.clear();
            ;
            node.children.addAll(children);
        }

        node.keys.clear();
        node.keys.addAll(lists);
        return split(fatherNode);
    }

    //search by given Hourly_Counts,aka the ID of ArrayList with int arrays of locations
    public int[] searchNode(B_Tree index, int Hourly_Counts, int position) {
        System.out.println("Searching...");
        LinkedList<int[]> target = index.root.keys;
        if (position == 0) {
            for (int i = 0; i < M; i++) {
                if (target.get(i)[0] == Hourly_Counts)
                    return target.get(i);
            }
        } else {
            for (int j = 0; j < M; j++) {
                if (j + 1 == position || j == Hourly_Counts)
                    return target.get(j);
            }
        }
        return null;
    }

    public int getPosition() {
        return position;
    }
}
