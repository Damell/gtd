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
