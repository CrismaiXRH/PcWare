package DAO;

import entity.Componentes;

public class ComponentesDAO extends Componentes {
    public ComponentesDAO(int id_componente, String nombre, String descripcion, int precio, int id_categoria, int id_fabricante) {
        super(id_componente, nombre, descripcion, precio, id_categoria, id_fabricante);
    }
}
