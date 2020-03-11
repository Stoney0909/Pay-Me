package com.example.springsophsoft.ui.AddCash;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.springsophsoft.AddingCardNumber;
import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.SendMoney;
import com.example.springsophsoft.TransMoney_card;

import java.util.ArrayList;

public class AddCashFragment extends Fragment {

    private AddCashViewModel addCashViewModel;
    private Button btnAddCard;
    CardDBHelper myDB;
    private ListView listing;
    ArrayAdapter adapter;
    ArrayList<String> listItem;
    public static String cardNumber;
    public static String CardBalance;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addCashViewModel = ViewModelProviders.of(this).get(AddCashViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_cash, container, false);
        btnAddCard = (Button)root.findViewById(R.id.btnAddCard);
        listing = (ListView)root.findViewById(R.id.listCard);
        myDB = new CardDBHelper(getActivity());

        listItem = new ArrayList<>();

        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();
            }
        });

        listing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = listing.getItemAtPosition(position).toString();
                String balance = myDB.getCardBalance((position + 1));
                cardNumber = text;
                CardBalance = balance;
                Intent intent = new Intent(getActivity(), TransMoney_card.class);
                startActivity(intent);
            }
        });

        ViewData();
        return root;
    }

    public static int getCardBalance()
    {
        String CardB = CardBalance;
        int ConvertC = Integer.parseInt(CardB);
        return ConvertC;
    }

    public static String getCard()
    {
        String card;
        card = cardNumber;
        return cardNumber;
    }


    public void ViewData()
    {
        Cursor cursor = myDB.getCardList();

        if(cursor.getCount() == 0)
        {
            toastMessage("You haven't add any card yet.");
        }
        else
        {
            while(cursor.moveToNext())
            {
                listItem.add(cursor.getString(1));

            }
            adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listItem);
            listing.setAdapter(adapter);
        }
    }

    public void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

  public void addCard()
  {
      Intent intent = new Intent(getActivity(), AddingCardNumber.class);
      startActivity(intent);
  }

}