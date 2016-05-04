package com.demo.bottomsheetsmapdemo;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean showFAB = true;
//    RecyclerView recyclerView;
    SimpleRecyclerAdapter adapter;

//    TextView textViewState;
//    TextView textViewOffset;

    private BottomSheetBehavior mBottomSheetBehavior;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        textViewState = (TextView) findViewById(R.id.bottom_sheet_state);
//        textViewOffset = (TextView) findViewById(R.id.bottom_sheet_offset);

        final Animation growAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_grow);
        final Animation shrinkAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_shrink);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.gmail_fab);

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


//        recyclerView = (RecyclerView) findViewById(R.id.gmail_list);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setHasFixedSize(true);
//
//        if (adapter == null) {
//            adapter = new SimpleRecyclerAdapter(this);
//            recyclerView.setAdapter(adapter);
//        }
//
//        adapter.SetOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
////                switch (position) {
////                    case 0:
////                        intent = new Intent(MainActivity.this, FabHideActivity.class);
////                        break;
////                    case 1:
////                        intent = new Intent(MainActivity.this, ToolbarOverlayActivity.class);
////                        break;
////                    case 2:
////                        intent = new Intent(MainActivity.this, NavDrawerActivity.class);
////                        break;
////                    case 3:
////                        intent = new Intent(MainActivity.this, AnimateToolbar.class);
////                        break;
////                    case 4:
////                        intent = new Intent(MainActivity.this, TabAnimationActivity.class);
////                        break;
////                    case 5:
////                        intent = new Intent(MainActivity.this, NestedToolbarActivity.class);
////                        break;
////                    case 6:
////                        intent = new Intent(MainActivity.this, QuickReturnActivity.class);
////                        break;
////                    case 7:
////                        intent = new Intent(MainActivity.this, RevealAnimation.class);
////                        break;
////                    case 8:
////                        intent = new Intent(MainActivity.this, GmailStyleActivity.class);
////                        break;
////                    case 9:
////                        intent = new Intent(MainActivity.this, PagerActivity.class);
////                        break;
//
////                    default:
//                Toast.makeText(getBaseContext(), "Undefined Click! at position " + position, Toast.LENGTH_SHORT).show();
////                }
//
////                if (intent != null)
////                    startActivity(intent);
//            }
//        });



        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.gmail_coordinator);
        View bottomSheet = coordinatorLayout.findViewById(R.id.gmail_bottom_sheet);
        View bottomSheetparent = coordinatorLayout.findViewById(R.id.bottomsheetparent);
//        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//
//        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//
//                switch (newState) {
//
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        if (showFAB)
//                            fab.startAnimation(shrinkAnimation);
//                        break;
//
//                    case BottomSheetBehavior.STATE_COLLAPSED:
//                        showFAB = true;
//                        fab.setVisibility(View.VISIBLE);
//                        fab.startAnimation(growAnimation);
//                        break;
//
//                    case BottomSheetBehavior.STATE_EXPANDED:
//                        showFAB = false;
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onSlide(View bottomSheet, float slideOffset) {
//
//            }
//        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        // Needed for map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        NestedScrollView parentThatHasBottomSheetBehavior = (NestedScrollView) findViewById(R.id.gmail_bottom_sheet);

        FrameLayout bottomsheetframe = (FrameLayout)parentThatHasBottomSheetBehavior.findViewById(R.id.bottomsheetframe);

        bottomsheetframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

//        bottomsheetparent

        mBottomSheetBehavior = BottomSheetBehavior.from(parentThatHasBottomSheetBehavior);

        View.OnClickListener bottomSheetClick = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(mBottomSheetBehavior != null){
                    if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED &&
                            mBottomSheetBehavior.getPeekHeight() == 300){
                        mBottomSheetBehavior.setPeekHeight(1500);
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
            }
        };
//
        bottomSheet.setOnClickListener(bottomSheetClick);

        if (mBottomSheetBehavior != null) {
            setStateText(mBottomSheetBehavior.getState());
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

                    if(newState == BottomSheetBehavior.STATE_EXPANDED){
                        mBottomSheetBehavior.setPeekHeight(1500);
                    }

//                    if(newState == BottomSheetBehavior.STATE_COLLAPSED &&
//                            mBottomSheetBehavior.getPeekHeight() == 700){
//                        mBottomSheetBehavior.setPeekHeight(100);
//                    }
//
//                    if(newState == BottomSheetBehavior.STATE_COLLAPSED &&
//                            mBottomSheetBehavior.getPeekHeight() == 100){
//                        mBottomSheetBehavior.setPeekHeight(700);
//
//                    }


                    if(newState == BottomSheetBehavior.STATE_COLLAPSED){
                        Log.i("BSB","Collapsed");
                        mBottomSheetBehavior.setPeekHeight(1500);
//                        bottomSheet.requestLayout();
//                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }



                    if(newState == BottomSheetBehavior.STATE_HIDDEN){
                        mBottomSheetBehavior.setPeekHeight(300);
                        bottomSheet.requestLayout();
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }

                    setStateText(newState);


                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                    setOffsetText(slideOffset);

                }
            });
        }


    }

    private void setStateText(int newState) {
//        textViewState.setText(getStateAsString(newState));
    }

    private void setOffsetText(float slideOffset) {
//        textViewOffset.setText("Offset" + Float.toString(slideOffset));
    }


    public static int getStateAsString(int newState) {
        switch (newState) {
            case BottomSheetBehavior.STATE_COLLAPSED:
                return R.string.collapsed;
            case BottomSheetBehavior.STATE_DRAGGING:
                return R.string.dragging;
            case BottomSheetBehavior.STATE_EXPANDED:
                return R.string.expanded;
            case BottomSheetBehavior.STATE_HIDDEN:
                return R.string.hidden;
            case BottomSheetBehavior.STATE_SETTLING:
                return R.string.settling;
        }
        return R.string.undefined;
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
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Main Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.demo.bottomsheetmapdemo/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Main Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.demo.bottomsheetdemo/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
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
}