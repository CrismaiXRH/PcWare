package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.PedidosComponentes;

public class PedidosComponentesDAO extends PedidosComponentes {

    private final static String INSERT = "INSERT INTO pedidos_componentes (id_pedido, id_componente, cantidad) VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE pedidos_componentes SET id_pedido=?, id_componente=?, cantidad=? WHERE id_pedidos_componentes=?";
    private final static String DELETE = "DELETE FROM pedidos_componentes WHERE id_pedidos_componentes=?";
    private final static String SELECTALL = "SELECT * FROM pedidos_componentes";
    private final static String SELECTBYID = "SELECT * FROM pedidos_componentes WHERE id_pedidos_componentes=?";
    private final static String SELECTBYCOMPONENTE = "SELECT * FROM pedidos_componentes WHERE id_componente=?";
    private final static String SELECTBYCANTIDAD = "SELECT * FROM pedidos_componentes WHERE cantidad=?";

    public PedidosComponentesDAO(int id_pedido, int id_componente, int cantidad) {
        super(id_pedido, id_componente, cantidad);
    }

    public PedidosComponentesDAO() {
        super();
    }

    public PedidosComponentesDAO(PedidosComponentes pc) {
        this(pc.getId_pedido(), pc.getId_componente(), pc.getCantidad());
    }

    public void save() {
        Connection conn = null;
        PreparedStatement ps = null;
        if (exists()) {
            // UPDATE
            try {
                conn = getWorkbenchConnection();
                if (conn != null) {
                    ps = conn.prepareStatement(UPDATE);
                    ps.setInt(1, this.cantidad);
                    ps.setInt(2, this.id_pedido);
                    ps.setInt(3, this.id_componente);
                    int rowsAffected = ps.executeUpdate();
                    System.out.println("Filas afectadas por el UPDATE: " + rowsAffected);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // INSERT
            try {
                conn = getWorkbenchConnection();
                if (conn != null) {
                    ps = conn.prepareStatement(INSERT);
                    ps.setInt(1, this.id_pedido);
                    ps.setInt(2, this.id_componente);
                    ps.setInt(3, this.cantidad);
                    int rowsAffected = ps.executeUpdate();
                    System.out.println("Filas afectadas por el INSERT: " + rowsAffected);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void remove() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setInt(1, this.id_pedido);
                ps.setInt(1, this.id_componente);
                int rowsAffected = ps.executeUpdate();
                System.out.println("Filas afectadas por el DELETE: " + rowsAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getById(int id_pedido, int id_componente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYID);
                ps.setInt(1, id_pedido);
                ps.setInt(1, id_componente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    this.id_pedido = rs.getInt("id_pedido");
                    this.id_componente = rs.getInt("id_componente");
                    this.cantidad = rs.getInt("cantidad");
                }
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

    public void getByComponente(int id_componente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYCOMPONENTE);
                ps.setInt(1, id_componente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    this.id_pedido = rs.getInt("id_pedido");
                    this.id_componente = rs.getInt("id_componente");
                    this.cantidad = rs.getInt("cantidad");
                }
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

    public void getByCantidad(int cantidad) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYCANTIDAD);
                ps.setInt(1, cantidad);
                rs = ps.executeQuery();
                if (rs.next()) {
                    this.id_pedido = rs.getInt("id_pedido");
                    this.id_componente = rs.getInt("id_componente");
                    this.cantidad = rs.getInt("cantidad");
                }
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

    public static List<PedidosComponentes> getAll() {
        List<PedidosComponentes> result = new ArrayList<>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTALL);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PedidosComponentes pc = new PedidosComponentes(
                            rs.getInt("id_pedido"),
                            rs.getInt("id_componente"),
                            rs.getInt("cantidad")
                    );
                    result.add(pc);
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
        return result;
    }

    private boolean exists() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYID);
                ps.setInt(1, this.id_pedido);
                ps.setInt(1, this.id_componente);
                rs = ps.executeQuery();
                exists = rs.next();
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
        return exists;
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
