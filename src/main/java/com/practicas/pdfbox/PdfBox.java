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
        
        String rutaPlantilla = "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\CartillaIdentidad.pdf";
        String rutaGuardadoDocumento = "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\formulario-autocompletado.pdf";
        
        Persona persona = new Persona();
        
        generadorPDF autoCompletadorPDF = new generadorPDF(persona, rutaPlantilla);
        autoCompletadorPDF.AutoCompletarPDF(rutaGuardadoDocumento);
        
    }

}

