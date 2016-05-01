package com.sam_chordas.android.stockhawk.api.model;

/**
 * @author albinmathew
 * @date 01/05/16.
 */
public class Result {
    private Query query;

    public Query getQuery() {
        return query;
    }


    public void setQuery(Query query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "[query = " + query + "]";
    }
}
