package org.algorithms;

/**
 * recursively (0.25 points)
 * <p>
 * iteratively (0.25 points)
 * <p>
 * discover implementation effectiveness (using unit tests and benchmarks) and provide description of results
 * in readme.md (discover complexity, provide time comparison, and explore which approach
 * is more suitable in different situations).
 */
public class Search {

    public int binarySearch(int arr[], int x) {

        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (arr[m] == x) {
                return m;
            }

            if (arr[m] < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }


    public int binarySearchRecursive(int search, int[] array, int start, int end) {

        int middle = (start + end) / 2;

        if (end < start) {
            return -1;
        }

        if (search < array[middle]) {
            return binarySearchRecursive(search, array, start, middle - 1);
        }

        if (search > array[middle]) {
            return binarySearchRecursive(search, array, middle + 1, end);
        }

        if (search == array[middle]) {
            return middle;
        }

        return -1;
    }
}
