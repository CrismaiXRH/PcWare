package DAO;

import java.sql.*;
import java.util.*;

import entity.Pedidos;

public class PedidosDAO extends Pedidos {

    private static final String INSERT = "INSERT INTO pedidos (fecha, id_cliente) VALUES (?, ?)";
    private final static String UPDATE = "UPDATE pedidos SET fecha = ?, id_cliente = ? WHERE id_pedido = ?";
    private static final String DELETE = "DELETE FROM pedidos WHERE id_pedido=?";
    private static final String SELECTALL = "SELECT * FROM pedidos";
    private static final String SELECTBYID = "SELECT * FROM pedidos WHERE id_pedido=?";
    private static final String SELECTBYCLIENTE = "SELECT * FROM pedidos WHERE id_cliente=?";
    private static final String SELECTBYFECHA = "SELECT * FROM pedidos WHERE fecha=?";
    private static final String SELECTBYCLIENTECOMPLEJO =
            "SELECT p.id_pedido, p.fecha, c.nombre AS componente, pc.cantidad, c.precio, (pc.cantidad * c.precio) AS total " +
                    "FROM Pedidos p " +
                    "JOIN Pedido_Componentes pc ON p.id_pedido = pc.id_pedido " +
                    "JOIN Componentes c ON pc.id_componente = c.id_componente " +
                    "JOIN Clientes cli ON p.id_cliente = cli.id_cliente " +
                    "WHERE LOWER(cli.nombre) = LOWER(?)";


    public PedidosDAO(int id_pedido, String fecha, int id_cliente) {
        super(id_pedido, fecha, id_cliente);
    }

    public PedidosDAO() {
        super();
    }

    public PedidosDAO(Pedidos pedido) {
        this(pedido.getId_pedido(), pedido.getFecha(), pedido.getId_cliente());
    }

    public PedidosDAO(int id_pedido) {
        this.getById(id_pedido);
    }

    public void save() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT);

                ps.setString(1, this.getFecha());  // La fecha del pedido
                ps.setInt(2, this.getId_cliente()); // El ID del cliente que realizó el pedido

                int rowsAffected = ps.executeUpdate();
                System.out.println("Filas afectadas por el INSERT: " + rowsAffected);
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

                if (this.getFecha() != null && !this.getFecha().isEmpty()) {
                    ps.setString(index++, this.getFecha());
                } else {
                    ps.setNull(index++, java.sql.Types.VARCHAR);
                }

                if (this.getId_cliente() > 0) {
                    ps.setInt(index++, this.getId_cliente());
                } else {
                    ps.setNull(index++, java.sql.Types.INTEGER);
                }

                ps.setInt(index, this.getId_pedido());

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



    public void delete() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getWorkbenchConnection();

            if (con != null) {
                ps = con.prepareStatement(DELETE);

                ps.setInt(1, id_pedido);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("El registro con id_pedido " + id_pedido + " ha sido eliminado.");
                } else {
                    System.out.println("No se encontró ningún registro con el id_pedido " + id_pedido);
                }
            } else {
                System.out.println("No se pudo establecer la conexión con la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, null);
        }
    }

    public List<Pedidos> getAll() {
        List<Pedidos> pedidos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getWorkbenchConnection();
            ps = con.prepareStatement(SELECTALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                pedidos.add(new Pedidos(
                        rs.getInt("id_pedido"),
                        rs.getString("fecha"),
                        rs.getInt("id_cliente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
        return pedidos;
    }

    public Pedidos getById(int id_pedido) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pedidos pedido = null;
        try {
            con = getWorkbenchConnection();
            if (con != null) {
                ps = con.prepareStatement(SELECTBYID);
                ps.setInt(1, id_pedido);
                rs = ps.executeQuery();
                if (rs.next()) {
                    pedido = new Pedidos(); // Instanciar el objeto Pedido
                    pedido.setId_pedido(rs.getInt("id_pedido"));
                    pedido.setFecha(rs.getString("fecha"));
                    pedido.setId_cliente(rs.getInt("id_cliente"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
        return pedido;
    }


    public List<Pedidos> getByCliente(int id_cliente) {
        List<Pedidos> pedidos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getWorkbenchConnection();
            ps = con.prepareStatement(SELECTBYCLIENTE);
            ps.setInt(1, id_cliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                pedidos.add(new Pedidos(
                        rs.getInt("id_pedido"),
                        rs.getString("fecha"),
                        rs.getInt("id_cliente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
        return pedidos;
    }

    public List<Pedidos> getByFecha(String fecha) {
        List<Pedidos> pedidos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getWorkbenchConnection();
            ps = con.prepareStatement(SELECTBYFECHA);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                pedidos.add(new Pedidos(
                        rs.getInt("id_pedido"),
                        rs.getString("fecha"),
                        rs.getInt("id_cliente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
        return pedidos;
    }

    public List<Map<String, Object>> getPedidosPorCliente(String nombreCliente) {
        List<Map<String, Object>> pedidos = new ArrayList<>();
        try (Connection conn = getWorkbenchConnection();
             PreparedStatement ps = conn.prepareStatement(SELECTBYCLIENTECOMPLEJO)) {
            ps.setString(1, nombreCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> pedido = new HashMap<>();
                    pedido.put("id_pedido", rs.getInt("id_pedido"));
                    pedido.put("fecha", rs.getString("fecha"));
                    pedido.put("componente", rs.getString("componente"));
                    pedido.put("cantidad", rs.getInt("cantidad"));
                    pedido.put("precio", rs.getBigDecimal("precio"));
                    pedido.put("total", rs.getBigDecimal("total"));
                    pedidos.add(pedido);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    private static Connection getWorkbenchConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/PcWare", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void closeResources(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
