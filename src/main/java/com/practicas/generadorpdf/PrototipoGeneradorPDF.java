package com.practicas.generadorpdf;


/**
 *
 * @author Alejandro
 */
public class PrototipoGeneradorPDF {

    public static void main(String[] args){
        
        String rutaPlantilla = "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\CartillaIdentidad.pdf";
        String rutaGuardadoDocumento = "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\";
        
        Persona persona = new Persona();

        GeneradorPDF generadorPDF_itext = new GeneradorPDF(persona, rutaPlantilla);
        generadorPDF_itext.AutoCompletarPDF(rutaGuardadoDocumento);

    }
}

