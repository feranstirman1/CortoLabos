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
        lblApellido=new JLabel("Apellido");
        lblNombre=new JLabel("Nombre");
        lblEdad=new JLabel("Edad");
        lblNumAfiliacion=new JLabel("Num Afiliacion");
        lblEstado=new JLabel("Estado");
        lblProfesion=new JLabel("Profesion");
        lblApellido.setBounds(200, 30, ANCHOC, ALTOC);
        lblEdad.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        lblEstado.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        lblNombre.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        lblNumAfiliacion.setBounds(0, 0, ANCHOC, ALTOC);
        lblProfesion.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
    }
    
    public final void formulario(){
        numAfiliacion= new JTextField();
        nombre= new JTextField();
        apellido=new JTextField();
        edad=new JTextField();
        profesion=new JComboBox();
        si=new JRadioButton("si",true);
        no=new JRadioButton("no");
        resultados= new JTable();
        table= new JPanel();
        insertar= new JButton("Insertar");
        actualizar=new JButton("Actualizar");
        buscar=new JButton("Buscar");
        eliminar=new JButton("Eliminar");
        vaciar= new JButton("Vaciar");
        
        profesion.addItem("Ingeniero");
        profesion.addItem("Mecanico");
        profesion.addItem("Profesor");
        profesion.addItem("Arquitecto");
        
        existencia= new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
                
    }
    
    public void llenarTabla(){
        tm=new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                        
                }
            }
        };
        tm.addColumn("Nombre");
        tm.addColumn("Apellido");
        tm.addColumn("Edad");
        tm.addColumn("Profesion");
        tm.addColumn("Estado");
    }
    
}
