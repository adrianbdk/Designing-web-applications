package zad4;
import java.util.ArrayList;

class Node<T> {
    private T item;
    private Node<T> leftChild;
    private Node<T> rightChild;

    Node(T value) {
        this.item = value;
        rightChild = null;
        leftChild = null;
    }

    public T getElement() {
        return item;
    }

    public void setElement(T value) {
        this.item = value;
    }

    public Node<T> getLeftChild(){
        return leftChild;
    }

    public Node<T> getRightChild(){
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void getItemsAscending(ArrayList<T> ascendingItems){
        if (leftChild != null) leftChild.getItemsAscending(ascendingItems);
        ascendingItems.add(item);
        if (rightChild != null) rightChild.getItemsAscending(ascendingItems);
    }

    public void getItemsDescending(ArrayList<T> ascendingItems){
        if (rightChild != null) rightChild.getItemsDescending(ascendingItems);
        ascendingItems.add(item);
        if (leftChild != null) leftChild.getItemsDescending(ascendingItems);
    }

}