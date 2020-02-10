package com.example.springsophsoft.model;

import android.provider.BaseColumns;

public final class Database_Module {
    public final static class Contacts implements BaseColumns{
        private static final String TABLE_NAME = "Account_table";
        private static final String _ID = BaseColumns._ID;
        private static final String First_Name = "First_Name";
        private static final String Last_Name = "Last_Name";
        private static final String Username = "Username";
        private static final String Password = "Password";
        private static final Double Balance = 0.00;
        private static final Integer Phone_Number = 0;
        private static final String Email = "Email";

    }
    public final class Cards implements BaseColumns{
        private static final String TABLE_NAME = "Cards.db";
        private static final String First_Name = "First_Name";
        private static final String Last_Name = "Last_Name";
        private static final String Billing_Address = "Billing_Address";
        private static final String Billing_State = "Billing_State";
        private static final String Card_Type = "Card_Type";
        private final  Integer Card_Number = 0;
        private final  Integer EXPR = 0;
        private final  Integer CVC = 0;
    }
    public final class  Transactions implements BaseColumns{
        private static final String Reason = "Reason";
        private static final String Account_ID = BaseColumns._ID;
        private static final String Transfer_To = BaseColumns._ID;
        private static final String Transfer_To_String = "Transfer To";
        private static final boolean Lending = false;
        private final Double Amount = 0.00;

    }
}
