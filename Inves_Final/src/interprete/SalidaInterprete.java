/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

/**
 *
 * @author David
 */
public abstract class SalidaInterprete implements Constantes.Constantes{
    
    public void interprete(SalidaInterprete salidaInterprete){
        salidaInterprete.salida();
    }
    public abstract boolean salida(); 
}
