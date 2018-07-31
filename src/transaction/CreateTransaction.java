package transaction;

import auth.POSUser;
import items.POSItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateTransaction {
    private ArrayList<POSItem> itemIdList;
    private POSUser transactionUser;
    private LocalDateTime dateTransaction;
    private double totalPrice;
    private double subTotalPrice;
    private double taxAmount;

    private Connection mariadb_default;

    public CreateTransaction(ArrayList<POSItem> itemIdList, POSUser transactionUser, LocalDateTime dateTransaction, double totalPrice, double subTotalPrice, double taxAmount){
        this.itemIdList = itemIdList;
        this.transactionUser = transactionUser;
        this.dateTransaction = dateTransaction;
        this.totalPrice = totalPrice;
        this.subTotalPrice = subTotalPrice;
        this.taxAmount = taxAmount;
    }

    public void writeTransaction() throws SQLException {
        String itemIdList = "";
        int onItem = 0;
        for (POSItem i: this.itemIdList){
            if (onItem == this.itemIdList.size() - 1){
                itemIdList += i.getItemId();
            }
            else {
                itemIdList += i.getItemId() + ";";
            }
        }

        //Step 2: write the user_id to an int
        int transactionUserId = this.transactionUser.getEmployeeId();

        //Step 3: write the date of transaction to a String
        String dateOfTransaction = this.dateTransaction.toString();

        //Step 4: write the price total to a double
        double transactionTotalPrice = this.totalPrice;

        //Step 5: write the price subtotal to a double
        double transactionSubTotalPrice = this.subTotalPrice;

        //Step 6: write tax amount to a double
        double taxAmount = this.taxAmount;

        //Step 7: generate query
        String query = "INSERT INTO pos_transactions VALUES ('" + itemIdList + "', " + transactionUserId + ", '" + dateOfTransaction + "', " + transactionTotalPrice + ", " + transactionSubTotalPrice
                        + ", " + taxAmount + ")";

        //Step 8: write query to db
        Statement execQuery = mariadb_default.createStatement();
        execQuery.executeQuery(query);

    }
}
