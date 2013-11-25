package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VerFotos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Vector<Image> images;
	private JComboBox<Integer> comboBox;
	private DefaultComboBoxModel<Integer> modeloCombo = new DefaultComboBoxModel<Integer>();
	private JLabel label_1 = new JLabel("");
	private JLabel label_2 = new JLabel("");
	//private ImageIcon imagenDefecto = new ImageIcon(verFotos.class.getResource("/localData/casaDefault.png"));
	private JLabel lblNewLabel = new JLabel();
	private JLabel lblNewLabel_1 = new JLabel();
	private Image aux = (new ImageIcon(VerFotos.class.getResource("/localData/casaDefault.png"))).getImage();
	/**
	 * Launch the application.
	 */
	public static void main( String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerFotos frame = new VerFotos(null);
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
	public VerFotos(Vector<ImageIcon> imagenes) {
		setResizable(false);
		setBackground(new Color(0, 206, 209));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerFotos.class.getResource("/localData/casaDefault.png")));
		setTitle("Ver fotos Casa Rural");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 497);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<Integer>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarImagen(comboBox.getSelectedIndex());
			}
		});
		comboBox.setModel(modeloCombo);
		comboBox.setSelectedIndex(-1);
		comboBox.setEnabled(false);
		comboBox.setBounds(137, 25, 157, 23);
		contentPane.add(comboBox);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (comboBox.getSelectedIndex() != comboBox.getItemCount()-1) comboBox.setSelectedIndex(comboBox.getSelectedIndex()+1);
			}
		});
		label_1.setIcon(new ImageIcon(VerFotos.class.getResource("/localData/flechaDer.png")));
		label_1.setBounds(338, 13, 50, 50);
		contentPane.add(label_1);
		
		label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (comboBox.getSelectedIndex() !=0) comboBox.setSelectedIndex(comboBox.getSelectedIndex()-1);
			}
		});
		label_2.setIcon(new ImageIcon(VerFotos.class.getResource("/localData/flechaIzq.png")));
		label_2.setBounds(43, 13, 50, 50);
		contentPane.add(label_2);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		lblNewLabel.setBounds(89, 107, 250, 250);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, -11, 474, 480);
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/localData/verfotos.jpg")));
		contentPane.add(lblNewLabel_1);

		images = new Vector<Image>();
		for (int i = 0; i <imagenes.size(); i++) images.add(imagenes.get(i).getImage());
		cargarImagen(0);
		rellenarComboBox();
	}
	
	public  void cargarImagen(int numero){
		Image aux2 = aux;
		if (images != null) {
			if (images != null && numero >= images.size()) numero = images.size();
			if (images != null && images.size() != 0)aux2 = images.elementAt(numero);
		}
		if (lblNewLabel.getHeight() != 0 && lblNewLabel.getWidth()!= 0) {
			Image aux1 = aux2.getScaledInstance(lblNewLabel.getHeight(), lblNewLabel.getWidth(), java.awt.Image.SCALE_SMOOTH);
			lblNewLabel.setIcon(new ImageIcon(aux1));
		}
	}
	
	public  void rellenarComboBox(){
		modeloCombo.removeAllElements();
		if (images == null || images.size() == 0){
			comboBox.setEnabled(false);
			label_1.setEnabled(false);
			label_2.setEnabled(false);
		} else { 
			for (int i = 1; i<=images.size(); i++) modeloCombo.addElement(i);
			comboBox.setEnabled(true);
			label_1.setEnabled(true);
			label_2.setEnabled(true);
		}
	}
}
