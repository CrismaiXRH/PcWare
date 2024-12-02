package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import entity.Pedidos;

public class PedidosDAO extends Pedidos {

    private static final String INSERT = "INSERT INTO pedidos (fecha, id_cliente) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE pedidos SET id_pedido=?, fecha=?, id_cliente=? WHERE id_pedido=?";
    private static final String DELETE = "DELETE FROM pedidos WHERE id_pedido=?";
    private static final String SELECTALL = "SELECT * FROM pedidos";
    private static final String SELECTBYID = "SELECT * FROM pedidos WHERE id_pedido=?";
    private static final String SELECTBYCLIENTE = "SELECT * FROM pedidos WHERE id_cliente=?";
    private static final String SELECTBYFECHA = "SELECT * FROM pedidos WHERE fecha=?";

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
                // Usamos la sentencia SQL previamente definida como constante (INSERT)
                ps = conn.prepareStatement(INSERT);

                // Asignamos los valores de los parámetros
                ps.setString(1, this.getFecha());  // La fecha del pedido
                ps.setInt(2, this.getId_cliente()); // El ID del cliente que realizó el pedido

                // Ejecutamos la sentencia SQL
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
                // Construcción dinámica del SQL
                StringBuilder sql = new StringBuilder("UPDATE pedidos SET ");
                boolean hasFields = false;

                if (this.getFecha() != null && !this.getFecha().isEmpty()) {
                    sql.append("fecha = ?, ");
                    hasFields = true;
                }
                if (this.getId_cliente() > 0) { // Si se proporciona un ID válido
                    sql.append("id_cliente = ?, ");
                    hasFields = true;
                }

                // Verificar si hay campos para actualizar
                if (!hasFields) {
                    System.out.println("No hay campos para actualizar.");
                    return;
                }

                // Quitar la última coma y agregar la cláusula WHERE
                sql.setLength(sql.length() - 2);
                sql.append(" WHERE id_pedido = ?");

                ps = conn.prepareStatement(sql.toString());

                // Asignar valores a los campos
                int index = 1;
                if (this.getFecha() != null && !this.getFecha().isEmpty()) {
                    ps.setString(index++, this.getFecha());
                }
                if (this.getId_cliente() > 0) {
                    ps.setInt(index++, this.getId_cliente());
                }
                ps.setInt(index, this.getId_pedido()); // Asignar el ID del pedido

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

    public void delete() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getWorkbenchConnection();
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, id_pedido);
            ps.executeUpdate();
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

    public void getById(int id_pedido) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getWorkbenchConnection();
            ps = con.prepareStatement(SELECTBYID);
            ps.setInt(1, id_pedido);
            rs = ps.executeQuery();
            if (rs.next()) {
                this.id_pedido = rs.getInt("id_pedido");
                this.fecha = rs.getString("fecha");
                this.id_cliente = rs.getInt("id_cliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
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

    public List<Pedidos> getByFecha(Calendar fecha) {
        List<Pedidos> pedidos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getWorkbenchConnection();
            ps = con.prepareStatement(SELECTBYFECHA);
            ps.setTimestamp(1, new Timestamp(fecha.getTimeInMillis()));
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
