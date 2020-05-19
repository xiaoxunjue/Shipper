package com.revenant.shipper.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.GuiJIBean;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ShipperTrackActivity extends AppCompatActivity {
    private MapView mapView;

    private AMap aMap;

    private SmoothMoveMarker moveMarker;


    private static final int START_STATUS = 0;

    private static final int MOVE_STATUS = 1;

    private static final int PAUSE_STATUS = 2;
    private static final int FINISH_STATUS = 3;

    private int mMarkerStatus = START_STATUS;
    int orderid;
    private ImageView leftview;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_track);
        leftview = findViewById(R.id.left_base_bar);
        leftview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView = findViewById(R.id.center_base_bar_title);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        init();

        textView.setText("司机轨迹");

        orderid = getIntent().getIntExtra("orderid", 0);
        getTrack(orderid);

    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    private void initMoveMarker() {
        addPolylineInPlayGround();
        // 获取轨迹坐标点
        List<LatLng> points = readLatLngs();
        LatLngBounds.Builder b = LatLngBounds.builder();
        for (int i = 0; i < points.size(); i++) {
            b.include(points.get(i));
        }
        LatLngBounds bounds = b.build();
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));

        moveMarker = new SmoothMoveMarker(aMap);
        // 设置滑动的图标
        moveMarker.setDescriptor(BitmapDescriptorFactory.fromResource(R.mipmap.car));

        /*
        //当移动Marker的当前位置不在轨迹起点，先从当前位置移动到轨迹上，再开始平滑移动
        // LatLng drivePoint = points.get(0);//设置小车当前位置，可以是任意点，这里直接设置为轨迹起点
        LatLng drivePoint = new LatLng(39.980521,116.351905);//设置小车当前位置，可以是任意点
        Pair<Integer, LatLng> pair = PointsUtil.calShortestDistancePoint(points, drivePoint);
        points.set(pair.first, drivePoint);
        List<LatLng> subList = points.subList(pair.first, points.size());
        // 设置滑动的轨迹左边点
        smoothMarker.setPoints(subList);*/

        moveMarker.setPoints(points);//设置平滑移动的轨迹list
//        moveMarker.setTotalDuration(8);//设置平滑移动的总时间

        aMap.setInfoWindowAdapter(infoWindowAdapter);
        moveMarker.setMoveListener(
                new SmoothMoveMarker.MoveListener() {
                    @Override
                    public void move(final double distance) {

                        Log.i("MY", "distance:  " + distance);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (infoWindowLayout != null && title != null && moveMarker.getMarker().isInfoWindowShown()) {
                                    title.setText("距离终点还有： " + (int) distance + "米");
                                }
                                if (distance == 0) {
                                    moveMarker.getMarker().hideInfoWindow();
                                    mMarkerStatus = FINISH_STATUS;
                                }
                            }
                        });
                    }
                });
        moveMarker.getMarker().showInfoWindow();
        moveMarker.startSmoothMove();
    }


    public void move(View view) {

        moveMarker.startSmoothMove();
    }

    AMap.InfoWindowAdapter infoWindowAdapter = new AMap.InfoWindowAdapter() {
        @Override
        public View getInfoWindow(Marker marker) {

            return getInfoWindowView(marker);
        }

        @Override
        public View getInfoContents(Marker marker) {


            return getInfoWindowView(marker);
        }
    };

    LinearLayout infoWindowLayout;
    TextView title;
    TextView snippet;

    private View getInfoWindowView(Marker marker) {
        if (infoWindowLayout == null) {
            infoWindowLayout = new LinearLayout(ShipperTrackActivity.this);
            infoWindowLayout.setOrientation(LinearLayout.VERTICAL);
            title = new TextView(ShipperTrackActivity.this);
            snippet = new TextView(ShipperTrackActivity.this);
            title.setTextColor(Color.BLACK);
            snippet.setTextColor(Color.BLACK);
            infoWindowLayout.setBackgroundResource(R.mipmap.infowindow_bg);

            infoWindowLayout.addView(title);
            infoWindowLayout.addView(snippet);
        }

        return infoWindowLayout;
    }

    private void addPolylineInPlayGround() {
        List<LatLng> list = readLatLngs();
        List<Integer> colorList = new ArrayList<Integer>();
        aMap.addPolyline(new PolylineOptions().setCustomTexture(BitmapDescriptorFactory.fromResource(R.mipmap.custtexture)) //setCustomTextureList(bitmapDescriptors)
                .addAll(list)
                .useGradient(true)
                .width(18));
        if (list != null) {
            if (list.size() == 1) {

                LatLng latLng = list.get(0);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(latLng);
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.start)));
                final Marker marker = aMap.addMarker(markerOption);
            } else if (list.size() > 1) {
                LatLng latLng = list.get(0);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(latLng);
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.start)));
                final Marker marker = aMap.addMarker(markerOption);

                LatLng latLng1 = list.get(list.size() - 1);
                MarkerOptions markerOption1 = new MarkerOptions();
                markerOption1.position(latLng1);
                markerOption1.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.end)));
                final Marker marker1 = aMap.addMarker(markerOption1);
            }
        }
    }

    private List<LatLng> readLatLngs() {
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < mDoubles.size(); i += 2) {
//            points.add(new LatLng(mDoubles.get(i + 1), mDoubles.get(i)));
            points.add(new LatLng(mDoubles.get(i), mDoubles.get(i + 1)));
        }
        for (int i = 0; i < points.size(); i++) {
            LogUtils.d("经度:" + points.get(i).longitude);
            LogUtils.d("维度:" + points.get(i).latitude);
        }
        return points;
    }


    private List<Double> mDoubles = new ArrayList<>();

    private void getTrack(int orderid) {
        OkGo.<String>get(Constants.Driver_Track)
                .params("orderId", orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        GuiJIBean g = GsonUtil.parseJsonWithGson(response.body(), GuiJIBean.class);
                        if (g.getCode().equals("0")) {
                            if (g.getData().size() > 0) {
                                List<String> data = g.getData();
                                if (data.size() > 0 && data.size() == 1) {
                                    String[] split = data.get(0).replace("[", "").replace("]", "").split(",");
                                    LogUtils.d("WWWWWWWWWWWWW"+split.toString());
                                    int length = split.length;
                                    LogUtils.d("WWWWWWWWWWWWW"+length);
                                    if (split.length>0){
                                        mDoubles.add(Double.valueOf(split[0]));
                                        mDoubles.add(Double.valueOf(split[1]));
                                    }


                                } else if (data.size() > 1) {
                                    for (int i = 0; i < data.size(); i++) {
                                        String[] split = data.get(i).replace("[", "").replace("]", "").split(",");
                                        mDoubles.add(Double.valueOf(split[0]));
                                        mDoubles.add(Double.valueOf(split[1]));
                                    }
                                }

                                if (data.size() > 1) {
                                    initMoveMarker();
                                } else if (data.size() == 1) {

                                    String[] split = data.get(0).replace("[", "").replace("]", "").split(",");
                                    LogUtils.d("WWWWWWWWWWWWW"+split.toString());
                                    int length = split.length;
                                    LogUtils.d("WWWWWWWWWWWWW"+length);
                                    if (split.length>0){
                                        LatLng latLng = readLatLngs().get(0);

                                        MarkerOptions markerOption = new MarkerOptions();
                                        markerOption.position(latLng);

                                        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                                .decodeResource(getResources(), R.mipmap.start)));

                                        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                                        final Marker marker = aMap.addMarker(markerOption);

                                        List<LatLng> points = readLatLngs();
                                        LatLngBounds.Builder b = LatLngBounds.builder();
                                        for (int i = 0; i < points.size(); i++) {
                                            b.include(points.get(i));
                                        }
                                        LatLngBounds bounds = b.build();
                                        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
                                    }




                                }

                            }

                        }

                    }
                });
    }
}