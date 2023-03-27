package com.practicas.pdfbox;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;
import java.util.Map;

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
        rutaGuardadoDocumento += "76121";
        try {
            cargarDocumento(rutaGuardadoDocumento);
            cargarFormulario();
            cargarCampos();
            completarCampos();
            deshabilitarCampos();
            cerrarDocumento();
            agregarMarcaDeAgua("Localizada", rutaGuardadoDocumento, ColorConstants.RED);
            agregarMarcaDeAgua("Desactivada", rutaGuardadoDocumento, ColorConstants.GRAY);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void cargarDocumento(String rutaGuardadoDocumento) throws IOException {
        rutaGuardadoDocumento+="_SeBusca.pdf";
        File plantilla = new File(this.rutaPlantilla);
        File archivoFinal = new File(rutaGuardadoDocumento);

        documento = new PdfDocument(new PdfReader(plantilla), new PdfWriter(archivoFinal));
    }
    
    private void cargarFormulario() {
        boolean crearSiNoExiste = true;
        formulario = PdfAcroForm.getAcroForm(documento, crearSiNoExiste);
    }

    private void cargarCampos(){
        Map<String, String> listaCamposTexto = persona.getCamposTexto();
        Map<String, String> listaCamposImagen = persona.getCamposImagen();
        
        camposTexto = new CamposTexto(listaCamposTexto, formulario);
        camposImagen = new CamposImagen(listaCamposImagen, formulario);
    }
    
    private void completarCampos() throws IOException{
        camposTexto.completarCamposDeTexto();
        camposImagen.completarCamposDeImagen();
    }

    private void deshabilitarCampos(){
        for (PdfFormField entry : formulario.getFormFields().values()) {
            entry.setReadOnly(true);
        }
    }
    
    private void cerrarDocumento() throws IOException {
        documento.close();
    }
    
    private void agregarMarcaDeAgua(String mensaje, String rutaGuardadoDocumento, Color color) throws IOException{
        String nuevaPlantilla= rutaGuardadoDocumento + "_SeBusca.pdf";
        rutaGuardadoDocumento+="_"+mensaje+".pdf";
        
        File plantilla = new File(nuevaPlantilla);
        File archivoFinal = new File(rutaGuardadoDocumento);
        
        documento = new PdfDocument(new PdfReader(plantilla), new PdfWriter(archivoFinal));
        
        Document document = new Document(documento);
        Paragraph texto = new Paragraph(mensaje);
        texto.setRotationAngle(45);
        texto.setFontSize(150);
        texto.setFontColor(color);
        texto.setFixedPosition(180, 30, 1000);
        document.add(texto);
        
        document.close();
        
        documento.close();
    }
}
