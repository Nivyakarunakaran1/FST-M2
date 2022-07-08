package examples;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrderExecution {
    @Test
    void test0() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(3)
    void test1() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(1)
    void test2() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(2)
    void test3() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void test4() {
        assertEquals(2, 1 + 1);
    }
}
