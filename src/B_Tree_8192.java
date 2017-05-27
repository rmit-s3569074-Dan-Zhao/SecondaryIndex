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

    //search by given Hourly_Counts
    public LinkedList<int[]> searchNode(B_Tree_8192 index, int Hourly_Counts, int position) {
        System.out.println("Searching...");
        Node Entry = index.root;
        LinkedList<int[]> results = new LinkedList<int[]>();
        if (position == 0) {
            for (int i = 0; i < root.keys.size(); i++) {
                if (root.keys.get(i)[0] == Hourly_Counts) {
                    //System.out.println("找见了" + i);
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
        return results;
    }

    public int getPosition() {
        return position;
    }
}
