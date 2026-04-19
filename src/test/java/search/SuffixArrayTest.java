package search;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuffixArrayTest {

    private final SuffixArray banana = new SuffixArray("banana");

    @Test
    void testSuffixArray() {
        assertArrayEquals(new int[]{5, 3, 1, 0, 4, 2}, banana.sa);
    }

    @Test
    void testLCP() {
        assertArrayEquals(new int[]{0, 1, 3, 0, 0, 2}, banana.lcp);
    }

    @Test
    void testContainsTrue() {
        assertTrue(banana.contains("ana"));
    }

    @Test
    void testContainsFalse() {
        assertFalse(banana.contains("xyz"));
    }
}