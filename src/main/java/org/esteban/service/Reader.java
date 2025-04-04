package org.esteban.service;

import org.esteban.model.Tarea;

import java.io.FileNotFoundException;
import java.util.List;

public interface Reader {

    List<Tarea> leerTareas() throws FileNotFoundException;

    //TODO: Implementar el metodo para leer desde base de datos
}
