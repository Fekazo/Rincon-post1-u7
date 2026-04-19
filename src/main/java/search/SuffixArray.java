package search;

/**
 * Suffix Array con construcción por ordenamiento (O(n log² n)) y
 * array LCP mediante algoritmo de Kasai (O(n)).
 * Permite búsqueda de patrón en O(m log n).
 */
public class SuffixArray {

    private final String s;
    private final int n;
    public final int[] sa;  // sa[i] = inicio del i-ésimo sufijo menor
    public final int[] lcp; // lcp[i] = LCP entre sufijos sa[i-1] y sa[i]

    public SuffixArray(String s) {
        this.s   = s;
        this.n   = s.length();
        this.sa  = buildSA();
        this.lcp = buildLCP();
    }

    private int[] buildSA() {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        java.util.Arrays.sort(idx, (a, b) -> s.substring(a).compareTo(s.substring(b)));
        int[] sa = new int[n];
        for (int i = 0; i < n; i++) sa[i] = idx[i];
        return sa;
    }

    /** Algoritmo de Kasai: construye LCP en O(n). */
    private int[] buildLCP() {
        int[] rank = new int[n], lcp = new int[n];
        for (int i = 0; i < n; i++) rank[sa[i]] = i;
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) h++;
                lcp[rank[i]] = h;
                if (h > 0) h--;
            }
        }
        return lcp;
    }

    /**
     * Verifica si pattern ocurre en el texto usando búsqueda binaria.
     * Complejidad: O(m log n).
     */
    public boolean contains(String pattern) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            String suffix = s.substring(sa[mid]);
            String prefix = suffix.length() >= pattern.length()
                    ? suffix.substring(0, pattern.length()) : suffix;
            int c = prefix.compareTo(pattern);
            if (c == 0)      return true;
            else if (c < 0)  lo = mid + 1;
            else             hi = mid - 1;
        }
        return false;
    }
}