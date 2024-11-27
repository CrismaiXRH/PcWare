package entity;

import java.util.Objects;

public class Fabricantes {
    protected int id_fabricante;
    protected String nombre;
    protected String pais;
    protected String telefono;

    public Fabricantes(int id_fabricante, String nombre, String pais, String telefono) {
        this.id_fabricante = id_fabricante;
        this.nombre = nombre;
        this.pais = pais;
        this.telefono = telefono;
    }

    public int getId_fabricante() {
        return id_fabricante;
    }

    public void setId_fabricante(int id_fabricante) {
        this.id_fabricante = id_fabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Fabricantes{" +
                "id_fabricante=" + id_fabricante +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fabricantes that = (Fabricantes) o;
        return id_fabricante == that.id_fabricante && Objects.equals(nombre, that.nombre) && Objects.equals(pais, that.pais) && Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_fabricante, nombre, pais, telefono);
    }


}
