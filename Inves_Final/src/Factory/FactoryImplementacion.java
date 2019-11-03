/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import CargaDatosExcel.MetodosCargaExcel;
import Strategia.MetodosCalculo;

/**
 *
 * @author cesard.chacond
 */
public interface FactoryImplementacion {
    public boolean inicioLecturaExcel(MetodosCargaExcel metodosExcel);
    public boolean inicioMetodos(MetodosCalculo a);
}
