package org.esteban.service;

import org.esteban.model.Tarea;

import java.util.List;

public interface Writer {

    int escribirTareas(List<Tarea> tareas);
}
