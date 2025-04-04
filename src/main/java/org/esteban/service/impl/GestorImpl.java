package org.esteban.service.impl;

import org.esteban.model.Tarea;
import org.esteban.service.Gestor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorImpl implements Gestor {

    private static final Logger LOGGER = LoggerFactory.getLogger(GestorImpl.class);
    private final List<Tarea> tareas = new ArrayList<>();

    @Override
    public int eliminarTarea(String id) {
        for(Tarea t : tareas){
            if(t.getId().equals(id)){
                tareas.remove(t);
                LOGGER.info("Tarea eliminada: {}", t);
                return 0;
            }
        }
        return 1;
    }

    @Override
    public void listarTareas() {
        for (Tarea tarea : tareas) {
            LOGGER.info("Tarea {}: {}",tarea.getId(),tarea);
        }

    }

    /**Buscar una tarea por ID
     * Method signature contiene Optional <Tarea> para evitar NullPointerException y poder devolver un valor vacío
     * @param id
     * @return
     */
    @Override
    public Optional<Tarea> buscarTareaPorId(String id) {

        for (Tarea t : tareas) {
            if (t.getId().equals(id)) {
                LOGGER.info("Tarea encontrada: {}", t);
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    @Override
    public int cambiarEstadoTarea(String id) {
        for (Tarea t : tareas) {
            if (t.getId().equals(id)) {
                t.setEstado(Tarea.Estado.COMPLETADA.name());
                LOGGER.debug("Tarea {} completada", t.getId());
                return 0;
            }
        }
        return 1;
    }

    @Override
    public List<Tarea> getTareas() {
        return tareas;
    }

    @Override
    public void setTareas(List<Tarea> tareas) {
        this.tareas.clear();
        this.tareas.addAll(tareas);
    }
}
