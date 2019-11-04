///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pruebas;
//
//import static Constantes.Constantes.Capacidad_Vehiculo;
//import static Constantes.Constantes.Nodos_Con_Ahorro;
//import static Constantes.Constantes.Nodos_Directos;
//import static Constantes.Constantes.Nodos_Sin_Ahorro;
//import static Constantes.Constantes.Nodos_Superan_Capacidad_Vehiculo;
//import static Constantes.Constantes.Volumen_Vehiculo;
//import Exception.NewExceptionCreacionDatos;
//import CargaDatosExcel.AtributosSistema;
//import CargaDatosExcel.CargaExcelImp;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import MetodosImp.MetodosEjecucionImp;
//import CargaDatosExcel.CargaExcel;
//
///**
// *
// * @author David
// */
//public class EjecucionMetodos {
//    private boolean CAB = true;
//    private boolean SinAB = true;
//    private AtributosSistema datos;
//    private MetodosEjecucionImp metodosEjecucion;
//
//    public EjecucionMetodos() {
//        this.metodosEjecucion = new MetodosEjecucionImp();
//        this.datos = AtributosSistema.getInstance();
//    }
//
//    public void inicioMetodos() {
//        CargaVehiculo();
//        SacarPesoPorDia();
//        try {
//            inicioProcesos();
//        } catch (NewExceptionCreacionDatos ex) {
//            Logger.getLogger(EjecucionMetodos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //CrearExcel(new MetodosCargaExcelImp());
//    }
//
//    /*
//    El proceso comienza
//     */
//    public final void inicioProcesos() throws NewExceptionCreacionDatos {
//        String dia = null;
//        for (int i = 0; i < datos.getPesoVolProvedores().size(); i++) {
//            dia = "Dia" + String.valueOf(i + 1);
//            System.out.println("Ruta para " + dia);
//            System.out.println("");
//            if (datos.getPesoTotalPorDia().get(dia).get(Constantes.Constantes.Demanda_kg) < datos.getCapVolVehiculo().get(Constantes.Constantes.Capacidad_Vehiculo)) {
//                System.out.println("Sistema se puede recoger con un solo Camion");
//                System.out.println(CrearNodosDirectos(dia));
//                ImprimirResultadoSistema(false, dia);
//            } else {
//                CrearPuntosArrayList(datos.getDistancias(), dia, datos.getPesoVolProvedores().size()); // unir parejas con ahorro
//
//                CompararNodosConCapacidadVehiculo(dia, datos.getCapVolVehiculo().get(Capacidad_Vehiculo), datos.getCapVolVehiculo().get(Volumen_Vehiculo),
//                        datos.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg), datos.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Volumen));
//
//                VerificarNodos(dia, datos.getMatrizPuntos().get(dia), datos.getNumeroProvedores(),
//                        datos.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg), datos.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Volumen));
//
//                ImprimirResultado(dia, datos.getMatrizPuntos().get(dia), datos.getDistancias(),
//                        datos.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg), datos.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Volumen));
//
//                ImprimirResultadoSistema(true, dia);
//                System.out.println("");
//            }
//
//        }
//    }
//
//    /*
//    Mirar si hay ahorros
//     */
//    public void CrearPuntosArrayList(double[][] Puntos, String dia, int numeroDias) throws NewExceptionCreacionDatos {
//        ArrayList<ArrayList<Double>> CA = new ArrayList<>(); // con ahorro
//        ArrayList<ArrayList<Double>> SinA = new ArrayList<>(); // sin ahorro
//        boolean NSV = true;
//        boolean entreNVS = true;
//        try {
//            for (int x = 1; x < Puntos.length; x++) {
//                for (int y = x + 1; y < Puntos[x].length; y++) {
//                    if (NSV) {
//                        datos.getNodosSuperanVehiculo().put(dia, new ArrayList<>());
//                        NSV = false;
//                    }
//                    if (datos.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg).get(x) > datos.getCapVolVehiculo().get(Constantes.Constantes.Capacidad_Vehiculo)) {
//                        for (int z = 0; z < datos.getNodosSuperanVehiculo().get(dia).size(); z++) {
//                            if (datos.getNodosSuperanVehiculo().get(dia).get(z) == x) {
//                                entreNVS = false;
//                                break;
//                            }
//                        }
//                        if (entreNVS) {
//                            datos.getNodosSuperanVehiculo().get(dia).add(x);
//                        }
//                        entreNVS = true;
//                        break;
//                    } else if (datos.getPesoVolProvedores().get(dia).get(Constantes.Constantes.Demanda_kg).get(y) > datos.getCapVolVehiculo().get(Constantes.Constantes.Capacidad_Vehiculo)) {
//                        for (int z = 0; z < datos.getNodosSuperanVehiculo().get(dia).size(); z++) {
//                            if (datos.getNodosSuperanVehiculo().get(dia).get(z) == y) {
//                                entreNVS = false;
//                                break;
//                            }
//                        }
//                        if (entreNVS) {
//                            datos.getNodosSuperanVehiculo().get(dia).add(y);
//                        }
//                        entreNVS = true;
//                    } else {
//                        double result = metodosEjecucion.CalcularAhorros(Puntos, x, y);
//                        if (result > 0) {
//                            if (CAB) {
//                                metodosEjecucion.CrearListas(datos.getMatrizPuntos(), Nodos_Con_Ahorro, numeroDias, dia);
//                                CAB = false;
//                            }
//                            CA = datos.getMatrizPuntos().get(dia).get(Nodos_Con_Ahorro);
//                            CA.get(0).add((double) x);
//                            CA.get(1).add((double) y);
//                            CA.get(2).add(result);
//                            datos.getMatrizPuntos().get(dia).put(Nodos_Con_Ahorro, CA);
//                        } else {
//                            if (SinAB) {
//                                metodosEjecucion.CrearListas(datos.getMatrizPuntos(), Nodos_Sin_Ahorro, numeroDias, dia);
//                                SinAB = false;
//                            }
//                            SinA = datos.getMatrizPuntos().get(dia).get(Nodos_Sin_Ahorro);
//                            SinA.get(0).add((double) x);
//                            SinA.get(1).add((double) y);
//                            SinA.get(2).add(result);
//                            datos.getMatrizPuntos().get(dia).put(Nodos_Sin_Ahorro, SinA);
//                        }
//                    }
//                }
//            }
//            CAB = true;
//            SinAB = true;
//            System.out.println("Lista de ahorro");
//            System.out.println(datos.getMatrizPuntos().get(dia).get(Nodos_Con_Ahorro)); // cuando queda null no hay ahorro
//            System.out.println(datos.getMatrizPuntos().get(dia).get(Nodos_Sin_Ahorro));
//            System.out.println("");
//        } catch (Exception e) {
//            AtributosSistema.getInstance().setError("1");
//
//        }
//
//    }
//
//    /*
//    Verificar con la capidad del vehiculo
//     */
//    private void CompararNodosConCapacidadVehiculo(String dia, Double CapacidadVehiculo, Double Volumen, ArrayList<Double> PesoProvedor, ArrayList<Double> VolumenProvedor) {
//        ArrayList<ArrayList<Double>> SinA = new ArrayList<>(); // sin ahorro
//        SinA = metodosEjecucion.CalcularNodosCapacidadVehiculo(datos.getMatrizPuntos().get(dia).get(Nodos_Con_Ahorro),
//                CapacidadVehiculo, Volumen, PesoProvedor, VolumenProvedor,
//                datos.getMatrizPuntos().get(dia).get(Nodos_Sin_Ahorro), dia);
//        if (SinA != null) {
//            datos.getMatrizPuntos().get(dia).put(Nodos_Superan_Capacidad_Vehiculo, SinA);
//        }
//        System.out.println("Milk Run");
//        System.out.println(datos.getMatrizPuntos().get(dia).get(Nodos_Con_Ahorro));
//
//        System.out.println("Superan la capacidad del vehiculo");
//        System.out.println(datos.getMatrizPuntos().get(dia).get(Nodos_Superan_Capacidad_Vehiculo));
//        System.out.println("");
//    }
//
//    /*
//    Verificar que nodos quedan directos
//     */
//    private void VerificarNodos(String dia, HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos, int numeroProvedores, ArrayList<Double> Peso, ArrayList<Double> Volumen) {
//        metodosEjecucion.VerificarNodosConRuta(matrizPuntos, numeroProvedores, Peso, Volumen, dia);
//        System.out.println("Rutas directas");
//        System.out.println(datos.getMatrizPuntos().get(dia).get(Nodos_Directos));
//        System.out.println("");
//    }
//
//    /*
//    imprimir y guardar
//     */
//    private void ImprimirResultado(String dia, HashMap<String, ArrayList<ArrayList<Double>>> matrizPuntos, double[][] distancias, ArrayList<Double> Peso, ArrayList<Double> Volumen) {
//        ArrayList<ArrayList<Double>> CA = new ArrayList<>(); // con ahorro
//        ArrayList<ArrayList<Double>> SinA = new ArrayList<>(); // puntos directos
//        if (matrizPuntos.get(Nodos_Con_Ahorro) != null) {
//            CA = metodosEjecucion.OrdenarAhorro(matrizPuntos.get(Nodos_Con_Ahorro));
//        }
//        if (matrizPuntos.get(Nodos_Directos) != null) {
//            SinA = matrizPuntos.get(Nodos_Directos);
//        } else {
//            SinA = NodosDirectosSinPuntos(SinA, dia);
//            matrizPuntos.put(Nodos_Directos, SinA);
//            SinA = matrizPuntos.get(Nodos_Directos);
//        }
//
//        metodosEjecucion.ImprimirPuntos(CA, SinA, distancias, Peso, Volumen, dia);
//        System.out.println("Funcionalidades.EvaluarPuntos.ImprimirResultado()");
//    }
//
//    /*
//    crear excel
//     */
//    private void CrearExcel(CargaExcel cargaDatos) {
//        cargaDatos.Crear();
//    }
//
//    /*
//    Metodo cuando no existe nada de ahorro
//     */
//    private ArrayList<ArrayList<Double>> NodosDirectosSinPuntos(ArrayList<ArrayList<Double>> Sin, String dia) {
//        for (int i = 1; i <= datos.getNumeroProvedores(); i++) {
//            Sin.add(new ArrayList<>());
//            Sin.get(i - 1).add((double) i);
//            Sin.get(i - 1).add(datos.getPesoVolProvedores().get(dia).get("Demanda  kg").get(i)); //Obtener el peso del punto
//            Sin.get(i - 1).add(datos.getPesoVolProvedores().get(dia).get("Volumen").get(i)); // OBtener el Volumen del punto
//        }
//
//        return Sin;
//    }
//
//    public final void CargaVehiculo() {
//        datos.getCapVolVehiculo().put(Capacidad_Vehiculo, 30.0);
//        datos.getCapVolVehiculo().put(Volumen_Vehiculo, 100.0);
//
//        //Tener en cuenta que si la capacidad del vehiculo es mayor a la carga, muestre el mensaje
//    }
//
//    private void ImprimirResultadoSistema(boolean par, String dia) {
//        if (par) {
//            System.out.println(" ");
//            System.out.println("Resultado del sistema " + " Peso: " + datos.getCargaTotalSistema().get(Constantes.Constantes.PesoTotalSistema));
//            System.out.println("Resultado del sistema " + " Distancia: " + datos.getCargaTotalSistema().get(Constantes.Constantes.DistanciaTOtalSistema));
//            System.out.println("Resultado del sistema " + " Volumen: " + datos.getCargaTotalSistema().get(Constantes.Constantes.VolumenTotalSistema));
//            if (datos.getNodosSuperanVehiculo().size() > 0) {
//                for (int i = 0; i < datos.getNodosSuperanVehiculo().get(dia).size(); i++) {
//                    System.out.println("Nodos Con cargar Mayor al vehiculo Provedor " + datos.getNodosSuperanVehiculo().get(dia).get(i));
//                }
//            }
//            System.out.println(" ");
//        } else {
//            System.out.println(" ");
//            System.out.println("Resultado del sistema " + " Peso: " + datos.getPesoTotalPorDia().get(dia).get(Constantes.Constantes.Demanda_kg));
//            //System.out.println("Resultado del sistema " + " Distancia: " + atributosCarga.getPesoTotalPorDia().get(dia).get(Constantes.Constantes.DistanciaTOtalSistema));
//            System.out.println("Resultado del sistema " + " Volumen: " + datos.getPesoTotalPorDia().get(dia).get(Constantes.Constantes.Volumen));
//            if (datos.getNodosSuperanVehiculo().size() > 0) {
//                for (int i = 0; i < datos.getNodosSuperanVehiculo().get(dia).size(); i++) {
//                    System.out.println("Nodos Con cargar Mayor al vehiculo Provedor " + datos.getNodosSuperanVehiculo().get(dia).get(i));
//                }
//            }
//            System.out.println(" ");
//        }
//
//    }
//
//    private void SacarPesoPorDia() {
//        double resultadoPeso = 0.0;
//        double resultadoVolumen = 0.0;
//        for (int i = 1; i <= datos.getPesoVolProvedores().size(); i++) {
//            datos.getPesoTotalPorDia().put("Dia" + i, new HashMap<>());
//            for (int y = 0; y < datos.getPesoVolProvedores().get("Dia" + i).get(Constantes.Constantes.Demanda_kg).size(); y++) {
//                resultadoPeso += datos.getPesoVolProvedores().get("Dia" + i).get(Constantes.Constantes.Demanda_kg).get(y);
//                resultadoVolumen += datos.getPesoVolProvedores().get("Dia" + i).get(Constantes.Constantes.Volumen).get(y);
//            }
//            datos.getPesoTotalPorDia().get("Dia" + i).put(Constantes.Constantes.Demanda_kg, resultadoPeso);
//            datos.getPesoTotalPorDia().get("Dia" + i).put(Constantes.Constantes.Volumen, resultadoVolumen);
//            resultadoPeso = 0.0;
//            resultadoVolumen = 0.0;
//        }
//    }
//
//    private String CrearNodosDirectos(String dia) {
//        String imprimir = "";
//        for (int i = 1; i <= datos.getNumeroProvedores(); i++) {
//
//            imprimir += " Posicion " + i + " a ";
//        }
//
//        return imprimir += " Posicion 0 ";
//    }
//}
