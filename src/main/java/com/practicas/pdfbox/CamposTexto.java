package com.practicas.pdfbox;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import java.io.IOException;
import java.util.Map;


/**
 *
 * @author PRACTICAS
 */
class CamposTexto {

    Map<String, String> camposDeTexto;
    PdfAcroForm formulario;
    
    public CamposTexto(Map<String, String> listaCamposTexto, PdfAcroForm formulario) {
        this.camposDeTexto = listaCamposTexto;
        this.formulario = formulario;
    }

    public void completarCamposDeTexto() throws IOException {
        for (Map.Entry<String, String> campo : camposDeTexto.entrySet()) {
            rellenarCampoTexto(campo);
        }
    }

    private void rellenarCampoTexto(Map.Entry<String, String> campo) throws IOException {
        
        DatosCampo datosCampo = new DatosCampo(campo);

        PdfFormField campoPDF = getCampo(datosCampo.nombreCampo);
        
        campoPDF.setValue(datosCampo.valorCampo);
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
}
