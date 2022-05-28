package A2;
//@name Vicki Young
//@date/version 2022.03.31
//CS245 Assignment02: Roll Bounce

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private int size;
    private T[] list;

    //constructor
    public ArrayList() {
        size = 0;
        list = (T[]) new Object[10];
    }

    /**
     * Grows the size of the given array by creating a copy of the origin array and
     * doubling its size. Copies all items over.
     **/
    private void growArray() {
        list = Arrays.copyOf(list, list.length * 2);
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param item element to be appended to this list
     * @return {@code true}
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    @Override
    public boolean add(T item) throws NullPointerException, IllegalArgumentException {
        //check if given item is null
        if (item == null) {
            throw new NullPointerException("Item is null.");
        }
        //check for illegal argument, specifically if given item is an empty string
        if (item.getClass().equals(String.class) && item.equals("")) {
            throw new IllegalArgumentException("Illegal argument of empty string.");
        }

        if (size == list.length) {
            growArray();
        }
        list[size++] = item;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param position   index at which the specified element is to be inserted
     * @param item element to be inserted
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
    @Override
    public void add(int position, T item) throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
        //check if given item is null
        if (item == null) {
            throw new NullPointerException("Item is null.");
        }
        //check for illegal argument, specifically if given item is an empty string
        if (item.getClass().equals(String.class)  && item.equals("")) {
            throw new IllegalArgumentException("Illegal argument of empty string.");
        }
        //check if given position is out of bounds
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("List index out of bounds.");
        }

        for (int i = size; i > position; i--) {
            list[i] = list[i-1];
        }
        list[position] = item;
        ++size;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param position index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public T get(int position) throws IndexOutOfBoundsException {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return list[position];
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param item element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    @Override
    public boolean remove(T item) throws NullPointerException {
        //check if given item is null
        if (item == null) {
            throw new NullPointerException("Item is null.");
        }
        //traverse list until position of first element matching item is found
        for (int i = 0; i < size; i++) {
            //if a matching element is found:
            if (list[i].equals(item)) {
                for (int j = i; j < size - 1; j++) {
                    list[j] = list[j+1];
                }
                //decrease size, return true
                --size;
                return true;
            }
        }
        //no matching elements found in the list; return false
        return false;
    }
}
