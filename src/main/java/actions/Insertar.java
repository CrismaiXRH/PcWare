package actions;

import DAO.*;

import java.util.Scanner;

public class Insertar {
    int opcion;

    public Insertar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
        System.out.println("Que quieres insertar?");
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
                sc.nextLine();
                System.out.println("Insertar Fabricante");
                System.out.println("Nombre: ");
                String nombreFabricante = sc.nextLine();
                System.out.println("País: ");
                String pais = sc.nextLine();
                System.out.println("Teléfono: ");
                String telefono = sc.nextLine();
                FabricantesDAO fabricantesDAO = new FabricantesDAO();
                fabricantesDAO.setNombre(nombreFabricante);
                fabricantesDAO.setPais(pais);
                fabricantesDAO.setTelefono(telefono);
                fabricantesDAO.save();
                break;
            case 2:
                sc.nextLine();
                System.out.println("Insertar Componente");
                System.out.println("Nombre: ");
                String nombreComponente = sc.nextLine();
                System.out.println("Decripción: ");
                String descripcion = sc.nextLine();
                System.out.println("Precio: ");
                int precio = sc.nextInt();
                System.out.println("Id Categoría: ");
                int id_categoria = sc.nextInt();
                System.out.println("Id Fabricante: ");
                int id_fabricante = sc.nextInt();
                ComponentesDAO componentesDAO = new ComponentesDAO();
                componentesDAO.setNombre(nombreComponente);
                componentesDAO.setDescripcion(descripcion);
                componentesDAO.setPrecio(precio);
                componentesDAO.setId_categoria(id_categoria);
                componentesDAO.setId_fabricante(id_fabricante);
                componentesDAO.save();
                break;
            case 3:
                sc.nextLine();
                System.out.println("Insertar Categoría");
                System.out.println("Nombre: ");
                String nombreCategoria = sc.nextLine();
                CategoriasDAO categoriasDAO = new CategoriasDAO();
                categoriasDAO.setNombre(nombreCategoria);
                categoriasDAO.save();
                break;
            case 4:
                sc.nextLine();
                System.out.println("Insertar Pedido");
                System.out.println("Fecha: ");
                String fecha = sc.nextLine();
                System.out.println("Id Cliente: ");
                int id_cliente = sc.nextInt();
                PedidosDAO pedidosDAO = new PedidosDAO();
                pedidosDAO.setFecha(fecha);
                pedidosDAO.setId_cliente(id_cliente);
                pedidosDAO.save();
                break;
            case 5:
                sc.nextLine();
                System.out.println("Insertar PedidoComponente");
                System.out.println("Id Pedido: ");
                int id_pedido = sc.nextInt();
                System.out.println("Id Componente: ");
                int id_componente = sc.nextInt();
                System.out.println("Cantidad: ");
                int cantidad = sc.nextInt();
                PedidosComponentesDAO pedidosComponentesDAO = new PedidosComponentesDAO();
                pedidosComponentesDAO.setId_pedido(id_pedido);
                pedidosComponentesDAO.setId_componente(id_componente);
                pedidosComponentesDAO.setCantidad(cantidad);
                pedidosComponentesDAO.save();
                break;
            case 6:
                sc.nextLine();
                System.out.println("Insertar Cliente");
                System.out.println("Nombre: ");
                String nombre = sc.nextLine();
                System.out.println("Email: ");
                String email = sc.nextLine();
                System.out.println("Teléfono: ");
                String telefonoCliente = sc.nextLine();
                ClientesDAO clientesDAO = new ClientesDAO();
                clientesDAO.setNombre(nombre);
                clientesDAO.setCorreo(email);
                clientesDAO.setTelefono(telefonoCliente);
                clientesDAO.save();
                break;
            case 7:
                return;
            default:
                System.out.println("Opción no válida");
                break;
            }
        }
    }


    public int getOpcion() {
        return this.opcion;
    }

    public void insertar() {
    }
}
