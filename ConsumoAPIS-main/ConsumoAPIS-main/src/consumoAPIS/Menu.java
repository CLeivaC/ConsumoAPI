package consumoAPIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;


public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private JButton btn_mostrarDatos;
	
	private JButton generar_Informe;

	private JButton btn_insertarComic;
	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_mostrarDatos = new JButton("MostrarDatos");
		
		btn_mostrarDatos.setBounds(156, 36, 114, 23);
		contentPane.add(btn_mostrarDatos);
		
		btn_insertarComic = new JButton("Insertar Comic");
		
		btn_insertarComic.setBounds(156, 70, 124, 23);
		contentPane.add(btn_insertarComic);
		
		 generar_Informe = new JButton("Generar informe");
		
		 generar_Informe.setBounds(131, 115, 162, 23);
		contentPane.add(generar_Informe);
		
		JButton btnNewButton_1_2 = new JButton("Cargar API");
		btnNewButton_1_2.setBounds(166, 177, 104, 23);
		contentPane.add(btnNewButton_1_2);
	}
	
	
	public void initListeners() {
		
		btn_mostrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_MostrarDatos interfaz = new Interfaz_MostrarDatos();
				interfaz.setVisible(true);
				setVisible(false);
				interfaz.manejarListeners();
				
			}
		});
		
		btn_insertarComic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InsertarComics interfaz_comics = new InsertarComics();
				interfaz_comics.setVisible(true);
				setVisible(false);
				interfaz_comics.insertarComic();
			}
		});
		
		generar_Informe.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        final String JRXML_PATH = "informe_comics.jrxml";
		        final String COMPILED_REPORT_PATH = "informe_comics.jasper";

		        try {
		            // Compile the JRXML file to a Jasper file (if not already compiled)
		            JasperCompileManager.compileReportToFile(JRXML_PATH);

		            // Load the compiled report design (.jasper file)
		            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(COMPILED_REPORT_PATH);

		            Conexion con = new Conexion();
		            con.conexionBD();

		            // Fetch data from the database
		            List<Comic> listComics = con.ejecutarSenteciaSelect("SELECT * FROM Comics");

		            // Convert the list of Comics to a JRBeanCollectionDataSource
		            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listComics);

		            // Define parameters if your report uses any
		            Map<String, Object> parameters = new HashMap<>();
		            // Add parameters if needed
		            // parameters.put("paramName", paramValue);

		            // Fill the report with data
		            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		            // Export the report to PDF
		            JasperExportManager.exportReportToPdfFile(jasperPrint, "informeComics.pdf");

		            JOptionPane.showMessageDialog(null, "Informe generado correctamente.");

		        } catch (JRException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al generar el informe: " + ex.getMessage());
		        }
		    }
		});
		
	}
}
