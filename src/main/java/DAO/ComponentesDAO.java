package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Componentes;

public class ComponentesDAO extends Componentes {
    private final static String INSERT = "INSERT INTO componentes (nombre, descripcion, precio, id_categoria, id_fabricante) VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE componentes SET nombre=?, descripcion=?, precio=?, id_categoria=?, id_fabricante=? WHERE id_componente=?";
    private final static String DELETE = "DELETE FROM componentes WHERE id_componente=?";
    private final static String SELECTALL = "SELECT * FROM componentes";
    private final static String SELECTBYID = "SELECT * FROM componentes WHERE id_componente=?";
    private final static String SELECTBYNAME = "SELECT * FROM componentes WHERE nombre=?";
    private final static String SELECTBYCATEGORY = "SELECT * FROM componentes WHERE id_categoria=?";
    private final static String SELECTBYFABRICANTE = "SELECT * FROM componentes WHERE id_fabricante=?";
    private final static String SELECTBYPRICE = "SELECT * FROM componentes WHERE precio=?";

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
                // Preparamos el INSERT para la tabla 'componentes'
                ps = conn.prepareStatement(INSERT);  // Asegúrate de tener la sentencia SQL correcta aquí
                ps.setString(1, this.getNombre());   // El nombre del componente
                ps.setString(2, this.getDescripcion()); // La descripción del componente
                ps.setInt(3, this.getPrecio());      // El precio del componente
                ps.setInt(4, this.getId_categoria()); // La categoría del componente
                ps.setInt(5, this.getId_fabricante()); // El fabricante del componente
                int rowsAffected = ps.executeUpdate(); // Ejecuta la sentencia SQL
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
                StringBuilder sql = new StringBuilder("UPDATE componentes SET ");
                boolean hasFields = false;

                if (this.getNombre() != null && !this.getNombre().isEmpty()) {
                    sql.append("nombre = ?, ");
                    hasFields = true;
                }
                if (this.getDescripcion() != null && !this.getDescripcion().isEmpty()) {
                    sql.append("descripcion = ?, ");
                    hasFields = true;
                }
                if (this.getPrecio() != 0) {
                    sql.append("precio = ?, ");
                    hasFields = true;
                }
                if (this.getId_categoria() != 0) {
                    sql.append("id_categoria = ?, ");
                    hasFields = true;
                }
                if (this.getId_fabricante() != 0) {
                    sql.append("id_fabricante = ?, ");
                    hasFields = true;
                }

                // Verificar si hay campos para actualizar
                if (!hasFields) {
                    System.out.println("No hay campos para actualizar.");
                    return;
                }

                // Quitar la última coma y agregar la cláusula WHERE
                sql.setLength(sql.length() - 2);
                sql.append(" WHERE id_componente = ?");

                ps = conn.prepareStatement(sql.toString());

                // Asignar valores a los campos
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
                ps.setInt(index, this.getId_componente()); // Asignar el ID

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
                        this.id_componente = -1; // Si se borra correctamente, marcamos como no existente
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

    public void getById(int id_componente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYID);
                ps.setInt(1, id_componente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    this.id_componente = rs.getInt(1);
                    this.nombre = rs.getString(2);
                    this.descripcion = rs.getString(3);
                    this.precio = (int) rs.getDouble(4);
                    this.id_categoria = rs.getInt(5);
                    this.id_fabricante = rs.getInt(6);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
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

    public void getByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYNAME);
                ps.setString(1, name);
                rs = ps.executeQuery();
                if (rs.next()) {
                    this.id_componente = rs.getInt(1);
                    this.nombre = rs.getString(2);
                    this.descripcion = rs.getString(3);
                    this.precio = (int) rs.getDouble(4);
                    this.id_categoria = rs.getInt(5);
                    this.id_fabricante = rs.getInt(6);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
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



    public static List<Componentes> getAll() {
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
