package A2;
//@name Vicki Young
//@date/version 2022.03.31
//CS245 Assignment02: Roll Bounce

public class LinkedList<T> implements List<T> {

    private int size;
    private Node<T> head;

    //private class Node for LinkedList
    private class Node<T> {
        private T data;
        private Node<T> next;

        //constructor
        public Node(T value) {
            data = value;
            next = null;
        }
    }

    //constructor
    public LinkedList() {
        size = 0;
        head = null;
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
    public boolean add(T item) throws NullPointerException, IllegalArgumentException {
        //check if given item is null
        if (item == null) {
            throw new NullPointerException("Item is null.");
        }
        //check for illegal argument, specifically if given item is an empty string
        if (item.getClass().equals(String.class) && item.equals("")) {
            throw new IllegalArgumentException("Illegal argument of empty string.");
        }

        //if list is empty, item becomes the head of the list
        if (head == null) {
            head = new Node(item);
            //list is not empty, add item to end of list
        } else {
            //traverse list to find the last node
            Node<T> previous = head;
            while (previous.next != null) {
                previous = previous.next;
            }
            //add item to end of list
            Node<T> node = new Node(item);
            previous.next = node;
        }
        //increment size, return true
        ++size;
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

        //if the item is being inserted at the head of the list
        if (position == 0) {
            //element becomes the new head node
            Node<T> newHead = new Node(item);
            newHead.next = head;
            head = newHead;
            //else inserting an item anywhere else in the list
        } else {
            //traverse list to find previous node before given position
            Node<T> previous = head;
            for (int i = 0; i < position - 1; i++) {
                previous = previous.next;
            }
            //insert given item by connecting newItem.next to previous.next, and previous.next to newItem
            Node<T> newItem = new Node(item);
            newItem.next = previous.next;
            previous.next = newItem;
        }
        //increment size
        ++size;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param item element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    public boolean contains(Object item) throws NullPointerException {
        //checks if item is null
        if (item == null) {
            throw new NullPointerException("Null values not accepted.");
        }

        //traverse list comparing data to given item
        for (int i = 0; i < size; i++) {
            //returns true if matching data is found
            if (item.equals(get(i))) {
                return true;
            }
        }
        //item is not found in list; return false
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param position index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    public T get(int position) throws IndexOutOfBoundsException {
        //check if given position is out of bounds
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        //traverse list to find node of the given position
        Node<T> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        //return position node data
        return current.data;
    }

    /**
     * Removes the last element in this list. Returns the element that was removed from the
     * list.
     *
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    public Object removeLast() throws IndexOutOfBoundsException {
        //check for out of bounds: if the list is empty, nothing to remove; throw exception
        if (head == null) {
            throw new IndexOutOfBoundsException("List is already empty.");
        }
        //else if list has only one item
        else if (size == 1) {
            //removes the head and returns its data
            Object data = head.data;
            head = null;
            --size;
            return data;
        }
        //else, remove normally
        else {
            //traverse list to find the second to last node and last node
            Node<T> secondLast = head;
            for (int i = 0; i < size - 2; i++) {
                secondLast = secondLast.next;
            }
            //remove last node (point secondLast.next to null) and return its data
            Object lastData = (secondLast.next).data;
            secondLast.next = null;
            --size;
            return lastData;
        }
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
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     */
    public boolean remove(Object item) throws NullPointerException {
        //check if given item is null
        if (item == null) {
            throw new NullPointerException("Item is null.");
        }

        //traverse list until position of first element matching item is found
        for (int position = 0; position < size; position++) {
            //if a matching element is found:
            if (item.equals(get(position))) {
                //and if the matching element is the only element in list (and so is also the head)
                if (size == 1) {
                    //remove head
                    head = null;
                    //else if the matching element is the head of the list
                } else if (position == 0) {
                    //shift head to next item in list
                    head = head.next;
                    //else the matching element is any other element in the list
                } else {
                    //traverse list up to position of matching element to find previous element/node
                    Node<T> previous = head;
                    for (int j = 0; j < position - 1; j++) {
                        previous = previous.next;
                    }
                    //remove matching element by connecting previous node to current.next
                    Node<T> current = previous.next;
                    previous.next = current.next;
                }
                //decrease size, return true
                --size;
                return true;
            }
        }
        //no matching elements found in the list; return false
        return false;
    }

    /**
     * Removes the element at the specified position from the end of the list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element previously at the specified position.
     *
     * @param position the index of the item the end of the list to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    public Object removeFromEnd(int position) throws IndexOutOfBoundsException {
        //check if given position is out of bounds
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        //finds index (from head = 0) in the list based on given position
        int index = size - position;

        //if the item to be removed is the end of the list
        if (index == size) {
            //no change, skip size decrement, return null
            return null;
            //else if the item to be removed is the head of the list
        } else if (index == 0) {
            //shift head to next item in list, decrement size, return object data
            Object data = head.data;
            head = head.next;
            --size;
            return data;
            //else item to be removed is anywhere else within list index boundaries
        } else {
            //traverse list to find previous and current
            Node<T> previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }
            //remove current node by connecting previous node to current.next
            Node<T> current = previous.next;
            Object data = current.data;
            previous.next = current.next;
            //decrement size and return object data
            --size;
            return data;
        }
    }

    /**
     * Shows ListNode as a String, with each object in parentheses separated by “arrows” (->).
     * A ListNode of {1, 2, 3} should return the String "(1) -> (2) -> (3)".
     * @return String representation of the ListNode
     */
    @Override
    public String toString() {
        String text = "[";
        Node<T> node = head;
        boolean notFirst = false;
        for (int i = 0; i < size; i++) {
            if (notFirst) {
                text += " -> ";
            }
            text += "(" + node.data + ")";
            node = node.next;
            notFirst = true;
        }
        text += "]";
        return text;
    }
}


