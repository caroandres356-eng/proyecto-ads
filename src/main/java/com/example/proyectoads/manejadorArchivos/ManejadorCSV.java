package main.java.com.example.proyectoads.manejadorArchivos;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ManejadorCSV {
    
    private static final String SEPARADOR = ",";
    
    /**
     * Lee un archivo CSV y convierte cada fila en un objeto
     * La primera fila debe ser la cabecera con los nombres de los atributos
     */
    public static <T> List<T> csvAObjetos(String rutaArchivo, Class<T> clase) {
        List<T> objetos = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Leer cabecera
            String lineaCabecera = br.readLine();
            if (lineaCabecera == null) return objetos;
            
            String[] atributos = lineaCabecera.split(SEPARADOR);
            for (int i = 0; i < atributos.length; i++) {
                atributos[i] = atributos[i].trim();
            }
            
            // Leer cada línea
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(SEPARADOR);
                for (int i = 0; i < valores.length; i++) {
                    valores[i] = valores[i].trim();
                }
                
                try {
                    T objeto = clase.getDeclaredConstructor().newInstance();
                    
                    for (int i = 0; i < atributos.length && i < valores.length; i++) {
                        try {
                            Field campo = clase.getDeclaredField(atributos[i]);
                            campo.setAccessible(true);
                            
                            Object valorConvertido = convertirValor(valores[i], campo.getType());
                            campo.set(objeto, valorConvertido);
                            
                        } catch (NoSuchFieldException e) {
                            // Ignorar atributos que no existen
                        }
                    }
                    
                    objetos.add(objeto);
                    
                } catch (Exception e) {
                    System.err.println("Error al crear objeto: " + e.getMessage());
                }
            }
            
            System.out.println("CSV leído: " + objetos.size() + " objetos creados");
            
        } catch (IOException e) {
            System.err.println("Error al leer CSV: " + e.getMessage());
        }
        
        return objetos;
    }
    
    /**
     * Convierte una lista de objetos a CSV y lo guarda
     */
    public static <T> boolean objetosACSV(String rutaArchivo, List<T> objetos, String[] atributos) {
        if (objetos == null || objetos.isEmpty()) return false;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            Class<?> clase = objetos.get(0).getClass();
            
            // Escribir cabecera
            bw.write(String.join(SEPARADOR, atributos));
            bw.newLine();
            
            // Escribir cada objeto
            for (T objeto : objetos) {
                List<String> valores = new ArrayList<>();
                
                for (String nombreAtributo : atributos) {
                    try {
                        Field campo = clase.getDeclaredField(nombreAtributo);
                        campo.setAccessible(true);
                        Object valor = campo.get(objeto);
                        valores.add(valor != null ? valor.toString() : "");
                    } catch (Exception e) {
                        valores.add("");
                    }
                }
                
                bw.write(String.join(SEPARADOR, valores));
                bw.newLine();
            }
            
            System.out.println("CSV guardado: " + rutaArchivo);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error al escribir CSV: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Convierte String a su tipo correspondiente
     */
    private static Object convertirValor(String valor, Class<?> tipo) {
        try {
            if (tipo == String.class) return valor;
            if (tipo == int.class || tipo == Integer.class) return Integer.parseInt(valor);
            if (tipo == double.class || tipo == Double.class) return Double.parseDouble(valor);
            if (tipo == boolean.class || tipo == Boolean.class) return Boolean.parseBoolean(valor);
            if (tipo == long.class || tipo == Long.class) return Long.parseLong(valor);
            if (tipo == float.class || tipo == Float.class) return Float.parseFloat(valor);
            return valor;
        } catch (Exception e) {
            return getValorPorDefecto(tipo);
        }
    }
    
    /**
     * Retorna valor por defecto según el tipo
     */
    private static Object getValorPorDefecto(Class<?> tipo) {
        if (tipo == int.class || tipo == Integer.class) return 0;
        if (tipo == double.class || tipo == Double.class) return 0.0;
        if (tipo == boolean.class || tipo == Boolean.class) return false;
        if (tipo == long.class || tipo == Long.class) return 0L;
        if (tipo == float.class || tipo == Float.class) return 0.0f;
        return "";
    }
}