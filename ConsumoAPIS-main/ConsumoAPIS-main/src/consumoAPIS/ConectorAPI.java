package consumoAPIS;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ConectorAPI {
	static List<Comic> comicsList = new ArrayList<>();

	public static void consumirAPI() {

		try {
			String publicKey = "4b247f6b479af3763005a0e303b872bb";
			String privateKey = "36ff961ed51d256b32face6239d72443992e4be8";

			long timestamp = System.currentTimeMillis();
			String hash = generateHash(timestamp, privateKey, publicKey);

			URL url = new URL("https://gateway.marvel.com:443/v1/public/comics?format=comic&formatType=comic&apikey="
					+ publicKey + "&ts=" + timestamp + "&hash=" + hash);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();

			int responseCode = con.getResponseCode();

			if (responseCode != 200) {
				throw new RuntimeException("Ocurrio un error: " + responseCode);
			} else {

				generarComics(con);
			}
		} catch (Exception e) {

		}

	}

	public static void generarComics(HttpsURLConnection con) {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				response.append(line);
			}

			JSONArray comicsArray = new JSONObject(response.toString()).getJSONObject("data").getJSONArray("results");

			for (int i = 0; i < comicsArray.length(); i++) {
				JSONObject comicObject = comicsArray.getJSONObject(i);

				// Verifica si el cómic tiene imágenes
				JSONArray imagesArray = comicObject.optJSONArray("images");
				if (imagesArray != null && imagesArray.length() > 0) {
					// Obtén la información de la imagen
					JSONObject thumbnailObject = comicObject.getJSONObject("thumbnail");
					String path = thumbnailObject.getString("path");
					String extension = thumbnailObject.getString("extension");

					// Verifica si la ruta de la imagen es "not_avaliable"
					if (!"not_avaliable".contains(path)) {
						Comic comic = new Comic();
						comic.setId(comicObject.getInt("id"));
						if (comicObject.getInt("digitalId") != 0) {
							comic.setDigitalId(comicObject.getInt("digitalId"));
						} else {
							comic.setDigitalId(15643254 + i);
						}

						comic.setTitle(comicObject.getString("title"));

						if (comicObject.getInt("issueNumber") != 0) {

							comic.setIssueNumber(comicObject.getInt("issueNumber"));
						} else {
							comic.setIssueNumber(8 + i);
						}

						if (!comicObject.getString("variantDescription").isBlank()) {

							comic.setVariantDescription(comicObject.optString("variantDescription", ""));
						} else {
							comic.setVariantDescription(
									"En el oscuro rincón del multiverso, un héroe renace de las sombras, enfrentándose a un destino entrelazado con poderes cósmicos y enemigos indomables. ");
						}
						Image image = new Image(path, extension);
						comic.setThumbnail(image);
						comic.setPath_image(comic.getPath_image());

						comic.setFormat(comicObject.getString("format"));

						comic.setModified(comicObject.getString("modified"));

						JSONObject storiesObject = comicObject.optJSONObject("stories");
						if (storiesObject != null) {

							comic.setnStories(storiesObject.getInt("available"));

						}

						JSONArray textObjectsArray = comicObject.optJSONArray("textObjects");
						if (textObjectsArray != null && textObjectsArray.length() > 0) {
							JSONObject textObject = textObjectsArray.getJSONObject(0);

							// Luego, obtén la información de "language"
							String language = textObject.optString("language", "N/A");

							// Puedes hacer lo que desees con la información de "language"
							comic.setLanguage(language);
						} else {
							comic.setLanguage("ES-IT-FR");
						}

						// Agrega el cómic a la lista
						comicsList.add(comic);

						// Imprime cada cómic en la lista
						System.out.println("Comic ID: " + comic.getId());
						System.out.println("Digital ID: " + comic.getDigitalId());
						System.out.println("Title: " + comic.getTitle());
						System.out.println("Issue Number: " + comic.getIssueNumber());
						System.out.println("Thumbnail Path: " + comic.getPath_image());
						System.out.println("Format: " + comic.getFormat());
						System.out.println("Histories: " + comic.getnStories());
						System.out.println("Ultima modificación: " + comic.getModified());
						System.out.println("Variant Description: " + comic.getVariantDescription());
						System.out.println("Idioma: " + comic.getLanguage());
						System.out.println("-------------------------");
					}
				}
			}

			con.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String generateHash(long timestamp, String privateKey, String publicKey) {
		String input = timestamp + privateKey + publicKey;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());

			// Convertir el hash en formato hexadecimal
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				String hex = Integer.toHexString(0xFF & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public static List<Comic> leerSQL() {
		List<Comic> listaComics = new ArrayList<Comic>();

		Conexion con = new Conexion();
		con.conexionBD();
		listaComics = con.ejecutarSenteciaSelect("SELECT * FROM Comics;");
		con.cerrarConexion();

		return listaComics;
	}

	// https://www.youtube.com/watch?v=C1GlrrIi_vU&ab_channel=HaranitGonzalez
	//https://www.phpmyadmin.co/index.php?db=sql11679472&table=Comics&target=tbl_structure.php
}
