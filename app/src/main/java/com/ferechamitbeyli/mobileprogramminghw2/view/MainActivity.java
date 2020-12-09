package com.ferechamitbeyli.mobileprogramminghw2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.ferechamitbeyli.mobileprogramminghw2.R;
import com.ferechamitbeyli.mobileprogramminghw2.model.Transaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Views
    private RadioButton expenseSearchRB, incomeSearchRB;
    private EditText searchET;
    private RecyclerView listRV;
    private Button addBTN, reportBTN;

    // Adapters
    public ListAdapter listAdapter;

    // Global variables
    public static ArrayList<Transaction> myTransactions  = new ArrayList<>();
    public static ArrayList<Transaction> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseSearchRB = findViewById(R.id.expenseSearch_rb);
        incomeSearchRB = findViewById(R.id.incomeSearch_rb);
        searchET = findViewById(R.id.search_et);
        listRV = findViewById(R.id.list_rv);
        addBTN = findViewById(R.id.add_btn);
        reportBTN = findViewById(R.id.report_btn);


        listRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listAdapter = new ListAdapter(myTransactions, new ListAdapter.OnTransactionListener() {
            @Override
            public void onTransactionClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                intent.putExtra("transactionPosition", position);

                startActivity(intent);
            }
        });

        listRV.setAdapter(listAdapter);

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });

        reportBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ReportActivity.class));
            }
        });

    }

    // Filters the RecyclerView in context of SearchBar and RadioButtons. The filter shows 100 minus to 100 plus range of given value.
    private void filter(String queryText) {
        ArrayList<Transaction> filteredList = new ArrayList<>();
        float value = 0F;
        boolean isSearchingExpense = false;

        if(expenseSearchRB.isChecked()) {
            isSearchingExpense = true;
        } else {
            isSearchingExpense = false;
        }

        if (isNumeric(queryText)) {
            if(!queryText.isEmpty()) { // Fixes the empty SearchBar bug
                value = Float.parseFloat(queryText);
            }

            for(Transaction item : myTransactions) {
                if(item.amount - 100 < value && item.amount + 100 > value && item.isExpense == isSearchingExpense) {
                    filteredList.add(item);
                }

            }
        }


        MainActivity.filteredList = filteredList;

        listAdapter.filterList(filteredList);
    }

    public static boolean isNumeric(String strNum) { // Checks if queryString is number or not
        if (strNum == null) {
            return false;
        }
        try {
            float floatNumber = Float.parseFloat(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        listAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listAdapter.notifyDataSetChanged();
    }
}