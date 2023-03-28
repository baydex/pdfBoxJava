package com.practicas.generadorpdf.campos;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import java.io.IOException;
import java.util.Map;


/**
 *
 * @author PRACTICAS
 */
public class CamposTextoFijo extends CamposImp{
    
    public CamposTextoFijo(Map<String, String> campos, PdfAcroForm formulario) {
        super(campos, formulario);
    }

    @Override
    void rellenarCampo(Map.Entry<String, String> campo) throws IOException {
        
        DatosCampo datosCampo = new DatosCampo(campo);

        PdfFormField campoPDF = getCampo(datosCampo.nombreCampo);
        
        campoPDF.setValue(datosCampo.valorCampo);
    }
    
}
