package test;

import DAO.ClientesDAO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateCliente {

    @Test
    void testUpdateClienteExistente() {
        // Supongamos que ya existe un cliente con ID 1 en la base de datos
        int idClienteExistente = 1;

        // Recuperamos el cliente existente por su ID
        ClientesDAO cliente = new ClientesDAO();
        cliente.getById(idClienteExistente);

        // Verificamos que el cliente realmente existe
        assertNotNull(cliente.getNombre(), "El cliente con ID 1 debe existir en la base de datos");

        // Modificamos los datos del cliente
        cliente.setNombre("Cliente Modificado");
        cliente.setCorreo("Prueba2");
        cliente.setTelefono("6253");
        cliente.save(); // Esto debe realizar el UPDATE en la base de datos

        // Recuperamos el cliente nuevamente para verificar los cambios
        ClientesDAO clienteActualizado = new ClientesDAO();
        clienteActualizado.getById(idClienteExistente);

        // Validamos que los datos actualizados coincidan
        assertEquals("Cliente Modificado", clienteActualizado.getNombre(), "El nombre debe haberse actualizado");
        assertEquals("Prueba2", clienteActualizado.getCorreo(), "La dirección debe haberse actualizado");
        assertEquals("6253", clienteActualizado.getTelefono(), "El teléfono debe haberse actualizado");
    }
}
