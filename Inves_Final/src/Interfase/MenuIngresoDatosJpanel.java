/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfase;

import CargaDatosExcel.AtributosSistema;
import pruebas.EjecucionMetodos;
import CargaDatosExcel.MetodosCargaExcelImp;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import Exception.NewExceptionCreacionDatos;
import Exception.NewExceptionExcel;
import Factory.FactoryImp;
import Factory.FactoryImplementacion;
import pruebas.EjecucionMetodos;
import Strategia.MetodosCalculo;
import Strategia.MetodosCalculoDistancia;
import Strategia.MetodosCalculoDistanciaImp;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.jgss.spi.MechanismFactory;

/**
 *
 * @author Cesar Chacond
 */
public class MenuIngresoDatosJpanel extends JFrame implements Constantes.ConstantesInterfase, ActionListener {

    private final int ancho = 500;
    private final int alto = 500;
    private JFileChooser seleccionar = new JFileChooser();
    private File archivo;
    private JButton btnDatos = new JButton(Constantes.ConstantesInterfase.label);

    public MenuIngresoDatosJpanel() {
        this.setSize(new Dimension(ancho, alto));
        this.setTitle("Ingresar Datos");
        this.setVisible(false);
        this.setLayout(null);
        btnDatos.addActionListener(this);
        btnDatos.setBounds(100, 100, 150, 150);
        add(btnDatos, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent activo) {
        if (activo.getSource() == btnDatos) {
            if (seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
                archivo = seleccionar.getSelectedFile();
                //file = new MetodosExcelImp(archivo.getPath());
                FactoryImplementacion factoryImp = new FactoryImp();
                factoryImp.inicioAplicativo(archivo.getPath(), "Distancia");
            }
        }
    }

}
