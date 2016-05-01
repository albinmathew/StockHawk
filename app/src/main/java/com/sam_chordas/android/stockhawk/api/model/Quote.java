package com.sam_chordas.android.stockhawk.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author albinmathew
 * @date 01/05/16.
 */
public class Quote {

    @SerializedName("Low")
    private String low;
    @SerializedName("Open")
    private String open;
    @SerializedName("Adj_Close")
    private String adjClose;
    @SerializedName("Close")
    private String close;
    @SerializedName("Date")
    private String date;
    @SerializedName("Volume")
    private String volume;
    @SerializedName("Symbol")
    private String symbol;
    @SerializedName("High")
    private String high;

    public String getLow() {
        return low;
    }

    public void setLow(String Low) {
        this.low = Low;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String Open) {
        this.open = Open;
    }

    public String getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(String Adj_Close) {
        this.adjClose = Adj_Close;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String Close) {
        this.close = Close;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String Volume) {
        this.volume = Volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String Symbol) {
        this.symbol = Symbol;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String High) {
        this.high = High;
    }

    @Override
    public String toString() {
        return "[low = " + low + ", open = " + open + ", adjClose = " + adjClose + ", close = " + close + ", date = " + date + ", volume = " + volume + ", symbol = " + symbol + ", high = " + high + "]";
    }
}


