/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfase;

import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class JpanelBar extends JPanel implements Constantes.ConstantesInterfase {

    private JMenuBar Menubar = new JMenuBar();
    private JMenu Menu = new JMenu();
    private JMenuItem jMenuItem = new JMenuItem();

    public JpanelBar() {
        this.setLayout(new BorderLayout());
        Menu();
    }

    private void Menu() {
        this.setLayout(null);
        Menu = new JMenu("Opciones");
        Menubar.add(Menu);
        jMenuItem = new JMenuItem(Constantes.ConstantesInterfase.label1);
        Menu.add(jMenuItem);
    }

    public JMenu getMenu() {
        return Menu;
    }

    public void setMenu(JMenu Menu) {
        this.Menu = Menu;
    }

    public JMenuItem getjMenuItem() {
        return jMenuItem;
    }

    public void setjMenuItem(JMenuItem jMenuItem) {
        this.jMenuItem = jMenuItem;
    }

    public JMenuBar getMenubar() {
        return Menubar;
    }

    public void setMenubar(JMenuBar Menubar) {
        this.Menubar = Menubar;
    }

}
