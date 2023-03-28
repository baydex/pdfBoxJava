/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practicas.pdfbox;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author PRACTICAS
 */
public abstract class CamposImp implements Campos{
    final Map<String, String> campos;
    private final PdfAcroForm formulario;
    
    public CamposImp(Map<String, String> campos, PdfAcroForm formulario){
        this.campos = campos;
        this.formulario = formulario;
    }

    @Override
    public void completarCampos() throws IOException{
        for (Map.Entry<String, String> campo : campos.entrySet()) {
            rellenarCampo(campo);
        }
    };
    
    abstract void rellenarCampo(Map.Entry<String, String> campo) throws IOException;
    
    class DatosCampo{
        
        String nombreCampo;
        String valorCampo;
        
        public DatosCampo(Map.Entry<String, String> campo){
            nombreCampo = campo.getKey();
            valorCampo = campo.getValue();
        }
    }
    
    public PdfFormField getCampo(String nombreCampo) {
        return formulario.getFormFields().get(nombreCampo);
    }
}
