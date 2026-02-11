/**
 * Creates a List abstract data type class
 * @author Abigail Lei
 */

/**
 * Constructor for the List ADT, takes in 
 * Only takes one type of element / object, but could be anything such as integers, booleans, etc.
 * Can begin as an empty list, and then elements are added
 * Can begin as a pre-populated list, and then elements are added.
 * Elements can be added to and removed from the list with no problem.
 * @throws TypeError if list is created with multiple different data types inside of it
 */


public interface ListADT<T>{

    /**
     * An accessor that returns the number of elements in the list
     * @return integer of how many elements are in the list
     * @throws NullPointerException if called on a list that has not been created yet.
     */
    public int size();

    /**
     * Checks the list and returns whether it is empty or not
     * True means the list empty and has size of 0
     * False means having at least one element
     * @return a boolean of if the list is empty or not
     * @throws NullPointerException if called on a list that has not been created yet.
     */
    public boolean isEmpty();

    /**
     * Accesses an element at a specific index, then returns it
     * @param index of element to access
     * @return T - the element at the index
     * @throws IndexOutOfBoundsError if index is invalid as in less than 0 (negative) or is greater than / equal to the size
     * @throws IndexOutOfBoundsError if list is empty
     * @throws NullPointerException if element at the index is null
     */
    public T get(int index);

    /**
     * Replaces the element of a specific index with a new element, T item
     * @param index an integer for the index of the position to change
     * @param item the new element of T type to replace the previous element with.
     * @throws IndexOutOfBoundsError if index is invalid, less than 0 (negative) or is greater than / equal to size
     * @throws IndexOutOfBoundsError if list is empty
     * @throws TypeError if the index is given as a non-integer
     * @throws NullPointerException if element T to replace previous element is null or doesn't exist
     */
    public T set(int index, T value);

    /**
     * Adds a new element at a specific index
     * pushes every element after it back an index
     * @param index an integer which is the index of where the new element is added
     * @param item is the element being added, can be many different types
     * @throws IndexOutOfBoundsError if index is invalid as in less than 0 (negative) or is greater than / equal to size
     * @throws TypeError if the index is given as a non-integer
     * @throws NullPointerException if element T to replace previous element is null 
    */
    public void add(int index, T value);

    /**
     * Appends the new element to the end of the list since there is no index
     * @param item: the item being added to the list, can be any type
     * @throws NullPointerException if element T to replace previous element is null
     */
    public void add(T value);

    /**
     * Removes the item at the specific index
     * Pushes forward each element behind the element that was removed
     * @param index the index of the item to be removed
     * @return the item that was removed from the list
     * @throws IndexOutOfBoundsError if index is invalid, less than 0 (negative) or is greater than / equal to size
     * @throws IndexOutOfBoundsError if list is empty
     * @throws TypeError if the index is given as a non-integer
     * @throws NullPointerException if element T to replace previous element is null
     */
    public T remove(int index);

}

public static void main(String args[]){
    
}
