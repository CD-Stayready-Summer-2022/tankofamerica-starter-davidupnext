package com.codedifferently.tankofamerica.domain.transaction.exceptions;


 public class TransactionFailedException extends Exception {
        public TransactionFailedException(String message) {
            super(message);
        }
    }
