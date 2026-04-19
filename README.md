# Search Recovery Lab

Implementación y benchmarking de algoritmos de búsqueda clásicos en Java, con verificación mediante tests JUnit 5 y medición de rendimiento con JMH 1.37.

---

## Algoritmos implementados

### BinarySearch
- **lowerBound / upperBound**: primer índice `>= key` / `> key` en arreglo ordenado.
- **countOccurrences**: conteo de apariciones usando `upperBound - lowerBound`.
- **bisectAnswer**: búsqueda binaria sobre la respuesta para predicados monótonos.
- **Complejidad**: O(log n) tiempo, O(1) espacio.

### KMP (Knuth-Morris-Pratt)
- Búsqueda de todas las ocurrencias de un patrón en un texto, incluyendo solapamientos.
- Función de fallo (`buildFailure`) en O(m).
- **Complejidad**: O(n + m) tiempo, O(m) espacio.

### SuffixArray
- Construcción del suffix array por ordenamiento de sufijos: O(n log² n).
- Array LCP mediante algoritmo de Kasai: O(n).
- Búsqueda de patrón con búsqueda binaria: O(m log n).

### TaskScheduler
- Caso de uso de `bisectAnswer`: distribución óptima de tareas entre k trabajadores minimizando la carga máxima.
- **Complejidad**: O(n log(sum)) tiempo.

---

## Resultados del Benchmark (JMH 1.37)

Modo: `AverageTime` | Unidad: `µs/op` | Iteraciones: 25

| Benchmark       |       n | Score (µs/op) | Error (µs/op) |
|-----------------|--------:|--------------:|--------------:|
| binarySearch    |   10000 |         0.014 |       ± 0.001 |
| binarySearch    |  100000 |         0.018 |       ± 0.001 |
| binarySearch    | 1000000 |         0.022 |       ± 0.001 |
| linearSearch    |   10000 |         1.229 |       ± 0.002 |
| linearSearch    |  100000 |        12.234 |       ± 0.113 |
| linearSearch    | 1000000 |       121.840 |       ± 0.274 |
| kmpSearch       |   10000 |        40.329 |       ± 0.329 |
| kmpSearch       |  100000 |       400.433 |       ± 1.303 |
| kmpSearch       | 1000000 |      4024.267 |       ± 39.246|

---

## Análisis

- **binarySearch** confirma O(log n): al multiplicar n por 10, el tiempo crece ~0.004 µs (logarítmico), prácticamente constante.
- **linearSearch** confirma O(n): al multiplicar n por 10, el tiempo se multiplica por ~10.
- **kmpSearch** confirma O(n): escala linealmente con n (×10 en tiempo al ×10 en n). El patrón usado (`"a"×20 + "b"`) fuerza el peor caso recorriendo todo el texto.

---

## Ejecución

```bash
# Tests
mvn test

# Benchmark
mvn package -DskipTests && java -jar target/benchmarks.jar