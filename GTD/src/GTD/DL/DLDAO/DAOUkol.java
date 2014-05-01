package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Ukol;
import GTD.DL.DLInterfaces.IDAOUkol;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání úkolu z databáze.
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class DAOUkol implements IDAOUkol {

    private Connection con;

    public DAOUkol() {
        con = DatabaseConnection.getConnection();

    }

    /**
     * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
     *
     * @param ukol
     */
    public boolean createUkol(Ukol ukol) {
        return false;
    }

    /**
     * Smaže úkol z databáze (resp. označí jako smazaný).
     *
     * @param ukol
     */
    public boolean deleteUkol(Ukol ukol) {
        return false;
    }

    /**
     * Vrátí všechny úkoly v systému.
     */
    public List getAllUkoly() {
        List<Ukol> ukoly = new ArrayList<Ukol>();
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
                ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name from pavlim33.tasks_v");
                while (rset.next()) {
                    Ukol ukl = new Ukol(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
                    //nastav interval
                    ukl.setInterval(rset.getDate(7), rset.getDate(8));
                    //nastav kontext ukolu vlastnika// zobrazit jen vlastnikovy?
                    ukl.setKontext(rset.getInt(9), rset.getString(10));
                    System.out.println(ukl);
                    ukoly.add(ukl);
                }
                rset.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("DB query error");
            }
        }
        return ukoly;
    }

    /**
     * Vrátí úkol podle jeho ID.
     *
     * @param id
     */
    public Ukol getUkol(int id) {
        Ukol ukol = null;
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
                ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name from pavlim33.tasks_v where id = " + id);
                while (rset.next()) {
                    ukol = new Ukol(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
                    //nastav interval
                    ukol.setInterval(rset.getDate(7), rset.getDate(8));
                    //nastav kontext ukolu vlastnika// zobrazit jen vlastnikovy?
                    ukol.setKontext(rset.getInt(9), rset.getString(10));
                }
                rset.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("DB query error");
            }
        }
        return ukol;
    }

    /**
     * Uloží zmenený úkol.
     *
     * @param ukol
     */
    public boolean updateUkol(Ukol ukol) {
        return false;
    }

    /**
     * Vrátí všechny úkoly daného kontextu.
     *
     * @param kontext
     */
    public List<Ukol> getUkolyKontextu(Kontext kontext) {
        List<Ukol> ukoly = new ArrayList<Ukol>();
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
                ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name from pavlim33.tasks_v where id_context="+ kontext.getKontextId());
                while (rset.next()) {
                    Ukol ukl = new Ukol(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
                    //nastav interval
                    ukl.setInterval(rset.getDate(7), rset.getDate(8));
                    //nastav kontext ukolu vlastnika// zobrazit jen vlastnikovy?
                    ukl.setKontext(rset.getInt(9), rset.getString(10));
                    System.out.println(ukl);
                    ukoly.add(ukl);
                }
                rset.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("DB query error");
            }
        }
        return ukoly;
    }

}
