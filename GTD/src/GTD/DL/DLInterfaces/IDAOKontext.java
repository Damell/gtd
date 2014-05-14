package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface pro správu Kontextu v databázi.
 * @version 1.0
 */
public interface IDAOKontext {

	/**
	 * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean createKontext(Kontext kontext);

	/**
	 * Smaže kontext z databáze.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean deleteKontext(Kontext kontext);

	/**
	 * Vrátí všechny kontexty v systému.
	 * @return 
	 */
	public List getAllKontexty();

	/**
	 * Vrátí kontext podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Kontext getKontext(int id);

	/**
	 * Uloží zmenený kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean updateKontext(Kontext kontext);

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getKontextyOsoby(Osoba osoba);

}