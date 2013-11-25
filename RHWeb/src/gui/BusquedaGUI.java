package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import domain.Offer;
import domain.RuralHouse;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JSpinner;

public class BusquedaGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton btnSalvar;
	private DefaultComboBoxModel<String> modeloCity = new DefaultComboBoxModel<String>();

	private JSpinner spinnerAparcamientos;
	private JSpinner spinnerSalas;
	private JSpinner spinnerHabitaciones;
	private JSpinner spinnerCocinas;
	private JSpinner spinnerBanos;
	
	private StarRater starRater;
	
	private JTable tableCasas;
	private JComboBox<String> comboCity;
	private DefaultTableModel modelTb = new DefaultTableModel(
			new Object[][] {
			},
			new Object[] {
				"nº", "Ciudad", "Baños", "Cocinas", "Salas", "Dormitorios", "Parkings"
			}
		);
	private JTable tableOfertas;
	private DefaultTableModel modelTbOfertas = new DefaultTableModel(
			new Object[][] {
			},
			new Object[] {
				/*"nº",*/ "FechaInicio", "FechaFin", "Precio"
			}
		);
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JTextPane textDescrp;
	private JLabel lblprop;
	private JLabel lblTelef;
	private JScrollPane scrollPaneOfer;
	private JButton button;
	private JButton button_1;
	private JButton btnNewButton_1;
	
	private SpinnerNumberModel modeloSpinnerBanos = new SpinnerNumberModel(2, 2, 40, 1);
	private SpinnerNumberModel modeloSpinnerCocinas = new SpinnerNumberModel(1, 1, 40, 1);
	private SpinnerNumberModel modeloSpinnerHabitaciones = new SpinnerNumberModel(3, 3, 40, 1);
	private SpinnerNumberModel modeloSpinnerSala = new SpinnerNumberModel(0, 0, 40, 1);
	private SpinnerNumberModel modeloSpinnerAparcamiento = new SpinnerNumberModel(0, 0, 40, 1);
	
	@SuppressWarnings("serial")
	public BusquedaGUI() {
		setLayout(null);
		
		btnSalvar = new JButton("Buscar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableCasas.setEnabled(false);
					String ciudad =(String) comboCity.getSelectedItem();
					actualizarTabla(ciudad,Integer.parseInt((String) spinnerBanos.getValue()),Integer.parseInt((String) spinnerHabitaciones.getValue()),Integer.parseInt((String) spinnerCocinas.getValue()),Integer.parseInt((String) spinnerSalas.getValue()),Integer.parseInt((String) spinnerAparcamientos.getValue()));
					button.setEnabled(false);
					button_1.setEnabled(false);
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					tableCasas.setEnabled(true);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
					System.out.println(e.getStackTrace());
				}
			}
		});
		btnSalvar.setForeground(Color.BLUE);
		btnSalvar.setFont(new Font("Dialog", Font.BOLD, 19));
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(201, 265, 163, 34);
		add(btnSalvar);
		
		tableCasas = new JTable(){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
		tableCasas.setModel(modelTb);
		tableCasas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCasas.setBounds(434, 38, 551, 229);
		ListSelectionModel cellSelectionModel = tableCasas.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        try {
		  		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		  		int selectedRow2 = tableCasas.getSelectedRow();				
		  		if(selectedRow2>=0){
			  		RuralHouse casita = facade.getCasas(Integer.parseInt((String) tableCasas.getValueAt(selectedRow2, 0)));
					lblprop.setText(casita.getUserAplication().getEmail());
					lblTelef.setText(casita.getUserAplication().getTelefono());
					textDescrp.setText(casita.getDescription());
					actualizarTablaOferta(Integer.parseInt((String) tableCasas.getValueAt(selectedRow2, 0)));
					starRater.setRating(casita.getNotaMedia());
					button.setEnabled(true);
					button_1.setEnabled(true);
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(true);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				javax.swing.JOptionPane.showMessageDialog(null,e1.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
			}
	      }

	    });
		add(tableCasas);
		
		scrollPane = new JScrollPane(tableCasas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(379, 13, 621, 239);
		add(scrollPane);
		
		tableOfertas = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
            return false;
        }};
        
        ListSelectionModel cellSelectionModel2 = tableOfertas.getSelectionModel();
	    cellSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel2.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
		  		if(Start.estadoLogin())
		  			btnNewButton.setEnabled(true);
		  		else
		  			btnNewButton.setEnabled(false);
	      }
	    });
		tableOfertas.setModel(modelTbOfertas);
		tableOfertas.setBounds(626, 282, 318, 66);
		add(tableOfertas);
		
		scrollPaneOfer = new JScrollPane(tableOfertas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneOfer.setBounds(379, 319, 423, 132);
		add(scrollPaneOfer);
		
		JLabel label_3 = new JLabel("Descripci\u00F3n:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(33, 310, 83, 34);
		add(label_3);
		
		btnNewButton = new JButton("Reservar\r\n");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					int x = (int) tableCasas.getSelectedRow();
					if (x ==-1) throw new Exception("Seleccione un día");
					Book reserv = facade.hacerReserva(Start.getUsuario(), Integer.parseInt((String) tableCasas.getValueAt(tableCasas.getSelectedRow(), 0)), (Date)tableOfertas.getValueAt(tableOfertas.getSelectedRow(), 0), (Date)tableOfertas.getValueAt(tableOfertas.getSelectedRow(), 1));
					if (javax.swing.JOptionPane.showConfirmDialog(null, "¿Quiere pagar ahora?", "Bien....", javax.swing.JOptionPane.YES_NO_OPTION) == 0){
						System.out.println(reserv.getNumeroDeReserva()+ " " + " " + Float.toString(reserv.getPrecio()));
						JPanel temp1 = new PagarGUI(reserv.getNumeroDeReserva(),reserv.getPrecio());
						Start.modificarPanelAbajo(temp1);
					}else{
						JPanel temp1 = new PantallaPrincipalGUI();
						Start.modificarPanelAbajo(temp1);
					}
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,"Selecciona una oferta", "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});	
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(835, 417, 163, 34);
		add(btnNewButton);
		
		textDescrp = new JTextPane();
		textDescrp.setOpaque(false);
		textDescrp.setEditable(false);
		textDescrp.setBounds(33, 346, 301, 105);
		textDescrp.setBorder(BorderFactory.createLoweredBevelBorder());
		add(textDescrp);
		
		JLabel lblNewLabel_1 = new JLabel("Propietario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(558, 278, 83, 21);
		add(lblNewLabel_1);
		
		lblprop = new JLabel("");
		lblprop.setForeground(new Color(0, 128, 0));
		lblprop.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblprop.setBorder(BorderFactory.createLoweredBevelBorder());
		lblprop.setBounds(644, 278, 158, 21);
		add(lblprop);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefono.setBounds(379, 278, 74, 21);
		add(lblTelefono);
		
		lblTelef = new JLabel("");
		lblTelef.setForeground(new Color(0, 128, 0));
		lblTelef.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelef.setBounds(463, 278, 85, 21);
		lblTelef.setBorder(BorderFactory.createLoweredBevelBorder());
		add(lblTelef);
		
		starRater = new StarRater(5, 2, 1);
		starRater.addStarListener(new StarRater.StarListener(){
			public void handleSelection(int selection) {
				System.out.println(selection);
			}});
	    starRater.setBounds(246, 319, 88, 21);
	    starRater.setEnabled(false);
	    add(starRater);
		
		JLabel label = new JLabel("N\u00BA min. aparcamientos*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label.setBounds(10, 225, 179, 27);
		add(label);
		
		spinnerAparcamientos = new JSpinner();
		spinnerAparcamientos.setModel(modeloSpinnerAparcamiento);
		spinnerAparcamientos.setBounds(201, 225, 67, 27);
		add(spinnerAparcamientos);
		
		spinnerSalas = new JSpinner();
		spinnerSalas.setModel(modeloSpinnerSala);
		spinnerSalas.setBounds(201, 182, 67, 27);
		add(spinnerSalas);
		
		JLabel label_1 = new JLabel("N\u00BA min. salas de estar*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_1.setBounds(23, 182, 166, 27);
		add(label_1);
		
		JLabel label_2 = new JLabel("N\u00BA min. habitaciones*:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_2.setBounds(33, 139, 156, 27);
		add(label_2);
		
		spinnerHabitaciones = new JSpinner();
		spinnerHabitaciones.setModel(modeloSpinnerHabitaciones);
		spinnerHabitaciones.setBounds(201, 139, 67, 27);
		add(spinnerHabitaciones);
		
		spinnerCocinas = new JSpinner();
		spinnerCocinas.setModel(modeloSpinnerCocinas);
		spinnerCocinas.setBounds(201, 96, 67, 27);
		add(spinnerCocinas);
		
		JLabel label_4 = new JLabel("N\u00BA min. cocinas*:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_4.setBounds(75, 96, 114, 27);
		add(label_4);
		
		JLabel label_5 = new JLabel("N\u00BA min. ba\u00F1os*:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_5.setBounds(75, 53, 114, 27);
		add(label_5);
		
		spinnerBanos = new JSpinner();
		spinnerBanos.setModel(modeloSpinnerBanos);
		spinnerBanos.setBounds(201, 55, 67, 27);
		add(spinnerBanos);
		
		comboCity = new JComboBox<String>();
		comboCity.setSelectedIndex(-1);
		comboCity.setModel(modeloCity);
		comboCity.setFont(new Font("Dialog", Font.BOLD, 11));
		comboCity.setBounds(201, 10, 149, 27);
		add(comboCity);
		
		JLabel label_6 = new JLabel("Ciudad*:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_6.setBounds(104, 10, 83, 27);
		add(label_6);
		
		button = new JButton("Ver imagenes");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					int x = (int) tableCasas.getSelectedRow();
					if (x ==-1) throw new Exception("Seleccione una fila de la tabla");
					VerFotos temp1 = new VerFotos(facade.getFotosRH(Integer.parseInt((String)tableCasas.getValueAt(tableCasas.getSelectedRow(), 0))));
					temp1.setVisible(true);
				} catch (Exception e2) {
					javax.swing.JOptionPane.showMessageDialog(null,e2.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Dialog", Font.BOLD, 19));
		button.setEnabled(false);
		button.setBounds(835, 319, 163, 34);
		add(button);
		
		button_1 = new JButton("Ver mapa");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					int x = (int) tableCasas.getSelectedRow();
					if (x ==-1) throw new Exception("Seleccione una fila de la tabla");
					Mapas temp1 = new Mapas(facade.getCasas(Integer.parseInt((String) tableCasas.getValueAt(tableCasas.getSelectedRow(), 0))).getCity());
					temp1.setVisible(true);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("Dialog", Font.BOLD, 19));
		button_1.setEnabled(false);
		button_1.setBounds(835, 368, 163, 34);
		add(button_1);
		
		JLabel lblCalificacin = new JLabel("Calificaci\u00F3n:");
		lblCalificacin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCalificacin.setBounds(151, 310, 83, 34);
		add(lblCalificacin);
		
		btnNewButton_1 = new JButton("Ver comentarios");
		btnNewButton_1.setBounds(835, 270, 163, 34);
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int x = (int) tableCasas.getSelectedRow();
					if (x ==-1) throw new Exception("Seleccione una fila de la tabla");
					VerComentariosGUI temp1 = new VerComentariosGUI(Integer.toString(Integer.parseInt((String)tableCasas.getValueAt(tableCasas.getSelectedRow(), 0))));
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
			btnSalvar.setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void actualizarTabla(String city, int banos, int habita, int cocina, int estar, int park ) throws Exception{
		
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			borrarTablaOferta();
			borrarTabla();
			Vector<RuralHouse> aux =  facade.getAllRuralHouses();
			Iterator<RuralHouse> i = aux.iterator();
			while (i.hasNext()){
				Vector<Object>  vector = new Vector<Object>();
				RuralHouse auxi = i.next();				
				boolean buscar = true;
				if (comboCity.getSelectedIndex() > -1 && ((String)comboCity.getSelectedItem()).compareToIgnoreCase(auxi.getCity())!=0) buscar = false;
				if (buscar && auxi.getPark()>=Integer.parseInt((String)spinnerAparcamientos.getValue()) &&
						auxi.getBaths()>=Integer.parseInt((String)spinnerBanos.getValue()) &&
								auxi.getKitchen()>=Integer.parseInt((String)spinnerCocinas.getValue()) &&
										auxi.getRooms()>=Integer.parseInt((String)spinnerHabitaciones.getValue()) &&
												auxi.getLiving()>=Integer.parseInt((String)spinnerSalas.getValue())){
					vector.add(auxi.getHouseNumber());
					vector.add(auxi.getCity());
					vector.add(auxi.getBaths());
					vector.add(auxi.getKitchen());
					vector.add(auxi.getLiving());
					vector.add(auxi.getRooms());
					vector.add(auxi.getPark());
					modelTb.addRow(vector);
				}			
			}
			ajustarColumnas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
	}
	
	private void actualizarTablaOferta(int num) throws Exception{
		
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			borrarTablaOferta();
			RuralHouse aux =  facade.getCasas(num);
			Vector<Offer> aux2= aux.getOfertas();
			Iterator<Offer> i = aux2.iterator();
			while (i.hasNext()){
				Vector<Object>  vector = new Vector<Object>();
				Offer auxi = i.next();
				if (!auxi.isReservado()){
					vector.add(auxi.getPrimerDia());
					vector.add(auxi.getUltimoDia());
					vector.add(auxi.getPrice());
					modelTbOfertas.addRow(vector);
				}
				
			}
			ajustarColumnas();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private void borrarTabla(){ while (modelTb.getRowCount() > 0){ System.out.println(modelTb.getRowCount());modelTb.removeRow(modelTb.getRowCount()-1);} }

	private void borrarTablaOferta(){ while (modelTbOfertas.getRowCount() > 0) modelTbOfertas.removeRow(modelTbOfertas.getRowCount()-1); }

	
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