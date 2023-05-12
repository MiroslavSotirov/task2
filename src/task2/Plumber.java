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
        RemoveFilter removeFilter1 = new RemoveFilter(1);
        RemoveFilter removeFilter5 = new RemoveFilter(5);
        MeasurementConversion measurementConversion = new MeasurementConversion(2);
        TemperatureConversion temperatureConversion = new TemperatureConversion(4);
        PressureWildPointsFilter wildpointsFilter = new PressureWildPointsFilter(3, 10);

        SinkFilter sinkFilter = new SinkFilter(new int[]{0, 4, 2, 3}, "OutputB.dat");
        SinkFilter wildpointsSinkFilter = new SinkFilter(new int[]{0, 3}, "WildPoints.dat");

        // connect the filters to each other
        sinkFilter.Connect(wildpointsFilter, 0, 0);
        wildpointsSinkFilter.Connect(wildpointsFilter, 0, 1);
        wildpointsFilter.Connect(temperatureConversion, 0, 0);
        temperatureConversion.Connect(measurementConversion, 0, 0);
        measurementConversion.Connect(removeFilter5, 0, 0);
        removeFilter5.Connect(removeFilter1, 0, 0);
        removeFilter1.Connect(sourceFilter, 0, 0);

        // start the filters
        sourceFilter.start();
        removeFilter1.start();
        removeFilter5.start();
        measurementConversion.start();
        temperatureConversion.start();
        wildpointsFilter.start();
        sinkFilter.start();
        wildpointsSinkFilter.start();

    }
}