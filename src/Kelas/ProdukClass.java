package Kelas;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ProdukClass extends KoneksiClass {

    private int productId, productCategory, productPrice;
    private String productName, productDescription;

    private final Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;

    public ProdukClass() {
        cnVar = configDB();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ResultSet showProduct() {

        try {
            query = "SELECT product.productId, product.productName, category.categoryName, product.productDescription, product.productPrice FROM product JOIN category ON product.productCategory = category.categoryId";

            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }

    public void saveData() {

        try {
            query = "INSERT INTO product (productId, productName, productDescription, productPrice, productCategory) VALUES (?, ?, ?, ?, ?)";

            psVar = cnVar.prepareStatement(query);
            psVar.setInt(1, this.productId);
            psVar.setString(2, this.productName);
            psVar.setString(3, this.productDescription);
            psVar.setInt(4, this.productPrice);
            psVar.setInt(5, this.productCategory);

            psVar.executeUpdate();
            psVar.close();

            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public void updateData() {

        try {
            query = "UPDATE product SET productName = ?, productCategory = ?, productDescription = ?, productPrice = ? WHERE productId = ?";

            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.productName);
            psVar.setInt(2, this.productCategory);
            psVar.setString(3, this.productDescription);
            psVar.setInt(4, this.productPrice);
            psVar.setInt(5, this.productId);
            psVar.executeUpdate();
            psVar.close();

            JOptionPane.showMessageDialog(null, "Data berhasil diubah");

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public void deleteData() {
        try {
            query = "DELETE FROM product WHERE productId = ?";

            psVar = cnVar.prepareStatement(query);
            psVar.setInt(1, this.productId);
            psVar.executeUpdate();
            psVar.close();

            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public ResultSet autoId() {
        try {
            query = "SELECT productId AS ID FROM product ORDER BY productId DESC LIMIT 1";

            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }
}
