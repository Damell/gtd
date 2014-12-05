/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

import GTD.DL.DLEntity.Project;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

/**
 * 
 * @author simon
 */
public class ProjectDeserializer extends JsonDeserializer<Project>
{

	@Override
	public Project deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException
	{
		JsonNode node = jp.getCodec().readTree(jp);
		int id = node.get(ApiConstants.PROJECT_ID).asInt();
		String title = node.get(ApiConstants.PROJECT_TITLE).asText();
		Project project = new Project();
		project.setId(id);
		project.setTitle(title);
		
		return project;
	}
	
}
