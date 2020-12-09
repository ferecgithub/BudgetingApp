package com.ferechamitbeyli.mobileprogramminghw2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.ferechamitbeyli.mobileprogramminghw2.R;
import com.ferechamitbeyli.mobileprogramminghw2.model.Transaction;

import java.util.ArrayList;


public class AddActivity extends AppCompatActivity {

    // Views
    RadioButton expenseRB, incomeRB;
    EditText dateET, timeET, descriptionET, amountET;
    Button addBtn, addDummyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        expenseRB = findViewById(R.id.add_expense_rb);
        incomeRB = findViewById(R.id.add_income_rb);
        dateET = findViewById(R.id.add_date_et);
        timeET = findViewById(R.id.add_time_et);
        descriptionET = findViewById(R.id.add_description_et);
        amountET = findViewById(R.id.add_amount_et);
        addBtn = findViewById(R.id.add_btn);
        addDummyBtn = findViewById(R.id.addDummy_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!MainActivity.filteredList.isEmpty()) {
                    addRecord(MainActivity.filteredList);
                }
                addRecord(MainActivity.myTransactions);

                finish();
            }
        });

        addDummyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArrayList(MainActivity.myTransactions);
                finish();
            }
        });
    }

    public void setArrayList(ArrayList<Transaction> arrayList) {
        // Dummy elements separated by months

        // Jan
        arrayList.add(new Transaction("01:00", "04.01.2020", "Zara Mont alındı", 89.90F, true));
        arrayList.add(new Transaction("01:58", "14.01.2020", "Emlak Geliri", 899.00F, false));
        // Feb
        arrayList.add(new Transaction("13:02", "25.02.2020", "Lewis Kot alındı", 175.90F, true));
        arrayList.add(new Transaction("14:01", "04.02.2020", "Zara Mont satıldı", 89.90F, false));
        // Mar
        arrayList.add(new Transaction("14:58", "14.03.2020", "Ev Satıldı ", 55899.00F, false));
        arrayList.add(new Transaction("15:23", "25.03.2020", "Lewis Ceket alındı", 185.90F, true));
        // Apr
        arrayList.add(new Transaction("02:00", "04.04.2020", "Araba Satıldı", 1289.90F, true));
        arrayList.add(new Transaction("03:53", "14.04.2020", "Emlak Geliri", 899.00F, false));
        // May
        arrayList.add(new Transaction("13:02", "25.05.2020", "Lewis Kot", 75.90F, true));
        arrayList.add(new Transaction("15:05", "04.05.2020", "Zara Mont", 289.90F, true));
        // Jun
        arrayList.add(new Transaction("02:58", "14.06.2020", "Emlak Geliri", 899.00F, false));
        arrayList.add(new Transaction("13:07", "25.06.2020", "Lewis Kot", 175.90F, true));
        // Jul
        arrayList.add(new Transaction("11:40", "04.07.2020", "Zara Mont", 89.90F, true));
        arrayList.add(new Transaction("14:38", "14.07.2020", "Çikolata Sattım", 59.00F, false));
        // Aug
        arrayList.add(new Transaction("06:32", "25.08.2020", "Pazar arabası aldım", 85.90F, true));
        arrayList.add(new Transaction("14:38", "14.08.2020", "Emlak Geliri", 899.00F, false));
        // Sep
        arrayList.add(new Transaction("06:32", "25.09.2020", "Laptop aldım", 8275.90F, true));
        arrayList.add(new Transaction("14:38", "14.09.2020", "Limon Sattım", 19.50F, false));
        // Oct
        arrayList.add(new Transaction("06:32", "25.10.2020", "Erik aldım", 6.50F, true));
        arrayList.add(new Transaction("14:38", "14.10.2020", "Emlak Geliri", 899.00F, false));
        // Nov
        arrayList.add(new Transaction("06:32", "25.11.2020", "Kırtasiye Alışverişi", 120.80F, true));
        arrayList.add(new Transaction("14:38", "14.11.2020", "Gömlek Sattım", 19.00F, false));
        // Dec
        arrayList.add(new Transaction("06:32", "25.12.2020", "LCW Sweatshirt aldım", 75.90F, true));
        arrayList.add(new Transaction("14:38", "14.12.2020", "Emlak Geliri", 899.00F, false));
    }

    public void addRecord(ArrayList<Transaction> arrayList) {
        boolean isExpenseSelected = false;

        if (expenseRB.isChecked()) {
            isExpenseSelected = true;
        } else {
            isExpenseSelected = false;
        }

        arrayList.add(new Transaction(
                timeET.getText().toString(),
                dateET.getText().toString(),
                descriptionET.getText().toString(),
                Float.parseFloat(amountET.getText().toString()),
                isExpenseSelected));

    }
}