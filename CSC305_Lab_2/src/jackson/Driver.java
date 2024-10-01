package jackson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static void main(String[] args) throws JSONException, IOException {
		
		List<String> files = Arrays.asList("input1.json", "input2.json", "input3.json");
		
		List<Person> people = files.stream().map(file -> {
			String content = null;
			try {
				content = Files.readString(Paths.get(file), StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				return MAPPER.readValue(content, Person.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
				return null;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
		
		for (Person p: people) {
			System.out.println(p.toString());
		}
		
	}
}
