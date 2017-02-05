package io.palliassist.palliassistmobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;
import android.widget.TextView;
import android.graphics.Typeface;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        Typeface jfsanssemi = Typeface.createFromAsset(getActivity().getAssets(),"fonts/jfinsanssemi.ttf"); // create a typeface from the raw ttf
        Typeface jfsansbi = Typeface.createFromAsset(getActivity().getAssets(),"fonts/jfinsansbi.ttf"); // create a typeface from the raw ttf

        CardView maincard = (CardView)rootView.findViewById(R.id.main_card_view);

        TextView name = (TextView)rootView.findViewById(R.id.profname);
        name.setTypeface(jfsanssemi); // apply the typeface to the textview

        TextView notify_text = (TextView)rootView.findViewById(R.id.notify_descrip);
        notify_text.setTypeface(jfsansbi); // apply the typeface to the textview

        TextView meetings_text = (TextView)rootView.findViewById(R.id.meetings_descrip);
        meetings_text.setTypeface(jfsansbi); // apply the typeface to the textview

        // Inflate the layout for this fragment
        return rootView;
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