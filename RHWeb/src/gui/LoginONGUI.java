package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;

public class LoginONGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton btnLogout;
	private static JButton modOwner;
	private static JButton modPerfil;
	private static JTextPane txtpnPropietario;

	/**
	 * Create the panel.
	 */
	public LoginONGUI() {
		setBackground(new Color(0, 255, 0));
		setLayout(null);
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Start.logout();
					Start.setFotoDefecto();
					JPanel temp1 = new LoginGUI();
					Start.modificarPanelArriba(temp1);
					JPanel temp2 = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(temp2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}				
			}
		});
		btnLogout.setBounds(267, 18, 126, 23);
		add(btnLogout);
		
		modPerfil = new JButton("Editar Perfil");
		modPerfil.setBounds(5, 18, 126, 23);
		modPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JPanel temp1= new UserRegisterGUI(Start.getUsuario().getEmail());
					Start.modificarPanelAbajo(temp1);
				} catch (Exception e) {
					e.getMessage();
				}
				
			}
		});
		add(modPerfil);
		
		modOwner = new JButton("Ser Propietario");
		modOwner.setBounds(136, 18, 126, 23);
		modOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JPanel temp1= new OwnerRegisterGUI();
					Start.modificarPanelAbajo(temp1);
				} catch (Exception e) {
					e.getMessage();
				}
				
			}
		});
		add(modOwner);
		
		txtpnPropietario = new JTextPane();
		txtpnPropietario.setFont(new Font("Vijaya", Font.BOLD, 25));
		txtpnPropietario.setForeground(new Color(255, 0, 0));
		txtpnPropietario.setEditable(false);
		txtpnPropietario.setOpaque(false);
		txtpnPropietario.setText("Registrado como usuario.");
		txtpnPropietario.setBounds(5, 56, 388, 31);
		add(txtpnPropietario);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 400, 100);
		add(lblNewLabel);

		lblNewLabel.setBounds(0, 0, 400, 100);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/loginfondo.jpg")));
		lblNewLabel.setVisible(true);
		
		inicializarCampos();
		
	}
	
	public void inicializarCampos(){
		try {
			ApplicationFacadeInterface facade = Start.getBusinessLogic();
			modPerfil.setVisible(true);
			modOwner.setVisible(true);
			if(facade.getOwner(Start.getUsuario()) != null){
				modOwner.setText("Editar Propietario");
				txtpnPropietario.setText("Registrado como propietario.");
			}else{
				modOwner.setText("Ser Propietario");
				txtpnPropietario.setText("Registrado como usuario.");
			}
			Start.setFotoPerfil(facade.getFotoPerfil(facade.getUsuario(Start.getUsuario()).getEmail()));
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
