package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOOsoba;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání osob z databáze.
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class DAOOsoba implements IDAOOsoba {

    public DAOOsoba() {

    }

    /**
     * Vytvoří nového uživatele.
     *
     * @param osoba
     */
    public boolean createOsoba(Osoba osoba) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin pavlim33.API.PERSONS_IU("
                    + "inp_login  => '" + osoba.getLogin() + "'"
                    + ",inp_fname => '" + osoba.getJmeno() + "'"
                    + ",inp_sname => '" + osoba.getPrijmeni() + "'"
                    + "); end;";
            //System.out.println(jobquery);
            CallableStatement callStmt = con.prepareCall(jobquery);
            //vystupni parametry, zatim nepotrebuji
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
     *
     * @param osoba
     */
    public boolean deactivateOsoba(Osoba osoba) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin pavlim33.API.PERSONS_DEL("
                    + "inp_id  => '" + osoba.getId() + "'"
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
     * Vrátí všechny osoby.
     */
    public List<Osoba> getAllOsoby() {
        List<Osoba> osoby = new ArrayList<Osoba>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, login, fname, sname from pavlim33.persons_v");
            while (rset.next()) {
                Osoba oso = new Osoba(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4));
                osoby.add(oso);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
        }
        return osoby;
    }

    /**
     * Vrátí osobu podle jejího ID.
     *
     * @param id
     */
    public Osoba getOsoba(int id) {
        Osoba osoba = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select id, login, fname, sname from pavlim33.members_v where id_person =" + id);
            while (rset.next()) {
                osoba = new Osoba(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error");
        }
        return osoba;
    }

    /**
     * Uloží změny osoby.
     *
     * @param osoba
     */
    public boolean updateOsoba(Osoba osoba) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin pavlim33.API.PERSONS_IU("
                    + "inp_id  => " + osoba.getId()
                    + "inp_login  => '" + osoba.getLogin() + "'"
                    + ",inp_fname => '" + osoba.getJmeno() + "'"
                    + ",inp_sname => '" + osoba.getPrijmeni() + "'"
                    + "); end;";
            //System.out.println(jobquery);
            CallableStatement callStmt = con.prepareCall(jobquery);
            //vystupni parametry, zatim nepotrebuji
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
     *
     * @param login
     */
    public boolean checkNewLogin(String login) {
        int count = 0;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select id from pavlim33.members_v where login = '" + login + "'");
            while (rset.next()) {
                count++;
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error");
        }
        if (count == 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Zkontroluje přihlašovací údaje.
     *
     * @param login
     * @param heslo
     */
    public boolean checkPrihlaseni(String login, String heslo) {
        boolean authenticated = false;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt
                    .executeQuery("select login, pass_hash from persons");
            while (rset.next()) {
                if (rset.getString(1).equals(login) && String.valueOf(rset.getLong(2)).equals(heslo)) {
                    authenticated = true;
                }
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error");
        }
        return authenticated;
    }

    @Override
    public Osoba getOsoba(String username) {
        Osoba osoba = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select id, login, fname, sname from pavlim33.members_v where login = '" + username + "'");
            while (rset.next()) {
                osoba = new Osoba(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error");
        }
        return osoba;
    }

}
