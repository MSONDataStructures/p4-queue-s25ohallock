package structures;

public class LinkedQueue<T> implements QueueInterface<T> {
    private QNode<T> first;
    private QNode<T> last;
    private int count;

    public LinkedQueue() {
        first = null;
        last = null;
        count = 0;
    }

    @Override
    public QueueInterface<T> enqueue(T elem) {
        if (elem == null) {
            throw new NullPointerException("Cannot enqueue null element");
        }

        QNode<T> newNode = new QNode<>(elem);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }

        count++;
        return this;
    }


    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue from an empty queue");
        }

        T element = first.getElement();

        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.getNext();
            first.setPrevious(null);
        }

        count--;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot peek from an empty queue");
        }
        return first.getElement();
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        QNode<T> current = first;

        while (current != null) {
            result.append(current.getElement());
            if (current.getNext() != null) {
                result.append(", ");
            }
            current = current.getNext();
        }

        return result.append("]").toString();
    }
}
