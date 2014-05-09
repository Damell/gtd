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
            String jobquery = "begin pavlim33.API.CONTEXT_IU("
                    + "inp_id_name  => '" + kontext.getKontextNazev() + "'"
                    + "inp_id_person  =>" + DatabaseConnection.getID()
                    + "); end;";
            System.out.println(jobquery);
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
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
            System.err.println("DB query error: " + e.getMessage());
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
            ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name, id_project from pavlim33.tasks_v");
            while (rset.next()) {
                //TODO Kontext ukl = new Kontext(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(7));
                //System.out.println(ukl);
                //kontexty.add(ukl);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
        }
        return kontexty;
    }

    /**
     * Vrátí kontext podle jeho ID.
     *
     * @param id
     */
    public Kontext getKontext(int id) {
        return null;
    }

    /**
     * Uloží zmenený kontext.
     *
     * @param kontext
     */
    public boolean updateKontext(Kontext kontext) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin pavlim33.API.CONTEXT_IU("
                    + "inp_id  =>" + kontext.getKontextId()
                    + "inp_id_name  => '" + kontext.getKontextNazev() + "'"
                    + "inp_id_person  =>" + DatabaseConnection.getID()
                    + "); end;";
            System.out.println(jobquery);
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
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
        return null;
    }

}
