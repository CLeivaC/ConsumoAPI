package consumoAPIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilidadJson {
	public static void writeToJson(List<Comic> comics) {
		/*
		 * try { // Crear un nuevo ObjectMapper para la escritura de JSON ObjectMapper
		 * objectMapper = new ObjectMapper();
		 * 
		 * // Configurar el formato de salida del JSON ObjectWriter objectWriter =
		 * objectMapper.writerWithDefaultPrettyPrinter();
		 * 
		 * // Convertir la lista de cómics a formato JSON y escribirlo en un archivo
		 * objectWriter.writeValue(new FileWriter(filePath), comics); } catch (Exception
		 * e) { e.printStackTrace(); }
		 */

		StringBuilder texto = new StringBuilder();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("comics.json"))) {

			texto.append("[ ");

			for (Comic c : comics) {

				texto.append("{\"id\":\"" + c.getId() + "\",\n\"digitalId\":" + c.getDigitalId() + ",\n\"title\":\""
						+ c.getTitle() + "\",\n\"issueNumber\":" + c.getIssueNumber() + ",\n\"path_image\":\""
						+ c.getPath_image() + "\",\n\"format\":\"" + c.getFormat() + "\",\n\"histories\":"
						+ c.getnStories() + ",\n\"modified\":\"" + c.getModified()
						+ "\",\n\"variantDescription\":\"" + c.getVariantDescription() + "\",\n\"language\":\""
						+ c.getLanguage() + "\"\n},");

			}
			texto.deleteCharAt(texto.length() - 1);
			texto.append(" ]");
			bw.write(texto.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/*public static List<Comic> readJson(String path) {
		List<Comic> listaComics = new ArrayList<Comic>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(br);

			// Asumiendo que "results" es un array en tu JSON
			JsonNode resultsArray = jsonNode.path("data").path("results");

			for (JsonNode comicNode : resultsArray) {
				int comicId = comicNode.path("id").asInt();
				int digitalId = comicNode.path("digitalId").asInt();
				String title = comicNode.path("title").asText();
				int issueNumber = comicNode.path("issueNumber").asInt();
				String variantDescription = comicNode.path("variantDescription").asText();
				String path_image = comicNode.path("path_image").asText();
				String format = comicNode.path("format").asText();
				int nStories = comicNode.path("nStories").asInt();
				String modified = comicNode.path("modified").asText();
				String language = comicNode.path("language").asText();

				// Crea una instancia de la clase Comic y agrégala a la lista

				Comic comic = new Comic(comicId, digitalId, title, issueNumber, variantDescription, path_image, format,
						nStories, modified, language);
				listaComics.add(comic);
			}
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return listaComics;

	}*/
	
	 public static List<Comic> readJson(String filePath) {
	        List<Comic> comics = new ArrayList<>();

	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            File file = new File(filePath);

	            // Read JSON file and convert to a List of Comic objects
	            comics = objectMapper.readValue(file, new TypeReference<List<Comic>>() {});
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return comics;
	    }
	
	
	
}