package io.palliassist.palliassistmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewGroupCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steven on 1/31/2017.
 */

public class PainFragment extends Fragment {

    private GestureDetector gestureDetector;
    private boolean tapped = false;
    private View layout = null;
    private ImageView circle;
    private ImageView bv;
    private float[] pts = new float[2];


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_pain_locator, container, false);
        layout = rootView;
        gestureDetector = new GestureDetector(rootView.getContext(), new GestureListener());

        Button submitbut = (Button) rootView.findViewById(R.id.button_submit); // adjust this line to get the TextView you want to change
        Typeface jfsanssemi = Typeface.createFromAsset(getActivity().getAssets(), "fonts/jfinsanssemi.ttf"); // create a typeface from the raw ttf
        submitbut.setTypeface(jfsanssemi); // apply the typeface to the textview

        // display body image
        bv = (ImageView) rootView.findViewById(R.id.bodyView);
        Bitmap body_bit = BitmapFactory.decodeResource(getResources(), R.drawable.body_image);
        bv.setImageBitmap(body_bit);

        View.OnTouchListener onTouchListenerBodyView = new View.OnTouchListener() {
            Matrix inverse = new Matrix();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bv.getImageMatrix().invert(inverse);
                pts[0] = event.getX();
                pts[1] = event.getY();
                inverse.mapPoints(pts);
                System.out.println("onTouch: " + Math.floor(pts[0]) + ",  y: " + Math.floor(pts[1]));
                Drawable bodyImage = bv.getDrawable();
                System.out.println("intrinsicWidth: " + bodyImage.getIntrinsicWidth());
                System.out.println("intrinsicHeigh: " + bodyImage.getIntrinsicHeight());

                return gestureDetector.onTouchEvent(event);
            }
        };

        bv.setOnTouchListener(onTouchListenerBodyView);


        submitbut.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SeekBar sb = (SeekBar) layout.findViewById(R.id.seekBar);
                int intensity = sb.getProgress();
                sendPain(pts[0]+10, pts[1]-80, bv.getDrawable().getIntrinsicHeight(), bv.getDrawable().getIntrinsicWidth(), intensity);

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
//            image.getLocationOnScreen(img_coord);
//            double x_center = (double)img_coord[0] + image.getWidth()/2.0;
//            double y_center = (double)img_coord[1] + image.getHeight()/2.0;

//            System.out.println("Y_CENTER: "+y_center);
//            System.out.println("X_CENTER: "+x_center);
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
                System.out.println(pain_icon);
                ImageView red_circle = new ImageView(getContext());
                System.out.println(red_circle);
//                pain_view.setX((float)x);
//                pain_view.setY((float)y);
                red_circle.setX((float) x - 80);
                red_circle.setY((float) y - 350);
//                red_circle.requestLayout();
                ((RelativeLayout) layout).addView(red_circle);
                circle = red_circle;
                System.out.println(red_circle.getLayoutParams());
                red_circle.getLayoutParams().height = pain_icon.getHeight();
                red_circle.getLayoutParams().width = pain_icon.getWidth();
                red_circle.setImageBitmap(pain_icon);
                Log.d("X", String.valueOf(red_circle.getHeight()));
                Log.d("Y",String.valueOf(pain_icon.getHeight()));
                Log.d("Width: ", String.valueOf(red_circle.getWidth()));
                Log.d("Height: ",String.valueOf(pain_icon.getWidth()));
                red_circle.bringToFront();

                System.out.println();

                ImageView image = (ImageView) layout.findViewById(R.id.bodyView);
                System.out.println("image.getX(): " + image.getX());
                System.out.println("image.getY(): " + image.getY());
                System.out.println("image.getWidth(): " + image.getWidth());
                System.out.println("image.getHeight(): " + image.getHeight());
                System.out.println("image.getLeft(): " + image.getLeft());
                System.out.println("image.getTop(): " + image.getTop());
            }
        }

    }

    private void sendPain(final double x_coord, final double y_coord, final int height, final int width, final int intensity) {

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
                params.put("type", "PAIN");
                params.put("timestamp", String.valueOf(ts.getTime()));
                params.put("patient", "patient0");
                params.put("width", String.valueOf(width));
                params.put("height", String.valueOf(height));
                params.put("x", String.valueOf(x_coord));
                params.put("y", String.valueOf(y_coord));
                params.put("intensity", String.valueOf(intensity));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        rq.add(sr);
    }
}
