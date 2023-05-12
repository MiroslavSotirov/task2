package task2;

/**
 * ***************************************************************************************************************
 * File:Plumber.java
 * <p/>
 * Description:
 * <p/>
 * This class instantiates and connects all of our Filters to create the output.
 * <p/>
 * ****************************************************************************************************************
 */
public class Plumber {
    public static void main(String argv[]) {

        SourceFilter sourceFilter = new SourceFilter("FlightData.dat");
        RemoveFilter deleteFilter1 = new RemoveFilter(1);
        RemoveFilter deleteFilter5 = new RemoveFilter(5);
        FeetToMeterFilter feetToMeterFilter = new FeetToMeterFilter(2);
        TemperatureConversion temperatureConversion = new TemperatureConversion(4);
        PressureWildPointsFilter wildpointsFilter = new PressureWildPointsFilter(3, 10);

        SinkFilter sinkFilter = new SinkFilter(new int[]{0, 4, 2, 3}, "OutputB.dat");
        SinkFilter wildpointsSinkFilter = new SinkFilter(new int[]{0, 3}, "WildPoints.dat");

        // connect the filters to each other
        sinkFilter.Connect(wildpointsFilter, 0, 0);
        wildpointsSinkFilter.Connect(wildpointsFilter, 0, 1);
        wildpointsFilter.Connect(temperatureConversion, 0, 0);
        temperatureConversion.Connect(feetToMeterFilter, 0, 0);
        feetToMeterFilter.Connect(deleteFilter5, 0, 0);
        deleteFilter5.Connect(deleteFilter1, 0, 0);
        deleteFilter1.Connect(sourceFilter, 0, 0);

        // start the filters
        sourceFilter.start();
        deleteFilter1.start();
        deleteFilter5.start();
        feetToMeterFilter.start();
        temperatureConversion.start();
        wildpointsFilter.start();
        sinkFilter.start();
        wildpointsSinkFilter.start();

    }
}