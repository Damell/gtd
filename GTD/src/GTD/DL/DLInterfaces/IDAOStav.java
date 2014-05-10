package GTD.DL.DLInterfaces;

/**
 * Interface pro získání ID typů
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IDAOStav {

    /**
     * Vrátí ID stavu: činnost Archivovaná
     *
     */
    public int getCinnostArchivovanaID();

    /**
     * Vrátí ID stavu: činost Ke zpracování
     *
     */
    public int getCinnostKeZpracovaniID();

    /**
     * Vrátí ID stavu: činost Zahozena
     *
     */
    public int getCinnostZahozenaID();

    /**
     * Vrátí ID stavu: činost Odlozena
     *
     */
    public int getCinnostOdlozenaID();

    /**
     * Vrátí ID stavu: činost Odlozena
     *
     */
    public int getCinnostZpracovanaID();

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
     */
    public int getOsobaAktivniID();

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
     */
    public int getOsobaNeaktivniID();

    /**
     * Vrátí ID stavu: konatakt email
     *
     */
    public int getKontaktEmailID();

    /**
     * Vrátí ID stavu: konatakt telefon
     *
     */
    public int getKontaktTelefonID();

    /**
     * Vrátí ID stavu: projekt Aktivni
     *
     */
    public int getProjektAktivniID();

    /**
     * Vrátí ID stavu: projekt Dokonceny
     *
     */
    public int getProjektDokoncenyID();

    /**
     * Vrátí ID stavu: úkol vytvořený
     *
     */
    public int getUkolVytvorenyID();

    /**
     * Vrátí ID stavu: úkol aktivní
     *
     */
    public int getUkolAktivniID();

    /**
     * Vrátí ID stavu: úkol v kalendáři
     *
     */
    public int getUkolVKalendariID();

    /**
     * Vrátí ID stavu: úkol hotový
     *
     */
    public int getUkolHotovyID();
}
