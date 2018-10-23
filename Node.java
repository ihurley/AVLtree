public class Node<Key extends Comparable<Key>, Value> {

    public Key key;
    public Value value;
    public Node<Key, Value> left;
    public Node<Key, Value> right;
    public boolean color;
    public int size;
    public static final boolean RED = false;
    public static final boolean BLACK = true;

    public Node(Key key, Value value,boolean color,int size) {
        this.key = key;
        this.value = value;
        this.color=color;
        this.size = size;
    }

    @Override
    public String toString() {
        String color = "black";
        if (this.color ==(RED)){color = "red";}
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                "}, color= "+ color;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Node<Key, Value> getLeft() {
        return left;
    }

    public void setLeft(Node<Key, Value> left) {
        this.left = left;
    }

    public Node<Key, Value> getRight() {
        return right;
    }

    public void setRight(Node<Key, Value> right) {
        this.right = right;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
