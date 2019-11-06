import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class AddProduct {
    private static Connection myConnection;

    public static void main(String[] args) {
        try {
            myConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bryghus;user=sa;password=Viggo-12345;");

            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Welcome to the interface for adding new products.");
            System.out.print("Product name: ");
            String productName = inLine.readLine();
            System.out.println("Write ? to get product groups");
            System.out.print("Product group: ");
            String productGroup = inLine.readLine();

            if (productGroup.equals("?")) {
                Statement stmt = myConnection.createStatement();
                ResultSet res = stmt.executeQuery("select productType from ProductGroup");

                String productGroupsPrint = "";

                while (res.next()) {
                    productGroupsPrint += res.getString(1) + ", ";
                }
                System.out.println(productGroupsPrint.substring(0, productGroupsPrint.length()-2));

                productGroup = inLine.readLine();
            }

            if (productGroup.trim().equals("")) {
                throw new RuntimeException("Product group cannot be an empty string");
            }
            else if (productName.trim().equals("")) {
                throw new RuntimeException("Product name cannot be an empty string");
            }

            String sql = "INSERT INTO Product VALUES (?,?)";// preparedStatement

            PreparedStatement prestmt = myConnection.prepareStatement(sql);
            prestmt.clearParameters();

            prestmt.setString(1, productName.trim());
            prestmt.setString(2, productGroup.trim());

            prestmt.executeUpdate();
            System.out.println("Done!");

            if (prestmt != null)
                prestmt.close();
            if (myConnection != null)
                myConnection.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                System.out.println("Product group does not exist");
            }
            else if (e.getErrorCode() == 0) {
                System.out.println("Something went wrong while trying to connect to the database, please check your connection");
            }
            else if (e.getErrorCode() == 8152) {
                System.out.println("Text is too long");
            }
            else {
                System.out.println("Code: " + e.getErrorCode());
            }
        } catch (RuntimeException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
