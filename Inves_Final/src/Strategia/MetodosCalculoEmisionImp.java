/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategia;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author cesard.chacond
 */
public class MetodosCalculoEmisionImp extends MetodosCalculoEmision {

    @Override
    public boolean InicioProceso() {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
        return true;
    }

    @Override
    public void CrearPuntosArrayList(double[][] Puntos, String dia, int numeroDias) {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
    }

    @Override
    public void CompararNodosConCapacidadVehiculo(String dia, Double CapacidadVehiculo, Double Volumen, ArrayList<Double> PesoProvedor, ArrayList<Double> VolumenProvedor) {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
    }

    @Override
    public void VerificarNodos(String dia, HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos, int numeroProvedores, ArrayList<Double> Peso, ArrayList<Double> Volumen) {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
    }

    @Override
    public void ImprimirResultado(String dia, HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos, double[][] distancias, ArrayList<Double> Peso, ArrayList<Double> Volumen) {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
    }

    @Override
    public ArrayList<ArrayList<Double>> NodosDirectosSinPuntos(ArrayList<ArrayList<Double>> Sin, String dia) {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
        return null;
    }

    @Override
    public void ImprimirResultadoSistema(boolean par, String dia) {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
    }

    @Override
    public void SacarPesoPorDia() {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
    }

    @Override
    public String CrearNodosDirectos(String dia) {
        System.out.println("Strategia.MetodosCalculoEmisionImp.InicioProceso()");
        return null;
    }

}
