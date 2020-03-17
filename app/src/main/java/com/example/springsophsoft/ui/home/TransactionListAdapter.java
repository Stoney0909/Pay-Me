package com.example.springsophsoft.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;

public class TransactionListAdapter extends ArrayAdapter<Transaction> {

    private ArrayList<Transaction> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtReciever;
        TextView txtSender;
        TextView txtAmount;
        TextView txtReason;
        TextView txtDate;


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
            viewHolder.txtReason = (TextView) convertView.findViewById(R.id.reasonTextView);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.DateTextView);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        Databasehelper db = new Databasehelper(mContext);



        String rName = db.getFirstNameByUsername(dataModel.getRecieverid()) + " " + db.getLastNameByUsername(dataModel.getRecieverid());
        String sName = db.getFirstNameByUsername(dataModel.getSenderid()) + " " + db.getLastNameByUsername(dataModel.getSenderid());
        if (rName.equals("null null") || rName.equals("not found not found")|| rName.equals(" not found")){
            viewHolder.txtReciever.setText(dataModel.getRecieverid());
        }
        else{viewHolder.txtReciever.setText(rName);}

        if (sName.equals("null null")|| sName.equals("not found not found") || sName.equals(" not found")) {
            viewHolder.txtSender.setText(dataModel.getSenderid());
        }
        else{viewHolder.txtSender.setText(sName);}


        viewHolder.txtReason.setText(dataModel.getReason());
        viewHolder.txtDate.setText(dataModel.getDate());
        if (dataModel.getSenderid().equals(LogIn.getString()))
        {
            viewHolder.txtAmount.setTextColor(Color.parseColor("#8315D520"));
            String amount = "+$" + dataModel.getAmount();
            viewHolder.txtAmount.setText(amount);
        }
        else{
            viewHolder.txtAmount.setTextColor(Color.parseColor("#FC0000"));
            String amount = "-$" + dataModel.getAmount();
            viewHolder.txtAmount.setText(amount);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}

