/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practicas.pdfbox;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author PRACTICAS
 */
public class GeneradorPDF {

    String rutaPlantilla;
    CamposTexto camposTexto;
    CamposImagen camposImagen;
    PdfDocument documento;
    PdfAcroForm formulario;
    Persona persona;
    
    public GeneradorPDF(Persona persona, String rutaPlantilla) {
        this.persona = persona;
        this.rutaPlantilla = rutaPlantilla;
    }

    public void AutoCompletarPDF(String rutaGuardadoDocumento) {
        try {
            cargarDocumento(rutaGuardadoDocumento);
            cargarFormulario();
            cargarCamposTexto();
            cargarCamposImagen();
            completarCamposTexto();
            completarCamposImagen();
            cerrarDocumento();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void cargarDocumento(String rutaGuardadoDocumento) throws IOException {
        File plantilla = new File(this.rutaPlantilla);
        File archivoFinal = new File(rutaGuardadoDocumento);

        documento = new PdfDocument(new PdfReader(plantilla), new PdfWriter(archivoFinal));
    }
    
    private void cargarFormulario() {
        formulario = PdfAcroForm.getAcroForm(documento, true);
    }

    private void cargarCamposTexto(){
        Map<String, String> listaCamposTexto = persona.getCamposTexto();
        camposTexto = new CamposTexto(listaCamposTexto, formulario);
    }
    
    private void cargarCamposImagen(){
        Map<String, String> listaCamposImagen = persona.getCamposImagen();
        camposImagen = new CamposImagen(listaCamposImagen, formulario, documento);
    }
    
    private void completarCamposTexto() throws IOException{
        camposTexto.completarCamposDeTexto();
    }
    
    private void completarCamposImagen() throws IOException{
        camposImagen.completarCamposDeImagen();
    }


    private void cerrarDocumento() throws IOException {
        documento.close();
    }

    
    
     
}
