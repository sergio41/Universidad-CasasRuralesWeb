package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import businessLogic.ApplicationFacadeInterface;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambiarContraseñaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_1 = new JLabel();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	/**
	 * Launch the application.
	 */
	public static void main( String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarContraseñaGUI frame = new CambiarContraseñaGUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CambiarContraseñaGUI() {
		setResizable(false);
		setBackground(new Color(0, 206, 209));
		setIconImage(Toolkit.getDefaultToolkit().getImage(CambiarContraseñaGUI.class.getResource("/localData/casaDefault.png")));
		setTitle("Ver fotos Casa Rural");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 395, 278);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a actual:");
		lblNewLabel_2.setBounds(32, 68, 101, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nueva Contrase\u00F1a:");
		lblNewLabel_3.setBounds(30, 121, 101, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblConfirmaContrasea = new JLabel("Confirma contrase\u00F1a:");
		lblConfirmaContrasea.setBounds(20, 172, 111, 14);
		contentPane.add(lblConfirmaContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 60, 220, 30);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(141, 113, 220, 30);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(141, 167, 220, 30);
		contentPane.add(passwordField_2);
		
		JLabel lblNewLabel_4 = new JLabel("Cambiar Contrase\u00F1a");
		lblNewLabel_4.setBounds(161, 11, 101, 30);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
		  		ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					if(passwordField.getText().compareTo(Start.getUsuario().getPass())==0 && passwordField_1.getText().compareTo(passwordField_2.getText())==0){
						facade.cambiarContra(Start.getUsuario(), passwordField_2.getText());
						dispose();
					}
				} catch (Exception e) {
						javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}					
			}
		});
		btnNewButton.setBounds(212, 209, 149, 30);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, -11, 392, 264);
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/localData/verfotos.jpg")));
		contentPane.add(lblNewLabel_1);
	}
}
