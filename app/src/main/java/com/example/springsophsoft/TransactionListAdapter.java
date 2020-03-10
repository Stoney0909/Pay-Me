package com.example.springsophsoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionListAdapter extends ArrayAdapter<Transaction> {

    private ArrayList<Transaction> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtReciever;
        TextView txtSender;
        TextView txtAmount;

    }

    public TransactionListAdapter(ArrayList<Transaction> data, Context context) {
        super(context, R.layout.transactionlist, data);
        this.dataSet = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Transaction dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.transactionlist, parent, false);
            viewHolder.txtReciever = (TextView) convertView.findViewById(R.id.recieverTextView);
            viewHolder.txtSender = (TextView) convertView.findViewById(R.id.senderTextView);
            viewHolder.txtAmount = (TextView) convertView.findViewById(R.id.amountTextView);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtReciever.setText(dataModel.getRecieverid());
        viewHolder.txtSender.setText(dataModel.getSenderid());
        viewHolder.txtAmount.setText(dataModel.getAmount());
        // Return the completed view to render on screen
        return convertView;
    }
}

