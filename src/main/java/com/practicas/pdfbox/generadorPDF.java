/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practicas.pdfbox;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

/**
 *
 * @author PRACTICAS
 */
public class generadorPDF {

    CamposTexto camposTexto;
    CamposImagen camposImagen;
    String rutaPlantilla;
    PDDocument documento;
    PDAcroForm formulario;

    public generadorPDF(Persona persona, String rutaPlantilla) {
        
        Map<String, String> listaCamposTexto = persona.getCamposTexto();
        camposTexto = new CamposTexto(listaCamposTexto);
        
        Map<String, String> listaCamposImagen = persona.getCamposImagen();
        camposImagen = new CamposImagen(listaCamposImagen);
        
        this.rutaPlantilla = rutaPlantilla;
    }

    public void AutoCompletarPDF(String rutaGuardadoDocumento) {
        try {
            cargarDocumento();
            cargarFormulario();
            camposTexto.completarCamposDeTexto(formulario);
            camposImagen.completarCamposDeImagen(formulario, documento);
            guardarDocumento(rutaGuardadoDocumento);
            cerrarDocumento();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void cargarDocumento() throws IOException {
        File file = new File(this.rutaPlantilla);

        documento = PDDocument.load(file);
    }

    private void cargarFormulario() {
        formulario = documento.getDocumentCatalog().getAcroForm();
    }

    private void guardarDocumento(String rutaGuardadoDocumento) throws IOException {
        documento.save(rutaGuardadoDocumento);
    }

    private void cerrarDocumento() throws IOException {
        documento.close();
    }
   
}
