package intellimate.izou.exampleaddon;

import intellimate.izou.output.OutputPlugin;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleOutputPlugin extends OutputPlugin<ExampleOutputData> {
    /*
    ID consists of class_name
     */
    public static final String ID = ExampleOutputExtension.class.getCanonicalName();

    public ExampleOutputPlugin() {
        super(ID);
    }

    @Override
    public void renderFinalOutput() {
        String totalOutput = "";
        for(ExampleOutputData outputData : this.getTDoneList()) {
            totalOutput += outputData.getData() + ", ";
        }
        totalOutput = totalOutput.substring(0, totalOutput.length() - 2);
        System.out.println(totalOutput);
    }
}
