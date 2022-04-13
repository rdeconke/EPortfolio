package Eportfolio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author rdeconke
 */
public class Portfolio {

    private ArrayList<Investment> Investments;
    private HashMap<String, ArrayList<Integer>> index;

    /**
     *
     * @param Investments investments arraylist
     *
     */
    public Portfolio(ArrayList Investments) {
        this.Investments = Investments;
        this.index = new HashMap<String, ArrayList<Integer>>();
    }

    /**
     * Basic constructor, empty array list
     */
    public Portfolio() {
        this(new ArrayList<Investment>());
    }

    /**
     * Buy method, allows the user to buy stocks and mutualfunds. Accepts input
     * using a scanner, and works in itself so no parameters used.
     */
    public String buy(String symbol, int quantity, double price, String name, int type) {
        String output = "";
        boolean found = false;
        boolean good = false;
        for (int i = 0; i < Investments.size(); i++) {
            if (Investments.get(i).getSymbol().equalsIgnoreCase(symbol)) {
                if (Investments.get(i).getType() != type) {
                    if (type == 1) {
                        output += ("Error, found a mutualfund with that symbol.\n");
                        found = true;
                    } else if (type == 0) {
                        output +=("Error, found a stock with that symbol.\n");
                        found = true;
                    }
                } else {
                    if (type == 0) {
                        output += ("Found selected Stock in portfolio.\n");
                        found = true;
                    } else if (type == 1) {
                        output += ("Found selected MutualFund in portfolio.\n");
                        found = true;
                    }
                    //set into array list
                    Investments.get(i).setQuantity(quantity + Investments.get(i).getQuantity());
                    try {
                        Investments.get(i).setPrice(price);
                    } catch (Exception e) {
                        output += ("Error, price can not be less than 0.");
                    }
                    
                    if (type == 0) {
                        Investments.get(i).setBookValue(Investments.get(i).getBookValue() + (price * quantity) + 9.99);
                    } else if (type == 1) {
                        Investments.get(i).setBookValue(Investments.get(i).getBookValue() + (price * quantity));
                    }
                    i = Investments.size() + 1;
                }
            }
        }
        //if price hasnt been changed that means we found nothing
        if (!found) {
            if (type == 0) {
                output += ("Could not find selected Symbol, generating new Stock\n");
            } else if (type == 1) {
                output += ("Could not find selected Symbol, generating new MutualFund\n");
            }
            //add new stock
            boolean goodAdd = true;
            if (type == 0) {
                try {
                    Investments.add(new Stock(symbol, name, quantity, price));
                } catch (Exception e) {
                    output += "\n" + e;
                    goodAdd = false;
                }
            } else if (type == 1) {
                try {
                    Investments.add(new MutualFund(symbol, name, quantity, price));
                } catch (Exception e) {
                    output += "\n" + e;
                    goodAdd = false;
                }
                
            }
            if (goodAdd) {
            StringTokenizer tokens = new StringTokenizer(name, " ");
            String nextToken = "";
            while (tokens.hasMoreTokens()) {
                nextToken = tokens.nextToken();
                nextToken = nextToken.toLowerCase();
                ArrayList<Integer> value = new ArrayList<Integer>();
                if (!index.containsKey(nextToken)) {
                    value.add(Investments.size() - 1);
                    index.put(nextToken, value);
                } else {
                    value = index.get(nextToken);
                    value.add(Investments.size() - 1);
                    index.put(nextToken, value);
                }
            }
            }
            

        }
        output += "\n" + this.toString();
        return output;
    }

    /**
     * Sell method, allows users to sell mutual funds and stocks. Works within
     * itself using a scanner so no paramaters or return.
     */
    public String sell(String symbol,double price, int quantity) {
        String output = "";
        boolean found = false;
        DecimalFormat money = new DecimalFormat("#.##");
        //check for symbol
        for (int i = 0; i < Investments.size(); i++) {
            if (Investments.get(i).getSymbol().equalsIgnoreCase(symbol)) {
                if (Investments.get(i).getType() == 0) {
                    found = true;
                    output += ("Found Stock " + Investments.get(i).getName() + " with symbol " + symbol + ". You currently own " + Investments.get(i).getQuantity() + ".\n");
                } else {
                    found = true;
                    output += ("Found MutualFund " + Investments.get(i).getName() + " with symbol " + symbol + ". You currently own " + Investments.get(i).getQuantity() + ".\n");
                }
                //ensure they dont oversell
                if (Investments.get(i).getQuantity() - quantity < 0) {
                    output += ("Error, Attempted to sell more than owned.\n");
                } else if (Investments.get(i).getQuantity() - quantity == 0) {
                    //case if 0
                    if (Investments.get(i).getType() == 0) {
                        output += ("Successfully sold all stock in " + Investments.get(i).getName() + ".\n"
                                + "You recieved a payment of " + money.format(price * quantity - 9.99) + ".\n");
                    } else if (Investments.get(i).getType() == 1) {
                        output += ("Successfully sold all MutualFunds in " + Investments.get(i).getName() + ".\n"
                                + "You recieved a payment of " + money.format(price * quantity - 45) + ".\n");
                    }

                    Iterator<HashMap.Entry<String, ArrayList<Integer>>> iter = index.entrySet().iterator();
                    ArrayList<String> empty = new ArrayList<String>();
                    while (iter.hasNext()) {
                        HashMap.Entry<String, ArrayList<Integer>> entry = iter.next();
                        ArrayList<Integer> value = entry.getValue();
                        if (entry.getValue().size() == 1) {
                            if (entry.getValue().get(0) == i) {
                                empty.add(entry.getKey());
                            } else if (entry.getValue().get(0) > i) {
                                value.add(value.get(0) - 1);
                                value.remove(0);
                                index.put(entry.getKey(), value);
                            }
                        } else {
                            for (int o = 0; o < value.size(); i++) {
                                if (value.get(o) > i) {
                                    value.set(o, value.get(o) - 1);
                                } else if (value.get(o) == i) {
                                    value.remove(o);
                                }
                            }
                            index.put(entry.getKey(), value);
                        }
                    }
                    for (int o = 0; o < empty.size(); o++) {
                        index.remove(empty.get(o));
                    }
                    Investments.remove(i);
            } else {
                if (Investments.get(i).getType() == 0) {
                    output += ("Successfully sold " + quantity + " stocks in " + Investments.get(i).getName() + ".\n" + ""
                            + "You received a payment of " + money.format(price * quantity - 9.99) + ".\n");
                    int oldQ = Investments.get(i).getQuantity();

                    Investments.get(i).setQuantity(Investments.get(i).getQuantity() - quantity);
                    Investments.get(i).setBookValue(Investments.get(i).getBookValue() * Investments.get(i).getQuantity() / oldQ);
                } else if (Investments.get(i).getType() == 1) {
                    output += ("Successfully sold " + quantity + " MutualFunds in " + Investments.get(i).getName() + ".\n" + ""
                            + "You received a payment of " + money.format(price * quantity - 45) + ".\n");
                    int oldQ = Investments.get(i).getQuantity();

                    Investments.get(i).setQuantity(Investments.get(i).getQuantity() - quantity);
                    Investments.get(i).setBookValue(Investments.get(i).getBookValue() * Investments.get(i).getQuantity() / oldQ);
                }
            }
        }
        }
        //if price was never updated we couldnt find the symbol inputted
        if (!found) {
            output += ("Error, could not find a Stock or Mutual fund with symbol " + symbol + ".\n");
        }
        return output;
    }


    /**
     * get gain function returns the total gain of the program.
     */
    public String[] getGain() {
        double stocksGain = 0;
        String output[] = {"",""};
        double mFGain = 0;
        double total = 0;
        DecimalFormat money = new DecimalFormat("#.##");
        for (int i = 0; i < Investments.size(); i++) {
            if (Investments.get(i).getType() == 0) {
                stocksGain = Investments.get(i).getPrice() * Investments.get(i).getQuantity() - Investments.get(i).getBookValue();
                output[0] += "You gained " + money.format(stocksGain) + " from the stock " + Investments.get(i).getName() + ".\n";
                total += stocksGain;
            } else {
                mFGain = Investments.get(i).getPrice() * Investments.get(i).getQuantity() - Investments.get(i).getBookValue() - 45;
                output[0] += "You gained " + money.format(mFGain) + " from the MutualFund " + Investments.get(i).getName() + ".\n";
                total += mFGain;
            }
        }
        //add together to get total gain
        output[1] = money.format(total);
        return output;
    }

    /**
     * Search function, allows the user to search through the investments for a
     * particular investment. Each parameter can be left blank or used, and the function will
     * print all investments that match all used parameters.
     */
    public String search(String symbol, String keywords, String price1, String price2) {
        Scanner s = new Scanner(System.in);
        ArrayList<Investment> good = new ArrayList<Investment>();

        boolean none = false;
        //if keywords were entered
        if (!keywords.isBlank()) {
            ArrayList<Integer> hits = new ArrayList<Integer>();
            //fill list of the same size of investments with zeros
            for (int i = 0; i < Investments.size(); i++) {
                hits.add(0);
            }
            //split around spaces
            StringTokenizer tokens = new StringTokenizer(keywords, " ");
            int og = tokens.countTokens();
            String currToken;
            ArrayList<Integer> indicies;
            //loop for all tokens
            while (tokens.hasMoreTokens()) {
                currToken = tokens.nextToken();
                //if we ever find a token that isnt in the map, nothing will ever match it
                //so we break and make the good list contain nothing.
                if (!index.containsKey(currToken)) {
                    none = true;
                    break;
                }
                //get the indicies from the hashmap
                indicies = index.get(currToken);
                for (int i = 0; i < indicies.size(); i++) {
                    //increment hits in the indexes that match
                    hits.set(indicies.get(i), hits.get(indicies.get(i)) + 1);
                }
            }
            //check if break happened
            if (!none) {
                for (int i = 0; i < hits.size(); i++) {
                    //if any index was increment the right amount of times, add it to the good list.
                    if (hits.get(i) == og) {
                        good.add(Investments.get(i));
                    }
                }
            }
        } else {
            good = (ArrayList<Investment>) Investments.clone();
        }
        int i = 0;
        ArrayList<Investment> good2 = new ArrayList<Investment>();
        if (!price1.isBlank()) {
            System.out.println("Pirce 1 not blank\n");
            double price1I = Double.parseDouble(price1);
            for (i = 0; i < good.size(); i ++) {
                System.out.println("Loop 1 happen\n");
                if (good.get(i).getPrice() >= price1I) {
                    good2.add(good.get(i));
                }
            }
        } else {
            good2 = (ArrayList<Investment>) good.clone();
        }
        ArrayList<Investment> good3 = new ArrayList<Investment>();
        System.out.println(price2);
        if (!price2.isBlank()) {
            double price2I = Double.parseDouble(price2);
            
            for (i = 0; i < good2.size(); i ++) {
                if (good2.get(i).getPrice() <= price2I) {
                    good3.add(good2.get(i));
                }
            }
        } else {
            good3 = (ArrayList<Investment>) good2.clone();
        }
        ArrayList<Investment> good4 = new ArrayList<Investment>();
        //check for symbol
        if (!symbol.isEmpty()) {
            i = 0;
            while (i < good3.size()) {
                if (!good3.get(i).getSymbol().equalsIgnoreCase(symbol)) {
                    good4.add(good3.get(i));
                }
                i++;
            }
        } else {
            good4 = (ArrayList<Investment>) good3.clone();
        }
        return ("These are the investments found that matched all parameters given: \n" + good4);
    }

    /**
     * accessor method for the investments
     * @return the investments arraylist
     */
    public ArrayList<Investment> getInvestments() {
        return Investments;
    }

    /**
     * mutator method for investments arraylist
     * @param Investments the new investments list
     */
    public void setInvestments(ArrayList<Investment> Investments) {
        this.Investments = Investments;
    }

    /**
     * accessor method for the hashmap
     * @return the hashmap
     */
    public HashMap<String, ArrayList<Integer>> getIndex() {
        return index;
    }

    /**
     *mutator method for index hashmap
     * @param index the new hashmap
     */
    public void setIndex(HashMap<String, ArrayList<Integer>> index) {
        this.index = index;
    }

    /**
     *tostring method for portfolio
     * @return the portfolio as a string
     */
    @Override
    public String toString() {
        return "Portfolio{\n" +Investments + "\nHashMap:\n" + index + '}';
    }

}
