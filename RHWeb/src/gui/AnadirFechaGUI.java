package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import businessLogic.ApplicationFacadeInterface;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JDateChooser;
import domain.Fechas;
import domain.RuralHouse;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class AnadirFechaGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableCasas;
	private JScrollPane scrollPane;
	private JDateChooser dateChooserFin;
	private JDateChooser dateChooserIni;
	private JCheckBox chckbxfechaFinalizacin;
	private JTextField textDia;
	private JLabel lblFechaFin;
	private JSpinner spinnerDias;
	private JComboBox<String> comBoxCasas;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton buttonEliminar;
	private JButton btnGuardar;
	private DefaultTableModel modelTb= new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fecha", "Precio", "MinDias", "Oferta", "Obli.Oferta" , "Reservado"
			}
		);
	
	@SuppressWarnings("serial")
	public AnadirFechaGUI() {
		setLayout(null);
		
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

	      }

	    });
		add(tableCasas);
		
		scrollPane = new JScrollPane(tableCasas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(377, 68, 590, 325);
		add(scrollPane);
		
		Date fechaHoy = new Date();
		long time = fechaHoy.getTime() + 1*(3600*24*1000);
		Date fechaManana = new Date();
		fechaManana.setTime(time);
		
		dateChooserIni = new JDateChooser();
		dateChooserIni.setEnabled(true);
		dateChooserIni.setDateFormatString("yyyy-MM-dd");
		dateChooserIni.setBounds(176, 68, 149, 34);
		dateChooserIni.setDate(fechaHoy);
		dateChooserIni.setMinSelectableDate(fechaHoy);
		dateChooserIni.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
					long time = dateChooserIni.getDate().getTime() + 1*(3600*24*1000);
					Date fechaSiguiente = new Date();
					fechaSiguiente.setTime(time);
					dateChooserFin.setMinSelectableDate(fechaSiguiente);
					dateChooserFin.setDate(fechaSiguiente);
	            }
	        }
	    });
		add(dateChooserIni);
		
		dateChooserFin = new JDateChooser();
		dateChooserFin.setEnabled(false);
		dateChooserFin.setVisible(false);
		dateChooserFin.setMinSelectableDate(fechaManana);
		dateChooserFin.setDateFormatString("yyyy-MM-dd");
		dateChooserFin.setBounds(176, 173, 149, 34);
		add(dateChooserFin);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblFechaInicio.setBounds(67, 68, 102, 34);
		add(lblFechaInicio);
		
		lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblFechaFin.setBounds(75, 173, 89, 34);
		lblFechaFin.setVisible(false);
		add(lblFechaFin);
		
		chckbxfechaFinalizacin = new JCheckBox("\u00BFM\u00E1s de un d\u00EDa?");
		chckbxfechaFinalizacin.setForeground(new Color(0, 255, 0));
		chckbxfechaFinalizacin.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		chckbxfechaFinalizacin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxfechaFinalizacin.isSelected()){
					dateChooserFin.setVisible(true);
					dateChooserFin.setEnabled(true);
					lblFechaFin.setVisible(true);
					spinnerDias.setEnabled(true);
				}else{
					dateChooserFin.setVisible(false);
					dateChooserFin.setEnabled(false);
					lblFechaFin.setVisible(false);
					spinnerDias.setValue(1);
					spinnerDias.setEnabled(false);}
			}
		});
		chckbxfechaFinalizacin.setBounds(106, 121, 205, 34);
		chckbxfechaFinalizacin.setOpaque(false);
		add(chckbxfechaFinalizacin);
		
		JLabel lblPrecioPorDa = new JLabel("Precio por d\u00EDa:");
		lblPrecioPorDa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPrecioPorDa.setBounds(46, 233, 123, 34);
		add(lblPrecioPorDa);
		
		textDia = new JTextField();
		textDia.setBounds(176, 234, 149, 34);
		textDia.setColumns(10);
		textDia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if (car == '.');
				else if((car<'0' || car>'9')) evt.consume();
			}
		});
		add(textDia);

		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.BLUE);
		btnGuardar.setFont(new Font("Dialog", Font.BOLD, 21));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ApplicationFacadeInterface facade = Start.getBusinessLogic();
					Date aux = dateChooserIni.getDate();
					Date aux2;
					if(chckbxfechaFinalizacin.isSelected()){
						aux2= dateChooserFin.getDate();
					}else{
						aux2= dateChooserIni.getDate();
					}
					int numdias= Integer.valueOf((String)spinnerDias.getValue());
					float precio = Float.parseFloat(textDia.getText());
					int numero= Integer.parseInt(comBoxCasas.getSelectedItem().toString());
					facade.anadirFechas(Start.getUsuario(), numero, aux, aux2, precio, numdias);
					JPanel temp1= new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(temp1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					javax.swing.JOptionPane.showMessageDialog(null,"Error al crear: " + e.getMessage(), "No....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(176, 359, 135, 34);
		add(btnGuardar);
		
		spinnerDias = new JSpinner();
		spinnerDias.setBounds(176, 292, 52, 34);
		spinnerDias.setValue(1);
		spinnerDias.setEnabled(false);
		add(spinnerDias);
		
		JLabel lblMnimoDeDas = new JLabel("M\u00EDnimo de d\u00EDas:");
		lblMnimoDeDas.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblMnimoDeDas.setBounds(37, 292, 144, 34);
		add(lblMnimoDeDas);
		
		comBoxCasas = new JComboBox<String>();
		comBoxCasas.setSelectedIndex(-1);
		comBoxCasas.setModel(modeloEC);
		comBoxCasas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxCasas.getSelectedIndex() != -1){
					actualizarTabla(Integer.parseInt(comBoxCasas.getSelectedItem().toString()));
					enaDis(true);
				} else{
					borrarTabla();
					enaDis(false);
				}
			}
		});
		comBoxCasas.setBounds(309, 15, 135, 33);
		add(comBoxCasas);
		
		buttonEliminar = new JButton("Eliminar");
		buttonEliminar.setForeground(Color.BLUE);
		buttonEliminar.setFont(new Font("Dialog", Font.BOLD, 21));
		buttonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade=Start.getBusinessLogic();
				try {						
					Vector<Fechas> aux1 =  facade.getFechas(Start.getUsuario(), Integer.parseInt(comBoxCasas.getSelectedItem().toString()));
					int x = (int) tableCasas.getSelectedRow();
					if (x ==-1) throw new Exception("Seleccione un día");
					Date ini = aux1.get(x).getFecha();
					facade.eliminarFecha(Start.getUsuario(), Integer.parseInt(comBoxCasas.getSelectedItem().toString()), ini);						
					actualizarTabla(Integer.parseInt(comBoxCasas.getSelectedItem().toString()));
					javax.swing.JOptionPane.showMessageDialog(null,"Se ha eliminado la Disponibilidad de la Fecha", "Bien....",javax.swing.JOptionPane.INFORMATION_MESSAGE);	
				}catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,"Error al eliminar: " + e.getMessage(), "No....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
				} 
			}
		});
		buttonEliminar.setBounds(832, 418, 135, 34);
		add(buttonEliminar);
		
		JLabel lblSeleccioneLosDatos = DefaultComponentFactory.getInstance().createTitle("Seleccione la casa rural:");
		lblSeleccioneLosDatos.setForeground(Color.WHITE);
		lblSeleccioneLosDatos.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		lblSeleccioneLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneLosDatos.setBounds(10, 15, 294, 32);
		add(lblSeleccioneLosDatos);
		
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
			while (i.hasNext()){
				modeloEC.addElement(Integer.toString(i.next().getHouseNumber()));
			}
			comBoxCasas.setSelectedIndex(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void actualizarTabla(int numeroRH){
		
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			borrarTabla();
			Vector<Fechas> aux =  facade.getFechas(Start.getUsuario(), numeroRH);
			Iterator<Fechas> i = aux.iterator();
			while (i.hasNext()){
				Vector<Object> vector = new Vector<Object>();
				Fechas auxi = i.next();
				vector.add(auxi.getFecha());
				vector.add(auxi.getPrecio());
				vector.add(auxi.getMinimoDias());
				if(auxi.getOfer()==null) vector.add("No");
				else vector.add("Si");
				if(auxi.isUnidoOferta())
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

	private void ajustarColumnas(){
		int anchoTabla = 551;
		int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
		TableColumn columnaTabla = null;
		for(int i=0; i<tableCasas.getColumnCount(); i++) {
			columnaTabla = tableCasas.getColumnModel().getColumn(i);
			switch(i) {
				case 0: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 1: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 2: anchoColumna = (10*anchoTabla)/100;
				anchoColumnaMin = (10*anchoTabla)/100;
				anchoColumnaMax = (10*anchoTabla)/100;
				break;
				case 3: anchoColumna = (10*anchoTabla)/100;
				anchoColumnaMin = (10*anchoTabla)/100;
				anchoColumnaMax = (10*anchoTabla)/100;
				break;
				case 4: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
				case 5: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
			}
			columnaTabla.setPreferredWidth(anchoColumna);
			columnaTabla.setMinWidth(anchoColumnaMin);
			columnaTabla.setMaxWidth(anchoColumnaMax);
		}
	}

	private void borrarTabla(){ while (modelTb.getRowCount() > 0) modelTb.removeRow(modelTb.getRowCount()-1); }
	
	private void enaDis(boolean b){
		dateChooserIni.setEnabled(b);
		dateChooserFin.setEnabled(b);
		textDia.setEnabled(b);
		spinnerDias.setEnabled(b);
		buttonEliminar.setEnabled(b);
		buttonEliminar.setEnabled(b);
		btnGuardar.setEnabled(b);
	}
}
