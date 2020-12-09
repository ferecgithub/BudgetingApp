package com.ferechamitbeyli.mobileprogramminghw2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.ferechamitbeyli.mobileprogramminghw2.R;
import com.ferechamitbeyli.mobileprogramminghw2.model.Transaction;

import java.util.ArrayList;
import java.util.Arrays;

public class ReportActivity extends AppCompatActivity {

    private ImageView income1, income2, income3, income4, income5, income6, income7, income8, income9, income10, income11, income12,
            expense1, expense2, expense3, expense4, expense5, expense6, expense7, expense8, expense9, expense10, expense11, expense12;


    String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        setViews();

        ImageView[] bars = {expense1 ,income1 , expense2, income2, expense3, income3, expense4, income4, expense5 , income5, expense6, income6,
                expense7, income7, expense8, income8, expense9, income9, expense10, income10, expense11, income11, expense12, income12};

        int fullHeight = 700; // The height of graph can be adjusted by this value.

        float[] monthlyExpenses = {0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F};
        float[] monthlyIncomes = {0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F};
        float[] allTransactions;
        float[] graphHeight = {0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F};

        ArrayList<Transaction> tempList = MainActivity.myTransactions;

        calculateTransactions(tempList, monthlyExpenses, monthlyIncomes); // Got expenses and incomes

        System.out.println(Arrays.toString(monthlyExpenses));
        System.out.println(Arrays.toString(monthlyIncomes));

        allTransactions = new float[monthlyExpenses.length + monthlyIncomes.length];

        for(int i = 0; i < 24; i += 2) { // Reordering the concatenated array as "expense, income"
            allTransactions[i] = monthlyExpenses[i/2];
            allTransactions[i + 1] = monthlyIncomes[i/2];

        }

        System.out.println(Arrays.toString(allTransactions));

        calculateGraphHeights(graphHeight, allTransactions);

        System.out.println(Arrays.toString(graphHeight));

        setGraphs(bars, graphHeight, fullHeight);

        System.out.println(Arrays.toString(graphHeight));

    }

    // The year difference did not be covered in this function. Just months are taken in consideration.
    public void calculateTransactions(ArrayList<Transaction> arrayList, float[] expenses, float[] incomes) {

        for (int i = 0; i < arrayList.size(); i++) {

            for (int j = 0; j < expenses.length; j++) {
                if (getMonth(arrayList.get(i).date).equals(months[j]) && arrayList.get(i).isExpense) {
                    expenses[j] += arrayList.get(i).amount;
                } else if (getMonth(arrayList.get(i).date).equals(months[j]) && !arrayList.get(i).isExpense) {
                    incomes[j] += arrayList.get(i).amount;
                }

            }

        }

    }

    public void calculateGraphHeights(float[] graphHeight, float[] allTransactions) {
        for (int i = 0; i < 24; i += 2) {
            graphHeight[i] = getBarGraphHeightPercentage(allTransactions[i], allTransactions[i + 1]); // Expense height
            graphHeight[i + 1] = (1 - getBarGraphHeightPercentage(allTransactions[i], allTransactions[i + 1])); // Income height
        }
    }

    public void setGraphs(ImageView[] barList, float[] graphHeight, int fullHeight) {
        for (int i = 0; i < 24; i += 2) {

            barList[i].getLayoutParams().height = (int) (fullHeight * graphHeight[i]); // Expense graph
            barList[i].requestLayout();
            barList[i + 1].getLayoutParams().height = (int) (fullHeight * graphHeight[i + 1]); // Income graph
            barList[i + 1].requestLayout();

        }
    }

    public String getMonth(String date) { // Getting the month string
        String[] splittedArray = date.split("\\.");
        return splittedArray[1];
    }

    public float getBarGraphHeightPercentage(Float expense, Float income) { // Getting percentage of expense (without multiplying with 100 for easier calculation later on)
        float totalValue = expense + income;

        return (expense / totalValue);
    }

    public void setViews() {
        income1 = findViewById(R.id.income1);
        income2 = findViewById(R.id.income2);
        income3 = findViewById(R.id.income3);
        income4 = findViewById(R.id.income4);
        income5 = findViewById(R.id.income5);
        income6 = findViewById(R.id.income6);
        income7 = findViewById(R.id.income7);
        income8 = findViewById(R.id.income8);
        income9 = findViewById(R.id.income9);
        income10 = findViewById(R.id.income10);
        income11 = findViewById(R.id.income11);
        income12 = findViewById(R.id.income12);

        expense1 = findViewById(R.id.expense1);
        expense2 = findViewById(R.id.expense2);
        expense3 = findViewById(R.id.expense3);
        expense4 = findViewById(R.id.expense4);
        expense5 = findViewById(R.id.expense5);
        expense6 = findViewById(R.id.expense6);
        expense7 = findViewById(R.id.expense7);
        expense8 = findViewById(R.id.expense8);
        expense9 = findViewById(R.id.expense9);
        expense10 = findViewById(R.id.expense10);
        expense11 = findViewById(R.id.expense11);
        expense12 = findViewById(R.id.expense12);
    }
}