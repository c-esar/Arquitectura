/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfase;

import CargaDatosExcel.MetodosCargaImp;
import MetodosImp.EjecucionMetodos;
import CargaDatosExcel.MetodosExcelImp;
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
import MetodosImp.EjecucionMetodos;
import java.awt.BorderLayout;

/**
 *
 * @author Cesar Chacond
 */
public class MenuIngresoDatosJpanel extends JFrame implements Constantes.ConstantesInterfase, ActionListener {

    private final int ancho = 500;
    private final int alto = 500;
    private JFileChooser seleccionar = new JFileChooser();
    private File archivo;
    private FileInputStream entrada;
    private FileOutputStream salida;
    private final MetodosCargaImp datos = MetodosCargaImp.getInstance();
    private MetodosExcelImp file;
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
                try {
                    file = new MetodosExcelImp(archivo.getPath());
                } catch (NewExceptionExcel excel) {
                    excel.LeerExcel();
                }
                try {
                    EjecucionMetodos e = new EjecucionMetodos();
                } catch (NewExceptionCreacionDatos expDato) {
                    expDato.ExceptionFunciones(MetodosCargaImp.getInstance().getError());
                }

            }
        }
    }

}
