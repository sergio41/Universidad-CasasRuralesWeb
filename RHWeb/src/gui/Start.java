package gui;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.rmi.Naming;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import configuration.Config;
import java.awt.Color;
import javax.swing.JTextPane;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

import domain.UserAplication;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Start extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	public static boolean isLocal=true;
	public static ApplicationFacadeInterface facadeInterface;
	public static JPanel panelArriba;
	public static JPanel panelPrincipal;
	private static JLabel lblNewLabel_1;
	private static Vector<String> vectorTwitter;
	private static int intTwitter;
	private static JTextPane textTwitter;
	private static ImageIcon logo;
	
	private static UserAplication usuario = null;
	private static String user = "";
	private static String passw = "";
	public static ApplicationFacadeInterface getBusinessLogic(){
		return facadeInterface;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new PantallaCargandoLanzadora();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			Config c=Config.getInstance();
			if (isLocal)
				facadeInterface=new FacadeImplementation();
			else {
				
				final String serverRMI = c.getServerRMI();
				// Remote service name
				String serviceName = "/"+c.getServiceRMI();
				//System.setSecurityManager(new RMISecurityManager());
				// RMI server port number
				int portNumber = Integer.parseInt(c.getPortRMI());
				// RMI server host IP IP 
				facadeInterface = (ApplicationFacadeInterface) Naming.lookup("rmi://"
					+ serverRMI + ":" + portNumber + serviceName);
			} 
			
			
		} catch (Exception e) {
		//System.out.println(e.toString());
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					Start frame = new Start();
					frame.setVisible(true);
					panelPrincipal = new PantallaPrincipalGUI();
					modificarPanelAbajo(panelPrincipal);
					panelArriba = new LoginGUI();
					modificarPanelArriba(panelArriba);
					controlTiempo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		setTitle("Casas Rurales Villatripas de Arriba");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Start.class.getResource("/localData/logo100x100.png")));
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 590);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("Villatripas de Arriba");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(102, 204, 51));
		lblTitulo.setFont(new Font("Viner Hand ITC", Font.BOLD, 32));
		lblTitulo.setBounds(63, 0, 443, 50);
		contentPane.add(lblTitulo);
		
		JLabel lblTwitter = new JLabel("");
		lblTwitter.setBounds(0, 50, 50, 50);
		lblTwitter.setIcon(new ImageIcon(getClass().getResource("/localData/twitter50x50.png")));
		contentPane.add(lblTwitter);
		
		textTwitter = new JTextPane();
		textTwitter.setForeground(new Color(0, 255, 255));
		textTwitter.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		textTwitter.setText("Error al procesar twitter");
		textTwitter.setOpaque(false);
		textTwitter.setEditable(false);
		textTwitter.setBounds(63, 39, 443, 61);
		
		contentPane.add(textTwitter);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Start.class.getResource("/localData/home50x50.png")));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				JPanel panel = new PantallaPrincipalGUI();
				modificarPanelAbajo(panel);
			}
		});
		lblNewLabel_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setBounds(0, 0, 50, 50);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(518, 0, 100, 100);
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/localData/logo100x100.png")));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 618, 100);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoArriba.jpg")));
		contentPane.add(lblNewLabel);
		
		logo = new ImageIcon(this.getClass().getResource("/localData/logo100x100.png"));
		
	}

	public static void modificarPanelAbajo(JPanel panel){
		panelPrincipal.setVisible(false);
		contentPane.remove(panelPrincipal);
		panel.setBounds(0, 100, 1018, 465);
		panelPrincipal = panel;
		contentPane.add(panelPrincipal);
		panelPrincipal.setVisible(true);
	}
	
	public static void modificarPanelArriba(JPanel panel){
		panelArriba.setVisible(false);
		contentPane.remove(panelArriba);
		panel.setBounds(618, 0, 400, 100);
		panelArriba = panel;
		contentPane.add(panelArriba);
		panelArriba.setVisible(true);
	}
	
	public static void controlTiempo(){
		TimerTask timerTask = new TimerTask() { 
			public void run() {
				try {
					intTwitter++;
					if (vectorTwitter == null || intTwitter == 10 || vectorTwitter.size()<intTwitter){
						vectorTwitter = facadeInterface.Ultimos10Tweets();
						intTwitter =0;
					}
					textTwitter.setText(vectorTwitter.get(intTwitter));
				} catch (Exception e) {
					textTwitter.setText("Error al procesar twitter");
				}
			} 
		}; 
		Timer timer = new Timer(); 
		timer.scheduleAtFixedRate(timerTask, 0, 15000);
	}
	
	
	public static void setFotoDefecto(){
		lblNewLabel_1.setIcon(logo);
	}
	
	public static void setFotoPerfil(ImageIcon perfil){
		if (perfil == null) setFotoDefecto();
		else {
	        Image aux1 = perfil.getImage().getScaledInstance(lblNewLabel_1.getHeight(), lblNewLabel_1.getWidth(), java.awt.Image.SCALE_SMOOTH);
	        lblNewLabel_1.setIcon(new ImageIcon(aux1));
		}
	}
	
	public static UserAplication getUsuario() throws Exception{
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		usuario = facade.hacerLogin(user, passw);
		return usuario;
		
	}
	
	public static UserAplication getUsuario1() throws Exception{
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		usuario = facade.getUsuario(usuario);
		return usuario;
		
	}
	
	public static void setpasswd(String pass){
		passw=pass;
	}
	
	public static boolean estadoLogin(){
		return (usuario!=null);
	}
	
	public static void logout(){
		usuario=null;
	}
	
	public static void hacerLogin(String email, String pass) throws Exception{
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		usuario = facade.hacerLogin(email, pass);
		if (pass != null || pass.compareTo("")!=0) user = email;
		if (pass != null || pass.compareTo("")!=0) passw = pass; 
	}
}
