package longah.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChartTest {
    /**
     * Tests the successful creation of a bar chart with valid X and Y axis inputs.
     */
    @Test
    public void viewBalancesBarChart_validInput_success() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList(10.00, -5.00, 0.00);
            Chart chart = Chart.viewBalancesBarChart(members, balances);

            assert chart != null;
            assertEquals(chart.getClass(), Chart.class);
            assertEquals(chart instanceof Chart, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests the unsuccessful creation of a bar chart with invalid X and Y axis inputs.
     */
    @Test
    public void viewBalancesBarChart_invalidInput_exceptionThrown() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList(10.00, -5.00);
            Chart.viewBalancesBarChart(members, balances);
            fail();
        } catch (Exception e) {
            assertEquals("X and Y-Axis sizes are not the same!!!", e.getMessage());
        }
    }

    /**
     * Tests the successful creation of a bar chart with MIN_VALUE balances.
     */
    @Test
    public void viewBalancesBarChart_minValue_success() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
            Chart chart = Chart.viewBalancesBarChart(members, balances);

            assert chart != null;
            assertEquals(chart.getClass(), Chart.class);
            assertEquals(chart instanceof Chart, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests the successful creation of a bar chart with NaN balances.
     */
    @Test
    public void viewBalancesBarChart_nan_success() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList(Double.NaN, Double.NaN, Double.NaN);
            Chart chart = Chart.viewBalancesBarChart(members, balances);

            assert chart != null;
            assertEquals(chart.getClass(), Chart.class);
            assertEquals(chart instanceof Chart, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests the unsuccessful creation of a bar chart with no members.
     */
    @Test
    public void viewBalancesBarChart_noMembers_exceptionThrown() {
        try {
            List<String> members = Arrays.asList();
            List<Double> balances = Arrays.asList(10.00, -5.00, 0.00);
            Chart.viewBalancesBarChart(members, balances);
            fail();
        } catch (Exception e) {
            assertEquals("X-Axis data cannot be empty!!!", e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a bar chart with no balances.
     */
    @Test
    public void viewBalancesBarChart_noBalances_exceptionThrown() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList();
            Chart.viewBalancesBarChart(members, balances);
            fail();
        } catch (Exception e) {
            assertEquals("Y-Axis data cannot be empty!!!", e.getMessage());
        }
    }

    /**
     * Tests the successful creation of a bar chart with negative balances.
     */
    @Test
    public void viewBalancesBarChart_negativeBalances_success() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList(-10.00, -20.00, -30.00);
            Chart chart = Chart.viewBalancesBarChart(members, balances);

            assert chart != null;
            assertEquals(chart.getClass(), Chart.class);
            assertEquals(chart instanceof Chart, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests the successful creation of a bar chart with zero balances.
     */
    @Test
    public void viewBalancesBarChart_zeroBalances_success() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList(0.00, 0.00, 0.00);
            Chart chart = Chart.viewBalancesBarChart(members, balances);

            assert chart != null;
            assertEquals(chart.getClass(), Chart.class);
            assertEquals(chart instanceof Chart, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
