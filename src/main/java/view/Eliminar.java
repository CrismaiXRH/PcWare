package view;

import DAO.*;

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
                    System.out.println("Eliminar Componente");
                    System.out.println("Id: ");
                    int id_componente = sc.nextInt();
                    ComponentesDAO componentesDAO = new ComponentesDAO();
                    componentesDAO.setId_fabricante(id_componente);
                    componentesDAO.remove();
                    break;
                case 3:
                    System.out.println("Eliminar Categoria");
                    System.out.println("Id: ");
                    int id_categoria = sc.nextInt();
                    CategoriasDAO categoriasDAO = new CategoriasDAO();
                    categoriasDAO.setId_categoria(id_categoria);
                    categoriasDAO.remove();
                    break;
                case 4:
                    System.out.println("Eliminar Pedido");
                    System.out.println("Id: ");
                    int id_pedido = sc.nextInt();
                    PedidosDAO pedidosDAO = new PedidosDAO();
                    pedidosDAO.setId_pedido(id_pedido);
                    pedidosDAO.delete();
                    break;
                case 5:
                    System.out.println("Eliminar PedidoComponente");
                    System.out.println("Id_pedido: ");
                    int id_pedido2 = sc.nextInt();
                    System.out.println("Id_componente: ");
                    int id_componente2 = sc.nextInt();

                    PedidosComponentesDAO pedidosComponentesDAO = new PedidosComponentesDAO();
                    pedidosComponentesDAO.setId_pedido(id_pedido2);  // Establecer el id_pedido
                    pedidosComponentesDAO.setId_componente(id_componente2);  // Establecer el id_componente

                    pedidosComponentesDAO.remove();

                    break;
                case 6:
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