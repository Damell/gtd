package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLInterfaces.IDAOProject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání projektu z databáze.
 *
 * @author GTD team
 * @version 1.0
 */
public class DAOProject implements IDAOProject {

	/**
	 * Konstruktor projektu
	 */
	public DAOProject() {
    }

    /**
     * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze.
     *
     * @param projekt
     */
    public boolean createProject(Project projekt) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String rodic = null;
            if (projekt.getRodic() != null) {
                rodic = projekt.getRodic().getId() + "";
            }
            String jobquery = "begin pavlim33.API.PROJECTS_IU("
                    + "inp_id_person  => ? "
                    + ",inp_name => ? "
                    + ",inp_description => ? "
                    + ",inp_id_project_parent => ? "
                    + ",inp_id_type => ? "
                    + ",out_id => ?"
                    + "); end;";
            //sySystem.out.println(jobquery);
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.setInt(1, projekt.getVlastnik_id());
            callStmt.setString(2, projekt.getNazev());
            callStmt.setString(3, projekt.getPopis());
            callStmt.setString(4, rodic);
            callStmt.setInt(5, projekt.getStav());
            callStmt.registerOutParameter(6, Types.INTEGER);

            callStmt.execute();
            //System.out.println("Result = " + callStmt.getObject(6));

            //přidat všechny osoby ze skupiny toho projektu
            for (int i = 0; i < projekt.getSkupina().size(); i++) {
                //System.out.println(projekt.getSkupina().get(i));
                //System.out.println(callStmt.getObject(6));
                String jobquery_members = "begin pavlim33.API.MEMBERS_IU("
                        + "inp_id_person  =>" + projekt.getSkupina().get(i).getId()
                        + ",inp_id_project => " + callStmt.getObject(6)
                        + "); end;";
                CallableStatement callStmt_members = con.prepareCall(jobquery_members);
                callStmt_members.execute();
                callStmt_members.close();
            }
            callStmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho
     * úkoly a podprojekty.
     *
     * @param projekt
     */
    public boolean deleteProject(Project projekt) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String jobquery = "begin pavlim33.API.PROJECTS_DEL(inp_id  => " + projekt.getId() + "); end;";
            //System.out.println(jobquery);
            CallableStatement callStmt = con.prepareCall(jobquery);
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
     * Vrátí všechny projekty v systému.
	 * @return List<Projekt>
     */
    public List<Project> getAllProjects() {
        List<Project> projekty = new ArrayList<Project>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_person, id_project_parent from pavlim33.projects_v");
            while (rset.next()) {
                Project pro = new Project(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));

                //Nastav rodice projektu
                Statement stmt_rodic = con.createStatement();
                ResultSet rset_rodic = stmt_rodic.executeQuery("select id, name, description, id_type, type_name, id_person, id_project_parent from pavlim33.projects_v where nvl(id,0) = nvl(" + rset.getInt(7) + ",0)");
                while (rset_rodic.next()) {
                    pro.setProjectrodic(rset_rodic.getInt(1), rset_rodic.getString(2), rset_rodic.getString(3), rset_rodic.getInt(4), rset_rodic.getString(5), rset_rodic.getInt(6));
                }
                rset_rodic.close();
                stmt_rodic.close();

                //Pridej osoby do projektu
                Statement stmt_osoby = con.createStatement();
                ResultSet rset_osoby = stmt_osoby.executeQuery("select id_person, login, fname, sname from pavlim33.members_v where id_project = " + rset.getInt(1));
                while (rset_osoby.next()) {
                    Person oso = new Person(rset_osoby.getInt(1), rset_osoby.getString(2), rset_osoby.getString(3), rset_osoby.getString(4));
                    pro.addOsoba(oso);
                }
                rset_osoby.close();
                stmt_osoby.close();

                //Pridej ukoly do projektu
                Statement stmt_ukoly = con.createStatement();
                ResultSet rset_ukoly = stmt_ukoly.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name, id_project from pavlim33.tasks_v where id_project = " + rset.getInt(1));
                while (rset_ukoly.next()) {
                    Task ukl = new Task(rset_ukoly.getInt(1), rset_ukoly.getString(2), rset_ukoly.getString(3), rset_ukoly.getInt(4), rset_ukoly.getString(5), rset_ukoly.getInt(6), rset_ukoly.getInt(11));
                    //nastav interval
                    ukl.setInterval(rset_ukoly.getDate(7), rset_ukoly.getDate(8));
                    //nastav kontext ukolu vlastnika// zobrazit kontext jen vlastnikovy kontextu?
                    ukl.setKontext(rset_ukoly.getInt(9), rset_ukoly.getString(10));
                    pro.addUkol(ukl);
                }
                rset_ukoly.close();
                stmt_ukoly.close();

                //Pro kazdy podprojekt spust pridani do seznamu podprojektu (reverzni volani funkce)
                Statement stmt_podprojekty = con.createStatement();
                ResultSet rset_podprojekty = stmt_podprojekty.executeQuery("select id from pavlim33.projects_v where nvl(id_project_parent,0) = nvl(" + rset.getInt(1) + ",0)");
                while (rset_podprojekty.next()) {
                    //pridej konkretni projekt
                    pro.addProjekt(getProject(rset_podprojekty.getInt(1)));
                }
                rset_podprojekty.close();
                stmt_podprojekty.close();

                //System.out.println(pro);
                projekty.add(pro);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error :: getAllProjekty: " + e.getMessage());
        }
        return projekty;
    }

    /**
     * Vrátí projekt podle jeho ID.
     *
     * @param id
	 * @return projekt
     */
    public Project getProject(int id) {
        Project projekt = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
            ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_person, id_project_parent from pavlim33.projects_v where nvl(id,0) = nvl(" + id + ",0)");
            while (rset.next()) {
                projekt = new Project(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));

                //Nastav rodice projektu
                if (rset.getInt(7) != 0) {
                    Statement stmt_rodic = con.createStatement();
                    ResultSet rset_rodic = stmt_rodic.executeQuery("select id, name, description, id_type, type_name, id_person, id_project_parent from pavlim33.projects_v where nvl(id,0) = nvl(" + rset.getInt(7) + ",0)");
                    while (rset_rodic.next()) {
                        projekt.setProjectrodic(rset_rodic.getInt(1), rset_rodic.getString(2), rset_rodic.getString(3), rset_rodic.getInt(4), rset_rodic.getString(5), rset_rodic.getInt(6));
                    }
                    rset_rodic.close();
                    stmt_rodic.close();
                }

                //System.out.println(rset.getInt(1));
                //Pridej osoby do projektu
                Statement stmt_osoby = con.createStatement();
                ResultSet rset_osoby = stmt_osoby.executeQuery("select id_person, login, fname, sname from pavlim33.members_v where id_project = " + rset.getInt(1));
                while (rset_osoby.next()) {
                    Person oso = new Person(rset_osoby.getInt(1), rset_osoby.getString(2), rset_osoby.getString(3), rset_osoby.getString(4));
                    projekt.addOsoba(oso);
                }
                rset_osoby.close();
                stmt_osoby.close();

                //Pridej ukolu do projektu
                Statement stmt_ukoly = con.createStatement();
                ResultSet rset_ukoly = stmt_ukoly.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name, id_project from pavlim33.tasks_v where id_project = " + rset.getInt(1));
                while (rset_ukoly.next()) {
                    Task ukl = new Task(rset_ukoly.getInt(1), rset_ukoly.getString(2), rset_ukoly.getString(3), rset_ukoly.getInt(4), rset_ukoly.getString(5), rset_ukoly.getInt(6), rset_ukoly.getInt(11));
                    //nastav interval
                    ukl.setInterval(rset_ukoly.getDate(7), rset_ukoly.getDate(8));
                    //nastav kontext ukolu vlastnika// zobrazit kontext jen vlastnikovy kontextu?
                    ukl.setKontext(rset_ukoly.getInt(9), rset_ukoly.getString(10));
                    projekt.addUkol(ukl);
                }
                rset_ukoly.close();
                stmt_ukoly.close();

                //pro kazdy podprojekt spust pridani do seznamu podprojektu (reverzni volani funkce)
                Statement stmt_podprojekty = con.createStatement();
                ResultSet rset_podprojekty = stmt_podprojekty.executeQuery("select id from pavlim33.projects where nvl(id_project_parent,0) = nvl(" + rset.getInt(1) + ",0)");
                while (rset_podprojekty.next()) {
                    //pridej konkretni projekt
                    Project p = getProject(rset_podprojekty.getInt(1));
                    projekt.addProjekt(p);
                }
                rset_podprojekty.close();
                stmt_podprojekty.close();

                //System.out.println(projekt);
                //projekty.add(pro);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error :: getProjekt: " + e.getMessage());
        }
        return projekt;
    }

    /**
     * Uloží zmenený projekt.
     *
     * @param projekt
     */
    public boolean updateProject(Project projekt) {
        Connection con = DatabaseConnection.getConnection();
        try {
            //http://docs.oracle.com/cd/B25329_01/doc/appdev.102/b25108/xedev_jdbc.htm
            String rodic = null;
            if (projekt.getRodic() != null) {
                rodic = projekt.getRodic().getId() + "";
            }
            String jobquery = "begin pavlim33.API.PROJECTS_IU("
                    + "inp_id_person  => ? "
                    + ",inp_name => ? "
                    + ",inp_description => ? "
                    + ",inp_id_project_parent => ? "
                    + ",inp_id_type => ? "
                    + ",out_id => ?"
                    + ",inp_id => ?"
                    + "); end;";
            //sySystem.out.println(jobquery);
            CallableStatement callStmt = con.prepareCall(jobquery);
            callStmt.setInt(1, projekt.getVlastnik_id());
            callStmt.setString(2, projekt.getNazev());
            callStmt.setString(3, projekt.getPopis());
            callStmt.setString(4, rodic);
            callStmt.setInt(5, projekt.getStav());
            callStmt.registerOutParameter(6, Types.INTEGER);
            callStmt.setInt(7, projekt.getId());

            callStmt.execute();
            //System.out.println("Result = " + callStmt.getObject(6));

            //přidat všechny osoby ze skupiny toho projektu
            for (int i = 0; i < projekt.getSkupina().size(); i++) {
                String jobquery_members = "begin pavlim33.API.MEMBERS_IU("
                        + "inp_id_person  =>" + projekt.getSkupina().get(i).getId()
                        + ",inp_id_project => " + projekt.getId()
                        + "); end;";
                CallableStatement callStmt_members = con.prepareCall(jobquery_members);
                callStmt_members.execute();
                callStmt_members.close();
            }
            callStmt.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 20021) {
                DatabaseConnection.showError("Chyba: "+ e.getMessage().substring(10, 100).trim());
            } else {
                DatabaseConnection.showError("DB query error: " + e.getMessage());
            }
            return false;
        }
        return true;
    }

    /**
     * Vrátí všechny projekty patřící zadané osobe.
     *
     * @param osoba
	 * @return List<Projekt>
     */
    public List<Project> getProjectsOfPerson(Person osoba) {
        List<Project> projekty = new ArrayList<Project>();
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            //Pridej do listu vsechny projektu osoby (vcetne podprojektu)
            ResultSet rset = stmt.executeQuery("select id from pavlim33.projects_v where id_person = " + osoba.getId());
            while (rset.next()) {
                projekty.add(getProject(rset.getInt(1)));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            DatabaseConnection.showError("DB query error :: getProjektyOsoby: " + e.getMessage());
        }
        return projekty;
    }

}
