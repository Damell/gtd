package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface pro správu Kontextu v databázi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IDAOKontext {

	/**
	 * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param kontext
	 */
	public boolean createKontext(Kontext kontext);

	/**
	 * Smaže kontext z databáze.
	 * 
	 * @param kontext
	 */
	public boolean deleteKontext(Kontext kontext);

	/**
	 * Vrátí všechny kontexty v systému.
	 */
	public List getAllKontexty();

	/**
	 * Vrátí kontext podle jeho ID.
	 * 
	 * @param id
	 */
	public Kontext getKontext(int id);

	/**
	 * Uloží zmenený kontext.
	 * 
	 * @param kontext
	 */
	public boolean updateKontext(Kontext kontext);

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * 
	 * @param osoba
	 */
	public List getKontextyOsoby(Osoba osoba);

}