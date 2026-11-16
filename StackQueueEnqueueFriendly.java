import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class StackQueueEnqueueFriendly {
    private final Deque<Integer> in = new ArrayDeque<>();
    private final Deque<Integer> out = new ArrayDeque<>();

    public void enqueue(int x) {
        in.push(x);
    }

    public int dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        while (!in.isEmpty()) out.push(in.pop());
        int front = out.pop();
        while (!out.isEmpty()) in.push(out.pop());
        return front;
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        while (!in.isEmpty()) out.push(in.pop());
        int front = out.peek();
        while (!out.isEmpty()) in.push(out.pop());
        return front;
    }

    public boolean isEmpty() { return in.isEmpty(); }
    public int size() { return in.size(); }

    public static void main(String[] args) {
        StackQueueEnqueueFriendly q = new StackQueueEnqueueFriendly();
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
