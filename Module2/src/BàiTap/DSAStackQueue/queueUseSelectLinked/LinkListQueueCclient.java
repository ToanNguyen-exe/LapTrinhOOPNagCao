package DSAStackQueue.queueUseSelectLinked;

public class LinkListQueueCclient {
    public static void main(String[] args) {
        MylinkedListQueue queue= new MylinkedListQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(90);
        queue.enqueue(40);
        queue.enqueue(50);
        System.out.println("dequeued item is "+ queue.dequeue().key);
    }
}
