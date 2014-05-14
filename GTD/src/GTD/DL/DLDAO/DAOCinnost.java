package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOCinnost;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání cinností z databáze.
 *
 * @version 1.0
 */
public class DAOCinnost implements IDAOCinnost {

	/**
	 *
	 */
	public DAOCinnost() {

    }

    /**
     * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
     *
     * @param cinnost
	 * @return 
     */
    public boolean createCinnost(Cinnost cinnost) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin pavlim33.API.ACTIVITIES_IU("
                    + "inp_id_person  =>" + cinnost.getVlastnik_id()
                    + ",inp_name => '" + cinnost.getNazev() + "'"
                    + ",inp_description => '" + cinnost.getPopis() + "'"
                    + ",inp_id_type => " + cinnost.getStav()
                    + "); end;";
            //System.out.println(jobquery);
            CallableStatement callStmt = con.prepareCall(jobquery);
            //vystupni parametry, zatim nepotrebuji
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Smaže cinnost z databáze.
     *
     * @param cinnost
	 * @return 
     */
    public boolean deleteCinnost(Cinnost cinnost) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin pavlim33.API.ACTIVITIES_DEL(inp_id  => " + cinnost.getId() + "); end;";
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Vrátí všechny cinnosti v systému.
	 * @return 
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
                //System.out.println(cin);
                cinnosti.add(cin);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return cinnosti;
    }

    /**
     * Vrátí cinnost podle jejího ID.
     *
     * @param id
	 * @return 
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
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return cinnost;
    }

    /**
     * Uloží zmenenou cinnost.
     *
     * @param cinnost
	 * @return 
     */
    public boolean updateCinnost(Cinnost cinnost) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //Statement stmt = con.createStatement();
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin pavlim33.API.ACTIVITIES_IU("
                    + "inp_id_person    =>" + cinnost.getVlastnik_id()
                    + ",inp_id =>" + cinnost.getId()
                    + ",inp_name => '" + cinnost.getNazev() + "'"
                    + ",inp_description => '" + cinnost.getPopis() + "'"
                    + ",inp_id_type => " + cinnost.getStav()
                    + "); end;";
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Vrátí všechny cinnosti patrící zadané osobe.
     *
     * @param osoba
	 * @return 
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
                //System.out.println(cin);
                cinnosti.add(cin);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return cinnosti;
    }

}
