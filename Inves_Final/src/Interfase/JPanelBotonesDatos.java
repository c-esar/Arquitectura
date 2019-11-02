/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfase;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author cesard.chacond
 */
public class JPanelBotonesDatos extends JPanel implements ConstantesInterfase {

    private GridLayout gridLayout1 = new GridLayout(1, 1);
    private JPanel panel = new JPanel();
    private JPanel panel_1 = new JPanel();
    private JPanel panel_2 = new JPanel();
    public JPanelBotonesDatos() {
        this.setLayout(new BorderLayout());
        TextoMenu();
        BotonesCargar();
        botonesGuardar();
        this.add(panel_2, BorderLayout.CENTER);
        //this.add(panel_1, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
    }

    private void botonesGuardar() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(btnGuardar);
    }
    
    private void BotonesCargar(){
        panel_1 = new JPanel();
        panel_1.setLayout(new FlowLayout());
        panel_1.add(btnCargaDatos);
    }

    private void TextoMenu() {
        panel_2 = new JPanel();
        panel_2.setLayout(new GridLayout(1, 1, 100, 100));
        panel_2.add(label_1);
        panel_2.add(text_field1);
    }

}
//        gridLayout1.setRows(1);
//        gridLayout1.setHgap(10); // separacion lados
//        gridLayout1.setColumns(3);
//        gridLayout1.setVgap(10);// separacion arriba abajo