package consumoAPIS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

	private Connection con;

	public void conexionBD() {

		String userName = "sql11681100";
		String password = "EGpDzE8rwE";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net/sql11681100";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			System.out.println("Conexión a la BD");
			// crearTabla(con);

		} catch (Exception e) {
			System.out.println("Error en conexión: "+e.getMessage());
		}

	}

	public List<Comic> ejecutarSenteciaSelect(String consulta) {

		if (consulta.startsWith("SELECT")) {
			return obtenerDatos(consulta);
		}else {
			return null;
		}
		
	}

	public void ejecutarSentenciaInsert(String consulta) {
		if (consulta.startsWith("INSERT")) {
			insertarDatos(consulta);
		}

	}

	private List<Comic> obtenerDatos(String consulta) {

		List<Comic> listaComic = new ArrayList<Comic>();

		ResultSet rs = null;
		Statement st = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(consulta);
			System.out.println("Tabla abierta");
		} catch (SQLException e) {
			System.out.println("Error al Abrir tabla ");
		}

		try {
			while (rs.next()) {

				// Obtener datos de cada fila
				int id = rs.getInt("id");
				int digitalId = rs.getInt("digitalId");
				String title = rs.getString("title");
				int issueNumber = rs.getInt("issueNumber");
				
				String variantDescription = rs.getString("variantDescription");
				
				String format = rs.getString("format");
				String path_image = rs.getString("path_image");
				
				int nHistories = rs.getInt("nStories");
				String modified = rs.getString("modified");
				String language = rs.getString("language");
				
				// Crear objeto Comic y agregarlo a la lista
				Comic comic = new Comic(id, digitalId, title, issueNumber,variantDescription,path_image,format,nHistories,modified,language);
				listaComic.add(comic);

			}
		} catch (Exception e) {
			System.out.println("Error al visualizar datos");
		}
		return listaComic;
	}

	private void insertarDatos(String consulta) {
		Statement st = null;

		try {
			st = con.createStatement();
			st.executeUpdate(consulta);
		} catch (SQLException e) {
			System.out.println("Error al insertarDatos: " + e.getMessage());
		} finally {
			try {
				if (st != null) {
					st.close();
					System.out.println("Los datos se insertaron correctamente.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void cerrarConexion() {

		try {
			con.close();
			System.out.println("Conexion cerrada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Crear la base de datos
	CREATE DATABASE IF NOT EXISTS marvel;
	
	-- Seleccionar la base de datos
	USE marvel;
	
	-- Crear la tabla "comics"
	CREATE TABLE IF NOT EXISTS comics (
	id INT PRIMARY KEY,
	digital_id INT,
	title VARCHAR(255),
	issue_number INT,
	variant_description VARCHAR(255)
	);
	*/

}
