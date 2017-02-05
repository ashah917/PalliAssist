package io.palliassist.palliassistmobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.AppCompatButton;
import android.graphics.Typeface;
import android.app.ProgressDialog;
import android.util.Log;
import android.content.Intent;
import android.widget.Button;

import butterknife.InjectView;

public class SettingsFragment extends Fragment implements View.OnClickListener {

   @InjectView(R.id.btn_logout) Button _logoutButton;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        AppCompatButton logoutlink = (AppCompatButton) rootView.findViewById(R.id.btn_logout); // adjust this line to get the TextView you want to change
        Typeface jfsanssemi = Typeface.createFromAsset(getActivity().getAssets(),"fonts/jfinsanssemi.ttf"); // create a typeface from the raw ttf
        logoutlink.setTypeface(jfsanssemi); // apply the typeface to the textview


        logoutlink.setOnClickListener(this);



        // Inflate the layout for this fragment
        return rootView;
    }

    public void onClick(View v) {
        logout();
    }

    public void logout() {

       // _logoutButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.NewTheme1exp);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging out...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLogoutSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onLogoutSuccess() {
       // _logoutButton.setEnabled(true);
        Log.d("LOGOUT","Logging out homies");
        Intent i=new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
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