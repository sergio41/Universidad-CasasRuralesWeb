package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import businessLogic.ApplicationFacadeInterface;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import domain.RuralHouse;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class PantallaPrincipalGUI extends JPanel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JDateChooser dateDesde;
	private static JDateChooser dateHasta;
	private static JButton btnGestionCasasRurales;
	private static JButton btnGestionOfertas;
	private static JButton btnGestionFechas;
	private static JComboBox<String> comboCity ;
	private DefaultComboBoxModel<String> modeloCity = new DefaultComboBoxModel<String>();
	private JTextPane numeroRH;
	private JButton btnGestReserv;
	
	/**
	 * Create the panel.
	 */
	public PantallaPrincipalGUI(){
		setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboCity.getSelectedIndex() != -1){
					JPanel panel = new BusquedaConFechasGUI(dateDesde.getDate(), dateHasta.getDate(), (String)comboCity.getSelectedItem());
					Start.modificarPanelAbajo(panel);
				}else{
					
				}
			}
		});
		btnBuscar.setBounds(20, 179, 223, 28);
		add(btnBuscar);
		
		JButton btnBusquedaAvanzada = new JButton("Busqueda de chollos");
		btnBusquedaAvanzada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new BusquedaGUI();
				Start.modificarPanelAbajo(panel);
			}
		});
		btnBusquedaAvanzada.setBounds(20, 223, 223, 28);
		add(btnBusquedaAvanzada);
		
		Date fechaHoy = new Date();
		long time = fechaHoy.getTime() + 1*(3600*24*1000);
		Date fechaManana = new Date();
		fechaManana.setTime(time);
		
		dateHasta = new JDateChooser();
		dateHasta.setMinSelectableDate(fechaManana);
		dateHasta.setDate(fechaManana);
		dateHasta.setDateFormatString("yyyy-MM-dd");
		dateHasta.setBounds(105, 138, 137, 28);
		add(dateHasta);
	
		dateDesde = new JDateChooser();
		dateDesde.setBounds(105, 97, 137, 28);
		dateDesde.setDate(fechaHoy);
		dateDesde.setDateFormatString("yyyy-MM-dd");
		dateDesde.setMinSelectableDate(fechaHoy);
		dateDesde.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
					long time = dateDesde.getDate().getTime() + 1*(3600*24*1000);
					Date fechaSiguiente = new Date();
					fechaSiguiente.setTime(time);
					dateHasta.setMinSelectableDate(fechaSiguiente);
					dateHasta.setDate(fechaSiguiente);
	            }
	        }
	    });
		add(dateDesde);

		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(20, 138, 73, 28);
		lblHasta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHasta.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		add(lblHasta);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(20, 97, 73, 28);
		lblDesde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesde.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		add(lblDesde);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(20, 57, 73, 28);
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCiudad.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		add(lblCiudad);
		
		JLabel lblSeleccioneLosDatos = DefaultComponentFactory.getInstance().createTitle("Seleccione los datos:");
		lblSeleccioneLosDatos.setForeground(new Color(255, 0, 0));
		lblSeleccioneLosDatos.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		lblSeleccioneLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneLosDatos.setBounds(20, 23, 223, 22);
		add(lblSeleccioneLosDatos);
		
		btnGestionCasasRurales = new JButton("Gestionar Casas Rurales");
		btnGestionCasasRurales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new GestionCasaRuralGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestionCasasRurales.setBounds(783, 138, 223, 28);
		add(btnGestionCasasRurales);
		
		btnGestionFechas = new JButton("Gestionar Disponibilidad");
		btnGestionFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new AnadirFechaGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestionFechas.setBounds(783, 97, 223, 28);
		add(btnGestionFechas);
		
		btnGestionOfertas = new JButton("Gestionar Ofertas");
		btnGestionOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new CreateOfferGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestionOfertas.setBounds(783, 179, 223, 28);
		add(btnGestionOfertas);
		
		btnGestReserv = new JButton("Gestionar Reservas Realizadas");
		btnGestReserv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new GestionarReservasGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestReserv.setBounds(783, 223, 223, 28);
		add(btnGestReserv);
		
		JButton button = new JButton("Busqueda avanzada");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new BusquedaConFechasGUI(null, null, null);
				Start.modificarPanelAbajo(panel);
			}
		});
		button.setBounds(20, 264, 223, 28);
		add(button);
		
		comboCity = new JComboBox<String>();
		comboCity.setSelectedIndex(-1);
		comboCity.setFont(new Font("Dialog", Font.BOLD, 11));
		comboCity.setBounds(105, 57, 137, 27);
		comboCity.setModel(modeloCity);
		add(comboCity);
		
		numeroRH = new JTextPane();
		numeroRH.setEditable(false);
		numeroRH.setForeground(new Color(51, 255, 51));
		numeroRH.setFont(new Font("Viner Hand ITC", Font.BOLD, 17));
		numeroRH.setText("dasfsdfvdgadfghrbdfh");
		numeroRH.setBounds(783, 23, 223, 120);
		numeroRH.setOpaque(false);
		add(numeroRH);
		
		JLabel lblTwitter = new JLabel("Twitter");
		lblTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(java.awt.Desktop.isDesktopSupported()){
					 try{
					      Desktop dk = Desktop.getDesktop();
					      dk.browse(new URI("https://twitter.com/CasasVillaArrib"));
					 }catch(Exception e){
						 System.out.println("Error al abrir URL: "+e.getMessage());
					 }
				}  
			}
		});
		lblTwitter.setBounds(58, 300, 150, 150);
		Image aux2 = (new ImageIcon(PantallaPrincipalGUI.class.getResource("/localData/twitter.png"))).getImage();
		Image aux1 = aux2.getScaledInstance(lblTwitter.getHeight(), lblTwitter.getWidth(), java.awt.Image.SCALE_SMOOTH);
		lblTwitter.setIcon(new ImageIcon(aux1));
		lblTwitter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(lblTwitter);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(281, 0, 490, 465);
		Image aux3 = (new ImageIcon(PantallaPrincipalGUI.class.getResource("/localData/papiro1.png"))).getImage();
		Image aux4 = aux3.getScaledInstance(lblNewLabel_1.getHeight(), lblNewLabel_1.getWidth(), java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(aux4));
		
		add(lblNewLabel_1);
		
		JLabel label = new JLabel("Twitter");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(java.awt.Desktop.isDesktopSupported()){
					 try{
					      Desktop dk = Desktop.getDesktop();
					      dk.browse(new URI("https://twitter.com/CasasVillaArrib"));
					 }catch(Exception e1){
						 System.out.println("Error al abrir URL: "+e1.getMessage());
					 }
				} 
			}
		});
		label.setBounds(819, 300, 150, 150);
		Image aux5 = (new ImageIcon(PantallaPrincipalGUI.class.getResource("/localData/twitter.png"))).getImage();
		Image aux6 = aux5.getScaledInstance(label.getHeight(), label.getWidth(), java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(aux6));
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(label);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1018, 465);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		add(lblNewLabel);
		
		
		inicializarCampos();
	}
	
	public void inicializarCampos(){
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		boolean login = false;
		boolean reserv = false;
		try {
			Vector<RuralHouse> vectorCasas = facade.getAllRuralHouses();
			numeroRH.setText("Se han registrado un total de " + vectorCasas.size() + " casas rurales");
			for(int i=0; i<vectorCasas.size();i++){
				if(!estaCity(vectorCasas.get(i).getCity())){
					modeloCity.addElement(vectorCasas.get(i).getCity());
				}
				
			}
			comboCity.setSelectedIndex(-1);
			if (Start.estadoLogin()){  
				if(facade.getOwner(Start.getUsuario())!=null)
					login = true;
				if(facade.getUsuario(Start.getUsuario()).getReservas().size()>0) reserv=true;
				else reserv=false;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		btnGestionCasasRurales.setVisible(login);
		btnGestionOfertas.setVisible(login);
		btnGestionFechas.setVisible(login);
		btnGestReserv.setVisible(reserv);
		
	}
	
	public boolean estaCity(String s){
		for (int j = 0; j<modeloCity.getSize();j++){
			if(modeloCity.getElementAt(j).compareTo(s)==0){
				return true;
			}
		}
		return false;		
	}
}

