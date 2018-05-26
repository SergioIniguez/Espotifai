package com.dao.impl;

import com.dao.ICancionesDao;
import com.utileria.Cancion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author _SERGIO_
 */
public class CancionDaoImpl implements ICancionesDao{
    String cadenaConexion = "jdbc:mysql://localhost:3306/espotifaidb?useSSL=false";
    String usuarioDB = "root";
    String passDB = "mysqlroot";
    Connection conexion;
    private void conexion(){
        try{
            conexion = DriverManager.getConnection(cadenaConexion,usuarioDB,passDB);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void cerrarConexion(){
        try{
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean create(Cancion entidad) throws SQLException {
        conexion();
        String insertCancion = "INSERT INTO canciones (nombre,artista) "
                + "VALUES(?,?)";
        PreparedStatement stm = conexion.prepareStatement(insertCancion);
        stm.setString(1, entidad.getNombre());
        stm.setString(2, entidad.getArtista());
        boolean ejecutado = stm.executeUpdate() > 0;
        //stm.close();
        cerrarConexion();
        return ejecutado;
    }

    @Override
    public List<Cancion> read() throws SQLException {
        List<Cancion> cancionesLista = new ArrayList<>();
        String leerQuery = "SELECT * FROM canciones";
                
        conexion();
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(leerQuery);
        while(resultado.next()){
            Cancion cancion = new Cancion();
            cancion.setNum_cancion(resultado.getInt(1));
            cancion.setNombre(resultado.getString(2));
            cancion.setArtista(resultado.getString(3));
            cancionesLista.add(cancion);
        }
            resultado.close();
            sentencia.close();
            cerrarConexion();
        return cancionesLista;
    }

    @Override
    public boolean update(Cancion entidad, int selCancion) throws SQLException {
        conexion();
        String updateCancion = "UPDATE canciones SET nombre = ?, artista = ?"
                + "WHERE num_cancion = ?";
        PreparedStatement stm = conexion.prepareStatement(updateCancion);
        stm.setString(1, entidad.getNombre());
        stm.setString(2, entidad.getArtista());
        stm.setInt(3, selCancion);
        boolean ejecutado = stm.executeUpdate()>0;
        //stm.close();
        cerrarConexion();
        return ejecutado;
    }

    @Override
    public boolean delete(int num_cancion) throws SQLException {
        conexion();
        String deleteCancion = "DELETE FROM canciones WHERE num_cancion = ?";
        PreparedStatement stm = conexion.prepareStatement(deleteCancion);
        stm.setInt(1, num_cancion);
        boolean ejecutado = stm.executeUpdate() > 0;
        //stm.close();
        cerrarConexion();
        return ejecutado;
    }
    
}
