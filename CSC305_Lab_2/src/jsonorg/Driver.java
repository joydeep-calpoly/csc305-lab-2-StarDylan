package jsonorg;

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

public class Driver {
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
				JSONObject json = new JSONObject(content);
				return Person.parseFromJson(json);
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
		
		for (Person p: people) {
			System.out.println(p.toString());
		}
		
	}
}
