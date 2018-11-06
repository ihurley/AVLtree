/**
 * Name: Izzy Hurley
 * Lab Name: AVT
 * Lab Purpose: The goal of AVT is to create a program
 * Date: 10/23/18
 * Collaborators: None
 * ON MY HONOR: IH
 */
import java.lang.Math.*;
import java.util.*;
public class AVLTree <Key extends Comparable<Key>, Value> {
        public Node root;     // root of the BST
        /**
         * Initializes an empty symbol table.
         */
        public AVLTree() {}

        public AVLTree(Node Root) {
            root = Root;
        }

        // number of node in subtree rooted at x; 0 if x is null
        private int size(Node x) {
            if (x == null) return 0;
            return x.size;
        }


        /**
         * Returns the number of key-value pairs in this symbol table.
         *
         * @return the number of key-value pairs in this symbol table
         */
        public int size() {
            return size(root);
        }

        /**
         * Is this symbol table empty?
         *
         * @return {@code true} if this symbol table is empty and {@code false} otherwise
         */
        public boolean isEmpty() {
            return root == null;
        }


        /***************************************************************************
         *  Standard BST search.
         ***************************************************************************/

        /**
         * Returns the value associated with the given key.
         *
         * @param key the key
         * @return the value associated with the given key if the key is in the symbol table
         * and {@code null} if the key is not in the symbol table
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to get() is null");
                return get(root, key);
        }

        // value associated with the given key in subtree rooted at x; null if no such key
        private Value get(Node x, Key key) {
            while (x != null) {
                int cmp = key.compareTo((Key)(x.key));
                if (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
                else return (Value)x.value;
            }
            return null;
        }

        /**
         * Does this symbol table contain the given key?
         *
         * @param key the key
         * @return {@code true} if this symbol table contains {@code key} and
         * {@code false} otherwise
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public boolean contains(Key key) {
            return get(key) != null;
        }

        /***************************************************************************
         *  Red-black tree insertion.
         ***************************************************************************/

        /**
         * Inserts the specified key-value pair into the symbol table, overwriting the old
         * value with the new value if the symbol table already contains the specified key.
         * Deletes the specified key (and its associated value) from this symbol table
         * if the specified value is {@code null}.
         *
         * @param key the key
         * @param val the value
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void put(Key key, Value val) {
            if (key == null) throw new IllegalArgumentException("first argument to put() is null");
            if (val == null) {
                delete(key);
                return;
            }

            root = balance(put(root, key, val));
            // assert check();
        }

         //insert the key-value pair in the subtree rooted at h
        private Node put(Node h, Key key, Value val) {

       Node x=  new Node(key,val,height());
      if (h == null){return new Node(key,val,0);}
      //WHAT TO RETURN FOR THESE
      if(x.getKey().compareTo(h.getKey())<0){
          if(h.getLeft()==null){
              h.setLeft(x);
          }else{
              h.setRight(put(h.getLeft(), key, val));
          }
      }
      else if(x.getKey().compareTo(h.getKey())>0){
          if(h.getRight()==null){
              h.setRight(x);
          }
          else{
              h.setRight(put(h.getRight(), key, val));
          }
      }
      else if (h!=null && x.getKey().compareTo(h.getKey())==0){
            h.setValue(val); return h;
      }
      return h;
      }
    /*  The current node must be one of the ancestors of the newly inserted node. Update the height of the current node.

        }


         * Removes the smallest key and associated value from the symbol table.
         *
         * @throws NoSuchElementException if the symbol table is empty
         */
        public void deleteMin() {
            if (isEmpty()) throw new NoSuchElementException("BST underflow");

            root = deleteMin(root);
            // assert check();
        }

        // delete the key-value pair with the minimum key rooted at h
        private Node deleteMin(Node h) {
        /**deleteMin**/
            return balance(h);
        }


        /**
         * Removes the largest key and associated value from the symbol table.
         *
         * @throws NoSuchElementException if the symbol table is empty
         */
        public void deleteMax() {
            if (isEmpty()) throw new NoSuchElementException("BST underflow");

            // if both children of root are black, set root to red

            root = deleteMax(root);
            // assert check();
        }

            // delete the key-value pair with the maximum key rooted at h
        private Node deleteMax(Node h) {
            /**deleteMax**/

            return balance(h);
        }

        /**
         * Removes the specified key and its associated value from this symbol table
         * (if the key is in this symbol table).
         *
         * @param key the key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to delete() is null");
            if (!contains(key)) return;

            // if both children of root are black, set root to red

            root = delete(root, key);
            // assert check();
        }

        // delete the key-value pair with the given key rooted at h
        private Node delete(Node h, Key key) {
             assert get(h, key) != null;

            if(h == null) return null;
            int i = key.compareTo((Key) h.getKey());

            if( i < 0) {
                h.setLeft(delete(h.getLeft(), key)); return balance(h);
            } else if(i > 0) {
                h.setRight(delete(h.getRight(), key)); return balance(h); }

                else {
                if(h.getRight() == null) return h.getLeft();
                if(h.getLeft() == null) return h.getRight();
                Node min = min(h.getRight());
                min.setLeft(h.getLeft());
                h = h.getRight(); //works because of recursive balance
            }
            h = balance(h);
            h.setSize(size(h.getRight()) + size(h.getLeft()) + 1);
            return h;

        }
//        public Node rotateRight(Node h) {
//        /**ROTATE RIGHT**/
//            if(h.left==null || h.left.right==null){
//                return null;
//            }
//            Node nRoot = h.left;
//            Node orphan = h.left.right;
//            h.setLeft(orphan);
//            nRoot.setRight(h);
//
//            return nRoot;
//        }
        private Node rotateRight(Node n) {
            Node r = n.left;
            n.left = r.right;
            r.right = n;
            n.size = Math.max(height(n.left), height(n.right)) + 1;
            r.size = Math.max(height(r.left), height(r.right)) + 1;
            return r;
        }
        private Node rotateLeft(Node n) {
            Node r = n.right;
            n.right = r.left;
            r.left = n;
            n.size = Math.max(height(n.left), height(n.right)) + 1;
            r.size = Math.max(height(r.left), height(r.right)) + 1;
            return r;
        }
        // make a right-leaning link lean to the left
//        public Node rotateLeft(Node h) {
//        /**ROTATE LEFT**/
//            if(h.right.left==null){
//                return null;
//            }
//            Node nRoot = h.right;
//            Node orphan = h.right.left;
//            h.setRight(orphan);
//            nRoot.setLeft(h);
//
//            return nRoot;
//
//
//
//        }
        public static boolean isGParent(Node n){
            if (n.right != null && (n.right.right != null || n.right.left != null)) {
                return true;
            } else if (n.left != null && (n.left.right != null || n.left.left != null)) {
                return true;
            } else return false;
        }
        private Node balance(Node h) {

            assert (h != null && height(h)>1);
        //update h's size
            if(Math.abs(height(h.getRight())-height(h.getLeft()))>2){
                if (height(h.getRight()) > height(h.getLeft())) {
                    h = rotateLeft(h);
                } else
                h = rotateRight(h);
            }
            else{
                return h;
            }
            return h;

        }


        /***************************************************************************
         *  Utility functions.
         ***************************************************************************/

        /**
         * Returns the height of the BST (for debugging).
         *
         * @return the height of the BST (a 1-node tree has height 0)
         */
        public int height() {
            return height(root);
        }

        private int height(Node x) {
            if (x == null) return -1;
            return 1 + Math.max(height(x.left), height(x.right));
        }



        /***************************************************************************
         *  Ordered symbol table methods.
         ***************************************************************************/

        /**
         * Returns the smallest key in the symbol table.
         *
         * @return the smallest key in the symbol table
         * @throws NoSuchElementException if the symbol table is empty
         */
        public Key min() {
            if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
            return (Key)min(root).key;
        }
        public String toString(){
            return toString(root, "");
        }
        private String toString(Node node, String x){

            x+=(node.toString());
            if(node.getLeft()!=null) {
                x += toString(node.getLeft(), "");
            }if(node.getRight()!=null){
                x += toString(node.getRight(), "");
            }
            return x;
        }

        // the smallest key in subtree rooted at x; null if no such key
        private Node min(Node x) {
        // assert x != null;
            if (x.left == null) return x;
            else return min(x.left);
        }

        /**
         * Returns the largest key in the symbol table.
         *
         * @return the largest key in the symbol table
         * @throws NoSuchElementException if the symbol table is empty
         */
        public Key max() {
            if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
            return (Key)max(root).key;
        }

        // the largest key in the subtree rooted at x; null if no such key
        private Node max(Node x) {
        // assert x != null;
            if (x.right == null) return x;
            else return max(x.right);
        }
        public boolean isAVL(){
            //code
            return false;
        }
    }

