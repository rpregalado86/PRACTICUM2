/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import clases.ManejoArchivoUsuario;
import clases.Usuario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Walter Mora
 */
public class LoginUsuarioApp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    
    private JButton btnConectar;
    private JPanel panel;
    private JLabel lblUsuario, lblClave;
    private JTextField txtUsuario, txtClave;
    Usuario usuario = null;
    private int numeroIntentos;
    
    public LoginUsuarioApp() {
        lblUsuario = new JLabel();
        lblUsuario.setText("Usuario:");
        txtUsuario = new JTextField(30);

        lblClave = new JLabel();
        lblClave.setText("Clave:");
        txtClave = new JPasswordField(30);
        txtClave.addActionListener(this);

        btnConectar = new JButton("Entrar");

        panel = new JPanel(new GridLayout(4, 1));
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblClave);
        panel.add(txtClave);
        panel.add(btnConectar);
        add(panel, BorderLayout.CENTER);
        btnConectar.addActionListener(this);
        setTitle("Login");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (txtUsuario.getText().trim().length() == 0 || txtClave.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "El usuario y la clave son requeridos",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                boolean esUsuarioValido = validaLogin(txtUsuario.getText(), txtClave.getText());

                if (esUsuarioValido) {
                    new MenuPrincipalApp(usuario);
                    this.setVisible(false);
                    this.dispose();
                } else {
                    numeroIntentos = numeroIntentos + 1;
                    if (numeroIntentos >= 3) {
                        //new MenuPrincipalApp(null);
                        this.setVisible(false);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Usuario o password incorrecto",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this,
                    "Ha ocurrido un error - " + exc.getMessage(),
                    "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        LoginUsuarioApp frame = new LoginUsuarioApp();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocation(screenWidth / 4, screenHeight / 4);

        frame.setSize(300, 100);
        frame.setVisible(true);
    }
    
    private boolean validaLogin(String username, String clave) throws Exception {
       //Instanciamos la clase que administra el archivo
        ManejoArchivoUsuario manejoArchivoUsuario = new ManejoArchivoUsuario();
        //Hacemos uso del metodo que buscar el usuario dentro del archivo
        usuario = manejoArchivoUsuario.buscarUsuario(username);
        if (usuario != null) {

            //Se evalua si la clave recuperada del archivo es igual a la ingresada
            if (clave.equals(usuario.getStrPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
             return false;
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
