import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {
    @Test
    void testExceptions() {
        System.out.println("Testing exceptions mentioned in the Javadoc...");

        // in constructor (int)
        assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<Object>(0);
        });

        // in constructor (int, Comparator)
        assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<Object>(0, null);
        });

        // in constructor (Collection)
        assertThrows(NullPointerException.class, () -> {
            ArrayList<Object> arr = new ArrayList<>();
            arr.add(null);
            new PriorityQueue<Object>(arr);
        });
        class CustomObject {
            String _id;
            public CustomObject(String id) { _id = id; }
        }
        assertThrows(ClassCastException.class, () -> {
            ArrayList<CustomObject> list = new ArrayList<>();
            list.add(new CustomObject("a"));
            list.add(new CustomObject("b"));
            new PriorityQueue<Object>(list);
        });

        // in add(E e)
        PriorityQueue<Object> queue = new PriorityQueue<>();
        assertThrows(NullPointerException.class, () -> {
            queue.add(null);
        });
        assertThrows(ClassCastException.class, () -> {
           queue.add(new CustomObject("a"));
        });
    }

    @ParameterizedTest
    @CsvSource({"3 7 4 5 4, 3 4 4 5 7",
            "1 8 11 2 10, 1 2 8 10 11",
            "3 15 11 4 20 6 9 5 8 12, 3 4 5 6 8 9 11 12 15 20",
            "8 1 18 14 13 6 10 5 15 16 19, 1 5 6 8 10 13 14 15 16 18 19",
            "2 10 21 18 14 13 16 17 15 5, 2 5 10 13 14 15 16 17 18 21",
    })
    void testOrder(String input, String expected) {
        ArrayList<Integer> inputList = new ArrayList<>();
        ArrayList<Integer> expectedList = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (String s : input.split(" ")) {
            inputList.add(Integer.parseInt(s));
        }
        for (String s : expected.split(" ")) {
            expectedList.add(Integer.parseInt(s));
        }
        inputList.forEach((x) -> queue.add(x));
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        assertArrayEquals(expectedList.toArray(), result.toArray());
    }
}
