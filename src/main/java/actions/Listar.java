package actions;

import DAO.ClientesDAO;
import DAO.FabricantesDAO;
import DAO.ComponentesDAO;
import DAO.CategoriasDAO;
import DAO.PedidosDAO;
import DAO.PedidosComponentesDAO;
import java.util.Scanner;

public class Listar {
    int opcion;

    public Listar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("¿Qué deseas consultar?");
            System.out.println("1. Fabricante");
            System.out.println("2. Componente");
            System.out.println("3. Categoría");
            System.out.println("4. Pedido");
            System.out.println("5. PedidoComponente");
            System.out.println("6. Cliente");
            System.out.println("7. Salir");

            this.opcion = sc.nextInt();

            switch (this.opcion) {
                case 1:
                    // Listar Fabricante
                    break;
                case 2:
                    // Listar Componente
                    break;
                case 3:
                    // Listar Categoría

                    break;
                case 4:
                    // Listar Pedido

                    break;
                case 5:
                    // Listar PedidoComponente

                    break;
                case 6:

                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    public int getOpcion() {
        return this.opcion;
    }

    public void consultar() {
        // Método vacío, para cumplir con la estructura
    }
}
