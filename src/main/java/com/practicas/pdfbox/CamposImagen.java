/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practicas.pdfbox;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;

/**
 *
 * @author PRACTICAS
 */
class CamposImagen {

    Map<String, String> camposDeImagen;
    PdfAcroForm formulario;
    PdfDocument documento;
    
    public CamposImagen(Map<String, String> listaCamposImagen, PdfAcroForm formulario, PdfDocument documento) {
        this.camposDeImagen = listaCamposImagen;
        this.formulario = formulario;
        this.documento = documento;
    }

    public void completarCamposDeImagen() throws IOException {
        for (Map.Entry<String, String> campo : camposDeImagen.entrySet()) {
            rellenarCampoImagen(campo);
        }
    }

    private void rellenarCampoImagen(Map.Entry<String, String> campo) throws FileNotFoundException, IOException {
        DatosCampo datosCampo = new DatosCampo(campo);
        
        PdfButtonFormField campoPDF = (PdfButtonFormField) getCampo(datosCampo.nombreCampo);
        
        String imagenBase64 = procesarImagen(datosCampo.valorCampo);
        
        campoPDF.setValue(imagenBase64);
        campoPDF.setBorderWidth(0);
    }
    
    class DatosCampo{
        
        String nombreCampo;
        String valorCampo;
        
        public DatosCampo(Map.Entry<String, String> campo){
            nombreCampo = campo.getKey();
            valorCampo = campo.getValue();
        }
    }   

    private PdfFormField getCampo(String nombreCampo) {
        return formulario.getFormFields().get(nombreCampo);
    }
    
    private String procesarImagen(String valorCampo) throws FileNotFoundException, IOException{
        File imagen = leerImagen(valorCampo);
        return convertirImagenABase64(imagen);
    }
    
    private File leerImagen(String valorCampo) throws FileNotFoundException{
        return new File(valorCampo);
    }
    
    private String convertirImagenABase64(File imagen) throws FileNotFoundException, IOException{

        byte[] imagenBytes = Files.readAllBytes(imagen.toPath());
        String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
        return imagenBase64;
    }
}
