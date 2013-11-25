package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;

import domain.Owner;
import javax.swing.JFormattedTextField;

public class OwnerRegisterGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField textProfesion;
	private static JTextField textIdioma3;
	private static JTextField textIdioma1;
	private static JTextField textIdioma2;
	private static JTextField textIdioma4;
	private static JButton buttonGuardar;
	
	private static JComboBox<String> comboMoneda;
	private static JRadioButton rdbtnProfesional = new JRadioButton("Profesional");
	private static JRadioButton rdbtnParticular = new JRadioButton("Particular");
	private static DefaultComboBoxModel<String> modeloMon = new DefaultComboBoxModel<String>();
	private static JFormattedTextField textCuentaBancaria;
	
	/**
	 * Create the panel.
	 */
	public OwnerRegisterGUI(){
		setLayout(null);
		buttonGuardar = new JButton("Registrar");
		buttonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				String cuenta= "";
				String tipo = "";
				String profes= "";
				String mon="";
				Vector<String> idiom = new Vector<String>();
				if(!textCuentaBancaria.isEditValid() || textCuentaBancaria.getText().compareTo("____-____-__-__________")==0 ||
						(!rdbtnParticular.isSelected()&&!rdbtnProfesional.isSelected())|| 
						(textIdioma1.getText().compareTo("")==0 && textIdioma2.getText().compareTo("")==0&&
						 textIdioma3.getText().compareTo("")==0 && textIdioma4.getText().compareTo("")==0)||
						 textProfesion.getText().compareTo("")==0 || comboMoneda.getSelectedIndex()<1){
					javax.swing.JOptionPane.showMessageDialog(null, "Algunos datos obligatorios faltan!", "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				}else{
					cuenta= textCuentaBancaria.getText();
					if(rdbtnParticular.isSelected()) tipo= "Particular";				
					else if(rdbtnProfesional.isSelected()) tipo= "Profesional";	
					idiom.add(textIdioma1.getText());
					idiom.add(textIdioma2.getText());
					idiom.add(textIdioma3.getText());
					idiom.add(textIdioma4.getText());
					profes = textProfesion.getText();
					mon= (String) comboMoneda.getSelectedItem();
	
					try {
						facade.modificarOwner(Start.getUsuario(), cuenta, tipo, idiom, profes, mon);
						JPanel temp1= new PantallaPrincipalGUI();
						Start.modificarPanelAbajo(temp1);
						JPanel temp2 = new LoginONGUI();
						Start.modificarPanelArriba(temp2);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				
			}
		});
		buttonGuardar.setForeground(Color.BLUE);
		buttonGuardar.setFont(new Font("Dialog", Font.PLAIN, 21));
		buttonGuardar.setBounds(650, 394, 124, 45);
		add(buttonGuardar);
		
		JLabel label = new JLabel("Los campos marcados con * son obligatorios");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.RED);
		label.setFont(new Font("Dialog", Font.PLAIN, 18));
		label.setBounds(272, 400, 366, 34);
		add(label);
		
		comboMoneda = new JComboBox<String>();
		comboMoneda.setBounds(360, 333, 109, 34);
		comboMoneda.setModel(modeloMon);
		modeloMon.removeAllElements();
		modeloMon.addElement("");
		modeloMon.addElement("€");
		modeloMon.addElement("$");
		add(comboMoneda);
		
		
		JLabel label_1 = new JLabel("Moneda:*");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_1.setBounds(153, 333, 161, 34);
		add(label_1);
		
		JLabel label_2 = new JLabel("Profesi\u00F3n:*");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_2.setBounds(153, 272, 161, 34);
		add(label_2);
		
		textProfesion = new JTextField();
		textProfesion.setColumns(10);
		textProfesion.setBounds(360, 272, 414, 34);
		add(textProfesion);
		
		textIdioma3 = new JTextField();
		textIdioma3.setColumns(10);
		textIdioma3.setBounds(360, 211, 161, 34);
		add(textIdioma3);
		
		textIdioma1 = new JTextField();
		textIdioma1.setColumns(10);
		textIdioma1.setBounds(360, 150, 161, 34);
		add(textIdioma1);
		
		textIdioma2 = new JTextField();
		textIdioma2.setColumns(10);
		textIdioma2.setBounds(613, 150, 161, 34);
		add(textIdioma2);
		
		textIdioma4 = new JTextField();
		textIdioma4.setColumns(10);
		textIdioma4.setBounds(613, 211, 161, 34);
		add(textIdioma4);
		
		rdbtnProfesional = new JRadioButton("Profesional");
		rdbtnProfesional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnParticular.setSelected(!rdbtnProfesional.isSelected());
			}
		});
		rdbtnProfesional.setOpaque(false);
		rdbtnProfesional.setBounds(613, 89, 127, 34);
		add(rdbtnProfesional);
		
		rdbtnParticular = new JRadioButton("Particular");
		rdbtnParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnProfesional.setSelected(!rdbtnParticular.isSelected());
			}
		});
		rdbtnParticular.setOpaque(false);
		rdbtnParticular.setBounds(360, 82, 127, 34);
		add(rdbtnParticular);
		
		JLabel label_3 = new JLabel("Usted es:*");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_3.setBounds(153, 82, 161, 34);
		add(label_3);
		
		JLabel label_4 = new JLabel("Idiomas hablados:*");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_4.setBounds(102, 150, 212, 34);
		add(label_4);
		
		JLabel label_5 = new JLabel("Cuenta bancar\u00EDa:*");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_5.setBounds(94, 25, 220, 34);
		add(label_5);
		
		MaskFormatter modeloCB = null;
		try {
			modeloCB = new MaskFormatter("####-####-##-##########");
			modeloCB.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textCuentaBancaria = new JFormattedTextField(modeloCB);
		textCuentaBancaria.setBounds(360, 25, 414, 34);
		add(textCuentaBancaria);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();

	}
	
	@SuppressWarnings("deprecation")
	private void inicializarCampos(){
		Owner ow;
		try {
			
			ow = (Start.getUsuario().getPropietario());
			if (ow!=null){
				textCuentaBancaria.enable(false);
				textCuentaBancaria.setText(ow.getBankAccount());
				System.out.println(ow.getTipo());
				if(ow.getTipo().compareTo("Particular")==0){
					System.out.println(ow.getTipo());
					rdbtnProfesional.setSelected(false);
					rdbtnParticular.setSelected(true);
				}else if(ow.getTipo().compareTo("Profesional")==0){
					System.out.println(ow.getTipo());
					rdbtnProfesional.setSelected(true);
					rdbtnParticular.setSelected(false);
				}
				System.out.println(ow.getTipo());
				textIdioma1.setText(ow.getIdiomas().elementAt(0));
				textIdioma2.setText(ow.getIdiomas().elementAt(1));
				textIdioma3.setText(ow.getIdiomas().elementAt(2));
				textIdioma4.setText(ow.getIdiomas().elementAt(3));
				textProfesion.setText(ow.getProfesion());
				comboMoneda.setSelectedItem(ow.getMoneda());
				buttonGuardar.setText("Guardar");
			} else {
				textCuentaBancaria.enable(true);
				textCuentaBancaria.setText("");
				rdbtnProfesional.setSelected(false);
				rdbtnParticular.setSelected(false);
				textIdioma1.setText("");
				textIdioma2.setText("");
				textIdioma3.setText("");
				textIdioma4.setText("");
				textProfesion.setText("");
				comboMoneda.setSelectedIndex(0);
				buttonGuardar.setText("Registrar");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
