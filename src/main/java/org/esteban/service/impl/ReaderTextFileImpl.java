package org.esteban.service.impl;

import org.esteban.model.Tarea;
import org.esteban.service.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderTextFileImpl implements Reader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderTextFileImpl.class);
    private final List<Tarea> tareas = new ArrayList<>();
    private final String nombreArchivo;

    public ReaderTextFileImpl(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public List<Tarea> leerTareas() throws FileNotFoundException {
        // Implementación para leer tareas desde un archivo
        LOGGER.info("Leyendo tareas desde el archivo: {}",nombreArchivo);

        try(FileReader fileReader = new FileReader(nombreArchivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader))
        {
            String linea;
            while((linea = bufferedReader.readLine()) != null) {

                // Procesar la línea y crear un objeto Tarea
                /*Formato de la lines:
                 * id,nombre,descripcion,estado
                  */
                /*Split devuelve un array de String, por lo que se puede acceder a cada elemento
                de la línea usando el índice correspondiente
                String[] elementos = linea.split(regex);
                elementos = [id], [nombre], [descripcion], [estado]
                 */
                Tarea tarea = crearTareaDesdeLinea(linea);
                // Agregar la tarea a una lista
                tareas.add(tarea);
            }
            LOGGER.info("Tareas leídas correctamente desde el archivo: {}",nombreArchivo);
            LOGGER.info("Total de tareas leídas: {}",tareas.size());
            return tareas;

        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    /**
     * Metodo para crear una tarea a partir de una línea del archivo
     * @param linea
     * @return
     */
    private Tarea crearTareaDesdeLinea(String linea) {
        String[] elementos = linea.split(",");
        String id = elementos[0];
        String nombre = elementos[1];
        String descripcion = elementos[2];
        String estado = elementos[3];
        return new Tarea(id, nombre, descripcion, estado);
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
