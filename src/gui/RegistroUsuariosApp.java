/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import clases.ManejoArchivoUsuario;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
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
import validaciones.Globales;
import validaciones.LetraDocumentFilter;

/**
 *
 * @author Walter Mora
 */
public class RegistroUsuariosApp extends JInternalFrame implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private static RegistroUsuariosApp instancia = null;

    private JInternalFrame internalFrame1;
    private JScrollPane scpUsuarios;
    private JTable tblUsuarios;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblNombres;
    private JTextField txtNombres;
    private JLabel lblApellidos;
    private JTextField txtApellidos;
    private JLabel lblPassword;
    private JTextField txtPassword;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnEliminar;
    private JDesktopPane desktop;

    private RegistroUsuariosApp(JDesktopPane desktop) {
        this.desktop = desktop;
        initComponents();
    }

    static RegistroUsuariosApp getInstance(JDesktopPane desktop) {
        if (instancia == null) {
            instancia = new RegistroUsuariosApp(desktop);
        }
        return instancia;
    }

    private void initComponents() {
        try {
            DocumentFilter soloLetras = new LetraDocumentFilter();
            
            internalFrame1 = new JInternalFrame();
            scpUsuarios = new JScrollPane();
            tblUsuarios = new JTable();
            lblUsername = new JLabel();
            txtUsername = new JTextField();
            lblNombres = new JLabel();
            txtNombres = new JTextField();
            lblApellidos = new JLabel();
            txtApellidos = new JTextField();
            lblPassword = new JLabel();
            txtPassword = new JPasswordField(30);

            btnGuardar = new JButton();
            btnCancelar = new JButton();
            btnEliminar = new JButton();

            internalFrame1.setVisible(true);
            internalFrame1.setTitle("Registro de Usuarios");
            internalFrame1.setClosable(true);
            internalFrame1.setIconifiable(true);
            internalFrame1.setMaximizable(true);
            Container internalFrame1ContentPane = internalFrame1.getContentPane();
            internalFrame1ContentPane.setLayout(null);

            //---- tblUsuarios ----
            armaGrilla();

            tblUsuarios.setAutoCreateRowSorter(true);

            ListSelectionModel listaModel = tblUsuarios.getSelectionModel();
            listaModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listaModel.addListSelectionListener(this);

            tblUsuarios.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        Point p = e.getPoint();
                        int row = tblUsuarios.rowAtPoint(p);

                        setValores((String) tblUsuarios.getValueAt(row, 0),
                                (String) tblUsuarios.getValueAt(row, 1),
                                (String) tblUsuarios.getValueAt(row, 2),
                                (String) tblUsuarios.getValueAt(row, 3));
                    }
                }
            });

            tblUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scpUsuarios.setViewportView(tblUsuarios);

            internalFrame1ContentPane.add(scpUsuarios);
            scpUsuarios.setViewportView(tblUsuarios);
            scpUsuarios.setBounds(15, 10, 550, 145);

            //---- lblUsername ----
            lblUsername.setText("C\u00f3digo:");
            lblUsername.setLabelFor(txtUsername);
            internalFrame1ContentPane.add(lblUsername);
            lblUsername.setBounds(new Rectangle(new Point(15, 170), lblUsername.getPreferredSize()));
            internalFrame1ContentPane.add(txtUsername);
            txtUsername.setBounds(80, 170, 151, txtUsername.getPreferredSize().height);

            //---- lblNombres ----
            lblNombres.setText("Nombres:");
            lblNombres.setLabelFor(txtNombres);
            internalFrame1ContentPane.add(lblNombres);
            lblNombres.setBounds(new Rectangle(new Point(15, 195), lblNombres.getPreferredSize()));
            internalFrame1ContentPane.add(txtNombres);
            txtNombres.setBounds(80, 195, 151, txtNombres.getPreferredSize().height);
            ((AbstractDocument) txtNombres.getDocument()).setDocumentFilter(soloLetras);

            //---- lblApellidos ----
            lblApellidos.setText("Apellidos:");
            lblApellidos.setLabelFor(txtApellidos);
            internalFrame1ContentPane.add(lblApellidos);
            lblApellidos.setBounds(new Rectangle(new Point(15, 220), lblApellidos.getPreferredSize()));
            internalFrame1ContentPane.add(txtApellidos);
            txtApellidos.setBounds(80, 220, 151, txtApellidos.getPreferredSize().height);
            ((AbstractDocument) txtApellidos.getDocument()).setDocumentFilter(soloLetras);

            //---- lblPassword ----
            lblPassword.setText("Clave:");
            lblPassword.setLabelFor(txtPassword);
            internalFrame1ContentPane.add(lblPassword);
            lblPassword.setBounds(new Rectangle(new Point(15, 245), lblPassword.getPreferredSize()));
            internalFrame1ContentPane.add(txtPassword);
            txtPassword.setBounds(80, 245, 151, txtPassword.getPreferredSize().height);

            //---- btnGuardar ----
            btnGuardar.setText("Guardar");
            btnGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnGrabarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnGuardar);
            btnGuardar.setBounds(new Rectangle(new Point(140, 270), btnGuardar.getPreferredSize()));

            //---- btnCancelar ----
            btnCancelar.setText("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnCancelarActionPerformed(e);
                }
            });
            internalFrame1ContentPane.add(btnCancelar);
            btnCancelar.setBounds(new Rectangle(new Point(251, 270), btnCancelar.getPreferredSize()));
            
            //---- btnEliminar ----
            btnEliminar.setText("Eliminar");
            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminar(e);
                }
            });
            internalFrame1ContentPane.add(btnEliminar);
            btnEliminar.setBounds(new Rectangle(new Point(362, 270), btnEliminar.getPreferredSize()));

            desktop.add(internalFrame1);
            internalFrame1.setBounds(25, 25, 590, 340);

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

    private void setValores(String username,
            String nombres,
            String apellidos,
            String password) {
        txtUsername.setText(username);
        txtNombres.setText(nombres);
        txtApellidos.setText(apellidos);
        txtPassword.setText(password);
    }

    private void btnGrabarActionPerformed(ActionEvent e) {
        try {
            if (txtNombres.getText().trim().length() == 0
                    || txtNombres.getText().trim().length() == 0
                    || txtApellidos.getText().trim().length() == 0
                    || txtPassword.getText().trim().length() == 0) {
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
                    ManejoArchivoUsuario manejoArchivoUsuario = new ManejoArchivoUsuario();
                    manejoArchivoUsuario.registrarUsuario(txtUsername.getText(),
                            txtNombres.getText(),
                            txtApellidos.getText(),
                            txtPassword.getText());

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
            if (txtUsername.getText().length() > 0) {
                int res = JOptionPane.showConfirmDialog(internalFrame1,
                        "Está seguro que desea Continuar ?",
                        "Mensaje",
                        JOptionPane.YES_NO_OPTION);

                if (res == JOptionPane.YES_OPTION) {
                    ManejoArchivoUsuario manejoArchivoUsuario = new ManejoArchivoUsuario();
                    manejoArchivoUsuario.eliminarUsuario(txtUsername.getText());
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
        txtUsername.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtPassword.setText("");
        btnGuardar.setEnabled(true);
    }

    private void armaGrilla() throws Exception {
        String[] cabUsuarios = {"UserName", "Nombres", "Apellidos", "Password"};
        Object[][] detUsuarios;

        ManejoArchivoUsuario manejoArchivoUsuario = new ManejoArchivoUsuario();
        detUsuarios = manejoArchivoUsuario.obtieneUsuarios();

        tblUsuarios.setModel(new DefaultTableModel(detUsuarios, cabUsuarios) {
            private static final long serialVersionUID = 1L;
            Class<?>[] columnTypes = new Class<?>[]{
                String.class, String.class, String.class, String.class
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
        Globales.ponerAnchoColumna(tblUsuarios, 0, 100, 100, 120, false);
        Globales.ponerAnchoColumna(tblUsuarios, 1, 163, 163, 163, false);
        Globales.ponerAnchoColumna(tblUsuarios, 2, 163, 163, 163, false);
        Globales.ponerAnchoColumna(tblUsuarios, 3, 120, 120, 120, false);
    }

    //Se lo debe implementar aunque no lo usemos
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
