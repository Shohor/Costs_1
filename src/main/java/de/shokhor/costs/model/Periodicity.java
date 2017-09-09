package de.shokhor.costs.model;

public enum Periodicity {

    EveryDay{
        @Override
        public String toString() {
            return "Every day";
        }
    },
    EveryWeek{
        @Override
        public String toString() {
            return "Every week";
        }
    },
    EveryMonth{
        @Override
        public String toString() {
            return "Every month";
        }
    };
}
