package edu.cunoc.Conexion;

import java.io.StringReader;

public class Format {

    public Format() {
    }

    public String traducirRespuesta(String respuesta) throws Exception {
        StringReader stringReader = new StringReader(respuesta);
        RespuestasScanner scanner = new RespuestasScanner(stringReader);
        RespuestasParser parser = new RespuestasParser(scanner);
        parser.parse();
        Respuesta r = parser.getRespuesta();
        return listarRespuesta(r);
    }

    public String listarRespuesta(Respuesta respuesta) {
        StringBuilder resultado = new StringBuilder();
        if (respuesta.isErroneo()){
            resultado = new StringBuilder("Se han encontrado demasiados conflictos. No se han realizado cambios.\n");
            if (!respuesta.getErroresLexicos().isEmpty()){
                resultado.append("Errores lexicos:\n");
                for (String error : respuesta.getErroresLexicos()){
                    resultado.append(error).append("\n");
                }
            }
            if (!respuesta.getErroresSintacticos().isEmpty()){
                resultado.append("Errores sintacticos:\n");
                for (String error : respuesta.getErroresSintacticos()){
                    resultado.append(error).append("\n");
                }
            }
            if (!respuesta.getErroresArchivo().isEmpty()){
                resultado.append("Errores de Archivos:\n");
                for (String error : respuesta.getErroresArchivo()){
                    resultado.append(error).append("\n");
                }
            }
            if (!respuesta.getExitos().isEmpty()){
                resultado.append("Acciones exitosas que han sido revertidas:\n");
                for (String exit : respuesta.getExitos()){
                    resultado.append(exit).append("\n");
                }
            }
        } else {
            resultado = new StringBuilder("Las acciones se realizaron con exitos. Los cambios han sido guardados.\n");
            for (String exito : respuesta.getExitos()){
                resultado.append("Accion: ").append(exito).append("\n");
            }
        }
        return resultado.toString();
    }
}
