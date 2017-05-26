import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
public class B_Tree {
    public static Integer M = 3;
    private Node root;

    public void add(int key) {
        if (root == null) {
            root = new Node(key);
            root.position = 0;
            return;
        }
    }

    public void add(Node node, int key) {
        if (node.keys.indexOf(key) != -1)
            return;
        //如果结点满了就要分裂但是估计还得改条件
        if (node.keys.size() >= (2 * B_Tree.M - 1)) {
            node = split(node);
        }
        //二分查找，确定新结点要插入的位置
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
        int n1 = B_Tree.M - 1 - 1;//前半段
        int n2 = n1 + 1;
        int n3 = 2 * B_Tree.M - 1 - 1;//后半段

        Node fatherNode = node.father;
        LinkedList<Integer> newNodeKeys = new LinkedList<Integer>();
        newNodeKeys.addAll(node.keys.subList(n2 + 1, n3 + 1));
        Node newNode = new Node(newNodeKeys);
        newNode.position = node.position + 1;

        List<Integer> lists = new LinkedList<Integer>();
        lists.addAll(node.keys.subList(0,n1+1));//旧结点的关键字

        if(fatherNode == null){
            fatherNode = new Node();
            fatherNode.keys.add(node.keys.get(n2));//把中间位置提取出来
            node.father = fatherNode;
            fatherNode.children.add(0,node);
            newNode.father = fatherNode;
            fatherNode.children.add(1,newNode);
            fatherNode.position = 0;
            root = fatherNode;
        }else{
            fatherNode.keys.add(node.position,node.keys.get(n2));
            newNode.father = fatherNode;
            fatherNode.children.add(node.position+1,newNode);
            //没听懂下头是在干啥
            for(int i = node.position+2;i<fatherNode.children.size()-1;i++){
                fatherNode.children.get(i).position = i;
            }
        }

        if (node.children.size()>0){
            LinkedList<Node> newChildren = new LinkedList<Node>();
            LinkedList<Node> children = new LinkedList<Node>();

            newChildren.addAll(node.children.subList(B_Tree.M, 2 * B_Tree.M));
            for(int i = 0; i<newChildren.size() - 1;i++){
                newChildren.get(i).position = i;
                newChildren.get(i).father = newNode;
            }
            children.addAll(node.children.subList(0,B_Tree.M));
            newNode.children = newChildren;
            node.children.clear();;
            node.children.addAll(children);
        }

        node.keys.clear();;
        node.keys.addAll(lists);

        return split(fatherNode);
    }

    //二分查找的方法！！
    public int binarySearch(Node node, int key) {
        return key;
    }


    private class Entry {
        int key;
        int[] value;
        private Node next;     // helper field to iterate over array entries

        public Entry(int key, int[] value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
