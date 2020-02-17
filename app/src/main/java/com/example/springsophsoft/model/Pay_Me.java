package com.example.springsophsoft.model;

import android.provider.BaseColumns;

public final class Pay_Me {
    public final static class Contact implements BaseColumns{
        public static final String TABLE_NAME = "Account_table";
        public static final String _ID = BaseColumns._ID;
        public static final String First_Name = "First_Name";
        public static final String Last_Name = "Last_Name";
        public static final String Username = "Username";
        public static final String Password = "Password";
        public static final Integer Phone_Number = 0;
        public static final String Email = "Email";

    }
//    public final class Cards implements BaseColumns{
//        public static final String TABLE_NAME = "Cards.db";
//        public static final String First_Name = "First_Name";
//        public static final String Last_Name = "Last_Name";
//        public static final String Billing_Address = "Billing_Address";
//        public static final String Billing_State = "Billing_State";
//        public static final String Billing_City = "Billing_City";
//        public static final String Card_Type = "Card_Type";
//        public final  Integer Card_Number = 0;
//        public final  Integer EXPR = 0;
//        public final  Integer CVC = 0;
//    }
//    public final class  Transactions implements BaseColumns{
//        public static final String Transaction_ID = BaseColumns._ID;
//        public static Integer ID = "ID";
//        public static final String Reason = "Reason";
//        public static final Integer Account_ID = 0;
//        public final Integer Transfer_To = 0;
//        public static final String Transfer_To_String = "Transfer To";
//        public static final boolean Lending = false;
//        public static final boolean isStillLending = false;
//        public final Double Amount = 0.00;
//
//    }
//    public final class Balance implements BaseColumns{
//        public final double Balance = 0.00;
//        public final double Owed = 0.00;
//        public final double Lended = 0.00;
//    }
}
