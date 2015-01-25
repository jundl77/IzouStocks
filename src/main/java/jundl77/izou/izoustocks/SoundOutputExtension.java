package jundl77.izou.izoustocks;

import intellimate.izou.events.Event;
import intellimate.izou.output.OutputExtension;
import intellimate.izou.system.Context;
import jundl77.izou.izousound.outputplugin.SoundOutputData;
import jundl77.izou.izousound.outputplugin.SoundOutputPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Output Extension for IzouSound
 */
public class SoundOutputExtension extends OutputExtension<SoundOutputData> {
    /**
     * The ID of the SoundOutputExtension
     */
    public static final String ID = SoundOutputExtension.class.getCanonicalName();

    /**
     * Creates a new output extension for IzouStocks
     *
     * @param context the context of the addOn
     */
    public SoundOutputExtension(Context context) {
        super(ID, context);
        addResourceIdToWishList(StocksContentGenerator.RESOURCE_ID);
        this.setPluginId(SoundOutputPlugin.ID);
    }

    @Override
    public SoundOutputData generate(Event event) {
        StocksFetchedData fetchedData = (StocksFetchedData) event
                .getListResourceContainer()
                .provideResource(StocksContentGenerator.RESOURCE_ID)
                .get(0)
                .getResource();

        List<String> paths = new ArrayList<>();
        paths.add(fetchedData.getSoundPath());

        getContext().logger.getLogger().debug("Converted StocksFetchedData resource to a SoundOutputData object");
        return new SoundOutputData(paths, null, -1, -1);
    }
}
