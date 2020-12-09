package com.ferechamitbeyli.mobileprogramminghw2.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferechamitbeyli.mobileprogramminghw2.R;
import com.ferechamitbeyli.mobileprogramminghw2.model.Transaction;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private ArrayList<Transaction> transactionList;
    private OnTransactionListener mOnTransactionListener;

    public ListAdapter(ArrayList<Transaction> transactionList, OnTransactionListener onTransactionListener) {
        this.transactionList = transactionList;
        this.mOnTransactionListener = onTransactionListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTransactionListener.onTransactionClick(myViewHolder.getAdapterPosition());
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date_tv.setText(transactionList.get(position).date);
        holder.amount_tv.setText(String.valueOf(transactionList.get(position).amount));
        holder.description_tv.setText(transactionList.get(position).description);
        if(transactionList.get(position).isExpense) {
            holder.type_tv.setText("Expense");
            holder.type_tv.setTextColor(Color.RED);
        } else {
            holder.type_tv.setText("Income");
            holder.type_tv.setTextColor(Color.GREEN);
        }

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void filterList(ArrayList<Transaction> filteredList) {

        if(!filteredList.isEmpty()) {
            transactionList = filteredList;
        } else {
            transactionList = MainActivity.myTransactions;
        }

        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView date_tv;
        private TextView amount_tv;
        private TextView description_tv;
        private TextView type_tv;


        public MyViewHolder(@NonNull View view) {
            super(view);

            date_tv = view.findViewById(R.id.item_date_tv);
            amount_tv = view.findViewById(R.id.item_amount_tv);
            description_tv = view.findViewById(R.id.item_description_tv);
            type_tv = view.findViewById(R.id.item_type_tv);

        }

    }

    public interface OnTransactionListener {
        void onTransactionClick(int position);
    }
}
