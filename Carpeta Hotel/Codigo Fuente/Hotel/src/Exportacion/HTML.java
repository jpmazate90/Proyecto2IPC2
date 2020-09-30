package Exportacion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HTML {// constantes que serviran para el lenguaje 

    private static final String ESTILO_TABLA = "<table style=\"border-collapse: collapse;\">";
    private static final String INICIO_OPERADOR_TR = "<tr>";
    private static final String FIN_OPERADOR_TR = "</tr>";
    private static final String ESTILO_LINEA_INICIAL = "<th style=\"border: 1px solid #000000;\">";
    private static final String FIN_OPERADOR_TH = "</th>";// constantes que serviran para el lenguaje 
    private static final String ESTILO_LINEA_ATRIBUTO = "<td style=\"border: 1px solid #000000;\">";
    private static final String FIN_OPERADOR_TD = "</td>";
    private static final String FIN_OPERADOR_TABLA = "</table>";
    private static final String INICIO_OPERADOR_HTML = "<html>";
    private static final String FIN_OPERADOR_HTML = "</html>";// constantes que serviran para el lenguaje 
    private static final String INICIO_OPERADOR_H = "<h1>";
    private static final String FIN_OPERADOR_H = "</h1>";
    private static final String INICIO_OPERADOR_P = "<p>";// constante de la clase
    private static final String FIN_OPERADOR_P = "</p>";
    private static final String PLANTILLA_ID = "ID";// constantes que serviran para el lenguaje 
    private static final String PLANTILLA_FECHA_INICIO = "FECHA INICIO";
    private static final String PLANTILLA_FECHA_FINAL = "FECHA FINAL";
    private static final String PLANTILLA_TOTAL = "TOTAL";
    private static final String PLANTILLA_TABLA_CONSUMOS = "TABLA DE CONSUMOS";
    private static final String PLANTILLA_TABLA_ALOJAMIENTOS = "TABLA ALOJAMIENTOS";
    private static final String PLANTILLA_ID_CONSUMO = "ID CONSUMO";
    private static final String PLANTILLA_ID_ALOJAMIENTO = "ID ALOJAMIENTO";// constante de la clase
    private static final String PLANTILLA_NOMBRE_COMIDA = "NOMBRE COMIDA";
    private static final String PLANTILLA_PRECIO_COMIDA = "PRECIO COMIDA";// constante de la clase
    private static final String PLANTILLA_CANTIDAD = "CANTIDAD";
    private static final String PLANTILLA_FECHA_CONSUMO = "FECHA CONSUMO";// constante de la clase
    private static final String PLANTILLA_ID_RESERVACION = "ID RESERVACION";
    private static final String PLANTILLA_FECHA_ENTRADA = "FECHA ENTRADA";// constante de la clase
    private static final String PLANTILLA_FECHA_SALIDA = "FECHA SALIDA";
    private static final String PLANTILLA_PRECIO = "PRECIO";// constante de la clase
    private static final String PLANTILLA_TOTAL_ALOJAMIENTO = "TOTAL DEL ALOJAMIENTO";
    private static final String PLANTILLA_TOTAL_CONSUMO = "TOTAL CONSUMO";
    private static final String PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE = "USUARIO QUE REGISTRO EL REPORTE";
     private static final String PLANTILLA_DPI_CLIENTE = "DPI CLIENTE";// constante de la clase
     private static final String PLANTILLA_ID_HABITACION = "ID HABITACION";// constante de la clase
     private static final String PLANTILLA_ID_HABITACION_MAS_POPULAR = "ID HABITACION MAS POPULAR";
     private static final String PLANTILLA_FECHA_ESPECIFICA = "FECHA ESPECIFICA";// constante de la clase
     private static final String PLANTILLA_TABLA_HABITACIONES_OCUPADAS = "TABLA HABITACIONES OCUPADAS";
     private static final String PLANTILLA_TABLA_HABITACIONES_DISPONIBLES = "TABLA HABITACIONES DISPONIBLES";
     private static final String PLANTILLA_HABITACION= "HABITACION";// constante de la clase
     private static final String PLANTILLA_NIVEL= "NIVEL";// constante de la clase
     private static final String PLANTILLA_OCUPADO= "OCUPADO";
     private static final String PLANTILLA_NOMBRE = "NOMBRE";// constante de la clase
     private static final String PLANTILLA_APELLIDO = "APELLIDO";
     private static final String PLANTILLA_TABLA_CLIENTES = "TABLA CLIENTES";
     private static final String PLANTILLA_DPI = "DPI";// constante de la clase
     private static final String PLANTILLA_NIT = "NIT";// constante de la clase
     
     

    // crea un objeto filechooser para escoger
    public static File usarFileChooser() {
        JFileChooser guardarComo = new JFileChooser();
        guardarComo.setApproveButtonText("Guardar");
        guardarComo.showSaveDialog(null);
        File archivo = new File(guardarComo.getSelectedFile() + ".html");
        return archivo;
    }

    // dice si se genero el archivo correctamente
    public static void decirQueSeGeneroElArchivo() {
        JOptionPane.showMessageDialog(null, "Se genero el archivo html correctamente");
    }

    public static void generarTitulo(File archivo, String titulo) {
        try {// abre la via para escribir en el archivo
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // crea unicamente el titulo de un archivo
            escritor.write(INICIO_OPERADOR_HTML);
            escritor.write(INICIO_OPERADOR_H);
            escritor.write(titulo);
            escritor.write(FIN_OPERADOR_H);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);

            escritor.flush();
            escritor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generarReporte0(File archivo, DefaultTableModel modelo, DefaultTableModel modelo2, String total1, String total2, String fechaInicial, String fechaFinal, String usuario) {
        try {// abre el archivo para su uso
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_FECHA_INICIO + ": " + fechaInicial);
            escritor.write(FIN_OPERADOR_P);// mete fecha inicial
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_FECHA_FINAL + ": " + fechaFinal);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_CONSUMOS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas = {PLANTILLA_ID_CONSUMO, PLANTILLA_ID_ALOJAMIENTO, PLANTILLA_NOMBRE_COMIDA, PLANTILLA_PRECIO_COMIDA, PLANTILLA_CANTIDAD, PLANTILLA_FECHA_CONSUMO, PLANTILLA_TOTAL_CONSUMO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[7];
                datos[0] =modelo.getValueAt(i, 0).toString();
                datos[1] =modelo.getValueAt(i, 1).toString();
                datos[2] =modelo.getValueAt(i, 2).toString();
                datos[3] =modelo.getValueAt(i, 3).toString();
                datos[4] =modelo.getValueAt(i, 4).toString();
                datos[5] =modelo.getValueAt(i, 5).toString();
                datos[6] =modelo.getValueAt(i, 6).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TOTAL+": "+ total1);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_ALOJAMIENTOS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas2 = {PLANTILLA_ID_ALOJAMIENTO,PLANTILLA_ID_RESERVACION,PLANTILLA_FECHA_ENTRADA,PLANTILLA_FECHA_SALIDA,PLANTILLA_PRECIO,PLANTILLA_TOTAL_ALOJAMIENTO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas2.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas2[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo2.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[6];
                datos[0] = modelo2.getValueAt(i, 0).toString();
                datos[1] = modelo2.getValueAt(i, 1).toString();
                datos[2] = modelo2.getValueAt(i, 2).toString();
                datos[3] = modelo2.getValueAt(i, 3).toString();
                datos[4] = modelo2.getValueAt(i, 4).toString();
                datos[5] = modelo2.getValueAt(i, 5).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TOTAL+": "+ total2);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
             escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);
            escritor.flush();
            escritor.close();

            decirQueSeGeneroElArchivo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void generarReporte1(File archivo, DefaultTableModel modelo, DefaultTableModel modelo2, String total1, String total2, String fechaInicial, String fechaFinal,String cliente, String usuario) {
        try {// abre el archivo para su uso
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_FECHA_INICIO + ": " + fechaInicial);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_FECHA_FINAL + ": " + fechaFinal);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_DPI_CLIENTE + ": " + cliente);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_CONSUMOS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas = {PLANTILLA_ID_CONSUMO, PLANTILLA_ID_ALOJAMIENTO, PLANTILLA_NOMBRE_COMIDA, PLANTILLA_PRECIO_COMIDA, PLANTILLA_CANTIDAD, PLANTILLA_FECHA_CONSUMO, PLANTILLA_TOTAL_CONSUMO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[7];
                datos[0] =modelo.getValueAt(i, 0).toString();
                datos[1] =modelo.getValueAt(i, 1).toString();
                datos[2] =modelo.getValueAt(i, 2).toString();
                datos[3] =modelo.getValueAt(i, 3).toString();
                datos[4] =modelo.getValueAt(i, 4).toString();
                datos[5] =modelo.getValueAt(i, 5).toString();
                datos[6] =modelo.getValueAt(i, 6).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TOTAL+": "+ total1);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_ALOJAMIENTOS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas2 = {PLANTILLA_ID_ALOJAMIENTO,PLANTILLA_ID_RESERVACION,PLANTILLA_FECHA_ENTRADA,PLANTILLA_FECHA_SALIDA,PLANTILLA_PRECIO,PLANTILLA_TOTAL_ALOJAMIENTO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas2.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas2[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo2.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[6];
                datos[0] = modelo2.getValueAt(i, 0).toString();
                datos[1] = modelo2.getValueAt(i, 1).toString();
                datos[2] = modelo2.getValueAt(i, 2).toString();
                datos[3] = modelo2.getValueAt(i, 3).toString();
                datos[4] = modelo2.getValueAt(i, 4).toString();
                datos[5] = modelo2.getValueAt(i, 5).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TOTAL+": "+ total2);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
             escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);
            escritor.flush();
            escritor.close();

            decirQueSeGeneroElArchivo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void generarReporte2(File archivo, DefaultTableModel modelo, DefaultTableModel modelo2, String total1, String total2,String idHabitacion, String usuario) {
        try {// abre el archivo para su uso
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_ID_HABITACION + ": " + idHabitacion);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_CONSUMOS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas = {PLANTILLA_ID_CONSUMO, PLANTILLA_ID_ALOJAMIENTO, PLANTILLA_NOMBRE_COMIDA, PLANTILLA_PRECIO_COMIDA, PLANTILLA_CANTIDAD, PLANTILLA_FECHA_CONSUMO, PLANTILLA_TOTAL_CONSUMO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[7];
                datos[0] =modelo.getValueAt(i, 0).toString();
                datos[1] =modelo.getValueAt(i, 1).toString();
                datos[2] =modelo.getValueAt(i, 2).toString();
                datos[3] =modelo.getValueAt(i, 3).toString();
                datos[4] =modelo.getValueAt(i, 4).toString();
                datos[5] =modelo.getValueAt(i, 5).toString();
                datos[6] =modelo.getValueAt(i, 6).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TOTAL+": "+ total1);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_ALOJAMIENTOS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas2 = {PLANTILLA_ID_ALOJAMIENTO,PLANTILLA_ID_RESERVACION,PLANTILLA_FECHA_ENTRADA,PLANTILLA_FECHA_SALIDA,PLANTILLA_PRECIO,PLANTILLA_TOTAL_ALOJAMIENTO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas2.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas2[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo2.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[6];
                datos[0] = modelo2.getValueAt(i, 0).toString();
                datos[1] = modelo2.getValueAt(i, 1).toString();
                datos[2] = modelo2.getValueAt(i, 2).toString();
                datos[3] = modelo2.getValueAt(i, 3).toString();
                datos[4] = modelo2.getValueAt(i, 4).toString();
                datos[5] = modelo2.getValueAt(i, 5).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TOTAL+": "+ total2);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
             escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);
            escritor.flush();
            escritor.close();

            decirQueSeGeneroElArchivo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generarReporte3(File archivo, DefaultTableModel modelo,String idHabitacion, String usuario) {
        try {// abre el archivo para su uso
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_ID_HABITACION_MAS_POPULAR + ": " + idHabitacion);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_ALOJAMIENTOS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas = {PLANTILLA_ID_ALOJAMIENTO,PLANTILLA_ID_RESERVACION,PLANTILLA_FECHA_ENTRADA,PLANTILLA_FECHA_SALIDA,PLANTILLA_PRECIO,PLANTILLA_TOTAL_ALOJAMIENTO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[6];
                datos[0] =modelo.getValueAt(i, 0).toString();
                datos[1] =modelo.getValueAt(i, 1).toString();
                datos[2] =modelo.getValueAt(i, 2).toString();
                datos[3] =modelo.getValueAt(i, 3).toString();
                datos[4] =modelo.getValueAt(i, 4).toString();
                datos[5] =modelo.getValueAt(i, 5).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            
             escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);
            escritor.flush();
            escritor.close();

            decirQueSeGeneroElArchivo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generarReporte4(File archivo, DefaultTableModel modelo,String fechaEspecifica, String usuario) {
        try {// abre el archivo para su uso
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_FECHA_ESPECIFICA + ": " + fechaEspecifica);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_HABITACIONES_OCUPADAS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas = {PLANTILLA_ID_HABITACION,PLANTILLA_HABITACION,PLANTILLA_NIVEL,PLANTILLA_OCUPADO,PLANTILLA_ID_RESERVACION,PLANTILLA_DPI_CLIENTE,PLANTILLA_NOMBRE,PLANTILLA_APELLIDO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[8];
                datos[0] =modelo.getValueAt(i, 0).toString();
                datos[1] =modelo.getValueAt(i, 1).toString();
                datos[2] =modelo.getValueAt(i, 2).toString();
                datos[3] =modelo.getValueAt(i, 3).toString();
                datos[4] =modelo.getValueAt(i, 4).toString();
                datos[5] =modelo.getValueAt(i, 5).toString();
                datos[6] =modelo.getValueAt(i, 6).toString();
                datos[7] =modelo.getValueAt(i, 7).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            
             escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);
            escritor.flush();
            escritor.close();

            decirQueSeGeneroElArchivo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void generarReporte5(File archivo, DefaultTableModel modelo,String fechaEspecifica, String usuario) {
        try {// abre el archivo para su uso
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_FECHA_ESPECIFICA + ": " + fechaEspecifica);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_HABITACIONES_DISPONIBLES);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas = {PLANTILLA_ID_HABITACION,PLANTILLA_HABITACION,PLANTILLA_NIVEL,PLANTILLA_OCUPADO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[4];
                datos[0] =modelo.getValueAt(i, 0).toString();
                datos[1] =modelo.getValueAt(i, 1).toString();
                datos[2] =modelo.getValueAt(i, 2).toString();
                datos[3] =modelo.getValueAt(i, 3).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            
             escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);
            escritor.flush();
            escritor.close();

            decirQueSeGeneroElArchivo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void generarReporte6(File archivo, DefaultTableModel modelo,String fechaInicio,String fechaFinal,String total, String usuario) {
        try {// abre el archivo para su uso
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_FECHA_INICIO + ": " + fechaInicio);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_FECHA_FINAL + ": " + fechaFinal);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_CONSUMOS);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas = {PLANTILLA_ID_CONSUMO, PLANTILLA_ID_ALOJAMIENTO, PLANTILLA_NOMBRE_COMIDA, PLANTILLA_PRECIO_COMIDA, PLANTILLA_CANTIDAD, PLANTILLA_FECHA_CONSUMO, PLANTILLA_TOTAL_CONSUMO};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[7];
                datos[0] =modelo.getValueAt(i, 0).toString();
                datos[1] =modelo.getValueAt(i, 1).toString();
                datos[2] =modelo.getValueAt(i, 2).toString();
                datos[3] =modelo.getValueAt(i, 3).toString();
                datos[4] =modelo.getValueAt(i, 4).toString();
                datos[5] =modelo.getValueAt(i, 5).toString();
                datos[6] =modelo.getValueAt(i, 6).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TOTAL+": "+ total);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            
             escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);
            escritor.flush();
            escritor.close();

            decirQueSeGeneroElArchivo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generarReporte7(File archivo, DefaultTableModel modelo, String usuario) {
        try {// abre el archivo para su uso
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
            

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_TABLA_CLIENTES);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(ESTILO_TABLA);
            escritor.newLine();
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();

            //agarra todo lo de la tabla en un arreglo
            String[] plantillas = {PLANTILLA_DPI,PLANTILLA_NOMBRE,PLANTILLA_APELLIDO,PLANTILLA_NIT};

            //ciclo para agilizar la escritura
            for (int j = 0; j < plantillas.length; j++) {
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor.write(FIN_OPERADOR_TR);
            escritor.newLine();

            // ciclo por el cual añade los valores a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[4];
                datos[0] =modelo.getValueAt(i, 0).toString();
                datos[1] =modelo.getValueAt(i, 1).toString();
                datos[2] =modelo.getValueAt(i, 2).toString();
                datos[3] =modelo.getValueAt(i, 3).toString();
                // añade los datos a la tabla
                for (int k = 0; k < datos.length; k++) {
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(datos[k]);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                escritor.write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor.write(FIN_OPERADOR_TABLA);
            
             escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_EL_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();

            escritor.write(FIN_OPERADOR_HTML);
            escritor.flush();
            escritor.close();

            decirQueSeGeneroElArchivo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
