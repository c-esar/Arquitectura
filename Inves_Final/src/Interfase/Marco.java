/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfase;

import CargaDatosExcel.MetodosCargaImp;
import CargaDatosExcel.MetodosExcelImp;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author cesard.chacond
 */
public class Marco extends JFrame implements Constantes.ConstantesInterfase, ActionListener {

    private final int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private JpanelBar menuPrincipal = new JpanelBar();

    public Marco() {
        this.setSize(new Dimension(ancho, alto));
        this.setTitle("Proyecto");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuPrincipal.getMenubar());
        add(menuPrincipal);
        menuPrincipal.getjMenuItem().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent activo) {
        Container f = this.getContentPane();
        if (activo.getSource() == menuPrincipal.getjMenuItem()) {
            MenuIngresoDatosJpanel menuIngreso = new MenuIngresoDatosJpanel();
            menuIngreso.setVisible(true);
        }

    }
}
