package test;

import DAO.CategoriasDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertCategorias {

    private CategoriasDAO categoriasDAO;

    @BeforeEach
    void setUp() {
        // Inicializamos CategoriasDAO con id_categoria -1 para insertar una nueva
        categoriasDAO = new CategoriasDAO(-1, "Categoría de prueba");
    }

    @Test
    void testGetById() {
        // Creamos y guardamos una nueva categoría
        CategoriasDAO dao = new CategoriasDAO(-1, "Categoría con ID");
        dao.save();  // Esto debería insertar la categoría en la base de datos

        // Ahora obtenemos la categoría por ID
        CategoriasDAO daoConId = new CategoriasDAO();
        daoConId.getById(dao.getId_categoria());  // Recuperamos la categoría por el ID

        // Verificamos que el nombre de la categoría no sea nulo
        assertNotNull(daoConId.getNombre(), "El nombre de la categoría no debe ser nulo");

        // Verificamos que el nombre sea el mismo que el insertado
        assertEquals("Categoría con ID", daoConId.getNombre(), "El nombre de la categoría debe coincidir");
    }
}
