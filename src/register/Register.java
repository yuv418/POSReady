package register;

public abstract class Register {
    public boolean addItem(){ //add an item to the sale
        return true;
    }

    public boolean removeItem(){ //remove an item from the sale
        return true;
    }

    public boolean logSale(){ //log the sale in the database
        return true;
    }

    public boolean exportSale(){ //export the sale (print, export invoice as pdf, etc.)
        return true;
    }

    public boolean modifyItem(){ //modify the item in a sale - can require admin privileges to change an item's value for just one sale
        return true;
    }


}
