import java.util.NoSuchElementException;

public class SinglyLinkedList {
    private static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node head;
    private int size;

    // ---------- insert ----------

    public void insertFirst(int x) {
        Node n = new Node(x);
        n.next = head;      // new node points at the old first node
        head = n;           // head now points at the new node
        size++;
    }

    public void insertLast(int x) {
        Node n = new Node(x);
        if (head == null) {
            head = n;
        } else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;   // stop at the last node
            cur.next = n;
        }
        size++;
    }

    public void insertAtPosition(int pos, int x) {
        if (pos < 0 || pos > size) throw new IndexOutOfBoundsException("pos " + pos);
        if (pos == 0) { insertFirst(x); return; }
        Node prev = head;
        for (int i = 0; i < pos - 1; i++) prev = prev.next;   // node before the spot
        Node n = new Node(x);
        n.next = prev.next;   // link the new node forward FIRST
        prev.next = n;        // then link prev to it (order matters!)
        size++;
    }

    /** Inserts x after the first node holding key. Returns false if key not found. */
    public boolean insertAfter(int key, int x) {
        Node cur = head;
        while (cur != null && cur.data != key) cur = cur.next;
        if (cur == null) return false;
        Node n = new Node(x);
        n.next = cur.next;
        cur.next = n;
        size++;
        return true;
    }

    /** Inserts x before the first node holding key. Returns false if key not found. */
    public boolean insertBefore(int key, int x) {
        if (head == null) return false;
        if (head.data == key) { insertFirst(x); return true; }
        Node prev = head;
        while (prev.next != null && prev.next.data != key) prev = prev.next;
        if (prev.next == null) return false;
        Node n = new Node(x);
        n.next = prev.next;
        prev.next = n;
        size++;
        return true;
    }

    // ---------- delete ----------

    public int deleteFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        int v = head.data;
        head = head.next;
        size--;
        return v;
    }

    public int deleteLast() {
        if (head == null) throw new NoSuchElementException("List is empty");
        if (head.next == null) {            // only one node
            int v = head.data;
            head = null;
            size--;
            return v;
        }
        Node prev = head;
        while (prev.next.next != null) prev = prev.next;   // second-to-last node
        int v = prev.next.data;
        prev.next = null;
        size--;
        return v;
    }

    public int deleteAtPosition(int pos) {
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException("pos " + pos);
        if (pos == 0) return deleteFirst();
        Node prev = head;
        for (int i = 0; i < pos - 1; i++) prev = prev.next;
        int v = prev.next.data;
        prev.next = prev.next.next;   // skip over the deleted node
        size--;
        return v;
    }

    /** Deletes the first node holding key. Returns false if not found. */
    public boolean deleteByValue(int key) {
        if (head == null) return false;
        if (head.data == key) { head = head.next; size--; return true; }
        Node prev = head;
        while (prev.next != null && prev.next.data != key) prev = prev.next;
        if (prev.next == null) return false;
        prev.next = prev.next.next;
        size--;
        return true;
    }

    // ---------- helpers ----------

    public boolean contains(int key) {
        for (Node c = head; c != null; c = c.next) if (c.data == key) return true;
        return false;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void display() {
        StringBuilder sb = new StringBuilder();
        for (Node c = head; c != null; c = c.next) sb.append(c.data).append(" -> ");
        System.out.println(sb.append("null"));
    }

    public static void main(String[] args) {
        SinglyLinkedList l = new SinglyLinkedList();
        l.insertFirst(2); l.insertFirst(1);   // 1 -> 2
        l.insertLast(4);                      // 1 -> 2 -> 4
        l.insertAtPosition(2, 3);             // 1 -> 2 -> 3 -> 4
        l.insertAfter(4, 5);                  // 1 -> 2 -> 3 -> 4 -> 5
        l.insertBefore(1, 0);                 // 0 -> 1 -> 2 -> 3 -> 4 -> 5
        l.display();
        l.deleteFirst();                      // 1 -> 2 -> 3 -> 4 -> 5
        l.deleteLast();                       // 1 -> 2 -> 3 -> 4
        l.deleteAtPosition(1);                // 1 -> 3 -> 4
        l.deleteByValue(3);                   // 1 -> 4
        l.display();
        System.out.println(l.size());         // 2
    }
}
