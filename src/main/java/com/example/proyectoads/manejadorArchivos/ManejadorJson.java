/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.example.proyectoads.manejadorArchivos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ManejadorJSON {
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * Guarda un objeto en formato JSON
     */
    public static boolean guardarJSON(Object objeto, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(objeto, writer);
            System.out.println("JSON guardado exitosamente en: " + rutaArchivo);
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar JSON: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Lee un objeto desde un archivo JSON
     */
    public static <T> T leerJSON(String rutaArchivo, Class<T> clase) {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            T objeto = gson.fromJson(contenido, clase);
            System.out.println("JSON leído exitosamente desde: " + rutaArchivo);
            return objeto;
        } catch (IOException e) {
            System.err.println("Error al leer JSON: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Lee una lista de objetos desde un archivo JSON
     */
    public static <T> T leerJSONLista(String rutaArchivo, Type tipoLista) {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            T lista = gson.fromJson(contenido, tipoLista);
            System.out.println("Lista JSON leída exitosamente desde: " + rutaArchivo);
            return lista;
        } catch (IOException e) {
            System.err.println("Error al leer lista JSON: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Convierte un objeto a String JSON
     */
    public static String objetoAJSON(Object objeto) {
        return gson.toJson(objeto);
    }
    
    /**
     * Convierte un String JSON a objeto
     */
    public static <T> T jsonAObjeto(String json, Class<T> clase) {
        return gson.fromJson(json, clase);
    }
}