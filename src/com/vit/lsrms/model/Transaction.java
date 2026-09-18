package com.vit.lsrms.model;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String studentRegNo;
    private String bookId;
    private String type; // "ISSUE" or "RETURN"
    private String timestamp;

    public Transaction(String transactionId, String studentRegNo, String bookId, String type) {
        this.transactionId = transactionId;
        this.studentRegNo = studentRegNo;
        this.bookId = bookId;
        this.type = type;
        this.timestamp = LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] TxID: " + transactionId + " | Student: " + studentRegNo + " | Book: " + bookId + " | Action: " + type;
    }
}
