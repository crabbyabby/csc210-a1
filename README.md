# A1 DynamicArray

## Basic Information

Your name: Abigail Lei

Other students you worked with, including TAs:
Claire Newcombe

If anyone was particularly helpful, please give them a shout-out here: 
Claire Newcombe

## References

Any references or resources used besides JavaDoc and course materials:
https://www.geeksforgeeks.org/java/generics-in-java/
https://www.w3schools.com/java/ref_keyword_implements.asp
https://www.geeksforgeeks.org/java/copy-constructor-in-java/
https://www.w3schools.com/java/java_arraylist.asp
https://www.geeksforgeeks.org/java/system-arraycopy-in-java/
https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-tests/2935935#2935935

If you used generative AI, how did you use it? What role did it play in your learning?


## Questions to Answer

What is the difference between size and capacity?
Size is the number of elements in the array, while capacity is how many elements the array can hold. Size will always be less than or equal to capacity.

When do we resize and why?
We resize when the size of the array is equal to the capacity and we are still adding new elements into the DynamicArray. This is so the size() < capacity at all times.

Which operations require shifting and why?
Any operation that adds or deletes an element from the middle of the array / not the very end of the array. This includes add (with an index), remove(int index) and addAll.

## Reflection 

Please provide a brief reflection about your experience with this assignment. What was easiest? What was hardest? How did your understanding of arrays and lists evolve?

This assignment took me a long time, I enjoyed implementing all of the specific functions because it made me understand it better. I had a hard time grasping the concept of the backing array, but after working on it it got better. It was kind of annoying to write a lot of JavaDoc! I had a fun time doing the unit tests again though.
