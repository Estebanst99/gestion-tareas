package org.esteban.service.impl;

import org.esteban.model.Tarea;
import org.esteban.service.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterTxtFile implements Writer {

    private final String nombreArchivo;

    public WriterTxtFile(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public int escribirTareas(List<Tarea> tareas) {

        try(FileWriter fw = new FileWriter(nombreArchivo);
            BufferedWriter bw = new BufferedWriter(fw)){
            for(Tarea t: tareas){
                bw.write(t.toString());
                bw.newLine();
            }
            //Fuerza la escritura inminente de los datos en el archivo
            bw.flush();

        }catch (IOException e){
           return -1;
        }
        return 0;
    }
}
