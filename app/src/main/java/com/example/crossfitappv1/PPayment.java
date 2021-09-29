package com.example.crossfitappv1;

public class PPayment {
    String pname;
    String amount;
    String holdername;
    String cNo;
    String Edate;
    String Cvn;

    public PPayment(String pname, String amount, String holdername, String cNo, String edate, String cvn) {
        this.pname = pname;
        this.amount = amount;
        this.holdername = holdername;
        this.cNo = cNo;
        Edate = edate;
        Cvn = cvn;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHoldername() {
        return holdername;
    }

    public void setHoldername(String holdername) {
        this.holdername = holdername;
    }

    public String getcNo() {
        return cNo;
    }

    public void setcNo(String cNo) {
        this.cNo = cNo;
    }

    public String getEdate() {
        return Edate;
    }

    public void setEdate(String edate) {
        Edate = edate;
    }

    public String getCvn() {
        return Cvn;
    }

    public void setCvn(String cvn) {
        Cvn = cvn;
    }
//    public PPayment(String pname, String amount, String holdername, String cNo, String edate, String cvn) {
//        this.pname = pname;
//        this.amount = amount;
//        this.holdername = holdername;
//        this.cNo = cNo;
//        Edate = edate;
//        this.Cvn = cvn;
//    }
//
//    public String getPname() {
//        return pname;
//    }
//
//    public void setPname(String pname) {
//        this.pname = pname;
//    }
//
//    public String getAmount() {
//        return amount;
//    }
//
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }
//
//    public String getHoldername() {
//        return holdername;
//    }
//
//    public void setHoldername(String holdername) {
//        this.holdername = holdername;
//    }
//
//    public String getcNo() {
//        return cNo;
//    }
//
//    public void setcNo(String cNo) {
//        this.cNo = cNo;
//    }
//
//    public String getEdate() {
//        return Edate;
//    }
//
//    public void setEdate(String edate) {
//        Edate = edate;
//    }
//
//    public String getCvn() {
//        return Cvn;
//    }
//
//    public void setCvn(String cvn) {
//        cvn = cvn;
//    }
//
}
