import java.util.NoSuchElementException;

/**
 * Name: Izzy Hurley
 * Lab Name: AVTDriver
 * Lab Purpose: The goal of AVTDriver is to create a program
 * Date: 10/23/18
 * Collaborators: None
 * ON MY HONOR: IH
 */
import java.util.NoSuchElementException;
import java.util.Queue;
public class AVTDriver <Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {

        Node root = new Node(10,10,0);
        AVLTree t = new AVLTree(root);
        t.put(9,9);
        t.put(8,8);
        t.put(7,7);
        t.put(6,6);
        t.put(5,5);
        System.out.println("put 9,8,7,6,5");
        System.out.println("Tree should balance and root should be 8 ----> root = "+ root.key);


    }
}