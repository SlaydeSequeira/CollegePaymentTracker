package com.example.smartbin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        PieChart pieChart = findViewById(R.id.pieChart);

        // Define your categories and corresponding amounts
        String[] categories = {
                "College Fees", "Exam Fees", "Textbook Cost", "Library Fine",
                "Sports Equipment", "Lab Fees", "Dormitory Rent", "Cafeteria Charges",
                "Transportation Fee", "Uniform Cost", "Graduation Fee", "Student Association Dues"
        };

        int[] amounts = {
                40000, 1000, 5000, 200, 1500, 2500, 8000, 350, 3000, 1200, 6000, 50
        };

        // Calculate the total amount
        int totalAmount = 0;
        for (int amount : amounts) {
            totalAmount += amount;
        }

        // Create PieEntries based on the percentages
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < categories.length; i++) {
            float percentage = (float) amounts[i] / totalAmount * 100;
            entries.add(new PieEntry(percentage, categories[i]));
        }

        // Create a dataset
        PieDataSet dataSet = new PieDataSet(entries, "Expense Categories");
        dataSet.setColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA);

        // Create pie data object
        PieData pieData = new PieData(dataSet);

        // Customize the pie chart
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false); // Disable chart description
        pieChart.setDrawEntryLabels(true); // Show labels for each slice

        // Update the chart
        pieChart.invalidate();
    }
}