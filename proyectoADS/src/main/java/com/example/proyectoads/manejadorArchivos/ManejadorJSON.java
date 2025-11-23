package com.example.proyectoads.manejadorArchivos;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * ManejadorJSON mejorado: registra adaptadores para java.time y evita
 * el InaccessibleObjectException que lanza Gson al intentar acceder por reflexión
 * a los campos internos de las clases del JDK.
 */
public class ManejadorJSON {

    // Gson configurado con adaptadores para java.time
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    /**
     * Guarda un objeto en formato JSON (sobrescribe si existe).
     */
    public static void guardarJSON(Object objeto, String rutaArchivo) {
        try (Writer writer = new FileWriter(rutaArchivo)) {
            gson.toJson(objeto, writer);
            System.out.println("JSON guardado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar JSON: " + e.getMessage());
        } catch (JsonIOException je) {
            // Mensaje adicional por si fallara la serialización
            System.err.println("Error de Gson al serializar: " + je.getMessage());
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
        } catch (JsonSyntaxException je) {
            System.err.println("JSON inválido o incompatible: " + je.getMessage());
            return null;
        }
    }

    /**
     * Lee una lista de objetos desde un archivo JSON
     * Uso:
     * Type tipoLista = new TypeToken<List<Universidad>>(){}.getType();
     * List<Universidad> lista = ManejadorJSON.leerJSONLista("universidades.json", tipoLista);
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
        } catch (JsonSyntaxException je) {
            System.err.println("JSON inválido o incompatible: " + je.getMessage());
            return null;
        }
    }

    /**
     * Convierte un objeto a String JSON
     */
    public static String objetoAJSON(Object objeto) {
        try {
            return gson.toJson(objeto);
        } catch (JsonIOException je) {
            System.err.println("Error de Gson al convertir objeto a JSON: " + je.getMessage());
            return null;
        }
    }

    /**
     * Convierte un String JSON a objeto
     */
    public static <T> T jsonAObjeto(String json, Class<T> clase) {
        try {
            return gson.fromJson(json, clase);
        } catch (JsonSyntaxException je) {
            System.err.println("JSON inválido: " + je.getMessage());
            return null;
        }
    }

    // -------------------------
    // ADAPTADORES PARA java.time
    // -------------------------

    /**
     * Adapter para LocalTime (formato ISO: HH:mm[:ss])
     */
    private static class LocalTimeAdapter extends TypeAdapter<LocalTime> {
        private static final DateTimeFormatter F = DateTimeFormatter.ISO_LOCAL_TIME;

        @Override
        public void write(JsonWriter out, LocalTime value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value.format(F));
        }

        @Override
        public LocalTime read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String s = in.nextString();
            return LocalTime.parse(s, F);
        }
    }

    /**
     * Adapter para LocalDate (formato ISO: yyyy-MM-dd)
     */
    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        private static final DateTimeFormatter F = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value.format(F));
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String s = in.nextString();
            return LocalDate.parse(s, F);
        }
    }

    /**
     * Adapter para LocalDateTime (formato ISO: yyyy-MM-ddTHH:mm:ss)
     */
    private static class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        private static final DateTimeFormatter F = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public void write(JsonWriter out, LocalDateTime value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value.format(F));
        }

        @Override
        public LocalDateTime read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String s = in.nextString();
            return LocalDateTime.parse(s, F);
        }
    }
}
