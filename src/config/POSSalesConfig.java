package config;

import utils.POSXMLReader;

public class POSSalesConfig {
    private static POSXMLReader psxr = new POSXMLReader("src/config/files/pos_sales_config.xml");
    public static double getSalesTax(){return Double.parseDouble(psxr.getResultByPath("sales_tax_percent"));}

}
