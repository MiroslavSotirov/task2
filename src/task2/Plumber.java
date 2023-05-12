package task2;

public class Plumber {
    public static void main(String argv[]) {

        SourceFilter sourceFilter = new SourceFilter("FlightData.dat");
        RemoveFilter removeFilter1 = new RemoveFilter(1);
        RemoveFilter removeFilter5 = new RemoveFilter(5);
        MeasurementConversion measurementConversion = new MeasurementConversion(2);
        TemperatureConversion temperatureConversion = new TemperatureConversion(4);
        PressureWildPoints pressureWildPoints = new PressureWildPoints(3, 10);

        SinkFilter sinkFilter = new SinkFilter(new int[]{0, 4, 2, 3}, "OutputB.dat");
        SinkFilter wildpointsSinkFilter = new SinkFilter(new int[]{0, 3}, "WildPoints.dat");

        sinkFilter.Connect(pressureWildPoints, 0, 0);
        wildpointsSinkFilter.Connect(pressureWildPoints, 0, 1);
        pressureWildPoints.Connect(temperatureConversion, 0, 0);
        temperatureConversion.Connect(measurementConversion, 0, 0);
        measurementConversion.Connect(removeFilter5, 0, 0);
        removeFilter5.Connect(removeFilter1, 0, 0);
        removeFilter1.Connect(sourceFilter, 0, 0);

        sourceFilter.start();
        removeFilter1.start();
        removeFilter5.start();
        measurementConversion.start();
        temperatureConversion.start();
        pressureWildPoints.start();
        sinkFilter.start();
        wildpointsSinkFilter.start();

    }
}