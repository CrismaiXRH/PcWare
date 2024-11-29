package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Fabricantes;

public class FabricantesDAO extends Fabricantes {

    private final static String INSERT = "INSERT INTO fabricantes (nombre, pais, telefono) VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE fabricantes SET nombre=?, pais=?, telefono=? WHERE id_fabricante=?";
    private final static String DELETE = "DELETE FROM fabricantes WHERE id_fabricante=?";
    private final static String SELECTALL = "SELECT * FROM fabricantes";
    private final static String SELECTBYID = "SELECT * FROM fabricantes WHERE id_fabricante=?";
    private final static String SELECTBYNAME = "SELECT * FROM fabricantes WHERE nombre=?";
    private final static String SELECTBYPAIS = "SELECT * FROM fabricantes WHERE pais=?";

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
        if (id_fabricante == -1){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = getWorkbenchConnection();
                if (conn != null) {
                    ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, this.getNombre());
                    ps.setString(2, this.getPais());
                    ps.setString(3, this.getTelefono());
                    if (ps.executeUpdate() == 1) {
                        rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            this.setId_fabricante(rs.getInt(1));
                        }
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
        }else {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = getWorkbenchConnection();
                if (conn != null) {
                    ps = conn.prepareStatement(UPDATE);
                    ps.setString(1, this.getNombre());
                    ps.setString(2, this.getPais());
                    ps.setString(3, this.getTelefono());
                    ps.setInt(4, this.getId_fabricante());
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

    public Fabricantes getByName(String nombre) {
        Fabricantes fabricantes = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYNAME);
                ps.setString(1, nombre);
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

    public Fabricantes getByPais(String pais) {
        Fabricantes fabricantes = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getWorkbenchConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SELECTBYPAIS);
                ps.setString(1, pais);
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
