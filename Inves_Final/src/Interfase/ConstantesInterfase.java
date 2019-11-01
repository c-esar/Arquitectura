/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfase;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author David
 */
public interface ConstantesInterfase {

    public final int ancho = 500;
    public final int alto = 500;

    public final static String btn_1 = "Ingresar Datos";
    public final static String btn_2 = "Salir";
    public final static String titulo = "Proyecto";

    public JButton boton_datos = new JButton(btn_1);
    public JButton boton_salir = new JButton(btn_2);
    

    //Datos del 2 panel
    public JLabel label_1 = new JLabel("Numero Provedores");
    public JLabel label_2 = new JLabel("Capacidad Vehiculo");
    public JLabel label_3 = new JLabel("Capacidad Volumen");
    public JLabel label_4 = new JLabel("Cargar minima");
    public JLabel label_5 = new JLabel("Provedores por Ruta");
    public JLabel label_6 = new JLabel("");
    
    public JTextField text_field1 = new JTextField();
    public JTextField text_field2 = new JTextField();
    public JTextField text_field3 = new JTextField();
    public JTextField text_field4 = new JTextField();
    public JTextField text_field5 = new JTextField();
    public JTextField text_field6 = new JTextField();
    
    public final static String btn_cargaDatos ="Subir Excel";
    public JButton btnCargaDatos = new JButton(btn_cargaDatos);
    
    
    public final static String btn_guardar = "Sacar Excel";
    public JButton btnGuardar = new JButton(btn_guardar);

}
