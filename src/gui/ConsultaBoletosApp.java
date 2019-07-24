/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import clases.ManejoArchivoRuta;
import clases.ManejoArchivoVenta;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;
import validaciones.Globales;
import validaciones.LetraDocumentFilter;
import validaciones.NumeroDecimalDocumentFilter;
import validaciones.NumeroDocumentFilter;

/**
 *
 * @author Walter Mora
 */
public class ConsultaBoletosApp extends JInternalFrame implements ListSelectionListener, FocusListener, ItemListener, TableModelListener {

    private static final long serialVersionUID = 1L;
    private static ConsultaBoletosApp instancia = null;

    private JInternalFrame internalFrame1;
    private JScrollPane scpDatos;
    private JTable tblDatos;
    private JLabel lblIdentificacion;
    private JTextField txtIdentificacion;
    private JLabel lblRuta;
    private JComboBox cboRuta;
    private JLabel lblFecha;
    private JFormattedTextField txtFecha;
    private JCheckBox chkUsarFecha;
    private JLabel lblTipoPasajero;
    private JComboBox cboTipoPasajero;
    private JButton btnConsultar;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnEliminar;
    private JDesktopPane desktop;

    private JTextField txtIdentificacionGrid;
    private JTextField txtNombresGrid;
    private JTextField txtApellidosGrid;
    private JFormattedTextField txtFechaNacimientoGrid;
    private JComboBox cboRutaGrid;
    private JComboBox cboTipoPasajeroGrid;
    private JFormattedTextField txtFechaGrid;
    private JFormattedTextField txtHoraGrid;
    private JTextField txtCostoGrid;

    private int filaActualEd;

    private ConsultaBoletosApp(JDesktopPane desktop) {
        this.desktop = desktop;
        initComponents();
        filaActualEd = -1;
    }

    static ConsultaBoletosApp getInstance(JDesktopPane desktop) {
        if (instancia == null) {
            instancia = new ConsultaBoletosApp(desktop);
        }
        return instancia;
    }

    private void initComponents() {
        try {
            DocumentFilter soloNumeros = new NumeroDocumentFilter();
            DocumentFilter soloLetras = new LetraDocumentFilter();
            MaskFormatter formatFecha = new MaskFormatter("##/##/####");
            MaskFormatter formatHora = new MaskFormatter("##:##:##");

            internalFrame1 = new JInternalFrame();
            scpDatos = new JScrollPane();
            tblDatos = new JTable() {
                @Override
                public String getToolTipText(MouseEvent e) {
                String tip = "Marcar el check para editar o eliminar una fila";
                return tip;
            }
            };
            lblIdentificacion = new JLabel();
            txtIdentificacion = new JTextField();
            lblRuta = new JLabel();
            cboRuta = new JComboBox();
            lblFecha = new JLabel();
            txtFecha = new JFormattedTextField(formatFecha);
            chkUsarFecha = new JCheckBox();
            lblTipoPasajero = new JLabel();
            cboTipoPasajero = new JComboBox();
            txtIdentificacionGrid = new JTextField();
            txtNombresGrid = new JTextField();
            txtApellidosGrid = new JTextField();
            txtFechaNacimientoGrid = new JFormattedTextField(formatFecha);
            txtFechaGrid = new JFormattedTextField(formatFecha);
            txtHoraGrid = new JFormattedTextField(formatHora);
            txtCostoGrid = new JTextField();
            cboRutaGrid = new JComboBox();
            cboTipoPasajeroGrid = new JComboBox();
            btnConsultar = new JButton();
            btnGuardar = new JButton();
            btnCancelar = new JButton();
            btnEliminar = new JButton();

            internalFrame1.setVisible(true);
            internalFrame1.setTitle("Reporte de Venta de Boletos");
            internalFrame1.setClosable(true);
            internalFrame1.setIconifiable(true);
            internalFrame1.setMaximizable(true);
            Container internalFrame1ContentPane = internalFrame1.getContentPane();
            internalFrame1ContentPane.setLayout(null);

            //---- lblIdentificacion ----
            lblIdentificacion.setText("Identificacion:");
            lblIdentificacion.setLabelFor(txtIdentificacion);
            internalFrame1ContentPane.add(lblIdentificacion);
            lblIdentificacion.setBounds(new Rectangle(new Point(15, 10), lblIdentificacion.getPreferredSize()));
            internalFrame1ContentPane.add(txtIdentificacion);
            txtIdentificacion.setBounds(100, 10, 120, txtIdentificacion.getPreferredSize().height);
            ((AbstractDocument) txtIdentificacion.getDocument()).setDocumentFilter(soloNumeros);

            //---- lblRuta ----
            lblRuta.setText("Ruta:");
            lblRuta.setLabelFor(cboRuta);
            internalFrame1ContentPane.add(lblRuta);
            lblRuta.setBounds(new Rectangle(new Point(250, 10), lblRuta.getPreferredSize()));
            llenarComboRuta();
            internalFrame1ContentPane.add(cboRuta);
            cboRuta.setBounds(287, 10, 200, cboRuta.getPreferredSize().height);

            //---- lblFecha ----
            lblFecha.setText("Fecha:");
            lblFecha.setLabelFor(txtFecha);
            internalFrame1ContentPane.add(lblFecha);
            lblFecha.setBounds(new Rectangle(new Point(515, 10), lblFecha.getPreferredSize()));
            internalFrame1ContentPane.add(txtFecha);
            txtFecha.setBounds(558, 10, 110, txtFecha.getPreferredSize().height);

            internalFrame1ContentPane.add(chkUsarFecha);
            chkUsarFecha.setBounds(665, 10, 30, chkUsarFecha.getPreferredSize().height);
            chkUsarFecha.addItemListener(this);
            chkUsarFecha.setSelected(true);

            //---- lblTipoPasajero ----
            lblTipoPasajero.setText("Tipo Cliente:");
            lblTipoPasajero.setLabelFor(cboTipoPasajero);
            internalFrame1ContentPane.add(lblTipoPasajero);
            lblTipoPasajero.setBounds(new Rectangle(new Point(700, 10), lblTipoPasajero.getPreferredSize()));
            llenarComboTipoPasajero();
            internalFrame1ContentPane.add(cboTipoPasajero);
            cboTipoPasajero.setBounds(780, 10, 151, cboTipoPasajero.getPreferredSize().height);

            txtIdentificacionGrid.addFocusListener(this);
            ((AbstractDocument) txtIdentificacionGrid.getDocument()).setDocumentFilter(soloNumeros);
            ((AbstractDocument) txtNombresGrid.getDocument()).setDocumentFilter(soloLetras);
            ((AbstractDocument) txtApellidosGrid.getDocument()).setDocumentFilter(soloLetras);
            txtCostoGrid.setDocument(new NumeroDecimalDocumentFilter(NumeroDecimalDocumentFilter.FLOAT));
            
            //---- tblDatos ----
            armaGrilla(true);

            tblDatos.setAutoCreateRowSorter(true);

            ListSelectionModel listaModel = tblDatos.getSelectionModel();
            listaModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            tblDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scpDatos.setViewportView(tblDatos);

            internalFrame1ContentPane.add(scpDatos);
            scpDatos.setViewportView(tblDatos);
            scpDatos.setBounds(15, 50, 1060, 240);

            //---- btnConsultar ----
            btnConsultar.setText("Consultar");
            btnConsultar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnConsultarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnConsultar);
            btnConsultar.setBounds(1090, 10, 90, 30);

            //---- btnCancelar ----
            btnCancelar.setText("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnCancelarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnCancelar);
            btnCancelar.setBounds(1090, 45, 90, 30);

            //---- btnGuardar ----
            btnGuardar.setText("Guardar");
            btnGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnGrabarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnGuardar);
            btnGuardar.setBounds(1090, 80, 90, 30);

            //---- btnEliminar ----
            btnEliminar.setText("Eliminar");
            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnEliminarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnEliminar);
            btnEliminar.setBounds(1090, 115, 90, 30);

            desktop.add(internalFrame1);
            internalFrame1.setBounds(25, 25, 1200, 340);

            internalFrame1.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent arg0) {

                    instancia = null;
                    internalFrame1.dispose();
                }
            });
            internalFrame1.toFront();
            internalFrame1.setSelected(true);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnConsultarActionPerformed(ActionEvent e) {
        try {
            armaGrilla(false);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnCancelarActionPerformed(ActionEvent e) {
        try {
            txtIdentificacion.setText("");
            cboRuta.setSelectedIndex(0);
            txtFecha.setValue(null);
            cboTipoPasajero.setSelectedIndex(0);
            chkUsarFecha.setSelected(true);
            filaActualEd = -1;
            armaGrilla(true);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnGrabarActionPerformed(ActionEvent e) {
        try {
            //Se graba si ha existido una fila seleccionada
            if (filaActualEd > - 1 && (Boolean) tblDatos.getValueAt(filaActualEd, 0)) {
                //Se graba la fila que esta marcada con el check
              
                    String codigo = (String) tblDatos.getValueAt(filaActualEd, 1);
                    String identificacion = (String) tblDatos.getValueAt(filaActualEd, 2);
                    String nombres = (String) tblDatos.getValueAt(filaActualEd, 3);
                    String apellidos = (String) tblDatos.getValueAt(filaActualEd, 4);
                    String fechaNacimiento = (String) tblDatos.getValueAt(filaActualEd, 5);

                    int posicion;
                    String codigoTipoPasajero;
                    String tipoPasajero = (String) tblDatos.getValueAt(filaActualEd, 6);
                    posicion = tipoPasajero.indexOf("-");
                    if (posicion > 0) {
                        codigoTipoPasajero = tipoPasajero.substring(0, posicion == 1 ? 1 : posicion - 1).trim();
                    } else {
                        codigoTipoPasajero = tipoPasajero.substring(0, tipoPasajero.length()).trim();
                    }

                    String codigoRuta;
                    String ruta = (String) tblDatos.getValueAt(filaActualEd, 7);
                    posicion = ruta.indexOf("-");
                    if (posicion > 0) {
                        codigoRuta = ruta.substring(0, posicion == 1 ? 1 : posicion - 1).trim();
                    } else {
                        codigoRuta = ruta.substring(0, ruta.length()).trim();
                    }

                    String fecha = (String) tblDatos.getValueAt(filaActualEd, 8);
                    String hora = (String) tblDatos.getValueAt(filaActualEd, 9);
                    String total = (String) tblDatos.getValueAt(filaActualEd, 10);

                    String fechaNacimientAux = fechaNacimiento.replace("/", "").trim();
                    String fechaAux = fecha.replace("/", "").trim();
                    String tiempoAux = hora.replace(":", "");

                    if (identificacion.trim().length() == 0
                            || nombres.trim().length() == 0
                            || apellidos.trim().length() == 0
                            || fechaNacimientAux.trim().length() == 0
                            || codigoTipoPasajero.trim().length() == 0
                            || codigoRuta.trim().length() == 0
                            || fechaAux.trim().length() == 0
                            || tiempoAux.trim().length() == 0) {

                        JOptionPane.showMessageDialog(internalFrame1,
                                "Existen campos obligatorios sin llenar",
                                "Mensaje",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (codigo.trim().length() > 0) {

                            int res = JOptionPane.showConfirmDialog(internalFrame1,
                                    "Está seguro que desea Continuar ?",
                                    "Mensaje",
                                    JOptionPane.YES_NO_OPTION);

                            if (res == JOptionPane.YES_OPTION) {
                                ManejoArchivoVenta manejoArchivoVenta = new ManejoArchivoVenta();
                                manejoArchivoVenta.registrarVenta(Integer.parseInt(codigo),
                                        identificacion,
                                        nombres,
                                        apellidos,
                                        fechaNacimiento,
                                        codigoTipoPasajero,
                                        Integer.parseInt(codigoRuta),
                                        fecha,
                                        hora,
                                        Double.parseDouble(total),
                                        Globales.usuarioLogin);

                                JOptionPane.showMessageDialog(internalFrame1, "Transacción procesada con exito");
                            }
                        }
                        filaActualEd = -1;
                        armaGrilla(false);
                    }
                }
            
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEliminarActionPerformed(ActionEvent e) {
        try {
            //Se elimina si ha existido una fila seleccionada
            if (filaActualEd > - 1 && (Boolean) tblDatos.getValueAt(filaActualEd, 0)) {
                //Se elimina la fila que esta marcada con el check
                
                    String codigo = (String) tblDatos.getValueAt(filaActualEd, 1);

                    if (codigo.trim().length() > 0) {
                        int res = JOptionPane.showConfirmDialog(internalFrame1,
                                "Está seguro que desea Continuar ?",
                                "Mensaje",
                                JOptionPane.YES_NO_OPTION);

                        if (res == JOptionPane.YES_OPTION) {
                            ManejoArchivoVenta manejoArchivoVenta = new ManejoArchivoVenta();
                            manejoArchivoVenta.eliminarVenta(Integer.parseInt(codigo));
                            armaGrilla(false);
                            JOptionPane.showMessageDialog(internalFrame1, "Transacción procesada con exito");
                        }
                    }
                
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void armaGrilla(boolean limpiar) throws Exception {
        String fecha;
        String fechaAux;

        String[] cabDatos = {"", "Código", "Identificacion", "Nombres",
            "Apellidos", "Fecha Nac", "Tipo Cliente", "Ruta",
            "Fecha", "Hora", "Total", "Usuario"};

        Object[][] detDatos = null;

        if (limpiar) {
            fechaAux = txtFecha.getText().replace("/", "").trim();
            fecha = (fechaAux.trim().length() == 0 ? "" : txtFecha.getText());

            ManejoArchivoVenta manejoArchivoVenta = new ManejoArchivoVenta();
            detDatos = manejoArchivoVenta.obtieneVentasCriterios(
                    txtIdentificacion.getText(),
                    Integer.parseInt(Globales.getValueCombo(cboRuta)),
                    fecha,
                    Globales.getValueCombo(cboTipoPasajero));
        }

        tblDatos.setModel(new DefaultTableModel(detDatos, cabDatos) {
            private static final long serialVersionUID = 1L;
            Class<?>[] columnTypes = new Class<?>[]{
                Boolean.class, String.class, String.class, String.class, String.class,
                String.class, String.class, String.class, String.class,
                String.class, String.class, String.class
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                
                    if (col != 0 && this.getValueAt(row, 0) == Boolean.FALSE || col == 11) {
                        return false;
                    }
                
                return true;
            }
        });
        tblDatos.getModel().addTableModelListener(this);
        //Asignamos un cajon de texto dentro del grid que contiene la validacion
        //de solo numeros y digito verificador
        TableColumn identificacionColumn = tblDatos.getColumnModel().getColumn(2);
        identificacionColumn.setCellEditor(new DefaultCellEditor(txtIdentificacionGrid));

        //Asignamos un cajon de texto dentro del grid que contiene la validacion
        //de solo letras para la columna nombres
        TableColumn nombresColumn = tblDatos.getColumnModel().getColumn(3);
        nombresColumn.setCellEditor(new DefaultCellEditor(txtNombresGrid));

        //Asignamos un cajon de texto dentro del grid que contiene la validacion
        //de solo letras para la columna apellidos
        TableColumn apellidosColumn = tblDatos.getColumnModel().getColumn(4);
        apellidosColumn.setCellEditor(new DefaultCellEditor(txtApellidosGrid));

        //Asignamos un cajon de texto dentro del grid que contiene la validacion
        //de mascara de fechas para la columna fecha de nacimiento
        TableColumn fechaNacimientoColumn = tblDatos.getColumnModel().getColumn(5);
        fechaNacimientoColumn.setCellEditor(new DefaultCellEditor(txtFechaNacimientoGrid));

        //Asignamos el combo tipo pasajero a la columna del grid
        TableColumn tipoPasajeroColumn = tblDatos.getColumnModel().getColumn(6);
        tipoPasajeroColumn.setCellEditor(new DefaultCellEditor(cboTipoPasajeroGrid));

        //Asignamos el combo de rutas a la columna del grid
        TableColumn rutaColumn = tblDatos.getColumnModel().getColumn(7);
        rutaColumn.setCellEditor(new DefaultCellEditor(cboRutaGrid));

        //Asignamos un cajon de texto dentro del grid que contiene la validacion
        //de mascara de fechas para la columna fecha
        TableColumn fechaColumn = tblDatos.getColumnModel().getColumn(8);
        fechaColumn.setCellEditor(new DefaultCellEditor(txtFechaGrid));

        //Asignamos un cajon de texto dentro del grid que contiene la validacion
        //de mascara de tiempo para la columna de hora
        TableColumn horaColumn = tblDatos.getColumnModel().getColumn(9);
        horaColumn.setCellEditor(new DefaultCellEditor(txtHoraGrid));

        //Asignamos un cajon de texto dentro del grid que contiene la validacion
        //de mascara de decimales para la columna costo
        TableColumn costoColumn = tblDatos.getColumnModel().getColumn(10);
        costoColumn.setCellEditor(new DefaultCellEditor(txtCostoGrid));

        Globales.ponerAnchoColumna(tblDatos, 0, 30, 30, 30, false);
        Globales.ponerAnchoColumna(tblDatos, 1, 0, 0, 0, false);
        Globales.ponerAnchoColumna(tblDatos, 2, 100, 100, 100, true);
        Globales.ponerAnchoColumna(tblDatos, 3, 150, 150, 150, true);
        Globales.ponerAnchoColumna(tblDatos, 4, 150, 150, 150, true);
        Globales.ponerAnchoColumna(tblDatos, 5, 80, 80, 80, true);
        Globales.ponerAnchoColumna(tblDatos, 6, 110, 110, 110, true);
        Globales.ponerAnchoColumna(tblDatos, 7, 110, 110, 110, true);
        Globales.ponerAnchoColumna(tblDatos, 8, 80, 80, 80, true);
        Globales.ponerAnchoColumna(tblDatos, 9, 80, 80, 80, true);
        Globales.ponerAnchoColumna(tblDatos, 10, 80, 80, 80, true);
        Globales.ponerAnchoColumna(tblDatos, 11, 80, 80, 80, true);
    }

    //Se lo debe implementar aunque no lo usemos
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            if (e.getSource() == txtIdentificacionGrid && txtIdentificacionGrid.getText().trim().length() > 0) {
                
                    if (Globales.esCedulaValida(txtIdentificacionGrid.getText())) {
                        JOptionPane.showMessageDialog(internalFrame1,
                                "La Cédula es Incorrecta",
                                "Mensaje",
                                JOptionPane.WARNING_MESSAGE);
                    }
                
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        Object source = e.getItemSelectable();
        if (source == chkUsarFecha) {
            if (chkUsarFecha.isSelected()) {
                txtFecha.setEnabled(false);
                txtFecha.setValue(null);
            } else {
                txtFecha.setEnabled(true);
            }
        }
    }
    
    private void llenarComboTipoPasajero() {
        cboTipoPasajero.addItem("0 - Seleccione");
        cboTipoPasajero.addItem("P - PERSONA");
        cboTipoPasajero.addItem("S - SOCIO");
        cboTipoPasajero.addItem("V - CLIENTE VIP");
        //Se llena otro combo para el que sera utilizado en el Grid
        cboTipoPasajeroGrid.addItem("0 - Seleccione");
        cboTipoPasajeroGrid.addItem("P - PERSONA");
        cboTipoPasajeroGrid.addItem("S - SOCIO");
        cboTipoPasajeroGrid.addItem("V - CLIENTE VIP");
    }

    private void llenarComboRuta() throws Exception {
        Object[][] rutas;

        ManejoArchivoRuta manejoArchivoRuta = new ManejoArchivoRuta();
        rutas = manejoArchivoRuta.obtieneRutas();

        cboRuta.addItem("0 - Seleccione");
        cboRutaGrid.addItem("0 - Seleccione");
        if (rutas != null) {
            int longitud = rutas.length;
            for (int i = 0; i < longitud; i++) {
                cboRuta.addItem(rutas[i][0] + " - " + rutas[i][1]);
                //Se llena otro combo para el que sera utilizado en el Grid
                cboRutaGrid.addItem(rutas[i][0] + " - " + rutas[i][1]);
            }
        }
    }

    //Se implementa este metodo para permitir que solo se marque una fila con el check
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();

        if (column == 0) {
            DefaultTableModel model = (DefaultTableModel) e.getSource();
            boolean marcado = (Boolean) model.getValueAt(row, column);
            if (marcado) {
                if (filaActualEd > -1 && filaActualEd != row) {
                    model.setValueAt(Boolean.FALSE, filaActualEd, 0);
                }
                filaActualEd = row;
            }
        }
    }
}
