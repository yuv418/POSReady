package register;

import auth.POSUser;
import items.POSItem;
import java.sql.*;
import java.util.Date;

public class RegisterSession {
     private POSItem[] registeritemlist;
     private POSUser sale_runner;
     private Date dateOfExecution;
     private double price_subtotal;
     private double price_total;
     private double tax_amnt;
     private int[] itemids;
     private Connection mariadb_default;


     public RegisterSession(POSUser sale_runner){
        this.sale_runner = sale_runner;
     }

     public

}
