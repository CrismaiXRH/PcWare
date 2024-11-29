package entity;

import java.util.Objects;

public class Clientes {
    protected int id_cliente;
    protected String nombre;
    protected String correo;
    protected String telefono;

    public Clientes(int id_cliente, String nombre, String correo, String telefono) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Clientes() {

    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "id_cliente=" + id_cliente +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientes clientes = (Clientes) o;
        return id_cliente == clientes.id_cliente && Objects.equals(nombre, clientes.nombre) && Objects.equals(correo, clientes.correo) && Objects.equals(telefono, clientes.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cliente, nombre, correo, telefono);
    }
}
