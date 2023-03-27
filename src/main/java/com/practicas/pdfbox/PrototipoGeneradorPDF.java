/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.practicas.pdfbox;


/**
 *
 * @author PRACTICAS
 */
public class PrototipoGeneradorPDF {

    public static void main(String[] args){
        
        String rutaPlantilla = "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\CartillaIdentidad.pdf";
        String rutaGuardadoDocumento = "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\formulario-autocompletado.pdf";
        
        Persona persona = new Persona();

        GeneradorPDF generadorPDF_itext = new GeneradorPDF(persona, rutaPlantilla);
        generadorPDF_itext.AutoCompletarPDF(rutaGuardadoDocumento);

    }

}

