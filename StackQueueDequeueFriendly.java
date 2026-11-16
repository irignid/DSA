import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class StackQueueDequeueFriendly {
    private final Deque<Integer> main = new ArrayDeque<>();
    private final Deque<Integer> temp = new ArrayDeque<>();

    public void enqueue(int x) {
        while (!main.isEmpty()) temp.push(main.pop());
        main.push(x);
        while (!temp.isEmpty()) main.push(temp.pop());
    }

    public int dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return main.pop();
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return main.peek();
    }

    public boolean isEmpty() { return main.isEmpty(); }
    public int size() { return main.size(); }

    public static void main(String[] args) {
        StackQueueDequeueFriendly q = new StackQueueDequeueFriendly();
        q.enqueue(1); q.enqueue(2); q.enqueue(3);
        System.out.println(q.peek());    // 1
        System.out.println(q.dequeue()); // 1
        q.enqueue(4);
        System.out.println(q.dequeue()); // 2
        System.out.println(q.dequeue()); // 3
        System.out.println(q.dequeue()); // 4
        System.out.println(q.isEmpty()); // true
    }
}
