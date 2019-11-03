/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaDatosExcel;

import Constantes.Constantes;
import Exception.NewExceptionExcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Exception.ExceptionSistema;

/**
 *
 * @author David
 */
public class MetodosCargaExcelImp implements MetodosCargaExcel{

    private String file;
    private FileInputStream files;
    private XSSFWorkbook wb;
    private String nombreDelDia;
    private String nombreVariable;
    private boolean entreProvedores = true;
    private int ContadorDia = 0;
    private AtributosSistema datos;

    public MetodosCargaExcelImp(String path) {
        datos = AtributosSistema.getInstance();
        file = path;
    }
    
 @Override
    public boolean IniciarLecturaExcel() {
        try {
            VerificarArchivoExcel();
            Leer();
            files.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean VerificarArchivoExcel() throws NewExceptionExcel, ExceptionSistema {
        try {
            files = new FileInputStream(new File(file));
            wb = new XSSFWorkbook(files);
            return true;
        } catch (FileNotFoundException ex) {
            datos.setError("1");
         
        } catch (IOException ex) {
            datos.setError("1");
            
        }
        return false;
    }

    @Override
    public boolean Leer() {
        XSSFSheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        for (int x = 1; x <= numFilas; x++) {
            Row fila = sheet.getRow(x);
            int numCols = fila.getLastCellNum();
            if (datos.getNumeroProvedores() == 0 && x == 1) {
                datos.setNumeroProvedores(numCols - 3);
            }
            ArrayList<Double> a = new ArrayList<>();
            for (int y = 0, j = 0; y < numCols; y++) {
                Cell celda = fila.getCell(y);
                if (!(celda == null)) {
                    switch (celda.getCellTypeEnum().toString()) {
                        case "NUMERIC": {
                            if (datos.getPesoVolProvedores().containsKey(nombreDelDia)) {
                                a = datos.getPesoVolProvedores().get(nombreDelDia).get(nombreVariable);
                                a.add(celda.getNumericCellValue());
                                datos.getPesoVolProvedores().get(nombreDelDia).put(nombreVariable, a);
                            } else {
                                if (entreProvedores) {
                                    datos.arregloMatriz(datos.getNumeroProvedores() + 1);
                                    entreProvedores = false;
                                }
                                datos.getDistancias()[j][x - 2] = celda.getNumericCellValue();
                                j++;
                            }
                            System.out.print(celda.getNumericCellValue() + " ");
                            break;
                        }
                        case "STRING": {
                            for (String v : datos.getVariablesNombresExcel()) {
                                if (celda.getStringCellValue().contains(v)) {
                                    switch (v) {
                                        case "Dia": {
                                            ContadorDia += 1;
                                            String vi = v + String.valueOf(ContadorDia);
                                            datos.getPesoVolProvedores().put(vi, new HashMap<>());
                                            nombreDelDia = vi;
                                            break;
                                        }
                                        case Constantes.Demanda_kg: {
                                            datos.getPesoVolProvedores().get(nombreDelDia).put(v, new ArrayList<>());
                                            nombreVariable = v;
                                            break;
                                        }
                                        case Constantes.Volumen: {
                                            datos.getPesoVolProvedores().get(nombreDelDia).put(v, new ArrayList<>());
                                            nombreVariable = v;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Error de carga");
                                        }
                                    }

                                }
                            }
                            System.out.print(celda.getStringCellValue() + " ");
                            break;
                        }
                        default: {
                        }
                    }
                }
            }
            System.out.println("");
        }
        //System.out.println(Arrays.toString(datos.getDistancias()));
        return true;
    }

    @Override
    public boolean Crear() {
        int filas = -1;
        int columnas = -1;
        datos = AtributosSistema.getInstance();
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Resultado");

        // Row row = sheet.createRow(sheet.addMergedRegion(new CellRangeAddress(0,0,0,10)));       
        filas++;
        Row row = sheet.createRow(filas);
        columnas++;
        row.createCell(columnas).setCellValue("Secuencia");
        for (int c = 1; c <= datos.getImprimirNodos().size(); c++) {
            filas++;
            Row dia = sheet.createRow(filas);
            columnas++;
            dia.createCell(columnas).setCellValue("Dia" + (c));
//            for (int i = 0; i < datos.getImprimirNodos().get("Dia" + (c)).get(Constantes.Nodos_Con_Ahorro).size(); i++) {
//                Row rown = sheet.createRow(filas++);
//                rown.createCell(columnas++).setCellValue("Ruta" + (i));
////                for (int y = 0; y < datos.getImprimirNodos().get(Constantes.Nodos_Con_Ahorro).get(0).size(); y++) {
////                    rown.createCell(y + 1).setCellValue(datos.getImprimirNodos().get(Constantes.Nodos_Con_Ahorro).get(0).get(y));
////                    rown.createCell(y + 2).setCellValue(datos.getImprimirNodos().get(Constantes.Nodos_Con_Ahorro).get(1).get(y));
////                }
//            }
        }

        try {
            FileOutputStream fileout = new FileOutputStream("Excel.xlsx");
            book.write(fileout);
            fileout.close();
        } catch (FileNotFoundException ex) {
           
        } catch (IOException ex) {
           
        }
        return false;

    }

}


//command
