package moneycalculator.model;

import java.util.Date;

public class ExchangeRate {

    private final Date date;
    private final double rate;
    private final Currency from;
    private final Currency to;

    public ExchangeRate(Date date, double rate, Currency from, Currency to) {
        this.date = date;
        this.rate = rate;
        this.from = from;
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    @Override
    public String toString() {
        return to.getCode();
    }

}
