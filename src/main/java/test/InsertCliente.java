package test;

import DAO.ClientesDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InsertCliente {
    private ClientesDAO clientesDAO;

    @BeforeEach
    void setUp() {
        clientesDAO = new ClientesDAO(-1, "Cliente de prueba", "pts", "telefono");
    }

    @Test
    void testGetById() {
        clientesDAO.save();  // Esto debería insertar el cliente en la base de datos

        ClientesDAO daoConId = new ClientesDAO();
        daoConId.getById(clientesDAO.getId_cliente());  // Recuperamos el cliente por el ID

        // Verificamos que el nombre del cliente no sea nulo
        assertNotNull(daoConId.getNombre(), "El nombre del cliente no debe ser nulo");

        // Verificamos que el nombre sea el mismo que el insertado
        assertEquals("Cliente de prueba", daoConId.getNombre(), "El nombre del cliente debe coincidir");
    }
}
