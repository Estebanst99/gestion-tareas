package org.esteban;

import org.esteban.exception.ReaderException;
import org.esteban.exception.RealizarAccionException;
import org.esteban.model.Tarea;
import org.esteban.service.Gestor;
import org.esteban.service.Reader;
import org.esteban.service.Writer;
import org.esteban.service.impl.GestorImpl;
import org.esteban.service.impl.ReaderTextFileImpl;
import org.esteban.service.impl.WriterTxtFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String NOMBRE_ARCHIVO = "files/tareas.txt";
    private static final String NOMBRE_ARCHIVO_SALIDA = "files/tareas-output.txt";
    private static final String MENSAJE_SELECCIONAR_TAREA = """
            Escribe el numero segun la opcion que prefieras:\s
            1.Marcar tarea por ID completada\s
            2.Mostrar una tarea por ID\s
            3.Eliminar una tarea""";
    private static final Scanner scanner = new Scanner(System.in);
    private static Gestor gestor = new GestorImpl();

    public static void main(String[] args) throws ReaderException, RealizarAccionException {

        LOGGER.info("Starting the application...");
        //Leer tareas desde un archivo

        try {
            Reader reader = new ReaderTextFileImpl(NOMBRE_ARCHIVO);
            //Leer tareas desde el archivo
            List<Tarea> tareas = reader.leerTareas();
            //Guardar tareas en una lista
            gestor = new GestorImpl();
            gestor.setTareas(tareas);
            //Listar tareas
            gestor.listarTareas();
            //El usuario selecciona una tarea para hacer (Buscar,completar,eliminar)
            LOGGER.info(MENSAJE_SELECCIONAR_TAREA);

            int option = scanner.nextInt();

            //Realizar la accion seleccionada
            realizarAccion(option);

            //Exportar tareas a un archivo
            Writer writer = new WriterTxtFile(NOMBRE_ARCHIVO_SALIDA);
            //Se escriben las tareas procesadas por el gestor
            writer.escribirTareas(gestor.getTareas());
            LOGGER.info("Tareas exportadas correctamente");

        } catch (FileNotFoundException e) {
            throw new ReaderException("Error reading tasks from file");
        }
    }

    public static void realizarAccion(int option) throws RealizarAccionException {
        String id = null;
        switch (option) {
            case 1 -> {
                //Marcar tarea por ID completada
                LOGGER.info("Ingrese el ID de la tarea a completar: ");
                scanner.nextLine();
                id = scanner.nextLine();

                if (gestor.cambiarEstadoTarea(id) == 0) {
                    LOGGER.info("Tarea completada correctamente");
                } else {
                    throw new RealizarAccionException("No se ha podido completar la tarea con el ID: " + id);
                }
            }
            case 2 -> {
                //Mostrar una tarea por ID
                LOGGER.info("Ingrese el ID de la tarea a mostrar: ");
                scanner.nextLine();
                id = scanner.nextLine();
                Optional<Tarea> tarea = gestor.buscarTareaPorId(id);
                if (tarea.isPresent()) {
                    LOGGER.info("Tarea encontrada: {}", tarea);
                } else {
                    throw new RealizarAccionException("No se ha encontrado la tarea con el ID: " + id);
                }
            }
            case 3 -> {
                //Eliminar una tarea
                LOGGER.info("Ingrese el ID de la tarea a eliminar: ");
                scanner.nextLine();
                id = scanner.nextLine();
                if (gestor.eliminarTarea(id) == 0) {
                    LOGGER.info("Tarea eliminada correctamente");
                } else {
                    throw new RealizarAccionException("No se ha podido eliminar la tarea con el ID: " + id);
                }
            }
            default -> LOGGER.error("Opcion invalida");
        }
    }
    //Modificar tareas
    //Eliminar tareas
    //Cambiar estado de tareas
    //Buscar tarea por ID
    //Exportar tareas a un archivo
    //Finalizar el programa

}