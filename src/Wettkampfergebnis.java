

import java.io.*;
import java.util.Arrays;

public class Wettkampfergebnis implements Sortable{

    List<Wettkampfkarte> listW; //w = wettkampfkarten

    public Wettkampfergebnis() {
        listW = new List<>();
    }


    public void save(String pfad) {
        File file = new File(pfad);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            listW.toFirst();
            while (listW.hasAccess()) {
                writer.write(listW.getContent().kartenInfoFuerDatei());
                writer.newLine();
                listW.next();
            }
            writer.flush();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    public void saveAs(String pfad) {
        File file = new File(pfad + ".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            listW.toFirst();
            while (listW.hasAccess()) {
                writer.write(listW.getContent().kartenInfoFuerDatei());
                writer.newLine();
                listW.next();
            }
            writer.flush();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    public void load(String datName) {

        File file = new File(datName);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(datName));
            String zeile;
            while ((zeile = in.readLine()) != null) {
                listW.append(zeileAuslesen(zeile)); // Hinzufügen einer aus der Zeile erstellten Wettkampfkarte
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException ignored) {
                }
        }
    }

    private Wettkampfkarte zeileAuslesen(String pZeile) { // Einen String mit festgelegter Struktur zu einer Karte machen
        String[] karte = new String[7]; // Neues Array erstellen
        int arrayIndex = 0;

        // So oft wie das Array lang ist ausführen
        // Einen leeren String zuweisen, da es sonst null gibt
        Arrays.fill(karte, "");

        for (int i = 0; i < pZeile.length(); i++) { // So oft wie die Zeile Zeichen besitzt, da alles einzeln ausgelesen wird
            if (pZeile.charAt(i) == ',') { // Falls das Zeichen bei i ein Komma ist, sollen die folgenden Zeichen in das nächste Feld gespeichert werden
                arrayIndex++; // Um 1 erhöhen, damit das nächste Feld im Array ausgewählt werden kann
            } else { // Falls es sich nicht um ein Komma handelt, wird das Zeichen bei i zum aktuellen Feld des Arrays hinzugefügt
                karte[arrayIndex] = karte[arrayIndex] + pZeile.charAt(i); // Das Zeichen dem aktuellen Feld hinzufügen
            }
        }
        //Erstellung der Wettkampfkarte anhand der gespeicherten Informationen im Array
        return new Wettkampfkarte(karte[0], karte[1], karte[2], Integer.parseInt(karte[3]), Integer.parseInt(karte[4]), Integer.parseInt(karte[5]), Integer.parseInt(karte[6]));
    }

    public void karteHinzufuegen(String pName, String pVorname, String pKlasse, int pAlter) {
        listW.append(new Wettkampfkarte(pName, pVorname, pKlasse, pAlter));
    }

    public void karteHinzufuegen(String pName, String pVorname, String pKlasse, int pAlter, int pLauf, int pSprung, int pWurf) {
        listW.append(new Wettkampfkarte(pName, pVorname, pKlasse, pAlter, pLauf, pSprung, pWurf));
    }

    public Wettkampfkarte besteKarte(List<Wettkampfkarte> pList, int pDisziplin) {
        pList.toFirst();
        Wettkampfkarte max = pList.getContent();
        while (pList.hasAccess()){
            if(pList.getContent().getPunkte(pDisziplin) > max.getPunkte(pDisziplin)){
                max = pList.getContent();
            }
            pList.next();
        }
        return max;
    }

    public void ergaenzeKarte(Wettkampfkarte pWettkampfkarte) {
        listW.append(pWettkampfkarte);
    }

    public List<Wettkampfkarte> getWettkampfliste() {
        return this.listW;
    }
    public void sortieren(int pDisziplin, int pVerfahren){
        long start1 = System.nanoTime();
        switch (pVerfahren){
            case 0:
                listW = quickSortAlle(listW);
            case 1:
                System.out.println("Bubblesort:");
                listW = bubbleSort(listW, pDisziplin);
                break;
            case 2:
                System.out.println("Selectionsort:");
                listW = selectionSort(listW, pDisziplin);
                break;
            case 3:
                System.out.println("Insertionsort:");
                listW = insertionSort(listW,pDisziplin);
                break;
            case 4:
                System.out.println("Quicksort:");
                listW = quickSort(listW, pDisziplin);
                break;
        }
        long end1 = System.nanoTime();
        System.out.println("vergangene Zeit in Nano Sekunden: "+ (end1-start1));
    }
    @Override
    public List<Wettkampfkarte> bubbleSort(List<Wettkampfkarte> unsortiert, int pDisziplin) {
        Wettkampfkarte dummy = new Wettkampfkarte("dummy", "dummy", "dummy", -1, 10000, 10000, 10000);
        Wettkampfkarte vorgaenger;
        boolean wurdeGetauscht;
        int index;
        wurdeGetauscht = true;

        while (wurdeGetauscht) {
            wurdeGetauscht = false;
            index = 0;
            unsortiert.toFirst();
            vorgaenger = dummy;
            while (unsortiert.hasAccess()) {
                if (unsortiert.getContent().getPunkte(pDisziplin) > vorgaenger.getPunkte(pDisziplin)) {
                    Wettkampfkarte temp;
                    int pos;
                    temp = unsortiert.getContent();
                    pos = index;
                    unsortiert.remove();
                    unsortiert.toFirst();
                    for (int i = 0; i < pos - 1; i++) {
                        unsortiert.next();
                    }
                    unsortiert.insert(temp);
                    wurdeGetauscht = true;
                }
                vorgaenger = unsortiert.getContent();
                unsortiert.next();
                index++;
            }
        }
        return unsortiert;
    }

    @Override
    public List<Wettkampfkarte> selectionSort(List<Wettkampfkarte> unsortiert, int pDisziplin) {
        Wettkampfkarte besteKarte;
        List<Wettkampfkarte> hilfsListe = new List<>();

        while (!unsortiert.isEmpty()){
            besteKarte = besteKarte(unsortiert, pDisziplin);
            unsortiert.toFirst();
            while(unsortiert.getContent() != besteKarte){
                unsortiert.next();
            }
            hilfsListe.append(besteKarte);
            unsortiert.remove();
        }
        return hilfsListe;

    }

    @Override
    public List<Wettkampfkarte> insertionSort(List<Wettkampfkarte> unsortiert, int pDisziplin) {
        List<Wettkampfkarte> hilfsListe = new List<>();
        while (!unsortiert.isEmpty()){
            unsortiert.toFirst();
            Wettkampfkarte ek = unsortiert.getContent();
            hilfsListe.toFirst();
            while (hilfsListe.hasAccess() && ek.getPunkte(pDisziplin) < hilfsListe.getContent().getPunkte(pDisziplin)){
                hilfsListe.next();
            }
            if (hilfsListe.hasAccess()){
                hilfsListe.insert(ek);
            }
            else{
                hilfsListe.append(ek);
            }
            unsortiert.remove();
        }
        return hilfsListe;
    }

    @Override
    public List<Wettkampfkarte> quickSort(List<Wettkampfkarte> unsortiert, int pDisziplin) {
        return null;
    }

    public List<Wettkampfkarte> quickSortAlle(List<Wettkampfkarte> unsortiert){
        unsortiert.toFirst();
        unsortiert.next();
        unsortiert.next();
        if (unsortiert.hasAccess()){
            unsortiert.toFirst();
            Wettkampfkarte piv = unsortiert.getContent();
            int pPiv = piv.getPunkte(1) + piv.getPunkte(2) + piv.getPunkte(3);
            unsortiert.remove();
            List<Wettkampfkarte> groessere = new List<>();
            List<Wettkampfkarte> kleinere = new List<>();
            while (!unsortiert.isEmpty()){
                if ((unsortiert.getContent().getPunkte(1)+unsortiert.getContent().getPunkte(2)+unsortiert.getContent().getPunkte(3)) > pPiv){
                    groessere.append(unsortiert.getContent());
                    unsortiert.remove();
                }
                else {
                    kleinere.append(unsortiert.getContent());
                    unsortiert.remove();
                }
            }
            quickSortAlle(groessere);
            quickSortAlle(kleinere);
            unsortiert.concat(groessere);
            unsortiert.append(piv);
            unsortiert.concat(kleinere);
        }
        return unsortiert;
    }
}

