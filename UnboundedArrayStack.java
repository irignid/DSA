import java.util.NoSuchElementException;

public class UnboundedArrayStack {
    private static final int MIN_CAPACITY = 4;
    private int[] data = new int[MIN_CAPACITY];
    private int size = 0;

    public void push(int x) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[size++] = x;
    }

    public int pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        int top = data[--size];
        if (data.length > MIN_CAPACITY && size <= data.length / 4) {
            resize(Math.max(MIN_CAPACITY, data.length / 2));
        }
        return top;
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return data[size - 1];
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
    public int capacity() { return data.length; }

    private void resize(int newCapacity) {
        int[] newData = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        System.out.println("  [resize -> capacity " + newCapacity + "]");
    }

    public static void main(String[] args) {
        UnboundedArrayStack s = new UnboundedArrayStack();
        for (int i = 1; i <= 20; i++) s.push(i);   // grows 4 -> 8 -> 16 -> 32
        System.out.println("size=" + s.size() + " capacity=" + s.capacity());
        while (s.size() > 2) s.pop();              // shrinks back down
        System.out.println("size=" + s.size() + " capacity=" + s.capacity());
    }
}
