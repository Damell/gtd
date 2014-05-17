package GTD.DL.DLDAO;

import GTD.DL.DLInterfaces.IDAOState;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Trída zapouzdruje metody pro získání ID stavů objektů
 *
 * @author GTD team
 * @version 1.0
 */
public class DAOState implements IDAOState {

    /**
     * Vrátí ID stavu: činnost Archivovaná
     *
     * @return id
     */
    @Override
    public int getActivityArchivedID() {
        return this.getID("activities", "A");
    }

    /**
     * Vrátí ID stavu: činost Ke zpracování
     *
     * @return id
     */
    @Override
    public int getActivityForProcessingID() {
        return this.getID("activities", "K");
    }

    /**
     * Vrátí ID stavu: činost Zahozena
     *
     * @return id
     */
    @Override
    public int getActivityDroppedID() {
        return this.getID("activities", "H");
    }

    /**
     * Vrátí ID stavu: činost Odlozena
     *
     * @return id
     */
    @Override
    public int getActivityPostponedID() {
        return this.getID("activities", "O");
    }

    /**
     * Vrátí ID stavu: činost Odlozena
     *
     * @return id
     */
    @Override
    public int getActivityProcessedID() {
        return this.getID("activities", "Z");
    }

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
     * @return id
     */
    @Override
    public int getPersonActiveID() {
        return this.getID("persons", "A");
    }

    /**
     * Vrátí ID stavu: osoby Aktivni
     *
     * @return id
     */
    @Override
    public int getPersonNotActiveID() {
        return this.getID("persons", "N");
    }

    /**
     * Vrátí ID stavu: konatakt email
     *
     * @return id
     */
    @Override
    public int getContactEmailID() {
        return this.getID("contacts", "E");
    }

    /**
     * Vrátí ID stavu: konatakt telefon
     *
     * @return id
     */
    @Override
    public int getContactPhoneID() {
        return this.getID("contacts", "T");
    }

    /**
     * Vrátí ID stavu: projekt Aktivni
     *
     * @return id
     */
    @Override
    public int getProjectActiveID() {
        return this.getID("projects", "A");
    }

    /**
     * Vrátí ID stavu: projekt Dokonceny
     *
     * @return id
     */
    @Override
    public int getProjectFinishedID() {
        return this.getID("projects", "D");
    }

    /**
     * Vrátí ID stavu: ukol Vytvoreny
     *
     * @return id
     */
    @Override
    public int getTaskCreatedID() {
        return this.getID("tasks", "V");
    }

    /**
     * Vrátí ID stavu: ukol Aktivni
     *
     * @return id
     */
    @Override
    public int getTaskActiveID() {
        return this.getID("tasks", "A");
    }

    /**
     * Vrátí ID stavu: ukol V kalendari
     *
     * @return id
     */
    @Override
    public int getTaskPlannedID() {
        return this.getID("tasks", "K");
    }

    /**
     * Vrátí ID stavu: ukol Hotovy
     *
     * @return id
     */
    @Override
    public int getTaskFinishedID() {
        return this.getID("tasks", "H");
    }

    /**
     * Vrátí ID stavu dle tabulky a kodu stavu
     *
     * @param název tabulky (tasks,project,..)
     * @param kód stavu
     *
     */
    private int getID(String table_name, String code) {
        int pid = -1;
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "select id from types "
                    + "where "
                    + "table_name = ? "
                    + "and code = ? ";
            PreparedStatement stmt = con.prepareStatement(jobquery);
            stmt.setString(1, table_name);
            stmt.setString(2, code);
            ResultSet rset = stmt.executeQuery();
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
