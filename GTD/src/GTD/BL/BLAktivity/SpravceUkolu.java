package GTD.BL.BLAktivity;
import GTD.BL.BLOsoby.SpravceOsob;
import GTD.DL.DLDAO.DAOUkol;
import GTD.DL.DLInterfaces.IDAOUkol;
import GTD.DL.DLEntity.*;
import java.util.List;

/**
 * Trída pro manipulaci s úkoly.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
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
	 */
	public boolean deleteUkol(Ukol ukol){
		return false;
	}

	/**
	 * Vrátí úkol.
	 * 
	 * @param id
	 */
	public Ukol getUkol(int id){
		return null;
	}

	/**
	 * Změní stav úkolu na "hotový". Dokončit úkol může jeho vlastník nebo vlastník
	 * nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param ukol
	 */
	public boolean finishUkol(Ukol ukol){
		return DAOUkol.updateUkol(ukol);
	}

	/**
	 * Uloží změněný úkol (změna názvu/popisu). Změnit úkol může  jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param ukol
	 */
	public boolean updateUkol(Ukol ukol){
		return false;
	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 */
	public List getUkolyKontextu(Kontext kontext){
		return null;
	}

	/**
	 * Přidá úkol do kalendáře. Toto může udělat vlastník úkolu nebo vlastník
	 * nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param ukol
	 * @param interval
	 */
	public boolean scheduleUkol(Ukol ukol, Interval interval){
		return false;
	}

	/**
	 * Nastaví kontext úkolu. Toto může udělat pouze vlastník úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 */
	public boolean setKontext(Ukol ukol, Kontext kontext){
		return false;
	}

	/**
	 * Změní vlastníka úkolu. Změnit vlastníka smí pouze jeho vlastník nebo vlastník
	 * prvního nadřazeného projektu.
	 * 
	 * @param ukol
	 * @param novyVlastnik
	 */
	public boolean changeOwner(Ukol ukol, Osoba novyVlastnik){
		return false;
	}

	public List getUkolyOsoby(Osoba osoba) {
		return DAOUkol.getUkolyOsoby(osoba);
	}

}