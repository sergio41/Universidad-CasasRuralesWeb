package gui;
import java.util.Vector;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import domain.RuralHouse;

import businessLogic.ApplicationFacadeInterface;

public class VerComentariosGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	private DefaultComboBoxModel<Integer> modeloImg = new DefaultComboBoxModel<Integer>();
	private JTextPane txtpnComentar;
	private JLabel textField_2;
	private int num;
	private JComboBox<Integer> comboBox;
	private JLabel label_2;
	private JLabel label_1;
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerComentariosGUI frame = new VerComentariosGUI(args[0]);
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
	public VerComentariosGUI(String nRH) {
	    num = Integer.parseInt(nRH);
		setForeground(Color.YELLOW);
		setTitle("Mapa de la Casa Rural");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerComentariosGUI.class.getResource("/localData/iconMap.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 309);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setForeground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtpnComentar = new JTextPane();
		txtpnComentar.setForeground(new Color(50, 205, 50));
		txtpnComentar.setOpaque(false);
		//txtpnComentar.setForeground(new Color(255, 0, 0));
		//txtpnComentar.setBackground(new Color(0, 206, 209));
		txtpnComentar.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		txtpnComentar.setText("Comentarios:");
		txtpnComentar.setBounds(187, 0, 140, 31);
		contentPane.add(txtpnComentar);
		
		textField_2 = new JLabel();
		textField_2.setBounds(60, 116, 385, 107);
		textField_2.setOpaque(false);
		textField_2.setBorder(BorderFactory.createLoweredBevelBorder());
		contentPane.add(textField_2);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setBounds(185, 72, 140, 20);
		comboBox.setModel(modeloImg);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					RuralHouse casita = facade.getCasas((num));
					Vector<String> comentarios = casita.getComentarios();
					textField_2.setText(comentarios.get(comboBox.getSelectedIndex()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});	
		contentPane.add(comboBox);
		
		label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (comboBox.getSelectedIndex() !=0) comboBox.setSelectedIndex(comboBox.getSelectedIndex()-1);
			}
		});
		label_2.setIcon(new ImageIcon(VerFotos.class.getResource("/localData/flechaIzq.png")));
		label_2.setBounds(83, 54, 50, 50);
		contentPane.add(label_2);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (comboBox.getSelectedIndex() != comboBox.getItemCount()-1) comboBox.setSelectedIndex(comboBox.getSelectedIndex()+1);
			}
		});
		label_1.setIcon(new ImageIcon(VerFotos.class.getResource("/localData/flechaDer.png")));
		label_1.setBounds(378, 54, 50, 50);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 506, 600);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/verfotos.jpg")));
		contentPane.add(lblNewLabel);
		
		inicializarCampos();
	}
	
	public void inicializarCampos(){
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			RuralHouse casita = facade.getCasas((num));
			Vector<String> comentarios = casita.getComentarios();
			if(comentarios.size()>0){
				for(int i =0; i<comentarios.size(); i++)
				modeloImg.addElement(i+1);
				comboBox.setSelectedIndex(0);
				textField_2.setText(comentarios.get(0));
			}else{
				textField_2.setText("No hay comentarios todavía");
			}			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
