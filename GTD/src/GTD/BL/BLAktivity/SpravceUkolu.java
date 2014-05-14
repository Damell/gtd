package GTD.BL.BLAktivity;
import GTD.BL.BLOsoby.SpravceOsob;
import GTD.DL.DLDAO.DAOUkol;
import GTD.DL.DLInterfaces.IDAOUkol;
import GTD.DL.DLEntity.*;
import java.util.List;

/**
 * Trída pro manipulaci s úkoly.
 * @version 1.0
 */
public class SpravceUkolu {

	private IDAOUkol DAOUkol;
	/**
	 * Odkaz na SpravceCinnosti - při přidání úkolu vzniklého z činnosti je třeba tuto
	 * činnost označit jako "zpracovanou" - to zařídí právě SpravceCinnosti.
	 */
	private SpravceCinnosti spravceCinnosti;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private SpravceOsob spravceOsob;

	/**
	 *
	 */
	public SpravceUkolu(){
		spravceCinnosti = new SpravceCinnosti();
		DAOUkol = new DAOUkol();
	}

	/**
	 * Vytvoří nový úkol. Úkol v projektu může vytvořit pouze vlastník tohoto projektu.
	 * 
	 * 
	 * @param ukol
	 * @param cinnost    Činnost, ze které úkol vznikl (pokud existuje) - používá se
	 * pro označení činnosti jako "zpracované".
	 * @return 
	 */
	public boolean addUkol(Ukol ukol, Cinnost cinnost){
		if (DAOUkol.createUkol(ukol)) {
			spravceCinnosti.deleteCinnost(cinnost);
			return true;
		}
		return false;
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný). Mazat úkol může pouze jeho vlastník a
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean deleteUkol(Ukol ukol){
		return DAOUkol.deleteUkol(ukol);
	}

	/**
	 * Vrátí úkol.
	 * 
	 * @param id
	 * @return 
	 */
	public Ukol getUkol(int id){
		return DAOUkol.getUkol(id);
	}

	/**
	 * Uloží změněný úkol (změna názvu/popisu). Změnit úkol může  jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean updateUkol(Ukol ukol){
		return DAOUkol.updateUkol(ukol);
	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public List getUkolyKontextu(Kontext kontext){
		return DAOUkol.getUkolyKontextu(kontext);
	}

	/**
	 * Vrátí všechny úkoly
	 * @return 
	 */
	public List getAllUkoly(){
		return DAOUkol.getAllUkoly();
	}

	/**
	 * Nastaví kontext úkolu. Toto může udělat pouze vlastník úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 * @return 
	 */
	public boolean setKontext(Ukol ukol, Kontext kontext){
		return false;
	}

	/**
	 *
	 * @param osoba
	 * @return
	 */
	public List getUkolyOsoby(Osoba osoba) {
		return DAOUkol.getUkolyOsoby(osoba);
	}

}