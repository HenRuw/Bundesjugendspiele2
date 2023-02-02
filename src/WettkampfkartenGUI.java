

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.*;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Beschreibung
 *
 * @version 1.0 vom 11.01.2023
 */

public class WettkampfkartenGUI extends JFrame {
    // Anfang Attribute
    private final JTextArea taWettkampfliste = new JTextArea();

    private final JTextField tfName = new JTextField();
    private final JTextField tfVorname = new JTextField();
    private final JTextField tfKlasse = new JTextField();
    private final JTextField tfAlter = new JTextField();
    private final JTextField tfLauf = new JTextField();
    private final JTextField tfSprung = new JTextField();
    private final JTextField tfWurf = new JTextField();
    private final JTextField tfDisziplin = new JTextField();
    private final JTextField tfVerfahren = new JTextField();

    private final JLabel lbWettkampfliste = new JLabel();
    private final JLabel lbStandardSpeicherortAendern = new JLabel();

    private final JFileChooser chooser = new JFileChooser();
    private final JFileChooser directoryChooser = new JFileChooser();
    private String pfad = "";
    private final List<String> configs = new List<>();

    private final Wettkampfergebnis we = new Wettkampfergebnis();
    // Ende Attribute

    public WettkampfkartenGUI() {
        // Frame-Initialisierung
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 848;
        int frameHeight = 530;
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setTitle("WettkampfkartenGUI");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        // Anfang Komponenten
        taWettkampfliste.setEditable(false);
        chooser.setDialogTitle("Datei auswählen");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
        chooser.addChoosableFileFilter(filter); // Filter für .txt Dateien
        chooser.setAcceptAllFileFilterUsed(false); // Alle Dateien deaktivieren, damit nur nach .txt Dateien gesucht werden kann
        directoryChooser.setDialogTitle("Pfad auswählen");
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        lbWettkampfliste.setBounds(328, 8, 488, 24);
        lbWettkampfliste.setText("Wettkampfliste");
        cp.add(lbWettkampfliste);
        tfDisziplin.setBounds(416, 312, 40, 24);
        tfDisziplin.setText("");
        cp.add(tfDisziplin);
        JButton btBester = new JButton();
        btBester.setBounds(520, 312, 88, 24);
        btBester.setText("Beste/r");
        btBester.setMargin(new Insets(2, 2, 2, 2));
        btBester.addActionListener(this::btBester_ActionPerformed);
        cp.add(btBester);
        JButton btZeit = new JButton();
        btZeit.setBounds(96, 400, 96, 24);
        btZeit.setText("Zeit");
        btZeit.setMargin(new Insets(2, 2, 2, 2));
        btZeit.addActionListener(this::btZeit_ActionPerformed);
        cp.add(btZeit);
        JButton btNeueKarteHinzufuegen = new JButton();
        btNeueKarteHinzufuegen.setBounds(40, 296, 184, 24);
        btNeueKarteHinzufuegen.setText("Neue Karte hinzufügen");
        btNeueKarteHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
        btNeueKarteHinzufuegen.addActionListener(this::btNeueKarteHinzufuegen_ActionPerformed);
        cp.add(btNeueKarteHinzufuegen);
        tfName.setBounds(128, 56, 96, 24);
        cp.add(tfName);
        JLabel lbWettkampfkarte = new JLabel();
        lbWettkampfkarte.setBounds(128, 24, 96, 24);
        lbWettkampfkarte.setText("Wettkampfkarte");
        cp.add(lbWettkampfkarte);
        JScrollPane jList1ScrollPane = new JScrollPane(taWettkampfliste);
        jList1ScrollPane.setBounds(328, 32, 488, 264);
        cp.add(jList1ScrollPane);

        tfVorname.setBounds(128, 88, 96, 24);
        cp.add(tfVorname);
        tfKlasse.setBounds(128, 120, 96, 24);
        cp.add(tfKlasse);
        tfAlter.setBounds(128, 152, 96, 24);
        cp.add(tfAlter);
        tfLauf.setBounds(128, 184, 96, 24);
        cp.add(tfLauf);
        tfSprung.setBounds(128, 216, 96, 24);
        cp.add(tfSprung);
        tfWurf.setBounds(128, 248, 96, 24);
        cp.add(tfWurf);
        JLabel lbName = new JLabel();
        lbName.setBounds(40, 56, 80, 24);

        lbName.setText("Name:");
        cp.add(lbName);
        JLabel lbVorname = new JLabel();
        lbVorname.setBounds(40, 88, 80, 24);
        lbVorname.setText("Vorname:");
        cp.add(lbVorname);
        JLabel lbKlasse = new JLabel();
        lbKlasse.setBounds(40, 120, 80, 24);
        lbKlasse.setText("Klasse:");
        cp.add(lbKlasse);
        JLabel lbAlter = new JLabel();
        lbAlter.setBounds(40, 152, 80, 24);
        lbAlter.setText("Alter:");
        cp.add(lbAlter);
        JLabel lbLauf = new JLabel();
        lbLauf.setBounds(40, 184, 80, 24);
        lbLauf.setText("(1) Lauf:");
        cp.add(lbLauf);
        JLabel lbSprung = new JLabel();
        lbSprung.setBounds(40, 216, 80, 24);
        lbSprung.setText("(2) Sprung:");
        cp.add(lbSprung);
        JLabel lbWurf = new JLabel();
        lbWurf.setBounds(40, 248, 80, 24);
        lbWurf.setText("(3) Wurf:");
        cp.add(lbWurf);
        JLabel lbDisziplin = new JLabel();
        lbDisziplin.setBounds(328, 312, 80, 24);
        lbDisziplin.setText("Disziplin:");
        cp.add(lbDisziplin);
        JLabel lbVerfahren = new JLabel();
        lbVerfahren.setBounds(328, 360, 80, 24);
        lbVerfahren.setText("Verfahren:");
        cp.add(lbVerfahren);
        lbStandardSpeicherortAendern.setBounds(388, 456, 165, 24);
        lbStandardSpeicherortAendern.setText("Standard Speicherort ändern");
        lbStandardSpeicherortAendern.setForeground(Color.BLUE);
        lbStandardSpeicherortAendern.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor für diese Komponente ändern

        Font defaultFont = lbStandardSpeicherortAendern.getFont();             // Standard Schrift speichern
        Font mouseEnteredFont = lbStandardSpeicherortAendern.getFont();
        Map attributes = mouseEnteredFont.getAttributes();                     // Attribute von der Standardschrift übernehmen
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON); // Unterstreichung hinzufügen

        lbStandardSpeicherortAendern.addMouseListener(new MouseAdapter() { // MouseListener hinzufügen
            public void mouseClicked(MouseEvent e) {
                lbStandardSpeicherortAendern_MouseClicked();     //Bei Klick: Methode ausführen
            }

            public void mouseEntered(MouseEvent e) {
                lbStandardSpeicherortAendern.setFont(mouseEnteredFont.deriveFont(attributes)); // Wenn die Komponente von der Maus berührt wird: Änderung der Schrift
            }

            public void mouseExited(MouseEvent e) {
                lbStandardSpeicherortAendern.setFont(defaultFont); // Wenn die Komponente nicht mehr von der Maus berührt wird: Standard Schrift
            }
        });
        cp.add(lbStandardSpeicherortAendern);

        JButton btSortieren = new JButton();
        btSortieren.setBounds(520, 360, 88, 24);
        btSortieren.setText("Sortieren");
        btSortieren.setMargin(new Insets(2, 2, 2, 2));
        btSortieren.addActionListener(this::btSortieren_ActionPerformed);
        cp.add(btSortieren);
        JButton btSpeichern = new JButton();
        btSpeichern.setBounds(520, 408, 88, 24);
        btSpeichern.setText("Speichern");
        btSpeichern.setMargin(new Insets(2, 2, 2, 2));
        btSpeichern.addActionListener(this::btSpeichern_ActionPerformed);
        cp.add(btSpeichern);
        JButton btLaden = new JButton();
        btLaden.setBounds(632, 408, 88, 24);
        btLaden.setText("Laden");
        btLaden.setMargin(new Insets(2, 2, 2, 2));
        btLaden.addActionListener(this::btLaden_ActionPerformed);
        cp.add(btLaden);
        JButton btSpeichernUnter = new JButton();
        btSpeichernUnter.setBounds(388, 408, 108, 24);
        btSpeichernUnter.setText("Speichern unter");
        btSpeichernUnter.setMargin(new Insets(2, 2, 2, 2));
        btSpeichernUnter.addActionListener(this::btSpeichernUnter_ActionPerformed);
        cp.add(btSpeichernUnter);
        tfVerfahren.setBounds(416, 360, 40, 24);
        tfVerfahren.setText("");
        cp.add(tfVerfahren);
        // Ende Komponenten

        setVisible(true);

        configsLaden(); //configs aus der Datei in die List speichern, um sie dann übernehmen zu können

        chooser.setCurrentDirectory(new File(configInhaltSuchen()));             // Standard Pfad aus den configs übernehmen
        directoryChooser.setCurrentDirectory(new File(configInhaltSuchen()));    // Standard Pfad aus den configs übernehmen
    } // end of public WettkampfkartenGUI

    // Anfang Methoden

    public void btNeueKarteHinzufuegen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        neueKarteHinzufuegen(); // Neue Karte erstellen
        updateList();             // Listen-Ansicht aktualisieren
        // Textfelder leeren
        tfName.setText("");
        tfVorname.setText("");
        tfKlasse.setText("");
        tfAlter.setText("");
        tfLauf.setText("");
        tfSprung.setText("");
        tfWurf.setText("");
    }

    public void btZeit_ActionPerformed(ActionEvent evt) {
        long time = 0;
        int wiederholungen = 10000;
        for (int i = 0; i < wiederholungen; i++) {
            time += we.sortieren(Integer.parseInt(tfDisziplin.getText()), Integer.parseInt(tfVerfahren.getText()));
        }
        System.out.println("Die durchschnittliche Laufdauer bei "+ wiederholungen + " Wiederholungen beträgt:");
        System.out.println(time/wiederholungen);
    }

    public void btBester_ActionPerformed(ActionEvent evt) {
        we.sortieren(0,0);
        updateList();

    }

    public void btSortieren_ActionPerformed(ActionEvent evt) {
        we.sortieren(Integer.parseInt(tfDisziplin.getText()), Integer.parseInt(tfVerfahren.getText()));
        updateList();

    }

    public void btSpeichern_ActionPerformed(ActionEvent evt) {
        if (Objects.equals(pfad, "")) {        // Falls kein Pfad/Dateiname angegeben ist, kann man davon ausgehen, dass es sich um eine neue Datei handelt.
            speichernUnter(); // Deshalb kann man hier die gleiche Methode wie bei "Speichern unter" aufrufen
        } else {
            we.save(pfad);    // Falls es schon eine Datei gibt, wird sie überschrieben bzw. aktualisiert
        }
    }

    public void btLaden_ActionPerformed(ActionEvent evt) {
        laden();            // Laden der Liste
        updateList();        // Listen-Ansicht aktualisieren
    }

    public void btSpeichernUnter_ActionPerformed(ActionEvent evt) {
        speichernUnter();
    }

    public void lbStandardSpeicherortAendern_MouseClicked() {
        int wahl = directoryChooser.showOpenDialog(null);

        if (wahl == JFileChooser.APPROVE_OPTION) {
            chooser.setCurrentDirectory(directoryChooser.getSelectedFile()); // Änderung des Standardspeicherorts zum ausgewählten Speicherort
            configInhaltAendern(directoryChooser.getSelectedFile().getAbsolutePath()); // Änderung in den configs speichern
            directoryChooser.setCurrentDirectory(directoryChooser.getSelectedFile()); // Ebenfalls die Änderung für die Pfadwahl
        }
    }

    private void speichernUnter() {
        if (lbWettkampfliste.getText().length() != 14) {    // Überprüfung, ob es sich um eine geöffnete Datei handelt
            chooser.setSelectedFile(new File(lbWettkampfliste.getText().substring(16, lbWettkampfliste.getText().length() - 4)));
            int wahl = chooser.showSaveDialog(null);

            if (wahl == JFileChooser.APPROVE_OPTION) {
                pfad = chooser.getSelectedFile().getPath();
                we.saveAs(chooser.getSelectedFile().getPath());
                lbWettkampfliste.setText("Wettkampfliste: " + chooser.getSelectedFile().getName() + ".txt"); // Aktualisierung der Dateinamen-Ansicht
            }
        } else {     // Trifft ein, wenn es sich um eine neue Datei handelt
            chooser.setSelectedFile(new File("Unbenannt 1")); // Namensvorschlag für Datei erstellen
            int wahl = chooser.showSaveDialog(null);

            if (wahl == JFileChooser.APPROVE_OPTION) {
                pfad = chooser.getSelectedFile().getPath();     // Pfad speichern. Notwendig für die "speichern" Option
                we.saveAs(chooser.getSelectedFile().getPath()); // Speichern der Datei
                lbWettkampfliste.setText("Wettkampfliste: " + chooser.getSelectedFile().getName() + ".txt"); // Aktualisierung der Dateinamen-Ansicht
            }
            chooser.setSelectedFile(new File("")); // Namensvorschlag zurücksetzen, damit dieser nicht angezeigt wird, wenn der FileChooser genutzt wird
        }
    }

    private void laden() {
        int wahl = chooser.showOpenDialog(null);

        if (wahl == JFileChooser.APPROVE_OPTION) {
            taWettkampfliste.setText(""); // TextArea leeren
            pfad = chooser.getSelectedFile().getPath(); // Pfad speichern. Notwendig für die "speichern" Option
            lbWettkampfliste.setText("Wettkampfliste: " + chooser.getSelectedFile().getName()); // Ansicht für Dateinamen aktualisieren
            we.load(chooser.getSelectedFile().getPath()); // Ausgewählte Datei laden
        }
    }

    private void updateList() { // Zum Aktualisieren der Listen-Ansicht
        taWettkampfliste.setText(""); // Leeren der TextArea
        List<Wettkampfkarte> tempWListe = we.getWettkampfliste(); // Kopie der Liste erstellen
        tempWListe.toFirst(); // Zum ersten Objekt gehen
        while (tempWListe.hasAccess()) { // Nur solange Zugriff vorhanden ist
            taWettkampfliste.setText(taWettkampfliste.getText() + tempWListe.getContent().getWettkampfkarteUebersicht() + "\n");
            tempWListe.next();
        }
    }

    private void neueKarteHinzufuegen() {
        int lauf, sprung, wurf;
        // Die 4 wichtigsten Informationen müssen vorhanden sein, sonst wird keine Karte erstellt
        if (tfName.getText().equals("") || tfVorname.getText().equals("") || tfKlasse.getText().equals("") || tfAlter.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ungültige Eingabe", "Fehlermeldung", JOptionPane.ERROR_MESSAGE); // Erstellung einer Fehlermeldung
        } else { // Wenn die 4 wichtigsten Werte vorhanden sind, wird die Eingabe der anderen Werte überprüft, um Systemfehler zu vermeiden,
            // auch wenn das Alter ein Integer ist, muss das nicht noch geprüft werden, dies schon in der ersten Abfrage überprüft wird
            if (tfLauf.getText().equals("")) {
                lauf = 0;
            } else {
                lauf = Integer.parseInt(tfLauf.getText());
            }
            if (tfSprung.getText().equals("")) {
                sprung = 0;
            } else {
                sprung = Integer.parseInt(tfSprung.getText());
            }
            if (tfWurf.getText().equals("")) {
                wurf = 0;
            } else {
                wurf = Integer.parseInt(tfWurf.getText());
            }
            // Erstellung der neuen Karte mit den Informationen
            we.karteHinzufuegen(tfName.getText(), tfVorname.getText(), tfKlasse.getText(), Integer.parseInt(tfAlter.getText()), lauf, sprung, wurf);
        }
    }

    private void configsLaden() { // Entweder werden die configs geladen oder falls das Program zum ersten Mal geöffnet wurde, wird eine config Datei erstellt
        File file = new File("config.txt");

        if (!file.exists()) { // Überprüfung, ob keine config Datei existiert. Wenn keine existiert, wird eine erstellt
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                // Erstellen der einzelnen "config-Variablen" und ihren Werten
                writer.write("default-directory=" + System.getProperty("user.home") + "\\Documents");
                writer.flush();
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        } else { // Falls eine config Datei vorhanden ist, wird sie einfach ausgelesen
            if (!file.canRead() || !file.isFile())
                System.exit(0);

            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader("config.txt"));
                String zeile;
                while ((zeile = in.readLine()) != null) {
                    configs.append(zeile);
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
    }

    private String configInhaltSuchen() {
        String configWert = ""; // Leeren String für den return erstellen

        configs.toFirst(); // Zum ersten Objekt gehen
        while (configs.hasAccess()) { // Nur solange Zugriff vorhanden ist
            if (configs.getContent().startsWith("default-directory")) { // Vergleich, ob die angegebene "config-Variable" einer in der Liste entspricht
                configWert = configs.getContent().substring("default-directory".length() + 1); // configWert auf den Wert aus der Liste setzen
                break; // Abbruch bei Fund
            }
            configs.next();
        }
        if (configWert.equals("") || configWert.contains("=")) {
            // Falls der configWert leer ist oder ein Gleichzeichen enthält, ist davon auszugehen,
            // dass entweder nichts gefunden wurde oder ein Schreibfehler vorliegt. Folgend: Erstellung einer Fehlermeldung
            JOptionPane.showMessageDialog(null, """
                    Programmfehler!\s

                    Fehler:   Gesuchte Config  'default-directory'  existiert nicht!\s

                    Programm überprüfen.""", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
        }
        return configWert;
    }

    private void configInhaltAendern(String pWert) {
        if (Objects.equals("default-directory", "") || Objects.equals(pWert, "")) { // Bei mindestens einem leeren Parameter ist davon auszugehen, dass es einen Fehler gibt
            // Erstellung einer Fehlermeldung
            JOptionPane.showMessageDialog(null, "Programmfehler! \n\nFehler:   Leerer Parameter bei Config-Suche! \n\nProgramm überprüfen", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
        } else {
            configs.toFirst(); // Zum ersten Objekt gehen
            while (configs.hasAccess()) { // Nur solange Zugriff vorhanden ist
                if (configs.getContent().startsWith("default-directory")) { // Vergleich, ob die angegebene "config-Variable" einer in der Liste entspricht
                    configs.setContent(configs.getContent().substring(0, "default-directory".length() + 1) + pWert); // Man setzt den Content auf den "config-Variablennamen" plus den neuen Wert
                    break; // Abbruch bei Fund
                }
                configs.next();
            }
            updateConfigFile(); // Änderung der Liste in die config Datei übernehmen
        }
    }

    private void updateConfigFile() {
        File file = new File("config.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            configs.toFirst();
            while (configs.hasAccess()) {
                writer.write(configs.getContent());
                writer.newLine();
                configs.next();
            }
            writer.flush();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new WettkampfkartenGUI();
    }


    // Ende Methoden
} // end of class WettkampfkartenGUI
