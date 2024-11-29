package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import entity.Pedidos;

public class PedidosDAO extends Pedidos {

    private static final String INSERT = "INSERT INTO pedidos (id_pedido, fecha, id_cliente) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE pedidos SET id_pedido=?, fecha=?, id_cliente=? WHERE id_pedido=?";
    private static final String DELETE = "DELETE FROM pedidos WHERE id_pedido=?";
    private static final String SELECTALL = "SELECT * FROM pedidos";
    private static final String SELECTBYID = "SELECT * FROM pedidos WHERE id_pedido=?";
    private static final String SELECTBYCLIENTE = "SELECT * FROM pedidos WHERE id_cliente=?";
    private static final String SELECTBYFECHA = "SELECT * FROM pedidos WHERE fecha=?";

    public PedidosDAO(int id_pedido, Calendar fecha, int id_cliente) {
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
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getWorkbenchConnection();
            ps = con.prepareStatement(INSERT);
            ps.setInt(1, id_pedido);
            ps.setTimestamp(2, new Timestamp(fecha.getTimeInMillis()));
            ps.setInt(3, id_cliente);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, null);
        }
    }

    public void update() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getWorkbenchConnection();
            ps = con.prepareStatement(UPDATE);
            ps.setInt(1, id_pedido);
            ps.setTimestamp(2, new Timestamp(fecha.getTimeInMillis()));
            ps.setInt(3, id_cliente);
            ps.setInt(4, id_pedido);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, null);
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
                        convertTimestampToCalendar(rs.getTimestamp("fecha")),
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
                this.fecha = convertTimestampToCalendar(rs.getTimestamp("fecha"));
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
                        convertTimestampToCalendar(rs.getTimestamp("fecha")),
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
                        convertTimestampToCalendar(rs.getTimestamp("fecha")),
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

    private Calendar convertTimestampToCalendar(Timestamp timestamp) {
        if (timestamp == null) return null;
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timestamp.getTime());
        return calendar;
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
