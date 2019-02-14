package airbnb;

public class QueueWithFixedArray {
    int arraySize;
    Node head, tail;
    int size;

    class Node {
        Node next;
        int[] array;
        int start, end;

        public Node() {
            array = new int[arraySize];
        }

    }

    public QueueWithFixedArray(int arraySize) {
        this.arraySize = arraySize;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.size =0;

    }

    public void offer(int v) {
        if (tail.end == arraySize) {
            tail.next = new Node();
            tail = tail.next;
        }

        tail.array[tail.end] = v;
        tail.end ++;
        this.size++;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public Integer poll()  {
        if (this.size == 0) {
            return null;
        }
        Node node = head.next;
        int v = node.array[node.start];
        node.start ++;
        if (node.start == this.arraySize) {
            if (node.next != null) {
                head.next = node.next;
            } else {
                node.start = 0;
                node.end = 0;
            }
        }
        this.size --;
        return v;
    }

    public static void main(String[] args) {
        QueueWithFixedArray queue = new QueueWithFixedArray(5);
        for(int i=0; i<30;i++) {
            queue.offer(i);
        }

        while(!queue.isEmpty()) {
            System.out.print(queue.poll() + ", ");
        }
        System.out.println();
        for(int i=0; i<8;i++) {
            queue.offer(i);
        }

        while(!queue.isEmpty()) {
            System.out.print(queue.poll() + ", ");
        }
        System.out.println();
        for(int i=0; i<8; i++) {
            System.out.print(queue.poll() + ", ");
        }

        System.out.println();
    }
}
