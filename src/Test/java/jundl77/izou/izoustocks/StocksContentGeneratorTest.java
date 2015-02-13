package jundl77.izou.izoustocks;

import intellimate.izou.events.Event;
import intellimate.izou.main.Main;
import intellimate.izou.properties.PropertiesContainer;
import intellimate.izou.system.Context;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;

public class StocksContentGeneratorTest {
    @Test
    public void testAnnounceResources() {
        StocksContentGenerator stocksContentGenerator = new StocksContentGenerator(new MockStocksContext());
        LocalTime before = LocalTime.now();
        stocksContentGenerator.provideResource(stocksContentGenerator.announceResources(), Optional.<Event>empty());
        //without List: 3, 4, 3, 4 Seconds
        //without < 0
        System.out.println(ChronoUnit.SECONDS.between(before, LocalTime.now()));
    }
    
    private class MockStocksContext extends Context {
        public MockStocksContext() {
            super(new StocksAddOn(), new Main(Arrays.asList(), true), "DEBUG");
            Context context = this;
            properties = new Properties() {
                @Override
                public PropertiesContainer getPropertiesContainer() {
                    return new PropertiesContainer(context) {
                        @Override
                        public java.util.Properties getProperties() {
                            java.util.Properties properties1 = new java.util.Properties();
                            properties1.setProperty("Google", "GOOG");
                            properties1.setProperty("Apple", "AAPL");
                            properties1.setProperty("Euro_to_dollar", "EURUSD=X");
                            properties1.setProperty("Intel", "INTC");
                            properties1.setProperty("Dow Jones Industrial Average", "DJI");
                            properties1.setProperty("BMW", "BMW.DE");
                            properties1.setProperty("Facebook", "FB");
                            return properties1;
                        }
                    };
                }
            };
        }
    }
}