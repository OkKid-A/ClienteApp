package edu.cunoc.Conexion;

import edu.cunoc.Interfaz.VentanaPrincipal;

import java.io.*;
import java.net.Socket;

public class Mensajero {

    private VentanaPrincipal ventanaPrincipal;

    public Mensajero(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public String enviarTexto(String texto) throws IOException {

            Socket cliente = new Socket("localhost",81);
            PrintWriter out = new PrintWriter(cliente.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            out.println(texto);
            cliente.shutdownOutput();
            String mensaje =in.readLine();
            String line;
            while ((line = in.readLine()) != null) {
                mensaje += "\n"+line ;
            }
        System.out.println(mensaje);
            in.close();
            out.close();
            cliente.close();
            return mensaje;

    }

    public String addUser(String contenido, String usuario){
        String userDefined =  usuario + "\n"+contenido;
        return formatType(userDefined,1);
    }

    public String formatType(String content, int tipo){
        if (tipo == 0){
            return tipo+content;
        } else {
            return tipo+content;
        }
    }
}
