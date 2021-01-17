package in.resoluteai.orahiassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.resoluteai.orahiassignment.adapter.DataAdapter;
import in.resoluteai.orahiassignment.modal.Datum;

public class DemoGraph extends AppCompatActivity {
    private static final String URl = "https://demo5636362.mockable.io/stats";
    LinearLayout linearChart;
    TextView textView;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private RequestQueue mQueue;
    List<Datum> datumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_graph);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        datumList = new ArrayList<>();

        mQueue = Volley.newRequestQueue(this);

        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("data");
                            Log.d("TAG", "Error" + array);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);
                                Datum datum = new Datum(object.getString("month"), object.getInt("stat"));
                                datumList.add(datum);
                            }
                            adapter = new DataAdapter(datumList, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(DemoGraph.this, error + " ", Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void drawChart(int count, int color, int height) {
        System.out.println(count + color + height);
        if (color == 3) {
            color = Color.RED;
        } else if (color == 1) {
            color = Color.BLUE;
        } else if (color == 2) {
            color = Color.GREEN;
        }

        for (int k = 1; k <= count; k++) {
            View view = new View(this);
            view.setBackgroundColor(color);
            view.setLayoutParams(new LinearLayout.LayoutParams(25, height));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
                    .getLayoutParams();
            params.setMargins(3, 0, 0, 0); // substitute parameters for left,
            // top, right, bottom
            view.setLayoutParams(params);
            linearChart.addView(view);
        }
    }
}