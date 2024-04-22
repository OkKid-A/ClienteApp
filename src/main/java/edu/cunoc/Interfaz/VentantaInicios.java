package edu.cunoc.Interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentantaInicios extends JFrame{
    private JPanel globalPanel;
    private JTextField idUserField;
    private JButton ingresarButton;
    private JFrame frame;

    public VentantaInicios() {
        fixComponents();
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idUserField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID");
                } else {
                    VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(idUserField.getText());
                    getFrame().dispose();
                }
            }
        });

    }

    public void fixComponents(){
        this.frame = new JFrame("Ventana de Inicio");
        frame.setContentPane(globalPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
