import java.io.*;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Wettkampfkartenersteller
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int anzahl;
    private JFileChooser chooser = new JFileChooser();
    private JFileChooser directoryChooser = new JFileChooser();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
    private String pfad = "";

    /**
     * Konstruktor für Objekte der Klasse Wettkampfkartenersteller
     */
    public Wettkampfkartenersteller()
    {
        // Instanzvariable initialisieren

    }

    public void kartenErstellen(int pAnzahl){
        anzahl = pAnzahl;
        String[] karten = erzeugeKarten(anzahl);
        schreibeDatei(karten);
    }

    private String[] erzeugeKarten(int anzahl){
        Random r = new Random();
        String[] out = new String[anzahl];

        for (int i = 0; i < anzahl; i++){
            String klasse = Integer.toString(r.nextInt(5, 12));
            String alter = Integer.toString(r.nextInt(10,19));
            String lauf = Integer.toString(r.nextInt(0,1000));
            String sprung = Integer.toString(r.nextInt(0,1000));
            String punkte = Integer.toString(r.nextInt(0,1000));

            out[i] = "Name" + Integer.toString(i);
            out[i] = out[i] + ",Vorname" + Integer.toString(i);
            out[i] = out[i] +"," + klasse;
            out[i] = out[i] +"," + alter;
            out[i] = out[i] +"," + lauf;
            out[i] = out[i] +"," + sprung;
            out[i] = out[i] +"," + punkte;
        }
        return out;
    }

    private void schreibeDatei(String[] quelle){
        chooser.setSelectedFile(new File("Unbenannt 1")); // Namensvorschlag für Datei erstellen
        int wahl = chooser.showSaveDialog(null);

        if(wahl == JFileChooser.APPROVE_OPTION) {
            pfad = chooser.getSelectedFile().getPath();     // Pfad speichern. Notwendig für die "speichern" Option
            schreibe(chooser.getSelectedFile().getPath(), quelle); // Speichern der Datei
        }
        chooser.setSelectedFile(new File("")); // Namensvorschlag zurücksetzen, damit dieser nicht angezeigt wird, wenn der FileChooser genutzt wird
    }

    private void schreibe(String pfad, String[] quelle){
        File file = new File(pfad + ".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < quelle.length; i++) {
                writer.write(quelle[i]);
                writer.newLine();
            }
            writer.flush();
        }
        catch(IOException ioe) {
            System.err.println(ioe);
        }
    }
}