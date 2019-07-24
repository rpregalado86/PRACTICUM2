/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import clases.ManejoArchivoRuta;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import validaciones.AlfaNumericoDocumentFilter;
import validaciones.Globales;
import validaciones.NumeroDecimalDocumentFilter;

/**
 *
 * @author Walter Mora
 */
public class RegistroRutasApp extends JInternalFrame implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private static RegistroRutasApp instancia = null;

    private JInternalFrame internalFrame1;
    private JScrollPane scpRutas;
    private JTable tblRutas;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblOrigen;
    private JTextField txtOrigen;
    private JLabel lblDestino;
    private JTextField txtDestino;
    private JLabel lblCosto;
    private JTextField txtCosto;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnEliminar;
    private JDesktopPane desktop;

    private RegistroRutasApp(JDesktopPane desktop) {
        this.desktop = desktop;
        initComponents();
    }

    static RegistroRutasApp getInstance(JDesktopPane desktop) {
        if (instancia == null) {
            instancia = new RegistroRutasApp(desktop);
        }
        return instancia;
    }

    private void initComponents() {
        try {
            internalFrame1 = new JInternalFrame();
            scpRutas = new JScrollPane();
            tblRutas = new JTable();
            lblCodigo = new JLabel();
            txtCodigo = new JTextField();
            lblNombre = new JLabel();
            txtNombre = new JTextField();
            lblOrigen = new JLabel();
            txtOrigen = new JTextField();
            lblDestino = new JLabel();
            txtDestino = new JTextField();
            lblCosto = new JLabel();
            txtCosto = new JTextField();

            btnGuardar = new JButton();
            btnCancelar = new JButton();
            btnEliminar = new JButton();

            internalFrame1.setVisible(true);
            internalFrame1.setTitle("Registro de Rutas");
            internalFrame1.setClosable(true);
            internalFrame1.setIconifiable(true);
            internalFrame1.setMaximizable(true);
            Container internalFrame1ContentPane = internalFrame1.getContentPane();
            internalFrame1ContentPane.setLayout(null);

            //---- tblRutas ----
            armaGrilla();

            tblRutas.setAutoCreateRowSorter(true);

            ListSelectionModel listaModel = tblRutas.getSelectionModel();
            listaModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listaModel.addListSelectionListener(this);

            tblRutas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        Point p = e.getPoint();
                        int row = tblRutas.rowAtPoint(p);

                        setValores(Integer.parseInt((String) tblRutas.getValueAt(row, 0)),
                                (String) tblRutas.getValueAt(row, 1),
                                (String) tblRutas.getValueAt(row, 2),
                                (String) tblRutas.getValueAt(row, 3),
                                (Double)tblRutas.getValueAt(row, 4));
                    }
                }
            });

            tblRutas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scpRutas.setViewportView(tblRutas);

            internalFrame1ContentPane.add(scpRutas);
            scpRutas.setViewportView(tblRutas);
            scpRutas.setBounds(15, 10, 550, 145);

            //---- lblCodigo ----
            lblCodigo.setText("C\u00f3digo:");
            lblCodigo.setLabelFor(txtCodigo);
            internalFrame1ContentPane.add(lblCodigo);
            lblCodigo.setBounds(new Rectangle(new Point(15, 170), lblCodigo.getPreferredSize()));
            txtCodigo.setEnabled(false);
            internalFrame1ContentPane.add(txtCodigo);
            txtCodigo.setBounds(80, 170, 60, txtCodigo.getPreferredSize().height);

            DocumentFilter alfaNumerico = new AlfaNumericoDocumentFilter();
            //---- lblNombre ----
            lblNombre.setText("Nombre:");
            lblNombre.setLabelFor(txtNombre);
            internalFrame1ContentPane.add(lblNombre);
            lblNombre.setBounds(new Rectangle(new Point(15, 195), lblNombre.getPreferredSize()));
            internalFrame1ContentPane.add(txtNombre);
            txtNombre.setBounds(80, 195, 151, txtNombre.getPreferredSize().height);
            ((AbstractDocument) txtNombre.getDocument()).setDocumentFilter(alfaNumerico);

            //---- lblOrigen ----
            lblOrigen.setText("Origen:");
            lblOrigen.setLabelFor(txtOrigen);
            internalFrame1ContentPane.add(lblOrigen);
            lblOrigen.setBounds(new Rectangle(new Point(15, 220), lblOrigen.getPreferredSize()));
            internalFrame1ContentPane.add(txtOrigen);
            txtOrigen.setBounds(80, 220, 151, txtOrigen.getPreferredSize().height);
            ((AbstractDocument) txtOrigen.getDocument()).setDocumentFilter(alfaNumerico);

            //---- lblDestino ----
            lblDestino.setText("Destino:");
            lblDestino.setLabelFor(txtDestino);
            internalFrame1ContentPane.add(lblDestino);
            lblDestino.setBounds(new Rectangle(new Point(15, 245), lblDestino.getPreferredSize()));
            internalFrame1ContentPane.add(txtDestino);
            txtDestino.setBounds(80, 245, 151, txtDestino.getPreferredSize().height);
            ((AbstractDocument) txtDestino.getDocument()).setDocumentFilter(alfaNumerico);
            
            //---- lblCosto ----
            lblCosto.setText("Costo:");
            lblCosto.setLabelFor(txtCosto);
            internalFrame1ContentPane.add(lblCosto);
            lblCosto.setBounds(new Rectangle(new Point(15, 270), lblCosto.getPreferredSize()));
            internalFrame1ContentPane.add(txtCosto);
            txtCosto.setBounds(80, 270, 60, txtCosto.getPreferredSize().height);
            txtCosto.setDocument(new NumeroDecimalDocumentFilter(NumeroDecimalDocumentFilter.FLOAT));

            //---- btnGuardar ----
            btnGuardar.setText("Guardar");
            btnGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnGrabarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnGuardar);
            btnGuardar.setBounds(new Rectangle(new Point(140, 295), btnGuardar.getPreferredSize()));

            //---- btnCancelar ----
            btnCancelar.setText("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnCancelarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnCancelar);
            btnCancelar.setBounds(new Rectangle(new Point(251, 295), btnCancelar.getPreferredSize()));

            //---- btnEliminar ----
            btnEliminar.setText("Eliminar");
            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminar(e);
                }
            });
            internalFrame1ContentPane.add(btnEliminar);
            btnEliminar.setBounds(new Rectangle(new Point(362, 295), btnEliminar.getPreferredSize()));

            desktop.add(internalFrame1);
            internalFrame1.setBounds(25, 25, 590, 365);

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

    private void setValores(int idRuta,
            String nombre,
            String origen,
            String destino,
            double costo) {
        txtCodigo.setText(Integer.toString(idRuta));
        txtNombre.setText(nombre);
        txtOrigen.setText(origen);
        txtDestino.setText(destino);
        txtCosto.setText(Double.toString(costo));
    }

    private void btnGrabarActionPerformed(ActionEvent e) {
        try {
            if (txtNombre.getText().trim().length() == 0
                    || txtOrigen.getText().trim().length() == 0
                    || txtDestino.getText().trim().length() == 0
                    || txtCosto.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(internalFrame1,
                        "Existen campos obligatorios sin llenar",
                        "Mensaje",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int res = JOptionPane.showConfirmDialog(internalFrame1,
                        "Está seguro que desea Continuar ?",
                        "Mensaje",
                        JOptionPane.YES_NO_OPTION);

                if (res == JOptionPane.YES_OPTION) {
                    ManejoArchivoRuta manejoArchivoRuta = new ManejoArchivoRuta();
                    int codigo = (txtCodigo.getText().trim().length() == 0 ? 0 : Integer.parseInt(txtCodigo.getText()));

                    codigo = manejoArchivoRuta.registrarRuta(codigo,
                            txtNombre.getText(),
                            txtOrigen.getText(),
                            txtDestino.getText(),
                            Double.parseDouble(txtCosto.getText()));

                    txtCodigo.setText(Long.toString(codigo));
                    btnGuardar.setEnabled(false);
                    JOptionPane.showMessageDialog(internalFrame1, "Transacción procesada con exito");
                    armaGrilla();
                }
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(internalFrame1,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar(ActionEvent e) {
        try {
            if (txtCodigo.getText().length() > 0) {
                int res = JOptionPane.showConfirmDialog(internalFrame1,
                        "Está seguro que desea Continuar ?",
                        "Mensaje",
                        JOptionPane.YES_NO_OPTION);

                if (res == JOptionPane.YES_OPTION) {
                    ManejoArchivoRuta manejoArchivoRuta = new ManejoArchivoRuta();
                    manejoArchivoRuta.eliminarRuta(Integer.parseInt(txtCodigo.getText()));
                    btnCancelarActionPerformed(e);
                    JOptionPane.showMessageDialog(internalFrame1, "Transacción procesada con exito");
                    armaGrilla();
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
        txtNombre.setText("");
        txtOrigen.setText("");
        txtDestino.setText("");
        txtCosto.setText("");
        btnGuardar.setEnabled(true);
    }

    private void armaGrilla() throws Exception {
        String[] cabRutas = {"Código", "Nombre", "Origen", "Destino", "Costo"};
        Object[][] detRutas;

        ManejoArchivoRuta manejoArchivoRuta = new ManejoArchivoRuta();
        detRutas = manejoArchivoRuta.obtieneRutas();

        tblRutas.setModel(new DefaultTableModel(detRutas, cabRutas) {
            private static final long serialVersionUID = 1L;
            Class<?>[] columnTypes = new Class<?>[]{
                BigDecimal.class, String.class, String.class, String.class, Double.class
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        Globales.ponerAnchoColumna(tblRutas, 0, 50, 50, 50, false);
        Globales.ponerAnchoColumna(tblRutas, 1, 205, 205, 205, false);
        Globales.ponerAnchoColumna(tblRutas, 2, 120, 120, 120, false);
        Globales.ponerAnchoColumna(tblRutas, 3, 120, 120, 120, false);
        Globales.ponerAnchoColumna(tblRutas, 4, 50, 50, 50, false);
    }

    //Se lo debe implementar aunque no lo usemos
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
