package io.palliassist.palliassistmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import io.palliassist.palliassistmobile.twilio.ui.ChatActivity;

/**
 * Created by Steven on 12/2/2016.
 */

public class EsasActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.esas_layout);
        setTitle("ESAS Survey");
    }




}
