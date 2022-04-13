
package Eportfolio;

/**
 *
 * @author rdeconke
 */
public class MutualFund extends Investment{

    /**
     *
     * @param symbol the symbol representing the mutual fund
     * @param name the name of the mutual fund
     * @param quantity the amount of units purchased
     * @param price the price they were purchased at
     */
    public MutualFund(String symbol, String name, int quantity, double price) throws Exception {
        super(symbol,name,quantity,price,1);
        this.setBookValue(quantity * price);
    }
    @Override
    /*
    Tostring method, returns the mutual fund as a string.
    */
    public String toString() {
        return "MutualFund{" + "symbol= " + this.getSymbol() + ", name= " + this.getName() + ", quantity= " + this.getQuantity() + ", price= " + this.getPrice() + ", bookValue= " + this.getBookValue() + '}';
    }
}
