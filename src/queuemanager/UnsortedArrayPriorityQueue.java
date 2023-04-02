/*
 * @author Ross Hendrick Beattie
 */
package queuemanager;
import java.lang.*;
import java.util.Arrays;
/**
 *
 * 
 * 
 * * @param <T> The type of things being stored.
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> 
{
    
    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;
    
    /**
     * The size of the storage array.
     */
    private final int capacity;
    
    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;
    
    /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    public UnsortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1; 
    }

   

    @Override
    public T head() throws QueueUnderflowException 
    {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            /*Scan for highest priority item*/
            int priorityIndex = 0;
            for (int i = 0; i < tailIndex; i++)
            {
                if (((PriorityItem<T>) storage[i]).getPriority() > ((PriorityItem<T>) storage[priorityIndex]).getPriority())
                {
                    priorityIndex = i;
                }
            }
            return ((PriorityItem<T>) storage[priorityIndex]).getItem();
        }
    }

     @Override
    public void add(T item, int priority) throws QueueOverflowException 
    {
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) 
        {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } 
        else 
        {
            storage[tailIndex] = new PriorityItem<>(item, priority);
        }
            
    }
    
    @Override
    public void remove() throws QueueUnderflowException 
    {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            int removalIndex = 0;
            for (int i = 0; i < tailIndex; i++)
            {
                if ((((PriorityItem<T>)storage[i]).getPriority()) > ((PriorityItem<T>)storage[removalIndex]).getPriority())
                {
                    removalIndex = i;
                }
            }
            
            Object[] newArray = new Object[capacity];
            
            int newArrayIndex = 0;
            for (int i=0; i<capacity; i++)
            {
                if (i != removalIndex)
                {
                    newArray[newArrayIndex] = storage[i];
                    newArrayIndex++;
                    System.out.println(newArrayIndex);
                }
            }
            System.arraycopy(newArray, 0, storage, 0, capacity);
            tailIndex = tailIndex-1;
        }
    }


    @Override
    public boolean isEmpty() 
    {
        return tailIndex < 0;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }
    
}

