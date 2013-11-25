package gui;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;

import domain.Book;

import externalDataSend.EnviarCorreo;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PagarGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldInput;
	private JRadioButton rdbtnTransferencia;
	private JRadioButton rdbtnTarjetaDeCredito;
	private JTextPane txtpnSeCaducaEn;
	private JTextPane txtpnMes;
	private JTextPane txtpnAo;
	private JFormattedTextField textMes;
	private JFormattedTextField textAno;
	private JFormattedTextField textNumeroTarjeta;
	private JTextPane txtpnNumeroDeTarjeta;
	private JTextPane textTitular;
	private JButton btnPagar;
	private JTextPane textNumero;
	private JTextPane textImporte;
	public PagarGUI(int num, float precio) {
		setLayout(null);
		
		JTextPane txtpnPasarelaDePago = new JTextPane();
		txtpnPasarelaDePago.setFont(new Font("Times New Roman", Font.BOLD, 35));
		txtpnPasarelaDePago.setForeground(new Color(50, 205, 50));
		txtpnPasarelaDePago.setOpaque(false);
		txtpnPasarelaDePago.setText("PASARELA DE PAGO:");
		txtpnPasarelaDePago.setBounds(292, 13, 372, 56);
		add(txtpnPasarelaDePago);
		
		JTextPane txtpnReservaNumero = new JTextPane();
		txtpnReservaNumero.setText("Reserva numero:");
		txtpnReservaNumero.setOpaque(false);
		txtpnReservaNumero.setForeground(new Color(0, 0, 255));
		txtpnReservaNumero.setFont(new Font("Times New Roman", Font.BOLD, 25));
		txtpnReservaNumero.setBounds(60, 84, 202, 38);
		add(txtpnReservaNumero);
		
		JTextPane txtpnImporte = new JTextPane();
		txtpnImporte.setText("Importe: ");
		txtpnImporte.setOpaque(false);
		txtpnImporte.setForeground(new Color(0, 0, 255));
		txtpnImporte.setFont(new Font("Times New Roman", Font.BOLD, 24));
		txtpnImporte.setBounds(154, 135, 108, 38);
		add(txtpnImporte);
		
		JTextPane txtpnComoDeseaPagar = new JTextPane();
		txtpnComoDeseaPagar.setText("Como desea pagar:");
		txtpnComoDeseaPagar.setOpaque(false);
		txtpnComoDeseaPagar.setForeground(new Color(0, 0, 255));
		txtpnComoDeseaPagar.setFont(new Font("Times New Roman", Font.BOLD, 24));
		txtpnComoDeseaPagar.setBounds(51, 195, 211, 38);
		add(txtpnComoDeseaPagar);
		
		rdbtnTransferencia = new JRadioButton("Transferencia");
		rdbtnTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnTarjetaDeCredito.setSelected(!rdbtnTransferencia.isSelected());
				enaDis(!rdbtnTransferencia.isSelected());
			}
		});
		rdbtnTransferencia.setOpaque(false);
		rdbtnTransferencia.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnTransferencia.setBounds(315, 208, 127, 25);
		add(rdbtnTransferencia);
		
		rdbtnTarjetaDeCredito = new JRadioButton("Tarjeta de credito");
		rdbtnTarjetaDeCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTransferencia.setSelected(!rdbtnTarjetaDeCredito.isSelected());
				enaDis(rdbtnTarjetaDeCredito.isSelected());
			}
		});
		rdbtnTarjetaDeCredito.setOpaque(false);
		rdbtnTarjetaDeCredito.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnTarjetaDeCredito.setBounds(498, 208, 188, 25);
		add(rdbtnTarjetaDeCredito);
		
		txtpnNumeroDeTarjeta = new JTextPane();
		txtpnNumeroDeTarjeta.setText("Numero de tarjeta:");
		txtpnNumeroDeTarjeta.setOpaque(false);
		txtpnNumeroDeTarjeta.setForeground(new Color(0, 0, 255));
		txtpnNumeroDeTarjeta.setFont(new Font("Times New Roman", Font.BOLD, 24));
		txtpnNumeroDeTarjeta.setBounds(51, 335, 211, 38);
		add(txtpnNumeroDeTarjeta);
		
		txtpnSeCaducaEn = new JTextPane();
		txtpnSeCaducaEn.setText("Se caduca en:");
		txtpnSeCaducaEn.setOpaque(false);
		txtpnSeCaducaEn.setForeground(new Color(0, 0, 255));
		txtpnSeCaducaEn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		txtpnSeCaducaEn.setBounds(561, 262, 170, 38);
		add(txtpnSeCaducaEn);
		
		MaskFormatter modeloCB = null;
		try {
			modeloCB = new MaskFormatter("####-####-####-####");
			modeloCB.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textNumeroTarjeta = new JFormattedTextField(modeloCB);
		textNumeroTarjeta.setBounds(273, 335, 170, 34);
		add(textNumeroTarjeta);
		
		txtpnMes = new JTextPane();
		txtpnMes.setText("Mes:");
		txtpnMes.setOpaque(false);
		txtpnMes.setForeground(Color.BLUE);
		txtpnMes.setFont(new Font("Times New Roman", Font.BOLD, 24));
		txtpnMes.setBounds(583, 317, 68, 38);
		add(txtpnMes);
		
		txtpnAo = new JTextPane();
		txtpnAo.setText("A\u00F1o:");
		txtpnAo.setOpaque(false);
		txtpnAo.setForeground(Color.BLUE);
		txtpnAo.setFont(new Font("Times New Roman", Font.BOLD, 24));
		txtpnAo.setBounds(583, 364, 68, 38);
		add(txtpnAo);
		
		MaskFormatter modeloMes = null;
		try {
			modeloCB = new MaskFormatter("##");
			modeloCB.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textMes = new JFormattedTextField((modeloMes));
		textMes.setBounds(663, 317, 62, 34);
		add(textMes);
		
		MaskFormatter modeloAno = null;
		try {
			modeloCB = new MaskFormatter("####");
			modeloCB.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textAno = new JFormattedTextField(modeloAno);
		textAno.setBounds(663, 364, 62, 34);
		add(textAno);
		
		textTitular = new JTextPane();
		textTitular.setText("Titular:");
		textTitular.setOpaque(false);
		textTitular.setForeground(Color.BLUE);
		textTitular.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textTitular.setBounds(166, 262, 95, 38);
		add(textTitular);
		
		textFieldInput = new JTextField();
		textFieldInput.setBounds(273, 262, 258, 34);
		add(textFieldInput);
		textFieldInput.setColumns(10);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					if(rdbtnTransferencia.isSelected()){
						Book reserva= facade.pagar(Integer.parseInt(textNumero.getText()), Start.getUsuario());
						EnviarCorreo.enviarCorreos(Start.getUsuario().getEmail(), "Numero de cuenta para transferencia", "Hola "+ Start.getUsuario().getName()+"! Aqui le enviamos el nº de Cuenta del propietario de la casa rural en la que usted esta pagando la reserva.<br/> El numero de cuenta es: "+ reserva.getCasa().getUserAplication().getPropietario().getBankAccount());
						javax.swing.JOptionPane.showMessageDialog(null,"Se le ha enviado un email con el numero de cuenta a la que deberá reslizar la transferencia.", "Bien bien....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
						JPanel aux = new PantallaPrincipalGUI();
						Start.modificarPanelAbajo(aux);
					}else{ 
						if(textTitular.getText()!="" && (textNumeroTarjeta.isEditValid() && textNumeroTarjeta.getText().compareTo("____-____-____-____")!=0)
								&& !textAno.getText().isEmpty() && !textMes.getText().isEmpty()){
							facade.pagar(Integer.parseInt(textNumero.getText()), Start.getUsuario());
							javax.swing.JOptionPane.showMessageDialog(null,"Pago realizado", "Bien bien....",javax.swing.JOptionPane.INFORMATION_MESSAGE);					
							JPanel aux = new PantallaPrincipalGUI();
							Start.modificarPanelAbajo(aux);
						}else javax.swing.JOptionPane.showMessageDialog(null,"Faltan campos por rellenar", "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					javax.swing.JOptionPane.showMessageDialog(null,e1.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPagar.setForeground(Color.BLUE);
		btnPagar.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnPagar.setBounds(856, 403, 124, 45);
		add(btnPagar);
		
		textNumero = new JTextPane();
		textNumero.setText(Integer.toString(num));
		textNumero.setOpaque(false);
		textNumero.setForeground(Color.BLUE);
		textNumero.setFont(new Font("Times New Roman", Font.BOLD, 25));
		textNumero.setBounds(274, 84, 202, 38);
		add(textNumero);
		
		textImporte = new JTextPane();
		textImporte.setText(Float.toString(precio));
		textImporte.setOpaque(false);
		textImporte.setForeground(Color.BLUE);
		textImporte.setFont(new Font("Times New Roman", Font.BOLD, 25));
		textImporte.setBounds(274, 135, 202, 38);
		add(textImporte);
	    
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		rdbtnTransferencia.setSelected(true);
		enaDis(false);
	}
	
	private void enaDis(boolean b){
		textFieldInput.setVisible(b);
		txtpnSeCaducaEn.setVisible(b);
		txtpnMes.setVisible(b);
		txtpnAo.setVisible(b);
		textMes.setVisible(b);
		textAno.setVisible(b);
		textNumeroTarjeta.setVisible(b);
		txtpnNumeroDeTarjeta.setVisible(b);
		textTitular.setVisible(b);
		//btnPagar.setVisible(b);
	}
}
