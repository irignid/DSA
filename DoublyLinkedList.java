import java.util.NoSuchElementException;

public class DoublyLinkedList {
    private static class Node {
        int data;
        Node prev, next;
        Node(int data) { this.data = data; }
    }

    private Node head, tail;
    private int size;

    // ---------- insert ----------

    public void insertFirst(int x) {
        Node n = new Node(x);
        n.next = head;
        if (head != null) head.prev = n;
        else tail = n;                 // list was empty
        head = n;
        size++;
    }

    public void insertLast(int x) {
        Node n = new Node(x);
        n.prev = tail;
        if (tail != null) tail.next = n;
        else head = n;                 // list was empty
        tail = n;
        size++;
    }

    public void insertAtPosition(int pos, int x) {
        if (pos < 0 || pos > size) throw new IndexOutOfBoundsException("pos " + pos);
        if (pos == 0) { insertFirst(x); return; }
        if (pos == size) { insertLast(x); return; }
        insertBeforeNode(nodeAt(pos), x);   // pushes the current node at pos to pos+1
    }

    /** Inserts x after the first node holding key. Returns false if key not found. */
    public boolean insertAfter(int key, int x) {
        Node c = find(key);
        if (c == null) return false;
        if (c == tail) { insertLast(x); return true; }
        Node n = new Node(x);
        n.prev = c;
        n.next = c.next;
        c.next.prev = n;   // the old next now points back at n
        c.next = n;
        size++;
        return true;
    }

    /** Inserts x before the first node holding key. Returns false if key not found. */
    public boolean insertBefore(int key, int x) {
        Node c = find(key);
        if (c == null) return false;
        insertBeforeNode(c, x);
        return true;
    }

    // ---------- delete ----------

    public int deleteFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        int v = head.data;
        head = head.next;
        if (head == null) tail = null;   // list became empty
        else head.prev = null;
        size--;
        return v;
    }

    public int deleteLast() {
        if (tail == null) throw new NoSuchElementException("List is empty");
        int v = tail.data;
        tail = tail.prev;
        if (tail == null) head = null;   // list became empty
        else tail.next = null;
        size--;
        return v;
    }

    public int deleteAtPosition(int pos) {
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException("pos " + pos);
        return unlink(nodeAt(pos));
    }

    /** Deletes the first node holding key. Returns false if not found. */
    public boolean deleteByValue(int key) {
        Node c = find(key);
        if (c == null) return false;
        unlink(c);
        return true;
    }

    // ---------- helpers ----------

    private void insertBeforeNode(Node c, int x) {
        if (c == head) { insertFirst(x); return; }
        Node n = new Node(x);
        n.next = c;
        n.prev = c.prev;
        c.prev.next = n;   // the old previous now points forward to n
        c.prev = n;
        size++;
    }

    /** Removes any node in O(1) once you hold a reference to it. */
    private int unlink(Node c) {
        if (c == head) return deleteFirst();
        if (c == tail) return deleteLast();
        c.prev.next = c.next;
        c.next.prev = c.prev;
        size--;
        return c.data;
    }

    private Node find(int key) {
        for (Node c = head; c != null; c = c.next) if (c.data == key) return c;
        return null;
    }

    /** Walks from whichever end is closer. */
    private Node nodeAt(int pos) {
        Node c;
        if (pos < size / 2) {
            c = head;
            for (int i = 0; i < pos; i++) c = c.next;
        } else {
            c = tail;
            for (int i = size - 1; i > pos; i--) c = c.prev;
        }
        return c;
    }

    public boolean contains(int key) { return find(key) != null; }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void displayForward() {
        StringBuilder sb = new StringBuilder("null <-> ");
        for (Node c = head; c != null; c = c.next) sb.append(c.data).append(" <-> ");
        System.out.println(sb.append("null"));
    }

    public void displayBackward() {
        StringBuilder sb = new StringBuilder("null <-> ");
        for (Node c = tail; c != null; c = c.prev) sb.append(c.data).append(" <-> ");
        System.out.println(sb.append("null"));
    }

    public static void main(String[] args) {
        DoublyLinkedList l = new DoublyLinkedList();
        l.insertFirst(2); l.insertFirst(1);   // 1 <-> 2
        l.insertLast(4);                      // 1 <-> 2 <-> 4
        l.insertAtPosition(2, 3);             // 1 <-> 2 <-> 3 <-> 4
        l.insertAfter(4, 5);                  // 1 <-> 2 <-> 3 <-> 4 <-> 5
        l.insertBefore(1, 0);                 // 0 <-> 1 <-> 2 <-> 3 <-> 4 <-> 5
        l.displayForward();
        l.displayBackward();                  // checks every prev pointer
        l.deleteFirst();                      // 1 <-> 2 <-> 3 <-> 4 <-> 5
        l.deleteLast();                       // 1 <-> 2 <-> 3 <-> 4
        l.deleteAtPosition(1);                // 1 <-> 3 <-> 4
        l.deleteByValue(3);                   // 1 <-> 4
        l.displayForward();
        l.displayBackward();
        System.out.println(l.size());         // 2
    }
}
