
package Exportacion;

import Enumerados.EnumeradoMenu;
import Logica.TablaModelo;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PDF {
 // sirve para exportar la factura
    public static void exportaFactura(TablaModelo modelo, int total, String cliente, String nit,File archivo, int totalAlojamiento){
        Document document = new Document();
         
        try{
            
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();
             // craea un docuent
            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(6);           
            
            PdfPCell celda1 = new PdfPCell(new Paragraph("FACTURA DEL HOTEL"));
            celda1.setColspan(6);
            table.addCell(celda1);
            
            PdfPCell celda2 = new PdfPCell(new Paragraph(EnumeradoMenu.CLIENTE.toString()+": "+cliente));
            celda2.setColspan(6);
            table.addCell(celda2);
            
            PdfPCell celda3 = new PdfPCell(new Paragraph(EnumeradoMenu.NIT.toString()+": "+nit));
            celda3.setColspan(6);
            table.addCell(celda3);
            
            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
            table.addCell(EnumeradoMenu.ID_CONSUMO.toString());
            table.addCell(EnumeradoMenu.ID_ALOJAMIENTO.toString());
            table.addCell(EnumeradoMenu.NOMBRE_COMIDA.toString());
            table.addCell(EnumeradoMenu.PRECIO_COMIDA.toString());
            table.addCell(EnumeradoMenu.CANTIDAD.toString());
            table.addCell(EnumeradoMenu.FECHA_CONSUMO.toString());
             
            for (int i = 0; i < modelo.getRowCount(); i++) {
                table.addCell(modelo.getValueAt(i, 0).toString());
                table.addCell(modelo.getValueAt(i, 1).toString());
                table.addCell(modelo.getValueAt(i, 2).toString());
                table.addCell(modelo.getValueAt(i, 3).toString());
                table.addCell(modelo.getValueAt(i, 4).toString());
                table.addCell(modelo.getValueAt(i, 5).toString());
            }
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
            PdfPCell celdaFinal2 = new PdfPCell(new Paragraph("Subtotal de consumo: "+total));
            celdaFinal2.setColspan(6);
            table.addCell(celdaFinal2);
            
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Subtotal de alojamiento "+totalAlojamiento));
            celdaFinal.setColspan(6);
            table.addCell(celdaFinal);
            
            int totalFinal = total+totalAlojamiento;
            PdfPCell celdaFinal3 = new PdfPCell(new Paragraph("Total de pago: "+totalFinal));
            celdaFinal3.setColspan(6);
            table.addCell(celdaFinal3);
             
            
             
            // Agregamos la tabla al documento            
            document.add(table);
             
            document.close();
            JOptionPane.showMessageDialog(null, "Se genero el archivo pdf para la factura correctamente");
             
        }catch(Exception e)
        {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
        }
    }
    // usa el file chooser
    public static File usarFileChooser(String extension){
        JFileChooser guardarComo = new JFileChooser();
        guardarComo.setApproveButtonText("Guardar");
        guardarComo.showSaveDialog(null);
        File archivo = new File (guardarComo.getSelectedFile()+extension);
        return archivo;
    }
    
}
