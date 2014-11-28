/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

/**
 *
 * @author skvarla
 */
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOProject;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.ProjectState;
import GTD.DL.DLEntity.Task;
import GTD.DL.hibernate.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectRestController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    String getAll() {

        DAOProject dp = new DAOProject();
        dp.setSessionFactory(HibernateUtil.getSessionFactory());

        List<Project> projects = dp.getAll();

        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Project p : projects) {
            JsonObject Rodic = Json.createObjectBuilder()
                    .add("id", p.getRodic().getId())
                    .add("title", p.getRodic().getNazev())
                    .build();

            ProjectState ps = p.getStav();
            JsonObject state = Json.createObjectBuilder()
                    .add("id", ps.getId())
                    .add("code", ps.getKod())
                    .add("title", ps.getNazev())
                    .add("description", ps.getPopis())
                    .build();

            JsonObject obj = Json.createObjectBuilder()
                    .add("id", p.getId())
                    .add("title", p.getNazev())
                    .add("description", p.getPopis())
                    .add("owner", p.getVlastnik().getId())
                    .add("creator", "none") // TODO p.getTvurce().getId()
                    .add("project", Rodic)
                    .add("state", state)
                    .build();
            builder.add(obj);

        }

        return builder.build().toString();

    }
}
