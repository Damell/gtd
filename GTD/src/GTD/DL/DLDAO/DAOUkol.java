package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Ukol;
import GTD.DL.DLInterfaces.IDAOUkol;
import java.sql.CallableStatement;
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

    public DAOUkol() {

    }

    /**
     * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
     *
     * @param ukol
     */
    public boolean createUkol(Ukol ukol) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin pavlim33.API.TASKS_IU("
                    + "inp_id_owner  =>" + ukol.getVlastnik_id()
                    + "inp_id_creator  =>" + ukol.getVlastnik_id()
                    + ",inp_name => '" + ukol.getNazev() + "'"
                    + ",inp_description => '" + ukol.getPopis() + "'"
                    + ",inp_id_project => " + ukol.getProjekt()
                    + ",inp_id_type => " + ukol.getStav()
                    + "); end;";
            //System.out.println(jobquery);
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
     * Smaže úkol z databáze (resp. označí jako smazaný).
     *
     * @param ukol
     */
    public boolean deleteUkol(Ukol ukol) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin pavlim33.API.TASKS_DEL(inp_id  => " + ukol.getId() + "); end;";
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
     * Vrátí všechny úkoly v systému.
     */
    public List getAllUkoly() {
        List<Ukol> ukoly = new ArrayList<Ukol>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name, id_project from pavlim33.tasks_v");
            while (rset.next()) {
                Ukol ukl = new Ukol(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(11));
                //nastav interval
                ukl.setInterval(rset.getDate(7), rset.getDate(8));
                //nastav kontext ukolu vlastnika// zobrazit jen vlastnikovy?
                ukl.setKontext(rset.getInt(9), rset.getString(10));
                //System.out.println(ukl);
                ukoly.add(ukl);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
        }
        return ukoly;
    }

    /**
     * Vrátí úkoly osoby.
     */
    public List getUkolyOsoby(Osoba osoba) {
        List<Ukol> ukoly = new ArrayList<Ukol>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select "
                    + "id, name, description, id_type, type_name, id_owner, "
                    + "date_from, date_to, id_context, context_name, "
                    + "id_project, project_name, project_description "
                    + "from pavlim33.tasks_v "
                    + "where id_owner = " + osoba.getId());
            while (rset.next()) {
                Ukol ukl = new Ukol(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(11));
                //nastav interval
                ukl.setInterval(rset.getDate(7), rset.getDate(8));
                //nastav kontext ukolu vlastnika// zobrazit jen vlastnikovy?
                ukl.setKontext(rset.getInt(9), rset.getString(10));
                //nastav popis a nazev projektu
                ukl.setProjekt(rset.getInt(11), rset.getString(12), rset.getString(13));
                //System.out.println(ukl);
                ukoly.add(ukl);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
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
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name, id_project from pavlim33.tasks_v where id = " + id);
            while (rset.next()) {
                ukol = new Ukol(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(7));
                //nastav interval
                ukol.setInterval(rset.getDate(7), rset.getDate(8));
                //nastav kontext ukolu vlastnika// zobrazit jen vlastnikovy?
                ukol.setKontext(rset.getInt(9), rset.getString(10));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
        }
        return ukol;
    }

    /**
     * Uloží zmenený úkol.
     *
     * @param ukol
     */
    public boolean updateUkol(Ukol ukol) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin pavlim33.API.TASKS_IU("
                    + "inp_id_owner  =>" + ukol.getVlastnik_id()
                    + "inp_id  =>" + ukol.getId()
                    + ",inp_name => '" + ukol.getNazev() + "'"
                    + ",inp_description => '" + ukol.getPopis() + "'"
                    + ",inp_id_project => " + ukol.getProjekt()
                    + ",inp_id_type => " + ukol.getStav()
                    + "); end;";
            //System.out.println(jobquery);
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
     * Vrátí všechny úkoly daného kontextu.
     *
     * @param kontext
     */
    public List<Ukol> getUkolyKontextu(Kontext kontext) {
        List<Ukol> ukoly = new ArrayList<Ukol>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name, id_project from pavlim33.tasks_v where id_context=" + kontext.getKontextId());
            while (rset.next()) {
                Ukol ukl = new Ukol(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(7));
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
            System.err.println("DB query error: " + e.getMessage());
        }
        return ukoly;
    }

}
