/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author cesard.chacond
 */
public class JpanelPrincipal extends JFrame implements ActionListener, ConstantesInterfase{


    public JpanelPrincipal(){
        JPanelBotones botonesMenu = new JPanelBotones();
        this.setSize(ancho, alto);
        this.setTitle(titulo);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(botonesMenu, BorderLayout.CENTER);
        
        boton_datos.addActionListener(this);
        boton_datos.setActionCommand(btn_1);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent activo) {
        String command = activo.getActionCommand();
        switch(command){         
            case btn_1:{
               MenuIngresoDatosJpanel menuIngreso = new MenuIngresoDatosJpanel();
               menuIngreso.setVisible(true);
               this.setVisible(false);
               break;
            }         
        }
    }
    
}
