package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Categorias;

public class CategoriasDAO extends Categorias {

    private final static String INSERT = "INSERT INTO Categorias (nombre) VALUES (?)";
    private final static String UPDATE = "UPDATE Categorias SET nombre=? WHERE id_categoria=?";
    private final static String DELETE = "DELETE FROM Categorias WHERE id_categoria=?";
    private final static String SELECTBYID = "SELECT id_categoria, nombre FROM Categorias WHERE id_categoria=?";
    private final static String SELECTALL = "SELECT id_categoria, nombre FROM Categorias";

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
                // Construcción dinámica del SQL
                StringBuilder sql = new StringBuilder("UPDATE categorias SET ");
                boolean hasFields = false;

                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    sql.append("nombre = ?, ");
                    hasFields = true;
                }

                // Verificar si hay campos para actualizar
                if (!hasFields) {
                    System.out.println("No hay campos para actualizar.");
                    return;
                }

                // Quitar la última coma y agregar la cláusula WHERE
                sql.setLength(sql.length() - 2);
                sql.append(" WHERE id_categoria = ?");

                ps = conn.prepareStatement(sql.toString());

                // Asignar valores a los campos
                int index = 1;
                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    ps.setString(index++, this.getNombre());
                }
                ps.setInt(index, this.getId_categoria()); // Asignar el ID

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
        if (id_categoria != -1) {
            // DELETE
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = getWorkbenchConnection();
                if (conn != null) {
                    ps = conn.prepareStatement(DELETE);
                    ps.setInt(1, this.id_categoria);
                    if (ps.executeUpdate() == 1) {
                        this.id_categoria = -1; // Si se borra correctamente, marcamos como no existente
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

    public void getById(int id_categoria) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYID);
                ps.setInt(1, id_categoria);
                rs = ps.executeQuery();
                if (rs.next()) {
                    this.id_categoria = rs.getInt("id_categoria"); // Cambié "id" por "id_categoria"
                    this.nombre = rs.getString("nombre");
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

    public static List<Categorias> getAll() {
        List<Categorias> result = new ArrayList<Categorias>();
        Connection conn = getWorkbenchConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(SELECTALL);
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
