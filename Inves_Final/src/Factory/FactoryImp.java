/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstractos.ClasesSistemas;
import CargaDatosExcel.MetodosCargaExcel;
import CargaDatosExcel.MetodosCargaExcelImp;
import pruebas.EjecucionMetodos;
import Strategia.MetodosCalculo;
import Strategia.MetodosCalculoDistanciaImp;

/**
 *
 * @author cesard.chacond
 */
public class FactoryImp implements FactoryImplementacion {

    public MetodosCargaExcel metodosExcel;
    public MetodosCalculo metodosCalculo;

    public FactoryImp() {
        metodosExcel = new MetodosCargaExcelImp();
    }

    @Override
    public void inicioAplicativo(String parametro, String calculo) {
        metodosExcel.IniciarLecturaExcel(parametro);
        if ("Distancia".equals(calculo)) {
            metodosCalculo = new MetodosCalculoDistanciaImp();
            metodosCalculo.InicioProceso();
        }
    }
}
