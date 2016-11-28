package io.palliassist.palliassistmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.Menu;
import io.palliassist.palliassistmobile.twilio.ui.ChatActivity;

public class MainActivity extends FragmentActivity {
    public static final String EXTRA_IMAGE = "extra_image";

    private int extraCurrentItem;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set current item based on the extra passed in the activity
        extraCurrentItem = getIntent().getIntExtra(EXTRA_IMAGE, -1);

    }

    /**
     * Switch case determining the different options on the menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Chat option(located in res/menu/main_menu.xml)
            case R.id.discuss:
                final Intent i = new Intent(this, ChatActivity.class);
                i.putExtra(MainActivity.EXTRA_IMAGE, extraCurrentItem);
                startActivity(i);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
