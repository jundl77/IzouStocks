package jundl77.izou.izoustocks;

import yahoofinance.Stock;

import java.util.HashMap;

/**
 * StocksFetchedData contains the requested stocks' data
 */
public class StocksFetchedData {
    private HashMap<String, Stock> stocks;
    private String soundPath;

    /**
     * Creates a new StocksFetchedData object which contains the requested stocks' data
     *
     * @param soundName the name of the sound to be played as an announcement noise for the stocks
     */
    public StocksFetchedData(String soundName) {
        this.stocks = new HashMap<>();

        if (soundName != null) {
            this.soundPath = StocksAddOn.ADDON_DATA_PATH_LOCAL + soundName;
        } else {
            this.soundPath = null;
        }
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

    /**
     * Gets the path to the sound file to play at the beginning of the stocks output
     *
     * @return the path to the sound file
     */
    public String getSoundPath() {
        return soundPath;
    }

    /**
     * Sets the path to the sound file to play at the beginning of the stocks output
     *
     * @param soundPath the path to the sound file
     */
    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }
}
