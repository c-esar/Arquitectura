/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosImp;

import Abstractos.Metodos;
import static Constantes.Constantes.Capacidad_Vehiculo;
import static Constantes.Constantes.Nodos_Con_Ahorro;
import static Constantes.Constantes.Nodos_Directos;
import static Constantes.Constantes.Nodos_Sin_Ahorro;
import static Constantes.Constantes.Nodos_Superan_Capacidad_Vehiculo;
import static Constantes.Constantes.Volumen_Vehiculo;
import Exception.NewExceptionCreacionDatos;
import Abstractos.MetodosCarga;
import CargaDatosExcel.MetodosCargaImp;
import CargaDatosExcel.MetodosExcelImp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David
 */
public class EjecucionMetodos {

    private final MetodosCargaImp atributosCarga;
    private final Metodos metodos;
    private boolean CAB = true;
    private boolean SinAB = true;
    private final NewExceptionCreacionDatos expDatos;

    public EjecucionMetodos() throws NewExceptionCreacionDatos {
        this.metodos = new MetodosImp();
        this.expDatos = new NewExceptionCreacionDatos();
        this.atributosCarga = MetodosCargaImp.getInstance();
        CargaVehiculo();
        SacarPesoPorDia();
        inicioProcesos();
        CrearExcel(new MetodosExcelImp());
    }

    /*
    El proceso comienza
     */
    public final void inicioProcesos() throws NewExceptionCreacionDatos {
        String dia = null;
        for (int i = 0; i < atributosCarga.getPesoVolProvedores().size(); i++) {
            dia = "Dia" + String.valueOf(i + 1);
            System.out.println("Ruta para " + dia);
            System.out.println("");
            if (atributosCarga.getPesoTotalPorDia().get(dia).get(Constantes.Constantes.Demanda_kg) < atributosCarga.getCapVolVehiculo().get(Constantes.Constantes.Capacidad_Vehiculo)) {
                System.out.println("Sistema se puede recoger con un solo Camion");
                System.out.println(CrearNodosDirectos(dia));
                ImprimirResultadoSistema(false, dia);
            } else {
                CrearPuntosArrayList(atributosCarga.getDistancias(), dia, atributosCarga.getPesoVolProvedores().size()); // unir parejas con ahorro

                CompararNodosConCapacidadVehiculo(dia, atributosCarga.getCapVolVehiculo().get(Capacidad_Vehiculo), atributosCarga.getCapVolVehiculo().get(Volumen_Vehiculo),
                        atributosCarga.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg), atributosCarga.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Volumen));

                VerificarNodos(dia, atributosCarga.getMatrizPuntos().get(dia), atributosCarga.getNumeroProvedores(),
                        atributosCarga.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg), atributosCarga.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Volumen));

                ImprimirResultado(dia, atributosCarga.getMatrizPuntos().get(dia), atributosCarga.getDistancias(),
                        atributosCarga.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg), atributosCarga.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Volumen));

                ImprimirResultadoSistema(true, dia);
                System.out.println("");
            }

        }
    }

    /*
    Mirar si hay ahorros
     */
    public void CrearPuntosArrayList(double[][] Puntos, String dia, int numeroDias) throws NewExceptionCreacionDatos {
        ArrayList<ArrayList<Double>> CA = new ArrayList<>(); // con ahorro
        ArrayList<ArrayList<Double>> SinA = new ArrayList<>(); // sin ahorro
        boolean NSV = true;
        boolean entreNVS = true;
        try {
            for (int x = 1; x < Puntos.length; x++) {
                for (int y = x + 1; y < Puntos[x].length; y++) {
                    if (NSV) {
                        atributosCarga.getNodosSuperanVehiculo().put(dia, new ArrayList<>());
                        NSV = false;
                    }
                    if (atributosCarga.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg).get(x) > atributosCarga.getCapVolVehiculo().get(Constantes.Constantes.Capacidad_Vehiculo)) {
                        for (int z = 0; z < atributosCarga.getNodosSuperanVehiculo().get(dia).size(); z++) {
                            if (atributosCarga.getNodosSuperanVehiculo().get(dia).get(z) == x) {
                                entreNVS = false;
                                break;
                            }
                        }
                        if (entreNVS) {
                            atributosCarga.getNodosSuperanVehiculo().get(dia).add(x);
                        }
                        entreNVS = true;
                        break;
                    } else if (atributosCarga.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg).get(y) > atributosCarga.getCapVolVehiculo().get(Constantes.Constantes.Capacidad_Vehiculo)) {
                        for (int z = 0; z < atributosCarga.getNodosSuperanVehiculo().get(dia).size(); z++) {
                            if (atributosCarga.getNodosSuperanVehiculo().get(dia).get(z) == y) {
                                entreNVS = false;
                                break;
                            }
                        }
                        if (entreNVS) {
                            atributosCarga.getNodosSuperanVehiculo().get(dia).add(y);
                        }
                        entreNVS = true;
                    } else {
                        double result = metodos.CalcularAhorros(Puntos, x, y);
                        if (result > 0) {
                            if (CAB) {
                                metodos.CrearListas(atributosCarga.getMatrizPuntos(), Nodos_Con_Ahorro, numeroDias, dia);
                                CAB = false;
                            }
                            CA = atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Con_Ahorro);
                            CA.get(0).add((double) x);
                            CA.get(1).add((double) y);
                            CA.get(2).add(result);
                            atributosCarga.getMatrizPuntos().get(dia).put(Nodos_Con_Ahorro, CA);
                        } else {
                            if (SinAB) {
                                metodos.CrearListas(atributosCarga.getMatrizPuntos(), Nodos_Sin_Ahorro, numeroDias, dia);
                                SinAB = false;
                            }
                            SinA = atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Sin_Ahorro);
                            SinA.get(0).add((double) x);
                            SinA.get(1).add((double) y);
                            SinA.get(2).add(result);
                            atributosCarga.getMatrizPuntos().get(dia).put(Nodos_Sin_Ahorro, SinA);
                        }
                    }
                }
            }
            CAB = true;
            SinAB = true;
            System.out.println("Lista de ahorro");
            System.out.println(atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Con_Ahorro)); // cuando queda null no hay ahorro
            System.out.println(atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Sin_Ahorro));
            System.out.println("");
        } catch (Exception e) {
            MetodosCargaImp.getInstance().setError("1");
            throw expDatos;
        }

    }

    /*
    Verificar con la capidad del vehiculo
     */
    private void CompararNodosConCapacidadVehiculo(String dia, Double CapacidadVehiculo, Double Volumen, ArrayList<Double> PesoProvedor, ArrayList<Double> VolumenProvedor) {
        ArrayList<ArrayList<Double>> SinA = new ArrayList<>(); // sin ahorro
        SinA = metodos.CalcularNodosCapacidadVehiculo(atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Con_Ahorro),
                CapacidadVehiculo, Volumen, PesoProvedor, VolumenProvedor,
                atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Sin_Ahorro), dia);
        if (SinA != null) {
            atributosCarga.getMatrizPuntos().get(dia).put(Nodos_Superan_Capacidad_Vehiculo, SinA);
        }
        System.out.println("Milk Run");
        System.out.println(atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Con_Ahorro));

        System.out.println("Superan la capacidad del vehiculo");
        System.out.println(atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Superan_Capacidad_Vehiculo));
        System.out.println("");
    }

    /*
    Verificar que nodos quedan directos
     */
    private void VerificarNodos(String dia, HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos, int numeroProvedores, ArrayList<Double> Peso, ArrayList<Double> Volumen) {
        metodos.VerificarNodosConRuta(matrizPuntos, numeroProvedores, Peso, Volumen, dia);
        System.out.println("Rutas directas");
        System.out.println(atributosCarga.getMatrizPuntos().get(dia).get(Nodos_Directos));
        System.out.println("");
    }

    /*
    imprimir y guardar
     */
    private void ImprimirResultado(String dia, HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos, double[][] distancias, ArrayList<Double> Peso, ArrayList<Double> Volumen) {
        ArrayList<ArrayList<Double>> CA = new ArrayList<>(); // con ahorro
        ArrayList<ArrayList<Double>> SinA = new ArrayList<>(); // puntos directos
        if (matrizPuntos.get(Nodos_Con_Ahorro) != null) {
            CA = metodos.OrdenarAhorro(matrizPuntos.get(Nodos_Con_Ahorro));
        }
        if (matrizPuntos.get(Nodos_Directos) != null) {
            SinA = matrizPuntos.get(Nodos_Directos);
        } else {
            SinA = NodosDirectosSinPuntos(SinA, dia);
            matrizPuntos.put(Nodos_Directos, SinA);
            SinA = matrizPuntos.get(Nodos_Directos);
        }

        metodos.ImprimirPuntos(CA, SinA, distancias, Peso, Volumen, dia);
        System.out.println("Funcionalidades.EvaluarPuntos.ImprimirResultado()");
    }

    /*
    crear excel
     */
    private void CrearExcel(MetodosCarga cargaDatos) {
        cargaDatos.Crear();
    }

    /*
    Metodo cuando no existe nada de ahorro
     */
    private ArrayList<ArrayList<Double>> NodosDirectosSinPuntos(ArrayList<ArrayList<Double>> Sin, String dia) {
        for (int i = 1; i <= atributosCarga.getNumeroProvedores(); i++) {
            Sin.add(new ArrayList<>());
            Sin.get(i - 1).add((double) i);
            Sin.get(i - 1).add(atributosCarga.getPesoVolProvedores().get(dia).get("Demanda  kg").get(i)); //Obtener el peso del punto
            Sin.get(i - 1).add(atributosCarga.getPesoVolProvedores().get(dia).get("Volumen").get(i)); // OBtener el Volumen del punto
        }

        return Sin;
    }

    public final void CargaVehiculo() {
        atributosCarga.getCapVolVehiculo().put(Capacidad_Vehiculo, 30.0);
        atributosCarga.getCapVolVehiculo().put(Volumen_Vehiculo, 100.0);

        //Tener en cuenta que si la capacidad del vehiculo es mayor a la carga, muestre el mensaje
    }

    private void ImprimirResultadoSistema(boolean par, String dia) {
        if (par) {
            System.out.println(" ");
            System.out.println("Resultado del sistema " + " Peso: " + atributosCarga.getCargaTotalSistema().get(Constantes.Constantes.PesoTotalSistema));
            System.out.println("Resultado del sistema " + " Distancia: " + atributosCarga.getCargaTotalSistema().get(Constantes.Constantes.DistanciaTOtalSistema));
            System.out.println("Resultado del sistema " + " Volumen: " + atributosCarga.getCargaTotalSistema().get(Constantes.Constantes.VolumenTotalSistema));
            if (atributosCarga.getNodosSuperanVehiculo().size() > 0) {
                for (int i = 0; i < atributosCarga.getNodosSuperanVehiculo().get(dia).size(); i++) {
                    System.out.println("Nodos Con cargar Mayor al vehiculo Provedor " + atributosCarga.getNodosSuperanVehiculo().get(dia).get(i));
                }
            }
            System.out.println(" ");
        } else {
            System.out.println(" ");
            System.out.println("Resultado del sistema " + " Peso: " + atributosCarga.getPesoTotalPorDia().get(dia).get(Constantes.Constantes.Demanda_kg));
            //System.out.println("Resultado del sistema " + " Distancia: " + atributosCarga.getPesoTotalPorDia().get(dia).get(Constantes.Constantes.DistanciaTOtalSistema));
            System.out.println("Resultado del sistema " + " Volumen: " + atributosCarga.getPesoTotalPorDia().get(dia).get(Constantes.Constantes.Volumen));
            if (atributosCarga.getNodosSuperanVehiculo().size() > 0) {
                for (int i = 0; i < atributosCarga.getNodosSuperanVehiculo().get(dia).size(); i++) {
                    System.out.println("Nodos Con cargar Mayor al vehiculo Provedor " + atributosCarga.getNodosSuperanVehiculo().get(dia).get(i));
                }
            }
            System.out.println(" ");
        }

    }

    private void SacarPesoPorDia() {
        double resultadoPeso = 0.0;
        double resultadoVolumen = 0.0;
        for (int i = 1; i <= atributosCarga.getPesoVolProvedores().size(); i++) {
            atributosCarga.getPesoTotalPorDia().put("Dia" + i, new HashMap<>());
            for (int y = 0; y < atributosCarga.getPesoVolProvedores().get("Dia" + i).get(Constantes.Constantes.Demanda_kg).size(); y++) {
                resultadoPeso += atributosCarga.getPesoVolProvedores().get("Dia" + i).get(Constantes.Constantes.Demanda_kg).get(y);
                resultadoVolumen += atributosCarga.getPesoVolProvedores().get("Dia" + i).get(Constantes.Constantes.Volumen).get(y);
            }
            atributosCarga.getPesoTotalPorDia().get("Dia" + i).put(Constantes.Constantes.Demanda_kg, resultadoPeso);
            atributosCarga.getPesoTotalPorDia().get("Dia" + i).put(Constantes.Constantes.Volumen, resultadoVolumen);
            resultadoPeso = 0.0;
            resultadoVolumen = 0.0;
        }
    }

    private String CrearNodosDirectos(String dia) {
        String imprimir = "";
        for (int i = 1; i <= atributosCarga.getNumeroProvedores(); i++) {

            imprimir += " Posicion " + i + " a ";
        }

        return imprimir += " Posicion 0 ";
    }
}
