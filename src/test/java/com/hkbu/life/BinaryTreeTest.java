package com.hkbu.life;

import static org.junit.Assert.*;

class BinaryTreeTest {

    void addNode() {
    }

    void printBinaryTree() {
    }

    @org.junit.jupiter.api.Test
    void checkSameLevel() {
        BinaryTree bt = new BinaryTree();
        bt.addSampleNode();
        assertEquals(bt.checkSameLevel(3, 7), false);
        assertEquals(bt.checkSameLevel(3, 5), true);
        assertEquals(bt.checkSameLevel(4, 5), true);
        assertEquals(bt.checkSameLevel(0, 1), false);
        assertEquals(bt.checkSameLevel(0, 3), false);
        assertEquals(bt.checkSameLevel(0, 5), false);
        assertEquals(bt.checkSameLevel(7, 16), false);
    }
}