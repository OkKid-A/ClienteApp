package edu.cunoc.Interfaz;

import edu.cunoc.Conexion.Format;
import edu.cunoc.Conexion.Mensajero;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class VentanaPrincipal extends JFrame{
    private JPanel globalPanel;
    private JPanel textPane;
    private JPanel queryPanel;
    private JPanel resultadosPanel;
    private JTextPane archivoTextPane;
    private JTextArea queryTextArea;
    private JTextArea resultadosTextArea;
    private JScrollPane archiScrollPane;
    private JScrollPane resulScrollPane;
    private JButton enviarAccionesButton;
    private JButton guardarButton;
    private JButton abrirArchivoButton;
    private Mensajero mensajero;
    private String userId;

    public VentanaPrincipal(String idUser) {
        fixComponents();
        setButtons();
        this.userId = idUser;
        this.mensajero = new Mensajero(this);

    }

    public void fixComponents(){
        JFrame frame = new JFrame("Ventana Principal");
        frame.setContentPane(globalPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setButtons(){
        enviarAccionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Format f = new Format();
                try {
                    resultadosTextArea.setText(f.traducirRespuesta(mensajero.enviarTexto(mensajero.addUser(archivoTextPane.getText(),userId))));
                    resulScrollPane.revalidate();
                    resulScrollPane.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al enviar el texto.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al procesar el texto de respuesta.");
                }
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaGuardarFile ventanaGuardarFile = new VentanaGuardarFile(archivoTextPane.getText());
            }
        });
        abrirArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("txt file", "txt");
                fileChooser.addChoosableFileFilter(filtro);
                int valido = fileChooser.showOpenDialog(null);
                if (valido == JFileChooser.APPROVE_OPTION) {
                    try {
                        archivoTextPane.setText(Files.readString(Path.of(fileChooser.getSelectedFile().getAbsolutePath())));
                        archiScrollPane.revalidate();
                        archiScrollPane.repaint();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "No se ha podido leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else  {
                    JOptionPane.showMessageDialog(new JFrame(),"Este archivo no es valido.");
                }
            }
        });
        InputMap inputMap = queryPanel.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = queryPanel.getActionMap();
        KeyStroke enterType = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);
        String enterKey = (String) inputMap.get(enterType);
        actionMap.put(enterKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Format f = new Format();
                    resultadosTextArea.setText(f.traducirRespuesta(mensajero.formatType(queryTextArea.getText(),0)));
                    resulScrollPane.revalidate();
                    resulScrollPane.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al enviar el texto.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al procesar el texto de respuesta.");
                }
            }
        });
    }

    private JFrame redundar(){
        return this;
    }
}
