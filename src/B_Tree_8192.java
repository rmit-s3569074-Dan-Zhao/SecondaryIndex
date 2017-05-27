import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
public class B_Tree_8192 {
    public static Integer M = 8192;
    public int position;

    public Node root;

    public void add(int[] key) {
        if (root == null) {
            root = new Node(key);
            root.position = 0;
            return;
        }
    }

    public Node getRoot() {
        return root;
    }

    public void add(Node node, int[] key) {
        if (node.keys.indexOf(key) != -1)
            return;
        //if the node is full, split it
        if (node.keys.size() >= M) {
            node = split(node);
            System.out.println("Node splited");
        }
        if (node.children.size() > 0) {
            for (int i = 0; i < node.children.size(); i++) {
                if (node.children.get(i) != null) {
                    add(node.children.get(i), key);
                } else {
                    node.keys.add(i, key);
                }
            }
        } else {
            node.keys.add(key);
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

    //search by given Hourly_Counts
    public LinkedList<int[]> searchNode(B_Tree_8192 index, int Hourly_Counts, int position) {
        System.out.println("Searching...");
        Node Entry = index.root;
        LinkedList<int[]> results = new LinkedList<int[]>();
        if (position == 0) {
            for (int i = 0; i < root.keys.size(); i++) {
                if (root.keys.get(i)[0] == Hourly_Counts) {
                    System.out.println("找见了" + i);
                    results.add(root.keys.get(i));
                }
            }
        } else {
            for (int j = 0; j < root.children.size(); j++) {
                if (j + 1 == position || root.children.get(j) != null) {
                    for (int i = 0; i < root.children.get(j).keys.size(); i++) {
                        if (root.children.get(j).keys.get(i)[0] == Hourly_Counts) {
                            results.add(root.children.get(j).keys.get(i));
                        }
                    }
                }
            }
        }
        System.out.println("Found nothing");
        return results;
    }

    public int getPosition() {
        return position;
    }
}
