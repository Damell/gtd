package GTD.DL.DLInterfaces;

/**
 * Interface pro získání ID typů
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IDAOTypy {

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
    public int getCinnostKeZahozenaID();

}
