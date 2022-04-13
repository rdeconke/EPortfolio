
package Eportfolio;
import java.text.DecimalFormat;

/**
 
 * @author rdeconke
 */
public class Stock extends Investment{

    /**
     * Basic constructor for stock
     * @param symbol the symbol representing the stock
     * @param name the name of the stock
     * @param quantity the quantity purchased
     * @param price the price purchased at
     */
    public Stock(String symbol, String name, int quantity, double price) throws Exception {
        super(symbol,name,quantity,price,0);
        this.setBookValue(quantity * price + 9.99);
    }

    @Override
    public String toString() {
        DecimalFormat price = new DecimalFormat("#.##");
        return "Stock{" + "symbol= " + this.getSymbol() + ", name= " + this.getName() + ", quantity= " + this.getQuantity() + ", price= " + price.format(this.getPrice()) + ", bookValue= " + price.format(this.getBookValue()) + '}';
    }
    
    
    
}
