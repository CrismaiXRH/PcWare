package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Categorias;

public class CategoriasDAO extends Categorias {

    private final static String INSERT = "INSERT INTO Categorias (nombre) VALUES (?)";
    private final static String UPDATE = "UPDATE Categorias SET nombre=? WHERE id_categoria=?";
    private final static String DELETE = "DELETE FROM Categorias WHERE id_categoria=?";
    private final static String SELECTBYID = "SELECT id_categoria, nombre FROM Categorias WHERE id_categoria=?";
    private final static String SELECTALL = "SELECT id_categoria, nombre FROM Categorias";
    private static final String SELECTPRODUCTOSCAROS =
            "SELECT c.id_categoria, ca.nombre AS categoria, c.nombre AS producto_mas_caro, MAX(c.precio) AS precio " +
                    "FROM Componentes c " +
                    "JOIN Categorias ca ON c.id_categoria = ca.id_categoria " +
                    "GROUP BY c.id_categoria";

    public CategoriasDAO(int id_categoria, String nombre) {
        super(id_categoria, nombre);
    }

    public CategoriasDAO() {
        super();
    }

    public CategoriasDAO(Categorias categoria) {
        this(categoria.getId_categoria(), categoria.getNombre());
    }

    public CategoriasDAO(int id_categoria) {
        this.getById(id_categoria);
    }

    public void save() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT); // No se usa RETURN_GENERATED_KEYS
                ps.setString(1, this.getNombre());
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
                StringBuilder sql = new StringBuilder(UPDATE);
                boolean hasFields = false;

                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    hasFields = true;
                }

                if (!hasFields) {
                    System.out.println("No hay campos para actualizar.");
                    return;
                }

                ps = conn.prepareStatement(sql.toString());

                int index = 1;
                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    ps.setString(index++, this.getNombre());
                }
                ps.setInt(index, this.getId_categoria());

                ps.executeUpdate();
                System.out.println("Categoría actualizada correctamente.");
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
        if (id_categoria != -1) {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = getWorkbenchConnection();
                if (conn != null) {
                    ps = conn.prepareStatement(DELETE);
                    ps.setInt(1, this.id_categoria);
                    if (ps.executeUpdate() == 1) {
                        this.id_categoria = -1;
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

    public Categorias getById(int id_categoria) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Categorias categoria = null;

        try {
            conn = getWorkbenchConnection(); // Método para obtener la conexión a la base de datos
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYID);
                ps.setInt(1, id_categoria);
                rs = ps.executeQuery();

                if (rs.next()) {
                    categoria = new Categorias();
                    categoria.setId_categoria(rs.getInt("id_categoria"));
                    categoria.setNombre(rs.getString("nombre"));
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
        return categoria;
    }


    public static List<Categorias> getAll() {
        List<Categorias> result = new ArrayList<Categorias>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement( SELECTALL);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Categorias c = new Categorias(rs.getInt("id_categoria"), rs.getString("nombre"));
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

    public List<Map<String, Object>> getProductosCarosPorCategoria() {
        List<Map<String, Object>> productos = new ArrayList<>();
        try (Connection conn = getWorkbenchConnection();
             PreparedStatement ps = conn.prepareStatement(SELECTPRODUCTOSCAROS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> producto = new HashMap<>();
                producto.put("categoria", rs.getString("categoria"));
                producto.put("producto_mas_caro", rs.getString("producto_mas_caro"));
                producto.put("precio", rs.getBigDecimal("precio"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
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
