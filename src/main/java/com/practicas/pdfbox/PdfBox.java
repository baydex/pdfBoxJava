/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.practicas.pdfbox;

/**
 *
 * @author PRACTICAS
 */
public class PdfBox {

    public static void main(String[] args){
        
        String rutaPlantilla = "C:\\Users\\PRACTICAS\\Downloads\\CartillaIdentidad.pdf";
        String rutaGuardadoDocumento = "C:\\Users\\PRACTICAS\\Downloads\\formulario-autocompletado.pdf";
        
        Usuario usuario = new Usuario();
        
        AutocompletadorPDF autoCompletadorPDF = new AutocompletadorPDF(usuario, rutaPlantilla);
        autoCompletadorPDF.AutoCompletarPDF(rutaGuardadoDocumento);
        
    }

}

