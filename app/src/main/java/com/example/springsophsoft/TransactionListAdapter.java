package com.example.springsophsoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TransactionListAdapter extends ArrayAdapter<Transaction> {
    private static final String TAG = "TransactionListApadter";

    private Context mContext;
    private int mresource;

    public TransactionListAdapter(Context context, int resource, ArrayList<Transaction> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String firstname = getItem(position).getRecieverid();
        String lastname = getItem(position).getSenderid();
        String amount = getItem(position).getAmount();
        String reason = getItem(position).getReason();
        String recieved = getItem(position).getDate();

        Transaction transaction = new Transaction(firstname, lastname, amount, reason, "R");
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mresource, parent, false);

        TextView firstnameview = (TextView) convertView.findViewById(R.id.recieveridtextview);
        TextView lastnameview = (TextView) convertView.findViewById(R.id.senderidtextview);
        TextView amountview = (TextView) convertView.findViewById(R.id.amounttextview);
        TextView reasonView = (TextView) convertView.findViewById(R.id.reasontextView);
        TextView recievedView = (TextView) convertView.findViewById(R.id.dateTextView);

        firstnameview.setText(firstname);
        lastnameview.setText(lastname);
        amount = "$" + amount;
        amountview.setText(amount);
        reasonView.setText(reason);
        recievedView.setText(recieved);

        return convertView;
    }
}

