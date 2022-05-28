package A2;
//@name Vicki Young
//@date/version 2022.03.31
//CS245 Assignment02: Roll Bounce

/**
 * List interface. To be implemented by ArrayList and LinkedList
 * add(T item) function = adds item to end of list, returns true if successful
 * add(int position, T item) = adds item at given position
 * get(int position) returns the item at the given position
 * remove(T item) removes the first instance of the given item in list, returns true if successful, false if no such item exists
 */
public interface List<T> {
    boolean add(T item) throws NullPointerException, IllegalArgumentException;
    void add(int position, T item) throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException;
    T get(int position) throws IndexOutOfBoundsException;
    boolean remove(T item) throws NullPointerException;
}
