package DAO;

import entity.Pedidos;

import java.util.Calendar;

public class PedidosDAO extends Pedidos {
    public PedidosDAO(int id_pedido, Calendar fecha, int id_cliente) {
        super(id_pedido, fecha, id_cliente);
    }
}
