package search;

/**
 * Variantes robustas de búsqueda binaria.
 * Invariante lowerBound: arr[result] >= key para todo result en [0, n].
 * Invariante upperBound: arr[result] >  key para todo result en [0, n].
 */
public class BinarySearch {

    /**
     * Retorna el índice del primer elemento >= key.
     * Si todos los elementos son < key, retorna arr.length.
     */
    public static int lowerBound(int[] arr, int key) {
        int lo = 0, hi = arr.length; // [lo, hi) semiabierto
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2; // evita desbordamiento
            if (arr[mid] < key) lo = mid + 1;
            else                hi = mid;
        }
        return lo;
    }

    /**
     * Retorna el índice del primer elemento > key.
     */
    public static int upperBound(int[] arr, int key) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= key) lo = mid + 1;
            else                 hi = mid;
        }
        return lo;
    }

    /**
     * Cuenta cuántas veces aparece key en el arreglo ordenado.
     */
    public static int countOccurrences(int[] arr, int key) {
        return upperBound(arr, key) - lowerBound(arr, key);
    }

    /**
     * Búsqueda sobre la respuesta: mínimo x en [lo, hi] tal que
     * predicate(x) = true.
     * Precondición: predicate es monótona (false...false, true...true).
     */
    public static long bisectAnswer(long lo, long hi,
                                    java.util.function.LongPredicate predicate) {
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (predicate.test(mid)) hi = mid;
            else                     lo = mid + 1;
        }
        return lo;
    }
}
