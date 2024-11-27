package entity;

import java.util.Objects;

public class PedidosComponentes {
    protected int id_pedido;
    protected int id_componente;
    protected int cantidad;

    public PedidosComponentes(int id_pedido, int id_componente, int cantidad) {
        this.id_pedido = id_pedido;
        this.id_componente = id_componente;
        this.cantidad = cantidad;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "PedidosComponentes{" +
                "id_pedido=" + id_pedido +
                ", id_componente=" + id_componente +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidosComponentes that = (PedidosComponentes) o;
        return id_pedido == that.id_pedido && id_componente == that.id_componente && cantidad == that.cantidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pedido, id_componente, cantidad);
    }
}
