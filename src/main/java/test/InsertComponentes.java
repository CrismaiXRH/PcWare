package test;

import DAO.ComponentesDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class InsertComponentes {
    private ComponentesDAO componentesDAO;

    @BeforeEach
    void setUp() {
        componentesDAO = new ComponentesDAO(-1, "Componente de prueba", "Descripción", 1000, 1, 1);
    }

    @Test
    void testGetById() {
        componentesDAO.save();  // Esto debería insertar el componente en la base de datos

        ComponentesDAO daoConId = new ComponentesDAO();
        daoConId.getById(componentesDAO.getId_componente());  // Recuperamos el componente por el ID

        // Verificamos que el nombre del componente no sea nulo
        assertNotNull(daoConId.getNombre(), "El nombre del componente no debe ser nulo");

        // Verificamos que el nombre sea el mismo que el insertado
        assertEquals("Componente de prueba", daoConId.getNombre(), "El nombre del componente debe coincidir");
    }
}
