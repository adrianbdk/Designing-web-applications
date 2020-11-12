package zad4;
import java.util.ArrayList;
import java.util.Comparator;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root = null;
    private Comparator<T> comparator = null;


    public BinaryTree(){
        root = null;
        comparator = Comparator.naturalOrder();
    }

    public BinaryTree(Comparator<T> comparator){
        this();
        this.comparator = comparator;
    }


    public void addNode(T value) {
        this.root = addRecursive(root, value);
    }

    private Node<T> addRecursive(Node<T> current, T value) {
        if (current == null)
            return new Node<T>(value);

        if (comparator != null) {
            if (comparator.compare(value, current.getElement()) < 0) {
                current.setLeftChild(addRecursive(current.getLeftChild(), value));
            } else if(comparator.compare(value, current.getElement()) > 0) {
                current.setRightChild(addRecursive(current.getRightChild(), value));
            } else return current;
        }
        else {
            if(value.compareTo(current.getElement()) < 0) {
                current.setLeftChild(addRecursive(current.getLeftChild(), value));
            } else if(value.compareTo(current.getElement()) > 0) {
                current.setRightChild(addRecursive(current.getRightChild(), value));
            } else return current;
        }
        return current;
    }


    public ArrayList<T> getItemsAscending(){
        if (root == null)
            return null;

        ArrayList<T> ascendingItems = new ArrayList<>();
        root.getItemsAscending(ascendingItems);

        return ascendingItems;
    }

    public ArrayList<T> getItemsDescending(){
        if (root == null)
            return null;

        ArrayList<T> descendingItems = new ArrayList<>();
        root.getItemsDescending(descendingItems);

        return descendingItems;
    }
}
