package com.hkbu.life;
import com.hkbu.life.Node.Node;

import java.util.ArrayList;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class BinaryTree {
    private ArrayList<Node> binaryTree;
    private int root = Integer.MIN_VALUE;
    private int next_index = 0;

    public BinaryTree(){
        this.binaryTree = new ArrayList<>();
    }

    public void addNode(Node node){
        if (binaryTree.isEmpty())
        {
            root = next_index;
        }
        binaryTree.add(node);
        int parent_index = (next_index-1)/2;
        Node parent = binaryTree.get(parent_index);
        if (next_index % 2 == 1){
            parent.left=node;
        }
        else{
            parent.right=node;
        }
        next_index++;
    }

    public Node getRoot(){
        return binaryTree.get(root);
    }
    public void printBinaryTree(){
        if(root < 0){
            System.out.println("Binary Tree is Empty");
            return;
        }
//        for (int i = 0; i<binaryTree.size(); i++){
//            System.out.println("index " + i + " : " + binaryTree.get(i).getValue());
//        }

//        int left = binaryTree.size()/2;
//        int right = binaryTree.size()-1;
//        while (left < right){
//
//        }
        int left = 0;
        int temp = left * 2+1;
        int right = binaryTree.size();
        int level = 0;
        do {
            for (int i=left; i<temp; i++){
                if(i>=right){break;}
                System.out.println("level " + level + " : "+ binaryTree.get(i));
            }
            level++;
            left = temp;
            temp = temp *2+1;
        }
        while (left < right);
    }

    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
            // Press Ctrl+D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Cmd+F8.
        BinaryTree bt = new BinaryTree();
        for (int i = 0; i <= 15; i++){
            bt.addNode(new Node(i));
        }
//        bt.addNode(new Node(3));
        bt.printBinaryTree();

        Node root = bt.getRoot();
        System.out.println(root.left.left.right.right);
    }
}