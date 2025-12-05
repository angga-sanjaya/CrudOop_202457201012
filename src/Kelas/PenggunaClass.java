package Kelas;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class PenggunaClass extends KoneksiClass {

    private String userName, userEmail, userPassword, userFullName;
    private int userStatus;

    private final Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;

    public PenggunaClass() {
        cnVar = super.configDB();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public ResultSet loadData() {

        try {
            query = "SELECT * FROM user";

            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }

    public void saveData() {

        try {
            query = "INSERT INTO user VALUES (?, ?, MD5(?), ?, ?)";

            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.userName);
            psVar.setString(2, this.userEmail);
            psVar.setString(3, this.userPassword);
            psVar.setString(4, this.userFullName);
            psVar.setInt(5, this.userStatus);
            psVar.executeUpdate();
            psVar.close();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public void updateData() {

        if (this.userPassword.equals("")) {

            try {

                query = "UPDATE user SET userEmail = ?, userFullName = ?, userStatus = ? WHERE userName = ?";

                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.userEmail);
                psVar.setString(2, this.userFullName);
                psVar.setInt(3, this.userStatus);
                psVar.setString(4, this.userName);
                psVar.executeUpdate();
                psVar.close();

                JOptionPane.showMessageDialog(null, "Data berhasil diubah");

            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
            }

        } else {

            try {

                query = "UPDATE user SET userEmail = ?, userPassword = MD5(?), userFullName = ?, userStatus = ? WHERE userName = ?";

                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.userEmail);
                psVar.setString(2, this.userPassword);
                psVar.setString(3, this.userFullName);
                psVar.setInt(4, this.userStatus);
                psVar.setString(5, this.userName);
                psVar.executeUpdate();
                psVar.close();

                JOptionPane.showMessageDialog(null, "Data berhasil diubah");

            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
            }
        }

    }

    public void deleteData() {

        try {
            query = "DELETE FROM user WHERE userName = ?";

            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.userName);
            psVar.executeUpdate();
            psVar.close();

            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public void loginApp() {
        try {
            query = "SELECT * FROM user WHERE userName = ? AND userPassword = MD5(?)";

            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.userName);
            psVar.setString(2, this.userPassword);
            rsVar = psVar.executeQuery();

            if (rsVar.next()) {
                SesiClass.setUserName(rsVar.getString("userName"));
                SesiClass.setNama(rsVar.getString("userFullName"));
                SesiClass.setEmail(rsVar.getString("userEmail"));
                SesiClass.setStatus("Active");

            } else {
                SesiClass.setStatus("Non-Active");
                JOptionPane.showMessageDialog(null, "Username atau Password salah, silahkan coba lagi");
            }

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public void logoutApp() {
        SesiClass.setUserName("");
        SesiClass.setEmail("");
        SesiClass.setNama("");
        SesiClass.setStatus("");
    }

    public void setPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
