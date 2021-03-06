package com.sam_chordas.android.stockhawk.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.api.model.Query;
import com.sam_chordas.android.stockhawk.api.model.Quote;
import com.sam_chordas.android.stockhawk.api.model.Result;
import com.sam_chordas.android.stockhawk.api.model.Results;
import com.sam_chordas.android.stockhawk.api.QuoteService;
import com.sam_chordas.android.stockhawk.utility.CustomSpinnerAdapter;
import com.sam_chordas.android.stockhawk.utility.DateUtils;
import com.squareup.okhttp.OkHttpClient;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * @author albinmathew
 * @date 01/05/16.
 */

public class GraphActivity extends AppCompatActivity {
    public static String symbol;
    private RelativeLayout mChartHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mChartHolder = (RelativeLayout) findViewById(R.id.container_main);
        symbol = getIntent().getExtras().getString("symbol");
        Log.d("symbol", symbol);
        getSupportActionBar().setTitle(symbol);
    }

    private void callRetrofitFetch(String symbol, String startDate, String endDate) {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://query.yahooapis.com/v1")
                .setClient(new OkClient(new OkHttpClient()));

        RestAdapter adapter = builder.build();
        QuoteService QuoteApi = adapter.create(QuoteService.class);

        String q = "select * from yahoo.finance.historicaldata where symbol = \"" + symbol + "\" and startDate = \"" + endDate + "\" and endDate = \"" + startDate + "\"";
        String diagnostics = "true";
        String env = "store://datatables.org/alltableswithkeys";
        String format = "json";
        QuoteApi.getHistoricalData(q, diagnostics, env, format, new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {

                mChartHolder.removeAllViews();

                Log.d("result", result.toString());

                Query mQuery = result.getQuery();
                Results mResult = mQuery.getResults();
                Quote[] mQuote = mResult.getQuote();

                XYSeries series = new XYSeries("Stock value - Historic Data");
                int hour = 0;
                for (Quote hf : mQuote) {
                    series.add(hour++, Double.parseDouble(hf.getHigh()));
                    Log.d("value", hf.getHigh());
                }
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                renderer.setLineWidth(2);
                renderer.setColor(Color.RED);
                renderer.setDisplayBoundingPoints(true);
                renderer.setPointStyle(PointStyle.CIRCLE);
                renderer.setPointStrokeWidth(3);
                XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

                XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
                dataset.addSeries(series);

                mRenderer.addSeriesRenderer(renderer);
                mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
                mRenderer.setPanEnabled(false, false);
                mRenderer.setYAxisMax(100);
                mRenderer.setYAxisMin(0);
                mRenderer.setShowGrid(true); // we show the grid
                GraphicalView chartView = ChartFactory.getLineChartView(getApplicationContext(), dataset, mRenderer);

                mChartHolder.addView(chartView);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error", error.toString());
                error.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_activity, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayList<String> list = new ArrayList<String>();
        list.add(getString(R.string.one_week));
        list.add(getString(R.string.one_month));
        list.add(getString(R.string.three_month));
        list.add(getString(R.string.six_month));
        list.add(getString(R.string.one_year));
        CustomSpinnerAdapter spinAdapter = new CustomSpinnerAdapter(
                getApplicationContext(), list);
        spinner.setAdapter(spinAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                String item = adapter.getItemAtPosition(position).toString();
                String startDate = DateUtils.getFormattedDate(System.currentTimeMillis());
                Date date = new Date();
                switch (item) {
                    case "1W":
                        callRetrofitFetch(symbol, startDate, DateUtils.getAWeekBackDate(date));
                        break;
                    case "1M":
                        callRetrofitFetch(symbol, startDate, DateUtils.getAMonthBackDate(date));
                        break;
                    case "3M":
                        callRetrofitFetch(symbol, startDate, DateUtils.getThreeMonthsBackDate(date));
                        break;
                    case "6M":
                        callRetrofitFetch(symbol, startDate, DateUtils.getSixMonthsBackDate(date));
                        break;
                    case "1Y":
                        callRetrofitFetch(symbol, startDate, DateUtils.getAYearBackDate(date));
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        return true;
    }
}
