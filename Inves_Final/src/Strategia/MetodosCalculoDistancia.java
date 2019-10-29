/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategia;

import Abstractos.ClasesSistemas;

/**
 *
 * @author cesard.chacond
 */
public abstract class MetodosCalculoDistancia extends ClasesSistemas implements MetodosCalculo{
    protected boolean CAB = true;
    protected boolean SinAB = true;

    public boolean isCAB() {
        return CAB;
    }

    public void setCAB(boolean CAB) {
        this.CAB = CAB;
    }

    public boolean isSinAB() {
        return SinAB;
    }

    public void setSinAB(boolean SinAB) {
        this.SinAB = SinAB;
    }
    
}
