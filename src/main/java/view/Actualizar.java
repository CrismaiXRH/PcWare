package view;

import DAO.*;

import java.util.Scanner;

public class Actualizar {
    int opcion;

    public Actualizar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Que quieres actualizar?");
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
                    System.out.println("Actualizar Fabricante");
                    System.out.println("Id: ");
                    int id_fabricante = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Dejar vacío para no actualizar el campo");
                    System.out.println("Nombre: ");
                    String nombreFabricante = sc.nextLine();

                    System.out.println("País: ");
                    String pais = sc.nextLine();

                    System.out.println("Teléfono: ");
                    String telefono = sc.nextLine();

                    FabricantesDAO fabricantesDAO = new FabricantesDAO();
                    fabricantesDAO.setId_fabricante(id_fabricante);

                    if (!nombreFabricante.isEmpty()) {
                        fabricantesDAO.setNombre(nombreFabricante);
                    }
                    if (!pais.isEmpty()) {
                        fabricantesDAO.setPais(pais);
                    }
                    if (!telefono.isEmpty()) {
                        fabricantesDAO.setTelefono(telefono);
                    }

                    fabricantesDAO.update();
                    break;
                case 2:
                    System.out.println("Actualizar Componente");
                    System.out.println("Id del componente: ");
                    int id_componente = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Dejar vacío para no actualizar el campo (o ingrese 0 para valores numéricos)");
                    System.out.println("Nombre: ");
                    String nombreComponente = sc.nextLine();

                    System.out.println("Descripción: ");
                    String descripcion = sc.nextLine();

                    System.out.println("Precio (entero): ");
                    int precio = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Id de la categoría: ");
                    int id_categoriaComponente = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Id del fabricante: ");
                    int id_fabricanteComponente = sc.nextInt();
                    sc.nextLine();

                    ComponentesDAO componentesDAO = new ComponentesDAO();
                    componentesDAO.setId_componente(id_componente);

                    if (!nombreComponente.isEmpty()) {
                        componentesDAO.setNombre(nombreComponente);
                    }
                    if (!descripcion.isEmpty()) {
                        componentesDAO.setDescripcion(descripcion);
                    }
                    if (precio != 0) {
                        componentesDAO.setPrecio(precio);
                    }
                    if (id_categoriaComponente != 0) {
                        componentesDAO.setId_categoria(id_categoriaComponente);
                    }
                    if (id_fabricanteComponente != 0) {
                        componentesDAO.setId_fabricante(id_fabricanteComponente);
                    }

                    componentesDAO.update();

                    break;
                case 3:
                    System.out.println("Actualizar Categoría");
                    System.out.println("Id de la categoría: ");
                    int id_categoria = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Dejar vacío para no actualizar el campo");
                    System.out.println("Nombre: ");
                    String nombreCategoria = sc.nextLine();

                    CategoriasDAO categoriasDAO = new CategoriasDAO();
                    categoriasDAO.setId_categoria(id_categoria);

                    if (!nombreCategoria.isEmpty()) {
                        categoriasDAO.setNombre(nombreCategoria);
                    }

                    categoriasDAO.update();

                    break;
                case 4:
                    System.out.println("Actualizar Pedido");
                    System.out.println("Id del pedido: ");
                    int id_pedido = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Dejar vacío para no actualizar el campo");
                    System.out.println("Fecha (YYYY-MM-DD): ");
                    String fecha = sc.nextLine();

                    System.out.println("Id del cliente: ");
                    String idClienteInput = sc.nextLine();

                    PedidosDAO pedidosDAO = new PedidosDAO();
                    pedidosDAO.setId_pedido(id_pedido);

                    if (!fecha.isEmpty()) {
                        pedidosDAO.setFecha(fecha);
                    }
                    if (!idClienteInput.isEmpty()) {
                        pedidosDAO.setId_cliente(Integer.parseInt(idClienteInput));
                    }

                    pedidosDAO.update();

                    break;
                case 5:
                    System.out.println("Actualizar Pedido-Componente");
                    System.out.println("Id del pedido: ");
                    int id_pedidoTablaCompuesta = sc.nextInt();
                    System.out.println("Id del componente: ");
                    int id_componenteTablaCompuesta = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Dejar vacío para no actualizar el campo");
                    System.out.println("Cantidad: ");
                    String cantidadInput = sc.nextLine();

                    PedidosComponentesDAO pedidosComponentesDAO = new PedidosComponentesDAO();
                    pedidosComponentesDAO.setId_pedido(id_pedidoTablaCompuesta);
                    pedidosComponentesDAO.setId_componente(id_componenteTablaCompuesta);

                    if (!cantidadInput.isEmpty()) {
                        pedidosComponentesDAO.setCantidad(Integer.parseInt(cantidadInput));
                    }

                    pedidosComponentesDAO.update();

                    break;
                case 6:
                    System.out.println("Actualizar Cliente");
                    System.out.println("Id del cliente: ");
                    int id_cliente = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Dejar vacío para no actualizar el campo");
                    System.out.println("Nombre: ");
                    String nombreCliente = sc.nextLine();

                    System.out.println("Correo: ");
                    String correo = sc.nextLine();

                    System.out.println("Teléfono: ");
                    String telefonoCliente = sc.nextLine();

                    ClientesDAO clientesDAO = new ClientesDAO();
                    clientesDAO.setId_cliente(id_cliente);

                    if (!nombreCliente.isEmpty()) {
                        clientesDAO.setNombre(nombreCliente);
                    }
                    if (!correo.isEmpty()) {
                        clientesDAO.setCorreo(correo);
                    }
                    if (!telefonoCliente.isEmpty()) {
                        clientesDAO.setTelefono(telefonoCliente);
                    }

                    clientesDAO.update();
                    break;
                case 7:
                    return;

                default:
                    System.out.println("Opcion incorrecta");

            }
        }
    }

    public int getOpcion() {
        return this.opcion;
    }
    public void Actualizar() {

    }
}
