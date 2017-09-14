package de.shokhor.costs.model;

public enum TypeCashAccountsAndCards {

    Cash{
        @Override
        public String toString() {
            return "Cash";
        }
    },
    PayPal{
        @Override
        public String toString() {
            return "PayPal";
        }
    },
    Credit_Card{
        @Override
        public String toString() {
            return "Credit card";
        }
    },
    Debit_Card{
        @Override
        public String toString() {
            return "Debit card";
        }
    },
    Bank_Account{
        @Override
        public String toString() {
            return "Bank account";
        }
    },
    Other{
        @Override
        public String toString() {
            return "Other";
        }
    };
}
