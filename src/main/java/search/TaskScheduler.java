package search;

import java.util.Arrays;
import java.util.function.LongPredicate;

/**
 * Caso de uso de bisectAnswer: distribución óptima de tareas entre k trabajadores.
 * Minimiza la carga máxima usando búsqueda binaria sobre la respuesta.
 */
public class TaskScheduler {

    /**
     * Retorna la carga máxima mínima posible al distribuir tasks entre k trabajadores.
     */
    public static long minMaxLoad(long[] tasks, int k) {
        // ¿Puede distribuirse con carga máxima = maxLoad?
        LongPredicate canDistribute = maxLoad -> {
            int workers = 1;
            long current = 0;
            for (long task : tasks) {
                if (task > maxLoad) return false;
                if (current + task > maxLoad) { workers++; current = task; }
                else current += task;
            }
            return workers <= k;
        };

        long lo = Arrays.stream(tasks).max().getAsLong();
        long hi = Arrays.stream(tasks).sum();
        return BinarySearch.bisectAnswer(lo, hi, canDistribute);
    }
}