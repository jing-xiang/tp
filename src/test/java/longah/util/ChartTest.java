package longah.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            List<Double> balances = Arrays.asList(10.0, -5.0, 0.0);
            Chart chart = Chart.viewBalancesBarChart(members, balances);

            assert chart != null;
            assertEquals(chart.getClass(), Chart.class);
            assertEquals(chart instanceof Chart, true);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful creation of a bar chart with invalid X and Y axis inputs.
     */
    @Test
    public void viewBalancesBarChart_invalidInput_exceptionThrown() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList(10.0, -5.0);
            Chart.viewBalancesBarChart(members, balances);
            fail();
        } catch (Exception e) {
            assertEquals("X and Y-Axis sizes are not the same!!!", e.getMessage());
        }
    }

    /**
     * Tests the successful creation of a bar chart with MAX_VALUE balances.
     */
    @Test
    public void viewBalancesBarChart_maxValue_success() {
        try {
            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
            List<Double> balances = Arrays.asList(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
            Chart chart = Chart.viewBalancesBarChart(members, balances);

            assert chart != null;
            assertEquals(chart.getClass(), Chart.class);
            assertEquals(chart instanceof Chart, true);
        } catch (Exception e) {
            fail();
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
            fail();
        }
    }

    //    /**
    //     * Tests the successful creation of a bar chart with NaN balances.
    //     */
    //    @Test
    //    public void viewBalancesBarChart_nan_success() {
    //        try {
    //            List<String> members = Arrays.asList("Alice", "Bob", "Charlie");
    //            List<Double> balances = Arrays.asList(Double.NaN, Double.NaN, Double.NaN);
    //            Chart chart = Chart.viewBalancesBarChart(members, balances);
    //
    //            assert chart != null;
    //            assertEquals(chart.getClass(), Chart.class);
    //            assertEquals(chart instanceof Chart, true);
    //        } catch (Exception e) {
    //            fail();
    //        }
    //    }

    /**
     * Tests the unsuccessful creation of a bar chart with no members.
     */
    @Test
    public void viewBalancesBarChart_noMembers_exceptionThrown() {
        try {
            List<String> members = Arrays.asList();
            List<Double> balances = Arrays.asList(10.0, -5.0, 0.0);
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
     * Tests the successful creation of a bar chart with a high number of members and balances.
     */
    @Test
    public void viewBalancesBarChart_maxMembersBalances_success() {
        try {
            List<String> members = Arrays.asList(
                    "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace",
                    "Hannah", "Isaac", "Jack", "Katherine", "Liam", "Mia", "Nathan",
                    "Olivia", "Peter", "Quinn", "Rachel", "Sam", "Tina", "Ulysses",
                    "Victoria", "Walter", "Xena", "Yvonne", "Zach"
            );

            // Create a list with 26 random values for balances
            List<Double> balances = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 26; i++) {
                balances.add(random.nextDouble() * 100); // Assuming balances are in the range of 0 to 100
            }

            // Create the chart
            Chart chart = Chart.viewBalancesBarChart(members, balances);

            // Assert the chart is not null and is an instance of Chart
            assert chart != null;
            assertEquals(chart.getClass(), Chart.class);
            assertEquals(chart instanceof Chart, true);
        } catch (Exception e) {
            fail();
        }
    }
}
