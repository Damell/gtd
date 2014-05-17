package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOActivity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání cinností z databáze.
 *
 * @author GTD team
 * @version 1.0
 */
public class DAOActivity implements IDAOActivity {

    /**
     * Konstruktor činnosti
     */
    public DAOActivity() {

    }

    /**
     * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
     *
     * @param cinnost
     */
    public boolean createActivity(Activity cinnost) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin API.ACTIVITIES_IU("
                    + "inp_id_person  => ? "
                    + ",inp_name => ? "
                    + ",inp_description => ? "
                    + ",inp_id_type => ? "
                    + "); end;";
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.setInt(1, cinnost.getVlastnik_id());
            callStmt.setString(2, cinnost.getNazev());
            callStmt.setString(3, cinnost.getPopis());
            callStmt.setInt(4, cinnost.getStav());
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Smaže cinnost z databáze.
     *
     * @param cinnost
     */
    public boolean deleteActivity(Activity cinnost) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin API.ACTIVITIES_DEL(inp_id  => ? ); end;";
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.setInt(1, cinnost.getId());
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Vrátí všechny cinnosti v systému.
     *
     * @return List<Cinnost>
     */
    public List getAllActivities() {
        List<Activity> cinnosti = new ArrayList<Activity>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_person from activities_v");
            while (rset.next()) {
                Activity cin = new Activity(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
                cinnosti.add(cin);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return cinnosti;
    }

    /**
     * Vrátí cinnost podle jejího ID.
     *
     * @param id
     * @return cinnost
     */
    public Activity getActivity(int id) {
        Activity cinnost = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "select "
                    + "id, name, description, id_type, type_name, id_person "
                    + "from activities_v "
                    + "where id = ? ";
            PreparedStatement stmt = con.prepareStatement(jobquery);
            stmt.setInt(1, id);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cinnost = new Activity(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return cinnost;
    }

    /**
     * Uloží zmenenou cinnost.
     *
     * @param cinnost
     */
    public boolean updateActivity(Activity cinnost) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin API.ACTIVITIES_IU("
                    + "inp_id_person    => ? "
                    + ",inp_id => ? "
                    + ",inp_name => ? "
                    + ",inp_description => ? "
                    + ",inp_id_type => ? "
                    + "); end;";
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.setInt(1, cinnost.getVlastnik_id());
            callStmt.setInt(2, cinnost.getId());
            callStmt.setString(3, cinnost.getNazev());
            callStmt.setString(4, cinnost.getPopis());
            callStmt.setInt(5, cinnost.getStav());
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Vrátí všechny cinnosti patrící zadané osobe.
     *
     * @param osoba
     * @return List<Cinnost>
     */
    @Override
    public List<Activity> getActivitiesOfPerson(Person osoba) {
        List<Activity> cinnosti = new ArrayList<Activity>();
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "select "
                    + "id, name, description, id_type, type_name, id_person "
                    + "from activities_v "
                    + "where id_person = ? "; //+ osoba.getId());
            PreparedStatement stmt = con.prepareStatement(jobquery);
            stmt.setInt(1, osoba.getId());
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                Activity cin = new Activity(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));
                cinnosti.add(cin);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return cinnosti;
    }

}
