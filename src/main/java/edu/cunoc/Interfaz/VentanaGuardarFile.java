package edu.cunoc.Interfaz;

import edu.cunoc.Archivos.Archivador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class VentanaGuardarFile extends JFrame {
    private JPanel globalPanel;
    private JLabel rutaLabel;
    private JButton selecRutaButton;
    private JTextField nombreField;
    private JButton crearArchivoButton;
    private String ruta;
    private String contenido;
    private JFrame frame;

    public VentanaGuardarFile(String contenido) {
        this.contenido = contenido;
        fixComponents();
        setButtons();
    }

    public void setButtons(){
        crearArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Archivador archivador = new Archivador();
                try {
                    archivador.guardarArchivo(nombreField.getText(), contenido,ruta + File.separator + nombreField.getText() + ".txt");
                    getFrame().dispose();
                } catch (FileAlreadyExistsException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),"Ya existe un archivo con este nombre.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(new JFrame(),"No se pudo guardar el archivo.");
                }
            }
        });
        selecRutaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showSaveDialog(null);
                ruta = fileChooser.getSelectedFile().getAbsolutePath();
                rutaLabel.setText("Ruta: "+ruta);
                redundar().pack();
            }
        });
    }

    public void fixComponents(){
        this.frame = new JFrame("Nuevo Proyecto");
        frame.setContentPane(globalPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JFrame redundar(){
        return this;
    }

    public JFrame getFrame() {
        return frame;
    }
}
