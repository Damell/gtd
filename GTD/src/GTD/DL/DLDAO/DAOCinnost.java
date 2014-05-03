package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOCinnost;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání cinností z databáze.
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:20
 */
public class DAOCinnost implements IDAOCinnost {

    public DAOCinnost() {

    }

    /**
     * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
     *
     * @param cinnost
     */
    public boolean createCinnost(Cinnost cinnost) {
        return false;
    }

    /**
     * Smaže cinnost z databáze.
     *
     * @param cinnost
     */
    public boolean deleteCinnost(Cinnost cinnost) {
        return false;
    }

    /**
     * Vrátí všechny cinnosti v systému.
     */
    public List getAllCinnosti() {
		List<Cinnost> cinnosti = new ArrayList<Cinnost>();
		Connection con = DatabaseConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			//Podminka pro prihlasenou osobu + DatabaseConnection.getID());
			ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_person from pavlim33.activities_v");
			while (rset.next()) {
				Cinnost cin = new Cinnost(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
				System.out.println(cin);
				cinnosti.add(cin);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("DB query error");
		}
		return cinnosti;
    }

    /**
     * Vrátí cinnost podle jejího ID.
     *
     * @param id
     */
    public Cinnost getCinnost(int id) {
		Cinnost cinnost = null;
		Connection con = DatabaseConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_person from pavlim33.activities_v where id =" + id);
			while (rset.next()) {
				cinnost = new Cinnost(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("DB query error");
		}
		return cinnost;
    }

    /**
     * Uloží zmenenou cinnost.
     *
     * @param cinnost
     */
    public boolean updateCinnost(Cinnost cinnost) {
        return false;
    }

    /**
     * Vrátí všechny cinnosti patrící zadané osobe.
     *
     * @param osoba
     */
	@Override
    public List<Cinnost> getCinnostiOsoby(Osoba osoba) {
		List<Cinnost> cinnosti = new ArrayList<Cinnost>();
		Connection con = DatabaseConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_person from pavlim33.activities_v where id_person = " + osoba.getId());
			while (rset.next()) {
				Cinnost cin = new Cinnost(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
				System.out.println(cin);
				cinnosti.add(cin);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("DB query error");
		}
		return cinnosti;
    }

}
