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
import javax.swing.JButton;
import javax.swing.JTextField;

import domain.RuralHouse;

import businessLogic.ApplicationFacadeInterface;

public class ComentariosGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	private DefaultComboBoxModel<Integer> modeloImg = new DefaultComboBoxModel<Integer>();
	private StarRater starRater;
	private JButton btnNewButton;
	private JTextPane txtpnComentar;
	private JTextField textField;
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
					ComentariosGUI frame = new ComentariosGUI(args[0]);
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
	public ComentariosGUI(String nRH) {
	
	    num = Integer.parseInt(nRH);
		setForeground(Color.YELLOW);
		setTitle("Mapa de la Casa Rural");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ComentariosGUI.class.getResource("/localData/iconMap.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 628);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setForeground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		starRater = new StarRater(5, 2, 1);
		starRater.addStarListener(new StarRater.StarListener(){
			public void handleSelection(int selection) {
				System.out.println(selection);
				starRater.setRating(starRater.getSelection());
			}});
		starRater.setBounds(247, 200, 80, 16);
	    starRater.setEnabled(true);
	    contentPane.add(starRater);
		
		txtpnComentar = new JTextPane();
		txtpnComentar.setEditable(false);
		txtpnComentar.setForeground(new Color(50, 205, 50));
		txtpnComentar.setOpaque(false);
		txtpnComentar.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		txtpnComentar.setText("Comentarios:");
		txtpnComentar.setBounds(187, 0, 140, 31);
		contentPane.add(txtpnComentar);
		
		btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					facade.anadirCalificacionACasaRural(num, textField.getText(), starRater.getSelection());
					dispose();
				} catch (Exception e1) {
					javax.swing.JOptionPane.showMessageDialog(null,e1.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(356, 200, 140, 31);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(10, 69, 486, 112);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnOtrosHanComentado = new JTextPane();
		txtpnOtrosHanComentado.setText("Otros han comentado:");
		txtpnOtrosHanComentado.setEditable(false);
		txtpnOtrosHanComentado.setForeground(Color.RED);
		txtpnOtrosHanComentado.setOpaque(false);
		txtpnOtrosHanComentado.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		txtpnOtrosHanComentado.setBackground(new Color(0, 206, 209));
		txtpnOtrosHanComentado.setBounds(10, 291, 273, 31);
		contentPane.add(txtpnOtrosHanComentado);
		
		textField_2 = new JLabel();
		textField_2.setBounds(62, 422, 385, 112);
		textField_2.setOpaque(false);
		textField_2.setBorder(BorderFactory.createLoweredBevelBorder());
		contentPane.add(textField_2);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setBounds(187, 378, 140, 20);
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
		label_2.setBounds(85, 360, 50, 50);
		contentPane.add(label_2);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (comboBox.getSelectedIndex() != comboBox.getItemCount()-1) comboBox.setSelectedIndex(comboBox.getSelectedIndex()+1);
			}
		});
		label_1.setIcon(new ImageIcon(VerFotos.class.getResource("/localData/flechaDer.png")));
		label_1.setBounds(380, 360, 50, 50);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 527, 600);
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
				starRater.setRating(casita.getNotaMedia());
				textField_2.setText(comentarios.get(0));
			}else{
				starRater.setRating(3);
				textField_2.setText("No hay comentarios todavía");
			}			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
