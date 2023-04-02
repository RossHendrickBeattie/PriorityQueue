package queuemanager;

import java.util.LinkedList;

/**
 *
 * 
 */
public class SortedLinkedListPriorityQueue<T> implements PriorityQueue<T> {
    
    /**
     * Where the data is actually stored.
     */
    
    
    private final LinkedList storage;
    
    public SortedLinkedListPriorityQueue() {
        storage = new LinkedList();
    }
    
    /**
     * Add the given item to the queue with the given priority. Throw an
     * exception if it's already full to capacity.
     *
     * @param item
     * @param priority
     * @throws QueueOverflowException
     */
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException
    {
        int priorityIndex = 0;
            for (int i = 0; i < storage.size(); i++)
            {
                if (priority > ( (PriorityItem<T>) storage.get(i) ).getPriority() )
                {
                    priorityIndex = i;
                }
            }
        
        storage.add(priorityIndex, new PriorityItem<T>(item, priority));
    }

    @Override
    public T head() throws QueueUnderflowException {
        T result = ((PriorityItem<T>) storage.getFirst()).getItem();
        return result;
    }

    @Override
    public void remove() throws QueueUnderflowException {
        storage.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return (storage.size() < 0);
    }
    
    @Override
    public String toString() {
        
        String result = "[";
        for (int i = 0; i < storage.size(); i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + ((PriorityItem<T>) storage.get(i));
        }
        result = result + "]";
        return result;
    }
}
