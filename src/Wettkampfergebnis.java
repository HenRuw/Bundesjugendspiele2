

import java.io.*;
import java.util.Arrays;

public class Wettkampfergebnis {

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

    public Wettkampfkarte besteKarte(int pDisziplin) {
        return null;
    }

    public void ergaenzeKarte(Wettkampfkarte pWettkampfkarte) {
        listW.append(pWettkampfkarte);
    }

    public List<Wettkampfkarte> getWettkampfliste() {
        return this.listW;
    }

    public void bubbleSort(int pDisziplin) {
        Wettkampfkarte dummy = new Wettkampfkarte("dummy", "dummy", "dummy", -1, 10000, 10000, 10000);
        Wettkampfkarte vorgaenger;
        boolean wurdeGetauscht;
        int index;
        wurdeGetauscht = true;

        while (wurdeGetauscht) {
            wurdeGetauscht = false;
            index = 0;
            listW.toFirst();
            vorgaenger = dummy;
            while (listW.hasAccess()) {
                if (listW.getContent().getPunkte(pDisziplin) > vorgaenger.getPunkte(pDisziplin)) {
                    Wettkampfkarte temp;
                    int pos;
                    temp = listW.getContent();
                    pos = index;
                    listW.remove();
                    listW.toFirst();
                    for (int i = 0; i < pos - 1; i++) {
                        listW.next();
                    }
                    listW.insert(temp);
                    wurdeGetauscht = true;
                }
                vorgaenger = listW.getContent();
                listW.next();
                index++;
            }
        }
    }
}

