package jundl77.izou.izoustocks;

import yahoofinance.Stock;

import java.util.HashMap;

/**
 * StocksFetchedData contains the requested stocks' data
 */
public class StocksFetchedData {
    private HashMap<String, Stock> stocks;

    /**
     * Creates a new StocksFetchedData object which contains the requested stocks' data
     */
    public StocksFetchedData() {
        this.stocks = new HashMap<>();
    }

    /**
     * Adds a stock to the stocks list
     *
     * @param ticker the ticker of the stock
     * @param stock the stock object itself
     */
    public void addStock(String ticker, Stock stock) {
        stocks.put(ticker, stock);
    }

    /**
     * Gets the list of all stocks requested
     *
     * @return the list of all stocks requested
     */
    public HashMap<String, Stock> getStocks() {
        return stocks;
    }
}
