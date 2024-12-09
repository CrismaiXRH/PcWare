package actions;

import DAO.ClientesDAO;
import DAO.FabricantesDAO;
import DAO.ComponentesDAO;
import DAO.CategoriasDAO;
import DAO.PedidosDAO;
import entity.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ListarComplejas {

    private PedidosDAO pedidosDAO;
    private ClientesDAO clientesDAO;
    private ComponentesDAO componentesDAO;
    private FabricantesDAO fabricantesDAO;
    private CategoriasDAO categoriasDAO;

    public ListarComplejas() {
        this.pedidosDAO = new PedidosDAO();
        this.clientesDAO = new ClientesDAO();
        this.componentesDAO = new ComponentesDAO();
        this.fabricantesDAO = new FabricantesDAO();
        this.categoriasDAO = new CategoriasDAO();

        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("1. Listar pedidos preguntando por el nombre de un cliente");
            System.out.println("2. Listar clientes con todo lo que se han gastado en pedidos");
            System.out.println("3. Listar componentes que nunca han sido comprados");
            System.out.println("4. Listar fabricantes junto con la cantidad de objetos que nos proporcionan");
            System.out.println("5. Listar productos más caros de cada categoría");
            System.out.println("6. Componentes que se han vendido más de X veces");
            System.out.println("7. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch(opcion) {
                case 1:
                    System.out.print("Introduzca el nombre del cliente: ");
                    String nombreCliente = sc.next();
                    List<Map<String, Object>> pedidos = pedidosDAO.getPedidosPorCliente(nombreCliente);
                    if (pedidos.isEmpty()) {
                        System.out.println("No se encontraron pedidos para el cliente " + nombreCliente);
                    } else {
                        for (Map<String, Object> pedido : pedidos) {
                            System.out.println("ID Pedido: " + pedido.get("id_pedido"));
                            System.out.println("Fecha: " + pedido.get("fecha"));
                            System.out.println("Componente: " + pedido.get("componente"));
                            System.out.println("Cantidad: " + pedido.get("cantidad"));
                            System.out.println("Precio: " + pedido.get("precio"));
                            System.out.println("Total: " + pedido.get("total"));
                            System.out.println("----------------------------");
                        }
                    }
                    break;

                case 2:
                    List<Map<String, Object>> clientesConGastos = clientesDAO.getClientesConGastos();
                    if (clientesConGastos.isEmpty()) {
                        System.out.println("No se encontraron clientes con pedidos.");
                    } else {
                        for (Map<String, Object> cliente : clientesConGastos) {
                            System.out.println("ID Cliente: " + cliente.get("id_cliente"));
                            System.out.println("Nombre: " + cliente.get("nombre"));
                            System.out.println("Total Gastado: " + cliente.get("total_gastado"));
                            System.out.println("----------------------------");
                        }
                    }
                    break;

                case 3:
                    List<Componentes> componentesNoComprados = componentesDAO.getComponentesNoComprados();
                    if (componentesNoComprados.isEmpty()) {
                        System.out.println("Todos los componentes han sido comprados.");
                    } else {
                        for (Componentes componente : componentesNoComprados) {
                            System.out.println("ID Componente: " + componente.getId_componente());
                            System.out.println("Nombre: " + componente.getNombre());
                            System.out.println("----------------------------");
                        }
                    }
                    break;

                case 4:
                    List<Map<String, Object>> fabricantesConCantidad = fabricantesDAO.getFabricantesConCantidadComponentes();
                    if (fabricantesConCantidad.isEmpty()) {
                        System.out.println("No se encontraron fabricantes con componentes.");
                    } else {
                        for (Map<String, Object> fabricante : fabricantesConCantidad) {
                            System.out.println("Fabricante: " + fabricante.get("fabricante"));
                            System.out.println("Cantidad de Componentes: " + fabricante.get("cantidad_componentes"));
                            System.out.println("----------------------------");
                        }
                    }
                    break;

                case 5:
                    List<Map<String, Object>> productosCaros = categoriasDAO.getProductosCarosPorCategoria();
                    if (productosCaros.isEmpty()) {
                        System.out.println("No se encontraron productos caros por categoría.");
                    } else {
                        for (Map<String, Object> producto : productosCaros) {
                            System.out.println("Categoría: " + producto.get("categoria"));
                            System.out.println("Producto más caro: " + producto.get("producto_mas_caro"));
                            System.out.println("Precio: " + producto.get("precio"));
                            System.out.println("----------------------------");
                        }
                    }
                    break;

                case 6:
                    System.out.print("Introduzca la cantidad mínima de ventas: ");
                    int cantidadMinima = sc.nextInt();
                    List<Map<String, Object>> componentesVendidos = componentesDAO.getComponentesVendidosMasXVeces(cantidadMinima);
                    if (componentesVendidos.isEmpty()) {
                        System.out.println("No se encontraron componentes que cumplan con la condición.");
                    } else {
                        for (Map<String, Object> componente : componentesVendidos) {
                            System.out.println("ID Componente: " + componente.get("id_componente"));
                            System.out.println("Nombre: " + componente.get("nombre"));
                            System.out.println("Cantidad Vendida: " + componente.get("cantidad_vendida"));
                            System.out.println("----------------------------");
                        }
                    }
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public void ListarComplejas() {
    }
}
