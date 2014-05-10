package GTD.DL.DLDAO;

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
public class DAOTypy {

    /**
     * Vrátí ID stavu: činnost Archivovaná
     *
     */
    public int getCinnostArchivovanaID() {
        return this.getID("activities", "A");
    }

    /**
     * Vrátí ID stavu: činost Ke zpracování
     *
     */
    public int getCinnostKeZpracovaniID() {
        return 48;//this.getID("activities", "K");
    }
 
        /**
     * Vrátí ID stavu: činost Zahozena
     *
     */
    public int getCinnostKeZahozenaID() {
        return this.getID("activities", "Z");
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
                    + "table_name = '" + table_name + "'"
                    + "code = '" + code + "'";
            System.out.println(jobquery);
            ResultSet rset = stmt.executeQuery(jobquery);
            while (rset.next()) {
                pid = rset.getInt(1);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB query error: " + e.getMessage());
        }
        return pid;
    }

}
