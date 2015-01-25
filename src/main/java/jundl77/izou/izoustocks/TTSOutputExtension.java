package jundl77.izou.izoustocks;

import intellimate.izou.events.Event;
import intellimate.izou.resource.Resource;
import intellimate.izou.system.Context;
import leanderk.izou.tts.outputextension.TTSData;
import leanderk.izou.tts.outputplugin.TTSOutputPlugin;
import yahoofinance.Stock;

import java.util.HashMap;
import java.util.Locale;

/**
 * TTSOutputExtension for IzouStocks
 */
public class TTSOutputExtension extends leanderk.izou.tts.outputextension.TTSOutputExtension {
    /**
     * The ID of the TTSOutputExtension
     */
    public static final String ID = TTSOutputExtension.class.getCanonicalName();

    /**
     * Creates a new OutputExtension for IzouTTS so that it can communicate with the user
     *
     * @param context the addOn's context
     */
    public TTSOutputExtension(Context context) {
        super(ID, context);
        addResourceIdToWishList(StocksContentGenerator.RESOURCE_ID);
        this.setPluginId(TTSOutputPlugin.ID);
    }

    private String convertCurrency(String currencyShortcut) {
        switch (currencyShortcut) {
            case "EUR":
                return "euros";
            case "USD":
                return "dollars";
            default:
                return currencyShortcut;
        }
    }

    @Override
    public TTSData generateSentence(Event event) {
        Resource<StocksFetchedData> resource = null;
        try {
            //noinspection unchecked
            resource = event
                    .getListResourceContainer()
                    .provideResource(StocksContentGenerator.RESOURCE_ID)
                    .get(0);
        } catch (Exception e) {
            getContext().logger.getLogger().error("Failed to get resource for clockTTS", e);
        }

        if (resource == null) {
            getContext().logger.getLogger().warn("Not able to obtain resource");
            return null;
        }

        StocksFetchedData fetchedData = resource.getResource();
        HashMap<String, Stock> stocks = fetchedData.getStocks();

        String ttsString = "";
        for(String stockName : stocks.keySet()) {
            ttsString += "The course for " + stockName + " is at " + stocks.get(stockName).getQuote().getPrice() +
                    " " + convertCurrency(stocks.get(stockName).getCurrency()) + ". ";
        }

        TTSData ttsData = TTSData.createTTSData(ttsString, getLocale(), 0, ID);
        ttsData.setAfterID("leanderk.izou.tts.commonextensions.WelcomeExtension");

        getContext().logger.getLogger().debug("Converted StocksFetchedData resource to a TTSData object");
        return ttsData;
    }

    @Override
    public boolean canGenerateForLanguage(String locale) {
        if (locale.equals(new Locale("de").getLanguage())) {
            return true;
        } else if (locale.equals(new Locale("en").getLanguage())) {
            return true;
        }
        return false;
    }
}
