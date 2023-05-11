package task2;

/**
 * ***************************************************************************************************************
 * File:FahrenheitToClesiusFilter.java
 * <p/>
 * Description:
 * <p/>
 * Converts the Measurement with the given Id from fahrenheit to celsius.
 * The conversion rate we use is: ((Fahrenheit - 32) * (5 / 9.0))
 * <p/>
 * ****************************************************************************************************************
 */

public class FahrenheitToCelsiusFilter extends MeasurementFramework {
    private final int id;

    /**
     * Instantiates a new FeetToMeterFilter object.
     *
     * @param id to remove
     */
    public FahrenheitToCelsiusFilter(int id) {
        super();
        this.id = id;
    }

    /**
     * Converts the Measurement with the given Id from fahrenheit to celsius.
     */
    @Override
    public void run() {

        while (true) {
            try {
                Measurement measurement = readMeasurementIn();

                if (measurement.getId() == this.id) {
                    double fahrenheit = measurement.getMeasurementDouble();
                    double celsius = ((fahrenheit - 32) * (5 / 9.0));
                    measurement.setMeasurement(Double.doubleToLongBits(celsius));
                }

                writeMeasurementOut(measurement);
            } catch (EndOfStreamException e) {
                ClosePorts();
                System.out.print("\n" + this.getName() + "::FahrenheitToCelcius Exiting;");
                break;
            }
        }

    }
}