package io.palliassist.palliassistmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class PainLocator extends AppCompatActivity {

    private GestureDetector gestureDetector;
    private boolean tapped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestureDetector = new GestureDetector(this, new GestureListener());
        setContentView(R.layout.activity_pain_locator);

        // display body image
        ImageView bv = (ImageView) findViewById(R.id.bodyView);
        Bitmap body_bit = BitmapFactory.decodeResource(getResources(), R.drawable.body_image);
        bv.setImageBitmap(body_bit);

        // touch listener
        bv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View bv, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }

        });
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();

            if (!tapped) {
                ImageView pain_view = (ImageView) findViewById(R.id.pain_view);
                pain_view.setX(x);
                pain_view.setY(y);
                Bitmap pain_icon = BitmapFactory.decodeResource(getResources(), R.drawable.star_48);
                pain_view.setImageBitmap(pain_icon);
                pain_view.bringToFront();
            }

            tapped = true;

            return true;
        }
    }
}
