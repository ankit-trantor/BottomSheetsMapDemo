package com.demo.bottomsheetsmapdemo;

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
    }

    @Override
    public void onStop() {
        super.onStop();
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




    private void setupBottomSheet(CoordinatorLayout coordinatorLayout){

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



        FrameLayout bottomsheetframe = (FrameLayout)bottomSheetParent.findViewById(R.id.bottomsheetframe);
        bottomsheetframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBottomSheetBehavior!= null){
                    if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED &&
                            mBottomSheetBehavior.getPeekHeight() == 1500){
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        fab.startAnimation(shrinkAnimation);
                    }else if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED &&
                            mBottomSheetBehavior.getPeekHeight() == 300){
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        fab.startAnimation(shrinkAnimation);
                    }else if(firstSlide){
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
                        } else if(mBottomSheetBehavior.getPeekHeight() == 300) {
                            mBottomSheetBehavior.setPeekHeight(1500);
                        } else if(firstSlide){
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