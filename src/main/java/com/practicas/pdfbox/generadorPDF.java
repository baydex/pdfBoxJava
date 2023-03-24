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

    Map<String, String> camposDeTexto;
    Map<String, String> camposDeImagen;
    String rutaPlantilla;
    PDDocument documento;
    PDAcroForm formulario;

    public generadorPDF(Persona persona, String rutaPlantilla) {
        camposDeTexto = persona.getCamposTexto();
        camposDeImagen = persona.getCamposImagen();
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

    private void completarCamposDeTexto() throws IOException {
        for (Map.Entry<String, String> campo : camposDeTexto.entrySet()) {
            rellenarCampoTexto(campo);
        }
    }

    private void rellenarCampoTexto(Map.Entry<String, String> campo) throws IOException {
        String nombreCampo = campo.getKey();
        String valorCampo = campo.getValue();

        PDField campoPDF = getCampo(nombreCampo);

        campoPDF.setValue(valorCampo);
    }

    private PDField getCampo(String nombreCampo) {
        return formulario.getField(nombreCampo);
    }

    private void completarCamposDeImagen() throws IOException {
        for (Map.Entry<String, String> campo : camposDeImagen.entrySet()) {
            rellenarCampoImagen(campo);
        }

    }

    private void rellenarCampoImagen(Map.Entry<String, String> campo) throws IOException {
        String nombreCampo = campo.getKey();
        String rutaImagen = campo.getValue();

        PDField campoPDF = getCampo(nombreCampo);

        PDImageXObject imagen = cargarImagen(rutaImagen);

        dibujarImagen(campoPDF, imagen);

        // El campo flota encima de la imagen dibujada, debe ser reseteada su "apariencia" para que no se superponga sobre la imagen
        ajustarPosicionDeCampo(campoPDF);
    }

    private PDImageXObject cargarImagen(String ruta) throws IOException {
        File fileImage = new File(ruta);
        return PDImageXObject.createFromFile(fileImage.getAbsolutePath(), documento);
    }

    private void dibujarImagen(PDField campoPDF, PDImageXObject imagen) throws IOException{

        PDPageContentStream contenido;
        DatosNuevaImagen datosImg = new DatosNuevaImagen(campoPDF);

        contenido = new PDPageContentStream(documento, datosImg.pagina, datosImg.modoDeAgregado, datosImg.compresion);
        contenido.drawImage(imagen, datosImg.x, datosImg.y, datosImg.width, datosImg.height);
        contenido.close();
    }

    class DatosNuevaImagen {

        PDAnnotationWidget widgets;
        PDPage pagina;

        float height;
        float width;
        float x;
        float y;
        PDPageContentStream.AppendMode modoDeAgregado;
        Boolean compresion;
        PDRectangle elemento;

        public DatosNuevaImagen(PDField campoPDF) {
            modoDeAgregado = PDPageContentStream.AppendMode.APPEND;
            compresion = true;

            widgets = campoPDF.getWidgets().get(0);

            pagina = widgets.getPage();
            elemento = widgets.getRectangle();

            guardarCoordenadas();
            guardarTamaño();

        }

        private void guardarCoordenadas() {
            x = elemento.getLowerLeftX();
            y = elemento.getLowerLeftY();
        }

        private void guardarTamaño() {
            height = elemento.getHeight();
            width = elemento.getWidth();
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
