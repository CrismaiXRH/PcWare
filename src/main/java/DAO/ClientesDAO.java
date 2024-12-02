package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Clientes;

public class ClientesDAO extends Clientes {

    private static final String INSERT = "INSERT INTO clientes (nombre, correo, telefono) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE clientes SET nombre=?, correo=?, telefono=? WHERE id_cliente=?";
    private static final String DELETE = "DELETE FROM clientes WHERE id_cliente=?";
    private static final String SELECTALL = "SELECT * FROM clientes";
    private static final String SELECTONE = "SELECT * FROM clientes WHERE id_cliente=?";
    private static final String SELECTBYNAME = "SELECT * FROM clientes WHERE nombre=?";

    public ClientesDAO(int id_cliente, String nombre, String correo, String telefono) {
        super(id_cliente, nombre, correo, telefono);
    }

    public ClientesDAO() {
        super();
    }

    public ClientesDAO(Clientes cliente) {
        this(cliente.getId_cliente(), cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono());
    }

    public ClientesDAO(int id_cliente) {
        this.getById(id_cliente);
    }

    public void save() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getCorreo());
                ps.setString(3, this.getTelefono());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    }

    public void update() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                // Construcción dinámica del SQL
                StringBuilder sql = new StringBuilder("UPDATE clientes SET ");
                boolean hasFields = false;

                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    sql.append("nombre = ?, ");
                    hasFields = true;
                }
                if (this.getCorreo() != null && !this.getCorreo().isEmpty()) {
                    sql.append("correo = ?, ");
                    hasFields = true;
                }
                if (this.getTelefono() != null && !this.getTelefono().isEmpty()) {
                    sql.append("telefono = ?, ");
                    hasFields = true;
                }

                // Verificar si hay campos para actualizar
                if (!hasFields) {
                    System.out.println("No hay campos para actualizar.");
                    return;
                }

                // Quitar la última coma y agregar la cláusula WHERE
                sql.setLength(sql.length() - 2);
                sql.append(" WHERE id_cliente = ?");

                ps = conn.prepareStatement(sql.toString());

                // Asignar valores a los campos
                int index = 1;
                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    ps.setString(index++, this.getNombre());
                }
                if (this.getCorreo() != null && !this.getCorreo().isEmpty()) {
                    ps.setString(index++, this.getCorreo());
                }
                if (this.getTelefono() != null && !this.getTelefono().isEmpty()) {
                    ps.setString(index++, this.getTelefono());
                }
                ps.setInt(index, this.getId_cliente()); // Asignar el ID

                // Ejecutar la consulta
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void remove() {
        if (id_cliente != -1) {
            // DELETE
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = getWorkbenchConnection();
                if (conn != null) {
                    ps = conn.prepareStatement(DELETE);
                    ps.setInt(1, this.id_cliente);
                    if (ps.executeUpdate() == 1) {
                        this.id_cliente = -1; // Si se borra correctamente, marcamos como no existente
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getById(int id_cliente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTONE);
                ps.setInt(1, id_cliente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    this.id_cliente = rs.getInt("id_cliente");
                    this.nombre = rs.getString("nombre");
                    this.correo = rs.getString("correo");
                    this.telefono = rs.getString("telefono");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Clientes> getAll() {
        List<Clientes> result = new ArrayList<Clientes>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTALL);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Clientes c = new Clientes(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("correo"), rs.getString("telefono"));
                    result.add(c);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static Connection getWorkbenchConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PcWare", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}