package gui;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import domain.UserAplication;
import businessLogic.ApplicationFacadeInterface;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.regex.Pattern;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserRegisterGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textEmail;
	private JPasswordField passPass;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JTextField textPais;
	private JComboBox<String> comboEC;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton buttonRegister;
	private JSpinner textEdad;
	private SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0, 0, 150, 1);
	private ImageIcon perfil = null;
	private ImageIcon imagenDefecto = new ImageIcon(VerFotos.class.getResource("/localData/perfilDefault.png"));
	private JLabel labelFoto;
	private JLabel lblCargando = null;
	private JButton btnNewButton;
	
	/**
	 * Create the panel.
	 */
	public UserRegisterGUI(String email){
		setLayout(null);
		
		JLabel label = new JLabel("Email (user)*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.PLAIN, 21));
		label.setBounds(12, 35, 156, 34);
		add(label);
		
		textEmail = new JTextField();
		
		textEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String email = textEmail.getText();
				String exp = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"; 
				CharSequence seq = email;
				Pattern pattern = Pattern.compile(exp,Pattern.CASE_INSENSITIVE); 
				java.util.regex.Matcher m = pattern.matcher(seq);
				if (m.matches())textEmail.setForeground(Color.GREEN);
				else textEmail.setForeground(Color.RED);
			}
		});
		textEmail.setColumns(10);
		textEmail.setText(email);
		textEmail.setBounds(192, 35, 366, 34);
		add(textEmail);
		
		JLabel label_1 = new JLabel("Pass*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_1.setBounds(44, 104, 124, 34);
		add(label_1);
		
		passPass = new JPasswordField();
		passPass.setBounds(192, 104, 192, 34);
		add(passPass);
		
		JLabel label_2 = new JLabel("Edad");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_2.setBounds(396, 104, 53, 34);
		add(label_2);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(366, 173, 192, 34);
		add(textNombre);
		
		JLabel label_3 = new JLabel("Nombre*");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_3.setBounds(279, 173, 81, 34);
		add(label_3);
		
		comboEC = new JComboBox<String>();
		comboEC.setBounds(192, 173, 75, 34);
		comboEC.setModel(modeloEC);
		modeloEC.addElement("");
		modeloEC.addElement("Sr.");
		modeloEC.addElement("Sra.");
		modeloEC.addElement("Srta.");
		add(comboEC);
		
		JLabel label_4 = new JLabel("Estado Civil*");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_4.setBounds(44, 173, 124, 34);
		add(label_4);
		
		JLabel label_5 = new JLabel("Apellidos");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_5.setBounds(44, 242, 124, 34);
		add(label_5);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(192, 242, 366, 34);
		add(textApellido);
		
		textTelefono = new JTextField();
		textTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if(textTelefono.getText().length()>=9) evt.consume();
				if((car<'0' || car>'9')) evt.consume();
			}
		});
		textTelefono.setColumns(10);
		textTelefono.setBounds(192, 311, 133, 34);
		add(textTelefono);
		
		JLabel label_6 = new JLabel("Telefono");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_6.setBounds(44, 311, 124, 34);
		add(label_6);
		
		JLabel label_7 = new JLabel("Pais*");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_7.setBounds(337, 311, 53, 34);
		add(label_7);
		
		textPais = new JTextField();
		textPais.setColumns(10);
		textPais.setBounds(402, 311, 156, 34);
		add(textPais);
		
		buttonRegister = new JButton("");
		buttonRegister.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				lblCargando.setVisible(true);
			}
		});
		buttonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String email = textEmail.getText();
				@SuppressWarnings("deprecation")
				String pass = passPass.getText();
				String edad = textEdad.getValue().toString();
				String estadoCivil = (String) comboEC.getSelectedItem();
				String nombre = textNombre.getText();
				String apellidos = textApellido.getText();
				String telefono = textTelefono.getText();
				String pais = textPais.getText();
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					if(Start.estadoLogin()){
						try {
							facade.modificarPerfil(Start.getUsuario1(), estadoCivil, nombre, apellidos, telefono, pais, edad, perfil);
							Start.hacerLogin(Start.getUsuario1().getEmail(), Start.getUsuario1().getPass());
							javax.swing.JOptionPane.showMessageDialog(null, "Perfil modificado correctamente.", "Bien....", javax.swing.JOptionPane.NO_OPTION);
							JPanel temp1 = new PantallaPrincipalGUI();
							Start.modificarPanelAbajo(temp1);
							Start.setFotoPerfil(facade.getFotoPerfil(facade.getUsuario(Start.getUsuario1()).getEmail()));
						} catch (Exception e) {
							javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
						}
					}else{ 
						try {
							facade.nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, (String) edad, perfil);
							JPanel temp = new LoginONGUI();
							Start.modificarPanelArriba(temp);
							javax.swing.JOptionPane.showMessageDialog(null, "Nuevo usuario registrado correctamente.\nLogueado.", "Bien....", javax.swing.JOptionPane.NO_OPTION);
							Start.hacerLogin(email, pass);
							Start.setFotoPerfil(facade.getFotoPerfil(facade.getUsuario(Start.getUsuario()).getEmail()));
							if (javax.swing.JOptionPane.showConfirmDialog(null, "¿Eres propietario de una casa rural?", "Bien....", javax.swing.JOptionPane.YES_NO_OPTION) == 0){
								JPanel temp1 = new OwnerRegisterGUI();
								Start.modificarPanelAbajo(temp1);
							}else{
								JPanel temp1 = new PantallaPrincipalGUI();
								Start.modificarPanelAbajo(temp1);
							}
						} catch (Exception e) {
							javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
						}

					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblCargando.setVisible(false);
			}
		});
		buttonRegister.setForeground(Color.BLUE);
		buttonRegister.setFont(new Font("Dialog", Font.PLAIN, 21));
		buttonRegister.setBounds(434, 380, 124, 45);
		add(buttonRegister);
		
		JLabel label_8 = new JLabel("Los campos marcados con * son obligatorios");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_8.setBounds(54, 380, 366, 34);
		add(label_8);
		
		textEdad = new JSpinner();
		textEdad.setBounds(477, 104, 81, 34);
		textEdad.setModel(modeloSpinner);
		add(textEdad);
		
		JButton btnAadirfoto = new JButton("A\u00F1adirFoto");
		btnAadirfoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anadirImagen();
			}
		});
		btnAadirfoto.setBounds(653, 84, 109, 23);
		add(btnAadirfoto);
		
		labelFoto = new JLabel("");
		labelFoto.setBounds(653, 120, 250, 250);
		labelFoto.setBorder(BorderFactory.createLoweredBevelBorder());
		add(labelFoto);
		
		JButton button_1 = new JButton("Eliminar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				perfil = null;
				cargarImagen();
			}
		});
		button_1.setBounds(794, 84, 109, 23);
		add(button_1);
		
		btnNewButton = new JButton("Cambiar Contrase\u00F1a");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambiarContraseñaGUI temp1 = new CambiarContraseñaGUI();
				temp1.setVisible(true);
			}
		});
		btnNewButton.setBounds(192, 104, 192, 34);
		btnNewButton.setVisible(false);
		
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		lblCargando = new JLabel("Cargando");
		lblCargando.setIcon(new ImageIcon(UserRegisterGUI.class.getResource("/localData/caragando.gif")));
		lblCargando.setBounds(376, 98, 220, 220);
		lblCargando.setVisible(false);
		add(lblCargando, 1);

		inicializarCampos();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializarCampos(){
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			if (Start.estadoLogin()){
				UserAplication user = facade.getUsuario(Start.getUsuario());
				textEmail.enable(false);
				textEmail.setText(user.getEmail());
				textEdad.setValue(Integer.parseInt(user.getEdad()));
				textNombre.setText(user.getName());
				textApellido.setText(user.getApellidos());
				textTelefono.setText(user.getTelefono());
				textPais.setText(user.getPais());
				comboEC.setSelectedItem(user.getEstadoCivil());
				buttonRegister.setText("Guardar");
				perfil = facade.getFotoPerfil(user.getEmail());
				btnNewButton.setVisible(true);
				passPass.setVisible(false);
			} else {
				textEmail.enable(true);
				//textEmail.setText("");
				passPass.setText("");
				modeloSpinner.setValue(0);
				textNombre.setText("");
				textApellido.setText("");
				textTelefono.setText("");
				textPais.setText("");
				comboEC.setSelectedIndex(0);
				buttonRegister.setText("Registrar");
				perfil = null;
				btnNewButton.setVisible(false);
				passPass.setVisible(true);
			}
			cargarImagen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private  void cargarImagen(){
		Image aux = imagenDefecto.getImage();
		if (perfil != null) {
			aux = perfil.getImage();
		}
        Image aux1 = aux.getScaledInstance(labelFoto.getHeight(), labelFoto.getWidth(), java.awt.Image.SCALE_SMOOTH);
        labelFoto.setIcon(new ImageIcon(aux1));
	}
	
	private void anadirImagen(){
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "bmp", "gif", "jpg", "png");
		fc.setFileFilter(filter);
		int respuesta = fc.showOpenDialog(this);
        if (respuesta == JFileChooser.APPROVE_OPTION){            		
       		try {
       			File imagenElegida = fc.getSelectedFile();
       	        ImageIcon imagen = new ImageIcon(imagenElegida.getPath());
       	        perfil = imagen;
       		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        cargarImagen();
	}
}
