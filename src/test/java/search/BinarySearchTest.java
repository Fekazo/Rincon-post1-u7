package search;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTest {

    // Checkpoint 1: lowerBound, upperBound, countOccurrences
    @Test
    void testLowerBound() {
        int[] arr = {1, 3, 3, 5, 7};
        assertEquals(1, BinarySearch.lowerBound(arr, 3));
    }

    @Test
    void testUpperBound() {
        int[] arr = {1, 3, 3, 5, 7};
        assertEquals(3, BinarySearch.upperBound(arr, 3));
    }

    @Test
    void testCountOccurrences() {
        int[] arr = {1, 3, 3, 5, 7};
        assertEquals(2, BinarySearch.countOccurrences(arr, 3));
    }

    // Checkpoint 2: bisectAnswer con TaskScheduler
    @Test
    void testBisectAnswerTaskScheduler() {
        long[] tasks = {3, 5, 2, 8, 4};
        int k = 3;
        long result = TaskScheduler.minMaxLoad(tasks, k);
        assertEquals(10L, result);
    }
}