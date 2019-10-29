/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosImp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David
 */
public interface MetodosEjecucion {

    public double CalcularAhorros(double[][] distancia, int PuntoX, int PuntoY);

//    public void MetodoBurbuja(ArrayList<ArrayList<Double>> Puntos); 
    public boolean CalcularPeso(double PuntoX, double PuntoY, double CapVehiculo);

    public boolean CalcularVolumen(double PuntoX, double PuntoY, double CapVolumen);

    public ArrayList<ArrayList<Double>> OrdenarAhorro(ArrayList<ArrayList<Double>> Puntos); // ahorro mayor a menor

    public void CrearListas(HashMap<String, HashMap<String, ArrayList<ArrayList<Double>>>> matrizPuntos, String numero, int dias, String nombreDia);

    public ArrayList<ArrayList<Double>> CalcularNodosCapacidadVehiculo(ArrayList<ArrayList<Double>> CA, Double CapacidadVehiculo, Double Volumen,
            ArrayList<Double> PesoProvedor, ArrayList<Double> VolumenProvedor, ArrayList<ArrayList<Double>> SinA, String dia);

    public void CrearListas();

    public void VerificarNodosConRuta(HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos,
            int numeroProvedores, ArrayList<Double> Peso, ArrayList<Double> Volumen, String dia);

    // imprimir
    public void ImprimirPuntos(ArrayList<ArrayList<Double>> CA, ArrayList<ArrayList<Double>> SinA, 
            double[][] distancias, ArrayList<Double> Peso, ArrayList<Double> Volumen, String dia);

    public double CalcularPesoImprimir(double PuntoX, double PuntoY, double CapVehiculo);

    public double CalcularVolumenImprimir(double PuntoX, double PuntoY, double CapVolumen);

    public double CalcularDistancia(double[][] distancia, int PuntoX, int PuntoY);
}
