package jundl77.izou.izoustocks;

import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.Event;
import intellimate.izou.resource.Resource;
import intellimate.izou.system.Context;
import intellimate.izou.system.Identification;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.util.*;

/**
 * StocksContentGenerator generates desired stocks' data
 */
public class StocksContentGenerator extends ContentGenerator {
    /**
     * The ID of the ContentGenerator
     */
    public static final String ID = StocksContentGenerator.class.getCanonicalName();

    /**
     * The ID of the stocks event, so that IzouStocks can be reached
     */
    public static final String STOCKS_EVENT_ID = StocksContentGenerator.class.getCanonicalName() + "stocksEvent";

    /**
     * The ID of the resource the StocksContentGenerator creates
     */
    public static final String RESOURCE_ID = StocksContentGenerator.class.getCanonicalName()+"resource";

    /**
     * Creates a new StocksContentGenerator object that can generate Stocks' data for desired stocks
     *
     * @param context the context of the addOn
     */
    public StocksContentGenerator(Context context) {
        super(ID, context);

        getContext().events.addEventIDToPropertiesFile("The event ID of the IzouStocks addOn.",
                "IZOUSTOCK_EVENT_ID", STOCKS_EVENT_ID);
    }

    private StocksFetchedData grabStocks() {
        Properties properties = getContext().properties.getPropertiesContainer().getProperties();
        StocksFetchedData fetchedData = new StocksFetchedData(getContext().properties.getPropertiesContainer().
                getProperties().getProperty("audioFileName"));
        Map<String, String> tickers = new HashMap<>();
        for (String stockName : properties.stringPropertyNames()) {
            if (stockName.equals("audioFileName")) {
                continue;
            }
            String ticker = (String) properties.get(stockName);
            tickers.put(ticker, stockName);
        }
        Map<String, Stock> stocks = YahooFinance.get(tickers.keySet().toArray(new String[tickers.size()]));
        for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
            if (entry.getValue() != null) {
                fetchedData.addStock(tickers.get(entry.getKey()).replace("_", " "), entry.getValue());
            } else {
                getContext().logger.getLogger().debug("Request to get " + entry.getKey() + " from Yahoo Finance failed");
            }
        }
        return fetchedData;
    }

    @Override
    public List<Resource> announceResources() {
        Optional<Identification> identification = getIdentificationManager().getIdentification(this);
        Resource<StocksFetchedData> resource = new Resource<>(RESOURCE_ID);
        if (identification.isPresent()) {
            resource = resource.setProvider(identification.get());
        }
        return Arrays.asList(resource);
    }

    @Override
    public List<String> announceEvents() {
        return Arrays.asList(STOCKS_EVENT_ID);
    }

    @Override
    public List<Resource> provideResource(List<Resource> resources, Optional<Event> event) {
        if(!resources.stream().anyMatch(resource -> resource.getResourceID().equals(RESOURCE_ID))) {
            return null;
        }

        StocksFetchedData fetchedData = grabStocks();

        Optional<Identification> identification = getIdentificationManager().getIdentification(this);
        Resource<StocksFetchedData> resource = new Resource<>(RESOURCE_ID);
        if(identification.isPresent()) {
            Identification id = identification.get();
            resource = resource
                    .setProvider(id)
                    .setResource(fetchedData);
            return Arrays.asList(resource);
        }
        getContext().logger.getLogger().debug("Created StocksFetchedData resource");
        return Arrays.asList();
    }
}
