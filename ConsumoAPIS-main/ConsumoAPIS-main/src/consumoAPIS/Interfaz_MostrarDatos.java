package consumoAPIS;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

public class Interfaz_MostrarDatos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JButton abrirJfilechooserXML;
	private JButton abrirJfilechooserJSON;
	private List<Comic> listComics = new ArrayList<Comic>();
	private String rutaCompleta;
	private DefaultTableModel modelo;
	JFileChooser fileChooser;

	JButton btn_mostrarSQL;
	private JPanel panel_1;
	private JButton btn_insertJson;

	/**
	 * Create the frame.
	 */
	public Interfaz_MostrarDatos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();

		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Mostrar Datos Comics Marvel");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JPanel borderCenter = new JPanel();
		borderCenter.setLayout(new BorderLayout());

		contentPane.add(borderCenter, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		borderCenter.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);

		abrirJfilechooserXML = new JButton("Seleccionar Archivo XML");

		textField_1 = new JTextField();
		panel_5.add(textField_1);
		textField_1.setColumns(30);

		panel_5.add(abrirJfilechooserXML);

		abrirJfilechooserJSON = new JButton("Seleccionar Archivo Json");
		panel_5.add(abrirJfilechooserJSON);
		btn_mostrarSQL = new JButton("Datos desde MySQL");
		panel_5.add(btn_mostrarSQL);

		JScrollPane scrollPane = new JScrollPane();

		borderCenter.add(scrollPane, BorderLayout.CENTER);

		String[] columnas = { "id", "digitalId", "title", "issueNumber", "variantDescription", "pathImage", "format",
				"nStories", "modified", "language" };
		modelo = new DefaultTableModel(null, columnas);
		table = new JTable(modelo);

		scrollPane.setViewportView(table);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JPanel panel_3 = new JPanel();

		panel_3.setLayout(new GridLayout(1, 3, 0, 0));

		Border bordePanel = BorderFactory.createEmptyBorder(20, 20, 20, 20);

		JPanel panelBorderUlt1 = new JPanel(new BorderLayout());

		panelBorderUlt1.setBorder(bordePanel);

		contentPane.setBackground(new Color(249, 228, 183));
		panel.setBackground(new Color(249, 228, 183));
		panel_2.setBackground(new Color(249, 228, 183));
		panel_3.setBackground(new Color(249, 228, 183));
		panel_5.setBackground(new Color(249, 228, 183));

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btn_insertJson = new JButton("Insertar Datos");

		panel_1.add(btn_insertJson);
		panelBorderUlt1.setBackground(new Color(249, 228, 183));

	}

	public void manejarListeners() {

		abrirJfilechooserXML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear un JFileChooser
				fileChooser = new JFileChooser();

				// Establecer la selección de archivos
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				// Configurar el filtro para que solo muestre archivos XML
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos XML", "xml");
				fileChooser.setFileFilter(filter);

				// Mostrar el cuadro de diálogo para seleccionar un archivo
				int result = fileChooser.showOpenDialog(contentPane);

				// Verificar si el usuario seleccionó un archivo
				if (result == JFileChooser.APPROVE_OPTION) {
					// Obtener el archivo seleccionado
					rutaCompleta = fileChooser.getSelectedFile().getAbsolutePath();
					textField_1.setText(rutaCompleta);
					System.out.println("Archivo seleccionado: " + rutaCompleta);

					// Limpiar la tabla
					limpiarTabla();

					// Cargar datos desde el archivo XML
					UtilidadXML xml = new UtilidadXML();
					org.w3c.dom.Document doc = xml.initReadDOM();
					listComics = xml.readComics(doc);

					// Cargar datos en la tabla
					cargarComicsEnTabla();
				}
			}
		});

		abrirJfilechooserJSON.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear un JFileChooser
				fileChooser = new JFileChooser();

				// Establecer la selección de archivos
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				// Configurar el filtro para que solo muestre archivos XML
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
				fileChooser.setFileFilter(filter);

				// Mostrar el cuadro de diálogo para seleccionar un archivo
				int result = fileChooser.showOpenDialog(contentPane);

				// Verificar si el usuario seleccionó un archivo
				if (result == JFileChooser.APPROVE_OPTION) {
					// Obtener el archivo seleccionado
					rutaCompleta = fileChooser.getSelectedFile().getAbsolutePath();
					textField_1.setText(rutaCompleta);
					System.out.println("Archivo seleccionado: " + rutaCompleta);

					// Limpiar la tabla
					limpiarTabla();

					// Cargar datos desde el archivo XML
					listComics = UtilidadJson.readJson(rutaCompleta);

					// Cargar datos en la tabla
					cargarComicsEnTabla();
				}

			}
		});

		btn_mostrarSQL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Limpiar la tabla
				limpiarTabla();

				// Fetch data from the database and populate the table model
				Conexion con = new Conexion();
				con.conexionBD();
				listComics = con.ejecutarSenteciaSelect("SELECT * FROM Comics");

				// Cargar datos en la tabla
				cargarComicsEnTabla();
			}
		});

		btn_insertJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertirSQL();
			}
		});

	}

	private void cargarComicsEnTabla() {

		for (Comic comic : listComics) {
			// Assuming Comic is a class representing a row in your table
			Vector<Object> fila = new Vector<>();
			fila.add(comic.getId());
			fila.add(comic.getDigitalId());
			fila.add(comic.getTitle());
			fila.add(comic.getIssueNumber());
			fila.add(comic.getPath_image());
			fila.add(comic.getFormat());
			fila.add(comic.getnStories());
			fila.add(comic.getModified());
			fila.add(comic.getVariantDescription());
			fila.add(comic.getLanguage());

			modelo.addRow(fila);
		}

	}

	public void convertirSQL() {

		StringBuilder sentencia = new StringBuilder();
		Conexion con = new Conexion();
		con.conexionBD();

		for (Comic elemento : listComics) {

			String thumbnailUrl = elemento.getPath_image();
			sentencia.append(
					"INSERT INTO Comics (id,digitalId,title,issueNumber,path_image,format,nStories,modified,variantDescription,language) VALUES ("
							+ elemento.getId() + "," + elemento.getDigitalId() + ",'" + elemento.getTitle() + "',"
							+ elemento.getIssueNumber() + ",'" + thumbnailUrl + "','" + elemento.getFormat() + "',"
							+ elemento.getnStories() + ",'" + elemento.getModified() + "','"
							+ elemento.getVariantDescription() + "','" + elemento.getLanguage() + "');");

			con.ejecutarSentenciaInsert(sentencia.toString());
			sentencia = new StringBuilder();
		}
		con.cerrarConexion();

	}

	private void limpiarTabla() {
		// Limpiar todas las filas de la tabla
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
	}

}
