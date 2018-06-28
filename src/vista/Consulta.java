/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;

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
    
    private static final int HEIGHT=30,WIDTH=130;
    
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
        lblApellido.setBounds(300,30, WIDTH, HEIGHT);
        lblEdad.setBounds(0, 60, WIDTH, HEIGHT);
        lblEstado.setBounds(0, 120, WIDTH, HEIGHT);
        lblNombre.setBounds(0, 30, WIDTH, HEIGHT);
        lblNumAfiliacion.setBounds(0, 0, WIDTH, HEIGHT);
        lblProfesion.setBounds(0, 90, WIDTH, HEIGHT);
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
        
        ////////////////////////////////////////////////////////////////////////////////////////
        numAfiliacion.setBounds(130, 0, WIDTH, HEIGHT);
        nombre.setBounds(130, 30, WIDTH, HEIGHT);
        apellido.setBounds(430, 30, WIDTH, HEIGHT);
        edad.setBounds(130, 60, WIDTH, HEIGHT);
        
        si.setBounds(130, 120, 50, HEIGHT);
        no.setBounds(190, 120, 50, HEIGHT);
        
        insertar.setBounds(0, 150, WIDTH, HEIGHT);
        actualizar.setBounds(130, 150, WIDTH, HEIGHT);
        eliminar.setBounds(260, 150, WIDTH, HEIGHT);
        vaciar.setBounds(390, 150, WIDTH, HEIGHT);
        buscar.setBounds(300, 0, WIDTH, HEIGHT);
        
        profesion.setBounds(130, 90, WIDTH, HEIGHT);
        
        table.setBounds(0, 180, 500, 200);
        table.add(new JScrollPane(resultados));
                
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
        
        FiltroDao fd=new FiltroDao();
        ArrayList<Persona> personas= fd.readAll();
        
        for(Persona p:personas){
            tm.addRow(new Object[]{p.getNumAfiliacion(),p.getNombre(),p.getApellido(),p.getProfesion(),p.isEstado()});
        }
        
        resultados.setModel(tm);
        
    }
    
    public void eventos(){
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd=new FiltroDao();
                Persona p= new Persona(nombre.getText(),apellido.getText(),Integer.parseInt(edad.getText()),profesion.getSelectedItem().toString(),true,numAfiliacion.getText());
                
                if(no.isSelected()){
                    p.setEstado(false);
                }
                
                if(fd.create(p)){
                    System.out.println("se creo con exito");
                    llenarTabla();
                }
                
            }
        });
        
        actualizar.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd=new FiltroDao();
                Persona p= new Persona(nombre.getText(),apellido.getText(),Integer.parseInt(edad.getText()),profesion.getSelectedItem().toString(),true,numAfiliacion.getText());
                
                if(no.isSelected()){
                    p.setEstado(false);
                }
                
                if(fd.update(p)){
                    llenarTabla();
                }
                
            }
        
        
        });
        
        
        eliminar.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                
                FiltroDao fd=new FiltroDao();
                if(fd.delete(numAfiliacion.getText())){
                    llenarTabla();
                }
                
            }
        
        
        });
        
        vaciar.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                
                limpiarCampos();
            }
        
        
        });
        
       buscar.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                
                FiltroDao fd= new FiltroDao();
                Persona p=fd.read(numAfiliacion.getText());
                if(p==null){
                    JOptionPane.showMessageDialog(null, "el filtro no se ha encontrado");
                }
                else{
                    
                    numAfiliacion.setText(p.getNumAfiliacion());
                    nombre.setText(p.getNombre());
                    apellido.setText(p.getApellido());
                    edad.setText(Integer.toString(p.getEdad()));
                    profesion.setSelectedItem(p.getProfesion());
                    
                    if(p.isEstado()){
                        si.setSelected(true);
                    }else{
                        no.setSelected(true);
                    }
                    
                }
            }
        
        
        });
        
        
    }
    
    public void limpiarCampos(){
        
        numAfiliacion.setText("");
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        
    }
    
    public static void main(String[] args){
        
        java.awt.EventQueue.invokeLater(new Runnable(){
        
            @Override
            public void run(){
                new Consulta().setVisible(true);
            }
            
            
        });
    }
    
}
