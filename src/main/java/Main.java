import model.MySqlConnection;
import view.*;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        try {
            Connection conn = MySqlConnection.getConnection();
            if (conn != null) {
                System.out.println("Conexion exitosa");
                while (true) {
                    System.out.println("Menu de opciones:");
                    System.out.println("-----------------");
                    System.out.println("Insertar datos [1]");
                    System.out.println("Actualizar datos [2]");
                    System.out.println("Eliminar datos [3]");
                    System.out.println("Consultas [4]");
                    System.out.println("Consultas Avanzadas [5]");
                    System.out.println("Salir [6]");
                    System.out.println("-----------------");
                    opcion = sc.nextInt();

                    switch (opcion) {
                        case 1:
                            //Llamar a la calse Insertar
                            Insertar insertar = new Insertar();
                            insertar.insertar();
                            break;
                        case 2:
                            Actualizar actualizar = new Actualizar();
                            actualizar.Actualizar();
                            break;
                        case 3:
                            Eliminar eliminar = new Eliminar();
                            eliminar.Eliminar();
                            break;
                        case 4:
                            Listar listar = new Listar();
                            listar.Listar();
                            break;

                            case 5:
                                ListarComplejas listarComplejas = new ListarComplejas();
                                listarComplejas.ListarComplejas();
                                break;
                        case 6:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Opcion no valida.");
                            break;
                    }
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
            MySqlConnection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

