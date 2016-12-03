package io.palliassist.palliassistmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import io.palliassist.palliassistmobile.twilio.ui.ChatActivity;

public class MainActivity extends FragmentActivity {

    Button button_message;
    Button button_notify;
    Button button_emergency;
    Button button_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_message = (Button)findViewById(R.id.button_message);
        button_notify = (Button)findViewById(R.id.button_notify);
        button_emergency = (Button)findViewById(R.id.button_emerg);
        button_settings = (Button)findViewById(R.id.button_setting);

    }


    /**
     * button listener that will do something if specific button is clicked
     *
     */

    public void buttonClicked(View view) {
        if (view.getId() == R.id.button_message) {
            final Intent i = new Intent(this, ChatActivity.class);
            startActivity(i);
            finish();
        }
        if (view.getId() == R.id.button_notify) {
            /* TODO*/
            return;
        }
        if (view.getId() == R.id.button_emerg) {
            /*TODO*/
            return;
        }
        if (view.getId() == R.id.button_setting) {
            /* TODO*/
            return;
        }

    }

    /**
     * Switch case determining the different options on the menu
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Chat option(located in res/menu/main_menu.xml)
            case R.id.context_message:
                final Intent i = new Intent(this, ChatActivity.class);
                startActivity(i);
                finish();
                return true;
            //Other 3 context menus need to be completed
            case R.id.context_notify:
                return true;
            case R.id.context_emerg:
                return true;
            case R.id.context_setting:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
