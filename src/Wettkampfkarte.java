public class Wettkampfkarte {

    private final String name;
    private final String vorname;
    private final String klasse;
    private final int alter;
    private int lauf;
    private int sprung;
    private int wurf;

    public Wettkampfkarte(String pName, String pVorname, String pKlasse, int pAlter, int pLauf, int pSprung, int pWurf) {
        name = pName;
        vorname = pVorname;
        klasse = pKlasse;
        alter = pAlter;
        lauf = pLauf;
        sprung = pSprung;
        wurf = pWurf;
    }

    public Wettkampfkarte(String pName, String pVorname, String pKlasse, int pAlter) {
        name = pName;
        vorname = pVorname;
        klasse = pKlasse;
        alter = pAlter;
        lauf = 0;
        sprung = 0;
        wurf = 0;
    }

    public String getWettkampfkarteUebersicht() {
        return vorname + " " + name + ", " + klasse + ", " + alter + " Jahre, Lauf: " + lauf + ", Sprung: " + sprung + ", Wurf: " + wurf;
    }

    @Override
    public String toString() {
        return "Wettkampfkarte [name=" + name + ", vorname=" + vorname + ", klasse=" + klasse + ", alter=" + alter
                + ", lauf=" + lauf + ", sprung=" + sprung + ", wurf=" + wurf + "]";
    }

    public String kartenInfoFuerDatei() {
        return name + "," + vorname + "," + klasse + "," + alter + "," + lauf + "," + sprung + "," + wurf;
    }

    public void setPunkte(int pDisziplin, int pPunkte) {
        if (pDisziplin == 1) {
            lauf = pPunkte;
        } else if (pDisziplin == 2) {
            sprung = pPunkte;
        } else if (pDisziplin == 3) {
            wurf = pPunkte;
        }
    }

    public int getPunkte(int pDisziplin) {
        if (pDisziplin == 1) {
            return this.lauf;
        } else if (pDisziplin == 2) {
            return this.sprung;
        } else if (pDisziplin == 3) {
            return this.wurf;
        }
        return -1;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getKlasse() {
        return klasse;
    }

    public int getAlter() {
        return alter;
    }

}