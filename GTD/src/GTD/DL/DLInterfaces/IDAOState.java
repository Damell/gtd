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
	 * @return 
     */
    public int getActivityArchivedID();

    /**
     * Vrátí ID stavu: činost Ke zpracování
     *
	 * @return 
     */
    public int getActivityForProcessingID();

    /**
     * Vrátí ID stavu: činost Zahozena
     *
	 * @return 
     */
    public int getActivityDroppedID();

    /**
     * Vrátí ID stavu: činost Odlozena
     *
	 * @return 
     */
    public int getActivityPostponedID();

    /**
     * Vrátí ID stavu: činost Odlozena
     *
	 * @return 
     */
    public int getActivityProcessedID();

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
	 * @return 
     */
    public int getPersonActiveID();

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
	 * @return 
     */
    public int getPersonNotActiveID();

    /**
     * Vrátí ID stavu: konatakt email
     *
	 * @return 
     */
    public int getContactEmailID();

    /**
     * Vrátí ID stavu: konatakt telefon
     *
	 * @return 
     */
    public int getContactPhoneID();

    /**
     * Vrátí ID stavu: projekt Aktivni
     *
	 * @return 
     */
    public int getProjectActiveID();

    /**
     * Vrátí ID stavu: projekt Dokonceny
     *
	 * @return 
     */
    public int getProjectFinishedID();

    /**
     * Vrátí ID stavu: úkol vytvořený
     *
	 * @return 
     */
    public int getTaskCreatedID();

    /**
     * Vrátí ID stavu: úkol aktivní
     *
	 * @return 
     */
    public int getTaskActiveID();

    /**
     * Vrátí ID stavu: úkol v kalendáři
     *
	 * @return 
     */
    public int getTaskPlannedID();

    /**
     * Vrátí ID stavu: úkol hotový
     *
	 * @return 
     */
    public int getTaskFinishedID();
}
