package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOKontext;
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
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:20
 */
public class DAOKontext implements IDAOKontext {

    public DAOKontext() {

    }

    /**
     * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
     *
     * @param kontext
     */
    public boolean createKontext(Kontext kontext) {
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
     */
    public boolean deleteKontext(Kontext kontext) {
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
     */
    public List getAllKontexty() {
        List<Kontext> kontexty = new ArrayList<Kontext>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name from pavlim33.contexts");
            while (rset.next()) {
                Kontext kon = new Kontext(rset.getInt(1), rset.getString(2));
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
     */
    public Kontext getKontext(int id) {
      Kontext kontext = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select id, name from pavlim33.contexts where "
                    + "id = " + id);
            while (rset.next()) {
                kontext = new Kontext(rset.getInt(1), rset.getString(2));
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
     */
    public boolean updateKontext(Kontext kontext) {
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
     */
    public List getKontextyOsoby(Osoba osoba) {
        List<Kontext> kontexty = new ArrayList<Kontext>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name from pavlim33.contexts where "
                    + "id_person = " + osoba.getId());
            while (rset.next()) {
                Kontext kon = new Kontext(rset.getInt(1), rset.getString(2));
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
