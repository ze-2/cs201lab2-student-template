
public class SinglyLinkedList<E extends Comparable<E>> {

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {

        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {
        E lastMin = null;
        E lastMax = null;

        for (int k = 0; k < size / 2; k++) {
            Node<E> min = null, minPrev = null;
            Node<E> max = null, maxPrev = null;
            Node<E> prev = null;
            Node<E> current = head;
            while (current != null) {
                E e = current.getElement();
                if ((lastMin == null || e.compareTo(lastMin) > 0) && (min == null || e.compareTo(min.getElement()) < 0)) {
                    min = current;
                    minPrev = prev;
                }
                if ((lastMax == null || e.compareTo(lastMax) < 0) && (max == null || e.compareTo(max.getElement()) > 0)) {
                    max = current;
                    maxPrev = prev;
                }
                prev = current;
                current = current.getNext();
            }

            lastMin = min.getElement();
            lastMax = max.getElement();
            swapNodes(minPrev, min, maxPrev, max);
        }
    }

    private void swapNodes(Node<E> aPrev, Node<E> a, Node<E> bPrev, Node<E> b) {
        if (a == b) {
            return;
        }

        if (aPrev == null) {
            head = b;
        } else {
            aPrev.setNext(b);
        }
        if (bPrev == null) {
            head = a;
        } else {
            bPrev.setNext(a);
        }

        Node<E> temp = a.getNext();
        a.setNext(b.getNext());
        b.setNext(temp);

        if (a.getNext() == null) {
            tail = a;
        }
        if (b.getNext() == null) {
            tail = b;
        }
    }

}
