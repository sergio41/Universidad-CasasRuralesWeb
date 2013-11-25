package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterface;
import domain.Book;
import domain.RuralHouse;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;

public class BusquedaConFechasGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton btnSalvar;
	private DefaultComboBoxModel<String> modeloCity = new DefaultComboBoxModel<String>();
	private SpinnerNumberModel modeloSpinnerBanos = new SpinnerNumberModel(2, 2, 40, 1);
	private SpinnerNumberModel modeloSpinnerCocinas = new SpinnerNumberModel(1, 1, 40, 1);
	private SpinnerNumberModel modeloSpinnerHabitaciones = new SpinnerNumberModel(3, 3, 40, 1);
	private SpinnerNumberModel modeloSpinnerSala = new SpinnerNumberModel(0, 0, 40, 1);
	private SpinnerNumberModel modeloSpinnerAparcamiento = new SpinnerNumberModel(0, 0, 40, 1);
	private JComboBox<String> comboCity;
	private JTable tableCasas;
	
	private JSpinner spinnerAparcamientos;
	private JSpinner spinnerSalas;
	private JSpinner spinnerHabitaciones;
	private JSpinner spinnerCocinas;
	private JSpinner spinnerBanos;
	
	private Vector<RuralHouse> vectorCasa = new Vector<RuralHouse>();
	private Date daInicio = new Date(), daFin = new Date();
	private DefaultTableModel modelTb = new DefaultTableModel(
			new Object[][] {
			},
			new Object[] {
				"nº", "Ciudad", "Baños", "Cocinas", "Salas", "Dormitorios", "Parkings"
			}
		);
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JTextPane textDescrp;
	private JLabel lblprop;
	private JLabel lblTelef;
	private JDateChooser dateHasta;
	private JDateChooser dateInicio;
	private JButton btnImg;
	private JButton buttonMapa;
	private JButton btnNewButton_1;
	
	@SuppressWarnings("serial")
	public BusquedaConFechasGUI(Date inicio, Date fin, String ciudad) {
		setLayout(null);
		
		JLabel lblNMinHabitaciones = new JLabel("N\u00BA min. habitaciones*:");
		lblNMinHabitaciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinHabitaciones.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinHabitaciones.setBounds(23, 138, 156, 27);
		add(lblNMinHabitaciones);
		
		JLabel lblNMinSalas = new JLabel("N\u00BA min. salas de estar*:");
		lblNMinSalas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinSalas.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinSalas.setBounds(13, 181, 166, 27);
		add(lblNMinSalas);
		
		JLabel lblNMinaparcamientos = new JLabel("N\u00BA min. aparcamientos*:");
		lblNMinaparcamientos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinaparcamientos.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinaparcamientos.setBounds(0, 224, 179, 27);
		add(lblNMinaparcamientos);
		
		JLabel lblNMinCocinas = new JLabel("N\u00BA min. cocinas*:");
		lblNMinCocinas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinCocinas.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinCocinas.setBounds(65, 95, 114, 27);
		add(lblNMinCocinas);
		
		JLabel lblNMinBaos = new JLabel("N\u00BA min. ba\u00F1os*:");
		lblNMinBaos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinBaos.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinBaos.setBounds(65, 52, 114, 27);
		add(lblNMinBaos);
		
		JLabel label_2 = new JLabel("Ciudad*:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_2.setBounds(94, 9, 83, 27);
		add(label_2);
		
		btnSalvar = new JButton("Buscar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					vectorCasa = new Vector<RuralHouse>();
					daInicio = dateInicio.getDate();
					daFin = dateHasta.getDate();
					Vector<RuralHouse> aux = facade.casasRuralesDisponibles(dateHasta.getDate(), dateHasta.getDate());
					Iterator<RuralHouse> i = aux.iterator();
					while (i.hasNext()){
						RuralHouse casa = i.next();
						boolean buscar = true;
						if (comboCity.getSelectedIndex() > -1 && ((String)comboCity.getSelectedItem()).compareToIgnoreCase(casa.getCity())!=0) buscar = false;
						if (buscar && casa.getPark()>=Integer.valueOf(spinnerAparcamientos.getValue().toString()) &&
								casa.getBaths()>=Integer.valueOf(spinnerBanos.getValue().toString()) &&
								casa.getKitchen()>=Integer.valueOf(spinnerCocinas.getValue().toString()) &&
								casa.getRooms()>=Integer.valueOf(spinnerHabitaciones.getValue().toString()) &&
								casa.getLiving()>=Integer.valueOf(spinnerSalas.getValue().toString()))vectorCasa.add(casa);
						}
					actualizarTabla();
					btnImg.setEnabled(false);
					btnNewButton.setEnabled(false);
					buttonMapa.setEnabled(false);
					btnNewButton_1.setEnabled(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnSalvar.setForeground(Color.BLUE);
		btnSalvar.setFont(new Font("Dialog", Font.BOLD, 19));
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(191, 353, 149, 34);
		add(btnSalvar);
		
		
		comboCity = new JComboBox<String>();
		comboCity.setFont(new Font("Dialog", Font.BOLD, 11));
		comboCity.setBounds(191, 9, 149, 27);
		comboCity.setModel(modeloCity);
		add(comboCity);
		
		tableCasas = new JTable(){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
		tableCasas.setModel(modelTb);
		tableCasas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCasas.setBounds(434, 38, 551, 229);
		ListSelectionModel cellSelectionModel = tableCasas.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        try {
		  		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		  		int selectedRow2 = tableCasas.getSelectedRow();
				RuralHouse casita = facade.getCasas(Integer.valueOf(tableCasas.getValueAt(selectedRow2, 0).toString()));
				lblprop.setText(casita.getUserAplication().getEmail());
				lblTelef.setText(casita.getUserAplication().getTelefono());
				textDescrp.setText(casita.getDescription());
				btnImg.setEnabled(true);
				if(Start.estadoLogin())
					btnNewButton.setEnabled(true);
				else
		  			btnNewButton.setEnabled(false);
				buttonMapa.setEnabled(true);
				btnNewButton_1.setEnabled(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
	      }

	    });
		add(tableCasas);
		
		
		scrollPane = new JScrollPane(tableCasas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(373, 9, 600, 250);
		add(scrollPane);
		
		JLabel label_3 = new JLabel("Descripci\u00F3n:");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_3.setBounds(373, 272, 114, 27);
		add(label_3);
		
		btnNewButton = new JButton("Reservar\r\n");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					System.out.println(daInicio.toString() + "   " + daFin.toString());
					Book reserv = facade.hacerReserva(Start.getUsuario(), Integer.valueOf((String) tableCasas.getValueAt(tableCasas.getSelectedRow(), 0)), daInicio, daFin);
					System.out.println("A");
					if (javax.swing.JOptionPane.showConfirmDialog(null, "¿Quiere pagar ahora?", "Bien....", javax.swing.JOptionPane.YES_NO_OPTION) == 0){
						System.out.println("B");
						System.out.println(reserv.getNumeroDeReserva()+ " " + " " + Float.toString(reserv.getPrecio()));
						JPanel temp1 = new PagarGUI(reserv.getNumeroDeReserva(),reserv.getPrecio());
						Start.modificarPanelAbajo(temp1);
					}else{
						JPanel temp1 = new PantallaPrincipalGUI();
						Start.modificarPanelAbajo(temp1);
					}
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		btnNewButton.setBounds(781, 419, 163, 34);
		btnNewButton.setEnabled(false);
		add(btnNewButton);
		
		textDescrp = new JTextPane();
		textDescrp.setBounds(377, 312, 301, 94);
		textDescrp.setBorder(BorderFactory.createLoweredBevelBorder());
		textDescrp.setOpaque(false);
		textDescrp.setEditable(false);
		add(textDescrp);
		
		JLabel lblNewLabel_1 = new JLabel("Propietario:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(689, 312, 83, 27);
		add(lblNewLabel_1);
		
		lblprop = new JLabel("");
		lblprop.setForeground(new Color(0, 128, 0));
		lblprop.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblprop.setBorder(BorderFactory.createLoweredBevelBorder());
		lblprop.setBounds(775, 312, 169, 27);
		add(lblprop);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTelefono.setBounds(689, 379, 74, 27);
		add(lblTelefono);
		
		lblTelef = new JLabel("");
		lblTelef.setForeground(new Color(0, 128, 0));
		lblTelef.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelef.setBorder(BorderFactory.createLoweredBevelBorder());
		lblTelef.setBounds(775, 379, 169, 27);
		add(lblTelef);
		
		btnImg = new JButton("Ver imagenes");
		btnImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					int x = Integer.valueOf(tableCasas.getSelectedRow());
					if (x ==-1) throw new Exception("Seleccione una fila de la tabla");
					VerFotos temp1 = new VerFotos(facade.getFotosRH(Integer.valueOf((String)tableCasas.getValueAt(tableCasas.getSelectedRow(), 0))));
					temp1.setVisible(true);
				} catch (Exception e2) {
					javax.swing.JOptionPane.showMessageDialog(null,e2.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnImg.setEnabled(false);
		btnImg.setForeground(Color.BLUE);
		btnImg.setFont(new Font("Dialog", Font.BOLD, 19));
		btnImg.setBounds(309, 419, 163, 34);
		add(btnImg);
		
		Date fechaHoy = new Date();
		long time = fechaHoy.getTime() + 1*(3600*24*1000);
		Date fechaManana = new Date();
		fechaManana.setTime(time);
		
		dateInicio = new JDateChooser();
		dateInicio.setEnabled(true);
		dateInicio.setDate(fechaHoy);
		dateInicio.setMinSelectableDate(fechaHoy);
		dateInicio.setDateFormatString("yyyy-MM-dd");
		dateInicio.setBounds(191, 267, 149, 27);
		dateInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
					long time = dateInicio.getDate().getTime() + 1*(3600*24*1000);
					Date fechaSiguiente = new Date();
					fechaSiguiente.setTime(time);
					dateHasta.setMinSelectableDate(fechaSiguiente);
					dateHasta.setDate(fechaSiguiente);
	            }
	        }
	    });
		add(dateInicio);
		
		dateHasta = new JDateChooser();
		dateHasta.setMinSelectableDate(fechaManana);
		dateHasta.setEnabled(true);
		dateHasta.setDateFormatString("yyyy-MM-dd");
		dateHasta.setBounds(191, 303, 149, 27);
		add(dateHasta);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFechaInicio.setBounds(94, 267, 96, 27);
		add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFechaFin.setBounds(107, 303, 83, 27);
		add(lblFechaFin);
		
		buttonMapa = new JButton("Ver mapa");
		buttonMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					int x = Integer.valueOf(tableCasas.getSelectedRow());
					if (x ==-1) throw new Exception("Seleccione una fila de la tabla");
					Mapas temp1 = new Mapas(facade.getCasas(Integer.parseInt((String) tableCasas.getValueAt(tableCasas.getSelectedRow(), 0))).getCity());
					temp1.setVisible(true);
				} catch (Exception e2) {
					javax.swing.JOptionPane.showMessageDialog(null,e2.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonMapa.setEnabled(false);
		buttonMapa.setForeground(Color.BLUE);
		buttonMapa.setFont(new Font("Dialog", Font.BOLD, 19));
		buttonMapa.setBounds(545, 419, 163, 34);
		add(buttonMapa);
		
		 spinnerBanos = new JSpinner();
		spinnerBanos.setModel(modeloSpinnerBanos);
		spinnerBanos.setBounds(191, 54, 67, 27);
		add(spinnerBanos);
		
		spinnerCocinas = new JSpinner();
		spinnerCocinas.setModel(modeloSpinnerCocinas);
		spinnerCocinas.setBounds(191, 95, 67, 27);
		add(spinnerCocinas);
		
		spinnerHabitaciones = new JSpinner();
		spinnerHabitaciones.setBounds(191, 138, 67, 27);
		spinnerHabitaciones.setModel(modeloSpinnerHabitaciones);
		add(spinnerHabitaciones);
		
		spinnerSalas = new JSpinner();
		spinnerSalas.setModel(modeloSpinnerSala);
		spinnerSalas.setBounds(191, 181, 67, 27);
		add(spinnerSalas);
		
		spinnerAparcamientos = new JSpinner();
		spinnerAparcamientos.setModel(modeloSpinnerAparcamiento);
		spinnerAparcamientos.setBounds(191, 224, 67, 27);
		add(spinnerAparcamientos);
		
		btnNewButton_1 = new JButton("Ver comentarios");
		btnNewButton_1.setBounds(65, 419, 163, 34);
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int x = Integer.valueOf(tableCasas.getSelectedRow());
					if (x ==-1) throw new Exception("Seleccione una fila de la tabla");
					VerComentariosGUI temp1 = new VerComentariosGUI(Integer.toString(Integer.valueOf((String)tableCasas.getValueAt(tableCasas.getSelectedRow(), 0))));
					temp1.setVisible(true);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}	
			}			
		});
		btnNewButton_1.setEnabled(false);
		add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
		
		if (ciudad != null)	comboCity.setSelectedItem(ciudad);
		if (inicio != null) dateInicio.setDate(inicio);
		if (fin != null) dateHasta.setDate(fin);
		
	}


	private void inicializarCampos() {
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			Vector<RuralHouse> vectorCasas = facade.getAllRuralHouses();
			for(int i=0; i<vectorCasas.size();i++){
				if(!estaCity(vectorCasas.get(i).getCity())){
					modeloCity.addElement(vectorCasas.get(i).getCity());
				}
			}
			
			if(Start.estadoLogin())
				btnNewButton.setEnabled(true);
			else
				btnNewButton.setEnabled(false);
			comboCity.setSelectedIndex(-1);
			btnSalvar.setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void actualizarTabla(){
		try {
			borrarTabla();
			Iterator<RuralHouse> i = vectorCasa.iterator();
			while (i.hasNext()){
				Vector<Object>  vector = new Vector<Object>();
				RuralHouse auxi = i.next();
				vector.add(auxi.getHouseNumber());
				vector.add(auxi.getCity());
				vector.add(auxi.getBaths());
				vector.add(auxi.getKitchen());
				vector.add(auxi.getLiving());
				vector.add(auxi.getRooms());
				vector.add(auxi.getPark());
				modelTb.addRow(vector);
			}
			ajustarColumnas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	
	
	private void borrarTabla(){ while (modelTb.getRowCount() > 0) modelTb.removeRow(modelTb.getRowCount()-1); }

	
	private void ajustarColumnas(){
		int anchoTabla = 551;
		int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
		TableColumn columnaTabla = null;
		for(int i=0; i<tableCasas.getColumnCount(); i++) {
			columnaTabla = tableCasas.getColumnModel().getColumn(i);
			switch(i) {
				case 0: anchoColumna = (5*anchoTabla)/100;
				anchoColumnaMin = (5*anchoTabla)/100;
				anchoColumnaMax = (5*anchoTabla)/100;
				break;
				case 1: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 2: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
				case 3: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
				case 4: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
				case 5: anchoColumna = (16*anchoTabla)/100;
				anchoColumnaMin = (16*anchoTabla)/100;
				anchoColumnaMax = (16*anchoTabla)/100;
				break;
				case 6: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
			}
			columnaTabla.setPreferredWidth(anchoColumna);
			columnaTabla.setMinWidth(anchoColumnaMin);
			columnaTabla.setMaxWidth(anchoColumnaMax);
			}
		}
	
	public DefaultComboBoxModel<String> getModeloEC() {
		return modeloEC;
	}

	public void setModeloEC(DefaultComboBoxModel<String> modeloEC) {
		this.modeloEC = modeloEC;
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
