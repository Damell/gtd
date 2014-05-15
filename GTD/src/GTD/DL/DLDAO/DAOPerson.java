package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOPerson;
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
 * @version 1.0
 */
public class DAOPerson implements IDAOPerson {

    /**
     * Konstruktor osoby
     */
    public DAOPerson() {

    }

    /**
     * Vytvoří nového uživatele.
     *
     * @param osoba
     * @return
     */
    public boolean createOsoba(Person osoba) {
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
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
     *
     * @param osoba
     * @return
     */
    public boolean deactivateOsoba(Person osoba) {
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
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Vrátí všechny osoby.
     *
     * @return List<Osoba>
     */
    public List<Person> getAllOsoby() {
        List<Person> osoby = new ArrayList<Person>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, login, fname, sname from pavlim33.persons_v");
            while (rset.next()) {
                Person oso = new Person(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4));
                osoby.add(oso);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return osoby;
    }

    /**
     * Vrátí osobu podle jejího ID.
     *
     * @param id
     * @return osoba
     */
    public Person getOsoba(int id) {
        Person osoba = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select id, login, fname, sname from pavlim33.persons_v where id =" + id);
            while (rset.next()) {
                osoba = new Person(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return osoba;
    }

    /**
     * Vrátí ID prihlasene ososby.
     *
     * @return id
     */
    public int getOsobaID() {
        int id = 0;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT id from pavlim33.persons where upper(login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')");
            while (rset.next()) {
                id = rset.getInt(1);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return id;
    }

    /**
     * Uloží změny osoby.
     *
     * @param osoba
     * @return
     */
    public boolean updateOsoba(Person osoba) {
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
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
     *
     * @param login
     * @return
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
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        if (count == 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Zkontroluje přihlašovací údaje. Zda je ucet povolen.
     *
     * @param login
     * @return true=povolen, false=nepovolen
     */
    public boolean checkPrihlaseni(String login) {
        boolean authenticated = false;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt
                    .executeQuery("select login, pass_hash from pavlim33.persons_v where login ='" + login + "'");
            while (rset.next()) {
                authenticated = true;
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return authenticated;
    }

    /**
     * Vráti osoby dle zadaného loginu
     *
     * @param login
     * @return osoba
     */
    @Override
    public Person getOsoba(String username) {
        Person osoba = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select id, login, fname, sname from pavlim33.members_v where login = '" + username + "'");
            while (rset.next()) {
                osoba = new Person(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return osoba;
    }

}
