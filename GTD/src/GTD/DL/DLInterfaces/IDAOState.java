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
    public int getCinnostArchivovanaID();

    /**
     * Vrátí ID stavu: činost Ke zpracování
     *
	 * @return 
     */
    public int getCinnostKeZpracovaniID();

    /**
     * Vrátí ID stavu: činost Zahozena
     *
	 * @return 
     */
    public int getCinnostZahozenaID();

    /**
     * Vrátí ID stavu: činost Odlozena
     *
	 * @return 
     */
    public int getCinnostOdlozenaID();

    /**
     * Vrátí ID stavu: činost Odlozena
     *
	 * @return 
     */
    public int getCinnostZpracovanaID();

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
	 * @return 
     */
    public int getOsobaAktivniID();

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
	 * @return 
     */
    public int getOsobaNeaktivniID();

    /**
     * Vrátí ID stavu: konatakt email
     *
	 * @return 
     */
    public int getKontaktEmailID();

    /**
     * Vrátí ID stavu: konatakt telefon
     *
	 * @return 
     */
    public int getKontaktTelefonID();

    /**
     * Vrátí ID stavu: projekt Aktivni
     *
	 * @return 
     */
    public int getProjektAktivniID();

    /**
     * Vrátí ID stavu: projekt Dokonceny
     *
	 * @return 
     */
    public int getProjektDokoncenyID();

    /**
     * Vrátí ID stavu: úkol vytvořený
     *
	 * @return 
     */
    public int getUkolVytvorenyID();

    /**
     * Vrátí ID stavu: úkol aktivní
     *
	 * @return 
     */
    public int getUkolAktivniID();

    /**
     * Vrátí ID stavu: úkol v kalendáři
     *
	 * @return 
     */
    public int getUkolVKalendariID();

    /**
     * Vrátí ID stavu: úkol hotový
     *
	 * @return 
     */
    public int getUkolHotovyID();
}
