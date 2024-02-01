package consumoAPIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

public class InsertarComics extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_id;
	private JTextField tf_digitalId;
	private JTextField tf_title;
	private JTextField tf_issueNumber;
	private JTextField tf_variantDescription;
	private JTextField tf_format;
	private JTextField tf_pathImage;
	private JTextField tf_stories;
	private JTextField tf_language;
	private JTextField tf_modified;
	private JButton btnCrearComic;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public InsertarComics() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("marvel-comics-here-come-i11484.jpg"));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Comics Marvel");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(153, 21, 137, 25);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("id");
		lblNewLabel_2.setBounds(58, 82, 46, 14);
		panel_1.add(lblNewLabel_2);
		
		tf_id = new JTextField();
		tf_id.setBounds(58, 107, 86, 20);
		panel_1.add(tf_id);
		tf_id.setColumns(10);
		
		tf_digitalId = new JTextField();
		tf_digitalId.setColumns(10);
		tf_digitalId.setBounds(203, 107, 86, 20);
		panel_1.add(tf_digitalId);
		
		JLabel lblNewLabel_2_1 = new JLabel("Digital id");
		lblNewLabel_2_1.setBounds(203, 82, 46, 14);
		panel_1.add(lblNewLabel_2_1);
		
		tf_title = new JTextField();
		tf_title.setColumns(10);
		tf_title.setBounds(324, 107, 86, 20);
		panel_1.add(tf_title);
		
		JLabel lblNewLabel_2_2 = new JLabel("Title");
		lblNewLabel_2_2.setBounds(324, 82, 46, 14);
		panel_1.add(lblNewLabel_2_2);
		
		tf_issueNumber = new JTextField();
		tf_issueNumber.setColumns(10);
		tf_issueNumber.setBounds(58, 196, 86, 20);
		panel_1.add(tf_issueNumber);
		
		JLabel lblNewLabel_2_3 = new JLabel("IssueNumber");
		lblNewLabel_2_3.setBounds(58, 171, 86, 14);
		panel_1.add(lblNewLabel_2_3);
		
		tf_variantDescription = new JTextField();
		tf_variantDescription.setColumns(10);
		tf_variantDescription.setBounds(191, 196, 86, 20);
		panel_1.add(tf_variantDescription);
		
		JLabel lblNewLabel_2_4 = new JLabel("Variant description");
		lblNewLabel_2_4.setBounds(191, 171, 99, 14);
		panel_1.add(lblNewLabel_2_4);
		
		tf_format = new JTextField();
		tf_format.setColumns(10);
		tf_format.setBounds(324, 196, 86, 20);
		panel_1.add(tf_format);
		
		JLabel lblNewLabel_2_5 = new JLabel("Format");
		lblNewLabel_2_5.setBounds(324, 171, 46, 14);
		panel_1.add(lblNewLabel_2_5);
		
		tf_pathImage = new JTextField();
		tf_pathImage.setColumns(10);
		tf_pathImage.setBounds(58, 284, 352, 20);
		panel_1.add(tf_pathImage);
		
		JLabel lblNewLabel_2_6 = new JLabel("Path image");
		lblNewLabel_2_6.setBounds(203, 258, 86, 14);
		panel_1.add(lblNewLabel_2_6);
		
		tf_stories = new JTextField();
		tf_stories.setColumns(10);
		tf_stories.setBounds(58, 376, 86, 20);
		panel_1.add(tf_stories);
		
		JLabel lblNewLabel_2_7 = new JLabel("Number of Stories");
		lblNewLabel_2_7.setBounds(58, 351, 107, 14);
		panel_1.add(lblNewLabel_2_7);
		
		tf_language = new JTextField();
		tf_language.setColumns(10);
		tf_language.setBounds(220, 376, 86, 20);
		panel_1.add(tf_language);
		
		JLabel lblNewLabel_2_8 = new JLabel("Language");
		lblNewLabel_2_8.setBounds(220, 351, 86, 14);
		panel_1.add(lblNewLabel_2_8);
		
		tf_modified = new JTextField();
		tf_modified.setColumns(10);
		tf_modified.setBounds(58, 456, 86, 20);
		panel_1.add(tf_modified);
		
		JLabel lblNewLabel_2_9 = new JLabel("Modified");
		lblNewLabel_2_9.setBounds(58, 431, 46, 14);
		panel_1.add(lblNewLabel_2_9);
		
		btnCrearComic = new JButton("Crear Comic");
		btnCrearComic.setBounds(153, 568, 95, 23);
		panel_1.add(btnCrearComic);
	}
	
	
	public void insertarComic() {
		
		btnCrearComic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Conexion con = new Conexion();
				con.conexionBD();
				con.ejecutarSentenciaInsert("INSERT INTO Comics (id,digitalId,title,issueNumber,path_image,format,nStories,modified,variantDescription,language) VALUES ("
						+ Integer.parseInt(tf_id.getText()) + "," + Integer.parseInt(tf_digitalId.getText()) + ",'" + tf_title.getText() + "',"
						+ Integer.parseInt(tf_issueNumber.getText()) + ",'" + tf_pathImage.getText() + "','" + tf_format.getText() + "',"
						+ Integer.parseInt(tf_stories.getText()) + ",'" + tf_modified.getText() + "','"
						+ tf_variantDescription.getText() + "','" + tf_language.getText() + "');");
				con.cerrarConexion();
			}
		});
		
	}
}
