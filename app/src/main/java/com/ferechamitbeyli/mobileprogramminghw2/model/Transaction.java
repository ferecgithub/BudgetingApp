package com.ferechamitbeyli.mobileprogramminghw2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    public String time, date, description;
    public float amount;
    public boolean isExpense;

    public Transaction() {}

    public Transaction(String time, String date, String description, Float amount, Boolean isExpense) {
        this.time = time;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    protected Transaction(Parcel in) {
        time = in.readString();
        date = in.readString();
        description = in.readString();
        amount = in.readFloat();
        isExpense = in.readByte() != 0;
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    @Override
    public String toString() {
        if(isExpense) return time + "\t\t\t" + date + "\t\t\t" + amount + "\t\t\t\t\t\t\t" + description + "\t\t\tExpense";
        else if(!isExpense) return time + "\t\t\t" + date + "\t\t\t" + amount + "\t\t\t\t\t\t\t" + description + "\t\t\tIncome";
        else return time + "\t\t\t" + date + "\t\t\t" + amount + "\t\t\t\t\t\t\t" + description + "\t\t\tNot defined!";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(description);
        dest.writeFloat(amount);
        dest.writeByte((byte) (isExpense ? 1 : 0));
    }
}
