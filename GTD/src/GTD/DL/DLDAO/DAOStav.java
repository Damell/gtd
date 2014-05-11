package GTD.DL.DLDAO;

import GTD.DL.DLInterfaces.IDAOStav;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Trída zapouzdruje metody pro získání ID stavů objektů
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:20
 */
public class DAOStav implements IDAOStav {

    /**
     * Vrátí ID stavu: činnost Archivovaná
     *
     */
	@Override
    public int getCinnostArchivovanaID() {
        return this.getID("activities", "A");
    }

    /**
     * Vrátí ID stavu: činost Ke zpracování
     *
     */
	@Override
    public int getCinnostKeZpracovaniID() {
        return this.getID("activities", "K");
    }

    /**
     * Vrátí ID stavu: činost Zahozena
     *
     */
	@Override
    public int getCinnostZahozenaID() {
        return this.getID("activities", "H");
    }

    /**
     * Vrátí ID stavu: činost Odlozena
     *
     */
    @Override
    public int getCinnostOdlozenaID() {
        return this.getID("activities", "O");
    }

    /**
     * Vrátí ID stavu: činost Odlozena
     *
     */
    @Override
    public int getCinnostZpracovanaID() {
        return this.getID("activities", "Z");
    }

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
     */
    @Override
    public int getOsobaAktivniID() {
        return this.getID("persons", "A");
    }

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
     */
    @Override
    public int getOsobaNeaktivniID() {
        return this.getID("persons", "N");
    }

    /**
     * Vrátí ID stavu: konatakt email
     *
     */
    @Override
    public int getKontaktEmailID() {
        return this.getID("contacts", "E");
    }

    /**
     * Vrátí ID stavu: konatakt telefon
     *
     */
    @Override
    public int getKontaktTelefonID() {
        return this.getID("contacts", "T");
    }

    /**
     * Vrátí ID stavu: projekt Aktivni
     *
     */
    @Override
    public int getProjektAktivniID() {
        return this.getID("projects", "A");
    }

    /**
     * Vrátí ID stavu: projekt Dokonceny
     *
     */
    @Override
    public int getProjektDokoncenyID() {
        return this.getID("projects", "D");
    }

    /**
     * Vrátí ID stavu: ukol Vytvoreny
     *
     */
    @Override
    public int getUkolVytvorenyID() {
        return this.getID("tasks", "V");
    }

    /**
     * Vrátí ID stavu: ukol Aktivni
     *
     */
    @Override
    public int getUkolAktivniID() {
        return this.getID("tasks", "A");
    }

    /**
     * Vrátí ID stavu: ukol V kalendari
     *
     */
    @Override
    public int getUkolVKalendariID() {
        return this.getID("tasks", "K");
    }

    /**
     * Vrátí ID stavu: ukol Hotovy
     *
     */
    @Override
    public int getUkolHotovyID() {
        return this.getID("tasks", "H");
    }
    

    /**
     * Vrátí ID stavu dle tabulky a kodu stavu
     *
     */
    private int getID(String table_name, String code) {
        int pid = -1;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String jobquery = "select id from pavlim33.types where "
                    + "table_name = '" + table_name + "' "
                    + "and code = '" + code + "'";
            //System.out.println(jobquery);
            ResultSet rset = stmt.executeQuery(jobquery);
            while (rset.next()) {
                pid = rset.getInt(1);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error :: getID: " + e.getMessage());
        }
        return pid;
    }

}
