package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 *
 * @author GTD team
 * @version 1.0
 */
public class DAOContext implements IDAOContext {

	/**
	 * Kontruktor kontextu
	 */
	public DAOContext() {

    }

    /**
     * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
     *
     * @param kontext
	 * @return 
     */
    public boolean createKontext(Context kontext) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin pavlim33.API.CONTEXTS_IU("
                    + "inp_name  => '" + kontext.getKontextNazev() + "'"
                    + "inp_id_person  =>" + DatabaseConnection.getID()
                    + "); end;";
            System.out.println(jobquery);
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
     * Smaže kontext z databáze.
     *
     * @param kontext
	 * @return 
     */
    public boolean deleteKontext(Context kontext) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin pavlim33.API.CONTEXTS_DEL(inp_id  => " + kontext.getKontextId() + "); end;";
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
     * Vrátí všechny kontexty v systému.
	 * @return List<Kontext>
     */
    public List getAllKontexty() {
        List<Context> kontexty = new ArrayList<Context>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name from pavlim33.contexts");
            while (rset.next()) {
                Context kon = new Context(rset.getInt(1), rset.getString(2));
                //System.out.println(ukl);
                kontexty.add(kon);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return kontexty;
    }

    /**
     * Vrátí kontext podle jeho ID.
     *
     * @param id
	 * @return kontext
     */
    public Context getKontext(int id) {
      Context kontext = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select id, name from pavlim33.contexts where "
                    + "id = " + id);
            while (rset.next()) {
                kontext = new Context(rset.getInt(1), rset.getString(2));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return kontext;
    }

    /**
     * Uloží zmenený kontext.
     *
     * @param kontext
	 * @return 
     */
    public boolean updateKontext(Context kontext) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin pavlim33.API.CONTEXTS_IU("
                    + "inp_id  =>" + kontext.getKontextId()
                    + "inp_id_name  => '" + kontext.getKontextNazev() + "'"
                    + "inp_id_person  =>" + DatabaseConnection.getID()
                    + "); end;";
            System.out.println(jobquery);
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
     * Vrátí všechny kontexty patrící zadané osobe.
     *
     * @param osoba
	 * @return List<Kontext> 
     */
    public List getKontextyOsoby(Person osoba) {
        List<Context> kontexty = new ArrayList<Context>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name from pavlim33.contexts where "
                    + "id_person = " + osoba.getId());
            while (rset.next()) {
                Context kon = new Context(rset.getInt(1), rset.getString(2));
                //System.out.println(ukl);
                kontexty.add(kon);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return kontexty;
    }

}
