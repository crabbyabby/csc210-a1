//import javax.management.RuntimethrowException;

/**
 * DynamicArray is a list-like data structure that stores elements in a backing array.
 * The DynamicArray holds a reference to the backing array.
 * It keeps track of the number of elements currently stored.
 * Elements can be accessed, added, removed, or modified by index.
 *
 * @param <T> The type of elements stored in the array. Can be any primitive or object.
 * Each DynamicArray's elements must all be the same type.
 * 
 * @author Abigail Lei
 */
public class DynamicArray<T> implements ListADT<T>{

    // Attributes
    private int capacity;
    private int size;
    private T[] data;

    /**
     * Constructs a new DynamicArray with a specific capacity.
     * Sets the size to 0 as it always initiates empty, creates backing array
     * @param capacity integer that specifies how many elements the array can hold.
     */
    public DynamicArray(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.data = makeArray(this.capacity);
    }

    /**
     * Copy constructor that creates a deep copy of the passed in DynamicArray
     * Copies size and capacity from the oldArray but creates a new backing array
     * Copies data over from oldArray
     * @param oldArray the DynamicArray to be copied
     */
    public DynamicArray(DynamicArray<T> oldArray){
        this.capacity = oldArray.capacity;
        this.data = makeArray(this.capacity);
        this.size = oldArray.size();

        if (this.size > 0){
            for (int i = 0; i < this.size; i++){
                this.data[i] = oldArray.get(i);
            }
        }
    }

    /**
     * Creates a new generic array of the given capacity.
     * <p>
     * Java does not allow direct creation of generic arrays. This helper method
     * safely encapsulates the required cast and suppresses the expected unchecked
     * cast warning.
     *
     * @param capacity the desired length of the array
     * @return a new array of type T[] with the given capacity
     */
    @SuppressWarnings("unchecked")
    private T[] makeArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    /**
     * Accessor for the size of the array.
     * @return int of the arrays size
     */
    public int size(){
        return this.size;
    }

    /**
     * Method that tells us if the array is empty or not
     * @return true if the array's size is 0, false if it is greater than 0
     */
    public boolean isEmpty(){
        if (size() == 0){
            return true;
        }
        else{
            return false;
        }      
    }

    /**
     * Getter a specific element in the Dynamic Array based on index
     * @param index int of the element to get
     * @return element of type T
     * @throws IndexOutOfBoundsException if the index is invalid (negative or greater than the array's size)
     */
    public T get(int index){
            if (index >= 0 && index < size()){
                return this.data[index]; 
            } else {
                throw new IndexOutOfBoundsException("Invalid index.");
            }
    }

    /**
     * Setter that changes a specific element of the Dynamic Array in place with inputted information
     * @param index of the element to change
     * @param value of same type of the Dynamic Array to replace the current elemt
     * @return the previous element  
     * @throws IndexOutOfBoundsException if the index is negative or greater than size + 1 of the DynamicArray
     */
    public T set(int index, T value){
        T returned;
            if (index >= 0 && index < size()){
                returned = this.data[index];
                this.data[index] = value;
                return returned;
            } else if (index == size() + 1) {
                this.data[index] = value;
                size++;
                return null;
            } else {
                throw new IndexOutOfBoundsException("Invalid index.");
            }
    }

   /**
    * Method that adds an element at a specific index
    * Pushes other elements one index up
    * @param index to add the element in
    * @param value to add into the Dynamic Array
    * @throws IndexOutOfBoundsException if the index is negative or greater than the size
    */
    public void add(int index, T value){
        if (index >= 0 && index <= size()){
            if (size() < this.capacity){
                for (int i = this.size - 1; i >= index; i--){
                    this.data[i+1] = this.data[i];
                }
                this.data[index] = value;
                this.size++;

            } else {
                this.capacity++;
                T[] newArray = makeArray(this.capacity);
                System.arraycopy(this.data, 0, newArray, 0, size);
                this.data = newArray;
                for (int i = this.size - 1; i >= index; i--){
                    this.data[i+1] = this.data[i];
                }

                this.data[index] = value;
                this.size++;
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

    /**
     * Method that adds element to the end of the Dynamic Array
     * @param value of same type to add in to the dynamic array
     */
    public void add(T value){
        if (size() < this.capacity){
            this.data[this.size] = value;
            size += 1;
        } else if (size() == this.capacity){
            this.capacity++;
            T[] newArray = makeArray(this.capacity);
            System.arraycopy(this.data, 0, newArray, 0, size);
            this.data = newArray;

            this.data[this.size] = value;
            this.size++;
        }
    }

    /**
     * Method that removes an element from a DynamicArray
     * Moves down elements in indexes above
     * @param index of element to be removed
     * @return element that is removed
     * @throws RuntimeException if the DynamicArray is empty
     * @throws IndexOutOfBoundsException if the index is negative or greater than the DynamicArray's size
     */
    public T remove(int index){
        T returned;
            if (index >= 0 && index < size()){
                if (size() > 0){
                    returned = this.data[index];
                    for (int i = index; i < this.size-1; i++){  
                        this.data[i] = this.data[i+1];
                    }
                    this.data[size-1] = null;
                    this.size--;
                    return returned;
                    
                } else {
                    throw new RuntimeException("Cannot remove from an empty array.");
                }
            } else {
                throw new IndexOutOfBoundsException("Invalid index.");
            }
    }

    /**
     * toString printing method that formats the dynamic array nicely
     * @return String of dyanmic array in format with brackets and commas
     */
    public String toString(){
        String printed = "[";
        String returned = "";
        if (this.size == 0){
            return "[]";
        } else {
            for (int i = 0; i < this.size; i++){
                printed += this.data[i].toString() + ", ";
            }
            returned = printed.substring(0, (printed.length()-2)) + "]";
            return returned;
        }
    }

    /**
     * Method that adds all elements of passed DynamicArray to end of preexisiting DynamicArray
     * @param newArray to be appended to the end of existing DynamicArray
     * @return new DynamicArray that is the new array concatenated to old array
     */
    public DynamicArray<T> append(DynamicArray<T> newArray){
        int newSize = this.size + newArray.size();
        DynamicArray<T> returned = new DynamicArray<T>(newSize);

        for (int i = 0; i < this.size; i++){
            returned.add(this.data[i]);
        }
        int j = 0;
        for (int i = this.size; i < newSize; i++){
            returned.add(newArray.get(j));
            j++;
        }
        return returned;
    } 


    /**
     * Inserts the elements of a DynamicArray at the index
     * @param newArray DynamicArray with elements to be inserted to the current DynamicArray
     * @param index to insert the elements at
     * @return new DynamicArray of the old DynamicArray with the new elements inserted
     * @throws IndexOutOfBoundsException if index passed is negative or greater than the size of the DynamicArray.
     *      
     */
    public DynamicArray<T> addAll(DynamicArray<T> newArray, int index){
        if (index > this.size || index < 0){
            throw new IndexOutOfBoundsException("Index must be between 0 and the size of the array.");
        } else {
            DynamicArray<T> returned = new DynamicArray<T>(this);
            for (int i = newArray.size() - 1; i >= 0; i--){
                returned.add(index, newArray.get(i));
            }
            return returned;
        }
    }

    /**
     * Splits a DynamicArray from a specified index until the end of the DynamicArray
     * @param index to start at to get elements from it until after
     * @return new DynamicArray with all elements from specified index and after
     * @throws IndexOutOfBoundsException if index passed is negative or greater than the size of the DynamicArray.
     */
    public DynamicArray<T> splitSuffix(int index){
        if (index >= this.size || index < 0){
            throw new IndexOutOfBoundsException("Invalid index. Index must be between 0 and the size of the array.");
        } else {
            DynamicArray<T> returned = new DynamicArray<>(this.size - index);
            for (int i = index; i < this.size; i++){
                returned.add(this.get(i));
            }

            return returned;

        }
    }

    /**
     * Splits a DynamicArray from the beginning until the specified index.
     * @param index that indicates where the splitting should end
     * @return new DynamicArray of just the elements split from old DynamicArray
     * @throws IndexOutOfBoundsException if index passed is negative or greater than the size of the DynamicArray.
     */
    public DynamicArray<T> splitPrefix(int index){
        if (index >= this.size || index < 0){
            throw new IndexOutOfBoundsException("Invalid index. Must be between 0 and the size of the array.");
        } else {
            DynamicArray<T> returned = new DynamicArray<>(index);
            for (int i = 0; i < index; i++){
                returned.add(this.get(i));
            }

            return returned;
        }
    }

    /**
     * Removes elements between certain indicies
     * @param fromIndex starting index to delete (inclusive)
     * @param toIndex ending index to delete (exclusive)
     * @return new DynamicArray without the deleted data
     * @throws IndexOutOfBoundsException if index passed is negative or greater than the size of the DynamicArray.
     * @throws IndexOutOfBoundsException if starting index (fromIndex) is after the ending index.
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex){
        if (fromIndex >= this.size || fromIndex < 0 || toIndex > this.size || toIndex < 0){
            throw new IndexOutOfBoundsException("Invalid index. Must be between 0 and the size of the array.");
        } else if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex must be less than toIndex");
        } else {
            DynamicArray<T> returned = new DynamicArray<>(size - (toIndex - fromIndex));
            for (int i = 0; i < fromIndex; i++){
                returned.add(this.get(i));
            }

            for (int i = toIndex; i < size(); i++){
                returned.add(this.get(i));
            }

            return returned;
        }
    }

    /**
     * Takes elements from a specified starting index to ending index and puts in new DynamicArray
     * @param fromIndex starting index to extract from, inclusive
     * @param toIndex ending index to extract from, exclusive
     * @return new DynamicArray with just the elements between fromIndex and toIndex
     * @throws IndexOutOfBoundsException if index passed is negative or greater than the size of the DynamicArray.
     * @throws IndexOutOfBoundsException if starting index (fromIndex) is greater than / after the ending index.
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex){
        if (fromIndex >= this.size || fromIndex < 0 || toIndex > this.size || toIndex < 0){
            throw new IndexOutOfBoundsException("Invalid index. Must be between 0 and the size of the array.");
        } else if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex must be less than toIndex");
        } else {
            DynamicArray<T> returned = new DynamicArray<>((toIndex - fromIndex));
        
            for (int i = fromIndex; i < toIndex; i++){
                returned.add(get(i));
            }

            return returned;
        }
    }

    public static void main(String args[]){
    DynamicArray<Integer> nums = new DynamicArray<Integer>(1);
    DynamicArray<Integer> nums2 = new DynamicArray<Integer>(8); 
    DynamicArray<Integer> nums3;
    DynamicArray<Integer> nums4;
    DynamicArray<String> strings = new DynamicArray<String>(5);
    strings.add("hi");
    strings.add("bye");
    nums.add(1);
    nums.add(2);
    nums.add(3);
    nums.add(4);
    System.out.println("------------------");
    nums.add(1, 555); 
    System.out.println(nums);
    System.out.println("------------------");

    nums.remove(0);

    nums2.add(-1);
    nums.remove(2);
    System.out.println(nums);
    System.out.println(nums2);
    //nums2.add(-50);

    nums3 = nums.append(nums2);
    //System.out.println(nums3);

    nums4 = nums.splitPrefix(1);
    //System.out.println(nums4);

}
}





