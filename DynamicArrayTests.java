import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.hamcrest.core.Every;

public class DynamicArrayTests {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> a3;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;
    //have to have data named data for copy constructor

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        a3 = stringToArray("exyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s){
        assertEquals("["+s+"] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("["+s+"] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~

    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
    compareToString(a1.append(s),"abcdefs");
    compareToString(s.append(a1),"sabcdef");
    compareToString(s.append(s),"ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }
//Every constructor and method should be tested.  Besides covering the expected 
// use cases, try to think about edge cases -- the exceptions to the normal 
// assumptions that are nevertheless still valid usage.  Additionally, if a method 
// is supposed to throw an exception in certain circumstances, you should write a test 
// to verify that it does.

    /**
     * Tests size() on an empty array
     * results in "0"
     */
    @Test
    public void testSizeEmpty(){
        assertEquals(empty.size(), 0);
    }

    /**
     * Tests size() on populated array
     * is accurate
     */
    @Test
    public void testSize(){
        assertEquals(a2.size(), 4);
    }

    /**
     * tests isEmpty() is accurate on an empty array
     */
    @Test
    public void testisEmpty(){
        assertEquals(empty.isEmpty(), true);
    }

    /**
     * tests that get on an empty array
     * results in IndexOutOfBoundsException
     */
    @Test
    public void getEmptyTest() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            empty.get(0));

        assertEquals("Invalid index.", exception.getMessage());
    }

    /**
     * 
     * Tests that get on an array with negative index
     * results in IndexOutOfBoundsException
     */
    @Test
    public void getNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.get(-1));

        assertEquals("Invalid index.", exception.getMessage());
    }

    /**
     * Tests that get with index equal to size on an array
     * results in IndexOutOfBoundsException
     */
    @Test public void getIndexSize(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            empty.get(4));

        assertEquals("Invalid index.", exception.getMessage());
    }

    /**
     * Tests that get on populated array
     * results in the value being returned
     */
    @Test public void getNormal(){
        assertEquals((char) a2.get(0), (char) 'w');
    }

    /**
     * Tests that set with a negative index
     * results in IndexOutOfBoundsException
     */
    @Test public void setNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.set(-4, 'h'));

        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void setEmptyTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            empty.set(0, 'h'));

        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void setSizeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.set(4, 'h'));

        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void setNormalTest(){
        a2.set(0, 'e');
        assertEquals(a2.toString(), "[e, x, y, z]");
    }

    @Test public void addNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.add(-4, 'h'));

        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void addLargeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.add(5, 'h'));

        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void addWorksTest(){
        a2.add(0, 'h');
        assertEquals(a2.toString(), "[h, w, x, y, z]");
    }

    @Test public void addWorksTest2(){
        a2.add('h');
        assertEquals(a2.toString(), "[w, x, y, z, h]");
    }

    @Test public void removeNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.remove(-2));

        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void removeLargeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.remove(4));

        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void removeEmptyTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            empty.remove(0));
        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void removeNormalTest(){
        a2.remove(3);
        assertEquals(a2.toString(), "[w, x, y]");
    }

    @Test public void toStringEmptyTest(){
        assertEquals(empty.toString(), "[]");
    }

    @Test public void addAllNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.addAll(a1, -1));
        assertEquals("Index must be between 0 and the size of the array.", exception.getMessage());
    }

    @Test public void addAllEmptyTest(){
        a2.addAll(empty, 2);
        assertEquals(a2.toString(), "[w, x, y, z]");
    }

    @Test public void addAllWorksTest(){
        assertEquals(a2.addAll(s, 0).toString(), "[s, w, x, y, z]");
    }

    @Test public void splitSuffixNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.splitSuffix(-1));
        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void splitSuffixLargeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.splitSuffix(5));
        assertEquals("Invalid index.", exception.getMessage());
    }

    @Test public void splitSuffixWorksTest(){
        assertEquals(a2.splitSuffix(2).toString(), "[y, z]");
    }

    @Test public void splitPrefixNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.splitPrefix(-1));
        assertEquals("Invalid index. Must be between 0 and the size of the array.", exception.getMessage());
    }

     @Test public void splitPrefixLargeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.splitPrefix(5));
        assertEquals("Invalid index. Must be between 0 and the size of the array.", exception.getMessage());
    }

    @Test public void splitPrefixWorksTest(){
        assertEquals(a2.splitPrefix(2).toString(), "[w, x]");
    }

    @Test public void deleteNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.delete(-1, 2));
        assertEquals("Invalid index. Must be between 0 and the size of the array.", exception.getMessage());
    }

    @Test public void deleteLargeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.delete(1, 6));
        assertEquals("Invalid index. Must be between 0 and the size of the array.", exception.getMessage());
    }

    @Test public void deleteOppositeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.delete(3, 1));
        assertEquals("fromIndex must be less than toIndex", exception.getMessage());
    }

    @Test public void deleteWorksTest(){
        assertEquals(a2.delete(1, 3).toString(), "[w, z]");
    }

    @Test public void extractNegativeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.delete(-1, 3));
        assertEquals("Invalid index. Must be between 0 and the size of the array.", exception.getMessage());
    }

    @Test public void extractLargeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.extract(1, 6));
        assertEquals("Invalid index. Must be between 0 and the size of the array.", exception.getMessage());
    }

    @Test public void extractOppositeTest(){
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () ->
            a2.extract(3, 1));
        assertEquals("fromIndex must be less than toIndex", exception.getMessage());
    }

    @Test public void extractWorksTest(){
        assertEquals(a2.extract(1, 3).toString(), "[x, y]");
    }
    


    

}




