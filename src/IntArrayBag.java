public class IntArrayBag implements Cloneable { // Implement 'Cloneable' to implement the 'clone()' method
    private static int index;
    private int[] data; // Name of the array
    private int manyItems; // Used to keep track the number of elements

    public IntArrayBag() {
        final int DEFAULT_CAPACITY = 10;
        data = new int[DEFAULT_CAPACITY]; // Default capacity of 10
    }

    public IntArrayBag(int initialCapacity) {
        // Validate that the capacity is not less than 0
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid Capacity. " + "\nYou entered: " + initialCapacity);
        }
        manyItems = 0; // Indicates that the array does not have any elements
        data = new int[initialCapacity]; // Allocates memory for an array with a specified capacity value
    }

    public static IntArrayBag union(IntArrayBag bag1, IntArrayBag bag2) { // This method will combine two arrays
        if (bag1 == null || bag2 == null) { // If one of the bags is empty, then throw an exception
            throw new NullPointerException("One of the bags is empty");
        }
        IntArrayBag answer = new IntArrayBag(bag1.size() + bag2.size()); // The new array will have the size of both arrays combined
        System.arraycopy(bag1.data, 0, answer.data, 0, bag1.manyItems); // Copy the elements of bag1 into the new array
        System.arraycopy(bag2.data, 0, answer.data, bag1.manyItems, bag2.manyItems); // Copy the elements of bag2 into the new array
        answer.manyItems = bag1.manyItems + bag2.manyItems; // The number of elements in the new array
        return answer;
    }

    // This method returns true if the array contains the specified value
    private static boolean contains(int[] a, int value) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value) {
                return true;
            }
        }
        return false; // Returns false if value was not found
    }

    public void add(int element) {
        // If the array is full, then resize it.
        if (data.length == manyItems) {
            int[] newItems = new int[(manyItems * 2) + 1];
            // Copy all existing items of the old array into the new array.
            ensureCapacity(manyItems * 2 + 1);
            for (int i = 0; i < manyItems; i++) {
                newItems[i] = data[i];
            }
            // TODO: Future Method Insertion - ensureCapacity(...); Status - Complete
            // Set "data" to this new array.
            data = newItems;
        }
        // Add the new item at the end of the array.
        data[manyItems] = element;
        manyItems++;
    }

    // This method will add all the elements from one array to another array
    public void addAll(IntArrayBag element) {
        if (element == null) {
            throw new NullPointerException("There are no elements in the array");
        }
        // TODO: ensureCapacity(<Argument>); Status - Complete
        ensureCapacity(manyItems + element.manyItems); // Ensure that the array has enough space to hold all the elements
        System.arraycopy(element.data, 0, data, manyItems, element.manyItems);
        manyItems += element.manyItems;
    }

    // This method will allow us to add multiple elements consecutively
    public void addMany(int... elements) {
        if (manyItems + elements.length > data.length) {
            // TODO: ensureCapacity(<Argument>) Status - Complete
            ensureCapacity((manyItems + elements.length) * 2 + 1); // Ensure that the array has enough space to hold all the elements
        }
        System.arraycopy(elements, 0, data, manyItems, elements.length);
        manyItems += elements.length;
    }

    public boolean remove(int target) { // This method will remove a specified value from the array
        int index = 0; // Index of the target if it is found
        while ((index < manyItems) && (target != data[index]))
            index++;
        if (index == manyItems) // The target was not found, so nothing is removed.
            return false;
        else { // The target was found at data[index].
            manyItems--;
            data[index] = data[manyItems];
            return true;
        }
    }

    public int countOccurrences(int target) { // This method will count the number of times a specified value occurs in the array
        int answer; // The number of times target occurs in the bag.
        int index;

        answer = 0; // Initialize answer to zero.
        for (index = 0; index < manyItems; index++) {
            if (target == data[index]) {
                answer++;
            }
        }
        return answer;
    }

    // Generate a Clone method
    @Override
    public IntArrayBag clone() { // This method will clone the array
        IntArrayBag answer; // Represents the copy of the array that activates the clone method
        try {
            answer = (IntArrayBag) super.clone(); // This line creates a new array for the clone to refer to
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable");
        }
        answer.data = data.clone(); // This line creates a new array for the clone's data instance variable to refer to
        return answer;
    }

    public void ensureCapacity(int minimumCapacity) {
        int[] biggerArray;

        if (data.length < minimumCapacity) {
            biggerArray = new int[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }

    public boolean search(int target) { // This method will search for a specified value in the array
        assert (data[index] > 0) : "There are no elements in the array"; // Assertion Statement
        for (int i = 0; i < data.length; i++) { // Iterate through the length of the array
            if (data[i] == target) {
                return true;
            }
        }
        return false;
    }

    // This method returns the largest value in an array
    public int maxOfArray(int[] array) {
        int maxValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        // Validate the computation using Assertion Statements
        assert contains(array, maxValue) : "maxOfArray answer is not contained within the array";
        return maxValue;
    }

    public void fill(int value) { // This method will fill the array with a specified value
        int i;
        for (i = 0; i < data.length; i++) {
            data[i] = value;
        }
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    public int size() {
        return data.length;
    }
}
