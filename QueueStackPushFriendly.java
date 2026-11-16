import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueStackPushFriendly {
    private Queue<Integer> main = new LinkedList<>();
    private Queue<Integer> temp = new LinkedList<>();

    public void push(int x) {
        main.offer(x);
    }

    public int pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        while (main.size() > 1) {
            temp.offer(main.poll());
        }
        int top = main.poll();
        swap();
        return top;
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        while (main.size() > 1) {
            temp.offer(main.poll());
        }
        int top = main.poll();
        temp.offer(top); // keep it in the stack
        swap();
        return top;
    }

    public boolean isEmpty() { return main.isEmpty(); }
    public int size() { return main.size(); }

    private void swap() {
        Queue<Integer> t = main;
        main = temp;
        temp = t;
    }

    public static void main(String[] args) {
        QueueStackPushFriendly s = new QueueStackPushFriendly();
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
