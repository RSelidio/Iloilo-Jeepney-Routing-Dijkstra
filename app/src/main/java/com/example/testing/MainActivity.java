

package com.example.testing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.mylibrary.MaterialSpinner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import static com.example.testing.Dijkstras.computePaths;
import static com.example.testing.Dijkstras.getShortestPathTo;

public class MainActivity extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {
  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
  private static final String[] ANDROID_VERSIONS = {
          ////////lapaz//////////////
          // "Fort San Pedro",1
          "Origin",
          "Robinson City", //9
          "University of Iloilo", //10
          "SM City",//2
          "Grand Xing Imperial",//3
          "Hall of Justice",//4
          //"Lapaz Plaza", //5
          "Lapaz Public Market", //6
          "Gaisano City", //7
          "University of San Agustin", //8
          "Plaza Libertad",//2
          ///////baluarte////////////


  };
  private static final String[] ANDROID_VERSIONSS = {
          ////////lapaz//////////////
          // "Fort San Pedro",1
          "Destination",
          "Robinson City", //9
          "University of Iloilo", //10
          "SM City",//2
          "Grand Xing Imperial",//3
          "Hall of Justice",//4
          //"Lapaz Plaza", //5
          "Lapaz Public Market", //6
          "Gaisano City", //7
          "University of San Agustin", //8
          "Plaza Libertad",//2
          ///////baluarte////////////


  };
  TextView pathView2;
  private boolean mPermissionDenied = false;
  private GoogleMap mMap;
  private Button button1;
  TextView distanceview;






  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mains);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

    distanceview = (TextView) findViewById(R.id.distanceview);





    button1 = (Button) findViewById(R.id.button1);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openMain();
      }
    });


    pathView2 = (TextView) findViewById(R.id.pathView2);
    pathView2.setMovementMethod(new ScrollingMovementMethod());
    pathView2 = (TextView) findViewById(R.id.pathView2);
    pathView2.setText("Directions");

    distanceview = (TextView) findViewById(R.id.distanceview);
    distanceview.setMovementMethod(new ScrollingMovementMethod());
    distanceview = (TextView) findViewById(R.id.distanceview);


    final MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
    spinner.setItems(ANDROID_VERSIONS);
    final MaterialSpinner spinner2 = (MaterialSpinner) findViewById(R.id.spinner2);
    spinner2.setItems(ANDROID_VERSIONSS);
    final MaterialSpinner spinner3 = (MaterialSpinner) findViewById(R.id.spinner2);




    spinner3.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

      {

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
          @RequiresApi(api = Build.VERSION_CODES.N)
          @Override
          public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
            String start = spinner.getText().toString();
            String destination = spinner2.getText().toString();
                Vertex A1 = new Vertex("Robinson City");
            Vertex A2 = new Vertex("University of Iloilo");
            Vertex A3 = new Vertex("SM City");
            Vertex A4 = new Vertex("Grand Xing Imperial");
            Vertex A5 = new Vertex("Hall of Justice");
            Vertex A6 = new Vertex("Lapaz Public Market");
            Vertex A7 = new Vertex("Gaisano City");
            Vertex A8 = new Vertex("University of San Agustin");
            Vertex A9 = new Vertex("Plaza Libertad");
            A1.adjacencies = new Edge[]{new Edge(A2, 1.4), new Edge(A3, 3.4), new Edge(A4, 0.8), new Edge(A5, 1.3), new Edge(A6, 1.9), new Edge(A7, 1.7), new Edge(A8, 1.3), new Edge(A9, 1.1)};
            A2.adjacencies = new Edge[]{new Edge(A1, 1.1), new Edge(A3, 4.5), new Edge(A4, 1.0), new Edge(A5, 1.4), new Edge(A6, 2.0), new Edge(A7, 1.8), new Edge(A8, 1.6), new Edge(A9, 0.5)};
            A3.adjacencies = new Edge[]{new Edge(A1, 3.6), new Edge(A2, 4.4), new Edge(A4, 5.5), new Edge(A5, 3.5), new Edge(A6, 4.0), new Edge(A7, 3.8), new Edge(A8, 2.2), new Edge(A9, 4.4)};
            A4.adjacencies = new Edge[]{new Edge(A1, 0.7), new Edge(A2, 0.9), new Edge(A3, 3.4), new Edge(A5, 0.3), new Edge(A6, 0.9), new Edge(A7, 0.7), new Edge(A8, 0.8), new Edge(A9, 1.2)};
            A5.adjacencies = new Edge[]{new Edge(A1, 1.3), new Edge(A2, 1.5), new Edge(A3, 3.4), new Edge(A4, 0.5), new Edge(A6, 0.5), new Edge(A7, 0.3), new Edge(A8, 0.8), new Edge(A9, 1.5)};
            A6.adjacencies = new Edge[]{new Edge(A1, 1.8), new Edge(A2, 1.9), new Edge(A3, 3.9), new Edge(A4, 1.1), new Edge(A5, 0.6), new Edge(A7, 0.4), new Edge(A8, 1.2), new Edge(A9, 2.0)};
            A7.adjacencies = new Edge[]{new Edge(A1, 1.6), new Edge(A2, 1.8), new Edge(A3, 3.6), new Edge(A4, 0.9), new Edge(A5, 0.4), new Edge(A6, 0.5), new Edge(A8, 1.1), new Edge(A9, 1.6)};
            A8.adjacencies = new Edge[]{new Edge(A1, 1.3), new Edge(A2, 1.6), new Edge(A3, 2.6), new Edge(A4, 0.7), new Edge(A5, 0.8), new Edge(A6, 0.8), new Edge(A7, 1.2),  new Edge(A9, 1.8)};
            A9.adjacencies = new Edge[]{new Edge(A1, 1.0), new Edge(A2, 0.4), new Edge(A3, 4.5), new Edge(A4, 1.0), new Edge(A5, 1.6), new Edge(A6, 2.2), new Edge(A7, 2.0), new Edge(A8, 1.1), new Edge(A9, 1.0)};



            switch (start) {
              case "Plaza Libertad":
                computePaths(A9);
                switch (destination) {

                  case "Plaza Libertad":
                    if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                    pathView2.setText("You are currently in Plaza Libertad");
                    break;
                  case "Grand Xing Imperial":
                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                    List<Vertex> path1 = getShortestPathTo(A4);
                    distanceview.setText(  path1 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A9_A4)));


                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))
                            //  .snippet("Population: 2,074,200")
                            .position(new LatLng(10.692438, 122.573488))
                            .title("Start Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692332, 122.573363))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700096, 122.569422))
                            .icon(vectorToBitmap(R.drawable.amupagidni ))
                            .title("End Point - Grand Xing Imperial"));




                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.692438, 122.573488),
                                    new LatLng(10.692332, 122.573363),

                                    new LatLng(10.693017, 122.572607),
                                    new LatLng(10.693973, 122.571060),
                                    new LatLng(10.694859, 122.570572),

                                    new LatLng(10.696567, 122.569140),
                                    new LatLng(10.700070, 122.569132),
                                    new LatLng(10.700096, 122.569422)));
                    break;
                  case "Hall of Justice":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path2 = getShortestPathTo(A5);
                    distanceview.setText(  path2 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A9_A5)));


                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692438, 122.573488))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .title("Start Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692332, 122.573363))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704231, 122.568115))
                            .icon(vectorToBitmap(R.drawable.amupagidni))
                            .title("End Point - Hall of Justice"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.692438, 122.573488),
                                    new LatLng(10.692332, 122.573363),

                                    new LatLng(10.693017, 122.572607),
                                    new LatLng(10.693973, 122.571060),
                                    new LatLng(10.694859, 122.570572),

                                    new LatLng(10.696567, 122.569140),
                                    new LatLng(10.700070, 122.569132),

                                    new LatLng(10.701639, 122.569121),
                                    new LatLng(10.702039, 122.568212),
                                    new LatLng(10.704205, 122.567938),
                                    new LatLng(10.704231, 122.568115)));
                    break;

                  case "Lapaz Public Market":
                    List<Vertex> path3 = getShortestPathTo(A6);
                    distanceview.setText(  path3 +"\n " + A6.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A9_A6)));



                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692438, 122.573488))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .title("Start Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692332, 122.573363))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708637, 122.567873))
                            .icon(vectorToBitmap(R.drawable.amupagidni))
                            .title("End Point - Lapaz Public Market"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.692438, 122.573488),
                                    new LatLng(10.692332, 122.573363),

                                    new LatLng(10.693017, 122.572607),
                                    new LatLng(10.693973, 122.571060),
                                    new LatLng(10.694859, 122.570572),

                                    new LatLng(10.696567, 122.569140),
                                    new LatLng(10.700070, 122.569132),

                                    new LatLng(10.701639, 122.569121),
                                    new LatLng(10.702039, 122.568212),
                                    new LatLng(10.704205, 122.567938),

                                    new LatLng(10.705106, 122.567911),
                                    new LatLng(10.706982, 122.567294),
                                    new LatLng(10.707799, 122.567133),
                                    new LatLng(10.708521, 122.568034),
                                    new LatLng(10.708637, 122.567873)));
                    break;
                  case "Gaisano City":
                    List<Vertex> path4 = getShortestPathTo(A7);
                    distanceview.setText(  path4 +"\n " + A7.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A9_A7)));




                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692438, 122.573488))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .title("Start Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692332, 122.573363))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707551, 122.567030))
                            .icon(vectorToBitmap(R.drawable.amupagidni))
                            .title("End Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707906, 122.567290))
                            .icon(vectorToBitmap(R.drawable.getoff))
                            .title("Get off - Andoks"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.692438, 122.573488),
                                    new LatLng(10.692332, 122.573363),

                                    new LatLng(10.693017, 122.572607),
                                    new LatLng(10.693973, 122.571060),
                                    new LatLng(10.694859, 122.570572),

                                    new LatLng(10.696567, 122.569140),
                                    new LatLng(10.700070, 122.569132),

                                    new LatLng(10.701639, 122.569121),
                                    new LatLng(10.702039, 122.568212),
                                    new LatLng(10.704205, 122.567938),


                                    new LatLng(10.704205, 122.567938),
                                    new LatLng(10.707798, 122.567121),
                                    new LatLng(10.707906, 122.567290),
                                    new LatLng(10.707654, 122.567301),
                                    new LatLng(10.707551, 122.567030)));
                    break;
                  case "University of San Agustin":
                    List<Vertex> path5 = getShortestPathTo(A8);
                    distanceview.setText(  path5 +"\n " + A8.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A9_A8)));



                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692438, 122.573488))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .title("Start Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692332, 122.573363))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704188, 122.568021))
                            .icon(vectorToBitmap(R.drawable.getoff))
                            .title("Get off - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700575, 122.563030))
                            .icon(vectorToBitmap(R.drawable.amupagidni))
                            .title("End Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704200, 122.567914))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - Lapaz Jeep"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.692438, 122.573488),
                                    new LatLng(10.692332, 122.573363),

                                    new LatLng(10.693017, 122.572607),
                                    new LatLng(10.693973, 122.571060),
                                    new LatLng(10.694859, 122.570572),

                                    new LatLng(10.696567, 122.569140),
                                    new LatLng(10.700070, 122.569132),

                                    new LatLng(10.701639, 122.569121),
                                    new LatLng(10.702039, 122.568212),
                                    new LatLng(10.704188, 122.568021),
                                    new LatLng(10.704200, 122.567914)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.704200, 122.567914),
                                    new LatLng(10.701853, 122.568221),
                                    new LatLng(10.701640, 122.567976),
                                    new LatLng(10.700965, 122.563073),
                                    new LatLng(10.700575, 122.563030)));
                    break;
                  case "Robinson City":
                    List<Vertex> path6 = getShortestPathTo(A1);
                    distanceview.setText(  path6 +"\n " + A1.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A9_A1)));




                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692438, 122.573488))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .title("Start Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692332, 122.573363))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695108, 122.565321))
                            .icon(vectorToBitmap(R.drawable.amupagidni))
                            .title("End Point - Robinson City"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.692394, 122.573460),
                                    new LatLng(10.692293, 122.573348),

                                    new LatLng(10.691797, 122.573874),
                                    new LatLng(10.691670, 122.573692),
                                    new LatLng(10.692007, 122.571566),

                                    new LatLng(10.692128, 122.569136),
                                    new LatLng(10.696505, 122.569086),

                                    new LatLng(10.695393, 122.565267),
                                    new LatLng(10.695108, 122.565321)));

                    break;
                  case "University of Iloilo":
                    List<Vertex> path7 = getShortestPathTo(A2);
                    distanceview.setText(  path7 +"\n " + A2.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A9_A2)));




                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692438, 122.573488))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .title("Start Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692332, 122.573363))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691826, 122.569780))
                            .icon(vectorToBitmap(R.drawable.amupagidni))
                            .title("End Point - University of Iloilo"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.692394, 122.573460),
                                    new LatLng(10.692293, 122.573348),

                                    new LatLng(10.691797, 122.573874),
                                    new LatLng(10.691670, 122.573692),
                                    new LatLng(10.692007, 122.571566),

                                    new LatLng(10.692117, 122.569872),
                                    new LatLng(10.691826, 122.569780)));
                    break;
                  case "SM City":
                    List<Vertex> path8 = getShortestPathTo(A3);
                    distanceview.setText(  path8 +"\n " + A3.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A9_A3)));



                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692394, 122.573460))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .title("Start Point - Plaza Libertad"));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692293, 122.573348))
                            .icon(vectorToBitmap(R.drawable.tryridez))
                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713669, 122.551816))
                            .icon(vectorToBitmap(R.drawable.amupagidni))
                            .title("End Point - University of Iloilo"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.692394, 122.573460),
                                    new LatLng(10.692293, 122.573348),

                                    new LatLng(10.691797, 122.573874),
                                    new LatLng(10.691670, 122.573692),
                                    new LatLng(10.692007, 122.571566),

                                    new LatLng(10.692117, 122.569872),


                                    new LatLng(10.692141, 122.569129),
                                    new LatLng(10.696487, 122.569072),
                                    new LatLng(10.694326, 122.561749),

                                    new LatLng(10.697264, 122.561747),
                                    new LatLng(10.696556, 122.554871),
                                    new LatLng(10.698800, 122.554581),
                                    new LatLng(10.700204, 122.554152),

                                    new LatLng(10.709033, 122.551824),
                                    new LatLng(10.710670, 122.551715),
                                    new LatLng(10.713495, 122.552369),
                                    new LatLng(10.713669, 122.551816)));



                    break;
                }
            }
            switch (start) {
              case "Grand Xing Imperial":
                computePaths(A4);
                switch (destination) {

                  case "Plaza Libertad":
                    List<Vertex> path12 = getShortestPathTo(A9);
                    distanceview.setText(  path12 +"\n " + A9.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A4_A9)));



                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700131, 122.569360))
                            .icon(vectorToBitmap(R.drawable.brattt))
                            .title("Start Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700079, 122.569069))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Balaurte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692439, 122.573500))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Plaza Libertad"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.700131, 122.569360),
                                    new LatLng(10.700079, 122.569069),


                                    new LatLng(10.696480, 122.569090),
                                    new LatLng(10.694746, 122.570504),
                                    new LatLng(10.693939, 122.571019),

                                    new LatLng(10.692879, 122.572703),
                                    new LatLng(10.692304, 122.573352),
                                    new LatLng(10.692431, 122.573470)));
                    break;
                  case "Grand Xing Imperial":
                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                    pathView2.setText(Html.fromHtml("You are currently in Grand Xing Imperial"
                            , Html.FROM_HTML_MODE_COMPACT));
                    break;

                  case "Hall of Justice":
                    List<Vertex> path13 = getShortestPathTo(A5);
                    distanceview.setText(  path13 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A4_A5)));




                    mMap.addMarker(new MarkerOptions()

                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700131, 122.569360))
                            .title("Start Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700174, 122.569134))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Balaurte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.704237, 122.568146))
                            .title("End Point - Hall of Justice"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700131, 122.569360),
                                    new LatLng(10.700174, 122.569134),


                                    new LatLng(10.701624, 122.569155),
                                    new LatLng(10.702040, 122.568205),
                                    new LatLng(10.704200, 122.567948),
                                    new LatLng(10.704237, 122.568146)));
                    break;




                  case "Lapaz Public Market":
                    List<Vertex> path15 = getShortestPathTo(A6);
                    distanceview.setText(  path15 +"\n " + A6.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A4_A6)));



                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700131, 122.569360))
                            .title("Start Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700174, 122.569134))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.708611, 122.567923))
                            .title("End Point - Lapaz Public Market"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.700131, 122.569360),
                                    new LatLng(10.700174, 122.569134),


                                    new LatLng(10.701624, 122.569155),
                                    new LatLng(10.702040, 122.568205),
                                    new LatLng(10.704200, 122.567948),

                                    new LatLng(10.705223, 122.567883),
                                    new LatLng(10.707014, 122.567280),
                                    new LatLng(10.707794, 122.567103),
                                    new LatLng(10.708474, 122.568020),
                                    new LatLng(10.708611, 122.567923)));
                    break;




                  case "Gaisano City":
                    List<Vertex> path16 = getShortestPathTo(A7);
                    distanceview.setText(  path16 +"\n " + A7.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A4_A7)));



                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700131, 122.569360))
                            .title("Start Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.700174, 122.569134))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.707543, 122.567042))
                            .title("End Point - Gaisano City"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700131, 122.569360),
                                    new LatLng(10.700174, 122.569134),


                                    new LatLng(10.701624, 122.569155),
                                    new LatLng(10.702040, 122.568205),
                                    new LatLng(10.704200, 122.567948),

                                    new LatLng(10.705223, 122.567883),
                                    new LatLng(10.707014, 122.567280),
                                    new LatLng(10.707794, 122.567103),
                                    new LatLng(10.707880, 122.567262),

                                    new LatLng(10.707801, 122.567313),
                                    new LatLng(10.707543, 122.567042)));
                    break;

                  case "University of San Agustin":
                    List<Vertex> path17 = getShortestPathTo(A8);
                    distanceview.setText(  path17 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A4_A8)));




                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700131, 122.569360))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700174, 122.569134))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707543, 122.567042))
                            .title("End Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704365, 122.567992))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700477, 122.562983))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Univeristy of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704374, 122.567883))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700131, 122.569360),
                                    new LatLng(10.700174, 122.569134),


                                    new LatLng(10.701624, 122.569155),
                                    new LatLng(10.702040, 122.568205),
                                    new LatLng(10.704365, 122.567992),
                                    new LatLng(10.704374, 122.567883)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.704374, 122.567883),
                                    new LatLng(10.702092, 122.568128),
                                    new LatLng(10.701753, 122.568294),

                                    new LatLng(10.701584, 122.567811),
                                    new LatLng(10.700888, 122.562919),
                                    new LatLng(10.700477, 122.562983)));
                    break;



                  case "Robinson City":
                    List<Vertex> path18 = getShortestPathTo(A1);
                    distanceview.setText(  path18 +"\n " + A1.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A4_A1)));



                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700124, 122.569259))
                            .title("Start Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.700160, 122.569068))
                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.695108, 122.565425))
                            .title("End Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696491, 122.568963))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.697052, 122.569012))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Hua Siong College of Iloilo"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700124, 122.569259),
                                    new LatLng(10.700160, 122.569068),


                                    new LatLng(10.697052, 122.569012),

                                    new LatLng(10.696509, 122.568948),
                                    new LatLng(10.695461, 122.565420),
                                    new LatLng(10.695108, 122.565425)));
                    break;


                  case "University of Iloilo":
                    List<Vertex> path19 = getShortestPathTo(A2);
                    distanceview.setText(  path19 +"\n " + A2.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A4_A2)));



                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700124, 122.569259))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700160, 122.569068))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691904, 122.569750))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of Iloilo"));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696362, 122.568942))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM Mandurriao Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696780, 122.569015))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Hua Siong College of Iloilo"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700124, 122.569259),
                                    new LatLng(10.700160, 122.569068),


                                    new LatLng(10.697052, 122.569012),

                                    new LatLng(10.696362, 122.568942),
                                    new LatLng(10.696375, 122.569065),
                                    new LatLng(10.692036, 122.569096),
                                    new LatLng(10.692015, 122.569769),
                                    new LatLng(10.691904, 122.569750)));
                    break;




                  case "SM City":
                    List<Vertex> path20 = getShortestPathTo(A3);
                    distanceview.setText(  path20 +"\n " + A3.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A4_A3)));



                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700124, 122.569259))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700174, 122.569134))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713624, 122.551890))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700874, 122.562875))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM Mandurriao Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704200, 122.567948))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700916, 122.562969))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - San Agustin University Front"));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.700131, 122.569360),
                                    new LatLng(10.700174, 122.569134),


                                    new LatLng(10.701624, 122.569155),
                                    new LatLng(10.702040, 122.568205),
                                    new LatLng(10.704200, 122.567948)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(


                                    new LatLng(10.704200, 122.567948),
                                    new LatLng(10.704210, 122.567914),
                                    new LatLng(10.703588, 122.567931),


                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701642, 122.568062),

                                    new LatLng(10.700916, 122.562969),
                                    new LatLng(10.700874, 122.562875),


                                    new LatLng(10.700897, 122.562851),


                                    new LatLng(10.699866, 122.554286),
                                    new LatLng(10.709320, 122.551749),
                                    new LatLng(10.711334, 122.551774),
                                    new LatLng(10.713492, 122.552384),
                                    new LatLng(10.713624, 122.551890)));
                    break;
                }
            }
            switch (start) {
              case "Hall of Justice":
                computePaths(A5);
                switch (destination) {

                  case "Plaza Libertad":
                    if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                    List<Vertex> path12 = getShortestPathTo(A9);
                    distanceview.setText(  path12 +"\n " + A9.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A5_A9)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704612, 122.568048))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704554, 122.567887))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692439, 122.573500))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701388, 122.567631))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));





                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(


                                    new LatLng(10.704612, 122.568048),
                                    new LatLng(10.704554, 122.567887),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),

                                    new LatLng(10.701667, 122.567593),
                                    new LatLng(10.701388, 122.567631)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701687, 122.568416),


                                    new LatLng(10.701497, 122.569060),
                                    new LatLng(10.696480, 122.569090),
                                    new LatLng(10.694746, 122.570504),
                                    new LatLng(10.693939, 122.571019),

                                    new LatLng(10.692879, 122.572703),
                                    new LatLng(10.692304, 122.573352),
                                    new LatLng(10.692431, 122.573470)));
                    break;

                  case "Grand Xing Imperial":
                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                    List<Vertex> path22 = getShortestPathTo(A4);
                    distanceview.setText(  path22 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A5_A4)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704612, 122.568048))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704554, 122.567887))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700065, 122.569364))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.cross))

                            .position(new LatLng(10.701667, 122.567593))
                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701388, 122.567631))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(


                                    new LatLng(10.704612, 122.568048),
                                    new LatLng(10.704554, 122.567887),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),

                                    new LatLng(10.701667, 122.567593),
                                    new LatLng(10.701388, 122.567631)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701687, 122.568416),


                                    new LatLng(10.701497, 122.569060),
                                    new LatLng(10.700075, 122.569047),
                                    new LatLng(10.700065, 122.569364)));

                    break;


                  case "Hall of Justice":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path23 = getShortestPathTo(A5);
                    distanceview.setText(  path23 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml("You are currently in Hall of Justice"
                            , Html.FROM_HTML_MODE_COMPACT));
                    break;

                  case "Lapaz Public Market":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path24 = getShortestPathTo(A6);
                    distanceview.setText(  path24 +"\n " + A6.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A5_A6)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.704192, 122.568105))
                            .title("Start Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704221, 122.567952))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.708597, 122.567865))
                            .title("End Point - Grand Xing Imperial"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(


                                    new LatLng(10.704192, 122.568105),
                                    new LatLng(10.704221, 122.567952),

                                    new LatLng(10.705145, 122.567915),
                                    new LatLng(10.706983, 122.567294),
                                    new LatLng(10.707774, 122.567120),

                                    new LatLng(10.708457, 122.567988),
                                    new LatLng(10.708597, 122.567865)));

                    break;

                  case "Gaisano City":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path25 = getShortestPathTo(A7);
                    distanceview.setText(  path25 +"\n " + A7.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A5_A7)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704192, 122.568105))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704221, 122.567952))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707540, 122.567042))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Gaisano City"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(


                                    new LatLng(10.704192, 122.568105),
                                    new LatLng(10.704221, 122.567952),

                                    new LatLng(10.705145, 122.567915),
                                    new LatLng(10.706983, 122.567294),

                                    new LatLng(10.707775, 122.567120),
                                    new LatLng(10.707875, 122.567257),
                                    new LatLng(10.707693, 122.567321),
                                    new LatLng(10.707540, 122.567042)));
                    break;

                  case "University of San Agustin":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path26 = getShortestPathTo(A8);
                    distanceview.setText(  path26 +"\n " + A8.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A5_A8)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.704612, 122.568048))
                            .title("Start Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704554, 122.567887))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700458, 122.562994))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Grand Xing Imperial"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(


                                    new LatLng(10.704612, 122.568048),
                                    new LatLng(10.704554, 122.567887),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),

                                    new LatLng(10.701433, 122.566820),
                                    new LatLng(10.700897, 122.562851),
                                    new LatLng(10.700458, 122.562994)));


                    break;

                  case "Robinson City":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path27 = getShortestPathTo(A1);
                    distanceview.setText(  path27 +"\n " + A1.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A5_A1)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704231, 122.568205))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704326, 122.567918))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695108, 122.565425))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701388, 122.567631))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.697052, 122.569012))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Hua Siong College of Iloilo"));




                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.704231, 122.568205),
                                    new LatLng(10.704326, 122.567918),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),
                                    new LatLng(10.701667, 122.567593),
                                    new LatLng(10.701388, 122.567631)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701717, 122.568413),
                                    new LatLng(10.701490, 122.569084),

                                    new LatLng(10.697052, 122.569012),

                                    new LatLng(10.696509, 122.568948),
                                    new LatLng(10.695461, 122.565420),
                                    new LatLng(10.695108, 122.565425)));
                    break;

                  case "University of Iloilo":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path28 = getShortestPathTo(A2);
                    distanceview.setText(  path28 +"\n " + A2.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A5_A2)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708923, 122.567588))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708822, 122.567388))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691888, 122.569454))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of Iloilo"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701388, 122.567631))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.697052, 122.569012))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Hua Siong College of Iloilo"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.696315, 122.568802))
                            .title("Ride - Lapaz Jeep"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708923, 122.567588),
                                    new LatLng(10.708822, 122.567388),
                                    new LatLng(10.709233, 122.566734),//sm

                                    new LatLng(10.708972, 122.566753),
                                    new LatLng(10.708223, 122.566972),
                                    new LatLng(10.705134, 122.567861),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),
                                    new LatLng(10.701667, 122.567593),
                                    new LatLng(10.701388, 122.567631)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701717, 122.568413),
                                    new LatLng(10.701490, 122.569084),

                                    new LatLng(10.697052, 122.569012),

                                    new LatLng(10.696449, 122.568754),
                                    new LatLng(10.696315, 122.568802),

                                    new LatLng(10.696386, 122.569070),
                                    new LatLng(10.692160, 122.569116),
                                    new LatLng(10.692036, 122.569054),
                                    new LatLng(10.692012, 122.569427),
                                    new LatLng(10.691888, 122.569454)));
                    break;

                  case "SM City":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path29 = getShortestPathTo(A3);
                    distanceview.setText(  path29 +"\n " + A3.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A5_A3)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.704612, 122.568048))
                            .title("Start Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704554, 122.567887))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700882, 122.562910))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Salt Gastro Lounge"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700849, 122.562834))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700849, 122.562834))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - SM City"));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(


                                    new LatLng(10.704612, 122.568048),
                                    new LatLng(10.704554, 122.567887),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),

                                    new LatLng(10.701433, 122.566820),
                                    new LatLng(10.700897, 122.562851),


                                    new LatLng(10.699866, 122.554286),
                                    new LatLng(10.709320, 122.551749),
                                    new LatLng(10.711334, 122.551774),
                                    new LatLng(10.713492, 122.552384),
                                    new LatLng(10.713624, 122.551890)));
                    break;
                }
            }

            switch (start) {
              case "Lapaz Public Market":
                computePaths(A5);

                switch (destination) {
                  case "Plaza Libertad":
                    List<Vertex> path32 = getShortestPathTo(A9);
                    distanceview.setText(  path32 +"\n " + A9.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A6_A9)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708923, 122.567588))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708822, 122.567388))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692439, 122.573500))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701388, 122.567631))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));




                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708923, 122.567588),
                                    new LatLng(10.708822, 122.567388),
                                    new LatLng(10.709233, 122.566734),//sm

                                    new LatLng(10.708972, 122.566753),
                                    new LatLng(10.708223, 122.566972),
                                    new LatLng(10.705134, 122.567861),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),

                                    new LatLng(10.701667, 122.567593),
                                    new LatLng(10.701388, 122.567631)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701687, 122.568416),


                                    new LatLng(10.701497, 122.569060),
                                    new LatLng(10.696480, 122.569090),
                                    new LatLng(10.694746, 122.570504),
                                    new LatLng(10.693939, 122.571019),

                                    new LatLng(10.692879, 122.572703),
                                    new LatLng(10.692304, 122.573352),
                                    new LatLng(10.692431, 122.573470)));
                    break;

                  case "Grand Xing Imperial":
                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                    List<Vertex> path33 = getShortestPathTo(A4);
                    distanceview.setText(  path33 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A6_A4)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708923, 122.567588))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.708822, 122.567388))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700069, 122.569369))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.701388, 122.567631))
                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708923, 122.567588),
                                    new LatLng(10.708822, 122.567388),
                                    new LatLng(10.709233, 122.566734),//sm

                                    new LatLng(10.708972, 122.566753),
                                    new LatLng(10.708223, 122.566972),
                                    new LatLng(10.705134, 122.567861),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),

                                    new LatLng(10.701667, 122.567593),
                                    new LatLng(10.701388, 122.567631)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701687, 122.568416),


                                    new LatLng(10.701497, 122.569060),
                                    new LatLng(10.700038, 122.569065),

                                    new LatLng(10.700069, 122.569369)));
                    break;

                  case "Hall of Justice":
                    List<Vertex> path34 = getShortestPathTo(A5);
                    distanceview.setText(  path34 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A6_A5)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.708923, 122.567588))
                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708822, 122.567388))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704236, 122.568203))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Hall of Justice"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708923, 122.567588),
                                    new LatLng(10.708822, 122.567388),
                                    new LatLng(10.709233, 122.566734),//sm

                                    new LatLng(10.708972, 122.566753),
                                    new LatLng(10.708223, 122.566972),
                                    new LatLng(10.705134, 122.567861),

                                    new LatLng(10.704222, 122.567909),
                                    new LatLng(10.704236, 122.568203)));


                    break;

                  case "Lapaz Public Market":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    pathView2.setText(Html.fromHtml("You are currently in Lapaz Public Market"
                            , Html.FROM_HTML_MODE_COMPACT));
                    break;
                  case "Gaisano City":
                    List<Vertex> path35 = getShortestPathTo(A7);
                    distanceview.setText(  path35 +"\n " + A7.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A6_A7)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708925, 122.567585))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708777, 122.567407))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.707360, 122.566889))
                            .title("End Point - Hall of Justice"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708925, 122.567585),
                                    new LatLng(10.708777, 122.567407),
                                    new LatLng(10.709204, 122.566715),//sm

                                    new LatLng(10.707380, 122.567155),
                                    new LatLng(10.707360, 122.566889)));
                    break;
                  case "University of San Agustin":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path36 = getShortestPathTo(A8);
                    distanceview.setText(  path36 +"\n " + A8.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A6_A8)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708925, 122.567585))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708777, 122.567407))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700556, 122.563010))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of San Agustin"));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708925, 122.567585),
                                    new LatLng(10.708777, 122.567407),
                                    new LatLng(10.709204, 122.566715),//sm


                                    new LatLng(10.706729, 122.567347),
                                    new LatLng(10.705084, 122.567878),
                                    new LatLng(10.702086, 122.568131),//sm
                                    new LatLng(10.701778, 122.568278),
                                    new LatLng(10.701578, 122.567817),
                                    new LatLng(10.700893, 122.562946),//sm
                                    new LatLng(10.700556, 122.563010)));
                    break;
                  case "Robinson City":

                    List<Vertex> path37 = getShortestPathTo(A1);
                    distanceview.setText(  path37 +"\n " + A1.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A6_A1)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708923, 122.567588))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708822, 122.567388))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695108, 122.565425))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701388, 122.567631))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .position(new LatLng(10.697052, 122.569012))
                            .title("Get off - Hua Siong College of Iloilo"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708923, 122.567588),
                                    new LatLng(10.708822, 122.567388),
                                    new LatLng(10.709233, 122.566734),//sm

                                    new LatLng(10.708972, 122.566753),
                                    new LatLng(10.708223, 122.566972),
                                    new LatLng(10.705134, 122.567861),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),
                                    new LatLng(10.701667, 122.567593),
                                    new LatLng(10.701388, 122.567631)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701717, 122.568413),
                                    new LatLng(10.701490, 122.569084),

                                    new LatLng(10.697052, 122.569012),

                                    new LatLng(10.696509, 122.568948),
                                    new LatLng(10.695461, 122.565420),
                                    new LatLng(10.695108, 122.565425)));
                    break;
                  case "University of Iloilo":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path38 = getShortestPathTo(A2);
                    distanceview.setText(  path38 +"\n " + A2.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A6_A2)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.708923, 122.567588))
                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.708822, 122.567388))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691888, 122.569454))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of Iloilo"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.701388, 122.567631))
                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .position(new LatLng(10.697052, 122.569012))
                            .title("Get off - Hua Siong College of Iloilo"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.696315, 122.568802))
                            .title("Ride - Lapaz Jeep"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708923, 122.567588),
                                    new LatLng(10.708822, 122.567388),
                                    new LatLng(10.709233, 122.566734),//sm

                                    new LatLng(10.708972, 122.566753),
                                    new LatLng(10.708223, 122.566972),
                                    new LatLng(10.705134, 122.567861),

                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),
                                    new LatLng(10.701667, 122.567593),
                                    new LatLng(10.701388, 122.567631)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701717, 122.568413),
                                    new LatLng(10.701490, 122.569084),

                                    new LatLng(10.697052, 122.569012),

                                    new LatLng(10.696449, 122.568754),
                                    new LatLng(10.696315, 122.568802),

                                    new LatLng(10.696386, 122.569070),
                                    new LatLng(10.692160, 122.569116),
                                    new LatLng(10.692036, 122.569054),
                                    new LatLng(10.692012, 122.569427),
                                    new LatLng(10.691888, 122.569454)));
                    break;
                  case "SM City":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path39 = getShortestPathTo(A3);
                    distanceview.setText(  path39 +"\n " + A3.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A6_A3)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.708925, 122.567585))
                            .title("Start Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708777, 122.567407))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.700852, 122.562656))
                            .title("Ride - SM Mandurriao Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713630, 122.551940))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700905, 122.562797))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Salt Gastro Lounge"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.708925, 122.567585),
                                    new LatLng(10.708777, 122.567407),
                                    new LatLng(10.709204, 122.566715),//sm


                                    new LatLng(10.706729, 122.567347),
                                    new LatLng(10.705084, 122.567878),
                                    new LatLng(10.702086, 122.568131),//sm
                                    new LatLng(10.701778, 122.568278),
                                    new LatLng(10.701578, 122.567817),
                                    new LatLng(10.700893, 122.562946),//sm
                                    new LatLng(10.700905, 122.562797),//sm
                                    new LatLng(10.700852, 122.562656),//sm


                                    new LatLng(10.699860, 122.554278),
                                    new LatLng(10.709148, 122.551794),
                                    new LatLng(10.711046, 122.551756),//sm
                                    new LatLng(10.713509, 122.552385),
                                    new LatLng(10.713630, 122.551940)));
                    break;
                }
            }

            switch (start) {
              case "Gaisano City":
                computePaths(A7);

                switch (destination) {
                  case "Plaza Libertad":
                    if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                    List<Vertex> path42 = getShortestPathTo(A9);
                    distanceview.setText(  path42 +"\n " + A9.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A7_A9)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707053, 122.567042))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707142, 122.567207))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692439, 122.573500))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("End Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701388, 122.567631))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.707053, 122.567042),
                                    new LatLng(10.707142, 122.567207),
                                    new LatLng(10.705148, 122.567840),//sm
                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),

                                    new LatLng(10.701667, 122.567593)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701687, 122.568416),


                                    new LatLng(10.701497, 122.569060),
                                    new LatLng(10.696480, 122.569090),
                                    new LatLng(10.694746, 122.570504),
                                    new LatLng(10.693939, 122.571019),

                                    new LatLng(10.692879, 122.572703),
                                    new LatLng(10.692304, 122.573352),
                                    new LatLng(10.692431, 122.573470)));
                    break;

                  case "Grand Xing Imperial":
                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                    List<Vertex> path43 = getShortestPathTo(A4);
                    distanceview.setText(  path43 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A7_A4)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.707053, 122.567042))
                            .title("Start Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707142, 122.567207))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699679, 122.569241))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701667, 122.567593))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Tibiao bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701388, 122.567631))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.707053, 122.567042),
                                    new LatLng(10.707142, 122.567207),
                                    new LatLng(10.705148, 122.567840),//sm
                                    new LatLng(10.703588, 122.567931),
                                    new LatLng(10.701883, 122.568221),
                                    new LatLng(10.701625, 122.568017),

                                    new LatLng(10.701667, 122.567593)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.701388, 122.567631),
                                    new LatLng(10.701687, 122.568416),
                                    new LatLng(10.701497, 122.569060),

                                    new LatLng(10.699643, 122.569077),
                                    new LatLng(10.699679, 122.569241)));
                    break;
                  case "Hall of Justice":

                    List<Vertex> path44 = getShortestPathTo(A5);
                    distanceview.setText(  path44 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A7_A5)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.707053, 122.567042))
                            .title("Start Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707142, 122.567207))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.704197, 122.568175))
                            .title("End Point - Hall of Justice"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.707053, 122.567042),
                                    new LatLng(10.707142, 122.567207),
                                    new LatLng(10.705148, 122.567840),//sm


                                    new LatLng(10.704136, 122.567878),
                                    new LatLng(10.704197, 122.568175)));


                    break;

                  case "Lapaz Public Market":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;

                    List<Vertex> path45 = getShortestPathTo(A6);
                    distanceview.setText(  path45 +"\n " + A6.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A7_A6)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.707552, 122.567017))
                            .title("Start Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707602, 122.567234))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Gaisano"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.708598, 122.567953))
                            .title("End Point - Lapaz Public Market"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.707552, 122.567017),
                                    new LatLng(10.707602, 122.567234),
                                    new LatLng(10.708474, 122.568071),//sm


                                    new LatLng(10.708598, 122.567953)));
                    break;
                  case "Gaisano City":
                    pathView2.setText(Html.fromHtml("You are currently in Gaisano City"
                            , Html.FROM_HTML_MODE_COMPACT));
                    break;
                  case "University of San Agustin":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;

                    List<Vertex> path46 = getShortestPathTo(A8);
                    distanceview.setText(  path46 +"\n " + A8.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A7_A8)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707053, 122.567042))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707142, 122.567207))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700658, 122.563011))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of San Agustin"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.707053, 122.567042),
                                    new LatLng(10.707142, 122.567207),
                                    new LatLng(10.705148, 122.567840),//sm


                                    new LatLng(10.704136, 122.567878),
                                    new LatLng(10.701828, 122.568262),
                                    new LatLng(10.701659, 122.568021),
                                    new LatLng(10.700884, 122.562925),
                                    new LatLng(10.700658, 122.563011)));
                    break;
                  case "Robinson City":

                    List<Vertex> path47 = getShortestPathTo(A1);
                    distanceview.setText(  path47 +"\n " + A1.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A7_A1)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707053, 122.567042))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707142, 122.567207))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695055, 122.565363))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696970, 122.569002))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Hua Siong College of Iloilo"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696500, 122.568923))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701659, 122.568021))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Tibiao Bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701330, 122.567638))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.707101, 122.567121),
                                    new LatLng(10.707104, 122.567207),
                                    new LatLng(10.705073, 122.567864),


                                    new LatLng(10.701828, 122.568262),
                                    new LatLng(10.701659, 122.568021),
                                    new LatLng(10.701625, 122.567590),
                                    new LatLng(10.701330, 122.567638),

                                    new LatLng(10.701704, 122.568416),
                                    new LatLng(10.701483, 122.569070),

                                    new LatLng(10.696970, 122.569002),

                                    new LatLng(10.696500, 122.568923),
                                    new LatLng(10.695403, 122.565315),
                                    new LatLng(10.695055, 122.565363)));
                    break;
                  case "University of Iloilo":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path48 = getShortestPathTo(A2);
                    distanceview.setText(  path48 +"\n " + A2.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A7_A2)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.707053, 122.567042))
                            .title("Start Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707142, 122.567207))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.691805, 122.569797))
                            .title("End Point - University of Iloilo"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696970, 122.569002))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Hua Siong College of Iloilo"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701659, 122.568021))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Tibiao Bakery"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696326, 122.568868))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701330, 122.567638))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696488, 122.568844))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Rose Pharmacy"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.707101, 122.567121),
                                    new LatLng(10.707104, 122.567207),
                                    new LatLng(10.705073, 122.567864),


                                    new LatLng(10.701828, 122.568262),
                                    new LatLng(10.701659, 122.568021),
                                    new LatLng(10.701625, 122.567590),
                                    new LatLng(10.701330, 122.567638),

                                    new LatLng(10.701704, 122.568416),
                                    new LatLng(10.701483, 122.569070),

                                    new LatLng(10.696970, 122.569002),

                                    new LatLng(10.696488, 122.568844),
                                    new LatLng(10.696326, 122.568868),
                                    new LatLng(10.696384, 122.569074),

                                    new LatLng(10.692021, 122.569084),
                                    new LatLng(10.692022, 122.569809),
                                    new LatLng(10.691805, 122.569797)));
                    break;
                  case "SM City":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path49 = getShortestPathTo(A3);
                    distanceview.setText(  path49 +"\n " + A3.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A7_A3)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707053, 122.567042))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707142, 122.567207))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700959, 122.563336))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM Mandurriao"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713647, 122.551893))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.701002, 122.563041))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Salt Gastro Lounge"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.707053, 122.567042),
                                    new LatLng(10.707142, 122.567207),
                                    new LatLng(10.705148, 122.567840),//sm


                                    new LatLng(10.704136, 122.567878),
                                    new LatLng(10.701828, 122.568262),
                                    new LatLng(10.701659, 122.568021),
                                    new LatLng(10.700884, 122.562925)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.700959, 122.563336),


                                    new LatLng(10.699877, 122.554289),
                                    new LatLng(10.709341, 122.551754),
                                    new LatLng(10.711125, 122.551758),
                                    new LatLng(10.713510, 122.552376),
                                    new LatLng(10.713647, 122.551893)));
                    break;

                }
            }

            switch (start) {
              case "University of San Agustin":
                computePaths(A8);

                switch (destination) {
                  case "Plaza Libertad":
                    if (R.id.spinner == 1 && R.id.spinner2 == 2) ;

                    List<Vertex> path52 = getShortestPathTo(A9);
                    distanceview.setText(  path52 +"\n " + A9.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A8_A9)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700633, 122.563004))
                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.700805, 122.562991))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.692439, 122.573500))
                            .title("End Point - Plaza Libertad"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700633, 122.563004),
                                    new LatLng(10.700805, 122.562991),
                                    new LatLng(10.701539, 122.568179),//sm

                                    new LatLng(10.701510, 122.569008),
                                    new LatLng(10.696680, 122.569024),
                                    new LatLng(10.693914, 122.571018),

                                    new LatLng(10.692886, 122.572603),
                                    new LatLng(10.692307, 122.573306),
                                    new LatLng(10.692439, 122.573500)));
                    break;
                  case "Grand Xing Imperial":
                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;

                    List<Vertex> path53 = getShortestPathTo(A4);
                    distanceview.setText(  path53 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A8_A4)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700633, 122.563004))
                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700805, 122.562991))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.700086, 122.569397))
                            .title("End Point - Grand Xing Imperial"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700633, 122.563004),
                                    new LatLng(10.700805, 122.562991),
                                    new LatLng(10.701539, 122.568179),//sm

                                    new LatLng(10.701499, 122.568980),
                                    new LatLng(10.700113, 122.569017),
                                    new LatLng(10.700086, 122.569397)));
                    break;
                  case "Hall of Justice":

                    List<Vertex> path54 = getShortestPathTo(A5);
                    distanceview.setText(  path54 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A8_A5)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700633, 122.563004))
                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700805, 122.562991))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.699769, 122.569183))
                            .title("Ride - Lapaz Jeep "));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.704385, 122.568165))
                            .title("End Point - Hall of Justice "));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700633, 122.563004),
                                    new LatLng(10.700805, 122.562991),
                                    new LatLng(10.701539, 122.568179),//sm
                                    new LatLng(10.701682, 122.568380),

                                    new LatLng(10.701499, 122.568980),

                                    new LatLng(10.700113, 122.569017),

                                    new LatLng(10.699767, 122.569034),
                                    new LatLng(10.699769, 122.569183
                                    ),

                                    new LatLng(10.701610, 122.569162),
                                    new LatLng(10.702151, 122.568185),
                                    new LatLng(10.704358, 122.567966),

                                    new LatLng(10.704385, 122.568165)));
                    break;

                  case "Lapaz Public Market":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;

                    List<Vertex> path55 = getShortestPathTo(A6);
                    distanceview.setText(  path55 +"\n " + A6.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A8_A6)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700633, 122.563004))
                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700805, 122.562991))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699769, 122.569183))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep "));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708655, 122.567937))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Lapaz Public Market "));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700633, 122.563004),
                                    new LatLng(10.700805, 122.562991),
                                    new LatLng(10.701539, 122.568179),//sm
                                    new LatLng(10.701682, 122.568380),
                                    new LatLng(10.701499, 122.568980),
                                    new LatLng(10.700113, 122.569017),
                                    new LatLng(10.699767, 122.569034),
                                    new LatLng(10.699769, 122.569183)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.699769, 122.569183),
                                    new LatLng(10.701610, 122.569162),
                                    new LatLng(10.702151, 122.568185),
                                    new LatLng(10.704358, 122.567966),
                                    new LatLng(10.704358, 122.567966),
                                    new LatLng(10.705179, 122.567891),
                                    new LatLng(10.707773, 122.567082),

                                    new LatLng(10.708450, 122.567971),
                                    new LatLng(10.708449, 122.568056),
                                    new LatLng(10.708655, 122.567937)));
                    break;
                  case "Gaisano City":

                    List<Vertex> path56 = getShortestPathTo(A7);
                    distanceview.setText(  path56 +"\n " + A7.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A8_A7)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700633, 122.563004))
                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700805, 122.562991))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699769, 122.569183))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep "));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.707548, 122.566971))
                            .title("End Point - Gaisano City "));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707899, 122.567274))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Andoks "));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700633, 122.563004),
                                    new LatLng(10.700805, 122.562991),
                                    new LatLng(10.701539, 122.568179),//sm
                                    new LatLng(10.701682, 122.568380),
                                    new LatLng(10.701499, 122.568980),
                                    new LatLng(10.700113, 122.569017),
                                    new LatLng(10.699767, 122.569034)));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.699769, 122.569183),
                                    new LatLng(10.701610, 122.569162),
                                    new LatLng(10.702151, 122.568185),
                                    new LatLng(10.704358, 122.567966),
                                    new LatLng(10.704358, 122.567966),
                                    new LatLng(10.705179, 122.567891),


                                    new LatLng(10.707773, 122.567082),
                                    new LatLng(10.707899, 122.567274),
                                    new LatLng(10.707694, 122.567273),
                                    new LatLng(10.707548, 122.566971)));
                    break;
                  case "University of San Agustin":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    pathView2.setText(Html.fromHtml("You are currently in University of San Agustin"
                            , Html.FROM_HTML_MODE_COMPACT));
                    break;
                  case "Robinson City":

                    List<Vertex> path57 = getShortestPathTo(A1);
                    distanceview.setText(  path57 +"\n " + A1.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A8_A1)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700633, 122.563004))
                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.700888, 122.562977))
                            .title("Ride - Lapaz Jeep "));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.695095, 122.565328))
                            .title("End Point - Robinson City "));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700570, 122.563019),
                                    new LatLng(10.700888, 122.562977),
                                    new LatLng(10.700680, 122.561765),//sm
                                    new LatLng(10.697337, 122.561717),
                                    new LatLng(10.694162, 122.561728),
                                    new LatLng(10.695248, 122.565269),
                                    new LatLng(10.695095, 122.565328)));
                    break;
                  case "University of Iloilo":

                    List<Vertex> path58 = getShortestPathTo(A2);
                    distanceview.setText(  path58 +"\n " + A2.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A8_A2)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.700633, 122.563004))
                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700925, 122.563006))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep "));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.691868, 122.569480))
                            .title("End Point - University of Iloilo "));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700588, 122.563049),
                                    new LatLng(10.700925, 122.563006),
                                    new LatLng(10.700709, 122.561735),//sm
                                    new LatLng(10.694200, 122.561744),
                                    new LatLng(10.696386, 122.569020),
                                    new LatLng(10.692015, 122.569077),
                                    new LatLng(10.692010, 122.569469),
                                    new LatLng(10.691868, 122.569480)));
                    break;
                  case "SM City":

                    List<Vertex> path59 = getShortestPathTo(A3);
                    distanceview.setText(  path59 +"\n " + A3.minDistance + " kilometer" );
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    pathView2.setText(Html.fromHtml(getString(R.string.A8_A3)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700633, 122.563004))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700925, 122.563006))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep "));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691868, 122.569480))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of Iloilo "));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.700551, 122.563002),
                                    new LatLng(10.700904, 122.562951),
                                    new LatLng(10.699879, 122.554276),//sm
                                    new LatLng(10.708929, 122.551836),
                                    new LatLng(10.711042, 122.551731),
                                    new LatLng(10.713507, 122.552382),
                                    new LatLng(10.713665, 122.551835)));
                    break;

                }
            }
            switch (start) {
              case "Robinson City":
                computePaths(A1);

                switch (destination) {
                  case "Plaza Libertad":
                    if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                    List<Vertex> path62 = getShortestPathTo(A9);
                    distanceview.setText(  path62 +"\n " + A9.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A1_A9)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695097, 122.565337))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695327, 122.565401))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692418, 122.573484))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696725, 122.569028))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Iznart Street"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696214, 122.568401))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Goodwill Marketing"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.695097, 122.565337),
                                    new LatLng(10.695327, 122.565401),
                                    new LatLng(10.696214, 122.568401),//sm

                                    new LatLng(10.696351, 122.568406),
                                    new LatLng(10.696554, 122.568948),
                                    new LatLng(10.696725, 122.569028),

                                    new LatLng(10.696446, 122.569130),
                                    new LatLng(10.694750, 122.570495),
                                    new LatLng(10.693916, 122.571000),
                                    new LatLng(10.692917, 122.572577),
                                    new LatLng(10.692313, 122.573363),
                                    new LatLng(10.692455, 122.573521)));
                    break;
                  case "Grand Xing Imperial":

                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                    List<Vertex> path63 = getShortestPathTo(A4);
                    distanceview.setText(  path63 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A1_A4)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.695097, 122.565337))
                            .title("Start Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .position(new LatLng(10.695327, 122.565401))
                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.700077, 122.569472))
                            .title("End Point - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696725, 122.569028))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Iznart Street"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696214, 122.568401))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Goodwill Marketing"));

                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.695097, 122.565337),
                                    new LatLng(10.695327, 122.565401),
                                    new LatLng(10.696214, 122.568401),//sm

                                    new LatLng(10.696351, 122.568406),
                                    new LatLng(10.696554, 122.568948),
                                    new LatLng(10.696725, 122.569028),
                                    new LatLng(10.696739, 122.569169),
                                    new LatLng(10.700046, 122.569141),
                                    new LatLng(10.700077, 122.569472)));
                    break;
                  case "Hall of Justice":

                    List<Vertex> path64 = getShortestPathTo(A5);
                    distanceview.setText(  path64 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A1_A5)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.695097, 122.565337))
                            .title("Start Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695327, 122.565401))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704419, 122.568137))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696725, 122.569028))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Iznart Street"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696214, 122.568401))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Goodwill Marketing"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.695097, 122.565337),
                                    new LatLng(10.695327, 122.565401),
                                    new LatLng(10.696214, 122.568401),//sm

                                    new LatLng(10.696351, 122.568406),
                                    new LatLng(10.696554, 122.568948),
                                    new LatLng(10.696725, 122.569028),
                                    new LatLng(10.696739, 122.569169),
                                    new LatLng(10.700046, 122.569141),

                                    new LatLng(10.701407, 122.569097),
                                    new LatLng(10.701571, 122.569219),
                                    new LatLng(10.701924, 122.568430),
                                    new LatLng(10.702256, 122.568146),
                                    new LatLng(10.704377, 122.567947),
                                    new LatLng(10.704419, 122.568137)));
                    break;

                  case "Lapaz Public Market":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path65 = getShortestPathTo(A6);
                    distanceview.setText(  path65 +"\n " + A6.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A1_A6)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695097, 122.565337))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695327, 122.565401))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708570, 122.567861))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696725, 122.569028))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Iznart Street"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696214, 122.568401))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Goodwill Marketing"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708451, 122.567992))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Petron"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.695097, 122.565337),
                                    new LatLng(10.695327, 122.565401),
                                    new LatLng(10.696214, 122.568401),//sm

                                    new LatLng(10.696351, 122.568406),
                                    new LatLng(10.696554, 122.568948),
                                    new LatLng(10.696725, 122.569028),
                                    new LatLng(10.696739, 122.569169),
                                    new LatLng(10.700046, 122.569141),

                                    new LatLng(10.701407, 122.569097),
                                    new LatLng(10.701571, 122.569219),
                                    new LatLng(10.701924, 122.568430),
                                    new LatLng(10.702256, 122.568146),
                                    new LatLng(10.704377, 122.567947),
                                    new LatLng(10.705073, 122.567924),
                                    new LatLng(10.706988, 122.567289),
                                    new LatLng(10.707776, 122.567131),
                                    new LatLng(10.708451, 122.567992),
                                    new LatLng(10.708570, 122.567861)));
                    break;
                  case "Gaisano City":

                    List<Vertex> path66 = getShortestPathTo(A7);
                    distanceview.setText(  path66 +"\n " + A7.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A1_A7)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695097, 122.565337))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695327, 122.565401))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708570, 122.567861))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Lapaz Public Market"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696725, 122.569028))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Iznart Street"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696214, 122.568401))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Goodwill Marketing"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .position(new LatLng(10.708451, 122.567992))
                            .title("Get off - Petron"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.695097, 122.565337),
                                    new LatLng(10.695327, 122.565401),
                                    new LatLng(10.696214, 122.568401),//sm

                                    new LatLng(10.696351, 122.568406),
                                    new LatLng(10.696554, 122.568948),
                                    new LatLng(10.696725, 122.569028),
                                    new LatLng(10.696739, 122.569169),
                                    new LatLng(10.700046, 122.569141),

                                    new LatLng(10.701407, 122.569097),
                                    new LatLng(10.701571, 122.569219),
                                    new LatLng(10.701924, 122.568430),
                                    new LatLng(10.702256, 122.568146),
                                    new LatLng(10.704377, 122.567947),
                                    new LatLng(10.705073, 122.567924),
                                    new LatLng(10.706988, 122.567289),
                                    new LatLng(10.707776, 122.567131),
                                    new LatLng(10.707806, 122.567302),
                                    new LatLng(10.707606, 122.567226),
                                    new LatLng(10.707522, 122.566979)));
                    break;
                  case "University of San Agustin":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path67 = getShortestPathTo(A8);
                    distanceview.setText(  path67 +"\n " + A8.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A1_A8)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.695097, 122.565337))
                            .title("Start Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695327, 122.565401))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700624, 122.563012))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696725, 122.569028))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Iznart Street"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696214, 122.568401))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Goodwill Marketing"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .position(new LatLng(10.704370, 122.568063))
                            .title("Get off - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704346, 122.567868))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.695097, 122.565337),
                                    new LatLng(10.695327, 122.565401),
                                    new LatLng(10.696214, 122.568401),//sm

                                    new LatLng(10.696351, 122.568406),
                                    new LatLng(10.696554, 122.568948),
                                    new LatLng(10.696725, 122.569028),
                                    new LatLng(10.696739, 122.569169),
                                    new LatLng(10.700046, 122.569141),

                                    new LatLng(10.701407, 122.569097),
                                    new LatLng(10.701571, 122.569219),
                                    new LatLng(10.701924, 122.568430),
                                    new LatLng(10.702256, 122.568146),
                                    new LatLng(10.704377, 122.567947)));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.704370, 122.568063),
                                    new LatLng(10.704346, 122.567868),
                                    new LatLng(10.701849, 122.568197),
                                    new LatLng(10.701611, 122.568002),
                                    new LatLng(10.700882, 122.562952),
                                    new LatLng(10.700624, 122.563012)));
                    break;
                  case "Robinson City":
                    pathView2.setText(Html.fromHtml("You are currently in Robinson City"
                            , Html.FROM_HTML_MODE_COMPACT));

                    break;
                  case "University of Iloilo":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path68 = getShortestPathTo(A2);
                    distanceview.setText(  path68 +"\n " + A2.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A1_A2)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.695097, 122.565337))
                            .title("Start Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695327, 122.565401))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692418, 122.573484))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.cross))

                            .position(new LatLng(10.696725, 122.569028))
                            .title("Cross Pedestrian - Iznart Street"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696214, 122.568401))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Goodwill Marketing"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.695097, 122.565337),
                                    new LatLng(10.695327, 122.565401),
                                    new LatLng(10.696214, 122.568401),//sm

                                    new LatLng(10.696351, 122.568406),
                                    new LatLng(10.696554, 122.568948),
                                    new LatLng(10.696725, 122.569028),

                                    new LatLng(10.696446, 122.569130),
                                    new LatLng(10.694750, 122.570495),
                                    new LatLng(10.693916, 122.571000),
                                    new LatLng(10.692917, 122.572577),
                                    new LatLng(10.692313, 122.573363),
                                    new LatLng(10.692455, 122.573521)));
                    break;
                  case "SM City":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path69 = getShortestPathTo(A3);
                    distanceview.setText(  path69 +"\n " + A3.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A1_A3)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.695167, 122.565332))
                            .title("Start Point - Robinson City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695415, 122.565376))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713638, 122.551800))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - SM City"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.695167, 122.565332),
                                    new LatLng(10.695415, 122.565376),
                                    new LatLng(10.694278, 122.561719),//sm

                                    new LatLng(10.697300, 122.561757),
                                    new LatLng(10.696567, 122.554863),
                                    new LatLng(10.698744, 122.554598),

                                    new LatLng(10.708790, 122.551859),
                                    new LatLng(10.711310, 122.551750),
                                    new LatLng(10.713487, 122.552427),
                                    new LatLng(10.713638, 122.551800)));
                    break;

                }
            }

            switch (start) {
              case "University of Iloilo":
                computePaths(A2);

                switch (destination) {
                  case "Plaza Libertad":
                    if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                    List<Vertex> path72 = getShortestPathTo(A9);
                    distanceview.setText(  path72 +"\n " + A9.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A2_A9)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.691929, 122.569312))
                            .title("Start Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692037, 122.569323))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.692418, 122.573484))
                            .title("End Point - Plaza Libertad"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.691929, 122.569312),
                                    new LatLng(10.692037, 122.569323),
                                    new LatLng(10.692034, 122.570672),//sm

                                    new LatLng(10.691955, 122.571667),
                                    new LatLng(10.691784, 122.572525),
                                    new LatLng(10.691632, 122.573687),

                                    new LatLng(10.691821, 122.573893),
                                    new LatLng(10.692323, 122.573377),
                                    new LatLng(10.692418, 122.573484)));
                    break;
                  case "Grand Xing Imperial":

                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                    List<Vertex> path73 = getShortestPathTo(A4);
                    distanceview.setText(  path73 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A2_A4)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691952, 122.569606))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - University of Iloilo"));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692152, 122.569852))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700036, 122.569488))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Plaza Libertad"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .position(new LatLng(10.696444, 122.568726))
                            .title("Get off - Rose Pharmacy"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.691952, 122.569606),
                                    new LatLng(10.692152, 122.569852),
                                    new LatLng(10.692160, 122.569117),//sm

                                    new LatLng(10.696270, 122.569107),
                                    new LatLng(10.696489, 122.569016),
                                    new LatLng(10.696444, 122.568726),
                                    new LatLng(10.696588, 122.568995),

                                    new LatLng(10.696765, 122.569148),
                                    new LatLng(10.700052, 122.569125),
                                    new LatLng(10.700036, 122.569488)));
                    break;
                  case "Hall of Justice":

                    List<Vertex> path74 = getShortestPathTo(A5);
                    distanceview.setText(  path74 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A2_A5)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691952, 122.569606))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - University of Iloilo"));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692152, 122.569852))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704190, 122.568128))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .position(new LatLng(10.696444, 122.568726))
                            .title("Get off - Rose Pharmacy"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.691952, 122.569606),
                                    new LatLng(10.692152, 122.569852),
                                    new LatLng(10.692160, 122.569117),//sm

                                    new LatLng(10.696270, 122.569107),
                                    new LatLng(10.696489, 122.569016),
                                    new LatLng(10.696444, 122.568726),
                                    new LatLng(10.696588, 122.568995),

                                    new LatLng(10.696765, 122.569148),
                                    new LatLng(10.700052, 122.569125),
                                    new LatLng(10.701429, 122.569109),
                                    new LatLng(10.701429, 122.569109),
                                    new LatLng(10.701558, 122.569229),
                                    new LatLng(10.701985, 122.568368),
                                    new LatLng(10.702238, 122.568156),
                                    new LatLng(10.704119, 122.567963),
                                    new LatLng(10.704190, 122.568128)));
                    break;

                  case "Lapaz Public Market":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path75 = getShortestPathTo(A6);
                    distanceview.setText(  path75 +"\n " + A6.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A2_A6)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.691952, 122.569606))
                            .title("Start Point - University of Iloilo"));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692152, 122.569852))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708570, 122.567861))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Hall of Justice"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.696444, 122.568726))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Rose Pharmacy"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708451, 122.567992))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Petron"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.691952, 122.569606),
                                    new LatLng(10.692152, 122.569852),
                                    new LatLng(10.692160, 122.569117),//sm

                                    new LatLng(10.696270, 122.569107),
                                    new LatLng(10.696489, 122.569016),
                                    new LatLng(10.696444, 122.568726),
                                    new LatLng(10.696588, 122.568995),

                                    new LatLng(10.696765, 122.569148),
                                    new LatLng(10.700052, 122.569125),
                                    new LatLng(10.701429, 122.569109),
                                    new LatLng(10.701429, 122.569109),
                                    new LatLng(10.701558, 122.569229),
                                    new LatLng(10.701985, 122.568368),
                                    new LatLng(10.702238, 122.568156),
                                    new LatLng(10.704119, 122.567963),

                                    new LatLng(10.705073, 122.567924),
                                    new LatLng(10.706988, 122.567289),
                                    new LatLng(10.707776, 122.567131),
                                    new LatLng(10.708451, 122.567992),
                                    new LatLng(10.708570, 122.567861)));
                    break;
                  case "Gaisano City":

                    List<Vertex> path76 = getShortestPathTo(A7);
                    distanceview.setText(  path76 +"\n " + A7.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A2_A7)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691952, 122.569606))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - University of Iloilo"));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692152, 122.569852))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707540, 122.566972))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707862, 122.567282))
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .title("Get off - Andoks lapaz"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.691952, 122.569606),
                                    new LatLng(10.692152, 122.569852),
                                    new LatLng(10.692160, 122.569117),//sm

                                    new LatLng(10.696270, 122.569107),
                                    new LatLng(10.696489, 122.569016),
                                    new LatLng(10.696444, 122.568726),
                                    new LatLng(10.696588, 122.568995),

                                    new LatLng(10.696765, 122.569148),
                                    new LatLng(10.700052, 122.569125),
                                    new LatLng(10.701429, 122.569109),
                                    new LatLng(10.701429, 122.569109),
                                    new LatLng(10.701558, 122.569229),
                                    new LatLng(10.701985, 122.568368),
                                    new LatLng(10.702238, 122.568156),
                                    new LatLng(10.704119, 122.567963),
                                    new LatLng(10.705194, 122.567887),

                                    new LatLng(10.706979, 122.567289),
                                    new LatLng(10.707778, 122.567117),

                                    new LatLng(10.707862, 122.567282),
                                    new LatLng(10.707606, 122.567224),

                                    new LatLng(10.707540, 122.566972)));
                    break;
                  case "University of San Agustin":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path77 = getShortestPathTo(A8);
                    distanceview.setText(  path77 +"\n " + A8.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A2_A8)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.691952, 122.569606))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - University of Iloilo"));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692152, 122.569852))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.707540, 122.566972))
                            .title("End Point - Gaisano City"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.getoff))

                            .position(new LatLng(10.707862, 122.567282))
                            .title("Get off - Andoks lapaz"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.691952, 122.569606),
                                    new LatLng(10.692152, 122.569852),
                                    new LatLng(10.692165, 122.569063),//sm

                                    new LatLng(10.692037, 122.569047),
                                    new LatLng(10.691926, 122.567958),
                                    new LatLng(10.697857, 122.567812),
                                    new LatLng(10.697893, 122.566420),

                                    new LatLng(10.697693, 122.564806),
                                    new LatLng(10.701165, 122.564869),
                                    new LatLng(10.700911, 122.563054),
                                    new LatLng(10.700650, 122.563025)));
                    break;
                  case "Robinson City":

                    List<Vertex> path78 = getShortestPathTo(A1);
                    distanceview.setText(  path78 +"\n " + A1.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A2_A1)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.691922, 122.569899))
                            .title("Start Point - University of Iloilo"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692123, 122.569915))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM Mandurriao Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.700613, 122.563044))
                            .title("End Point - Robinson"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.691863, 122.569967),
                                    new LatLng(10.692123, 122.569915),
                                    new LatLng(10.692049, 122.568967),//sm

                                    new LatLng(10.691949, 122.567946),
                                    new LatLng(10.696173, 122.567867),
                                    new LatLng(10.697883, 122.567798),

                                    new LatLng(10.697899, 122.566314),
                                    new LatLng(10.697693, 122.564806),
                                    new LatLng(10.701164, 122.564875),
                                    new LatLng(10.700914, 122.563070),
                                    new LatLng(10.700613, 122.563044)));
                    break;
                  case "University of Iloilo":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    pathView2.setText(Html.fromHtml("You are currently in University of Iloilo"
                            , Html.FROM_HTML_MODE_COMPACT));
                    break;
                  case "SM City":

                    List<Vertex> path79 = getShortestPathTo(A3);
                    distanceview.setText(  path79 +"\n " + A3.minDistance + " kilometer" );
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    pathView2.setText(Html.fromHtml(getString(R.string.A2_A3)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.691922, 122.569899))
                            .title("Start Point - University of San Agustin"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.692124, 122.569895))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.713648, 122.551834))
                            .title("End Point - SM City"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.691922, 122.569899),
                                    new LatLng(10.692124, 122.569895),
                                    new LatLng(10.692157, 122.569121),//sm

                                    new LatLng(10.696409, 122.569078),
                                    new LatLng(10.696491, 122.568908),
                                    new LatLng(10.694290, 122.561749),

                                    new LatLng(10.697282, 122.561754),
                                    new LatLng(10.696581, 122.554863),
                                    new LatLng(10.698787, 122.554581),
                                    new LatLng(10.709368, 122.551760),

                                    new LatLng(10.711448, 122.551810),
                                    new LatLng(10.713489, 122.552357),
                                    new LatLng(10.713648, 122.551834)));
                    break;

                }
            }
            switch (start) {
              case "SM City":
                computePaths(A3);

                switch (destination) {
                  case "Plaza Libertad":
                    if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                    List<Vertex> path82 = getShortestPathTo(A9);
                    distanceview.setText(  path82 +"\n " + A9.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A3_A9)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.714123, 122.551454))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713715, 122.552212))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.692384, 122.573438))
                            .title("End Point - Plaza Libertad"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.713715, 122.552212),
                                    new LatLng(10.711310, 122.551673),
                                    new LatLng(10.709053, 122.551659),//sm

                                    new LatLng(10.699676, 122.554176),
                                    new LatLng(10.700111, 122.558009),
                                    new LatLng(10.700765, 122.563038),

                                    new LatLng(10.701690, 122.568406),
                                    new LatLng(10.701459, 122.569054),
                                    new LatLng(10.696629, 122.569019),
                                    new LatLng(10.694783, 122.570444),
                                    new LatLng(10.693940, 122.570991),

                                    new LatLng(10.692917, 122.572552),
                                    new LatLng(10.692863, 122.572728),
                                    new LatLng(10.692305, 122.573347),
                                    new LatLng(10.692384, 122.573438)));
                    break;
                  case "Grand Xing Imperial":

                    if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                    List<Vertex> path83 = getShortestPathTo(A4);
                    distanceview.setText(  path83 +"\n " + A4.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A3_A4)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.714123, 122.551454))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713715, 122.552212))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699622, 122.569358))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Grand Xing Imperial"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.713715, 122.552212),
                                    new LatLng(10.711310, 122.551673),
                                    new LatLng(10.709053, 122.551659),//sm

                                    new LatLng(10.699676, 122.554176),//general luna
                                    new LatLng(10.700111, 122.558009),
                                    new LatLng(10.700765, 122.563038),

                                    new LatLng(10.701690, 122.568406),
                                    new LatLng(10.701459, 122.569054),


                                    new LatLng(10.699639, 122.569075),
                                    new LatLng(10.699622, 122.569358)));


                    break;
                  case "Hall of Justice":

                    List<Vertex> path84 = getShortestPathTo(A5);
                    distanceview.setText(  path84 +"\n " + A5.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A3_A5)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.714123, 122.551454))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713715, 122.552212))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699868, 122.569097))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699932, 122.569136))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.704420, 122.568198))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Hall of Justice"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.713715, 122.552212),
                                    new LatLng(10.711310, 122.551673),
                                    new LatLng(10.709053, 122.551659),//sm

                                    new LatLng(10.699676, 122.554176),//general luna
                                    new LatLng(10.700111, 122.558009),
                                    new LatLng(10.700765, 122.563038),

                                    new LatLng(10.701690, 122.568406),
                                    new LatLng(10.701459, 122.569054),


                                    new LatLng(10.699639, 122.569075),
                                    new LatLng(10.699622, 122.569358)));
                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.699622, 122.569358),
                                    new LatLng(10.699932, 122.569136),
                                    new LatLng(10.701413, 122.569123),//sm

                                    new LatLng(10.701540, 122.569232),//general luna
                                    new LatLng(10.701883, 122.568524),

                                    new LatLng(10.702228, 122.568162),

                                    new LatLng(10.704388, 122.567954),
                                    new LatLng(10.704420, 122.568198)));

                    break;

                  case "Lapaz Public Market":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path85 = getShortestPathTo(A6);
                    distanceview.setText(  path85 +"\n " + A6.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A3_A6)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.714123, 122.551454))
                            .title("Start Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713715, 122.552212))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699868, 122.569097))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699932, 122.569136))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.708699, 122.567960))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Lapaz Public Market"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.713715, 122.552212),
                                    new LatLng(10.711310, 122.551673),
                                    new LatLng(10.709053, 122.551659),//sm

                                    new LatLng(10.699676, 122.554176),//general luna
                                    new LatLng(10.700111, 122.558009),
                                    new LatLng(10.700765, 122.563038),

                                    new LatLng(10.701690, 122.568406),
                                    new LatLng(10.701459, 122.569054),


                                    new LatLng(10.699639, 122.569075),
                                    new LatLng(10.699622, 122.569358)));
                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.699622, 122.569358),
                                    new LatLng(10.699932, 122.569136),
                                    new LatLng(10.701413, 122.569123),//sm
                                    new LatLng(10.701540, 122.569232),//general luna
                                    new LatLng(10.701883, 122.568524),
                                    new LatLng(10.702228, 122.568162),
                                    new LatLng(10.704388, 122.567954),

                                    new LatLng(10.705134, 122.567924),//general luna
                                    new LatLng(10.706975, 122.567308),

                                    new LatLng(10.707787, 122.567123),

                                    new LatLng(10.707787, 122.567123),
                                    new LatLng(10.708528, 122.568091),

                                    new LatLng(10.708699, 122.567960)));
                    break;
                  case "Gaisano City":

                    List<Vertex> path86 = getShortestPathTo(A7);
                    distanceview.setText(  path86 +"\n " + A7.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A3_A7)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.714123, 122.551454))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713715, 122.552212))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699868, 122.569097))
                            .icon(vectorToBitmap(R.drawable.cross))

                            .title("Cross Pedestrian - Grand Xing Imperial"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.699932, 122.569136))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - Lapaz Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.707528, 122.566955))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Lapaz Public Market"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.713715, 122.552212),
                                    new LatLng(10.711310, 122.551673),
                                    new LatLng(10.709053, 122.551659),//sm

                                    new LatLng(10.699676, 122.554176),//general luna
                                    new LatLng(10.700111, 122.558009),
                                    new LatLng(10.700765, 122.563038),

                                    new LatLng(10.701690, 122.568406),
                                    new LatLng(10.701459, 122.569054),


                                    new LatLng(10.699639, 122.569075),
                                    new LatLng(10.699622, 122.569358)));
                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.GRAY)
                            .add(
                                    new LatLng(10.699622, 122.569358),
                                    new LatLng(10.699932, 122.569136),
                                    new LatLng(10.701413, 122.569123),//sm
                                    new LatLng(10.701540, 122.569232),//general luna
                                    new LatLng(10.701883, 122.568524),
                                    new LatLng(10.702228, 122.568162),
                                    new LatLng(10.704388, 122.567954),

                                    new LatLng(10.705134, 122.567924),//general luna
                                    new LatLng(10.706975, 122.567308),

                                    new LatLng(10.707787, 122.567123),

                                    new LatLng(10.707787, 122.567123),
                                    new LatLng(10.707814, 122.567287),

                                    new LatLng(10.707599, 122.567245),
                                    new LatLng(10.707528, 122.566955)));
                    break;
                  case "University of San Agustin":

                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path87 = getShortestPathTo(A8);
                    distanceview.setText(  path87 +"\n " + A8.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A3_A8)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.714123, 122.551454))
                            .title("Start Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713715, 122.552212))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.700494, 122.563027))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - University of San Agustin"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.713715, 122.552212),
                                    new LatLng(10.711310, 122.551673),
                                    new LatLng(10.709053, 122.551659),//sm


                                    new LatLng(10.699676, 122.554176),
                                    new LatLng(10.700317, 122.560028),
                                    new LatLng(10.700736, 122.563059),
                                    new LatLng(10.700494, 122.563027)));
                    break;
                  case "Robinson City":

                    List<Vertex> path88 = getShortestPathTo(A1);
                    distanceview.setText(  path88 +"\n " + A1.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A3_A1)));

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.714123, 122.551454))
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .title("Start Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713715, 122.552212))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.695067, 122.565311))
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .title("End Point - Robinson City"));



                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.713715, 122.552212),
                                    new LatLng(10.711310, 122.551673),
                                    new LatLng(10.709053, 122.551659),//sm


                                    new LatLng(10.699676, 122.554176),
                                    new LatLng(10.700317, 122.560028),
                                    new LatLng(10.700587, 122.561746),

                                    new LatLng(10.698757, 122.561715),
                                    new LatLng(10.694191, 122.561744),
                                    new LatLng(10.695255, 122.565222),
                                    new LatLng(10.695067, 122.565311)));
                    break;
                  case "University of Iloilo":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;

                    List<Vertex> path89 = getShortestPathTo(A2);
                    distanceview.setText(  path89 +"\n " + A2.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml(getString(R.string.A3_A2)));

                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.brattt))

                            .position(new LatLng(10.714123, 122.551454))
                            .title("Start Point - SM City"));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.713715, 122.552212))
                            .icon(vectorToBitmap(R.drawable.tryridez))

                            .title("Ride - SM City Proper Jeep"));
                    mMap.addMarker(new MarkerOptions()
                            .icon(vectorToBitmap(R.drawable.amupagidni))

                            .position(new LatLng(10.691831, 122.569834))
                            .title("End Point - University of Iloilo"));


                    mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .width(15)
                            .color(Color.BLACK)
                            .add(
                                    new LatLng(10.713715, 122.552212),
                                    new LatLng(10.711310, 122.551673),
                                    new LatLng(10.709053, 122.551659),//sm


                                    new LatLng(10.699676, 122.554176),
                                    new LatLng(10.700602, 122.561759),
                                    new LatLng(10.694227, 122.561740),

                                    new LatLng(10.695276, 122.565269),
                                    new LatLng(10.696398, 122.568976),
                                    new LatLng(10.692026, 122.569047),
                                    new LatLng(10.692024, 122.569831),
                                    new LatLng(10.691831, 122.569834)));
                    break;

                  case "SM City":
                    if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                    List<Vertex> path90 = getShortestPathTo(A3);
                    distanceview.setText(  path90 +"\n " + A3.minDistance + " kilometer" );
                    pathView2.setText(Html.fromHtml("You are currently in SM City"
                            , Html.FROM_HTML_MODE_COMPACT));
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(10.714123, 122.551454))
                            .title("Start Point - SM City"));
                    break;

                }
            }

spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

         @RequiresApi(api = Build.VERSION_CODES.N)
         @Override
         public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
         String start = spinner.getText().toString();
         String destination = spinner2.getText().toString();


           Vertex A1 = new Vertex("Robinson City");
           Vertex A2 = new Vertex("University of Iloilo");
           Vertex A3 = new Vertex("SM City");
           Vertex A4 = new Vertex("Grand Xing Imperial");
           Vertex A5 = new Vertex("Hall of Justice");
           Vertex A6 = new Vertex("Lapaz Public Market");
           Vertex A7 = new Vertex("Gaisano City");
           Vertex A8 = new Vertex("University of San Agustin");
           Vertex A9 = new Vertex("Plaza Libertad");
           A1.adjacencies = new Edge[]{new Edge(A2, 1.4), new Edge(A3, 3.4), new Edge(A4, 1.0), new Edge(A5, 1.3), new Edge(A6, 1.9), new Edge(A7, 1.7), new Edge(A8, 1.3), new Edge(A9, 1.1)};
           A2.adjacencies = new Edge[]{new Edge(A1, 1.1), new Edge(A3, 4.5), new Edge(A4, 1.0), new Edge(A5, 1.4), new Edge(A6, 2.0), new Edge(A7, 1.8), new Edge(A8, 1.6), new Edge(A9, 0.5)};
           A3.adjacencies = new Edge[]{new Edge(A1, 3.6), new Edge(A2, 4.4), new Edge(A4, 5.5), new Edge(A5, 3.5), new Edge(A6, 4.0), new Edge(A7, 3.8), new Edge(A8, 2.2), new Edge(A9, 4.4)};
           A4.adjacencies = new Edge[]{new Edge(A1, 0.7), new Edge(A2, 0.9), new Edge(A3, 3.4), new Edge(A5, 0.3), new Edge(A6, 0.9), new Edge(A7, 0.7), new Edge(A8, 0.8), new Edge(A9, 1.2)};
           A5.adjacencies = new Edge[]{new Edge(A1, 1.3), new Edge(A2, 1.5), new Edge(A3, 3.4), new Edge(A4, 0.5), new Edge(A6, 0.5), new Edge(A7, 0.3), new Edge(A8, 0.8), new Edge(A9, 1.5)};
           A6.adjacencies = new Edge[]{new Edge(A1, 1.8), new Edge(A2, 1.9), new Edge(A3, 3.9), new Edge(A4, 1.1), new Edge(A5, 0.6), new Edge(A7, 0.4), new Edge(A8, 1.2), new Edge(A9, 2.0)};
           A7.adjacencies = new Edge[]{new Edge(A1, 1.6), new Edge(A2, 1.8), new Edge(A3, 3.6), new Edge(A4, 0.9), new Edge(A5, 0.4), new Edge(A6, 0.5), new Edge(A8, 1.1), new Edge(A9, 1.6)};
           A8.adjacencies = new Edge[]{new Edge(A1, 1.3), new Edge(A2, 1.6), new Edge(A3, 2.6), new Edge(A4, 0.7), new Edge(A5, 0.8), new Edge(A6, 0.8), new Edge(A7, 1.2), new Edge(A9, 1.8)};
           A9.adjacencies = new Edge[]{new Edge(A1, 1.0), new Edge(A2, 0.4), new Edge(A3, 4.5), new Edge(A4, 1.0), new Edge(A5, 1.6), new Edge(A6, 2.2), new Edge(A7, 2.0), new Edge(A8, 1.1)};

           switch (start) {
             case "Plaza Libertad":
               computePaths(A9);
               switch (destination) {

                 case "Plaza Libertad":
                   if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                   pathView2.setText("You are currently in Plaza Libertad");
                   break;
                 case "Grand Xing Imperial":
                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                   List<Vertex> path1 = getShortestPathTo(A4);
                   distanceview.setText(  path1 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A9_A4)));


                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))
                           //  .snippet("Population: 2,074,200")
                           .position(new LatLng(10.692438, 122.573488))
                           .title("Start Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692332, 122.573363))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700096, 122.569422))
                           .icon(vectorToBitmap(R.drawable.amupagidni ))
                           .title("End Point - Grand Xing Imperial"));




                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.692438, 122.573488),
                                   new LatLng(10.692332, 122.573363),

                                   new LatLng(10.693017, 122.572607),
                                   new LatLng(10.693973, 122.571060),
                                   new LatLng(10.694859, 122.570572),

                                   new LatLng(10.696567, 122.569140),
                                   new LatLng(10.700070, 122.569132),
                                   new LatLng(10.700096, 122.569422)));
                   break;
                 case "Hall of Justice":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path2 = getShortestPathTo(A5);
                   distanceview.setText(  path2 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A9_A5)));


                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692438, 122.573488))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .title("Start Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692332, 122.573363))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704231, 122.568115))
                           .icon(vectorToBitmap(R.drawable.amupagidni))
                           .title("End Point - Hall of Justice"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.692438, 122.573488),
                                   new LatLng(10.692332, 122.573363),

                                   new LatLng(10.693017, 122.572607),
                                   new LatLng(10.693973, 122.571060),
                                   new LatLng(10.694859, 122.570572),

                                   new LatLng(10.696567, 122.569140),
                                   new LatLng(10.700070, 122.569132),

                                   new LatLng(10.701639, 122.569121),
                                   new LatLng(10.702039, 122.568212),
                                   new LatLng(10.704205, 122.567938),
                                   new LatLng(10.704231, 122.568115)));
                   break;

                 case "Lapaz Public Market":
                   List<Vertex> path3 = getShortestPathTo(A6);
                   distanceview.setText(  path3 +"\n " + A6.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A9_A6)));



                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692438, 122.573488))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .title("Start Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692332, 122.573363))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708637, 122.567873))
                           .icon(vectorToBitmap(R.drawable.amupagidni))
                           .title("End Point - Lapaz Public Market"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.692438, 122.573488),
                                   new LatLng(10.692332, 122.573363),

                                   new LatLng(10.693017, 122.572607),
                                   new LatLng(10.693973, 122.571060),
                                   new LatLng(10.694859, 122.570572),

                                   new LatLng(10.696567, 122.569140),
                                   new LatLng(10.700070, 122.569132),

                                   new LatLng(10.701639, 122.569121),
                                   new LatLng(10.702039, 122.568212),
                                   new LatLng(10.704205, 122.567938),

                                   new LatLng(10.705106, 122.567911),
                                   new LatLng(10.706982, 122.567294),
                                   new LatLng(10.707799, 122.567133),
                                   new LatLng(10.708521, 122.568034),
                                   new LatLng(10.708637, 122.567873)));
                   break;
                 case "Gaisano City":
                   List<Vertex> path4 = getShortestPathTo(A7);
                   distanceview.setText(  path4 +"\n " + A7.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A9_A7)));




                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692438, 122.573488))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .title("Start Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692332, 122.573363))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707551, 122.567030))
                           .icon(vectorToBitmap(R.drawable.amupagidni))
                           .title("End Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707906, 122.567290))
                           .icon(vectorToBitmap(R.drawable.getoff))
                           .title("Get off - Andoks"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.692438, 122.573488),
                                   new LatLng(10.692332, 122.573363),

                                   new LatLng(10.693017, 122.572607),
                                   new LatLng(10.693973, 122.571060),
                                   new LatLng(10.694859, 122.570572),

                                   new LatLng(10.696567, 122.569140),
                                   new LatLng(10.700070, 122.569132),

                                   new LatLng(10.701639, 122.569121),
                                   new LatLng(10.702039, 122.568212),
                                   new LatLng(10.704205, 122.567938),


                                   new LatLng(10.704205, 122.567938),
                                   new LatLng(10.707798, 122.567121),
                                   new LatLng(10.707906, 122.567290),
                                   new LatLng(10.707654, 122.567301),
                                   new LatLng(10.707551, 122.567030)));
                   break;
                 case "University of San Agustin":
                   List<Vertex> path5 = getShortestPathTo(A8);
                   distanceview.setText(  path5 +"\n " + A8.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A9_A8)));



                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692438, 122.573488))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .title("Start Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692332, 122.573363))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704188, 122.568021))
                           .icon(vectorToBitmap(R.drawable.getoff))
                           .title("Get off - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700575, 122.563030))
                           .icon(vectorToBitmap(R.drawable.amupagidni))
                           .title("End Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704200, 122.567914))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - Lapaz Jeep"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.692438, 122.573488),
                                   new LatLng(10.692332, 122.573363),

                                   new LatLng(10.693017, 122.572607),
                                   new LatLng(10.693973, 122.571060),
                                   new LatLng(10.694859, 122.570572),

                                   new LatLng(10.696567, 122.569140),
                                   new LatLng(10.700070, 122.569132),

                                   new LatLng(10.701639, 122.569121),
                                   new LatLng(10.702039, 122.568212),
                                   new LatLng(10.704188, 122.568021),
                                   new LatLng(10.704200, 122.567914)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.704200, 122.567914),
                                   new LatLng(10.701853, 122.568221),
                                   new LatLng(10.701640, 122.567976),
                                   new LatLng(10.700965, 122.563073),
                                   new LatLng(10.700575, 122.563030)));
                   break;
                 case "Robinson City":
                   List<Vertex> path6 = getShortestPathTo(A1);
                   distanceview.setText(  path6 +"\n " + A1.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A9_A1)));




                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692438, 122.573488))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .title("Start Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692332, 122.573363))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695108, 122.565321))
                           .icon(vectorToBitmap(R.drawable.amupagidni))
                           .title("End Point - Robinson City"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.692394, 122.573460),
                                   new LatLng(10.692293, 122.573348),

                                   new LatLng(10.691797, 122.573874),
                                   new LatLng(10.691670, 122.573692),
                                   new LatLng(10.692007, 122.571566),

                                   new LatLng(10.692128, 122.569136),
                                   new LatLng(10.696505, 122.569086),

                                   new LatLng(10.695393, 122.565267),
                                   new LatLng(10.695108, 122.565321)));

                   break;
                 case "University of Iloilo":
                   List<Vertex> path7 = getShortestPathTo(A2);
                   distanceview.setText(  path7 +"\n " + A2.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A9_A2)));




                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692438, 122.573488))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .title("Start Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692332, 122.573363))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691826, 122.569780))
                           .icon(vectorToBitmap(R.drawable.amupagidni))
                           .title("End Point - University of Iloilo"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.692394, 122.573460),
                                   new LatLng(10.692293, 122.573348),

                                   new LatLng(10.691797, 122.573874),
                                   new LatLng(10.691670, 122.573692),
                                   new LatLng(10.692007, 122.571566),

                                   new LatLng(10.692117, 122.569872),
                                   new LatLng(10.691826, 122.569780)));
                   break;
                 case "SM City":
                   List<Vertex> path8 = getShortestPathTo(A3);
                   distanceview.setText(  path8 +"\n " + A3.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A9_A3)));



                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692394, 122.573460))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .title("Start Point - Plaza Libertad"));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692293, 122.573348))
                           .icon(vectorToBitmap(R.drawable.tryridez))
                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713669, 122.551816))
                           .icon(vectorToBitmap(R.drawable.amupagidni))
                           .title("End Point - University of Iloilo"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.692394, 122.573460),
                                   new LatLng(10.692293, 122.573348),

                                   new LatLng(10.691797, 122.573874),
                                   new LatLng(10.691670, 122.573692),
                                   new LatLng(10.692007, 122.571566),

                                   new LatLng(10.692117, 122.569872),


                                   new LatLng(10.692141, 122.569129),
                                   new LatLng(10.696487, 122.569072),
                                   new LatLng(10.694326, 122.561749),

                                   new LatLng(10.697264, 122.561747),
                                   new LatLng(10.696556, 122.554871),
                                   new LatLng(10.698800, 122.554581),
                                   new LatLng(10.700204, 122.554152),

                                   new LatLng(10.709033, 122.551824),
                                   new LatLng(10.710670, 122.551715),
                                   new LatLng(10.713495, 122.552369),
                                   new LatLng(10.713669, 122.551816)));



                   break;
               }
           }
           switch (start) {
             case "Grand Xing Imperial":
               computePaths(A4);
               switch (destination) {

                 case "Plaza Libertad":
                   List<Vertex> path12 = getShortestPathTo(A9);
                   distanceview.setText(  path12 +"\n " + A9.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A4_A9)));



                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700131, 122.569360))
                           .icon(vectorToBitmap(R.drawable.brattt))
                           .title("Start Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700079, 122.569069))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Balaurte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692439, 122.573500))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Plaza Libertad"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.700131, 122.569360),
                                   new LatLng(10.700079, 122.569069),


                                   new LatLng(10.696480, 122.569090),
                                   new LatLng(10.694746, 122.570504),
                                   new LatLng(10.693939, 122.571019),

                                   new LatLng(10.692879, 122.572703),
                                   new LatLng(10.692304, 122.573352),
                                   new LatLng(10.692431, 122.573470)));
                   break;
                 case "Grand Xing Imperial":
                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                   pathView2.setText(Html.fromHtml("You are currently in Grand Xing Imperial"
                           , Html.FROM_HTML_MODE_COMPACT));
                   break;

                 case "Hall of Justice":
                   List<Vertex> path13 = getShortestPathTo(A5);
                   distanceview.setText(  path13 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A4_A5)));




                   mMap.addMarker(new MarkerOptions()

                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700131, 122.569360))
                           .title("Start Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700174, 122.569134))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Balaurte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.704237, 122.568146))
                           .title("End Point - Hall of Justice"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700131, 122.569360),
                                   new LatLng(10.700174, 122.569134),


                                   new LatLng(10.701624, 122.569155),
                                   new LatLng(10.702040, 122.568205),
                                   new LatLng(10.704200, 122.567948),
                                   new LatLng(10.704237, 122.568146)));
                   break;




                 case "Lapaz Public Market":
                   List<Vertex> path15 = getShortestPathTo(A6);
                   distanceview.setText(  path15 +"\n " + A6.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A4_A6)));



                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700131, 122.569360))
                           .title("Start Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700174, 122.569134))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.708611, 122.567923))
                           .title("End Point - Lapaz Public Market"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.700131, 122.569360),
                                   new LatLng(10.700174, 122.569134),


                                   new LatLng(10.701624, 122.569155),
                                   new LatLng(10.702040, 122.568205),
                                   new LatLng(10.704200, 122.567948),

                                   new LatLng(10.705223, 122.567883),
                                   new LatLng(10.707014, 122.567280),
                                   new LatLng(10.707794, 122.567103),
                                   new LatLng(10.708474, 122.568020),
                                   new LatLng(10.708611, 122.567923)));
                   break;




                 case "Gaisano City":
                   List<Vertex> path16 = getShortestPathTo(A7);
                   distanceview.setText(  path16 +"\n " + A7.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A4_A7)));



                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700131, 122.569360))
                           .title("Start Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.700174, 122.569134))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.707543, 122.567042))
                           .title("End Point - Gaisano City"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700131, 122.569360),
                                   new LatLng(10.700174, 122.569134),


                                   new LatLng(10.701624, 122.569155),
                                   new LatLng(10.702040, 122.568205),
                                   new LatLng(10.704200, 122.567948),

                                   new LatLng(10.705223, 122.567883),
                                   new LatLng(10.707014, 122.567280),
                                   new LatLng(10.707794, 122.567103),
                                   new LatLng(10.707880, 122.567262),

                                   new LatLng(10.707801, 122.567313),
                                   new LatLng(10.707543, 122.567042)));
                   break;

                 case "University of San Agustin":
                   List<Vertex> path17 = getShortestPathTo(A8);
                   distanceview.setText(  path17 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A4_A8)));




                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700131, 122.569360))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700174, 122.569134))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707543, 122.567042))
                           .title("End Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704365, 122.567992))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700477, 122.562983))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Univeristy of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704374, 122.567883))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700131, 122.569360),
                                   new LatLng(10.700174, 122.569134),


                                   new LatLng(10.701624, 122.569155),
                                   new LatLng(10.702040, 122.568205),
                                   new LatLng(10.704365, 122.567992),
                                   new LatLng(10.704374, 122.567883)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.704374, 122.567883),
                                   new LatLng(10.702092, 122.568128),
                                   new LatLng(10.701753, 122.568294),

                                   new LatLng(10.701584, 122.567811),
                                   new LatLng(10.700888, 122.562919),
                                   new LatLng(10.700477, 122.562983)));
                   break;



                 case "Robinson City":
                   List<Vertex> path18 = getShortestPathTo(A1);
                   distanceview.setText(  path18 +"\n " + A1.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A4_A1)));



                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700124, 122.569259))
                           .title("Start Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.700160, 122.569068))
                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.695108, 122.565425))
                           .title("End Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696491, 122.568963))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.697052, 122.569012))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Hua Siong College of Iloilo"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700124, 122.569259),
                                   new LatLng(10.700160, 122.569068),


                                   new LatLng(10.697052, 122.569012),

                                   new LatLng(10.696509, 122.568948),
                                   new LatLng(10.695461, 122.565420),
                                   new LatLng(10.695108, 122.565425)));
                   break;


                 case "University of Iloilo":
                   List<Vertex> path19 = getShortestPathTo(A2);
                   distanceview.setText(  path19 +"\n " + A2.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A4_A2)));



                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700124, 122.569259))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700160, 122.569068))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691904, 122.569750))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of Iloilo"));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696362, 122.568942))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM Mandurriao Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696780, 122.569015))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Hua Siong College of Iloilo"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700124, 122.569259),
                                   new LatLng(10.700160, 122.569068),


                                   new LatLng(10.697052, 122.569012),

                                   new LatLng(10.696362, 122.568942),
                                   new LatLng(10.696375, 122.569065),
                                   new LatLng(10.692036, 122.569096),
                                   new LatLng(10.692015, 122.569769),
                                   new LatLng(10.691904, 122.569750)));
                   break;




                 case "SM City":
                   List<Vertex> path20 = getShortestPathTo(A3);
                   distanceview.setText(  path20 +"\n " + A3.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A4_A3)));



                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700124, 122.569259))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700174, 122.569134))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713624, 122.551890))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700874, 122.562875))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM Mandurriao Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704200, 122.567948))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700916, 122.562969))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - San Agustin University Front"));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.700131, 122.569360),
                                   new LatLng(10.700174, 122.569134),


                                   new LatLng(10.701624, 122.569155),
                                   new LatLng(10.702040, 122.568205),
                                   new LatLng(10.704200, 122.567948)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(


                                   new LatLng(10.704200, 122.567948),
                                   new LatLng(10.704210, 122.567914),
                                   new LatLng(10.703588, 122.567931),


                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701642, 122.568062),

                                   new LatLng(10.700916, 122.562969),
                                   new LatLng(10.700874, 122.562875),


                                   new LatLng(10.700897, 122.562851),


                                   new LatLng(10.699866, 122.554286),
                                   new LatLng(10.709320, 122.551749),
                                   new LatLng(10.711334, 122.551774),
                                   new LatLng(10.713492, 122.552384),
                                   new LatLng(10.713624, 122.551890)));
                   break;
               }
           }
           switch (start) {
             case "Hall of Justice":
               computePaths(A5);
               switch (destination) {

                 case "Plaza Libertad":
                   if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                   List<Vertex> path12 = getShortestPathTo(A9);
                   distanceview.setText(  path12 +"\n " + A9.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A5_A9)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704612, 122.568048))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704554, 122.567887))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692439, 122.573500))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701388, 122.567631))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));





                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(


                                   new LatLng(10.704612, 122.568048),
                                   new LatLng(10.704554, 122.567887),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),

                                   new LatLng(10.701667, 122.567593),
                                   new LatLng(10.701388, 122.567631)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701687, 122.568416),


                                   new LatLng(10.701497, 122.569060),
                                   new LatLng(10.696480, 122.569090),
                                   new LatLng(10.694746, 122.570504),
                                   new LatLng(10.693939, 122.571019),

                                   new LatLng(10.692879, 122.572703),
                                   new LatLng(10.692304, 122.573352),
                                   new LatLng(10.692431, 122.573470)));
                   break;

                 case "Grand Xing Imperial":
                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                   List<Vertex> path22 = getShortestPathTo(A4);
                   distanceview.setText(  path22 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A5_A4)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704612, 122.568048))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704554, 122.567887))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700065, 122.569364))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.cross))

                           .position(new LatLng(10.701667, 122.567593))
                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701388, 122.567631))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(


                                   new LatLng(10.704612, 122.568048),
                                   new LatLng(10.704554, 122.567887),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),

                                   new LatLng(10.701667, 122.567593),
                                   new LatLng(10.701388, 122.567631)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701687, 122.568416),


                                   new LatLng(10.701497, 122.569060),
                                   new LatLng(10.700075, 122.569047),
                                   new LatLng(10.700065, 122.569364)));

                   break;


                 case "Hall of Justice":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path23 = getShortestPathTo(A5);
                   distanceview.setText(  path23 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml("You are currently in Hall of Justice"
                           , Html.FROM_HTML_MODE_COMPACT));
                   break;

                 case "Lapaz Public Market":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path24 = getShortestPathTo(A6);
                   distanceview.setText(  path24 +"\n " + A6.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A5_A6)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.704192, 122.568105))
                           .title("Start Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704221, 122.567952))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.708597, 122.567865))
                           .title("End Point - Grand Xing Imperial"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(


                                   new LatLng(10.704192, 122.568105),
                                   new LatLng(10.704221, 122.567952),

                                   new LatLng(10.705145, 122.567915),
                                   new LatLng(10.706983, 122.567294),
                                   new LatLng(10.707774, 122.567120),

                                   new LatLng(10.708457, 122.567988),
                                   new LatLng(10.708597, 122.567865)));

                   break;

                 case "Gaisano City":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path25 = getShortestPathTo(A7);
                   distanceview.setText(  path25 +"\n " + A7.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A5_A7)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704192, 122.568105))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704221, 122.567952))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707540, 122.567042))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Gaisano City"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(


                                   new LatLng(10.704192, 122.568105),
                                   new LatLng(10.704221, 122.567952),

                                   new LatLng(10.705145, 122.567915),
                                   new LatLng(10.706983, 122.567294),

                                   new LatLng(10.707775, 122.567120),
                                   new LatLng(10.707875, 122.567257),
                                   new LatLng(10.707693, 122.567321),
                                   new LatLng(10.707540, 122.567042)));
                   break;

                 case "University of San Agustin":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path26 = getShortestPathTo(A8);
                   distanceview.setText(  path26 +"\n " + A8.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A5_A8)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.704612, 122.568048))
                           .title("Start Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704554, 122.567887))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700458, 122.562994))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Grand Xing Imperial"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(


                                   new LatLng(10.704612, 122.568048),
                                   new LatLng(10.704554, 122.567887),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),

                                   new LatLng(10.701433, 122.566820),
                                   new LatLng(10.700897, 122.562851),
                                   new LatLng(10.700458, 122.562994)));


                   break;

                 case "Robinson City":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path27 = getShortestPathTo(A1);
                   distanceview.setText(  path27 +"\n " + A1.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A5_A1)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704231, 122.568205))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704326, 122.567918))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695108, 122.565425))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701388, 122.567631))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.697052, 122.569012))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Hua Siong College of Iloilo"));




                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.704231, 122.568205),
                                   new LatLng(10.704326, 122.567918),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),
                                   new LatLng(10.701667, 122.567593),
                                   new LatLng(10.701388, 122.567631)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701717, 122.568413),
                                   new LatLng(10.701490, 122.569084),

                                   new LatLng(10.697052, 122.569012),

                                   new LatLng(10.696509, 122.568948),
                                   new LatLng(10.695461, 122.565420),
                                   new LatLng(10.695108, 122.565425)));
                   break;

                 case "University of Iloilo":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path28 = getShortestPathTo(A2);
                   distanceview.setText(  path28 +"\n " + A2.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A5_A2)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708923, 122.567588))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708822, 122.567388))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691888, 122.569454))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of Iloilo"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701388, 122.567631))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.697052, 122.569012))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Hua Siong College of Iloilo"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.696315, 122.568802))
                           .title("Ride - Lapaz Jeep"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708923, 122.567588),
                                   new LatLng(10.708822, 122.567388),
                                   new LatLng(10.709233, 122.566734),//sm

                                   new LatLng(10.708972, 122.566753),
                                   new LatLng(10.708223, 122.566972),
                                   new LatLng(10.705134, 122.567861),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),
                                   new LatLng(10.701667, 122.567593),
                                   new LatLng(10.701388, 122.567631)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701717, 122.568413),
                                   new LatLng(10.701490, 122.569084),

                                   new LatLng(10.697052, 122.569012),

                                   new LatLng(10.696449, 122.568754),
                                   new LatLng(10.696315, 122.568802),

                                   new LatLng(10.696386, 122.569070),
                                   new LatLng(10.692160, 122.569116),
                                   new LatLng(10.692036, 122.569054),
                                   new LatLng(10.692012, 122.569427),
                                   new LatLng(10.691888, 122.569454)));
                   break;

                 case "SM City":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path29 = getShortestPathTo(A3);
                   distanceview.setText(  path29 +"\n " + A3.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A5_A3)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.704612, 122.568048))
                           .title("Start Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704554, 122.567887))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700882, 122.562910))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Salt Gastro Lounge"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700849, 122.562834))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700849, 122.562834))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - SM City"));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(


                                   new LatLng(10.704612, 122.568048),
                                   new LatLng(10.704554, 122.567887),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),

                                   new LatLng(10.701433, 122.566820),
                                   new LatLng(10.700897, 122.562851),


                                   new LatLng(10.699866, 122.554286),
                                   new LatLng(10.709320, 122.551749),
                                   new LatLng(10.711334, 122.551774),
                                   new LatLng(10.713492, 122.552384),
                                   new LatLng(10.713624, 122.551890)));
                   break;
               }
           }

           switch (start) {
             case "Lapaz Public Market":
               computePaths(A5);

               switch (destination) {
                 case "Plaza Libertad":
                   List<Vertex> path32 = getShortestPathTo(A9);
                   distanceview.setText(  path32 +"\n " + A9.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A6_A9)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708923, 122.567588))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708822, 122.567388))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692439, 122.573500))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701388, 122.567631))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));




                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708923, 122.567588),
                                   new LatLng(10.708822, 122.567388),
                                   new LatLng(10.709233, 122.566734),//sm

                                   new LatLng(10.708972, 122.566753),
                                   new LatLng(10.708223, 122.566972),
                                   new LatLng(10.705134, 122.567861),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),

                                   new LatLng(10.701667, 122.567593),
                                   new LatLng(10.701388, 122.567631)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701687, 122.568416),


                                   new LatLng(10.701497, 122.569060),
                                   new LatLng(10.696480, 122.569090),
                                   new LatLng(10.694746, 122.570504),
                                   new LatLng(10.693939, 122.571019),

                                   new LatLng(10.692879, 122.572703),
                                   new LatLng(10.692304, 122.573352),
                                   new LatLng(10.692431, 122.573470)));
                   break;

                 case "Grand Xing Imperial":
                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                   List<Vertex> path33 = getShortestPathTo(A4);
                   distanceview.setText(  path33 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A6_A4)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708923, 122.567588))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.708822, 122.567388))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700069, 122.569369))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.701388, 122.567631))
                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708923, 122.567588),
                                   new LatLng(10.708822, 122.567388),
                                   new LatLng(10.709233, 122.566734),//sm

                                   new LatLng(10.708972, 122.566753),
                                   new LatLng(10.708223, 122.566972),
                                   new LatLng(10.705134, 122.567861),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),

                                   new LatLng(10.701667, 122.567593),
                                   new LatLng(10.701388, 122.567631)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701687, 122.568416),


                                   new LatLng(10.701497, 122.569060),
                                   new LatLng(10.700038, 122.569065),

                                   new LatLng(10.700069, 122.569369)));
                   break;

                 case "Hall of Justice":
                   List<Vertex> path34 = getShortestPathTo(A5);
                   distanceview.setText(  path34 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A6_A5)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.708923, 122.567588))
                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708822, 122.567388))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704236, 122.568203))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Hall of Justice"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708923, 122.567588),
                                   new LatLng(10.708822, 122.567388),
                                   new LatLng(10.709233, 122.566734),//sm

                                   new LatLng(10.708972, 122.566753),
                                   new LatLng(10.708223, 122.566972),
                                   new LatLng(10.705134, 122.567861),

                                   new LatLng(10.704222, 122.567909),
                                   new LatLng(10.704236, 122.568203)));


                   break;

                 case "Lapaz Public Market":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   pathView2.setText(Html.fromHtml("You are currently in Lapaz Public Market"
                           , Html.FROM_HTML_MODE_COMPACT));
                   break;
                 case "Gaisano City":
                   List<Vertex> path35 = getShortestPathTo(A7);
                   distanceview.setText(  path35 +"\n " + A7.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A6_A7)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708925, 122.567585))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708777, 122.567407))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.707360, 122.566889))
                           .title("End Point - Hall of Justice"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708925, 122.567585),
                                   new LatLng(10.708777, 122.567407),
                                   new LatLng(10.709204, 122.566715),//sm

                                   new LatLng(10.707380, 122.567155),
                                   new LatLng(10.707360, 122.566889)));
                   break;
                 case "University of San Agustin":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path36 = getShortestPathTo(A8);
                   distanceview.setText(  path36 +"\n " + A8.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A6_A8)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708925, 122.567585))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708777, 122.567407))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700556, 122.563010))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of San Agustin"));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708925, 122.567585),
                                   new LatLng(10.708777, 122.567407),
                                   new LatLng(10.709204, 122.566715),//sm


                                   new LatLng(10.706729, 122.567347),
                                   new LatLng(10.705084, 122.567878),
                                   new LatLng(10.702086, 122.568131),//sm
                                   new LatLng(10.701778, 122.568278),
                                   new LatLng(10.701578, 122.567817),
                                   new LatLng(10.700893, 122.562946),//sm
                                   new LatLng(10.700556, 122.563010)));
                   break;
                 case "Robinson City":

                   List<Vertex> path37 = getShortestPathTo(A1);
                   distanceview.setText(  path37 +"\n " + A1.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A6_A1)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708923, 122.567588))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708822, 122.567388))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695108, 122.565425))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701388, 122.567631))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .position(new LatLng(10.697052, 122.569012))
                           .title("Get off - Hua Siong College of Iloilo"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708923, 122.567588),
                                   new LatLng(10.708822, 122.567388),
                                   new LatLng(10.709233, 122.566734),//sm

                                   new LatLng(10.708972, 122.566753),
                                   new LatLng(10.708223, 122.566972),
                                   new LatLng(10.705134, 122.567861),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),
                                   new LatLng(10.701667, 122.567593),
                                   new LatLng(10.701388, 122.567631)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701717, 122.568413),
                                   new LatLng(10.701490, 122.569084),

                                   new LatLng(10.697052, 122.569012),

                                   new LatLng(10.696509, 122.568948),
                                   new LatLng(10.695461, 122.565420),
                                   new LatLng(10.695108, 122.565425)));
                   break;
                 case "University of Iloilo":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path38 = getShortestPathTo(A2);
                   distanceview.setText(  path38 +"\n " + A2.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A6_A2)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.708923, 122.567588))
                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.708822, 122.567388))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691888, 122.569454))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of Iloilo"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.701388, 122.567631))
                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .position(new LatLng(10.697052, 122.569012))
                           .title("Get off - Hua Siong College of Iloilo"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.696315, 122.568802))
                           .title("Ride - Lapaz Jeep"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708923, 122.567588),
                                   new LatLng(10.708822, 122.567388),
                                   new LatLng(10.709233, 122.566734),//sm

                                   new LatLng(10.708972, 122.566753),
                                   new LatLng(10.708223, 122.566972),
                                   new LatLng(10.705134, 122.567861),

                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),
                                   new LatLng(10.701667, 122.567593),
                                   new LatLng(10.701388, 122.567631)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701717, 122.568413),
                                   new LatLng(10.701490, 122.569084),

                                   new LatLng(10.697052, 122.569012),

                                   new LatLng(10.696449, 122.568754),
                                   new LatLng(10.696315, 122.568802),

                                   new LatLng(10.696386, 122.569070),
                                   new LatLng(10.692160, 122.569116),
                                   new LatLng(10.692036, 122.569054),
                                   new LatLng(10.692012, 122.569427),
                                   new LatLng(10.691888, 122.569454)));
                   break;
                 case "SM City":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path39 = getShortestPathTo(A3);
                   distanceview.setText(  path39 +"\n " + A3.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A6_A3)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.708925, 122.567585))
                           .title("Start Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708777, 122.567407))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.700852, 122.562656))
                           .title("Ride - SM Mandurriao Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713630, 122.551940))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700905, 122.562797))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Salt Gastro Lounge"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.708925, 122.567585),
                                   new LatLng(10.708777, 122.567407),
                                   new LatLng(10.709204, 122.566715),//sm


                                   new LatLng(10.706729, 122.567347),
                                   new LatLng(10.705084, 122.567878),
                                   new LatLng(10.702086, 122.568131),//sm
                                   new LatLng(10.701778, 122.568278),
                                   new LatLng(10.701578, 122.567817),
                                   new LatLng(10.700893, 122.562946),//sm
                                   new LatLng(10.700905, 122.562797),//sm
                                   new LatLng(10.700852, 122.562656),//sm


                                   new LatLng(10.699860, 122.554278),
                                   new LatLng(10.709148, 122.551794),
                                   new LatLng(10.711046, 122.551756),//sm
                                   new LatLng(10.713509, 122.552385),
                                   new LatLng(10.713630, 122.551940)));
                   break;
               }
           }

           switch (start) {
             case "Gaisano City":
               computePaths(A7);

               switch (destination) {
                 case "Plaza Libertad":
                   if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                   List<Vertex> path42 = getShortestPathTo(A9);
                   distanceview.setText(  path42 +"\n " + A9.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A7_A9)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707053, 122.567042))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707142, 122.567207))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692439, 122.573500))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("End Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701388, 122.567631))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.707053, 122.567042),
                                   new LatLng(10.707142, 122.567207),
                                   new LatLng(10.705148, 122.567840),//sm
                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),

                                   new LatLng(10.701667, 122.567593)));

                                   mMap.addPolyline(new PolylineOptions()
                                           .clickable(true)
                                           .width(15)
                                           .color(Color.GRAY)
                                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701687, 122.568416),


                                   new LatLng(10.701497, 122.569060),
                                   new LatLng(10.696480, 122.569090),
                                   new LatLng(10.694746, 122.570504),
                                   new LatLng(10.693939, 122.571019),

                                   new LatLng(10.692879, 122.572703),
                                   new LatLng(10.692304, 122.573352),
                                   new LatLng(10.692431, 122.573470)));
                   break;

                 case "Grand Xing Imperial":
                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                   List<Vertex> path43 = getShortestPathTo(A4);
                   distanceview.setText(  path43 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A7_A4)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.707053, 122.567042))
                           .title("Start Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707142, 122.567207))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699679, 122.569241))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701667, 122.567593))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Tibiao bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701388, 122.567631))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.707053, 122.567042),
                                   new LatLng(10.707142, 122.567207),
                                   new LatLng(10.705148, 122.567840),//sm
                                   new LatLng(10.703588, 122.567931),
                                   new LatLng(10.701883, 122.568221),
                                   new LatLng(10.701625, 122.568017),

                                   new LatLng(10.701667, 122.567593)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.701388, 122.567631),
                                   new LatLng(10.701687, 122.568416),
                                   new LatLng(10.701497, 122.569060),

                                   new LatLng(10.699643, 122.569077),
                                   new LatLng(10.699679, 122.569241)));
                   break;
                 case "Hall of Justice":

                   List<Vertex> path44 = getShortestPathTo(A5);
                   distanceview.setText(  path44 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A7_A5)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.707053, 122.567042))
                           .title("Start Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707142, 122.567207))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.704197, 122.568175))
                           .title("End Point - Hall of Justice"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.707053, 122.567042),
                                   new LatLng(10.707142, 122.567207),
                                   new LatLng(10.705148, 122.567840),//sm


                                   new LatLng(10.704136, 122.567878),
                                   new LatLng(10.704197, 122.568175)));


                   break;

                 case "Lapaz Public Market":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;

                   List<Vertex> path45 = getShortestPathTo(A6);
                   distanceview.setText(  path45 +"\n " + A6.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A7_A6)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.707552, 122.567017))
                           .title("Start Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707602, 122.567234))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Gaisano"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.708598, 122.567953))
                           .title("End Point - Lapaz Public Market"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.707552, 122.567017),
                                   new LatLng(10.707602, 122.567234),
                                   new LatLng(10.708474, 122.568071),//sm


                                   new LatLng(10.708598, 122.567953)));
                   break;
                 case "Gaisano City":
                   pathView2.setText(Html.fromHtml("You are currently in Gaisano City"
                           , Html.FROM_HTML_MODE_COMPACT));
                   break;
                 case "University of San Agustin":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;

                   List<Vertex> path46 = getShortestPathTo(A8);
                   distanceview.setText(  path46 +"\n " + A8.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A7_A8)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707053, 122.567042))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707142, 122.567207))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700658, 122.563011))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of San Agustin"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.707053, 122.567042),
                                   new LatLng(10.707142, 122.567207),
                                   new LatLng(10.705148, 122.567840),//sm


                                   new LatLng(10.704136, 122.567878),
                                   new LatLng(10.701828, 122.568262),
                                   new LatLng(10.701659, 122.568021),
                                   new LatLng(10.700884, 122.562925),
                                   new LatLng(10.700658, 122.563011)));
                   break;
                 case "Robinson City":

                   List<Vertex> path47 = getShortestPathTo(A1);
                   distanceview.setText(  path47 +"\n " + A1.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A7_A1)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707053, 122.567042))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707142, 122.567207))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695055, 122.565363))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696970, 122.569002))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Hua Siong College of Iloilo"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696500, 122.568923))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701659, 122.568021))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Tibiao Bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701330, 122.567638))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.707101, 122.567121),
                                   new LatLng(10.707104, 122.567207),
                                   new LatLng(10.705073, 122.567864),


                                   new LatLng(10.701828, 122.568262),
                                   new LatLng(10.701659, 122.568021),
                                   new LatLng(10.701625, 122.567590),
                                   new LatLng(10.701330, 122.567638),

                                   new LatLng(10.701704, 122.568416),
                                   new LatLng(10.701483, 122.569070),

                                   new LatLng(10.696970, 122.569002),

                                   new LatLng(10.696500, 122.568923),
                                   new LatLng(10.695403, 122.565315),
                                   new LatLng(10.695055, 122.565363)));
                   break;
                 case "University of Iloilo":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path48 = getShortestPathTo(A2);
                   distanceview.setText(  path48 +"\n " + A2.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A7_A2)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.707053, 122.567042))
                           .title("Start Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707142, 122.567207))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.691805, 122.569797))
                           .title("End Point - University of Iloilo"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696970, 122.569002))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Hua Siong College of Iloilo"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701659, 122.568021))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Tibiao Bakery"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696326, 122.568868))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701330, 122.567638))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696488, 122.568844))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Rose Pharmacy"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.707101, 122.567121),
                                   new LatLng(10.707104, 122.567207),
                                   new LatLng(10.705073, 122.567864),


                                   new LatLng(10.701828, 122.568262),
                                   new LatLng(10.701659, 122.568021),
                                   new LatLng(10.701625, 122.567590),
                                   new LatLng(10.701330, 122.567638),

                                   new LatLng(10.701704, 122.568416),
                                   new LatLng(10.701483, 122.569070),

                                   new LatLng(10.696970, 122.569002),

                                   new LatLng(10.696488, 122.568844),
                                   new LatLng(10.696326, 122.568868),
                                   new LatLng(10.696384, 122.569074),

                                   new LatLng(10.692021, 122.569084),
                                   new LatLng(10.692022, 122.569809),
                                   new LatLng(10.691805, 122.569797)));
                   break;
                 case "SM City":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path49 = getShortestPathTo(A3);
                   distanceview.setText(  path49 +"\n " + A3.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A7_A3)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707053, 122.567042))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707142, 122.567207))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700959, 122.563336))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM Mandurriao"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713647, 122.551893))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.701002, 122.563041))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Salt Gastro Lounge"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.707053, 122.567042),
                                   new LatLng(10.707142, 122.567207),
                                   new LatLng(10.705148, 122.567840),//sm


                                   new LatLng(10.704136, 122.567878),
                                   new LatLng(10.701828, 122.568262),
                                   new LatLng(10.701659, 122.568021),
                                   new LatLng(10.700884, 122.562925)));

                                   mMap.addPolyline(new PolylineOptions()
                                           .clickable(true)
                                           .width(15)
                                           .color(Color.GRAY)
                                           .add(
                                   new LatLng(10.700959, 122.563336),


                                   new LatLng(10.699877, 122.554289),
                                   new LatLng(10.709341, 122.551754),
                                   new LatLng(10.711125, 122.551758),
                                   new LatLng(10.713510, 122.552376),
                                   new LatLng(10.713647, 122.551893)));
                   break;

               }
           }

           switch (start) {
             case "University of San Agustin":
               computePaths(A8);

               switch (destination) {
                 case "Plaza Libertad":
                   if (R.id.spinner == 1 && R.id.spinner2 == 2) ;

                   List<Vertex> path52 = getShortestPathTo(A9);
                   distanceview.setText(  path52 +"\n " + A9.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A8_A9)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700633, 122.563004))
                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.700805, 122.562991))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.692439, 122.573500))
                           .title("End Point - Plaza Libertad"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700633, 122.563004),
                                   new LatLng(10.700805, 122.562991),
                                   new LatLng(10.701539, 122.568179),//sm

                                   new LatLng(10.701510, 122.569008),
                                   new LatLng(10.696680, 122.569024),
                                   new LatLng(10.693914, 122.571018),

                                   new LatLng(10.692886, 122.572603),
                                   new LatLng(10.692307, 122.573306),
                                   new LatLng(10.692439, 122.573500)));
                   break;
                 case "Grand Xing Imperial":
                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;

                   List<Vertex> path53 = getShortestPathTo(A4);
                   distanceview.setText(  path53 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A8_A4)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700633, 122.563004))
                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700805, 122.562991))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.700086, 122.569397))
                           .title("End Point - Grand Xing Imperial"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700633, 122.563004),
                                   new LatLng(10.700805, 122.562991),
                                   new LatLng(10.701539, 122.568179),//sm

                                   new LatLng(10.701499, 122.568980),
                                   new LatLng(10.700113, 122.569017),
                                   new LatLng(10.700086, 122.569397)));
                   break;
                 case "Hall of Justice":

                   List<Vertex> path54 = getShortestPathTo(A5);
                   distanceview.setText(  path54 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A8_A5)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700633, 122.563004))
                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700805, 122.562991))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.699769, 122.569183))
                           .title("Ride - Lapaz Jeep "));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.704385, 122.568165))
                           .title("End Point - Hall of Justice "));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700633, 122.563004),
                                   new LatLng(10.700805, 122.562991),
                                   new LatLng(10.701539, 122.568179),//sm
                                   new LatLng(10.701682, 122.568380),

                                   new LatLng(10.701499, 122.568980),

                                   new LatLng(10.700113, 122.569017),

                                   new LatLng(10.699767, 122.569034),
                                   new LatLng(10.699769, 122.569183
                                   ),

                                   new LatLng(10.701610, 122.569162),
                                   new LatLng(10.702151, 122.568185),
                                   new LatLng(10.704358, 122.567966),

                                   new LatLng(10.704385, 122.568165)));
                   break;

                 case "Lapaz Public Market":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;

                   List<Vertex> path55 = getShortestPathTo(A6);
                   distanceview.setText(  path55 +"\n " + A6.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A8_A6)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700633, 122.563004))
                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700805, 122.562991))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699769, 122.569183))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep "));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708655, 122.567937))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Lapaz Public Market "));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700633, 122.563004),
                                   new LatLng(10.700805, 122.562991),
                                   new LatLng(10.701539, 122.568179),//sm
                                   new LatLng(10.701682, 122.568380),
                                   new LatLng(10.701499, 122.568980),
                                   new LatLng(10.700113, 122.569017),
                                   new LatLng(10.699767, 122.569034),
                                   new LatLng(10.699769, 122.569183)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.699769, 122.569183),
                                   new LatLng(10.701610, 122.569162),
                                   new LatLng(10.702151, 122.568185),
                                   new LatLng(10.704358, 122.567966),
                                   new LatLng(10.704358, 122.567966),
                                   new LatLng(10.705179, 122.567891),
                                   new LatLng(10.707773, 122.567082),

                                   new LatLng(10.708450, 122.567971),
                                   new LatLng(10.708449, 122.568056),
                                   new LatLng(10.708655, 122.567937)));
                   break;
                 case "Gaisano City":

                   List<Vertex> path56 = getShortestPathTo(A7);
                   distanceview.setText(  path56 +"\n " + A7.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A8_A7)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700633, 122.563004))
                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700805, 122.562991))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep / Baluarte Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699769, 122.569183))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep "));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.707548, 122.566971))
                           .title("End Point - Gaisano City "));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707899, 122.567274))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Andoks "));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700633, 122.563004),
                                   new LatLng(10.700805, 122.562991),
                                   new LatLng(10.701539, 122.568179),//sm
                                   new LatLng(10.701682, 122.568380),
                                   new LatLng(10.701499, 122.568980),
                                   new LatLng(10.700113, 122.569017),
                                   new LatLng(10.699767, 122.569034)));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.699769, 122.569183),
                                   new LatLng(10.701610, 122.569162),
                                   new LatLng(10.702151, 122.568185),
                                   new LatLng(10.704358, 122.567966),
                                   new LatLng(10.704358, 122.567966),
                                   new LatLng(10.705179, 122.567891),


                                   new LatLng(10.707773, 122.567082),
                                   new LatLng(10.707899, 122.567274),
                                   new LatLng(10.707694, 122.567273),
                                   new LatLng(10.707548, 122.566971)));
                   break;
                 case "University of San Agustin":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   pathView2.setText(Html.fromHtml("You are currently in University of San Agustin"
                           , Html.FROM_HTML_MODE_COMPACT));
                   break;
                 case "Robinson City":

                   List<Vertex> path57 = getShortestPathTo(A1);
                   distanceview.setText(  path57 +"\n " + A1.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A8_A1)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700633, 122.563004))
                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.700888, 122.562977))
                           .title("Ride - Lapaz Jeep "));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.695095, 122.565328))
                           .title("End Point - Robinson City "));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700570, 122.563019),
                                   new LatLng(10.700888, 122.562977),
                                   new LatLng(10.700680, 122.561765),//sm
                                   new LatLng(10.697337, 122.561717),
                                   new LatLng(10.694162, 122.561728),
                                   new LatLng(10.695248, 122.565269),
                                   new LatLng(10.695095, 122.565328)));
                   break;
                 case "University of Iloilo":

                   List<Vertex> path58 = getShortestPathTo(A2);
                   distanceview.setText(  path58 +"\n " + A2.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A8_A2)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.700633, 122.563004))
                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700925, 122.563006))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep "));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.691868, 122.569480))
                           .title("End Point - University of Iloilo "));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700588, 122.563049),
                                   new LatLng(10.700925, 122.563006),
                                   new LatLng(10.700709, 122.561735),//sm
                                   new LatLng(10.694200, 122.561744),
                                   new LatLng(10.696386, 122.569020),
                                   new LatLng(10.692015, 122.569077),
                                   new LatLng(10.692010, 122.569469),
                                   new LatLng(10.691868, 122.569480)));
                   break;
                 case "SM City":

                   List<Vertex> path59 = getShortestPathTo(A3);
                   distanceview.setText(  path59 +"\n " + A3.minDistance + " kilometer" );
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   pathView2.setText(Html.fromHtml(getString(R.string.A8_A3)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700633, 122.563004))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700925, 122.563006))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep "));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691868, 122.569480))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of Iloilo "));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.700551, 122.563002),
                                   new LatLng(10.700904, 122.562951),
                                   new LatLng(10.699879, 122.554276),//sm
                                   new LatLng(10.708929, 122.551836),
                                   new LatLng(10.711042, 122.551731),
                                   new LatLng(10.713507, 122.552382),
                                   new LatLng(10.713665, 122.551835)));
                   break;

               }
           }
           switch (start) {
             case "Robinson City":
               computePaths(A1);

               switch (destination) {
                 case "Plaza Libertad":
                   if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                   List<Vertex> path62 = getShortestPathTo(A9);
                   distanceview.setText(  path62 +"\n " + A9.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A1_A9)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695097, 122.565337))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695327, 122.565401))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692418, 122.573484))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696725, 122.569028))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Iznart Street"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696214, 122.568401))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Goodwill Marketing"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.695097, 122.565337),
                                   new LatLng(10.695327, 122.565401),
                                   new LatLng(10.696214, 122.568401),//sm

                                   new LatLng(10.696351, 122.568406),
                                   new LatLng(10.696554, 122.568948),
                                   new LatLng(10.696725, 122.569028),

                                   new LatLng(10.696446, 122.569130),
                                   new LatLng(10.694750, 122.570495),
                                   new LatLng(10.693916, 122.571000),
                                   new LatLng(10.692917, 122.572577),
                                   new LatLng(10.692313, 122.573363),
                                   new LatLng(10.692455, 122.573521)));
                   break;
                 case "Grand Xing Imperial":

                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                   List<Vertex> path63 = getShortestPathTo(A4);
                   distanceview.setText(  path63 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A1_A4)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.695097, 122.565337))
                           .title("Start Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .position(new LatLng(10.695327, 122.565401))
                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.700077, 122.569472))
                           .title("End Point - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696725, 122.569028))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Iznart Street"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696214, 122.568401))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Goodwill Marketing"));

                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.695097, 122.565337),
                                   new LatLng(10.695327, 122.565401),
                                   new LatLng(10.696214, 122.568401),//sm

                                   new LatLng(10.696351, 122.568406),
                                   new LatLng(10.696554, 122.568948),
                                   new LatLng(10.696725, 122.569028),
                                   new LatLng(10.696739, 122.569169),
                                   new LatLng(10.700046, 122.569141),
                                   new LatLng(10.700077, 122.569472)));
                   break;
                 case "Hall of Justice":

                   List<Vertex> path64 = getShortestPathTo(A5);
                   distanceview.setText(  path64 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A1_A5)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.695097, 122.565337))
                           .title("Start Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695327, 122.565401))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704419, 122.568137))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696725, 122.569028))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Iznart Street"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696214, 122.568401))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Goodwill Marketing"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.695097, 122.565337),
                                   new LatLng(10.695327, 122.565401),
                                   new LatLng(10.696214, 122.568401),//sm

                                   new LatLng(10.696351, 122.568406),
                                   new LatLng(10.696554, 122.568948),
                                   new LatLng(10.696725, 122.569028),
                                   new LatLng(10.696739, 122.569169),
                                   new LatLng(10.700046, 122.569141),

                                   new LatLng(10.701407, 122.569097),
                                   new LatLng(10.701571, 122.569219),
                                   new LatLng(10.701924, 122.568430),
                                   new LatLng(10.702256, 122.568146),
                                   new LatLng(10.704377, 122.567947),
                                   new LatLng(10.704419, 122.568137)));
                   break;

                 case "Lapaz Public Market":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path65 = getShortestPathTo(A6);
                   distanceview.setText(  path65 +"\n " + A6.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A1_A6)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695097, 122.565337))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695327, 122.565401))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708570, 122.567861))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696725, 122.569028))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Iznart Street"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696214, 122.568401))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Goodwill Marketing"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708451, 122.567992))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Petron"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.695097, 122.565337),
                                   new LatLng(10.695327, 122.565401),
                                   new LatLng(10.696214, 122.568401),//sm

                                   new LatLng(10.696351, 122.568406),
                                   new LatLng(10.696554, 122.568948),
                                   new LatLng(10.696725, 122.569028),
                                   new LatLng(10.696739, 122.569169),
                                   new LatLng(10.700046, 122.569141),

                                   new LatLng(10.701407, 122.569097),
                                   new LatLng(10.701571, 122.569219),
                                   new LatLng(10.701924, 122.568430),
                                   new LatLng(10.702256, 122.568146),
                                   new LatLng(10.704377, 122.567947),
                                   new LatLng(10.705073, 122.567924),
                                   new LatLng(10.706988, 122.567289),
                                   new LatLng(10.707776, 122.567131),
                                   new LatLng(10.708451, 122.567992),
                                   new LatLng(10.708570, 122.567861)));
                   break;
                 case "Gaisano City":

                   List<Vertex> path66 = getShortestPathTo(A7);
                   distanceview.setText(  path66 +"\n " + A7.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A1_A7)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695097, 122.565337))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695327, 122.565401))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708570, 122.567861))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Lapaz Public Market"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696725, 122.569028))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Iznart Street"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696214, 122.568401))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Goodwill Marketing"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .position(new LatLng(10.708451, 122.567992))
                           .title("Get off - Petron"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.695097, 122.565337),
                                   new LatLng(10.695327, 122.565401),
                                   new LatLng(10.696214, 122.568401),//sm

                                   new LatLng(10.696351, 122.568406),
                                   new LatLng(10.696554, 122.568948),
                                   new LatLng(10.696725, 122.569028),
                                   new LatLng(10.696739, 122.569169),
                                   new LatLng(10.700046, 122.569141),

                                   new LatLng(10.701407, 122.569097),
                                   new LatLng(10.701571, 122.569219),
                                   new LatLng(10.701924, 122.568430),
                                   new LatLng(10.702256, 122.568146),
                                   new LatLng(10.704377, 122.567947),
                                   new LatLng(10.705073, 122.567924),
                                   new LatLng(10.706988, 122.567289),
                                   new LatLng(10.707776, 122.567131),
                                   new LatLng(10.707806, 122.567302),
                                   new LatLng(10.707606, 122.567226),
                                   new LatLng(10.707522, 122.566979)));
                   break;
                 case "University of San Agustin":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path67 = getShortestPathTo(A8);
                   distanceview.setText(  path67 +"\n " + A8.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A1_A8)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.695097, 122.565337))
                           .title("Start Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695327, 122.565401))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700624, 122.563012))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696725, 122.569028))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Iznart Street"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696214, 122.568401))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Goodwill Marketing"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .position(new LatLng(10.704370, 122.568063))
                           .title("Get off - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704346, 122.567868))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.695097, 122.565337),
                                   new LatLng(10.695327, 122.565401),
                                   new LatLng(10.696214, 122.568401),//sm

                                   new LatLng(10.696351, 122.568406),
                                   new LatLng(10.696554, 122.568948),
                                   new LatLng(10.696725, 122.569028),
                                   new LatLng(10.696739, 122.569169),
                                   new LatLng(10.700046, 122.569141),

                                   new LatLng(10.701407, 122.569097),
                                   new LatLng(10.701571, 122.569219),
                                   new LatLng(10.701924, 122.568430),
                                   new LatLng(10.702256, 122.568146),
                                   new LatLng(10.704377, 122.567947)));


                                   mMap.addPolyline(new PolylineOptions()
                                           .clickable(true)
                                           .width(15)
                                           .color(Color.GRAY)
                                           .add(
                                   new LatLng(10.704370, 122.568063),
                                   new LatLng(10.704346, 122.567868),
                                   new LatLng(10.701849, 122.568197),
                                   new LatLng(10.701611, 122.568002),
                                   new LatLng(10.700882, 122.562952),
                                   new LatLng(10.700624, 122.563012)));
                   break;
                 case "Robinson City":
                   pathView2.setText(Html.fromHtml("You are currently in Robinson City"
                           , Html.FROM_HTML_MODE_COMPACT));

                   break;
                 case "University of Iloilo":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path68 = getShortestPathTo(A2);
                   distanceview.setText(  path68 +"\n " + A2.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A1_A2)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.695097, 122.565337))
                           .title("Start Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695327, 122.565401))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692418, 122.573484))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.cross))

                           .position(new LatLng(10.696725, 122.569028))
                           .title("Cross Pedestrian - Iznart Street"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696214, 122.568401))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Goodwill Marketing"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.695097, 122.565337),
                                   new LatLng(10.695327, 122.565401),
                                   new LatLng(10.696214, 122.568401),//sm

                                   new LatLng(10.696351, 122.568406),
                                   new LatLng(10.696554, 122.568948),
                                   new LatLng(10.696725, 122.569028),

                                   new LatLng(10.696446, 122.569130),
                                   new LatLng(10.694750, 122.570495),
                                   new LatLng(10.693916, 122.571000),
                                   new LatLng(10.692917, 122.572577),
                                   new LatLng(10.692313, 122.573363),
                                   new LatLng(10.692455, 122.573521)));
                   break;
                 case "SM City":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path69 = getShortestPathTo(A3);
                   distanceview.setText(  path69 +"\n " + A3.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A1_A3)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.695167, 122.565332))
                           .title("Start Point - Robinson City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695415, 122.565376))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713638, 122.551800))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - SM City"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.695167, 122.565332),
                                   new LatLng(10.695415, 122.565376),
                                   new LatLng(10.694278, 122.561719),//sm

                                   new LatLng(10.697300, 122.561757),
                                   new LatLng(10.696567, 122.554863),
                                   new LatLng(10.698744, 122.554598),

                                   new LatLng(10.708790, 122.551859),
                                   new LatLng(10.711310, 122.551750),
                                   new LatLng(10.713487, 122.552427),
                                   new LatLng(10.713638, 122.551800)));
                   break;

               }
           }

           switch (start) {
             case "University of Iloilo":
               computePaths(A2);

               switch (destination) {
                 case "Plaza Libertad":
                   if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                   List<Vertex> path72 = getShortestPathTo(A9);
                   distanceview.setText(  path72 +"\n " + A9.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A2_A9)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.691929, 122.569312))
                           .title("Start Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692037, 122.569323))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.692418, 122.573484))
                           .title("End Point - Plaza Libertad"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.691929, 122.569312),
                                   new LatLng(10.692037, 122.569323),
                                   new LatLng(10.692034, 122.570672),//sm

                                   new LatLng(10.691955, 122.571667),
                                   new LatLng(10.691784, 122.572525),
                                   new LatLng(10.691632, 122.573687),

                                   new LatLng(10.691821, 122.573893),
                                   new LatLng(10.692323, 122.573377),
                                   new LatLng(10.692418, 122.573484)));
                   break;
                 case "Grand Xing Imperial":

                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                   List<Vertex> path73 = getShortestPathTo(A4);
                   distanceview.setText(  path73 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A2_A4)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691952, 122.569606))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - University of Iloilo"));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692152, 122.569852))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700036, 122.569488))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Plaza Libertad"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .position(new LatLng(10.696444, 122.568726))
                           .title("Get off - Rose Pharmacy"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.691952, 122.569606),
                                   new LatLng(10.692152, 122.569852),
                                   new LatLng(10.692160, 122.569117),//sm

                                   new LatLng(10.696270, 122.569107),
                                   new LatLng(10.696489, 122.569016),
                                   new LatLng(10.696444, 122.568726),
                                   new LatLng(10.696588, 122.568995),

                                   new LatLng(10.696765, 122.569148),
                                   new LatLng(10.700052, 122.569125),
                                   new LatLng(10.700036, 122.569488)));
                   break;
                 case "Hall of Justice":

                   List<Vertex> path74 = getShortestPathTo(A5);
                   distanceview.setText(  path74 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A2_A5)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691952, 122.569606))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - University of Iloilo"));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692152, 122.569852))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704190, 122.568128))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .position(new LatLng(10.696444, 122.568726))
                           .title("Get off - Rose Pharmacy"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.691952, 122.569606),
                                   new LatLng(10.692152, 122.569852),
                                   new LatLng(10.692160, 122.569117),//sm

                                   new LatLng(10.696270, 122.569107),
                                   new LatLng(10.696489, 122.569016),
                                   new LatLng(10.696444, 122.568726),
                                   new LatLng(10.696588, 122.568995),

                                   new LatLng(10.696765, 122.569148),
                                   new LatLng(10.700052, 122.569125),
                                   new LatLng(10.701429, 122.569109),
                                   new LatLng(10.701429, 122.569109),
                                   new LatLng(10.701558, 122.569229),
                                   new LatLng(10.701985, 122.568368),
                                   new LatLng(10.702238, 122.568156),
                                   new LatLng(10.704119, 122.567963),
                                   new LatLng(10.704190, 122.568128)));
                   break;

                 case "Lapaz Public Market":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path75 = getShortestPathTo(A6);
                   distanceview.setText(  path75 +"\n " + A6.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A2_A6)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.691952, 122.569606))
                           .title("Start Point - University of Iloilo"));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692152, 122.569852))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708570, 122.567861))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Hall of Justice"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.696444, 122.568726))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Rose Pharmacy"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708451, 122.567992))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Petron"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.691952, 122.569606),
                                   new LatLng(10.692152, 122.569852),
                                   new LatLng(10.692160, 122.569117),//sm

                                   new LatLng(10.696270, 122.569107),
                                   new LatLng(10.696489, 122.569016),
                                   new LatLng(10.696444, 122.568726),
                                   new LatLng(10.696588, 122.568995),

                                   new LatLng(10.696765, 122.569148),
                                   new LatLng(10.700052, 122.569125),
                                   new LatLng(10.701429, 122.569109),
                                   new LatLng(10.701429, 122.569109),
                                   new LatLng(10.701558, 122.569229),
                                   new LatLng(10.701985, 122.568368),
                                   new LatLng(10.702238, 122.568156),
                                   new LatLng(10.704119, 122.567963),

                                   new LatLng(10.705073, 122.567924),
                                   new LatLng(10.706988, 122.567289),
                                   new LatLng(10.707776, 122.567131),
                                   new LatLng(10.708451, 122.567992),
                                   new LatLng(10.708570, 122.567861)));
                   break;
                 case "Gaisano City":

                   List<Vertex> path76 = getShortestPathTo(A7);
                   distanceview.setText(  path76 +"\n " + A7.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A2_A7)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691952, 122.569606))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - University of Iloilo"));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692152, 122.569852))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707540, 122.566972))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707862, 122.567282))
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .title("Get off - Andoks lapaz"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.691952, 122.569606),
                                   new LatLng(10.692152, 122.569852),
                                   new LatLng(10.692160, 122.569117),//sm

                                   new LatLng(10.696270, 122.569107),
                                   new LatLng(10.696489, 122.569016),
                                   new LatLng(10.696444, 122.568726),
                                   new LatLng(10.696588, 122.568995),

                                   new LatLng(10.696765, 122.569148),
                                   new LatLng(10.700052, 122.569125),
                                   new LatLng(10.701429, 122.569109),
                                   new LatLng(10.701429, 122.569109),
                                   new LatLng(10.701558, 122.569229),
                                   new LatLng(10.701985, 122.568368),
                                   new LatLng(10.702238, 122.568156),
                                   new LatLng(10.704119, 122.567963),
                                   new LatLng(10.705194, 122.567887),

                                   new LatLng(10.706979, 122.567289),
                                   new LatLng(10.707778, 122.567117),

                                   new LatLng(10.707862, 122.567282),
                                   new LatLng(10.707606, 122.567224),

                                   new LatLng(10.707540, 122.566972)));
                   break;
                 case "University of San Agustin":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path77 = getShortestPathTo(A8);
                   distanceview.setText(  path77 +"\n " + A8.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A2_A8)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.691952, 122.569606))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - University of Iloilo"));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692152, 122.569852))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.707540, 122.566972))
                           .title("End Point - Gaisano City"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.getoff))

                           .position(new LatLng(10.707862, 122.567282))
                           .title("Get off - Andoks lapaz"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.691952, 122.569606),
                                   new LatLng(10.692152, 122.569852),
                                   new LatLng(10.692165, 122.569063),//sm

                                   new LatLng(10.692037, 122.569047),
                                   new LatLng(10.691926, 122.567958),
                                   new LatLng(10.697857, 122.567812),
                                   new LatLng(10.697893, 122.566420),

                                   new LatLng(10.697693, 122.564806),
                                   new LatLng(10.701165, 122.564869),
                                   new LatLng(10.700911, 122.563054),
                                   new LatLng(10.700650, 122.563025)));
                   break;
                 case "Robinson City":

                   List<Vertex> path78 = getShortestPathTo(A1);
                   distanceview.setText(  path78 +"\n " + A1.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A2_A1)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.691922, 122.569899))
                           .title("Start Point - University of Iloilo"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692123, 122.569915))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM Mandurriao Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.700613, 122.563044))
                           .title("End Point - Robinson"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.691863, 122.569967),
                                   new LatLng(10.692123, 122.569915),
                                   new LatLng(10.692049, 122.568967),//sm

                                   new LatLng(10.691949, 122.567946),
                                   new LatLng(10.696173, 122.567867),
                                   new LatLng(10.697883, 122.567798),

                                   new LatLng(10.697899, 122.566314),
                                   new LatLng(10.697693, 122.564806),
                                   new LatLng(10.701164, 122.564875),
                                   new LatLng(10.700914, 122.563070),
                                   new LatLng(10.700613, 122.563044)));
                   break;
                 case "University of Iloilo":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   pathView2.setText(Html.fromHtml("You are currently in University of Iloilo"
                           , Html.FROM_HTML_MODE_COMPACT));
                   break;
                 case "SM City":

                   List<Vertex> path79 = getShortestPathTo(A3);
                   distanceview.setText(  path79 +"\n " + A3.minDistance + " kilometer" );
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   pathView2.setText(Html.fromHtml(getString(R.string.A2_A3)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.691922, 122.569899))
                           .title("Start Point - University of San Agustin"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.692124, 122.569895))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.713648, 122.551834))
                           .title("End Point - SM City"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.691922, 122.569899),
                                   new LatLng(10.692124, 122.569895),
                                   new LatLng(10.692157, 122.569121),//sm

                                   new LatLng(10.696409, 122.569078),
                                   new LatLng(10.696491, 122.568908),
                                   new LatLng(10.694290, 122.561749),

                                   new LatLng(10.697282, 122.561754),
                                   new LatLng(10.696581, 122.554863),
                                   new LatLng(10.698787, 122.554581),
                                   new LatLng(10.709368, 122.551760),

                                   new LatLng(10.711448, 122.551810),
                                   new LatLng(10.713489, 122.552357),
                                   new LatLng(10.713648, 122.551834)));
                   break;

               }
           }
           switch (start) {
             case "SM City":
               computePaths(A3);

               switch (destination) {
                 case "Plaza Libertad":
                   if (R.id.spinner == 1 && R.id.spinner2 == 2) ;
                   List<Vertex> path82 = getShortestPathTo(A9);
                   distanceview.setText(  path82 +"\n " + A9.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A3_A9)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.714123, 122.551454))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713715, 122.552212))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.692384, 122.573438))
                           .title("End Point - Plaza Libertad"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.713715, 122.552212),
                                   new LatLng(10.711310, 122.551673),
                                   new LatLng(10.709053, 122.551659),//sm

                                   new LatLng(10.699676, 122.554176),
                                   new LatLng(10.700111, 122.558009),
                                   new LatLng(10.700765, 122.563038),

                                   new LatLng(10.701690, 122.568406),
                                   new LatLng(10.701459, 122.569054),
                                   new LatLng(10.696629, 122.569019),
                                   new LatLng(10.694783, 122.570444),
                                   new LatLng(10.693940, 122.570991),

                                   new LatLng(10.692917, 122.572552),
                                   new LatLng(10.692863, 122.572728),
                                   new LatLng(10.692305, 122.573347),
                                   new LatLng(10.692384, 122.573438)));
                   break;
                 case "Grand Xing Imperial":

                   if (R.id.spinner == 1 && R.id.spinner2 == 3) ;
                   List<Vertex> path83 = getShortestPathTo(A4);
                   distanceview.setText(  path83 +"\n " + A4.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A3_A4)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.714123, 122.551454))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713715, 122.552212))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699622, 122.569358))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Grand Xing Imperial"));


                   mMap.addPolyline(new PolylineOptions()
                         .clickable(true)
                         .width(15)
                         .color(Color.BLACK)
                         .add(
                                 new LatLng(10.713715, 122.552212),
                                 new LatLng(10.711310, 122.551673),
                                 new LatLng(10.709053, 122.551659),//sm

                                 new LatLng(10.699676, 122.554176),//general luna
                                 new LatLng(10.700111, 122.558009),
                                 new LatLng(10.700765, 122.563038),

                                 new LatLng(10.701690, 122.568406),
                                 new LatLng(10.701459, 122.569054),


                                 new LatLng(10.699639, 122.569075),
                                 new LatLng(10.699622, 122.569358)));


                   break;
                 case "Hall of Justice":

                   List<Vertex> path84 = getShortestPathTo(A5);
                   distanceview.setText(  path84 +"\n " + A5.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A3_A5)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.714123, 122.551454))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713715, 122.552212))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699868, 122.569097))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699932, 122.569136))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.704420, 122.568198))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Hall of Justice"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.713715, 122.552212),
                                   new LatLng(10.711310, 122.551673),
                                   new LatLng(10.709053, 122.551659),//sm

                                   new LatLng(10.699676, 122.554176),//general luna
                                   new LatLng(10.700111, 122.558009),
                                   new LatLng(10.700765, 122.563038),

                                   new LatLng(10.701690, 122.568406),
                                   new LatLng(10.701459, 122.569054),


                                   new LatLng(10.699639, 122.569075),
                                   new LatLng(10.699622, 122.569358)));
                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.699622, 122.569358),
                                   new LatLng(10.699932, 122.569136),
                                   new LatLng(10.701413, 122.569123),//sm

                                   new LatLng(10.701540, 122.569232),//general luna
                                   new LatLng(10.701883, 122.568524),

                                   new LatLng(10.702228, 122.568162),

                                   new LatLng(10.704388, 122.567954),
                                   new LatLng(10.704420, 122.568198)));

                   break;

                 case "Lapaz Public Market":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path85 = getShortestPathTo(A6);
                   distanceview.setText(  path85 +"\n " + A6.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A3_A6)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.714123, 122.551454))
                           .title("Start Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713715, 122.552212))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699868, 122.569097))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699932, 122.569136))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.708699, 122.567960))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Lapaz Public Market"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.713715, 122.552212),
                                   new LatLng(10.711310, 122.551673),
                                   new LatLng(10.709053, 122.551659),//sm

                                   new LatLng(10.699676, 122.554176),//general luna
                                   new LatLng(10.700111, 122.558009),
                                   new LatLng(10.700765, 122.563038),

                                   new LatLng(10.701690, 122.568406),
                                   new LatLng(10.701459, 122.569054),


                                   new LatLng(10.699639, 122.569075),
                                   new LatLng(10.699622, 122.569358)));
                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.699622, 122.569358),
                                   new LatLng(10.699932, 122.569136),
                                   new LatLng(10.701413, 122.569123),//sm
                                   new LatLng(10.701540, 122.569232),//general luna
                                   new LatLng(10.701883, 122.568524),
                                   new LatLng(10.702228, 122.568162),
                                   new LatLng(10.704388, 122.567954),

                                   new LatLng(10.705134, 122.567924),//general luna
                                   new LatLng(10.706975, 122.567308),

                                   new LatLng(10.707787, 122.567123),

                                   new LatLng(10.707787, 122.567123),
                                   new LatLng(10.708528, 122.568091),

                                   new LatLng(10.708699, 122.567960)));
                   break;
                 case "Gaisano City":

                   List<Vertex> path86 = getShortestPathTo(A7);
                   distanceview.setText(  path86 +"\n " + A7.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A3_A7)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.714123, 122.551454))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713715, 122.552212))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699868, 122.569097))
                           .icon(vectorToBitmap(R.drawable.cross))

                           .title("Cross Pedestrian - Grand Xing Imperial"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.699932, 122.569136))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - Lapaz Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.707528, 122.566955))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Lapaz Public Market"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.713715, 122.552212),
                                   new LatLng(10.711310, 122.551673),
                                   new LatLng(10.709053, 122.551659),//sm

                                   new LatLng(10.699676, 122.554176),//general luna
                                   new LatLng(10.700111, 122.558009),
                                   new LatLng(10.700765, 122.563038),

                                   new LatLng(10.701690, 122.568406),
                                   new LatLng(10.701459, 122.569054),


                                   new LatLng(10.699639, 122.569075),
                                   new LatLng(10.699622, 122.569358)));
                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.GRAY)
                           .add(
                                   new LatLng(10.699622, 122.569358),
                                   new LatLng(10.699932, 122.569136),
                                   new LatLng(10.701413, 122.569123),//sm
                                   new LatLng(10.701540, 122.569232),//general luna
                                   new LatLng(10.701883, 122.568524),
                                   new LatLng(10.702228, 122.568162),
                                   new LatLng(10.704388, 122.567954),

                                   new LatLng(10.705134, 122.567924),//general luna
                                   new LatLng(10.706975, 122.567308),

                                   new LatLng(10.707787, 122.567123),

                                   new LatLng(10.707787, 122.567123),
                                   new LatLng(10.707814, 122.567287),

                                   new LatLng(10.707599, 122.567245),
                                   new LatLng(10.707528, 122.566955)));
                   break;
                 case "University of San Agustin":

                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path87 = getShortestPathTo(A8);
                   distanceview.setText(  path87 +"\n " + A8.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A3_A8)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.714123, 122.551454))
                           .title("Start Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713715, 122.552212))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.700494, 122.563027))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - University of San Agustin"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.713715, 122.552212),
                                   new LatLng(10.711310, 122.551673),
                                   new LatLng(10.709053, 122.551659),//sm


                                   new LatLng(10.699676, 122.554176),
                                   new LatLng(10.700317, 122.560028),
                                   new LatLng(10.700736, 122.563059),
                                   new LatLng(10.700494, 122.563027)));
                   break;
                 case "Robinson City":

                   List<Vertex> path88 = getShortestPathTo(A1);
                   distanceview.setText(  path88 +"\n " + A1.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A3_A1)));

                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.714123, 122.551454))
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .title("Start Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713715, 122.552212))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.695067, 122.565311))
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .title("End Point - Robinson City"));



                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.713715, 122.552212),
                                   new LatLng(10.711310, 122.551673),
                                   new LatLng(10.709053, 122.551659),//sm


                                   new LatLng(10.699676, 122.554176),
                                   new LatLng(10.700317, 122.560028),
                                   new LatLng(10.700587, 122.561746),

                                   new LatLng(10.698757, 122.561715),
                                   new LatLng(10.694191, 122.561744),
                                   new LatLng(10.695255, 122.565222),
                                   new LatLng(10.695067, 122.565311)));
                   break;
                 case "University of Iloilo":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;

                   List<Vertex> path89 = getShortestPathTo(A2);
                   distanceview.setText(  path89 +"\n " + A2.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml(getString(R.string.A3_A2)));

                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.brattt))

                           .position(new LatLng(10.714123, 122.551454))
                           .title("Start Point - SM City"));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.713715, 122.552212))
                           .icon(vectorToBitmap(R.drawable.tryridez))

                           .title("Ride - SM City Proper Jeep"));
                   mMap.addMarker(new MarkerOptions()
                           .icon(vectorToBitmap(R.drawable.amupagidni))

                           .position(new LatLng(10.691831, 122.569834))
                           .title("End Point - University of Iloilo"));


                   mMap.addPolyline(new PolylineOptions()
                           .clickable(true)
                           .width(15)
                           .color(Color.BLACK)
                           .add(
                                   new LatLng(10.713715, 122.552212),
                                   new LatLng(10.711310, 122.551673),
                                   new LatLng(10.709053, 122.551659),//sm


                                   new LatLng(10.699676, 122.554176),
                                   new LatLng(10.700602, 122.561759),
                                   new LatLng(10.694227, 122.561740),

                                   new LatLng(10.695276, 122.565269),
                                   new LatLng(10.696398, 122.568976),
                                   new LatLng(10.692026, 122.569047),
                                   new LatLng(10.692024, 122.569831),
                                   new LatLng(10.691831, 122.569834)));
                   break;

                 case "SM City":
                   if (R.id.spinner == 1 && R.id.spinner2 == 4) ;
                   List<Vertex> path90 = getShortestPathTo(A3);
                   distanceview.setText(  path90 +"\n " + A3.minDistance + " kilometer" );
                   pathView2.setText(Html.fromHtml("You are currently in SM City"
                           , Html.FROM_HTML_MODE_COMPACT));
                   mMap.addMarker(new MarkerOptions()
                           .position(new LatLng(10.714123, 122.551454))
                           .title("Start Point - SM City"));
                   break;

               }
           }






         }
            }
            );
          }
        });
      }

      @Override
      public void onItemSelected(MaterialSpinner view, int position, long id, String item) {


      }
    });
  }
  public void openMain() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    mMap.setOnMyLocationButtonClickListener(this);
    mMap.setOnMyLocationClickListener(this);
    enableMyLocation();

    LatLng latLng = new LatLng(10.70170436, 122.56821871);
mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15),5000,null);
  }
  private void enableMyLocation() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
      // Permission to access the location is missing.
      PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
              Manifest.permission.ACCESS_FINE_LOCATION, true);
    } else if (mMap != null) {
      // Access to the location has been granted to the app.
      mMap.setMyLocationEnabled(true);
    }
  }

  @Override
  public boolean onMyLocationButtonClick() {
    Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
    // Return false so that we don't consume the event and the default behavior still occurs
    // (the camera animates to the user's current position).
    return false;
  }

  @Override
  public void onMyLocationClick(@NonNull Location location) {
    Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
      return;
    }

    if (PermissionUtils.isPermissionGranted(permissions, grantResults,
            Manifest.permission.ACCESS_FINE_LOCATION)) {
      // Enable the my location layer if the permission has been granted.
      enableMyLocation();
    } else {
      // Display the missing permission error dialog when the fragments resume.
      mPermissionDenied = true;
    }
  }

  @Override
  protected void onResumeFragments() {
    super.onResumeFragments();
    if (mPermissionDenied) {
      // Permission was not granted, display error dialog.
      showMissingPermissionError();
      mPermissionDenied = false;
    }
  }

  /**
   * Displays a dialog with error message explaining that the location permission is missing.
   */
  private void showMissingPermissionError() {


  }



  private BitmapDescriptor vectorToBitmap(@DrawableRes int id) {
    Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
    Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
            vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());

    vectorDrawable.draw(canvas);
    return BitmapDescriptorFactory.fromBitmap(bitmap);
  }



}