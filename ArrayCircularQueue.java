import java.util.NoSuchElementException;

public class ArrayCircularQueue {
    private final int[] data;
    private int head = 0;
    private int size = 0;

    public ArrayCircularQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        data = new int[capacity];
    }

    public void enqueue(int x) {
        if (isFull()) throw new IllegalStateException("Queue is full");
        data[(head + size) % data.length] = x;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int v = data[head];
        head = (head + 1) % data.length;
        size--;
        return v;
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return data[head];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == data.length; }
    public int size() { return size; }

    public void display() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(data[(head + i) % data.length]);
        }
        System.out.println(sb.append("]"));
    }

    public static void main(String[] args) {
        ArrayCircularQueue q = new ArrayCircularQueue(4);
        q.enqueue(1); q.enqueue(2); q.enqueue(3); q.enqueue(4);
        q.display();                      // [1, 2, 3, 4]
        System.out.println(q.isFull());   // true
        System.out.println(q.dequeue());  // 1
        System.out.println(q.dequeue());  // 2
        q.enqueue(5); q.enqueue(6);
        q.display();                      // [3, 4, 5, 6]
    }
}
