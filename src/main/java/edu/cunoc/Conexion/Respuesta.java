package edu.cunoc.Conexion;

import java.util.ArrayList;

public class Respuesta {

    private ArrayList<String> erroresSintacticos;
    private ArrayList<String> erroresLexicos;
    private ArrayList<String> erroresArchivo;
    private ArrayList<String> exitos;
    private boolean erroneo;

    public Respuesta() {
        this.erroresSintacticos = new ArrayList<String>();
        this.erroresLexicos = new ArrayList<String>();
        this.erroresArchivo = new ArrayList<String>();
        this.exitos = new ArrayList<String>();
        this.erroneo = false;
    }

    public void addErrorlexico(String error) {
        this.erroneo = true;
        erroresLexicos.add(error);
    }

    public void addErrorSintactico(String error) {
        this.erroneo = true;
        erroresSintacticos.add(error);
    }

    public void addErrorArchivo(String error) {
        this.erroneo = true;
        erroresArchivo.add(error);
    }

    public void addExito(String exito) {
        exitos.add(exito);
    }

    public boolean isErroneo() {
        return erroneo;
    }

    public void setErroneo(boolean erroneo) {
        this.erroneo = erroneo;
    }

    public ArrayList<String> getErroresSintacticos() {
        return erroresSintacticos;
    }

    public void setErroresSintacticos(ArrayList<String> erroresSintacticos) {
        this.erroresSintacticos = erroresSintacticos;
    }

    public ArrayList<String> getErroresLexicos() {
        return erroresLexicos;
    }

    public void setErroresLexicos(ArrayList<String> erroresLexicos) {
        this.erroresLexicos = erroresLexicos;
    }

    public ArrayList<String> getErroresArchivo() {
        return erroresArchivo;
    }

    public void setErroresArchivo(ArrayList<String> erroresArchivo) {
        this.erroresArchivo = erroresArchivo;
    }

    public ArrayList<String> getExitos() {
        return exitos;
    }

    public void setExitos(ArrayList<String> exitos) {
        this.exitos = exitos;
    }
}
