/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {
    
    public JLabel lblNumAfiliacion,lblNombre,lblApellido,lblEdad,lblProfesion,lblEstado;
    
    public JTextField numAfiliacion,nombre,apellido,edad;
    public JComboBox profesion;
    
    ButtonGroup existencia= new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton insertar,actualizar,eliminar,vaciar,buscar;
    
    private static final int ALTOC=30,ANCHOC=130;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("CORTO LABOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container= getContentPane();
        container.add(lblApellido);
        container.add(lblEdad);
        container.add(lblEstado);
        container.add(lblNombre);
        container.add(lblNumAfiliacion);
        container.add(lblProfesion);
        container.add(numAfiliacion);
        container.add(nombre);
        container.add(apellido);
        container.add(edad);
        container.add(profesion);
        container.add(no);
        container.add(si);
        container.add(resultados);
        container.add(table);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(vaciar);
        container.add(buscar);
        setSize(800,500);
        eventos();
    }
    
    public final void agregarLabels(){
        
    }
    
}
