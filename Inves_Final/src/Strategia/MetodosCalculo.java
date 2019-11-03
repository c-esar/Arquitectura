/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategia;

import CargaDatosExcel.AtributosSistema;
import MetodosImp.MetodosEjecucion;
import MetodosImp.MetodosEjecucionImp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author cesard.chacond
 */
public interface MetodosCalculo{
    
    public AtributosSistema datos = AtributosSistema.getInstance();
    public MetodosEjecucionImp metodosEjecucion = new MetodosEjecucionImp();
    public boolean InicioProceso();
    public void CrearPuntosArrayList(double[][] Puntos, String dia, int numeroDias);
    public void CompararNodosConCapacidadVehiculo(String dia, Double CapacidadVehiculo, Double Volumen, ArrayList<Double> PesoProvedor, ArrayList<Double> VolumenProvedor);
    public void VerificarNodos(String dia, HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos, int numeroProvedores, ArrayList<Double> Peso, ArrayList<Double> Volumen);
    public void ImprimirResultado(String dia, HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos, double[][] distancias, ArrayList<Double> Peso, ArrayList<Double> Volumen);
    public ArrayList<ArrayList<Double>> NodosDirectosSinPuntos(ArrayList<ArrayList<Double>> Sin, String dia);
    public void ImprimirResultadoSistema(boolean par, String dia);
    public void SacarPesoPorDia();
    public String CrearNodosDirectos(String dia);
}
