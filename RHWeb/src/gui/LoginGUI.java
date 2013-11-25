package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import businessLogic.ApplicationFacadeInterface;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textEmail;
	private JPasswordField passPass;
	private static JButton btnEntrar;
	private static JButton btnSignUp;
	/**
	 * Create the panel.
	 */
	public LoginGUI() {
		setForeground(new Color(240, 255, 255));
		//setBackground(new Color(0, 255, 0));
		setLayout(null);
		setOpaque(false);
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textEmail.getText();
				@SuppressWarnings("deprecation")
				String pass = passPass.getText();
				try {
					Start.hacerLogin(email, pass);
					JPanel temp = new LoginONGUI();
					Start.modificarPanelArriba(temp);
					JPanel temp1 = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(temp1);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
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
		textEmail.setBounds(28, 16, 219, 25);
		add(textEmail);
		textEmail.setColumns(10);
		
		passPass = new JPasswordField();
		passPass.setBounds(28, 57, 219, 25);
		add(passPass);
		btnEntrar.setBounds(275, 57, 97, 25);
		add(btnEntrar);
		
		btnSignUp = new JButton("Sign UP!");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanel temp = new UserRegisterGUI(textEmail.getText());
				Start.modificarPanelAbajo(temp);
			}
		});
		btnSignUp.setBounds(275, 16, 97, 25);
		add(btnSignUp);
		
		JLabel lblNewLabel_1 = new JLabel("Recuperar Contrase\u00F1a");
		lblNewLabel_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					facade.recuperarContrasena(textEmail.getText());
					javax.swing.JOptionPane.showMessageDialog(null,"Recibirás un email con la contraseña.", "Bien",javax.swing.JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(120, 86, 127, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 400, 100);
		add(lblNewLabel);

		lblNewLabel.setBounds(0, 0, 400, 100);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/loginfondo.jpg")));
		lblNewLabel.setVisible(true);
		
	}
}
