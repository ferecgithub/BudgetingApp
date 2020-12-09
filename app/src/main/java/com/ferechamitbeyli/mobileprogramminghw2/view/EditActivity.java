package com.ferechamitbeyli.mobileprogramminghw2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.ferechamitbeyli.mobileprogramminghw2.R;
import com.ferechamitbeyli.mobileprogramminghw2.model.Transaction;

import java.util.ArrayList;


public class EditActivity extends AppCompatActivity {

    // Views
    RadioButton expenseRB, incomeRB;
    EditText dateET, timeET, descriptionET, amountET;
    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        expenseRB = findViewById(R.id.update_expense_rb);
        incomeRB = findViewById(R.id.update_income_rb);
        timeET = findViewById(R.id.update_time_et);
        dateET = findViewById(R.id.update_date_et);
        descriptionET = findViewById(R.id.update_description_et);
        amountET = findViewById(R.id.update_amount_et);
        saveBtn = findViewById(R.id.save_btn);

        Intent updateIntent = getIntent();

        int position = updateIntent.getIntExtra("transactionPositionForUpdate", 0);


        if (MainActivity.filteredList.isEmpty()) {
            populateViews(MainActivity.myTransactions.get(position));
        } else {
            populateViews(MainActivity.filteredList.get(position));
        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!MainActivity.filteredList.isEmpty()) {
                    updateRecord(MainActivity.filteredList, position);
                }
                updateRecord(MainActivity.myTransactions, position);

                finish();

            }
        });

    }

    public void updateRecord(ArrayList<Transaction> arrayList, int position) {
        if(expenseRB.isChecked()) {
            arrayList.get(position).isExpense = true;
        } else if (incomeRB.isChecked()) {
            arrayList.get(position).isExpense = false;
        }

        arrayList.get(position).time = timeET.getText().toString();
        arrayList.get(position).date = dateET.getText().toString();
        arrayList.get(position).description = descriptionET.getText().toString();
        arrayList.get(position).amount = Float.parseFloat(amountET.getText().toString());
    }

    public void populateViews(Transaction transaction) {

        timeET.setText(transaction.time);
        dateET.setText(transaction.date);
        descriptionET.setText(transaction.description);
        amountET.setText(String.valueOf(transaction.amount));
        if (transaction.isExpense) {
            expenseRB.setChecked(true);
            incomeRB.setChecked(false);
        } else {
            incomeRB.setChecked(true);
            expenseRB.setChecked(false);
        }
    }

}