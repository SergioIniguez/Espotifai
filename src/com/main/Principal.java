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
        System.out.println("<----Has ingresado a ESPOTIFAI, Bienvenido---->");  
        System.out.println("Ingresa la opción que desees");
        System.out.println("1 -> Crear nueva canción");
        System.out.println("2 -> Mostrar canciones");
        System.out.println("3 -> Actualizar canción");
        System.out.println("4 -> Eliminar canción");
        System.out.println("5 -> Salir");
        
        int opcion = scan.nextInt();
        flag = eleccion(opcion);              
        }
        System.out.println("Gracias por usar ESPOTIFAI");
    }
     private static boolean eleccion(int opcion) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Cancion cancion = new Cancion();
        CancionDaoImpl cancionDao = new CancionDaoImpl();
        List <Cancion> cancionesLista = cancionDao.read();
        boolean resultado = true; 
        boolean seguir = true; 
        boolean actualizado = true;
        switch(opcion){
            case 1:
                System.out.println("Ingresa el nombre de la canción");
                String nombre_cancion = scan.nextLine();
                System.out.println("Ingresa el nombre del artista");
                String nombre_artista = scan.nextLine();
                
                cancion.setNombre(nombre_cancion);
                cancion.setArtista(nombre_artista);
                
                resultado = cancionDao.create(cancion);
                if(resultado)
                    System.out.println("Se ingresó corrrectamente \n");
                else
                    System.out.println("No se arma \n");   
                
                break;
            case 2:
                System.out.println("<----Lista de Canciones---->");
                for(Cancion i : cancionesLista){
                    System.out.println("Id: " + i.getNum_cancion());
                    System.out.println("Nombre: " + i.getNombre());
                    System.out.println("Artista: " + i.getArtista() + "\n");
                }
                break;
            case 3:
                System.out.println("<----Modifica la canción---->");
                System.out.println("Elige la canción a modificar");
                
                for(Cancion i:cancionesLista){
                    System.out.println(i.getNum_cancion() + " -> " + i.getNombre() + " - " 
                            + i.getArtista());
                }
                int selCancion = scan.nextInt();
                scan.nextLine();
                System.out.println("Ingresa el nuevo nombre para la canción:");
                String nuevo_nombre_cancion =  scan.nextLine();
                System.out.println("Ingresa el nuevo artista para la canción:");
                String nuevo_nombre_artista =  scan.nextLine();                

                cancion.setNombre(nuevo_nombre_cancion);
                cancion.setArtista(nuevo_nombre_artista);                

                actualizado = cancionDao.update(cancion,selCancion);
                
                if(actualizado)
                    System.out.println("Se actualizó correctamente \n");
                else
                    System.out.println("No se pudo actualizar carnal :( \n");
                
                break;
            case 4:
                System.out.println("<----Elimina la Canción---->");
                System.out.println("Seleciona la canción a eliminar");
                
                for(Cancion i:cancionesLista){
                    System.out.println(i.getNum_cancion() + " -> " + i.getNombre() + " - " 
                            + i.getArtista());
                }

                int elimina = scan.nextInt();
                boolean eliminado = cancionDao.delete(elimina);

                if(eliminado)
                    System.out.println("Se elimino correctamente");
                else
                    System.out.println("No pudo eliminar carnal :(");
                
                break;
            case 5:
                seguir = false;
                break;
        }
        return seguir;
    }
    }
   
