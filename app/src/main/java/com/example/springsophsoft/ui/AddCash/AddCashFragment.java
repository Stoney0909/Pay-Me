package com.example.springsophsoft.ui.AddCash;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.springsophsoft.AddingCardNumber;
import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.SignUp;

import java.util.ArrayList;

public class AddCashFragment extends Fragment {

    private AddCashViewModel addCashViewModel;
    private Button btnAddCard;
    CardDBHelper myDB;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> listItem;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addCashViewModel = ViewModelProviders.of(this).get(AddCashViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_cash, container, false);
        Button btnAddCard = (Button)root.findViewById(R.id.btnAddCard);
        ListView listView = (ListView)root.findViewById(R.id.listCard);
        myDB = new CardDBHelper(getActivity());

        listItem = new ArrayList<>();

        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();
            }
        });

        ViewData();
        return root;
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

            toastMessage("You add the card..");
//            while(cursor.moveToNext())
//            {
//                listItem.add(cursor.getString(1));
//            }
//
//            adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listItem);
//            listView.setAdapter(adapter);
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