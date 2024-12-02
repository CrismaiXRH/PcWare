package actions;

import DAO.ClientesDAO;
import DAO.FabricantesDAO;
import DAO.ComponentesDAO;
import DAO.CategoriasDAO;
import DAO.PedidosDAO;
import DAO.PedidosComponentesDAO;
import entity.*;

import java.util.List;
import java.util.Scanner;

public class Listar {
    int opcion;
    ComponentesDAO componentesDAO = new ComponentesDAO();
    FabricantesDAO fabricantesDAO = new FabricantesDAO();
    CategoriasDAO categoriasDAO = new CategoriasDAO();
    PedidosDAO pedidosDAO = new PedidosDAO();
    PedidosComponentesDAO pedidosComponentesDAO = new PedidosComponentesDAO();
    ClientesDAO clientesDAO = new ClientesDAO();

    public Listar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("¿Qué tabla deseas consultar?");
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
                    System.out.println("Listar Fabricante");
                    System.out.println("1. Listar todo");
                    System.out.println("2. Listar por id");
                    System.out.println("3. Listar por nombre");
                    System.out.println("4. Listar por pais");
                    System.out.println("5. Salir");
                    opcion = sc.nextInt();

                    switch (opcion) {
                        case 1:
                            List<Fabricantes> fabricantes = fabricantesDAO.getAll();
                            if (fabricantes.isEmpty()) {
                                System.out.println("No hay fabricantes registrados.");
                            } else {
                                for (Fabricantes f : fabricantes) {
                                    System.out.println("ID: " + f.getId_fabricante());
                                    System.out.println("Nombre: " + f.getNombre());
                                    System.out.println("País: " + f.getPais());
                                    System.out.println("Teléfono: " + f.getTelefono());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;

                        case 2:
                            System.out.println("Introduzca el ID del fabricante: ");
                            int idFabricante = sc.nextInt();
                            Fabricantes fabricante = fabricantesDAO.getById(idFabricante);
                            if (fabricante == null) {
                                System.out.println("No se encontraron fabricantes con ese ID.");
                            } else {
                                System.out.println("ID: " + fabricante.getId_fabricante());
                                System.out.println("Nombre: " + fabricante.getNombre());
                                System.out.println("País: " + fabricante.getPais());
                                System.out.println("Teléfono: " + fabricante.getTelefono());
                                System.out.println("----------------------------");
                            }
                            break;

                        case 3:
                            sc.nextLine();
                            System.out.println("Introduzca el nombre del fabricante: ");
                            String nombre = sc.nextLine();
                            List<Fabricantes> fabricantesPorNombre = fabricantesDAO.getByName(nombre);
                            if (fabricantesPorNombre.isEmpty()) {
                                System.out.println("No se encontraron fabricantes con ese nombre.");
                            } else {
                                for (Fabricantes f : fabricantesPorNombre) {
                                    System.out.println("ID: " + f.getId_fabricante());
                                    System.out.println("Nombre: " + f.getNombre());
                                    System.out.println("País: " + f.getPais());
                                    System.out.println("Teléfono: " + f.getTelefono());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;

                        case 4:
                            sc.nextLine();
                            System.out.println("Introduzca el país del fabricante: ");
                            String pais = sc.nextLine();
                            List<Fabricantes> fabricantesPorPais = fabricantesDAO.getByPais(pais);
                            if (fabricantesPorPais.isEmpty()) {
                                System.out.println("No se encontraron fabricantes en ese país.");
                            } else {
                                for (Fabricantes f : fabricantesPorPais) {
                                    System.out.println("ID: " + f.getId_fabricante());
                                    System.out.println("Nombre: " + f.getNombre());
                                    System.out.println("País: " + f.getPais());
                                    System.out.println("Teléfono: " + f.getTelefono());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;

                        case 5:
                            return;

                        default:
                            System.out.println("Opción no válida");
                    }


                    break;
                case 2:
                    System.out.println("Listar Componente");
                    System.out.println("1. Listar todo");
                    System.out.println("2. Listar por id");
                    System.out.println("3. Listar por nombre");
                    System.out.println("4. Listar por precio exacto");
                    System.out.println("5. Listar por precio mayor a");
                    System.out.println("6. Listar por precio menor a");
                    System.out.println("7. Salir");
                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            List<Componentes> componentes = componentesDAO.getAll();
                            if (componentes.isEmpty()) {
                                System.out.println("No hay componentes registrados.");
                            } else {
                                for (Componentes c : componentes) {
                                    System.out.println("ID: " + c.getId_componente());
                                    System.out.println("Nombre: " + c.getNombre());
                                    System.out.println("Precio: " + c.getPrecio());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Introduzca el ID del componente: ");
                            int idComponente = sc.nextInt();
                            Componentes componente = componentesDAO.getById(idComponente);
                            if (componente == null) {
                                System.out.println("No se encontraron componentes con ese ID.");
                            } else {
                                System.out.println("ID: " + componente.getId_componente());
                                System.out.println("Nombre: " + componente.getNombre());
                                System.out.println("Precio: " + componente.getPrecio());
                                System.out.println("----------------------------");
                            }
                            break;
                        case 3:
                            sc.nextLine();
                            System.out.println("Introduzca el nombre del componente: ");
                            String nombre = sc.nextLine();
                            Componentes componentePorNombre = componentesDAO.getByName(nombre);
                            if (componentePorNombre == null) {
                                System.out.println("No se encontraron componentes con ese nombre.");
                            } else {
                                System.out.println("ID: " + componentePorNombre.getId_componente());
                                System.out.println("Nombre: " + componentePorNombre.getNombre());
                                System.out.println("Precio: " + componentePorNombre.getPrecio());
                                System.out.println("----------------------------");

                            }
                            break;
                        case 4:
                            System.out.println("Introduzca el precio del componente: ");
                            int precio = sc.nextInt();
                            List<Componentes> componentesPorPrecio = componentesDAO.getByPrice(precio);
                            if (componentesPorPrecio.isEmpty()) {
                                System.out.println("No se encontraron componentes con ese precio.");
                            } else {
                                for (Componentes c : componentesPorPrecio) {
                                    System.out.println("ID: " + c.getId_componente());
                                    System.out.println("Nombre: " + c.getNombre());
                                    System.out.println("Precio: " + c.getPrecio());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;
                        case 5:
                            System.out.println("Introduzca el precio mínimo del componente: ");
                            int precio2 = sc.nextInt();
                            List<Componentes> componentesPorPrecio2 = componentesDAO.getByPriceUpper(precio2);
                            if (componentesPorPrecio2.isEmpty()) {
                                System.out.println("No se encontraron componentes con precio mayor a " + precio2);
                            } else {
                                for (Componentes c : componentesPorPrecio2) {
                                    System.out.println("ID: " + c.getId_componente());
                                    System.out.println("Nombre: " + c.getNombre());
                                    System.out.println("Precio: " + c.getPrecio());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;

                        case 6:
                            System.out.println("Introduzca el precio máximo del componente: ");
                            int precio3 = sc.nextInt();
                            List<Componentes> componentesPorPrecio3 = componentesDAO.getByPriceLower(precio3);
                            if (componentesPorPrecio3.isEmpty()) {
                                System.out.println("No se encontraron componentes con precio menor a " + precio3);
                            } else {
                                for (Componentes c : componentesPorPrecio3) {
                                    System.out.println("ID: " + c.getId_componente());
                                    System.out.println("Nombre: " + c.getNombre());
                                    System.out.println("Precio: " + c.getPrecio());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;
                        case 7:
                            return;
                        default:
                            System.out.println("Opción no válida");
                    }

                    break;
                case 3:

                    System.out.println("Listar Categoría");
                    System.out.println("1. Listar todas");
                    System.out.println("2. Listar por id");
                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            List<Categorias> categorias = categoriasDAO.getAll();
                            if (categorias.isEmpty()) {
                                System.out.println("No se encontraron categorías.");
                            } else {
                                for (Categorias c : categorias) {
                                    System.out.println("ID: " + c.getId_categoria());
                                    System.out.println("Nombre: " + c.getNombre());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Introduzca el ID del pedido: ");
                            int idPedido = sc.nextInt();
                            Pedidos pedidos = pedidosDAO.getById(idPedido);
                            if (pedidos == null) {
                                System.out.println("No se encontraron componentes con ese ID.");
                            } else {
                                System.out.println("ID: " + pedidos.getId_pedido());
                                System.out.println("Fecha: " + pedidos.getFecha());
                                System.out.println("----------------------------");
                            }
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                    break;
                case 4:
                    System.out.println("Listar Pedidos");
                    System.out.println("1. Listar todos");
                    System.out.println("2. Listar por id");
                    System.out.println("3. Listar por cliente");
                    System.out.println("4. Listar por fecha");

                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            List<Pedidos> pedidos = pedidosDAO.getAll();
                            if (pedidos.isEmpty()) {
                                System.out.println("No se encontraron pedidos.");
                            } else {
                                for (Pedidos p : pedidos) {
                                    System.out.println("ID: " + p.getId_pedido());
                                    System.out.println("Fecha: " + p.getFecha());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Introduzca el ID del pedido: ");
                            int idPedido = sc.nextInt();
                            Pedidos pedido = pedidosDAO.getById(idPedido);
                            if (pedido == null) {
                                System.out.println("No se encontraron pedidos con ese ID.");
                            } else {
                                System.out.println("ID: " + pedido.getId_pedido());
                                System.out.println("Fecha: " + pedido.getFecha());
                                System.out.println("----------------------------");
                            }
                            break;
                        case 3:
                            System.out.println("Introduzca el ID del cliente: ");
                            int idCliente = sc.nextInt();
                            List<Pedidos> pedidosPorCliente = pedidosDAO.getByCliente(idCliente);
                            if (pedidosPorCliente.isEmpty()) {
                                System.out.println("No se encontraron pedidos para ese cliente.");
                            } else {
                                for (Pedidos p : pedidosPorCliente) {
                                    System.out.println("ID: " + p.getId_pedido());
                                    System.out.println("Fecha: " + p.getFecha());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;
                       case 4:
                            System.out.println("Introduzca la fecha del pedido: ");
                            String fecha = sc.next();
                            List<Pedidos> pedidosPorFecha = pedidosDAO.getByFecha(fecha);
                            if (pedidosPorFecha.isEmpty()) {
                                System.out.println("No se encontraron pedidos para esa fecha.");
                            } else {
                                for (Pedidos p : pedidosPorFecha) {
                                    System.out.println("ID: " + p.getId_pedido());
                                    System.out.println("Fecha: " + p.getFecha());
                                    System.out.println("----------------------------");
                                }
                            }

                    }

                    break;
                case 5:
                    System.out.println("Listar PedidoComponentes");
                    System.out.println("1. Listar todos");
                    System.out.println("2. Listar por id");
                    System.out.println("3. Listar por cantidad");
                    System.out.println("4. Salir");
                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            List<PedidosComponentes> pedidoComponentes = pedidosComponentesDAO.getAll();
                            if (pedidoComponentes.isEmpty()) {
                                System.out.println("No se encontraron registros de pedidoComponentes.");
                            } else {
                                for (PedidosComponentes p : pedidoComponentes) {
                                    System.out.println("ID Pedido: " + p.getId_pedido());
                                    System.out.println("ID Componente: " + p.getId_componente());
                                    System.out.println("Cantidad: " + p.getCantidad());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;

                        case 2:
                            System.out.println("Introduzca el ID del pedido: ");
                            int idPedido = sc.nextInt();
                            System.out.println("Introduzca el ID del componente: ");
                            int idComponente = sc.nextInt();

                            PedidosComponentes pedidoComponente = pedidosComponentesDAO.getById(idPedido, idComponente);
                            if (pedidoComponente == null) {
                                System.out.println("No se encontraron registros con el ID proporcionado.");
                            } else {
                                System.out.println("ID Pedido: " + pedidoComponente.getId_pedido());
                                System.out.println("ID Componente: " + pedidoComponente.getId_componente());
                                System.out.println("Cantidad: " + pedidoComponente.getCantidad());
                                System.out.println("----------------------------");
                            }
                            break;

                        case 3:
                            System.out.println("Introduzca la cantidad mínima de componentes: ");
                            int cantidad = sc.nextInt();

                            List<PedidosComponentes> pedidoComponentesPorCantidad = pedidosComponentesDAO.getByCantidad(cantidad);
                            if (pedidoComponentesPorCantidad.isEmpty()) {
                                System.out.println("No se encontraron registros con una cantidad mayor o igual a " + cantidad + ".");
                            } else {
                                for (PedidosComponentes p : pedidoComponentesPorCantidad) {
                                    System.out.println("ID Pedido: " + p.getId_pedido());
                                    System.out.println("ID Componente: " + p.getId_componente());
                                    System.out.println("Cantidad: " + p.getCantidad());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;

                        case 4:
                            return;

                        default:
                            System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                    }

                    break;
                case 6:

                    System.out.println("Listar Clientes");
                    System.out.println("1. Listar todos");
                    System.out.println("2. Listar por id");
                    System.out.println("3. Listar por nombre");
                    System.out.println("4. Salir");
                    opcion = sc.nextInt();
                    switch (opcion){
                        case 1:
                            List<Clientes> clientes = clientesDAO.getAll();
                            if (clientes.isEmpty()) {
                                System.out.println("No se encontraron registros de clientes.");
                            } else {
                                for (Clientes c : clientes) {
                                    System.out.println("ID: " + c.getId_cliente());
                                    System.out.println("Nombre: " + c.getNombre());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Introduzca el ID del cliente: ");
                            int id = sc.nextInt();
                            Clientes cliente = clientesDAO.getById(id);
                            if (cliente == null) {
                                System.out.println("No se encontraron registros con el ID proporcionado.");
                            } else {
                                System.out.println("ID: " + cliente.getId_cliente());
                                System.out.println("Nombre: " + cliente.getNombre());
                                System.out.println("----------------------------");
                            }
                            break;
                        case 3:
                            System.out.println("Introduzca el nombre del cliente: ");
                            String nombre = sc.next();
                            List<Clientes> clientesPorNombre = clientesDAO.getByName(nombre);
                            if (clientesPorNombre.isEmpty()) {
                                System.out.println("No se encontraron registros con el nombre proporcionado.");
                            } else {
                                for (Clientes c : clientesPorNombre) {
                                    System.out.println("ID: " + c.getId_cliente());
                                    System.out.println("Nombre: " + c.getNombre());
                                    System.out.println("----------------------------");
                                }
                            }
                            break;
                        case 4:
                            return;
                        default:
                            System.out.println("Opción no válida. Por favor, selecciona una opcion valida.");
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }

    }

    public void Listar() {

    }

}