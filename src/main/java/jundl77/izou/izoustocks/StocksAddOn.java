package jundl77.izou.izoustocks;

import intellimate.izou.activator.Activator;
import intellimate.izou.addon.AddOn;
import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.EventsController;
import intellimate.izou.output.OutputExtension;
import intellimate.izou.output.OutputPlugin;
import ro.fortsoft.pf4j.Extension;

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
     * Creates a new StocksAddOn
     */
    public StocksAddOn() {
        super(ID);
    }

    @Override
    public void prepare() {

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
        OutputExtension[] outputExtensions = new OutputExtension[1];
        outputExtensions[0] = new TTSOutputExtension(getContext());
        return outputExtensions;
    }
}
