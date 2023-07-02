import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntArrayBag bag = new IntArrayBag();
        System.out.println("The default capacity of the array: " + bag.getCapacity());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("An empty array:");
        bag.print();
        System.out.println("-------------------------------------------------------------------");
        bag.add(10);
        bag.add(50);
        bag.add(150);
        bag.add(20);
        bag.add(80);
        bag.add(20);
        bag.add(70);
        bag.add(100);
        bag.add(55);
        bag.add(40);
        System.out.println("The array after adding 10 elements:");
        bag.print();
        // After adding our 11th element into the array, the capacity of the array will double and add one
        bag.add(500);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("The current capacity of the array after adding our 11th element: " + bag.getCapacity());
        System.out.println("-------------------------------------------------------------------");
        // We know for a fact that our new capacity is of size 21 because (10 * 2) + 1  =>  20 + 1  =>  21
        // Now lets print the elements of the array
        System.out.println("The array after adding our 11th element:");
        bag.print();
        System.out.println("-------------------------------------------------------------------");
    }
}