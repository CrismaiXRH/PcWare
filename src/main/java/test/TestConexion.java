package test;

public class TestConexion {
    public static void main(String[] args) {
        model.MySqlConnection.getConnection();

        if (model.MySqlConnection.getConnection() == null) {
            System.out.println("No se pudo conectar a la base de datos.");
        }else {
            System.out.println("Conexion exitosa");
        }
        model.MySqlConnection.closeConnection();
    }
}
