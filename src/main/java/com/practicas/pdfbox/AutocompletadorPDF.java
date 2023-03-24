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
public class AutocompletadorPDF {

    Map<String, String> camposDeTexto;
    Map<String, String> camposDeImagen;
    String rutaPlantilla;
    PDDocument documento;
    PDAcroForm formulario;

    public AutocompletadorPDF(Usuario usuario, String rutaPlantilla) {
        camposDeTexto = usuario.getCamposTexto();
        camposDeImagen = usuario.getCamposImagen();
        this.rutaPlantilla = rutaPlantilla;
    }

    public void AutoCompletarPDF(String rutaGuardadoDocumento) {
        try {
            cargarDocumento();
            cargarFormulario();
            completarCamposDeTexto();
            completarCamposDeImagen();
            guardarDocumento(rutaGuardadoDocumento);
            cerrarDocumento();
        } catch (Exception e) {
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

    private void completarCamposDeTexto() throws IOException {
        for (Map.Entry<String, String> campo : camposDeTexto.entrySet()) {
            rellenarCampoTexto(campo);
        }
    }
    
    private void rellenarCampoTexto(Map.Entry<String, String> campo) throws IOException{
            String clave = campo.getKey();
            String valor = campo.getValue();

            PDField campoPDF = getCampo(clave);
            
            campoPDF.setValue(valor);
    }

    private PDField getCampo(String clave) {
        return formulario.getField(clave);
    }

    private void completarCamposDeImagen() throws IOException {
        for (Map.Entry<String, String> campo : camposDeImagen.entrySet()) {
            rellenarCampoImagen(campo);
        }

    }
    
    private void rellenarCampoImagen(Map.Entry<String, String> campo) throws IOException{  
            String clave = campo.getKey();
            String ruta = campo.getValue();

            PDField campoPDF = getCampo(clave);

            PDImageXObject imagen = cargarImagen(ruta);

            dibujarImagen(campoPDF, imagen);
           
            // El campo flota encima de la imagen dibujada, debe ser reseteada su "apariencia" para que no se superponga sobre la imagen
            ajustarPosicionDeCampo(campoPDF); 
    }

    private PDImageXObject cargarImagen(String ruta) throws IOException {
        File fileImage = new File(ruta);
        return PDImageXObject.createFromFile(fileImage.getAbsolutePath(), documento);
    }
    
    class DatosNuevaImagen{
        PDAnnotationWidget widgets;
        PDPage pagina;

        float height;
        float width;
        float x;
        float y;
        PDPageContentStream.AppendMode appendContent;
        Boolean compress;
        PDRectangle rectangulo;
        public DatosNuevaImagen(PDField campoPDF){
            appendContent = PDPageContentStream.AppendMode.APPEND;
            compress = true;
            
            widgets = campoPDF.getWidgets().get(0);
            
            pagina = widgets.getPage();
            rectangulo = widgets.getRectangle();
            
            guardarCoordenadas();
            guardarTamaño();
            
        }
        private void guardarCoordenadas(){
            x = rectangulo.getLowerLeftX();
            y = rectangulo.getLowerLeftY();
        }
        private void guardarTamaño(){
            height = rectangulo.getHeight();
            width = rectangulo.getWidth();
        }
    }
    
    private void dibujarImagen(PDField campoPDF, PDImageXObject imagen) throws IOException {
        
        DatosNuevaImagen datos = new DatosNuevaImagen(campoPDF);
        
        try (PDPageContentStream contentStream = new PDPageContentStream(documento, datos.pagina, datos.appendContent , datos.compress)) {
            contentStream.drawImage(imagen, datos.x, datos.y, datos.width, datos.height);
        }
    }

    private void ajustarPosicionDeCampo(PDField campoPDF) {
        PDAppearanceDictionary nuevaApariencia = new PDAppearanceDictionary();
        campoPDF.getWidgets().get(0).setAppearance(nuevaApariencia);
    }

    private void guardarDocumento(String rutaGuardadoDocumento) throws IOException {
        documento.save(rutaGuardadoDocumento);
    }

    private void cerrarDocumento() throws IOException {
        documento.close();
    }
}
