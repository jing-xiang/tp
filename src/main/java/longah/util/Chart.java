package longah.util;

import org.knowm.xchart.AnnotationTextPanel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;


import javax.swing.JFrame;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.awt.Font;

public class Chart {

    /**
     * Constructor for Chart.
     *
     * @param chart The chart to be displayed.
     */
    public Chart(CategoryChart chart) {
        JFrame frame = new SwingWrapper(chart).displayChart();
        javax.swing.SwingUtilities.invokeLater(
                ()->frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }

    /**
     * Create and display a bar chart.
     *
     * @param members  List of member names or categories.
     * @param balances List of member balances corresponding to the labels.
     * @return
     */
    public static Chart viewBalancesBarChart(List<String> members, List<Double> balances) {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Member Balances")
                .xAxisTitle("Members").yAxisTitle("Balances").theme(Styler.ChartTheme.GGPlot2).build();
        chart.getStyler().setLabelsRotation(1);

        List<Double> positiveBalances = new ArrayList<>();
        List<Double> negativeBalances = new ArrayList<>();
        for (Double balance : balances) {
            if (balance >= 0) {
                positiveBalances.add(balance);
                negativeBalances.add(null); // Null value to maintain position for negative balances
            } else {
                positiveBalances.add(null); // Null value to maintain position for positive balances
                negativeBalances.add(balance);
            }
        }

        chart.addSeries("Positive Balances", members, positiveBalances)
                .setFillColor(Color.GREEN);
        chart.addSeries("Negative Balances", members, negativeBalances)
                .setFillColor(Color.RED);

        // set tooltips
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setToolTipsAlwaysVisible(true);
        chart.getStyler().setToolTipFont( new Font("Verdana", Font.BOLD, 12));
        chart.getStyler().setToolTipHighlightColor(Color.BLUE);
        chart.getStyler().setToolTipBorderColor(Color.BLACK);
        chart.getStyler().setToolTipBackgroundColor(Color.ORANGE);
        chart.getStyler().setToolTipType(Styler.ToolTipType.yLabels);

        // set annotations for recommended command
        chart.addAnnotation(
                new AnnotationTextPanel(
                        "Use the command 'list debts' \n to find out the best \n way to solve your debts!",
                        800,
                        600,
                        true));
        chart.getStyler().setAnnotationTextPanelPadding(20);
        chart.getStyler().setAnnotationTextPanelFont(new Font("Verdana", Font.BOLD, 12));
        chart.getStyler().setAnnotationTextPanelBackgroundColor(Color.DARK_GRAY);
        chart.getStyler().setAnnotationTextPanelFontColor(Color.LIGHT_GRAY);

        return new Chart(chart);
    }
}
