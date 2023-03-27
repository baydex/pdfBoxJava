/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practicas.pdfbox;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PRACTICAS
 */
public class Persona {

    Map<String, String> camposDeTexto;
    Map<String, String> camposDeImagen;

    public Persona(){
        
        camposDeTexto = new HashMap<>();
        camposDeImagen = new HashMap<>();

        completarCamposTexto();
        
        completarCamposImagen();
    }
    
    private void completarCamposTexto(){
        camposDeTexto.put("TU NOMBRE", "María Cristina Benedicta Montejano Merlos");
        
        
        camposDeTexto.put("DÍA", "23");
        camposDeTexto.put("MES", "03");
        camposDeTexto.put("AÑO", "2023");
        camposDeTexto.put("PESO", "asdfghjklasdfghjk");
        camposDeTexto.put("COMPLEXIÓN", "Delgado");
        camposDeTexto.put("ESTATURA", "1.78");
        camposDeTexto.put("CICATRICES", "OPCIÓN 1");
        camposDeTexto.put("DESCRIBA CICATRICES", "Tengo una redonda en el brazo");
        camposDeTexto.put("LUNARES", "OPCIÓN 2");
        camposDeTexto.put("DESCRIBA LUNARES", "");
        camposDeTexto.put("TATUAJES", "OPCIÓN 2");
        camposDeTexto.put("DESCRIBA TATUAJES", "");
        camposDeTexto.put("DISCAPACIDAD", "OPCIÓN 2");
        camposDeTexto.put("DESCRIBA DISCAPACIDAD", "");
        camposDeTexto.put("ACCESORIOS", "OPCIÓN 1");
        camposDeTexto.put("DESCRIBA ACCESORIOS", "Collar negro, reloj negro, pulsera negra en la mano derecha, pulsera roja de corazon y azul de facultad de informatica en la izquierda, lentes");
        camposDeTexto.put("TIPO SANGRE", "A+");
        camposDeTexto.put("ALGUNA ALERGIA", "No");
        camposDeTexto.put("CLAVE SEGURIDAD SOCIAL", "OPCIÓN 1");
        camposDeTexto.put("CLAVE SS", "");
        camposDeTexto.put("INSTITUCIÓN SALUD", "OPCIÓN 1");
        camposDeTexto.put("CLAVE INSTSALUD", "");
        camposDeTexto.put("TOMA MEDICINA", "No");
        camposDeTexto.put("CURP", "AUOA000714HQTGLLA8");
        camposDeTexto.put("NÚMERO TEL", "442 716 33 48");
        camposDeTexto.put("NÚMERO CEL", "442 716 33 48");
        camposDeTexto.put("NÚMERO TEL AMIGO 1", "442 599 56 56");
        camposDeTexto.put("NOMBRE AMIGO 1", "Maria del carmen");
        camposDeTexto.put("NÚMERO TEL AMIGO 2", "442 363 36 48");
        camposDeTexto.put("NOMBRE AMIGO 2", "Ramiro Aguilera");
        camposDeTexto.put("NÚMERO TEL AMIGO 3", "442 160 02 24");
        camposDeTexto.put("NOMBRE AMIGO 3", "Herlinda Olalde");
        camposDeTexto.put("MENCIÓN LUGAR FRECUENTE 1", "Fiscalia General del Estado de Querétaro");
        camposDeTexto.put("MENCIÓN LUGAR FRECUENTE 2", "Colonia centro sur, avenida Fray Luis de León, ext. 3101 int. 3");
        camposDeTexto.put("CUENTA FACEBOOK", "Ale Aguilera");
        camposDeTexto.put("RED SOCIAL 1", "");
        camposDeTexto.put("RED SOCIAL 2", "");
        camposDeTexto.put("RED SOCIAL 3", "");
        camposDeTexto.put("RED SOCIAL 4", "");
        
    }
    
    private void completarCamposImagen(){
        camposDeImagen.put("TU FOTO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\profile.jpg");
        camposDeImagen.put("ANULAR IZQUIERDO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("MEÑIQUE IZQUIERDO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("MEDIO IZQUIERDO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("ÍNDICE IZQUIERDO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("PULGAR IZQUIERDO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("PULGAR DERECHO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("ÍNDICE DERECHO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("MEDIO DERECHO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("ANULAR DERCHO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        camposDeImagen.put("MEÑIQUE DERECHO", "C:\\Users\\PRACTICAS\\Documents\\proyectos\\generarPDF\\huella.png");
        
    }
    public Map<String, String> getCamposTexto() {
        return camposDeTexto;
    }
    
    public Map<String, String> getCamposImagen() {
        return camposDeImagen;
    }
}
