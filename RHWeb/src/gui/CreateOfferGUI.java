package gui;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.toedter.calendar.JDateChooser;

import domain.*;
import businessLogic.*;

import java.awt.event.*;
import java.beans.*;
import java.util.*;

public class CreateOfferGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> comBCasas;
	private DefaultComboBoxModel<Integer> modeloEC = new DefaultComboBoxModel<Integer>();
	private JComboBox<String> comBObligatorio;
	private DefaultComboBoxModel<String> modeloOB = new DefaultComboBoxModel<String>();
	private JDateChooser calendarFirstday;
	private JDateChooser calendarLastday;
	private JButton bttnAnadir;
	private JButton btnEliminar = new JButton();
	private JTextField textPrecio;
	private JTable table;
	private DefaultTableModel modelTb = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nº", "Inicio", "Fin", "Precio", "Oblig.", "Reser."
			}
		);
	private JScrollPane scrollPane;

	public CreateOfferGUI(){		
		setLayout(null);
		JLabel ruralhouselbl = new JLabel("Casa rural: ");
		ruralhouselbl.setBounds(30, 110, 126, 22);
		ruralhouselbl.setForeground(Color.BLUE);
		ruralhouselbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		add(ruralhouselbl);
		
		comBCasas = new JComboBox<Integer>();
		comBCasas.setBounds(196, 110, 149, 22);
		comBCasas.setModel(modeloEC);
		comBCasas.setSelectedIndex(-1);
		comBCasas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBCasas.getSelectedIndex() != -1){
					enaDis(true);
					actualizarTabla(Integer.parseInt(comBCasas.getSelectedItem().toString()));
				} else borrarTabla();
			}
		});
		add(comBCasas);
		
		
		Date fechaHoy = new Date();
		long time = fechaHoy.getTime() + 1*(3600*24*1000);
		Date fechaManana = new Date();
		fechaManana.setTime(time);
		
		calendarLastday = new JDateChooser();
		calendarLastday.setMinSelectableDate(fechaManana);
		calendarLastday.setDate(fechaManana);
		calendarLastday.setDateFormatString("yyyy-MM-dd");
		calendarLastday.setBounds(196, 186, 149, 22);
		calendarLastday.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
		    @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
	            }
	        }
	    });
		add(calendarLastday);
	
		calendarFirstday = new JDateChooser();
		calendarFirstday.setBounds(196, 145, 149, 22);
		calendarFirstday.setDate(fechaHoy);
		calendarFirstday.setDateFormatString("yyyy-MM-dd");
		calendarFirstday.setMinSelectableDate(fechaHoy);
		try {
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		calendarFirstday.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @SuppressWarnings("deprecation")
			@Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
					long time = calendarFirstday.getDate().getTime() + 1*(3600*24*1000);
					Date fechaSiguiente = new Date();
					fechaSiguiente.setTime(time);
					System.out.println(fechaSiguiente.getDate());
					calendarLastday.setMinSelectableDate(fechaSiguiente);
					calendarLastday.setDate(fechaSiguiente);
	            }
	        }
	    });
		add(calendarFirstday);	
				
		bttnAnadir = new JButton("A\u00F1adir");
		bttnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					System.out.println(calendarFirstday.getDate());
					System.out.println(calendarLastday.getDate());
					boolean aux = true;
					if (comBObligatorio.getSelectedIndex() == 0) aux = false;
					facade.anadirOferta(Start.getUsuario(), Integer.parseInt(comBCasas.getSelectedItem().toString()), calendarFirstday.getDate(), calendarLastday.getDate(), Float.parseFloat(textPrecio.getText()), aux);
					table.removeAll();
					JPanel panel = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(panel);
				} catch (Exception e1) {
					javax.swing.JOptionPane.showMessageDialog(null,"Error al crear oferta. " + e1.getMessage(), "No....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		bttnAnadir.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		bttnAnadir.setForeground(Color.BLUE);
		bttnAnadir.setBounds(196, 306, 149, 46);
		add(bttnAnadir);
		
		JLabel firstdaylbl = new JLabel("Primer d\u00EDa:");
		firstdaylbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		firstdaylbl.setForeground(Color.BLUE);
		firstdaylbl.setBounds(30, 145, 110, 22);
		add(firstdaylbl);
		
		JLabel lastdaylbl = new JLabel("\u00DAltimo d\u00EDa:");
		lastdaylbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lastdaylbl.setForeground(Color.BLUE);
		lastdaylbl.setBounds(30, 186, 110, 22);
		add(lastdaylbl);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setForeground(Color.BLUE);
		lblPrecio.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblPrecio.setBounds(30, 219, 110, 22);
		add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(196, 219, 149, 22);
		textPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if (car == '.');
				else if((car<'0' || car>'9')) evt.consume();
			}
		});
		add(textPrecio);
		
		table = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
		table.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				btnEliminar.setEnabled(true);
			}
		});
		table.setModel(modelTb);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(402, 35, 551, 317);
		add(table);
		
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(402, 35, 551, 317);
		add(scrollPane);
		
		JLabel lblAadirNuevaOferta = new JLabel("A\u00F1adir nueva oferta:");
		lblAadirNuevaOferta.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
		lblAadirNuevaOferta.setForeground(new Color(0, 255, 0));
		lblAadirNuevaOferta.setBounds(30, 35, 315, 51);
		add(lblAadirNuevaOferta);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBCasas.getSelectedIndex() >= 0) {
					ApplicationFacadeInterface facade=Start.getBusinessLogic();
					try {						
						Vector<Offer> aux1 =  facade.getOfertas(Start.getUsuario(), Integer.parseInt(comBCasas.getSelectedItem().toString()));
						int x = Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0));
						Date ini = aux1.get(x).getPrimerDia();
						Date fin = aux1.get(x).getUltimoDia();
						facade.eliminarOferta(Start.getUsuario(), Integer.parseInt(comBCasas.getSelectedItem().toString()), ini, fin);						
						System.out.print("1");
						JPanel panel = new PantallaPrincipalGUI();
						Start.modificarPanelAbajo(panel);
						javax.swing.JOptionPane.showMessageDialog(null,"Se ha eliminado la oferta", "Bien....",javax.swing.JOptionPane.INFORMATION_MESSAGE);	
					}catch (Exception e) {
						javax.swing.JOptionPane.showMessageDialog(null,"Error al eliminar: " + e.getMessage(), "No....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
					} 
					
				}
			}
		});
		btnEliminar.setForeground(Color.BLUE);
		btnEliminar.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(804, 374, 149, 46);
		add(btnEliminar);
		
		comBObligatorio = new JComboBox<String>();
		comBObligatorio.setModel(modeloOB);
		comBObligatorio.setSelectedIndex(-1);
		comBObligatorio.setBounds(30, 262, 315, 31);
		modeloOB.addElement("Esta oferta se puede reservar por dias. *");
		modeloOB.addElement("Esta oferta no se puede dividir. *");
		add(comBObligatorio);
		
		JTextPane txtpnLasOfertasPueden = new JTextPane();
		txtpnLasOfertasPueden.setForeground(new Color(255, 0, 0));
		txtpnLasOfertasPueden.setEditable(false);
		txtpnLasOfertasPueden.setBorder(BorderFactory.createLoweredBevelBorder());
		txtpnLasOfertasPueden.setFont(new Font("Vani", Font.BOLD, 14));
		txtpnLasOfertasPueden.setText("Las ofertas pueden ser de dos tipos: La oferta puede ser completa, es decir, no se puede reservar por dias. El otro tipo es lo contrario. Se puede reservar por dias (fracciones de la oferta).");
		txtpnLasOfertasPueden.setBounds(30, 376, 762, 44);
		add(txtpnLasOfertasPueden);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
	}

	private void inicializarCampos() {
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		java.util.Iterator<RuralHouse> i;
		try {
			i = facade.getOwner(Start.getUsuario()).getRuralHouses().iterator();
			while (i.hasNext())	modeloEC.addElement(i.next().getHouseNumber());
			comBCasas.setSelectedIndex(-1);
			enaDis(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enaDis(boolean b){
		textPrecio.setEnabled(b);
		calendarFirstday.setEnabled(b);
		calendarLastday.setEnabled(b);
		bttnAnadir.setEnabled(b);
		table.setEnabled(b);
		comBObligatorio.setEnabled(b);
	}
	
	private void actualizarTabla(int numeroRH){
	
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			borrarTabla();
			Vector<Offer> aux =  facade.getOfertas(Start.getUsuario(), numeroRH);
			Iterator<Offer> i = aux.iterator();
			while (i.hasNext()){
				Vector<Object> vector = new Vector<Object>();
				Offer auxi = i.next();
				vector.add(modelTb.getRowCount());
				vector.add(auxi.getPrimerDia());
				vector.add(auxi.getUltimoDia());
				vector.add(auxi.getPrice());
				if(auxi.isUnidoAFechas())
					vector.add("Si");
				else	
					vector.add("No");
				if(auxi.isReservado())
					vector.add("Si");
				else
					vector.add("No");
				modelTb.addRow(vector);
			}
			ajustarColumnas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void borrarTabla(){ while (modelTb.getRowCount() > 0) modelTb.removeRow(modelTb.getRowCount()-1); }
	
	private void ajustarColumnas(){
		int anchoTabla = 551;
		int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
		TableColumn columnaTabla = null;
		for(int i=0; i<table.getColumnCount(); i++) {
			columnaTabla = table.getColumnModel().getColumn(i);
			switch(i) {
				case 0: anchoColumna = (5*anchoTabla)/100;
				anchoColumnaMin = (5*anchoTabla)/100;
				anchoColumnaMax = (5*anchoTabla)/100;
				break;
				case 1: anchoColumna = (32*anchoTabla)/100;
				anchoColumnaMin = (32*anchoTabla)/100;
				anchoColumnaMax = (32*anchoTabla)/100;
				break;
				case 2: anchoColumna = (32*anchoTabla)/100;
				anchoColumnaMin = (32*anchoTabla)/100;
				anchoColumnaMax = (32*anchoTabla)/100;
				break;
				case 3: anchoColumna = (11*anchoTabla)/100;
				anchoColumnaMin = (11*anchoTabla)/100;
				anchoColumnaMax = (11*anchoTabla)/100;
				break;
				case 4: anchoColumna = (10*anchoTabla)/100;
				anchoColumnaMin = (10*anchoTabla)/100;
				anchoColumnaMax = (10*anchoTabla)/100;
				break;
				case 5: anchoColumna = (10*anchoTabla)/100;
				anchoColumnaMin = (10*anchoTabla)/100;
				anchoColumnaMax = (10*anchoTabla)/100;
				break;
			}
			columnaTabla.setPreferredWidth(anchoColumna);
			columnaTabla.setMinWidth(anchoColumnaMin);
			columnaTabla.setMaxWidth(anchoColumnaMax);
			}
		}
	}
