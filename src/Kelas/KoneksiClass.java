package Kelas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiClass {

    private Connection mysqlConfig;

    public Connection configDB() {
        try {
            String url  = "jdbc:mysql://localhost:3306/crudoop_202457201012";
            String user = "root";
            String pass = "";

            mysqlConfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error Koneksi : " + e.getMessage());
        }

        return mysqlConfig;
    }
}
