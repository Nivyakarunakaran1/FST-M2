package examples;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonNameTest {

    @Test
    void firstNameStartsWithJ() {
        Person person = new Person("John", "Doe");

        // Assertion
        assertTrue(person.getName().startsWith("J")); //Pass
        assertFalse(person.getName2().startsWith("J")); // Fail
    }

    @Test
    void personHasFirstName() {
        Person person = new Person("John", "Doe");
        // Assertion
        assertNotNull(person.getName()); //Pass
        assertNull(person.getName2()); //Fail
    }

    @Test
    void iterablesEqual() {
        final List<String> list = Arrays.asList("orange", "mango", "banana");
        final List<String> expected = Arrays.asList("banana", "mango", "orange");
        // Sort array
        Collections.sort(list);

        // Assertion
        assertIterableEquals(expected, list); //Pass
    }

    @Test
    void iterablesAddEqual() {
        final List<String> list = Arrays.asList("orange", "mango", "banana");
        final List<String> expected = Arrays.asList("banana", "mango", "orange");

        // Add a new value
        list.add("apple");
        // Sort array
        Collections.sort(list);

        // Assertion
        assertIterableEquals(expected, list); //Fail
    }

    @Test
    void arraysEqual() {
        final int[] array = { 3, 2, 1 };
        final int[] expected = { 1, 2, 3 };

        // Sorting an Array
        Arrays.sort(array);

        // Assertion
        assertArrayEquals(expected, array); //Pass
    }


}
