/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import clases.Usuario;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import validaciones.Globales;

/**
 *
 * @author Walter Mora
 */
public class MenuPrincipalApp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    public static JDesktopPane desktop;

    public MenuPrincipalApp(Usuario usuario) {
        //Creamos la ventana del menu principal.
        super("Transporte");
        JMenuBar menuBar = null;
        JMenu menu = null;
        JMenuItem menuItem = null;

        //Creamos una barra de menu
        menuBar = new JMenuBar();

        menu = new JMenu("Mantenimientos");
        menuItem = new JMenuItem("Registro de Usuarios");
        menuItem.setActionCommand("01");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Registro de Rutas");
        menuItem.setActionCommand("02");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuBar.add(menu);

        menu = new JMenu("Procesos");
        menuItem = new JMenuItem("Venta de Boletos");
        menuItem.setActionCommand("03");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuBar.add(menu);

        menu = new JMenu("Reportes");
        menuItem = new JMenuItem("Reporte de venta de Boletos");
        menuItem.setActionCommand("04");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuBar.add(menu);

        Globales.usuarioLogin = usuario.getStrUserName();
        String etiquetaUsuario = usuario.getStrUserName() + " - " + usuario.getStrNombres() + " " + usuario.getStrApellidos();

        int PosX = (1000 - 30) - (etiquetaUsuario.length() * 6);
        JLabel lblUsuarioLogin = new JLabel(etiquetaUsuario);
        lblUsuarioLogin.setBounds(new Rectangle(new Point(PosX, 10), lblUsuarioLogin.getPreferredSize()));

        desktop = new JDesktopPane();
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        desktop.add(lblUsuarioLogin);

        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);
        setContentPane(desktop);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setSize(450, 260);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if ("01".equals(e.getActionCommand())) {
            RegistroUsuariosApp.getInstance(desktop);
        }
        if ("02".equals(e.getActionCommand())) {
            RegistroRutasApp.getInstance(desktop);
        }
        if ("03".equals(e.getActionCommand())) {
            VentaBoletosApp.getInstance(desktop);
        }
        if ("04".equals(e.getActionCommand())) {
            ConsultaBoletosApp.getInstance(desktop);
        }
    }

}
