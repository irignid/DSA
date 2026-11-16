import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueStackPopFriendly {
    private Queue<Integer> main = new LinkedList<>();
    private Queue<Integer> temp = new LinkedList<>();

    public void push(int x) {
        temp.offer(x);
        while (!main.isEmpty()) {
            temp.offer(main.poll());
        }
        Queue<Integer> t = main;
        main = temp;
        temp = t;
    }

    public int pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return main.poll();
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return main.peek();
    }

    public boolean isEmpty() { return main.isEmpty(); }
    public int size() { return main.size(); }

    public static void main(String[] args) {
        QueueStackPopFriendly s = new QueueStackPopFriendly();
        s.push(1); s.push(2); s.push(3);
        System.out.println(s.peek()); // 3
        System.out.println(s.pop());  // 3
        System.out.println(s.pop());  // 2
        s.push(4);
        System.out.println(s.pop());  // 4
        System.out.println(s.pop());  // 1
        System.out.println(s.isEmpty()); // true
    }
}
