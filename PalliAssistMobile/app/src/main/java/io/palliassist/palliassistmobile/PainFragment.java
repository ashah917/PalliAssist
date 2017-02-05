package io.palliassist.palliassistmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewGroupCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Steven on 1/31/2017.
 */

public class PainFragment extends Fragment {

    private GestureDetector gestureDetector;
    private boolean tapped = false;
    private View layout = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_pain_locator, container, false);
        layout = rootView;
        gestureDetector = new GestureDetector(rootView.getContext(), new GestureListener());

        // display body image
        ImageView bv = (ImageView) rootView.findViewById(R.id.bodyView);
        Bitmap body_bit = BitmapFactory.decodeResource(getResources(), R.drawable.body_image);
        bv.setImageBitmap(body_bit);

        // touch listener
        bv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View bv, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }

        });



        return rootView;
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            int[] img_coord = new int[2];
            ImageView image = (ImageView) layout.findViewById(R.id.pain_view);
            image.getLocationOnScreen(img_coord);
            double x_center = (double)img_coord[0] + image.getWidth()/2.0;
            double y_center = (double)img_coord[1] + image.getHeight()/2.0;

            System.out.println("Y_CENTER: "+y_center);
            System.out.println("X_CENTER: "+x_center);
            double x = (double)e.getRawX();
            double y = (double)e.getRawY();
//            double x = (double)x_center;
//            double y = (double) y_center;

            System.out.println("X" + x);
            System.out.println("Y" + y);
            //int x = (int)e.getX();
            //int y = (int)e.getY();
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                Bitmap pain_icon = BitmapFactory.decodeResource(getResources(), R.drawable.red_circle);
                ImageView pain_view = (ImageView) layout.findViewById(R.id.pain_view);
//                pain_view.setX((float)x);
//                pain_view.setY((float)y);
                pain_view.setX((float)x-80);
                pain_view.setY((float)y-350);
                pain_view.requestLayout();
                pain_view.getLayoutParams().height = pain_icon.getHeight();
                pain_view.getLayoutParams().width = pain_icon.getWidth();
                pain_view.setImageBitmap(pain_icon);
                Log.d("TA",String.valueOf(pain_view.getHeight()));
                Log.d("TA",String.valueOf(pain_icon.getHeight()));
                Log.d("TA",String.valueOf(pain_view.getWidth()));
                Log.d("TA",String.valueOf(pain_icon.getWidth()));
                pain_view.bringToFront();
            }
        }

    }
}
