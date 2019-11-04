/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;


import Strategia.MetodosCalculo;
import CargaDatosExcel.CargaExcel;

/**
 *
 * @author cesard.chacond
 */
public class FactoryImp implements FactoryImplementacion {

    @Override
    public boolean inicioMetodos(MetodosCalculo calculo) {
        return calculo.InicioProceso();
    }
}
