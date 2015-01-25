package jundl77.izou.izoustocks;

import intellimate.izou.activator.Activator;
import intellimate.izou.addon.AddOn;
import intellimate.izou.addon.AddOnManager;
import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.EventsController;
import intellimate.izou.output.OutputExtension;
import intellimate.izou.output.OutputPlugin;
import ro.fortsoft.pf4j.Extension;

import java.io.File;

/**
 * The Stocks AddOn is a addOn that gets stock prices and outputs them via IzouTTS and IzouSound
 */
@Extension
public class StocksAddOn extends AddOn {
    /**
     * The ID of the addOn
     */
    public static final String ID = StocksAddOn.class.getCanonicalName();

    /**
     * The local data path where external data should be stored (sound files etc.)
     */
    public static final String ADDON_DATA_PATH_LOCAL = AddOnManager.ADDON_DATA_PATH + "IzouStocks" + File.separator;

    /**
     * Creates a new StocksAddOn
     */
    public StocksAddOn() {
        super(ID);
    }

    @Override
    public void prepare() {
        File newDir = new File(ADDON_DATA_PATH_LOCAL.substring(0, ADDON_DATA_PATH_LOCAL.length() - 1));

        if (!newDir.exists()) {
            try {
                newDir.mkdir();
            } catch (SecurityException e) {
                getContext().logger.getLogger().warn("Unable to make IzouClock resource directory", e);
            }
        }
    }

    @Override
    public Activator[] registerActivator() {
        return null;
    }

    @Override
    public ContentGenerator[] registerContentGenerator() {
        ContentGenerator[] contentGenerators = new ContentGenerator[1];
        contentGenerators[0] = new StocksContentGenerator(getContext());
        return contentGenerators;
    }

    @Override
    public EventsController[] registerEventController() {
        return null;
    }

    @Override
    public OutputPlugin[] registerOutputPlugin() {
        return null;
    }

    @Override
    public OutputExtension[] registerOutputExtension() {
        OutputExtension[] outputExtensions = new OutputExtension[2];
        outputExtensions[0] = new SoundOutputExtension(getContext());
        outputExtensions[1] = new TTSOutputExtension(getContext());
        return outputExtensions;
    }
}
