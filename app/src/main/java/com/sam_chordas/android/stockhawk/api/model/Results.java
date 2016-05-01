package com.sam_chordas.android.stockhawk.api.model;

import java.util.Arrays;

/**
 * @author albinmathew
 * @date 01/05/16.
 */
public class Results {
    private Quote[] quote;

    public Quote[] getQuote() {
        return quote;
    }

    public void setQuote(Quote[] quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "[quote = " + Arrays.toString(quote) + "]";
    }
}

