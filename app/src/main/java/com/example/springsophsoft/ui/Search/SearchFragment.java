package com.example.springsophsoft.ui.Search;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.HomePage;
import com.example.springsophsoft.R;
import com.example.springsophsoft.SendMoney;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    Databasehelper db;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    private ListView userlist;
    private EditText et;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        et = (EditText)root.findViewById(R.id.myFilter);
        db = new Databasehelper(getActivity());
        userlist = (ListView)root.findViewById(R.id.listView1);
        listItem = new ArrayList<>();
        SimpleCursorAdapter adapter2;

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text=userlist.getItemAtPosition(position).toString();
                Intent intent = new Intent(getActivity(), SendMoney.class);
                intent.putExtra("Person_SendingTo",text);
                startActivity(intent);

             //   toastMessage(text);
            }
        });


        viewData();
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });

        return root;
    }

    public void viewData(){
        Cursor cursor=db.viewData();
        if(cursor.getCount()==0){
            toastMessage("The data is empty.");
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listItem);
            userlist.setAdapter(adapter);
        }
    }

    public void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}