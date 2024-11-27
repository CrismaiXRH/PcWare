package DAO;

import entity.Categorias;
import model.MySqlConnection;

import javax.management.monitor.StringMonitor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriasDAO extends Categorias {
    public CategoriasDAO(int id_categoria, String nombre) {
        super(id_categoria, nombre);
    }

    public CategoriasDAO(Categorias a){
        this(a.getId_categoria(), a.getNombre());
    }

    public CategoriasDAO(int id){
        this.getId_categoria(id);
    }

    public void save() {
     if ()
    }

    public void getById(int id) {
        Connection connection = MySqlConnection.getConnection();
        if (connection != null) {
            PreparedStatement statement;
            try {
                statement = connection.prepareStatement("SELECT * FROM categorias WHERE id_categoria = ?");
                statement.setInt(1, id);
                if (statement.execute()) {
                    this.id_categoria = statement.getInt("id_categoria");
                    this.nombre = statement.getString("nombre");
                }
            }catch (SQLException e ){
                e.printStackTrace();
            }
        }
    }
}
