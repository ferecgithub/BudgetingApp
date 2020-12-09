package com.ferechamitbeyli.mobileprogramminghw2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ferechamitbeyli.mobileprogramminghw2.R;
import com.ferechamitbeyli.mobileprogramminghw2.model.Transaction;


public class DetailActivity extends AppCompatActivity {

    // Views
    TextView timeTV, dateTV, descriptionTV, amountTV, typeTV;
    Button updateBTN, deleteBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        timeTV = findViewById(R.id.time_tv);
        dateTV = findViewById(R.id.date_tv);
        descriptionTV = findViewById(R.id.description_tv);
        amountTV = findViewById(R.id.amount_tv);
        typeTV = findViewById(R.id.type_tv);
        updateBTN = findViewById(R.id.update_btn);
        deleteBTN = findViewById(R.id.delete_btn);

        Intent intent = getIntent();

        int position = intent.getIntExtra("transactionPosition", 0);

        if (MainActivity.filteredList.isEmpty()) {
            populateViews(MainActivity.myTransactions.get(position));
        } else {
            populateViews(MainActivity.filteredList.get(position));
        }

        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(getApplicationContext(), EditActivity.class);
                updateIntent.putExtra("transactionPositionForUpdate", position);
                startActivity(updateIntent);
                finish();
            }
        });

        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.filteredList.isEmpty()) {
                    MainActivity.myTransactions.remove(position);
                } else {
                    MainActivity.filteredList.remove(position);
                    MainActivity.myTransactions.remove(MainActivity.filteredList.get(position));
                }

                finish();
            }
        });
    }

    public void populateViews(Transaction transaction) {

        timeTV.setText("Time: " + transaction.time);
        dateTV.setText("Date: " + transaction.date);
        descriptionTV.setText("Description: " + transaction.description);
        amountTV.setText("Amount: " + transaction.amount);
        if (transaction.isExpense) {
            typeTV.setText("Type: Expense");
        } else {
            typeTV.setText("Type: Income");
        }
    }


}