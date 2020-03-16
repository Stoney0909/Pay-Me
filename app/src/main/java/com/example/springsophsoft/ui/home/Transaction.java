package com.example.springsophsoft.ui.home;

public class Transaction {
    private String reason, recieverid, senderid, amount, date;

    public Transaction(String recieverid, String senderid, String amount, String reason, String date) {
        this.recieverid = recieverid;
        this.senderid = senderid;
        this.amount = amount;
        this.reason = reason;
        this.date = date;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecieverid() {
        return recieverid;
    }

    public void setRecieverid(String recieverid) {
        this.recieverid = recieverid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }
}

