package entity;

import java.util.Objects;

public class Categorias {

    protected int id_categoria;
    protected String nombre;

    // Constructor con parámetros
    public Categorias(int id_categoria, String nombre) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }

    // Constructor por defecto
    public Categorias() {}

    // Métodos getter y setter
    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString para mostrar el objeto en formato legible
    @Override
    public String toString() {
        return "Categorias{" +
                "id_categoria=" + id_categoria +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    // Método equals para comparar objetos de tipo Categorias
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorias that = (Categorias) o;
        return id_categoria == that.id_categoria && Objects.equals(nombre, that.nombre);
    }

    // Método hashCode para generar un código hash único basado en los campos id_categoria y nombre
    @Override
    public int hashCode() {
        return Objects.hash(id_categoria, nombre);
    }
}
