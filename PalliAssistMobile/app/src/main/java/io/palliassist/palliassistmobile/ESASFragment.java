package io.palliassist.palliassistmobile;

/**
 * Created by Senthil on 1/3/17.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import butterknife.InjectView;


class AndroidExternalFontsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_esas);

    }
}

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

        submitbut.setOnClickListener(this);



        // Inflate the layout for this fragment
        return rootView;
    }

    public void onClick(View v){

        SeekBar seek1 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar1);
        seek1.setProgress(0);

        SeekBar seek2 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar2);
        seek2.setProgress(0);

        SeekBar seek3 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar3);
        seek3.setProgress(0);

        SeekBar seek4 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar4);
        seek4.setProgress(0);

        SeekBar seek5 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar5);
        seek5.setProgress(0);

        SeekBar seek6 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar6);
        seek6.setProgress(0);

        SeekBar seek7 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar7);
        seek7.setProgress(0);

        SeekBar seek8 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar8);
        seek8.setProgress(0);

        SeekBar seek9 = (SeekBar) getActivity().findViewById(R.id.simpleSeekBar9);
        seek9.setProgress(0);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
