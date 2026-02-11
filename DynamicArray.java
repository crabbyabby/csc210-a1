import javax.management.RuntimeErrorException;

/**
 * TODO: Describe what this class represents and how it works at a high level
 *
 * @param <T> TODO: Describe what type of elements this class can store
 * @author Abigail Lei
 */
public class DynamicArray<T>{

    private int capacity;
    private int size;
    private T[] array;

    public DynamicArray(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.array = makeArray(this.capacity);
    }

    public DynamicArray(DynamicArray<T> oldArray){
        this.capacity = oldArray.capacity;
        this.array = makeArray(this.capacity);
        this.size = oldArray.size();

        if (this.size > 0){
            for (int i = 0; i < this.size; i++){
                this.array[i] = oldArray.get(i);
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
    int size(){
        return this.size;
    }

    /**
     * Method that tells us if the array is empty or not
     * @return true if the array's size is 0, false if it is greater than 0
     */
    boolean isEmpty(){
        if (size() == 0){
            return true;
        }
        else{
            return false;
        }      
    }

    /**
     * 
     * @param index
     * @return element of type T
     * @throws 
     */
    T get(int index){
            if (index >= 0 && index < size()){
                return this.array[index]; 
            } else {
                throw new IndexOutOfBoundsException("Invalid index.");
            }
    }

    T set(int index, T value){
        T returned;
            if (index >= 0 && index < size()){
                returned = this.array[index];
                this.array[index] = value;
                return returned;
            } else {
                throw new IndexOutOfBoundsException("Invalid index.");
            }
    }

   
    void add(int index, T value){
        if (index > 0 && index <= size()){
            if (size() < capacity){
                for (int i = this.size - 1; i >= index; i--){
                    array[i+1] = array[i];
                }
                array[index] = value;
                this.size++;

            } else {
                throw new RuntimeException("Cannot add anymore items, capacity reached.");
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

    void add(T value){
            if (size() < capacity){
                this.array[size] = value;
                size += 1;
            } else {
                throw new RuntimeException("Cannot add anymore items, capacity reached.");
            }
    }

    T remove(int index){
        T returned;
            if (index > 0 && index <= size()){
                if (size() > 0){
                    returned = this.array[index];
                    for (int i = index; i < size; i++){  
                        this.array[i] = this.array[i+1];
                    }
                    this.array[size] = null;
                    size--;
                    return returned;
                    
                } else {
                    throw new RuntimeException("Cannot remove from an empty array.");
                }
            } else {
                throw new IndexOutOfBoundsException("Invalid index.");
            }
    }

    public String toString(){
        String printed = "[";
        String returned = "";
        if (this.size == 0){
            return "[]";
        } else {
            for (int i = 0; i < this.size; i++){
                printed += this.array[i].toString() + ", ";
            }
            returned = printed.substring(0, (printed.length()-2)) + "]";
            return returned;
        }
    }

    public DynamicArray<T> append(DynamicArray<T> newArray){
        if (this.size + newArray.size() > this.capacity){
            throw new RuntimeException("Capacity reached. Cannot add these Dynamic Arrays together");
        } else {
            int newSize = this.size + newArray.size();
            DynamicArray<T> returned = new DynamicArray<T>(newSize);

            for (int i = 0; i < this.size; i++){
                returned.add(this.array[i]);
            }
            int j = 0;
            for (int i = this.size; i < newSize; i++){
                returned.add(newArray.get(j));
                j++;
            }
            return returned;
        }
    } 

    // this doesn't work LOL
    //`addAll` inserts all the elements of a passed `DynamicArray` at the specified index, returning the result as a new `DynamicArray`.
    public DynamicArray<T> addAll(DynamicArray<T> newArray, int index){
        if (index > this.size || index < 0){
            throw new IndexOutOfBoundsException("Index must be between 0 and the size of the array.");
        } else {
            if (this.size + newArray.size() > this.capacity){
                throw new RuntimeException("Capacity reached. Cannot add these Dynamic Arrays together");
            } else {
                DynamicArray<T> returned = new DynamicArray<T>(this);
                for (int i = newArray.size() - 1; i >= 0; i--){
                    returned.add(index, newArray.get(i));
                }
                return returned;
            }
        }
    }

    //`splitSuffix` returns the elements from a specified index and after as a new `DynamicArray`.
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

    //* `delete` removes the elements spanning from the first index up to just before the other, i.e., [fromIndex, toIndex). 
    // It returns a new DynamicArray with that range removed; the original is unchanged.

    // array of size 5. delete (1,3) --> delete 2 things.
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



//`append` concatenates a passed `DynamicArray` to the end of the current `DynamicArray` and returns the result as a new `DynamicArray` object.


// `splitPrefix` returns the elements before a specified index as a new `DynamicArray`.


//* `extract` returns a new `DynamicArray` containing the elements from one index up to just before another, [fromIndex, toIndex).

public static void main(String args[]){
    DynamicArray<Integer> nums = new DynamicArray<Integer>(8);
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
    System.out.println(nums);
    nums.add(1, 555); // 
    System.out.println(nums.size());

    System.out.println("------------------");

    nums2.add(-1);
    nums2.add(-50);

    nums3 = nums.append(nums2);
    System.out.println(nums3);

    nums4 = nums.splitPrefix(1);
    System.out.println(nums4);

}
}

