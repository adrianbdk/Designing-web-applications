import java.util.Comparator;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root = null;
    private Comparator<T> comparator = null;

    public void addNode(T value) {
        this.root = addRecursive(root, value);
    }

    private Node<T> addRecursive(Node<T> current, T value) {
        if (current == null)
            return new Node<T>(value);

        if (comparator != null) {
            if (comparator.compare(value, current.getElement()) < 0) {
            }
        }
    }
}
