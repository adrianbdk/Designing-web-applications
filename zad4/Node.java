class Node<T> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;

    Node(T value) {
        this.value = value;
        rightChild = null;
        leftChild = null;
    }

    public T getElement() {
        return value;
    }

    public void setElement(T value) {
        this.value = value;
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

}