/**
 * Creates a List abstract data type class
 * @author Abigail Lei
 */

/**
 * Constructor for the List ADT, takes in 
 * Only takes one type of element / object, but could be anything such as integers, booleans, etc.
 * Can begin as an empty list, and then elements are added
 * Can begin as a pre-populated list, and then elements are added.
 * Elements can be added to and removed from the list with no problem
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
     * @throws IndexOutOfBoundsException if index is invalid, less than 0 or is greater than the size
     */
    public T get(int index);

    /**
     * Replaces the element of a specific index with a new element, T item
     * @param index an integer for the index of the position to change
     * @param value the new element of T type to replace the previous element with.
     * @throws IndexOutOfBoundsException if index is invalid, less than 0 or is greater than size
     * @throws IndexOutOfBoundsException if list is empty
     * @return the previous element that got replaced
     */
    public T set(int index, T value);

    /**
     * Adds a new element at a specific index
     * pushes every element after it back an index
     * @param index an integer which is the index of where the new element is added
     * @param value is the element being added, can be many different types
     * @throws IndexOutOfBoundsException if index is invalid as in less than 0 or is greater than size
    */
    public void add(int index, T value);

    /**
     * Appends the new element to the end of the list since there is no index
     * @param value the item being added to the list, can be any type
     */
    public void add(T value);

    /**
     * Removes the item at the specific index
     * Pushes forward each element behind the element that was removed
     * @param index the index of the item to be removed
     * @return the item that was removed from the list
     * @throws IndexOutOfBoundsException if index is invalid: less than 0 or is greater than size
     * @throws IndexOutOfBoundsException if list is empty
     */
    public T remove(int index);
    

    /**
     * toString printing method that formats the dynamic array nicely
     * @return String of dyanmic array in format with brackets and commas
     */
    public String toString();

    /**
     * Method that adds all elements of passed DynamicArray 
     * to end of preexisiting DynamicArray
     * @param newArray to be appended to the end of existing DynamicArray
     * @return new DynamicArray that is the new array concatenated to old array
     */
    public DynamicArray<T> append(DynamicArray<T> newArray);

     /**
     * Inserts the elements of a DynamicArray at the index
     * @param newArray DynamicArray with elements to be inserted to the current DynamicArray
     * @param index to insert the elements at
     * @return new DynamicArray of the old DynamicArray with the new elements inserted
     * @throws IndexOutOfBoundsException if index is negative or greater than the size of the DynamicArray.
     *      
     */
    public DynamicArray<T> addAll(DynamicArray<T> newArray, int index);

     /**
     * Splits a DynamicArray from a specified index until the end of the DynamicArray
     * @param index to start at to get elements from it until after
     * @return new DynamicArray with all elements from specified index and after
     * @throws IndexOutOfBoundsException if index is negative or greater than the size of the DynamicArray.
     */
    public DynamicArray<T> splitSuffix(int index);

     /**
     * Splits a DynamicArray from the beginning until the specified index.
     * @param index that indicates where the splitting should end
     * @return new DynamicArray of just the elements split from old DynamicArray
     * @throws IndexOutOfBoundsException if index passed is negative or greater than size of the DynamicArray.
     */
    public DynamicArray<T> splitPrefix(int index);

    /**
     * Removes elements between certain indicies
     * @param fromIndex starting index to delete (inclusive)
     * @param toIndex ending index to delete (exclusive)
     * @return new DynamicArray without the deleted data
     * @throws IndexOutOfBoundsException if index passed is negative or greater than size of the DynamicArray.
     * @throws IndexOutOfBoundsException if starting index (fromIndex) is after the ending index.
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex);

    /**
     * Takes elements from a specified starting index to ending index and puts in new DynamicArray
     * @param fromIndex starting index to extract from, inclusive
     * @param toIndex ending index to extract from, exclusive
     * @return new DynamicArray with just the elements between fromIndex and toIndex
     * @throws IndexOutOfBoundsException if index passed is negative or greater than size of the DynamicArray.
     * @throws IndexOutOfBoundsException if starting index is greater than the ending index.
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex);

}


