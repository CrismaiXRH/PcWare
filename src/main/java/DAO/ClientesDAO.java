package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Clientes;

public class ClientesDAO extends Clientes {

    private static final String INSERT = "INSERT INTO clientes (nombre, correo, telefono) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE clientes SET nombre=?, correo=?, telefono=? WHERE id_cliente=?";
    private static final String DELETE = "DELETE FROM clientes WHERE id_cliente=?";
    private static final String SELECTALL = "SELECT * FROM clientes";
    private static final String SELECTONE = "SELECT * FROM clientes WHERE id_cliente=?";
    private static final String SELECTBYNAME = "SELECT * FROM clientes WHERE nombre=?";
    private static final String SELECTCLIENTESGASTOS =
            "SELECT cli.id_cliente, cli.nombre, SUM(pc.cantidad * c.precio) AS total_gastado " +
                    "FROM Clientes cli " +
                    "JOIN Pedidos p ON cli.id_cliente = p.id_cliente " +
                    "JOIN Pedido_Componentes pc ON p.id_pedido = pc.id_pedido " +
                    "JOIN Componentes c ON pc.id_componente = c.id_componente " +
                    "GROUP BY cli.id_cliente";

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
                ps = conn.prepareStatement(UPDATE);

                int index = 1;

                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    ps.setString(index++, this.getNombre());
                } else {
                    ps.setString(index++, this.getNombre());
                }

                if (this.getCorreo() != null && !this.getCorreo().isEmpty()) {
                    ps.setString(index++, this.getCorreo());
                }

                if (this.getTelefono() != null && !this.getTelefono().isEmpty()) {
                    ps.setString(index++, this.getTelefono());
                }

                ps.setInt(index, this.getId_cliente());

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
                        this.id_cliente = -1;
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

    public Clientes getById(int id_cliente) {
        Clientes cliente = null;
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
                    cliente = new Clientes();
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setTelefono(rs.getString("telefono"));
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

        return cliente;
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

    public List<Clientes> getByName(String name) {
        List<Clientes> clientes = new ArrayList<>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTBYNAME);
                ps.setString(1, name);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Clientes cliente = new Clientes(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("telefono")
                    );
                    clientes.add(cliente);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return clientes;
    }

    public List<Map<String, Object>> getClientesConGastos() {
        List<Map<String, Object>> clientes = new ArrayList<>();
        try (Connection conn = getWorkbenchConnection();
             PreparedStatement ps = conn.prepareStatement(SELECTCLIENTESGASTOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> cliente = new HashMap<>();
                cliente.put("id_cliente", rs.getInt("id_cliente"));
                cliente.put("nombre", rs.getString("nombre"));
                cliente.put("total_gastado", rs.getBigDecimal("total_gastado"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
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