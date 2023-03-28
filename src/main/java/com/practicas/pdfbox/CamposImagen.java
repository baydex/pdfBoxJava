package com.practicas.pdfbox;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;

/**
 *
 * @author PRACTICAS
 */
class CamposImagen extends CamposImp{
    
    public CamposImagen(Map<String, String> campos, PdfAcroForm formulario) {
        super(campos, formulario);
    }

    @Override
    void rellenarCampo(Map.Entry<String, String> campo) throws FileNotFoundException, IOException {
        DatosCampo datosCampo = new DatosCampo(campo);
        
        PdfButtonFormField campoPDF = (PdfButtonFormField) getCampo(datosCampo.nombreCampo);
        
        String imagenBase64 = procesarImagen(datosCampo.valorCampo);
        
        campoPDF.setValue(imagenBase64);
        campoPDF.setBorderWidth(0);
    }

    private String procesarImagen(String valorCampo) throws FileNotFoundException, IOException{
        File imagen = leerImagen(valorCampo);
        return convertirImagenABase64(imagen);
    }
    
    private File leerImagen(String valorCampo) throws FileNotFoundException{
        return new File(valorCampo);
    }
    
    private String convertirImagenABase64(File imagen) throws IOException{

        byte[] imagenBytes = Files.readAllBytes(imagen.toPath());
        String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
        return imagenBase64;
    }
}
