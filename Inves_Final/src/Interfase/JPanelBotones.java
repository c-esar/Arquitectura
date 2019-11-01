/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author cesard.chacond
 */
public class JPanelBotones extends JPanel implements ConstantesInterfase{
    
    public JPanelBotones(){
        this.setLayout(new BorderLayout());
        botonesMenu();
    }

    private void botonesMenu() {
        this.setLayout(new FlowLayout());
        add(boton_datos);
        add(boton_salir);
    }
    
}
