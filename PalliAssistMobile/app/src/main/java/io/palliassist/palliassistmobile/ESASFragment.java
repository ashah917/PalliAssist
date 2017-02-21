package io.palliassist.palliassistmobile;

/**
 * Created by Senthil on 1/3/17.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;


public class ESASFragment extends Fragment implements View.OnClickListener {

    @InjectView(R.id.button_submit) Button _submitButton;

    public ESASFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_esas, container, false);

        Button submitbut = (Button) rootView.findViewById(R.id.button_submit); // adjust this line to get the TextView you want to change
        Typeface jfsanssemi = Typeface.createFromAsset(getActivity().getAssets(),"fonts/jfinsanssemi.ttf"); // create a typeface from the raw ttf
        submitbut.setTypeface(jfsanssemi); // apply the typeface to the textview


        final TextView val1 = (TextView) rootView.findViewById(R.id.val1);
        val1.setTypeface(jfsanssemi);
        val1.setTextSize(30);
        final SeekBar seek1 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar1);
        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val1.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val2 = (TextView) rootView.findViewById(R.id.val2);
        val2.setTypeface(jfsanssemi);
        val2.setTextSize(30);
        final SeekBar seek2 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar2);
        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress2, boolean fromUser) {
                val2.setText(String.valueOf(progress2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val3 = (TextView) rootView.findViewById(R.id.val3);
        val3.setTypeface(jfsanssemi);
        val3.setTextSize(30);
        SeekBar seek3 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar3);
        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress3, boolean fromUser) {
                val3.setText(String.valueOf(progress3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val4 = (TextView) rootView.findViewById(R.id.val4);
        val4.setTypeface(jfsanssemi);
        val4.setTextSize(30);
        SeekBar seek4 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar4);
        seek4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress4, boolean fromUser) {
                val4.setText(String.valueOf(progress4));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val5 = (TextView) rootView.findViewById(R.id.val5);
        val5.setTypeface(jfsanssemi);
        val5.setTextSize(30);
        SeekBar seek5 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar5);
        seek5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress5, boolean fromUser) {
                val5.setText(String.valueOf(progress5));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val6 = (TextView) rootView.findViewById(R.id.val6);
        val6.setTypeface(jfsanssemi);
        val6.setTextSize(30);
        SeekBar seek6 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar6);
        seek6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress6, boolean fromUser) {
                val6.setText(String.valueOf(progress6));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val7 = (TextView) rootView.findViewById(R.id.val7);
        val7.setTypeface(jfsanssemi);
        val7.setTextSize(30);
        SeekBar seek7 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar7);
        seek7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress7, boolean fromUser) {
                val7.setText(String.valueOf(progress7));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val8 = (TextView) rootView.findViewById(R.id.val8);
        val8.setTypeface(jfsanssemi);
        val8.setTextSize(30);
        SeekBar seek8 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar8);
        seek8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress8, boolean fromUser) {
                val8.setText(String.valueOf(progress8));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val9 = (TextView) rootView.findViewById(R.id.val9);
        val9.setTypeface(jfsanssemi);
        val9.setTextSize(30);
        SeekBar seek9 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar9);
        seek9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress9, boolean fromUser) {
                val9.setText(String.valueOf(progress9));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val10 = (TextView) rootView.findViewById(R.id.val10);
        val10.setTypeface(jfsanssemi);
        val10.setTextSize(30);
        SeekBar seek10 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar10);
        seek10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress10, boolean fromUser) {
                val10.setText(String.valueOf(progress10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val11 = (TextView) rootView.findViewById(R.id.val11);
        val11.setTypeface(jfsanssemi);
        val11.setTextSize(30);
        SeekBar seek11 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar11);
        seek11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress11, boolean fromUser) {
                val11.setText(String.valueOf(progress11));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val12 = (TextView) rootView.findViewById(R.id.val12);
        val12.setTypeface(jfsanssemi);
        val12.setTextSize(30);
        SeekBar seek12 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar12);
        seek12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress12, boolean fromUser) {
                val12.setText(String.valueOf(progress12));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView val13 = (TextView) rootView.findViewById(R.id.val13);
        val13.setTypeface(jfsanssemi);
        val13.setTextSize(30);
        SeekBar seek13 = (SeekBar) rootView.findViewById(R.id.simpleSeekBar13);
        seek13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress13, boolean fromUser) {
                val13.setText(String.valueOf(progress13));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submitbut.setOnClickListener(this);


        // Inflate the layout for this fragment
        return rootView;
    }

    public void onClick(View v){

        List<Map<String, String>> esasDataList = new ArrayList<Map<String, String>>();

        SeekBar seek1 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar1);
        Map<String, String> q1 = new HashMap<>();
        q1.put("question", "pain");
        q1.put("answer", Integer.toString(seek1.getProgress()));
        esasDataList.add(q1);
        seek1.setProgress(0);

        SeekBar seek2 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar2);
        Map<String, String> q2 = new HashMap<>();
        q2.put("question", "tiredness");
        q2.put("answer", Integer.toString(seek2.getProgress()));
        esasDataList.add(q2);
        seek2.setProgress(0);

        SeekBar seek3 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar3);
        Map<String, String> q3 = new HashMap<>();
        q3.put("question", "drowsiness");
        q3.put("answer", Integer.toString(seek3.getProgress()));
        esasDataList.add(q3);
        seek3.setProgress(0);

        SeekBar seek4 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar4);
        Map<String, String> q4 = new HashMap<>();
        q4.put("question", "nausea");
        q4.put("answer", Integer.toString(seek4.getProgress()));
        esasDataList.add(q4);
        seek4.setProgress(0);

        SeekBar seek5 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar5);
        Map<String, String> q5 = new HashMap<>();
        q5.put("question", "appetite");
        q5.put("answer", Integer.toString(seek5.getProgress()));
        esasDataList.add(q5);
        seek5.setProgress(0);

        SeekBar seek6 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar6);
        Map<String, String> q6 = new HashMap<>();
        q6.put("question", "breathing");
        q6.put("answer", Integer.toString(seek6.getProgress()));
        esasDataList.add(q6);
        seek6.setProgress(0);

        SeekBar seek7 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar7);
        Map<String, String> q7 = new HashMap<>();
        q7.put("question", "depression");
        q7.put("answer", Integer.toString(seek7.getProgress()));
        esasDataList.add(q7);
        seek7.setProgress(0);

        SeekBar seek8 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar8);
        Map<String, String> q8 = new HashMap<>();
        q8.put("question", "anxiety");
        q8.put("answer", Integer.toString(seek8.getProgress()));
        esasDataList.add(q8);
        seek8.setProgress(0);

        SeekBar seek9 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar9);
        Map<String, String> q9 = new HashMap<>();
        q9.put("question", "wellbeing");
        q9.put("answer", Integer.toString(seek9.getProgress()));
        esasDataList.add(q9);
        seek9.setProgress(0);

        SeekBar seek10 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar10);
        Map<String, String> q10 = new HashMap<>();
        q10.put("question", "constipation");
        q10.put("answer", Integer.toString(seek10.getProgress()));
        esasDataList.add(q10);
        seek10.setProgress(0);

        SeekBar seek11 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar11);
        Map<String, String> q11 = new HashMap<>();
        q11.put("question", "fever");
        q11.put("answer", Integer.toString(seek11.getProgress()));
        esasDataList.add(q11);
        seek11.setProgress(0);

        SeekBar seek12 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar12);
        Map<String, String> q12 = new HashMap<>();
        q12.put("question", "vomiting");
        q12.put("answer", Integer.toString(seek12.getProgress()));
        esasDataList.add(q12);
        seek12.setProgress(0);

        SeekBar seek13 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar13);
        Map<String, String> q13 = new HashMap<>();
        q13.put("question", "confusion");
        q13.put("answer", Integer.toString(seek13.getProgress()));
        esasDataList.add(q13);
        seek13.setProgress(0);

        Gson gson = new Gson();
        sendEsas(gson.toJson(esasDataList));

    }


    private void sendEsas(final String esasData) {

        RequestQueue rq = Volley.newRequestQueue(this.getContext());


        StringRequest sr = new StringRequest(Request.Method.POST, "http://50f5539c.ngrok.io/fcm", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("sendEsas", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                Date ts = new Date();
                params.put("action", "SAVE");
                params.put("type", "ESAS");
                params.put("timestamp", String.valueOf(ts.getTime()));
                params.put("patient", "patient0");
                params.put("questions", esasData);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
//                params.put("csrfmiddlewaretoken", "93FEyottp1lXVPnFVi8auE753SgDjCvDq7EUCrbSpOOXC3vYD8o7ySC8SnXQbaND");
                return params;
            }
        };
        rq.add(sr);
    }

}
