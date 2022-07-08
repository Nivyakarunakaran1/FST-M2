package examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Simple multiplication should work")
    public void testMultiply() {
        assertEquals(20, calculator.multiply(4, 5));
    }

    @Test
    void addNumbers() {
        Calculator calculator = new Calculator();
        // Assertion
        assertEquals(3, calculator.add(1, 2)); // Passes
        assertNotEquals(3, calculator.add(1, 2)); // Fails
    }

    @Test
    void addNumbers2() {
        Calculator calculator = new Calculator();

        // Assertion with a custom message
        // that will be displayed if the test fails
        assertEquals(3, calculator.add(1, 2), "1 + 2 should equal 3");
    }

}
