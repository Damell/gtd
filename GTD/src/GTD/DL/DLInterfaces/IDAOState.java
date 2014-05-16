package GTD.DL.DLInterfaces;

/**
 * Interface pro získání ID typů
 *
 * @author GTD team
 * @version 1.0
 */
public interface IDAOState {

    /**
     * Vrátí ID stavu: činnost Archivovaná
     *
     */
    public int getActivityArchivedID();

    /**
     * Vrátí ID stavu: činost Ke zpracování
     *
     */
    public int getActivityForProcessingID();

    /**
     * Vrátí ID stavu: činost Zahozena
     *
     */
    public int getActivityDroppedID();

    /**
     * Vrátí ID stavu: činost Odlozena
     *
     */
    public int getActivityPostponedID();

    /**
     * Vrátí ID stavu: činost Odlozena
     *
     */
    public int getActivityProcessedID();

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
     */
    public int getPersonActiveID();

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
     */
    public int getPersonNotActiveID();

    /**
     * Vrátí ID stavu: konatakt email
     *
     */
    public int getContactEmailID();

    /**
     * Vrátí ID stavu: konatakt telefon
     *
     */
    public int getContactPhoneID();

    /**
     * Vrátí ID stavu: projekt Aktivni
     *
     */
    public int getProjectActiveID();

    /**
     * Vrátí ID stavu: projekt Dokonceny
     *
     */
    public int getProjectFinishedID();

    /**
     * Vrátí ID stavu: úkol vytvořený
     *
     */
    public int getTaskCreatedID();

    /**
     * Vrátí ID stavu: úkol aktivní
     *
     */
    public int getTaskActiveID();

    /**
     * Vrátí ID stavu: úkol v kalendáři
     *
     */
    public int getTaskPlannedID();

    /**
     * Vrátí ID stavu: úkol hotový
     *
     */
    public int getTaskFinishedID();
}
