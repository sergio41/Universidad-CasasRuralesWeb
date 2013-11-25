package gui;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class Mapas extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBox;
	private JSlider slider;
	
	private String center = "SanchoElSabio19,Gasteiz";
	private int zoomI = 15;
	private String size = "500x500";
	private String maptype = "roadmap";
	private String sensor = "false";
	private String marker = "icon:http://montesinos.wikispaces.com/file/view/kfm_home(2).png/73196529/43x43/kfm_home(2).png%7csize:mid%7Ccolor:0xFFFF00%7Clabel:C%7C";
	private JLabel lblMapa;
	private JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mapas frame = new Mapas(args[0]);
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
	public Mapas(String direccion) {
		setResizable(false);
		center="";
		/*Sergio
			center = direccion.replaceAll(" ", "");
		 */
		for (int x=0; x < direccion.length(); x++) if (direccion.charAt(x) != ' ') center += direccion.charAt(x);
		setForeground(Color.YELLOW);
		setTitle("Mapa de la Casa Rural");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mapas.class.getResource("/localData/iconMap.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 628);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setForeground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMapa = new JLabel("New label");
		lblMapa.setBorder(BorderFactory.createLoweredBevelBorder());
		lblMapa.setBounds(12, 81, 494, 500);
		contentPane.add(lblMapa);
		
		comboBox = new JComboBox<String>();
		modeloEC.addElement("roadmap");
		modeloEC.addElement("satellite");
		modeloEC.addElement("terrain");
		modeloEC.addElement("hybrid");
		comboBox.setModel(modeloEC);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().compareTo(maptype) != 0) {
					maptype = comboBox.getSelectedItem().toString();
					crearMapa();
				}
			}
		});
		comboBox.setBounds(12, 37, 174, 31);
		contentPane.add(comboBox);
		
		slider = new JSlider();
		slider.setOpaque(false);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				zoomI = slider.getValue();
				crearMapa();
			}
		});
		slider.setValue(15);
		slider.setMaximum(21);
		slider.setBounds(293, 37, 174, 31);
		contentPane.add(slider);
		
		JTextPane txtpnEligeTipoDe = new JTextPane();
		txtpnEligeTipoDe.setForeground(new Color(0, 255, 0));
		txtpnEligeTipoDe.setOpaque(false);
		txtpnEligeTipoDe.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		txtpnEligeTipoDe.setText("Elige tipo de mapa y el zoom:");
		txtpnEligeTipoDe.setBounds(89, 0, 344, 31);
		contentPane.add(txtpnEligeTipoDe);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 527, 600);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/verfotos.jpg")));
		contentPane.add(lblNewLabel);
	}
	
	private void crearMapa(){
		String aux = "http://maps.googleapis.com/maps/api/staticmap?";
		aux = aux.concat("center=" + center);
		aux = aux.concat("&zoom=" + Integer.toString(zoomI));
		aux = aux.concat("&size=" + size);
		aux = aux.concat("&maptype=" + maptype);
		aux = aux.concat("&sensor=" + sensor);
		aux = aux.concat("&markers=" + marker + center);
		System.out.println("hola ." + aux);
		ponerMapa(aux);
	}
	
	private void ponerMapa(String sURL){
		try {
			BufferedImage imgBufferedImage = ImageIO.read(new URL(sURL));
			lblMapa.setIcon(new ImageIcon(imgBufferedImage));
		} catch (Exception ex) {
			System.out.println("Error!" + ex);
		}
	}
}