package entity;

import java.util.Objects;

public class Componentes {
    protected int id_componente;
    protected String nombre;
    protected String descripcion;
    protected int precio;
    protected int id_categoria;
    protected int id_fabricante;

    public Componentes(int id_componente, String nombre, String descripcion, int precio, int id_categoria, int id_fabricante) {
        this.id_componente = id_componente;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.id_fabricante = id_fabricante;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_fabricante() {
        return id_fabricante;
    }

    public void setId_fabricante(int id_fabricante) {
        this.id_fabricante = id_fabricante;
    }

    @Override
    public String toString() {
        return "Componentes{" +
                "id_componente=" + id_componente +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", id_categoria=" + id_categoria +
                ", id_fabricante=" + id_fabricante +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Componentes that = (Componentes) o;
        return id_componente == that.id_componente && precio == that.precio && id_categoria == that.id_categoria && id_fabricante == that.id_fabricante && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_componente, nombre, descripcion, precio, id_categoria, id_fabricante);
    }
}
