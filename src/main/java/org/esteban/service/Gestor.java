package org.esteban.service;

import org.esteban.model.Tarea;

import java.util.List;
import java.util.Optional;

public interface Gestor {


    //Eliminar una tarea
    int eliminarTarea(String id);

    //Listar todas las tareas
    void listarTareas();

    //Buscar una tarea por ID
    Optional<Tarea> buscarTareaPorId(String id);

    //Cambiar el estado a completada de una tarea por ID
    int cambiarEstadoTarea(String id);

    List<Tarea> getTareas();

    void setTareas(List<Tarea> tareas);
}
