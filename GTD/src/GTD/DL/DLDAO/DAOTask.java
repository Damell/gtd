package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLInterfaces.IDAOTask;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání úkolu z databáze.
 *
 * @author GTD team
 * @version 1.0
 */
public class DAOTask implements IDAOTask {

    /**
     * Konstruktor úkolu
     */
    public DAOTask() {

    }

    /**
     * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
     *
     * @param ukol
     */
    public boolean createTask(Task ukol) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String jobquery = "begin API.TASKS_IU("
                    + "inp_id_owner  => ? "
                    + ",inp_id_creator  => ? "
                    + ",inp_name => ? "
                    + ",inp_description => ? "
                    + ",inp_id_project => ? "
                    + ",inp_id_type => ? "
                    + "); end;";
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.setInt(1, ukol.getVlastnik_id());
            callStmt.setInt(2, ukol.getTvurce());
            callStmt.setString(3, ukol.getNazev());
            callStmt.setString(4, ukol.getPopis());
            callStmt.setInt(5, ukol.getProjekt());
            callStmt.setInt(6, ukol.getStav());
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Smaže úkol z databáze (resp. označí jako smazaný).
     *
     * @param ukol
     */
    public boolean deleteTask(Task ukol) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin API.TASKS_DEL(inp_id  => ? ); end;";
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.setInt(1, ukol.getId());
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 20021) {
                DatabaseConnection.showError("Chyba: " + e.getMessage().substring(10, 100).trim());
            } else {
                DatabaseConnection.showError("DB query error: " + e.getMessage());
            }
            return false;
        }
        return true;
    }

    /**
     * Vrátí všechny úkoly v systému.
     *
     * @return List<Ukol>
     */
    public List getAllTasks() {
        List<Task> ukoly = new ArrayList<Task>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select "
                    + "id, name, description, id_type, type_name, "
                    + "id_owner, date_from, date_to, id_context, context_name, id_project "
                    + "from tasks_v");
            while (rset.next()) {
                Task ukl = new Task(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(11));
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
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return ukoly;
    }

    /**
     * Vrátí úkoly osoby.
     *
     * @param osoba
     * @return List<Ukol>
     */
    public List getTasksOfPerson(Person osoba) {
        List<Task> ukoly = new ArrayList<Task>();
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "select "
                    + "id, name, description, id_type, type_name, id_owner, "
                    + "date_from, date_to, id_context, context_name, "
                    + "id_project, project_name, project_description "
                    + "from tasks_v "
                    + "where id_owner = ? ";
            PreparedStatement prepStmt = con.prepareStatement(jobquery);
            prepStmt.setInt(1, osoba.getId());
            ResultSet rset = prepStmt.executeQuery();

            while (rset.next()) {
                Task ukl = new Task(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(11));
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
            prepStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return ukoly;
    }

    /**
     * Vrátí úkol podle jeho ID.
     *
     * @param id
     * @return ukol
     */
    public Task getTask(int id) {
        Task ukol = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "select id, name, description, id_type, type_name, id_owner, "
                    + "date_from, date_to, id_context, context_name, id_project "
                    + "from tasks_v "
                    + "where id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(jobquery);
            prepStmt.setInt(1, id);
            ResultSet rset = prepStmt.executeQuery();

            while (rset.next()) {
                ukol = new Task(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(7));
                //nastav interval
                ukol.setInterval(rset.getDate(7), rset.getDate(8));
                //nastav kontext ukolu vlastnika// zobrazit jen vlastnikovy?
                ukol.setKontext(rset.getInt(9), rset.getString(10));
            }
            rset.close();
            prepStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return ukol;
    }

    /**
     * Uloží zmenený úkol.
     *
     * @param ukol
     */
    public boolean updateTask(Task ukol) {
        String date_from = null;
        String date_to = null;
        if (ukol.getKalendar().isSet()) {
            date_from = new SimpleDateFormat("dd.MM.yyyy").format(ukol.getKalendar().getFrom());
            date_to = new SimpleDateFormat("dd.MM.yyyy").format(ukol.getKalendar().getTo());
        }

        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin API.TASKS_IU("
                    + "inp_id_owner  => ? "
                    + ",inp_id  => ? "
                    + ",inp_name => ? "
                    + ",inp_description => ? "
                    + ",inp_id_project => ? "
                    + ",inp_id_type => ? "
                    + ",inp_date_from => ? "
                    + ",inp_date_to => ? "
                    + "); end;";
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.setInt(1, ukol.getVlastnik_id());
            callStmt.setInt(2, ukol.getId());
            callStmt.setString(3, ukol.getNazev());
            callStmt.setString(4, ukol.getPopis());
            callStmt.setInt(5, ukol.getProjekt());
            callStmt.setInt(6, ukol.getStav());
            callStmt.setString(7, date_from);
            callStmt.setString(8, date_to);
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 20021) {
                DatabaseConnection.showError("Chyba: " + e.getMessage().substring(10, 100).trim());
            } else {
                DatabaseConnection.showError("DB query error: " + e.getMessage());
            }
            return false;
        }
        return true;
    }

    /**
     * Vrátí všechny úkoly daného kontextu.
     *
     * @param kontext
     * @return ukoly
     */
    public List<Task> getTasksWithContext(Context kontext) {
        List<Task> ukoly = new ArrayList<Task>();
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "select "
                    + "id, name, description, id_type, type_name, id_owner, "
                    + "date_from, date_to, id_context, context_name, id_project "
                    + "from tasks_v "
                    + "where id_context = ? ";
            PreparedStatement prepStmt = con.prepareStatement(jobquery);
            prepStmt.setInt(1, kontext.getKontextId());
            ResultSet rset = prepStmt.executeQuery();
            while (rset.next()) {
                Task ukl = new Task(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getInt(7));
                //nastav interval
                ukl.setInterval(rset.getDate(7), rset.getDate(8));
                //nastav kontext ukolu vlastnika// zobrazit jen vlastnikovy?
                ukl.setKontext(rset.getInt(9), rset.getString(10));
                System.out.println(ukl);
                ukoly.add(ukl);
            }
            rset.close();
            prepStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
        }
        return ukoly;
    }

}
