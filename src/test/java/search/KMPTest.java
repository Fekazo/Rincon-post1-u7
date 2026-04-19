package search;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class KMPTest {

    @Test
    void testBuildFailure() {
        int[] expected = {0, 0, 1, 2, 0, 1, 2, 3, 4};
        assertArrayEquals(expected, KMP.buildFailure("ABABCABAB"));
    }

    @Test
    void testSearchOverlapping() {
        List<Integer> result = KMP.search("AABAABAABAAB", "AABA");
        assertEquals(List.of(0, 3, 6), result);
    }
}