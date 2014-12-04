/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/**
 *
 * @author simon
 */
public class PersonSerializer extends JsonSerializer<Person>
{

	@Override
	public void serialize(Person p, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException
	{
		jg.writeNumber(p.getId());
	}
	
}
