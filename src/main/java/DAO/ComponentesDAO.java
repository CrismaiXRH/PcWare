package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Componentes;

public class ComponentesDAO extends Componentes {
    private final static String INSERT = "INSERT INTO componentes (nombre, descripcion, precio, id_categoria, id_fabricante) VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE componentes SET nombre=?, descripcion=?, precio=?, id_categoria=?, id_fabricante=? WHERE id_componente=?";
    private final static String DELETE = "DELETE FROM componentes WHERE id_componente=?";
    private final static String SELECTALL = "SELECT * FROM componentes";
    private final static String SELECTBYID = "SELECT * FROM componentes WHERE id_componente=?";
    private final static String SELECTBYNAME = "SELECT * FROM componentes WHERE LOWER(nombre) LIKE LOWER(?)";
    private final static String SELECTBYCATEGORY = "SELECT * FROM componentes WHERE id_categoria=?";
    private final static String SELECTBYFABRICANTE = "SELECT * FROM componentes WHERE id_fabricante=?";
    private final static String SELECTBYPRICE = "SELECT * FROM componentes WHERE precio=?";
    private final static String SELECTBYPRICEUPPER = "SELECT * FROM componentes WHERE precio>?";
    private final static String SELECTBYPRICELOWER = "SELECT * FROM componentes WHERE precio<?";
    private static final String SELECTNOCOMPRADOS =
            "SELECT c.id_componente, c.nombre " +
                    "FROM Componentes c " +
                    "LEFT JOIN Pedido_Componentes pc ON c.id_componente = pc.id_componente " +
                    "WHERE pc.id_componente IS NULL";

    private static final String SELECTVENDIDOSMASXVECES =
            "SELECT c.id_componente, c.nombre, SUM(pc.cantidad) AS cantidad_vendida " +
                    "FROM Componentes c " +
                    "JOIN Pedido_Componentes pc ON c.id_componente = pc.id_componente " +
                    "GROUP BY c.id_componente " +
                    "HAVING SUM(pc.cantidad) > ?";


    public ComponentesDAO(int id_componente, String nombre, String descripcion, int precio, int id_categoria, int id_fabricante) {
        super(id_componente, nombre, descripcion, precio, id_categoria, id_fabricante);
    }

    public ComponentesDAO() {
        super();
    }

    public ComponentesDAO(Componentes componentes) {
        this(componentes.getId_componente(), componentes.getNombre(), componentes.getDescripcion(), componentes.getPrecio(), componentes.getId_categoria(), componentes.getId_fabricante());
    }

    public ComponentesDAO(int id_componente) {
        this.getById(id_componente);
    }
    public void save() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getDescripcion());
                ps.setInt(3, this.getPrecio());
                ps.setInt(4, this.getId_categoria());
                ps.setInt(5, this.getId_fabricante());
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

                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    ps.setString(index++, this.getNombre());
                }

                if (this.getDescripcion() != null && !this.getDescripcion().isEmpty()) {
                    ps.setString(index++, this.getDescripcion());
                }

                if (this.getPrecio() != 0) {
                    ps.setInt(index++, this.getPrecio());
                }

                if (this.getId_categoria() != 0) {
                    ps.setInt(index++, this.getId_categoria());
                }

                if (this.getId_fabricante() != 0) {
                    ps.setInt(index++, this.getId_fabricante());
                }

                ps.setInt(index, this.getId_componente());

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
        if (id_componente != -1) {
            // DELETE
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = getWorkbenchConnection();
                if (conn != null) {
                    ps = conn.prepareStatement(DELETE);
                    ps.setInt(1, this.id_componente);
                    if (ps.executeUpdate() == 1) {
                        this.id_componente = -1;
                    }
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
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

    public Componentes getById(int id_componente) {
        Componentes componente = null;
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTBYID);
                ps.setInt(1, id_componente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    componente = new Componentes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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
        return componente;
    }

    public Componentes getByName(String name) {
        Componentes componente = null;
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTBYNAME);
                ps.setString(1, name);
                rs = ps.executeQuery();
                if (rs.next()) {
                    componente = new Componentes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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
        return componente;
    }



    public List<Componentes> getByCategoria(int id_categoria) {
        List<Componentes> result = new ArrayList<Componentes>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTBYCATEGORY);
                ps.setInt(1, id_categoria);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Componentes c = new Componentes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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

    public List<Componentes> getByFabricante(int id_fabricante) {
        List<Componentes> result = new ArrayList<Componentes>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTBYFABRICANTE);
                ps.setInt(1, id_fabricante);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Componentes c = new Componentes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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

    public List<Componentes> getByPrice(int precio) {
        List<Componentes> result = new ArrayList<Componentes>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTBYPRICE);
                ps.setInt(1, precio);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Componentes c = new Componentes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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



    public List<Componentes> getAll() {
        List<Componentes> result = new ArrayList<Componentes>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTALL);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Componentes c = new Componentes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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

    public List<Componentes> getByPriceUpper(int precio) {
        List<Componentes> componentes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYPRICEUPPER);
                ps.setDouble(1, precio);
                rs = ps.executeQuery();

                while (rs.next()) {
                    componentes.add(new Componentes(
                            rs.getInt("id_componente"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getInt("precio"),
                            rs.getInt("id_categoria"),
                            rs.getInt("id_fabricante")
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

        return componentes;
    }

    public List<Componentes> getByPriceLower(int precio) {
        List<Componentes> componentes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYPRICELOWER);
                ps.setDouble(1, precio);
                rs = ps.executeQuery();

                while (rs.next()) {
                    componentes.add(new Componentes(
                            rs.getInt("id_componente"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getInt("precio"),
                            rs.getInt("id_categoria"),
                            rs.getInt("id_fabricante")
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

        return componentes;
    }

    public List<Componentes> getComponentesNoComprados() {
        List<Componentes> componentes = new ArrayList<>();
        try (Connection conn = getWorkbenchConnection();
             PreparedStatement ps = conn.prepareStatement(SELECTNOCOMPRADOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Componentes componente = new Componentes();
                componente.setId_componente(rs.getInt("id_componente"));
                componente.setNombre(rs.getString("nombre"));
                componentes.add(componente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return componentes;
    }

    public List<Map<String, Object>> getComponentesVendidosMasXVeces(int cantidadMinima) {
        List<Map<String, Object>> componentes = new ArrayList<>();
        try (Connection conn = getWorkbenchConnection();
             PreparedStatement ps = conn.prepareStatement(SELECTVENDIDOSMASXVECES)) {
            ps.setInt(1, cantidadMinima);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> componente = new HashMap<>();
                    componente.put("id_componente", rs.getInt("id_componente"));
                    componente.put("nombre", rs.getString("nombre"));
                    componente.put("cantidad_vendida", rs.getInt("cantidad_vendida"));
                    componentes.add(componente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return componentes;
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
