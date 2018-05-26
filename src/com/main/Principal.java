package com.main;

import com.dao.impl.CancionDaoImpl;
import com.utileria.Cancion;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author _SERGIO_
 */
public class Principal {
    public static void main(String[] args) throws SQLException{
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        while(flag){
        System.out.println("Ingresa la opción que desees");
        System.out.println("1 -> Crear nueva canción");
        System.out.println("2 -> Mostrar canciones");
        System.out.println("3 -> Actualizar canción");
        System.out.println("4 -> Eliminar canción");
        System.out.println("5 -> Salir");
        
        int opcion = scan.nextInt();
        flag = eleccion(opcion);              
        }
        System.out.println("Gracias por usar Espotifai");
    }
     private static boolean eleccion(int opcion) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Cancion cancion = new Cancion();
        CancionDaoImpl cancionDao = new CancionDaoImpl();
        boolean resultado = true; 
        boolean seguir = true; 
        switch(opcion){
            case 1:
                System.out.println("Ingresa el nombre de la canción");
                String nombre_cancion = scan.nextLine();
                System.out.println("Ingresa el nombre del artista");
                String nombre_artista = scan.nextLine();
                
                cancion.setNombre(nombre_cancion);
                cancion.setArtista(nombre_artista);
                
                resultado = cancionDao.create(cancion);
                if(resultado){
                    System.out.println("Se ingresó corrrectamente");
                } else {
                    System.out.println("No se arma");   
                }
                break;
            case 2:
                List<Cancion> cancionesLista = cancionDao.read();
                for(Cancion i : cancionesLista){
                    System.out.println("Id: " + i.getNum_cancion());
                    System.out.println("Nombre: " + i.getNombre());
                    System.out.println("Artista: " + i.getArtista());
                }
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                seguir = false;
                break;
        }
        return seguir;
    }
    }
   
