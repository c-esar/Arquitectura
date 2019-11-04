/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

import CargaDatosExcel.AtributosSistema;

/**
 *
 * @author David
 */
public abstract class SalidaInterprete {
      
    public void interprete(SalidaInterprete salidaInterprete){
        salidaInterprete.salida();
    }
    public AtributosSistema datos = AtributosSistema.getInstance();
    public abstract boolean salida(); 
}
