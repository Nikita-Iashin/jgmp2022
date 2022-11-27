package org;

import org.algorithms.Search;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTest {

    @Test
    void smokeTestForSort() {
        Search search = new Search();
        int testNum = 10;
        int[] testArr = {2, 3, 4, testNum, 40};
        int result = search.binarySearch(testArr, testNum);
        assertEquals(result, 3, "It seems the method did not find expected value index");
    }

    @Test
    void smokeTestForRecursiveBinarySearch() {
        Search search = new Search();
        int[] testArr = {2, 5, 6, 8, 9, 10};
        int key = 5;
        int start = 0;
        int end = testArr.length - 1;

        int index = search.binarySearchRecursive(key, testArr, start, end);

        assertEquals(1, index, "The index of the number is not found");
    }
}
