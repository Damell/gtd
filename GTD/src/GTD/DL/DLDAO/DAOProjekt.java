package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import GTD.DL.DLEntity.Ukol;
import GTD.DL.DLInterfaces.IDAOProjekt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání projektu z databáze.
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class DAOProjekt implements IDAOProjekt {

    private Connection con;

    public DAOProjekt() {
        con = DatabaseConnection.getConnection();
    }

    /**
     * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze.
     *
     * @param projekt
     */
    public boolean createProjekt(Projekt projekt) {
        return false;
    }

    /**
     * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho
     * úkoly a podprojekty.
     *
     * @param projekt
     */
    public boolean deleteProjekt(Projekt projekt) {
        return false;
    }

    /**
     * Vrátí všechny projekty v systému.
     */
    public List<Projekt> getAllProjekty() {
        List<Projekt> projekty = new ArrayList<Projekt>();
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
                ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_person, id_project_parent from pavlim33.projects_v");
                while (rset.next()) {
                    Projekt pro = new Projekt(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));

                    //Nastav rodice projektu
                    ResultSet rset_rodic = stmt.executeQuery("select id, name, description, id_type, type_name, id_person, id_project_parent from pavlim33.projects_v where nvl(id,0) = nvl(" + rset.getInt(7) + ",0)");
                    while (rset.next()) {
                        pro.setProjectrodic(rset_rodic.getInt(1), rset_rodic.getString(2), rset_rodic.getString(3), rset_rodic.getInt(4), rset_rodic.getString(5), rset_rodic.getInt(6));
                    }
                    rset_rodic.close();

                    //Pridej osoby do projektu
                    ResultSet rset_osoby = stmt.executeQuery("select id_person, login, fname, sname from pavlim33.members_v where id_project = " + rset.getInt(1));
                    while (rset_osoby.next()) {
                        Osoba oso = new Osoba(rset_osoby.getInt(1), rset_osoby.getString(2), rset_osoby.getString(3), rset_osoby.getString(4));
                        pro.addOsoba(oso);
                    }
                    rset_osoby.close();

                    //Pridej ukolu do projektu
                    ResultSet rset_ukoly = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name from pavlim33.tasks_v where id_project = " + rset.getInt(1));
                    while (rset_ukoly.next()) {
                        Ukol ukl = new Ukol(rset_ukoly.getInt(1), rset_ukoly.getString(2), rset_ukoly.getString(3), rset_ukoly.getInt(4), rset_ukoly.getString(5), rset_ukoly.getInt(6));
                        //nastav interval
                        ukl.setInterval(rset_ukoly.getDate(7), rset_ukoly.getDate(8));
                        //nastav kontext ukolu vlastnika// zobrazit kontext jen vlastnikovy kontextu?
                        ukl.setKontext(rset_ukoly.getInt(9), rset_ukoly.getString(10));
                        pro.addUkol(ukl);
                    }
                    rset_ukoly.close();

                    //pro kazdy podprojekt spust pridani do seznamu podprojektu (reverzni volani funkce)
                    ResultSet rset_podprojekty = stmt.executeQuery("select id from pavlim33.projects where nvl(id_project_parent,0) = nvl(" + rset.getInt(1) + ",0)");
                    while (rset_podprojekty.next()) {
                        //pridej konkretni projekt
                        pro.addProjekt(getProjekt(rset_podprojekty.getInt(1)));
                    }

                    System.out.println(pro);
                    projekty.add(pro);
                }
                rset.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("DB query error");
            }
        }
        return projekty;
    }

    /**
     * Vrátí projekt podle jeho ID.
     *
     * @param id
     */
    public Projekt getProjekt(int id) {
        Projekt projekt = null;
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                //Podminka pro prihlasenou osobu + DatabaseConnection.getID());
                ResultSet rset = stmt.executeQuery("select id, name, description, id_type, type_name, id_person, id_project_parent from pavlim33.projects_v where nvl(id,0) = nvl(" + id + ",0)");
                while (rset.next()) {
                    projekt = new Projekt(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getInt(6));

                    //Nastav rodice projektu
                    ResultSet rset_rodic = stmt.executeQuery("select id, name, description, id_type, type_name, id_person, id_project_parent from pavlim33.projects_v where nvl(id,0) = nvl(" + rset.getInt(7) + ",0)");
                    while (rset.next()) {
                        projekt.setProjectrodic(rset_rodic.getInt(1), rset_rodic.getString(2), rset_rodic.getString(3), rset_rodic.getInt(4), rset_rodic.getString(5), rset_rodic.getInt(6));
                    }
                    rset_rodic.close();

                    //Pridej osoby do projektu
                    ResultSet rset_osoby = stmt.executeQuery("select id_person, login, fname, sname from pavlim33.members_v where id_project = " + rset.getInt(1));
                    while (rset_osoby.next()) {
                        Osoba oso = new Osoba(rset_osoby.getInt(1), rset_osoby.getString(2), rset_osoby.getString(3), rset_osoby.getString(4));
                        projekt.addOsoba(oso);
                    }
                    rset_osoby.close();

                    //Pridej ukolu do projektu
                    ResultSet rset_ukoly = stmt.executeQuery("select id, name, description, id_type, type_name, id_owner, date_from, date_to, id_context, context_name from pavlim33.tasks_v where id_project = " + rset.getInt(1));
                    while (rset_ukoly.next()) {
                        Ukol ukl = new Ukol(rset_ukoly.getInt(1), rset_ukoly.getString(2), rset_ukoly.getString(3), rset_ukoly.getInt(4), rset_ukoly.getString(5), rset_ukoly.getInt(6));
                        //nastav interval
                        ukl.setInterval(rset_ukoly.getDate(7), rset_ukoly.getDate(8));
                        //nastav kontext ukolu vlastnika// zobrazit kontext jen vlastnikovy kontextu?
                        ukl.setKontext(rset_ukoly.getInt(9), rset_ukoly.getString(10));
                        projekt.addUkol(ukl);
                    }
                    rset_ukoly.close();

                    //pro kazdy podprojekt spust pridani do seznamu podprojektu (reverzni volani funkce)
                    ResultSet rset_podprojekty = stmt.executeQuery("select id from pavlim33.projects where nvl(id_project_parent,0) = nvl(" + rset.getInt(1) + ",0)");
                    while (rset_podprojekty.next()) {
                        //pridej konkretni projekt
                        projekt.addProjekt(getProjekt(rset_podprojekty.getInt(1)));
                    }

                    System.out.println(projekt);
                    //projekty.add(pro);
                }
                rset.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("DB query error");
            }
        }
        return projekt;

    }

    /**
     * Uloží zmenený projekt.
     *
     * @param projekt
     */
    public boolean updateProjekt(Projekt projekt) {
        return false;
    }

    /**
     * Vrátí všechny projekty patřící zadané osobe.
     *
     * @param osoba
     */
    public List<Projekt> getProjektyOsoby(Osoba osoba) {
        List<Projekt> projekty = new ArrayList<Projekt>();
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                //Pridej do listu vsechny projektu osoby (vcetne podprojektu)
                ResultSet rset = stmt.executeQuery("select id from pavlim33.projects_v where id_person = " + osoba.getId());
                while (rset.next()) {
                    projekty.add(getProjekt(rset.getInt(1)));
                }
                rset.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("DB query error");
            }
        }
        return projekty;
    }

}
