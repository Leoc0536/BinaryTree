package com.hkbu.life.Node;

public class Node {

    public Node left, right;

    int value;

    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
