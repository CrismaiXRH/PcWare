package entity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

public class Pedidos {

    protected int id_pedido;
    protected Calendar fecha;
    protected int id_cliente;

    public Pedidos(int id_pedido, Calendar fecha, int id_cliente) {
        this.id_pedido = id_pedido;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
    }
    public Pedidos() {}

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "id_pedido=" + id_pedido +
                ", fecha=" + fecha +
                ", id_cliente=" + id_cliente +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedidos pedidos = (Pedidos) o;
        return id_pedido == pedidos.id_pedido && id_cliente == pedidos.id_cliente && Objects.equals(fecha, pedidos.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pedido, fecha, id_cliente);
    }
}
