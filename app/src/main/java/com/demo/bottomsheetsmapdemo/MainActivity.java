package com.demo.bottomsheetsmapdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean showFAB = true;
    boolean firstSlide = true;
    private BottomSheetBehavior mBottomSheetBehavior;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Needed for map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Helper to setup bottom sheet UI flow
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.map_coordinator);
        setupBottomSheet(coordinatorLayout);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.demo.bottomsheetsmapdemo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.demo.bottomsheetsmapdemo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    // Needed for map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    private void setupBottomSheet(CoordinatorLayout coordinatorLayout) {

        final Animation growAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_grow);
        final Animation shrinkAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_shrink);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_bottom_sheet);

        fab.setVisibility(View.VISIBLE);
        fab.startAnimation(growAnimation);

        shrinkAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fab.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        NestedScrollView bottomSheetParent = (NestedScrollView) findViewById(R.id.parent_bottom_sheet);


        FrameLayout bottomsheetframe = (FrameLayout) bottomSheetParent.findViewById(R.id.bottomsheetframe);
        bottomsheetframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehavior != null) {
                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED &&
                            mBottomSheetBehavior.getPeekHeight() == 1500) {
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        fab.startAnimation(shrinkAnimation);
                    } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED &&
                            mBottomSheetBehavior.getPeekHeight() == 300) {
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        fab.startAnimation(shrinkAnimation);
                    } else if (firstSlide) {
                        firstSlide = false;
                        mBottomSheetBehavior.setPeekHeight(1500);
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        fab.startAnimation(shrinkAnimation);
                    }
                }
            }
        });
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheetParent);
        mBottomSheetBehavior.setPeekHeight(300);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        if (mBottomSheetBehavior != null) {
//            setStateText(mBottomSheetBehavior.getState());
            mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {

                    switch (newState) {

                        case BottomSheetBehavior.STATE_DRAGGING:
                            if (showFAB)
                                fab.startAnimation(shrinkAnimation);
                            break;

                        case BottomSheetBehavior.STATE_COLLAPSED:
                            showFAB = true;
                            fab.setVisibility(View.VISIBLE);
                            fab.startAnimation(growAnimation);
                            break;

                        case BottomSheetBehavior.STATE_EXPANDED:
                            showFAB = false;
                            break;
                    }

                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        mBottomSheetBehavior.setPeekHeight(1500);
                    }

                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        Log.i("BSB", "Collapsed");
                    }


                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        if (mBottomSheetBehavior.getPeekHeight() == 1500) {
                            mBottomSheetBehavior.setPeekHeight(300);
                        } else if (mBottomSheetBehavior.getPeekHeight() == 300) {
                            mBottomSheetBehavior.setPeekHeight(1500);
                        } else if (firstSlide) {
                            firstSlide = false;
                            mBottomSheetBehavior.setPeekHeight(300);
                        }
                        bottomSheet.requestLayout();
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }

                    // setStateText(newState);


                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                    // setOffsetText(slideOffset);

                }
            });
        }
    }


}