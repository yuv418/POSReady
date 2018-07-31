package register;

import auth.POSUser;
import config.POSSalesConfig;
import items.POSItem;
import transaction.CreateTransaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class RegisterSession {
     private POSUser sale_runner;
     private LocalDateTime dateOfExecution;
     private double price_subtotal;
     private double price_total;
     private double tax_amnt;
     private double salestax;
     private Connection mariadb_default;
     private ArrayList<POSItem> itemList;

     public RegisterSession(POSUser sale_runner){
          /*
          *Start RegisterSession with a POSUser
          *@param User to start sale
          *
           */
        this.sale_runner = sale_runner;
        this.dateOfExecution = LocalDateTime.now();
        this.salestax = POSSalesConfig.getSalesTax();

     }

     public void addItem(POSItem item){
         /*
          *Start RegisterSession with a POSUser
          *@param Item to add to list of items
          *
          */
          itemList.add(item);
          calculateSubTotal();
          calculateTotal();
     }

     public void endSession() throws SQLException{
         /*
          *End register session, deassign all variables, insert transaction into db.
          *
          *
          */

         //create transaction with class and write;
         CreateTransaction ct = new CreateTransaction(this.itemList, this.sale_runner, this.dateOfExecution, this.price_total, this.price_subtotal, this.tax_amnt);
         ct.writeTransaction();


     }

     private void calculateSubTotal(){
         this.price_subtotal = 0; //start over
         for (POSItem i: this.itemList){
             this.price_subtotal += i.getItemPrice();
         }
     }

     private void calculateTotal(){
         //assume that subtotal is calculated.
         this.tax_amnt = this.price_subtotal * this.salestax;
         this.price_total = this.price_subtotal + this.tax_amnt;
     }








}
