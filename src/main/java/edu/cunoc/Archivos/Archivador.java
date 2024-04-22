package edu.cunoc.Archivos;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class Archivador {

    public Archivador() {
    }

    public void guardarArchivo(String nombreArchivo, String contenido, String path) throws IOException {
        File archivo = new File(path);
        String linea;
        if (!archivo.createNewFile()){
            throw new FileAlreadyExistsException(nombreArchivo+" ya existe");
        }
        try (FileWriter fw = new FileWriter(archivo.getAbsolutePath())) {
            fw.write(contenido);
        }
    }
}
