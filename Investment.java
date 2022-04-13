
package Eportfolio;

import java.text.DecimalFormat;

/**
 *
 * @author rdeconke
 * 
 */
public class Investment {
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    private int type;

    /**
     * Default constructor
     * @param symbol the symbol
     * @param name the name of the investment
     * @param quantity how many were bought
     * @param price the price paid per unit
     * @param type stock / mf
     */
    public Investment(String symbol, String name, int quantity, double price, int type) throws Exception{
        if (symbol.isBlank()) {
            throw new Exception("Error, symbol can not be empty.");
        } else {
            this.symbol = symbol;
        }
        if (name.isBlank()) {
            throw new Exception("Error, name can not be empty.");
        } else {
            this.name = name;
        }
        if (quantity <= 0) {
            throw new Exception("Error, quantity can not be less than 1.");
        } else {
            this.quantity = quantity;
        }
        if (price <= 0) {
            throw new Exception("Error, price can not be less than 1.");
        } else {
            this.price = price;
        }
        
        this.type = type;
    }

    /**
     *Accessor method for symbol
     * @return
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Accessor method for name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *Accessor method for quantity
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *Accessor method for price
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Accessor method for book value
     * @return
     */
    public double getBookValue() {
        return bookValue;
    }

    /**
     * Mutator for symbol
     * @param symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Mutator for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator for quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * mutator for price
     * @param price the new price to be set
     */
    public void setPrice(double price) throws Exception{
        if (price <= 0) {
            throw new Exception("Error, price can not be below 0.");
        } else {
            this.price = price;
        }
        
    }

    /**
     *mutator for bookvalue
     * @param bookValue the new book value
     */
    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }
    /**
     * Accessor for type
     * @return 1 or 0 depending on stock or mf
     */
    public int getType() {
        return type;
    }
    /**
     * Mutator for type
     * @param type new type
     */
    public void setType(int type) {
        this.type = type;
    }
    
    /**
     * Tostring method, prints the investment
     * @return 
     */
    @Override
    public String toString() {
        DecimalFormat price = new DecimalFormat("#.##");
        return "investment{" + "symbol= " + this.getSymbol() + ", name= " + this.getName() + ", quantity= " + this.getQuantity() + ", price= " + price.format(this.getPrice()) + ", bookValue= " + price.format(this.getBookValue()) + '}';
    } 

    
    
        
}
