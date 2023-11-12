package com.hkbu.life;
import com.hkbu.life.Node.Node;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class BinaryTree {
    private ArrayList<Node> binaryTree;
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
        for (int i = 0; i <= 16; i++){
            binaryTree.add(new Node(i));
        }
    }

    // for development testing only
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
            // Press Ctrl+D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Cmd+F8.
        BinaryTree bt = new BinaryTree();

//        bt.addNode(new Node(3));
        bt.printBinaryTree();


        // figuring out Null pointer part (leo)
//        System.out.println(root.left.left.right.right);

//        for (int i = ; i <; i++) {
//
//        }
        bt.getIndex(8);
    }
}