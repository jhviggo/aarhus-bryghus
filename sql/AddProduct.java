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

            Statement stmt = myConnection.createStatement();
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Would you like to add a product (add)");
            System.out.println("or get total sales on a date(date)");

            String choice = inLine.readLine();

            if (choice.trim().equalsIgnoreCase("add")) {
                /**
                 * Opgave 6a
                 */
                System.out.println("Welcome to the interface for adding new products.");
                System.out.print("Product name: ");
                String productName = inLine.readLine();
                System.out.println("Write ? to get product groups");
                System.out.print("Product group: ");
                String productGroup = inLine.readLine();

                if (productGroup.equals("?")) {
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

                String sql = "INSERT INTO Product VALUES (?,?)";

                PreparedStatement prestmt = myConnection.prepareStatement(sql);
                prestmt.clearParameters();

                prestmt.setString(1, productName.trim());
                prestmt.setString(2, productGroup.trim());

                prestmt.executeUpdate();
                System.out.println("Done!");

                prestmt.close();
            }
            else if (choice.trim().equalsIgnoreCase("date")) {
                System.out.println("(date 2019-11-4 and product 1 works as an example)");
                System.out.println("Date (yyyy-mm-dd): ");
                String date = inLine.readLine();

                if (date.trim().equals("")) {
                    throw new RuntimeException("Date cannot be an empty string");
                }

                System.out.println("Product id: ");
                String productId = inLine.readLine();

                if (productId.trim().equals("")) {
                    throw new RuntimeException("Product id cannot be an empty string");
                }

                String query = "SELECT SUM(pip.price * ol.amount) FROM ProductInPriceList pip, OrderLine ol, Orders o WHERE ol.orderId = o.id AND ol.product = pip.product AND pip.priceList = ol.priceList AND DATEDIFF(dd, o.startTimeStamp,'" + date + "') = 0 AND pip.product = " + productId;
                ResultSet res = stmt.executeQuery(query);
                System.out.print("Total income for this product on " + date + ": ");
                while (res.next()) {
                    System.out.println(res.getString(1) + "kr");
                }

            }

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
            else if (e.getErrorCode() == 102) {
                System.out.println("Syntax error! Stop touching our pretty code :(");
            }
            else {
                System.out.println("Code: " + e.getErrorCode());
                System.out.println(e.getMessage());
            }
        } catch (RuntimeException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
