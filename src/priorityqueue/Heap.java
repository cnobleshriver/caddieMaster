package priorityqueue;

import java.lang.reflect.Array;
import java.util.Comparator;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;

  /**
   * Constructor for the heap.
   * 
   * @param comparator comparator object to define a sorting order for the heap
   *                   elements.
   * @param isMaxHeap  Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
    heap = (T[]) new Object[INIT_SIZE];
    this.isMaxHeap = isMaxHeap;
    this.comparator = comparator;
    this.numElements = 0;
  }

  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained.
   *
   * @param index the index to bubble up
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleUp(int index) {
    if (index > getSize() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    int parentIndex;
    while (index > 0) {
      parentIndex = getParentOf(index);
      if (compareElements(heap[index], heap[parentIndex]) <= 0) {
        return;
      } else {
        swapIndices(heap, index, parentIndex);
        index = parentIndex;
      }
    }
  }

  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained.
   * 
   * @param index
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleDown(int index) {
    if (index >= numElements || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    int childIndex = getLeftChildOf(index);
    T value = heap[index];
    T maxValue;
    int maxIndex;
    while (childIndex < numElements) {
      maxValue = value;
      maxIndex = -1;
      for (int i = 0; (i < 2) && (i + childIndex < numElements); i++) {
        if (compareElements(heap[i + childIndex], (maxValue)) > 0) {
          maxValue = heap[i + childIndex];
          maxIndex = i + childIndex;
        }
      }
      if (compareElements(maxValue, value) == 0) {
        return;
      } else {
        swapIndices(heap, index, maxIndex);
        index = maxIndex;
        childIndex = getLeftChildOf(index);
      }
    }
  }

  private int getLeftChildOf(int parentIndex) {
    return parentIndex * 2 + 1;
  }

  private int getParentOf(int childIndex) {
    return (childIndex - 1) / 2;
  }

  private void swapIndices(T[] inputArray, int index1, int index2) {
    T temp = inputArray[index1];
    inputArray[index1] = inputArray[index2];
    inputArray[index2] = temp;
  }

  private void expandCapacity() {
    T[] newHeap = (T[]) new Object[heap.length * 2];
    for (int i = 0; i < heap.length; i++) {
      newHeap[i] = heap[i];
    }
    heap = newHeap;
  }

  private void heapify(int len) {
    for (int i = len / 2 - 1; i >= 0; i--) {
      bubbleDown(i);
    }
  }

  /**
   * Test for if the queue is empty.
   * 
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    boolean isEmpty = false;
    if (numElements == 0) {
      isEmpty = true;
    }
    return isEmpty;
  }

  /**
   * Number of data elements in the queue.
   * 
   * @return the size
   */
  public int getSize() {
    return numElements;
  }

  /**
   * Compare method to implement max/min heap behavior. It changes the value of a
   * variable, compareSign,
   * based on the state of the boolean variable isMaxHeap. It then calls the
   * compare method from the
   * comparator object and multiplies its output by compareSign.
   * 
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if
   *         {@code element1 == element2},
   *         negative int otherwise (if isMaxHeap),
   *         return negative int if {@code element1 > element2}, 0 if
   *         {@code element1 == element2},
   *         positive int otherwise (if ! isMinHeap).
   */
  public int compareElements(T element1, T element2) {
    int result = 0;
    int compareSign = -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap
   * without removing the element.
   * 
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek() throws QueueUnderflowException {
    if (isEmpty()) {
      throw new QueueUnderflowException();
    }
    T data = heap[0];
    return data;
  }

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority
   * in the heap.
   * 
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeueElement() throws QueueUnderflowException {
    if (isEmpty()) {
      throw new QueueUnderflowException();
    }
    T data = heap[0];
    heap[0] = heap[getSize() - 1];
    numElements--;
    heapify(getSize());
    return data;
  }

  /**
   * Enqueue the element.
   * 
   * @param the new element
   */
  public void enqueueElement(T newElement) {
    int size = getSize();
    if (size >= heap.length) {
      expandCapacity();
    }
    heap[size] = newElement;
    numElements++;
    bubbleUp(size);
  }
}