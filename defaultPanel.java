package Eportfolio;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *The panel the program is drawn on
 *
 */
public class defaultPanel extends JFrame {

    private Portfolio p;
    private JPanel buyPanel, buyPanelButtons, buyPanelText;
    private JPanel sellPanel, sellPanelButtons, sellPanelText;
    private JPanel updatePanel, updatePanelButtons, updatePanelText;
    private JPanel searchPanel, searchPanelButtons, searchPanelText;
    private JPanel gainPanel, gainPanelText;
    private JTextField symbolT, nameT, priceT, quantityT;
    private JComboBox types;
    private JScrollPane scrolledText;
    private JButton prev, next;
    private int updatePlace;
    private JTextArea text;
    private JTextArea temp;
    private String commandLine[];
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    /**
     * Default constructor
     * @param args command line arguments
     * @param port portfolio
     */
    public defaultPanel(String args[], Portfolio port) {
        super();
        p = port;
        updatePlace = 0;
        commandLine = args;
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setTitle("ePortfolio");
        temp = new JTextArea();
        temp.setText("Welcome to ePortfolio. \n" +
"Choose a command from the Commands menu to buy or sell \n an investment, update prices for all investments,\n get gain for the portfolio, search for relevant investments,\n or quit the program.");
        add(temp,BorderLayout.CENTER);
        generateMenu();
        generateBuyPanel();
        generateSellPanel();
        generateUpdatePanel();
        generateGainPanel();
        generateSearchPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Generate menu function, made to make the program more modular.
     * simply creates the menu bar at the top.
     */
    private void generateMenu() {
        JMenu options = new JMenu("Commands");
        JMenuItem buy = new JMenuItem("Buy");
        buy.addActionListener(new menuListener());
        JMenuItem sell = new JMenuItem("Sell");
        sell.addActionListener(new menuListener());
        JMenuItem update = new JMenuItem("Update");
        update.addActionListener(new menuListener());
        JMenuItem gain = new JMenuItem("getGain");
        gain.addActionListener(new menuListener());
        JMenuItem search = new JMenuItem("Search");
        search.addActionListener(new menuListener());
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new menuListener());
        options.add(buy);
        options.add(sell);
        options.add(update);
        options.add(gain);
        options.add(search);
        options.add(quit);
        JMenuBar bar = new JMenuBar();
        bar.add(options);
        add(bar, BorderLayout.NORTH);
    }
    /**
     * Generate buy panel function, creates all needed
     * containers for the buy panel and displays them.
     */
    private void generateBuyPanel() {
        buyPanel = new JPanel();
        buyPanel.setLayout(new GridLayout(0, 2, 20, 20));
        JLabel buying = new JLabel();
        buying.setText("Buying an Investment.");
        JLabel placeHolder = new JLabel();
        buyPanel.add(buying);
        buyPanel.add(placeHolder);
        JLabel type = new JLabel();
        type.setText("Type:");
        buyPanel.add(type);
        String typesStrings[] = {"Stock", "MutualFund"};
        types = new JComboBox(typesStrings);
        buyPanel.add(types);
        JLabel symbol = new JLabel();
        symbol.setText("Symbol: ");
        buyPanel.add(symbol);
        symbolT = new JTextField();
        buyPanel.add(symbolT);
        JLabel name = new JLabel();
        name.setText("Name: ");
        buyPanel.add(name);
        nameT = new JTextField();
        buyPanel.add(nameT);
        JLabel quantity = new JLabel();
        quantity.setText("Quantity: ");
        buyPanel.add(quantity);
        quantityT = new JTextField();
        buyPanel.add(quantityT);
        JLabel price = new JLabel();
        price.setText("Price: ");
        buyPanel.add(price);
        priceT = new JTextField();
        buyPanel.add(priceT);
        add(buyPanel, BorderLayout.LINE_START);
        //buyPanel.setSize(200,200);
        buyPanel.setVisible(false);
        buyPanelButtons = new JPanel();
        buyPanelButtons.setLayout(new GridLayout(0, 1, 0, 100));
        JButton reset = new JButton();
        reset.setText("reset");
        reset.addActionListener(new resetListener());
        JButton buy = new JButton();
        buy.setText("Buy");
        buy.addActionListener(new buyListener());
        buyPanelButtons.add(buy);
        buyPanelButtons.add(reset);
        add(buyPanelButtons, BorderLayout.EAST);
        buyPanelButtons.setVisible(false);
        text = new JTextArea(10, 50);
        text.setEditable(false);
        scrolledText = new JScrollPane(text);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        buyPanelText = new JPanel();
        buyPanelText.add(scrolledText);
        add(buyPanelText, BorderLayout.SOUTH);
        buyPanelText.setVisible(false);
    }
    /**
     * Generate sell panel function, creates all the needed containers for
     * the sell panel.
     */
    private void generateSellPanel() {
        sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(0, 2, 20, 20));
        JLabel selling = new JLabel();
        selling.setText("selling an Investment.");
        JLabel placeHolder = new JLabel();
        sellPanel.add(selling);
        sellPanel.add(placeHolder);
        JLabel symbol = new JLabel();
        symbol.setText("Symbol: ");
        sellPanel.add(symbol);
        symbolT = new JTextField();
        sellPanel.add(symbolT);
        JLabel quantity = new JLabel();
        quantity.setText("Quantity: ");
        sellPanel.add(quantity);
        quantityT = new JTextField();
        sellPanel.add(quantityT);
        JLabel price = new JLabel();
        price.setText("Price: ");
        sellPanel.add(price);
        priceT = new JTextField();
        sellPanel.add(priceT);
        add(sellPanel, BorderLayout.LINE_START);
        //sellPanel.setSize(200,200);
        sellPanel.setVisible(false);
        sellPanelButtons = new JPanel();
        sellPanelButtons.setLayout(new GridLayout(0, 1, 0, 100));
        JButton reset = new JButton();
        reset.setText("reset");
        reset.addActionListener(new resetListener());
        JButton sell = new JButton();
        sell.setText("sell");
        sell.addActionListener(new sellListener());
        sellPanelButtons.add(sell);
        sellPanelButtons.add(reset);
        add(sellPanelButtons, BorderLayout.EAST);
        sellPanelButtons.setVisible(false);
        text = new JTextArea(10, 50);
        text.setEditable(false);
        scrolledText = new JScrollPane(text);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sellPanelText = new JPanel();
        sellPanelText.add(scrolledText);
        add(sellPanelText, BorderLayout.SOUTH);
        sellPanelText.setVisible(false);
    }
    /**
     * Generates all needed containers for the update panel.
     */
    private void generateUpdatePanel() {
        updatePlace = 0;
        updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(0, 2, 20, 20));
        JLabel updateing = new JLabel();
        updateing.setText("updating Investments.");
        JLabel placeHolder = new JLabel();
        updatePanel.add(updateing);
        updatePanel.add(placeHolder);
        JLabel symbol = new JLabel();
        symbol.setText("Symbol: ");
        updatePanel.add(symbol);
        symbolT = new JTextField();
        symbolT.setEditable(false);
        updatePanel.add(symbolT);
        JLabel name = new JLabel();
        name.setText("Name:  ");
        updatePanel.add(name);
        nameT = new JTextField();
        nameT.setEditable(false);
        updatePanel.add(nameT);
        JLabel price = new JLabel();
        price.setText("Price: ");
        updatePanel.add(price);
        priceT = new JTextField();
        updatePanel.add(priceT);
        add(updatePanel, BorderLayout.LINE_START);
        //updatePanel.setSize(200,200);
        updatePanel.setVisible(false);
        updatePanelButtons = new JPanel();
        updatePanelButtons.setLayout(new GridLayout(0, 1, 0, 100));
        prev = new JButton();
        prev.setText("Prev");
        prev.addActionListener(new prevListener());
        next = new JButton();
        next.setText("Next");
        next.addActionListener(new nextListener());
        JButton update = new JButton();
        update.setText("Save");
        update.addActionListener(new updateListener());
        prev.setEnabled(false);
        if (p.getInvestments().size() < 2) {
            next.setEnabled(false);
            if (p.getInvestments().size() < 1) {
                update.setEnabled(false);
            }
        } else {
            update.setEnabled(true);
            next.setEnabled(true);
            nameT.setText(p.getInvestments().get(updatePlace).getName());
            symbolT.setText(p.getInvestments().get(updatePlace).getSymbol());
            priceT.setText("" + p.getInvestments().get(updatePlace).getPrice());
        }
        updatePanelButtons.add(prev);
        updatePanelButtons.add(next);
        updatePanelButtons.add(update);
        add(updatePanelButtons, BorderLayout.EAST);
        updatePanelButtons.setVisible(false);
        text = new JTextArea(10, 50);
        text.setEditable(false);
        scrolledText = new JScrollPane(text);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        updatePanelText = new JPanel();
        updatePanelText.add(scrolledText);
        add(updatePanelText, BorderLayout.SOUTH);
        updatePanelText.setVisible(false);
    }
    /**
     * generates all needed containers for the gain panel.
     */
    private void generateGainPanel() {
        gainPanel = new JPanel();
        gainPanel.setLayout(new GridLayout(0, 2, 20, 300));
        JLabel gaining = new JLabel();
        gaining.setText("getting total Gain");
        JLabel placeHolder = new JLabel();
        gainPanel.add(gaining);
        gainPanel.add(placeHolder);
        JLabel gain = new JLabel();
        gain.setText("total gain: ");
        gainPanel.add(gain);
        symbolT = new JTextField();
        symbolT.setEditable(false);
        gainPanel.add(symbolT);
        add(gainPanel, BorderLayout.LINE_START);
        gainPanel.setVisible(false);
        text = new JTextArea(10, 50);
        text.setEditable(false);
        scrolledText = new JScrollPane(text);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        gainPanelText = new JPanel();
        gainPanelText.add(scrolledText);
        add(gainPanelText, BorderLayout.SOUTH);
        gainPanelText.setVisible(false);
        String gains[] = p.getGain();
        text.setText(gains[0]);
        symbolT.setText(gains[1]);
    }
    /**
     * Generates all needed containers for the search panel
     */
    private void generateSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(0, 2, 20, 20));
        JLabel searching = new JLabel();
        searching.setText("searching Investments");
        JLabel placeHolder = new JLabel();
        searchPanel.add(searching);
        searchPanel.add(placeHolder);
        JLabel symbol = new JLabel();
        symbol.setText("Symbol: ");
        searchPanel.add(symbol);
        symbolT = new JTextField();
        searchPanel.add(symbolT);
        JLabel name = new JLabel();
        name.setText("Name keywords: ");
        searchPanel.add(name);
        nameT = new JTextField();
        searchPanel.add(nameT);
        JLabel quantity = new JLabel();
        quantity.setText("Low price: ");
        searchPanel.add(quantity);
        quantityT = new JTextField();
        searchPanel.add(quantityT);
        JLabel price = new JLabel();
        price.setText("High price: ");
        searchPanel.add(price);
        priceT = new JTextField();
        searchPanel.add(priceT);
        add(searchPanel, BorderLayout.LINE_START);
        //searchPanel.setSize(200,200);
        searchPanel.setVisible(false);
        searchPanelButtons = new JPanel();
        searchPanelButtons.setLayout(new GridLayout(0, 1, 0, 100));
        JButton reset = new JButton();
        reset.setText("reset");
        reset.addActionListener(new resetListener());
        JButton search = new JButton();
        search.setText("search");
        search.addActionListener(new searchListener());
        searchPanelButtons.add(search);
        searchPanelButtons.add(reset);
        add(searchPanelButtons, BorderLayout.EAST);
        searchPanelButtons.setVisible(false);
        text = new JTextArea(10, 50);
        text.setEditable(false);
        scrolledText = new JScrollPane(text);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchPanelText = new JPanel();
        searchPanelText.add(scrolledText);
        add(searchPanelText, BorderLayout.SOUTH);
        searchPanelText.setVisible(false);
    }
    /**
     * Function to hide all panels that are not being used.
     */
    private void hideAll() {
        buyPanel.setVisible(false);
        buyPanelButtons.setVisible(false);
        buyPanelText.setVisible(false);
        sellPanel.setVisible(false);
        sellPanelButtons.setVisible(false);
        sellPanelText.setVisible(false);
        updatePanel.setVisible(false);
        updatePanelButtons.setVisible(false);
        updatePanelText.setVisible(false);
        gainPanel.setVisible(false);
        gainPanelText.setVisible(false);
        searchPanel.setVisible(false);
        searchPanelButtons.setVisible(false);
        searchPanelText.setVisible(false);
        temp.setVisible(false);
    }
    /**
     * Listener for the menu box, calls functions based on option
     * Additional functionality to write to output file when quitting.
     */
    private class menuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String menuString = e.getActionCommand();
            if (menuString.equals("Quit")) {
                try {
                    PrintWriter fileWriter = new PrintWriter(commandLine[0], "UTF-8");
                    int outputType;
                    for (int i = 0; i < p.getInvestments().size(); i++) {

                        outputType = p.getInvestments().get(i).getType();
                        if (outputType == 0) {
                            fileWriter.println("type = \"Stock\"\n" + "symbol = \"" + p.getInvestments().get(i).getSymbol()
                                    + "\"\nname = \"" + p.getInvestments().get(i).getName()
                                    + "\"\nquantity = \"" + p.getInvestments().get(i).getQuantity()
                                    + "\"\nprice = \"" + p.getInvestments().get(i).getPrice()
                                    + "\"\nbookValue = \"" + p.getInvestments().get(i).getBookValue()
                                    + "\"\n");
                        }

                    }
                    fileWriter.close();
                } catch (Exception f) {
                    System.out.println("Unable to write to file.");
                }
                System.exit(0);
            } else if (menuString.equals("Buy")) {
                hideAll();
                generateBuyPanel();
                buyPanel.setVisible(true);
                buyPanelButtons.setVisible(true);
                buyPanelText.setVisible(true);
            } else if (menuString.equals("Sell")) {
                hideAll();
                generateSellPanel();
                sellPanel.setVisible(true);
                sellPanelButtons.setVisible(true);
                sellPanelText.setVisible(true);
            } else if (menuString.equals("Update")) {
                hideAll();
                generateUpdatePanel();
                updatePanel.setVisible(true);
                updatePanelButtons.setVisible(true);
                updatePanelText.setVisible(true);
            } else if (menuString.equals("getGain")) {
                hideAll();
                generateGainPanel();
                gainPanel.setVisible(true);
                gainPanelText.setVisible(true);
            } else if (menuString.equals("Search")) {
                hideAll();
                generateSearchPanel();
                searchPanel.setVisible(true);
                searchPanelButtons.setVisible(true);
                searchPanelText.setVisible(true);
            }
        }
    }
    /**
     * Action listen and does error checking for buy button, calls 
     * the buy function 
     */
    private class buyListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            boolean good = true;
            int quan = 0;
            double price = 0;
            String name = nameT.getText();
            String typeS = (String) (types.getSelectedItem());
            //System.out.println(typeS);
            try {
                price = Double.parseDouble(priceT.getText());
            } catch (Exception ex) {
                text.setText("Error, please enter a decimal number for price.");
                good = false;
            }
            try {
                quan = Integer.parseInt(quantityT.getText());
            } catch (Exception ex) {
                text.setText("Error, please enter an Integer for quantity.");
                good = false;
            }
            String sym = symbolT.getText();
            int type;
            if (good) {
                if (typeS.equalsIgnoreCase("Stock")) {
                    type = 0;
                    //System.out.println("happened");
                } else {
                    type = 1;
                }
                try {
                    text.setText(p.buy(sym, quan, price, name, type));
                } catch (Exception ex) {
                    text.setText(ex.toString());
                }

            }
        }
    }
    /**
     * Listener for sell button, calls the sell function and
     * does some error checking.
     */
    private class sellListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            double price = 0;
            int quan = 0;
            boolean good = true;
            try {
                price = Double.parseDouble(priceT.getText());
            } catch (Exception ex) {
                text.setText("Error, please enter a decimal number for price.");
                good = false;
            }
            try {
                quan = Integer.parseInt(quantityT.getText());
            } catch (Exception ex) {
                text.setText("Error, please enter an Integer for quantity.");
                good = false;
            }
            String sym = symbolT.getText();
            if (good) {
                text.setText(p.sell(sym, price, quan));
            }
        }
    }
    /**
     * Listener for update button, calls the update function on 
     * the current update place, and error checks price.
     */
    private class updateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            double price = 0;
            try {
                price = Double.parseDouble(priceT.getText());
            } catch (Exception ex) {
                text.setText("Error, please enter a decimal number for price.");
            }
            try {
                p.getInvestments().get(updatePlace).setPrice(price);
                priceT.setText("" + price);
            } catch (Exception ex) {
                text.setText("Error, price must be greater than 0.");
            }
        }
    }
    /**
     * listener for next button, 
     * updates updatePlace to keep track of where we
     * are in the array.
     */
    private class nextListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            updatePlace++;
            if (updatePlace == p.getInvestments().size() - 1) {
                next.setEnabled(false);
            }
            prev.setEnabled(true);
            nameT.setText(p.getInvestments().get(updatePlace).getName());
            symbolT.setText(p.getInvestments().get(updatePlace).getSymbol());
            priceT.setText("" + p.getInvestments().get(updatePlace).getPrice());
        }
    }
    /**
     * Essentially the opposite of the next listener
     * subtracts from update place and updates prev and next buttons
     * to ensure no null pointers are possible.
     */
    private class prevListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            updatePlace--;
            if (updatePlace == 0) {
                prev.setEnabled(false);
            }
            next.setEnabled(true);
            nameT.setText(p.getInvestments().get(updatePlace).getName());
            symbolT.setText(p.getInvestments().get(updatePlace).getSymbol());
            priceT.setText("" + p.getInvestments().get(updatePlace).getPrice());
        }
    }
    /**
     * listener for reset button.
     * clears all text fields.
     */
    private class resetListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            nameT.setText("");
            symbolT.setText("");
            priceT.setText("");
            quantityT.setText("");
            text.setText("");
        }
    }
    /**
     * listener for search function.
     * Does error checking and gets parameters for search.
     */
    private class searchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String symbl = symbolT.getText();
            String name = nameT.getText();
            String highT = "", lowT = "";
            double test;
            boolean good = true;
            if (!quantityT.getText().isBlank()) {
                try {
                    test = Double.parseDouble(quantityT.getText());
                } catch (Exception ex) {
                    text.setText("Error, please enter a decimal number for low price.");
                    good = false;
                }
            }
            if (!priceT.getText().isBlank()) {
                try {
                    test = Double.parseDouble(priceT.getText());
                } catch (Exception ex) {
                    text.setText("Error, please enter a decimal number for high price.");
                    good = false;
                }
            }
            if (good) {
                lowT = quantityT.getText();
                highT = priceT.getText();
            }
            text.setText(p.search(symbl, name, lowT, highT));
        }
    }

}
