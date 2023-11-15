package com.hkbu.life;
import com.hkbu.life.Node.Node;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class BinaryTree {
    private static ArrayList<Node> binaryTree;
    private int root = Integer.MIN_VALUE;

    public BinaryTree(){
        this.binaryTree = new ArrayList<>();
    }

    public void addNode(Node node){
        if (binaryTree.isEmpty())
        {
            root = 0;
        }
        binaryTree.add(node);
        linkParent(node);
    }

    public void linkParent(Node node){
        int index = binaryTree.indexOf(node);
        int parent_index = (index-1)/2;
        Node parent = binaryTree.get(parent_index);
        if (index % 2 == 1){
            parent.left=node;
        }
        else{
            parent.right=node;
        }
    }

    public Node getRoot(){
        return binaryTree.get(root);
    }

    public int getSize(){
        return binaryTree.size();
    }

    public void printBinaryTree(){
        if(root < 0){
            System.out.println("Binary Tree is Empty");
            return;
        }
        int left = 0;
        int temp = left * 2 + 1;
        int right = binaryTree.size();
        int level = 0;
        do {
            System.out.print("level " + level + " : ");
            for (int i=left; i<temp; i++){
                if(i>=right){break;}
                System.out.print(binaryTree.get(i) + "\t");
            }
            level++;
            left = temp;
            temp = temp *2+1;
            System.out.println();
        }
        while (left < right);
    }

    // can use this to printing in reverse level order
    public List<Node> getNodesByLevel(int level){

        int startIndex = (int)Math.pow(2, level) -1 ;
        if(startIndex > binaryTree.size()){
            throw new IndexOutOfBoundsException("Entered level is greater than tree size.");
        }
        int endIndex = Math.min((int)Math.pow(2, level+1) -1, binaryTree.size());
        return binaryTree.subList(startIndex, endIndex);
    }

    // return index if given value exists
    public int getIndex(int value){
        List<Node> t = binaryTree.stream().filter(n -> n.getValue() == value).toList();
        if(t.isEmpty()){
            return -1;
        }
        return binaryTree.indexOf(t.get(0));
    }

    // given two values (not index), return if they belongs to same level
    public boolean checkSameLevel(int valueOne, int valueTwo){
        int indexOne = getIndex(valueOne);
        int indexTwo = getIndex(valueTwo);

        System.out.println("index of value One: " + indexOne);
        System.out.println("index of value Two: " + indexTwo + "\n");

        int levelOne = (int)Math.floor(Math.log(indexOne+1)/Math.log(2));
        int levelTwo = (int)Math.floor(Math.log(indexTwo+1)/Math.log(2));

        return levelOne==levelTwo;
    }

    // for testing purpose, use in BinaryTreeTest.java
    // src/test/java/com/hkbu/life/BinaryTreeTest.java
    public void addSampleNode(){
        for (int i = 0; i <= 16; i++) {
            addNode(new Node(i));
        }
    }

//    return true if parent is greater than both child.
    public boolean checkChild(int parentIndex){
        int parentValue = binaryTree.get(parentIndex).getValue();
        int rightChildIndex = parentIndex*2+2;
        Integer rightChildValue = Integer.MAX_VALUE;
        if (rightChildIndex < binaryTree.size()){
            Node rightChild = binaryTree.get(rightChildIndex);
            rightChildValue= rightChild.getValue();
        }
        int leftChildIndex = parentIndex*2+1;
        int leftChildValue = binaryTree.get(leftChildIndex).getValue();
        return (rightChildValue != null && parentValue < rightChildValue && parentValue > leftChildValue);
    }

    public String checkBinarySearchTree(){
        int maxIndex = binaryTree.size();
        System.out.println(maxIndex);
        for (int i = maxIndex/2-1; i >= 0; i--) {
            if(!checkChild(i)){
                return "NOT";
            }
        }
        return "it is binary search tree";
    }

    // for development testing only
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
            // Press Ctrl+D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Cmd+F8.
        BinaryTree bt = new BinaryTree();

//        bt.addSampleNode();
        bt.addNode(new Node(33));
        bt.addNode(new Node(22));
        bt.addNode(new Node(39));
        bt.addNode(new Node(13));
        bt.addNode(new Node(28));
        bt.addNode(new Node(36));
        bt.addNode(new Node(43));

        bt.printBinaryTree();
//        bt.addNode(new Node(3));
        System.out.println(bt.checkBinarySearchTree());

        // figuring out Null pointer part (leo)
//        System.out.println(root.left.left.right.right);

//        for (int i = ; i <; i++) {
//
//        }
        System.out.println(bt.getIndex(8));
    }
}