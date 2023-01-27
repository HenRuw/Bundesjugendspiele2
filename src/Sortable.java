
/**
 * Ein Interface, die Methoden für das Sortieren einer Liste bereitstellt
 *
 * @author Thomas Räder
 * @version 1.0
 */

public interface Sortable
{
    List<Wettkampfkarte> bubbleSort(List<Wettkampfkarte> unsortiert);

    List<Wettkampfkarte> selectionSort(List<Wettkampfkarte> unsortiert);

    List<Wettkampfkarte> insertionSort(List<Wettkampfkarte> unsortiert);

    List<Wettkampfkarte> quickSort(List<Wettkampfkarte> unsortiert);
}
