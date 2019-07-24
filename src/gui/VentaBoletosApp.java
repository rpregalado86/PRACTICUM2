/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import clases.Cliente;
import clases.ManejoArchivoRuta;
import clases.ManejoArchivoVenta;
import clases.Ruta;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;
import validaciones.Fecha;
import validaciones.Globales;
import validaciones.LetraDocumentFilter;
import validaciones.NumeroDocumentFilter;

/**
 *
 * @author Walter Mora
 */
public class VentaBoletosApp extends JInternalFrame implements FocusListener {

    private static final long serialVersionUID = 1L;
    private static VentaBoletosApp instancia = null;

    private JInternalFrame internalFrame1;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblIdentificacion;
    private JTextField txtIdentificacion;
    private JLabel lblNombres;
    private JTextField txtNombres;
    private JLabel lblApellidos;
    private JTextField txtApellidos;
    private JLabel lblFechaNacimiento;
    private JFormattedTextField txtFechaNacimiento;
    private JLabel lblEdad;
    private JTextField txtEdad;
    private JLabel lblTipoPasajero;
    private JComboBox cboTipoPasajero;
    private JLabel lblRuta;
    private JComboBox cboRuta;
    private JLabel lblOrigen;
    private JTextField txtOrigen;
    private JLabel lblDestino;
    private JTextField txtDestino;
    private JLabel lblCosto;
    private JTextField txtCosto;
    private JLabel lblFecha;
    private JFormattedTextField txtFecha;
    private JLabel lblHora;
    private JSpinner jspHora;
    private JLabel lblTotal;
    private JTextField txtTotal;
    private JLabel lblDescuento;

    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnEliminar;
    private JDesktopPane desktop;

    private VentaBoletosApp(JDesktopPane desktop) {
        this.desktop = desktop;
        initComponents();
    }

    static VentaBoletosApp getInstance(JDesktopPane desktop) {
        if (instancia == null) {
            instancia = new VentaBoletosApp(desktop);
        }
        return instancia;
    }

    private void initComponents() {
        try {
            DocumentFilter soloNumeros = new NumeroDocumentFilter();
            DocumentFilter soloLetras = new LetraDocumentFilter();
            MaskFormatter fomatFecha = new MaskFormatter("##/##/####");

            internalFrame1 = new JInternalFrame();
            lblCodigo = new JLabel();
            txtCodigo = new JTextField();
            lblIdentificacion = new JLabel();
            txtIdentificacion = new JTextField();
            lblNombres = new JLabel();
            txtNombres = new JTextField();
            lblApellidos = new JLabel();
            txtApellidos = new JTextField();
            lblFechaNacimiento = new JLabel();
            txtFechaNacimiento = new JFormattedTextField(fomatFecha);
            lblEdad = new JLabel();
            txtEdad = new JTextField();
            lblTipoPasajero = new JLabel();
            cboTipoPasajero = new JComboBox();
            lblRuta = new JLabel();
            cboRuta = new JComboBox();
            lblOrigen = new JLabel();
            txtOrigen = new JTextField();
            lblDestino = new JLabel();
            txtDestino = new JTextField();
            lblCosto = new JLabel();
            txtCosto = new JTextField();
            lblFecha = new JLabel();
            txtFecha = new JFormattedTextField(fomatFecha);
            lblHora = new JLabel();
            jspHora = new JSpinner(new SpinnerDateModel());
            lblTotal = new JLabel();
            txtTotal = new JTextField();
            lblDescuento = new JLabel();

            btnGuardar = new JButton();
            btnCancelar = new JButton();

            internalFrame1.setVisible(true);
            internalFrame1.setTitle("Venta de Boletos");
            internalFrame1.setClosable(true);
            internalFrame1.setIconifiable(true);
            internalFrame1.setMaximizable(true);
            Container internalFrame1ContentPane = internalFrame1.getContentPane();
            internalFrame1ContentPane.setLayout(null);

            //---- lblCodigo ----
            lblCodigo.setText("C\u00f3digo:");
            lblCodigo.setLabelFor(txtCodigo);
            internalFrame1ContentPane.add(lblCodigo);
            lblCodigo.setBounds(new Rectangle(new Point(15, 10), lblCodigo.getPreferredSize()));
            txtCodigo.setEnabled(false);
            internalFrame1ContentPane.add(txtCodigo);
            txtCodigo.setBounds(125, 10, 60, txtCodigo.getPreferredSize().height);

            //---- lblIdentificacion ----
            lblIdentificacion.setText("Identificacion:");
            lblIdentificacion.setLabelFor(txtIdentificacion);
            internalFrame1ContentPane.add(lblIdentificacion);
            lblIdentificacion.setBounds(new Rectangle(new Point(15, 35), lblIdentificacion.getPreferredSize()));
            internalFrame1ContentPane.add(txtIdentificacion);
            txtIdentificacion.setBounds(125, 35, 151, txtIdentificacion.getPreferredSize().height);
            txtIdentificacion.setToolTipText("Para validar numero presionar TAB");
            txtIdentificacion.addFocusListener(this);
            ((AbstractDocument) txtIdentificacion.getDocument()).setDocumentFilter(soloNumeros);

            //---- lblNombres ----
            lblNombres.setText("Nombre:");
            lblNombres.setLabelFor(txtNombres);
            internalFrame1ContentPane.add(lblNombres);
            lblNombres.setBounds(new Rectangle(new Point(15, 60), lblNombres.getPreferredSize()));
            internalFrame1ContentPane.add(txtNombres);
            txtNombres.setBounds(125, 60, 400, txtNombres.getPreferredSize().height);
            ((AbstractDocument) txtNombres.getDocument()).setDocumentFilter(soloLetras);

            //---- lblApellidos ----
            lblApellidos.setText("Apellidos:");
            lblApellidos.setLabelFor(txtApellidos);
            internalFrame1ContentPane.add(lblApellidos);
            lblApellidos.setBounds(new Rectangle(new Point(15, 85), lblApellidos.getPreferredSize()));
            internalFrame1ContentPane.add(txtApellidos);
            txtApellidos.setBounds(125, 85, 400, txtApellidos.getPreferredSize().height);
            ((AbstractDocument) txtApellidos.getDocument()).setDocumentFilter(soloLetras);

            //---- lblFechaNacimiento ----
            lblFechaNacimiento.setText("Fecha Nacimiento:");
            lblFechaNacimiento.setLabelFor(txtFechaNacimiento);
            internalFrame1ContentPane.add(lblFechaNacimiento);
            lblFechaNacimiento.setBounds(new Rectangle(new Point(15, 110), lblFechaNacimiento.getPreferredSize()));
            internalFrame1ContentPane.add(txtFechaNacimiento);
            txtFechaNacimiento.setBounds(125, 110, 110, txtFechaNacimiento.getPreferredSize().height);
            txtFechaNacimiento.setToolTipText("Para validar fecha presionar TAB");
            txtFechaNacimiento.addFocusListener(this);

            //---- lblEdad ----
            lblEdad.setText("Edad:");
            lblEdad.setLabelFor(txtEdad);
            internalFrame1ContentPane.add(lblEdad);
            lblEdad.setBounds(new Rectangle(new Point(245, 110), lblEdad.getPreferredSize()));
            internalFrame1ContentPane.add(txtEdad);
            txtEdad.setBounds(285, 110, 110, txtEdad.getPreferredSize().height);
            txtEdad.setEnabled(false);

            //---- lblTipoPasajero ----
            lblTipoPasajero.setText("Tipo Cliente:");
            lblTipoPasajero.setLabelFor(cboTipoPasajero);
            internalFrame1ContentPane.add(lblTipoPasajero);
            lblTipoPasajero.setBounds(new Rectangle(new Point(15, 135), lblTipoPasajero.getPreferredSize()));
            cboTipoPasajero.addItem("0 - Seleccione");
            cboTipoPasajero.addItem("P - PERSONA");
            cboTipoPasajero.addItem("S - SOCIO");
            cboTipoPasajero.addItem("V - CLIENTE VIP");
            internalFrame1ContentPane.add(cboTipoPasajero);
            cboTipoPasajero.setBounds(125, 135, 151, cboTipoPasajero.getPreferredSize().height);
            cboTipoPasajero.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cboTipoPasajeroActionPerformed(e);
                }
            });

            //---- lblRuta ----
            lblRuta.setText("Ruta:");
            lblRuta.setLabelFor(cboRuta);
            internalFrame1ContentPane.add(lblRuta);
            lblRuta.setBounds(new Rectangle(new Point(15, 165), lblRuta.getPreferredSize()));
            llenarComboRuta();
            internalFrame1ContentPane.add(cboRuta);
            cboRuta.setBounds(125, 165, 200, cboRuta.getPreferredSize().height);
            cboRuta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cboRutaActionPerformed(e);
                }
            });

            //---- lblOrigen ----
            lblOrigen.setText("Origen:");
            lblOrigen.setLabelFor(txtOrigen);
            internalFrame1ContentPane.add(lblOrigen);
            lblOrigen.setBounds(new Rectangle(new Point(15, 197), lblOrigen.getPreferredSize()));
            internalFrame1ContentPane.add(txtOrigen);
            txtOrigen.setBounds(125, 197, 151, txtOrigen.getPreferredSize().height);
            txtOrigen.setEnabled(false);

            //---- lblDestino ----
            lblDestino.setText("Destino:");
            lblDestino.setLabelFor(txtDestino);
            internalFrame1ContentPane.add(lblDestino);
            lblDestino.setBounds(new Rectangle(new Point(286, 197), lblDestino.getPreferredSize()));
            internalFrame1ContentPane.add(txtDestino);
            txtDestino.setBounds(340, 197, 151, txtDestino.getPreferredSize().height);
            txtDestino.setEnabled(false);

            //---- lblCosto ----
            lblCosto.setText("Costo:");
            lblCosto.setLabelFor(txtCosto);
            internalFrame1ContentPane.add(lblCosto);
            lblCosto.setBounds(new Rectangle(new Point(501, 197), lblCosto.getPreferredSize()));
            internalFrame1ContentPane.add(txtCosto);
            txtCosto.setBounds(545, 197, 60, txtCosto.getPreferredSize().height);
            txtCosto.setEnabled(false);

            //---- lblFecha ----
            lblFecha.setText("Fecha:");
            lblFecha.setLabelFor(txtFecha);
            internalFrame1ContentPane.add(lblFecha);
            lblFecha.setBounds(new Rectangle(new Point(15, 222), lblFecha.getPreferredSize()));
            internalFrame1ContentPane.add(txtFecha);
            txtFecha.setBounds(125, 222, 110, txtFecha.getPreferredSize().height);

            //---- lblHora ----
            lblHora.setText("Hora:");
            internalFrame1ContentPane.add(lblHora);
            lblHora.setBounds(new Rectangle(new Point(15, 247), lblHora.getPreferredSize()));
            JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(jspHora, "HH:mm:ss");
            jspHora.setEditor(timeEditor);
            jspHora.setValue(new Date());
            internalFrame1ContentPane.add(jspHora);
            jspHora.setBounds(125, 247, 110, jspHora.getPreferredSize().height);

            //---- lblTotal ----
            lblTotal.setText("Total:");
            lblTotal.setLabelFor(txtTotal);
            internalFrame1ContentPane.add(lblTotal);
            lblTotal.setBounds(new Rectangle(new Point(15, 272), lblTotal.getPreferredSize()));
            internalFrame1ContentPane.add(txtTotal);
            txtTotal.setBounds(125, 272, 60, txtTotal.getPreferredSize().height);
            txtTotal.setEnabled(false);

            //---- lblDescuento ----
            lblDescuento.setText("");
            internalFrame1ContentPane.add(lblDescuento);
            lblDescuento.setBounds(new Rectangle(new Point(190, 272), lblDescuento.getPreferredSize()));

            //---- btnGuardar ----
            btnGuardar.setText("Guardar");
            btnGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnGrabarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnGuardar);
            btnGuardar.setBounds(new Rectangle(new Point(200, 297), btnGuardar.getPreferredSize()));

            //---- btnCancelar ----
            btnCancelar.setText("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnCancelarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnCancelar);
            btnCancelar.setBounds(new Rectangle(new Point(311, 297), btnCancelar.getPreferredSize()));

            desktop.add(internalFrame1);
            internalFrame1.setBounds(25, 25, 625, 365);

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

    private void btnGrabarActionPerformed(ActionEvent e) {
        String fechaNacimientAux = txtFechaNacimiento.getText().replace("/", "").trim();
        String fechaAux = txtFecha.getText().replace("/", "").trim();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String tiempoAux = sdf.format(jspHora.getValue()).replace(":", "");

        try {
            if (txtIdentificacion.getText().trim().length() == 0
                    || txtNombres.getText().trim().length() == 0
                    || txtApellidos.getText().trim().length() == 0
                    || fechaNacimientAux.trim().length() == 0
                    || cboTipoPasajero.getSelectedIndex() == 0
                    || cboRuta.getSelectedIndex() == 0
                    || fechaAux.trim().length() == 0
                    || tiempoAux.trim().length() == 0) {

                JOptionPane.showMessageDialog(internalFrame1,
                        "Existen campos obligatorios sin llenar",
                        "Mensaje",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (Globales.esCedulaValida(txtIdentificacion.getText()) == false) {
                    JOptionPane.showMessageDialog(internalFrame1,
                            "La Cédula es Incorrecta",
                            "Mensaje",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    int res = JOptionPane.showConfirmDialog(internalFrame1,
                            "Está seguro que desea Continuar ?",
                            "Mensaje",
                            JOptionPane.YES_NO_OPTION);

                    if (res == JOptionPane.YES_OPTION) {
                        ManejoArchivoVenta manejoArchivoVenta = new ManejoArchivoVenta();
                        int codigo = (txtCodigo.getText().trim().length() == 0 ? 0 : Integer.parseInt(txtCodigo.getText()));

                        codigo = manejoArchivoVenta.registrarVenta(codigo,
                                txtIdentificacion.getText(),
                                txtNombres.getText(),
                                txtApellidos.getText(),
                                txtFechaNacimiento.getText(),
                                Globales.getValueCombo(cboTipoPasajero),
                                Integer.parseInt(Globales.getValueCombo(cboRuta)),
                                txtFecha.getText(),
                                sdf.format(jspHora.getValue()),
                                Double.parseDouble(txtTotal.getText()),
                                Globales.usuarioLogin);
                        txtCodigo.setText(Integer.toString(codigo));
                        btnGuardar.setEnabled(false);
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

    private void btnCancelarActionPerformed(ActionEvent e) {
        txtCodigo.setText("");
        txtIdentificacion.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        cboTipoPasajero.setSelectedIndex(0);
        txtFechaNacimiento.setValue(null);
        txtEdad.setText("");
        cboRuta.setSelectedIndex(0);
        txtOrigen.setText("");
        txtDestino.setText("");
        txtCosto.setText("");
        txtFecha.setValue(null);
        jspHora.setValue(new Date());
        txtTotal.setText("");
        lblDescuento.setText("");
        btnGuardar.setEnabled(true);
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            if (e.getSource() == txtIdentificacion) {
                if (txtIdentificacion.getText().trim().length() > 0) {
                    if (Globales.esCedulaValida(txtIdentificacion.getText()) == false) {
                        JOptionPane.showMessageDialog(internalFrame1,
                                "La Cédula es Incorrecta",
                                "Mensaje",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        ManejoArchivoVenta manejoArchivoVenta = new ManejoArchivoVenta();
                        Cliente cliente = manejoArchivoVenta.buscarCliente(txtIdentificacion.getText());
                        if (cliente != null) {
                            txtNombres.setText(cliente.getStrNombres());
                            txtApellidos.setText(cliente.getStrApellidos());
                            txtFechaNacimiento.setText(Fecha.getFecha("dd/MM/yyyy", cliente.getDatFechaNacimiento()));
                            Globales.setValueCombo(cboTipoPasajero, cliente.getStrTipoCliente());
                            obtenerEdad();
                        }
                    }
                }
            } else if (e.getSource() == txtFechaNacimiento) {
                obtenerEdad();
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cboRutaActionPerformed(ActionEvent evt) {
        try {
            if (cboRuta.getSelectedIndex() > 0) {
                ManejoArchivoRuta manejoArchivoRuta = new ManejoArchivoRuta();
                Ruta ruta = manejoArchivoRuta.buscarRuta(Integer.parseInt(Globales.getValueCombo(cboRuta)));
                if (ruta != null) {
                    txtOrigen.setText(ruta.getStrOrigen());
                    txtDestino.setText(ruta.getStrDestino());
                    txtCosto.setText(Double.toString(ruta.getDouCosto()));
                    //Se calcula el total
                    calcularTotal();
                }
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cboTipoPasajeroActionPerformed(ActionEvent evt) {
        try {
            if (cboRuta.getSelectedIndex() > 0) {
                //Si se cambia el tipo de pasajero se procede a calcular nuevamente el total
                calcularTotal();
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarComboRuta() throws Exception {
        Object[][] rutas;

        ManejoArchivoRuta manejoArchivoRuta = new ManejoArchivoRuta();
        rutas = manejoArchivoRuta.obtieneRutas();

        cboRuta.addItem("0 - Seleccione");
        if (rutas != null) {
            int longitud = rutas.length;
            for (int i = 0; i < longitud; i++) {
                cboRuta.addItem(rutas[i][0] + " - " + rutas[i][1]);
            }
        }
    }

    private void obtenerEdad() {
        try {
            Fecha.getFecha("dd/MM/yyyy", txtFechaNacimiento.getText());

            int dia = Integer.parseInt(txtFechaNacimiento.getText().substring(0, 2));
            int mes = Integer.parseInt(txtFechaNacimiento.getText().substring(3, 5));
            int anio = Integer.parseInt(txtFechaNacimiento.getText().substring(6, 10));
            txtEdad.setText(Integer.toString(Fecha.edad(anio, mes - 1, dia)));
            //Si se cambia la edad se procede a calcular nuevamente el total
            calcularTotal();
        } catch (Exception exc) {
            txtFechaNacimiento.setValue(null);
            txtFechaNacimiento.requestFocus();
        }
    }

    private void calcularTotal() {
        double costo, total, descuento = 0;

        if (Integer.parseInt(txtEdad.getText()) <= Globales.EDAD_MENOR_EDAD) {
            descuento = descuento + Globales.DESCTO_MENOR_EDAD;
        } else if (Integer.parseInt(txtEdad.getText()) >= Globales.EDAD_MAYOR_EDAD) {
            descuento = descuento + Globales.DESCTO_MAYOR_EDAD;
        }

        if ("S".equals(Globales.getValueCombo(cboTipoPasajero))) {
            descuento = descuento + Globales.DESCTO_SOCIO;
        } else if ("V".equals(Globales.getValueCombo(cboTipoPasajero))) {
            descuento = descuento + Globales.DESCTO_VIP;
        }

        costo = (txtCosto.getText().trim().length() == 0d ? 0d : Double.parseDouble(txtCosto.getText()));
        lblDescuento.setText(descuento + "% Dscto");
        lblDescuento.setSize(lblDescuento.getPreferredSize());
        total = costo - (costo * (descuento / 100));
        total = (double) Math.round(total * 100) / 100;
        txtTotal.setText(Double.toString(total));
    }
}
