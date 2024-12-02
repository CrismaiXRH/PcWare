package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Fabricantes;

public class FabricantesDAO extends Fabricantes {

    private final static String INSERT = "INSERT INTO fabricantes (nombre, pais, telefono) VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE fabricantes SET nombre = ?, pais = ?, telefono = ? WHERE id_fabricante = ?";
    private final static String DELETE = "DELETE FROM fabricantes WHERE id_fabricante=?";
    private final static String SELECTALL = "SELECT * FROM fabricantes";
    private final static String SELECTBYID = "SELECT * FROM fabricantes WHERE id_fabricante=?";
    private final static String SELECTBYNAME = "SELECT * FROM Fabricantes WHERE LOWER(nombre) LIKE LOWER(?)";
    private final static String SELECTBYPAIS = "SELECT * FROM fabricantes WHERE LOWER(pais) LIKE LOWER(?)";
    private static final String SELECTFABRICANTESCANTIDAD =
            "SELECT f.nombre AS fabricante, COUNT(c.id_componente) AS cantidad_componentes " +
                    "FROM Fabricantes f " +
                    "JOIN Componentes c ON f.id_fabricante = c.id_fabricante " +
                    "GROUP BY f.id_fabricante";

    public FabricantesDAO(int id_fabricante, String nombre, String pais, String telefono) {
        super(id_fabricante, nombre, pais, telefono);
    }

    public FabricantesDAO() {
        super();
    }

    public FabricantesDAO(Fabricantes fabricantes) {
        this(fabricantes.getId_fabricante(), fabricantes.getNombre(), fabricantes.getPais(), fabricantes.getTelefono());
    }

    public FabricantesDAO(int id_fabricante) {
        this.getById(id_fabricante);
    }

    public void save() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getPais());
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

                if (this.nombre != null && !this.nombre.isEmpty()) {
                    ps.setString(index++, this.nombre);
                } else {
                    ps.setString(index++, null);
                }

                if (this.pais != null && !this.pais.isEmpty()) {
                    ps.setString(index++, this.pais);
                } else {
                    ps.setString(index++, null);
                }

                if (this.telefono != null && !this.telefono.isEmpty()) {
                    ps.setString(index++, this.telefono);
                } else {
                    ps.setString(index++, null);
                }

                ps.setInt(index, this.id_fabricante);

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
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setInt(1, this.getId_fabricante());
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

    public List<Fabricantes> getAll() {
        List<Fabricantes> fabricantes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTALL);
                rs = ps.executeQuery();
                while (rs.next()) {
                    fabricantes.add(new Fabricantes(rs.getInt("id_fabricante"), rs.getString("nombre"), rs.getString("pais"), rs.getString("telefono")));
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
        return fabricantes;
    }

    public Fabricantes getById(int id_fabricante) {
        Fabricantes fabricantes = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYID);
                ps.setInt(1, id_fabricante);
                rs = ps.executeQuery();
                if (rs.next()) {
                    fabricantes = new Fabricantes(rs.getInt("id_fabricante"), rs.getString("nombre"), rs.getString("pais"), rs.getString("telefono"));
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
        return fabricantes;
    }

    public List<Fabricantes> getByName(String nombre) {
        List<Fabricantes> fabricantes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYNAME);
                ps.setString(1, "%" + nombre + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    fabricantes.add(new Fabricantes(
                            rs.getInt("id_fabricante"),
                            rs.getString("nombre"),
                            rs.getString("pais"),
                            rs.getString("telefono")
                    ));
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
        return fabricantes;
    }


    public List<Fabricantes> getByPais(String pais) {
        List<Fabricantes> fabricantes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYPAIS);
                ps.setString(1, "%" + pais + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    fabricantes.add(new Fabricantes(
                            rs.getInt("id_fabricante"),
                            rs.getString("nombre"),
                            rs.getString("pais"),
                            rs.getString("telefono")
                    ));
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
        return fabricantes;
    }

    public List<Map<String, Object>> getFabricantesConCantidadComponentes() {
        List<Map<String, Object>> fabricantes = new ArrayList<>();
        try (Connection conn = getWorkbenchConnection();
             PreparedStatement ps = conn.prepareStatement(SELECTFABRICANTESCANTIDAD);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> fabricante = new HashMap<>();
                fabricante.put("fabricante", rs.getString("fabricante"));
                fabricante.put("cantidad_componentes", rs.getInt("cantidad_componentes"));
                fabricantes.add(fabricante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fabricantes;
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
