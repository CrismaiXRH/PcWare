package DAO;

import entity.Clientes;

public class ClientesDAO extends Clientes {
    public ClientesDAO (int id_cliente, String nombre, String correo, String telefono) {
        super(id_cliente, nombre, correo, telefono);
    }
}
