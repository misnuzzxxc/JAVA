package Linked;

import java.util.Iterator;
import java.util.NoSuchElementException;

// 노드 클래스 (내부 클래스로 선언)
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// 연결 리스트 클래스
class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}

// 큐 클래스
class Queue<T> extends LinkedList<T> {
    public void enqueue(T data) {
        add(data);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T data = get(0);
        delete(0);
        return data;
    }

    public boolean isEmpty() {
        return getSize() == 0; // size 대신 getSize() 메서드 사용
    }
}

// 스택 클래스
class Stack<T> extends LinkedList<T> {
    public void push(T data) {
        add(data);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T data = get(getSize() - 1); // size 대신 getSize() 메서드 사용
        delete(getSize() - 1);
        return data;
    }

    public boolean isEmpty() {
        return getSize() == 0; // size 대신 getSize() 메서드 사용
    }
}

// 메인 클래스 (예제 사용)
public class Ex01 {
    public static void main(String[] args) {
        // 사용 예제
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();

        Queue<String> queue = new Queue<>();
        queue.enqueue("첫 번째");
        queue.enqueue("두 번째");
        queue.enqueue("세 번째");

        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }

        Stack<Character> stack = new Stack<>();
        stack.push('가');
        stack.push('나');
        stack.push('다');

        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }
}
