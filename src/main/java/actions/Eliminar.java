package actions;

import DAO.ClientesDAO;
import DAO.FabricantesDAO;
import DAO.PedidosComponentesDAO;

import java.util.Scanner;

public class Eliminar {
    int opcion;

    public Eliminar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Que quieres eliminar?");
            System.out.println("1. Fabricante");
            System.out.println("2. Componente");
            System.out.println("3. Categoría");
            System.out.println("4. Pedido");
            System.out.println("5. PedidoComponente");
            System.out.println("6. Cliente");
            System.out.println("7. Salir");

            this.opcion = sc.nextInt();

            switch (this.opcion) {
                case 1:
                    System.out.println("Eliminar Fabricante");
                    System.out.println("Id: ");
                    int id_fabricante = sc.nextInt();
                    FabricantesDAO fabricantesDAO = new FabricantesDAO();
                    fabricantesDAO.setId_fabricante(id_fabricante);
                    fabricantesDAO.delete();
                    break;
                case 2:
                    //Lamar a la funcoin remove de ComponentesDAO
                    System.out.println("Eliminar Componente");
                    System.out.println("Id: ");
                    int id_componente = sc.nextInt();
                    FabricantesDAO fabricantesDAO2 = new FabricantesDAO();
                    fabricantesDAO2.setId_fabricante(id_componente);
                    fabricantesDAO2.delete();
                    break;
                case 3:
                    //Lamar a la funcoin remove de CategoriasDAO
                    System.out.println("Eliminar Categoria");
                    System.out.println("Id: ");
                    int id_categoria = sc.nextInt();
                    FabricantesDAO fabricantesDAO3 = new FabricantesDAO();
                    fabricantesDAO3.setId_fabricante(id_categoria);
                    fabricantesDAO3.delete();
                    break;
                case 4:
                    //Lamar a la funcoin remove de PedidosDAO
                    System.out.println("Eliminar Pedido");
                    System.out.println("Id: ");
                    int id_pedido = sc.nextInt();
                    FabricantesDAO fabricantesDAO4 = new FabricantesDAO();
                    fabricantesDAO4.setId_fabricante(id_pedido);
                    fabricantesDAO4.delete();
                    break;
                case 5:
                    // Llamar a la función remove de PedidosComponentesDAO
                    System.out.println("Eliminar PedidoComponente");
                    System.out.println("Id_pedido: ");
                    int id_pedido2 = sc.nextInt();
                    System.out.println("Id_componente: ");
                    int id_componente2 = sc.nextInt();

                    // Crear una instancia de PedidosComponentesDAO
                    PedidosComponentesDAO pedidosComponentesDAO = new PedidosComponentesDAO();
                    pedidosComponentesDAO.setId_pedido(id_pedido2);  // Establecer el id_pedido
                    pedidosComponentesDAO.setId_componente(id_componente2);  // Establecer el id_componente

                    // Llamar al método remove para eliminar el registro
                    pedidosComponentesDAO.remove();

                    break;
                case 6:
                    //Lamar a la funcoin remove de ClientesDAO
                    System.out.println("Eliminar Cliente");
                    System.out.println("Id: ");
                    int id_cliente = sc.nextInt();
                    ClientesDAO clientesDAO = new ClientesDAO();
                    clientesDAO.setId_cliente(id_cliente);
                    clientesDAO.remove();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    public int getOpcion() {
     return this.opcion;
    }

    public void Eliminar() {

    }
}