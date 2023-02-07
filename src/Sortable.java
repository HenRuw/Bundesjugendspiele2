
/**
 * Ein Interface, die Methoden für das Sortieren einer Liste bereitstellt
 *
 * @author Thomas Räder
 * @version 1.0
 */

public interface Sortable
{
    List<Wettkampfkarte> radixSort(List<Wettkampfkarte> unsortiert, int pDisziplin);
    List<Wettkampfkarte> bubbleSort(List<Wettkampfkarte> unsortiert, int pDisziplin);

    List<Wettkampfkarte> selectionSort(List<Wettkampfkarte> unsortiert, int pDisziplin);

    List<Wettkampfkarte> insertionSort(List<Wettkampfkarte> unsortiert, int pDisziplin);

    List<Wettkampfkarte> quickSort(List<Wettkampfkarte> unsortiert, int pDisziplin);
}
