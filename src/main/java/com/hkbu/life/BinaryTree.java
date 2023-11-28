package com.hkbu.life;
import java.util.ArrayList;
import java.util.List;
class Node {

    private Node left, right;

    int value;

    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft(){
        if (String.valueOf(this.value).equals("null")){
            return null;
        }
        return left;
    }

    public Node getRight(){
        if (String.valueOf(this.value).equals("null")){
            return null;
        }
        return right;
    }
    @Override
    public String toString(){
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Integer getValue() {
        return value;
    }
}

public class BinaryTree {
    // storing Nodes in ArrayList
    private ArrayList<Node> binaryTree;
    private int root = Integer.MIN_VALUE;

    public BinaryTree(){
        this.binaryTree = new ArrayList<>();
    }

    // add node and link to parent node, i.e.
    public void addNode(Node node){
        if (binaryTree.isEmpty())
        {
            root = 0;
        }
        binaryTree.add(node);
        linkParent(node);
    }

    // link to parent node after adding new node(calculate index automatically
    public void linkParent(Node node){
        int index = binaryTree.indexOf(node);
        int parent_index = (index-1)/2;
        Node parent = binaryTree.get(parent_index);
        if (index % 2 == 1){
            parent.setLeft(node);
        }
        else{
            parent.setRight(node);
        }
    }

    public void printBinaryTree(){
        int size = binaryTree.size();
        int height = (int)Math.floor(Math.log(size)/Math.log(2));
        for (int i = 0; i <= height; i++) {
            System.out.print("level " + i + " : ");
            List<Node> nodeList = getNodesByLevel(i);
            for (Node node:nodeList) {
                System.out.print(node + "\t");
            }
            System.out.println();
        }
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

    // searching for the node values and return index if existed in binary tree
    // i.e. 33, 22, 39
    // getIndexOfNode(39) -> 2
    public int getIndexOfNode(int value){
        List<Node> t = binaryTree.stream().filter(n -> n.getValue() == value).toList();
        if(t.isEmpty()){
            return -1;
        }
        return binaryTree.indexOf(t.get(0));
    }

    // use for question three.
    // given two values (not index), return if they belong to same level
    public boolean checkSameLevel(int valueOne, int valueTwo){
        int indexOne = getIndexOfNode(valueOne);
        int indexTwo = getIndexOfNode(valueTwo);

        int levelOne = (int)Math.floor(Math.log(indexOne+1)/Math.log(2));
        int levelTwo = (int)Math.floor(Math.log(indexTwo+1)/Math.log(2));

        return levelOne==levelTwo;
    }

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

    // return true if tree is binary search tree structure
    public String checkBinarySearchTree(){
        int maxIndex = binaryTree.size();
        for (int i = maxIndex/2-1; i >= 0; i--) {
            if(!checkChild(i)){
                return "NO this tree is NOT a binary search tree.";
            }
        }
        return "This tree is a binary search tree.";
    }
    // Question 1 or a start
    public int getHeight() {
        int size = binaryTree.size();
        return (int)Math.floor(Math.log(size)/Math.log(2));
    }
    // Question 1 or a closed

    // Question 2 or b start
    public void printNodesInReverseOrder() {
        int size = binaryTree.size();
        int height = (int)Math.floor(Math.log(size)/Math.log(2));
        // wrap it with for loop in reverse order
        for (int i = height; i >= 0; i--) {
            System.out.print("level " + i + " : ");
            List<Node> nodeList = getNodesByLevel(i);
            for (Node node: nodeList) {
                System.out.print(node + "\t");
            }
            System.out.println();
        }
    }
    // Question 2 or b closed


    // Question 3
    public String checkCousin(int nodeOne, int nodeTwo) {
        if (checkSameLevel(nodeOne, nodeTwo)) {
            // get node index
            int indexOne = getIndexOfNode(nodeOne);
            int indexTwo = getIndexOfNode(nodeTwo);

            // calculation of parent node index
            int parentOfOne = (indexOne - 1) / 2;
            int parentOfTwo = (indexTwo - 1) / 2;
            if (parentOfOne != parentOfTwo){
                return nodeOne+" and " + nodeTwo+" are cousins.";
            }
        }
        return nodeOne + " and " + nodeTwo + " are not cousins.";
    }
    // End of Question 3

    public static void main(String[] args) throws RuntimeException{
        BinaryTree bt = new BinaryTree();

        bt.addNode(new Node(33));
        bt.addNode(new Node(22));
        bt.addNode(new Node(39));
        bt.addNode(new Node(13));
        bt.addNode(new Node(28));
        bt.addNode(new Node(36));
        bt.addNode(new Node(43));
        bt.addNode(new Node(44));

        bt.printBinaryTree();
        System.out.println("Binary Search Tree? "+bt.checkBinarySearchTree());


        BinaryTree bt_two = new BinaryTree();

        bt_two.addNode(new Node(33));
        bt_two.addNode(new Node(22));
        bt_two.addNode(new Node(39));
        bt_two.addNode(new Node(13));
        bt_two.addNode(new Node(28));
        bt_two.addNode(new Node(36));
        bt_two.addNode(new Node(43));
        bt_two.addNode(new Node(57));
        bt_two.addNode(new Node(63));

        bt_two.printBinaryTree();
        // ---------Question One---------
        System.out.println("----------Question One----------");
        System.out.println("Height of tree: " + bt.getHeight());

        // ---------Question Two---------
        System.out.println("----------Question Two----------");
        bt.printNodesInReverseOrder();
        System.out.println("----------Tree Two----------");
        bt_two.printNodesInReverseOrder();

        // ---------Question Three-------
        System.out.println("----------Question Three----------");
        System.out.println("----------Tree One----------");
        System.out.println(bt.checkCousin(33,36));
        System.out.println(bt.checkCousin(43,44));
        System.out.println(bt.checkCousin(13,36));
        System.out.println("----------Tree Two----------");
        System.out.println(bt_two.checkCousin(39,13));
        System.out.println(bt_two.checkCousin(63,33));
        System.out.println(bt_two.checkCousin(63,57));

        // ---------Question Four---------
        System.out.println("----------Question Four-----------");
        System.out.println(bt.checkBinarySearchTree());
        System.out.println(bt_two.checkBinarySearchTree());
    }
}

