/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstractos.ClasesSistemas;
import MetodosImp.MetodosEjecucion;
import CargaDatosExcel.AtributosSistema;
import CargaDatosExcel.MetodosCargaExcelImp;
import Exception.ExceptionSistema;
import Exception.NewExceptionCreacionDatos;
import pruebas.EjecucionMetodos;
import Strategia.MetodosCalculo;

/**
 *
 * @author cesard.chacond
 */
public interface FactoryImplementacion {
    public void inicioAplicativo(String parametro, String calculo);
}
