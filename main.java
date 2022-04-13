package Eportfolio;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashMap;
/**
 *
 * @author rdeconke
 */
public class main {

    /**
     * Main program,
     *All this does now is create the gui, and read the inital portfolio from a file.
     * @param args command line arguments, not used.
     */
    
    public static void main(String[] args) {
        boolean quitting = false;
        Portfolio p = new Portfolio();
        defaultPanel panel = new defaultPanel(args,p);
        panel.setVisible(true);
        
        String input = "";
        String typeS;
        String fileLine = "";
        int type = 2;
        String symbol = "";
        String name = "";
        int quantity = 0;
        double price = 0;
        double bookValue = 0;
        StringTokenizer tokenizer1;
        if (args.length < 1) {
            System.out.println("Error, No file name found as command line argument.");
            quitting = true;
        } else {
            File f = new File(args[0]);
            try {
            
            Scanner inputScanner = new Scanner(f);
                System.out.println("Found file " + args[0] + " successfully.\n");
            while (inputScanner.hasNextLine()) {
                for (int i = 0; i < 6; i++) {
                    fileLine = inputScanner.nextLine();
                    tokenizer1 = new StringTokenizer(fileLine, "\"");
                    tokenizer1.nextToken();
                    typeS = tokenizer1.nextToken();
                    if (i == 0) {
                        if (typeS.equals("Stock")) {
                            type = 0;
                        } else {
                            type = 1;
                        }
                    } else if (i == 1) {
                        symbol = typeS;
                    } else if (i == 2) {
                        name = typeS;
                    } else if (i == 3) {
                        quantity = Integer.parseInt(typeS);
                    } else if (i == 4) {
                        price = Double.parseDouble(typeS);
                    } else if (i == 5) {
                        bookValue = Double.parseDouble(typeS);
                    }
                }
                StringTokenizer tokens = new StringTokenizer(name, " ");
            String nextToken = "";
            while (tokens.hasMoreTokens()) {
                nextToken = tokens.nextToken();
                nextToken = nextToken.toLowerCase();
                ArrayList<Integer> value = new ArrayList<Integer>();
                if (!p.getIndex().containsKey(nextToken)) {
                    value.add(p.getInvestments().size());
                    p.getIndex().put(nextToken, value);
                } else {
                    value = p.getIndex().get(nextToken);
                    value.add(p.getInvestments().size());
                    p.getIndex().put(nextToken, value);
                }
                }
                inputScanner.nextLine();
                if (type == 0) {
                    try {
                        p.getInvestments().add(new Stock(symbol,name,quantity,price));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    
                } else if (type == 1) {
                    try {
                        p.getInvestments().add(new MutualFund(symbol,name,quantity,price));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File to read from not found, will be created.");
            try {
                f.createNewFile();
            } catch (Exception Create){
                System.out.println("Unable to create file.");
            }
            
        }
        }
    }
}