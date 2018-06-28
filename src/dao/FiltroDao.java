/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.istack.internal.logging.Logger;
import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import modelo.Persona;

/**
 *
 * @author LN710Q
 */
public class FiltroDao implements metodos<Persona> {
    
    private static final String SQL_INSERT="INSERT INTO personas (numAfiliacion,nombres,apellidos,edad,profesion,estado) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE personas SET nombres=?,apellidos=?,edad=?,profesion=?,estado=? WHERE numAfiliacion=?";
    private static final String SQL_DELETE="DELETE FROM personas WHERE numAfiliacion=?";
    private static final String SQL_READ="SELECT * FROM personas WHERE numAfiliacion=?";
    private static final String SQL_READALL="SELECT * FROM personas";
    private Conexion con;
    
    @Override
    public boolean create(Persona g){
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNumAfiliacion());
            ps.setString(2, g.getNombre());
            ps.setString(3, g.getApellido());
            ps.setInt(4, g.getEdad());
            ps.setString(5, g.getProfesion());
            ps.setBoolean(6, true);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            //Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    
    @Override
    public boolean delete(Object key){
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1,key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            //Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    
    @Override
    public boolean update(Persona c){
        PreparedStatement ps;
        try{
            System.out.println(c.getNumAfiliacion());
            ps=con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setInt(3, c.getEdad());
            ps.setString(4, c.getProfesion());
            ps.setBoolean(5, c.isEstado());
            ps.setString(6, c.getNumAfiliacion());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    
    @Override
    public Persona read(Object key){
        Persona p=null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps=con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs=ps.executeQuery();
            
            while(rs.next()){
                p= new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getBoolean(7));
            }
            rs.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            con.cerrarConexion();
        }
        return p;
    }
    
    @Override
    public ArrayList<Persona> readAll(){
        ArrayList<Persona> all= new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s=con.getCnx().prepareStatement(SQL_READALL);
            rs=s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getBoolean(7)));
            }
            rs.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return all;
    }
    
}
