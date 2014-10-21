package GTD.DL.DLDAO;

import GTD.DL.DLInterfaces.IDAOState;

/**
 * Trída zapouzdruje metody pro získání ID stavů objektů
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:52
 */
public class DAOState implements IDAOState {

	public DAOState(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Vrátí ID stavu: činnost Archivovaná
	 * @return id
	 */
	@Override
	public int getCinnostArchivovanaID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: činost Ke zpracování
	 * @return  id
	 */
	@Override
	public int getCinnostKeZpracovaniID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: činost Odlozena
	 * @return  id
	 */
	@Override
	public int getCinnostOdlozenaID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: činost Zahozena
	 * @return  id
	 */
	@Override
	public int getCinnostZahozenaID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: činost Odlozena
	 * @return  id
	 */
	@Override
	public int getCinnostZpracovanaID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu dle tabulky a kodu stavu
	 * @param název tabulky (tasks,project,..)
	 * @param kód stavu
	 * 
	 * @param table_name
	 * @param code
	 */
	private int getID(String table_name, String code){
		return 0;
	}

	/**
	 * Vrátí ID stavu: konatakt email
	 * @return  id
	 */
	@Override
	public int getKontaktEmailID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: konatakt telefon
	 * @return  id
	 */
	@Override
	public int getKontaktTelefonID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: osoby Aktivni
	 * @return  id
	 */
	@Override
	public int getOsobaAktivniID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: osoby Aktivni
	 * @return  id
	 */
	@Override
	public int getOsobaNeaktivniID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: projekt Aktivni
	 * @return  id
	 */
	@Override
	public int getProjektAktivniID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: projekt Dokonceny
	 * @return  id
	 */
	@Override
	public int getProjektDokoncenyID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: ukol Aktivni
	 * @return  id
	 */
	@Override
	public int getUkolAktivniID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: ukol Hotovy
	 * @return  id
	 */
	@Override
	public int getUkolHotovyID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: ukol V kalendari
	 * @return  id
	 */
	@Override
	public int getUkolVKalendariID(){
		return 0;
	}

	/**
	 * Vrátí ID stavu: ukol Vytvoreny
	 * @return  id
	 */
	@Override
	public int getUkolVytvorenyID(){
		return 0;
	}

}