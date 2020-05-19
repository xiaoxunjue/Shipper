package com.revenant.shipper.ui.activity;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolylineOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.GuiJIBean;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;

import java.util.Arrays;
import java.util.List;

public class ShipperMapActivity extends AppCompatActivity {


    ImageView leftBaseBar;
    TextView centerBaseBarTitle;

    MapView mapView;
    private AMap aMap;
    int orderid;
    private List<Double> mDoubles = new ArrayList<>();
    private List<LatLng> listLat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_shipper_map);
        centerBaseBarTitle = findViewById(R.id.center_base_bar_title);
        centerBaseBarTitle.setText("司机轨迹");

        leftBaseBar = findViewById(R.id.left_base_bar);
        leftBaseBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        initView();
        initData();
    }


    public void initView() {

        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    public void initData() {
        orderid = getIntent().getIntExtra("orderid", 0);
        getTrack(orderid);
    }

    private void init() {
        setUpMap();
    }

    private void setUpMap() {
        aMap.moveCamera(CameraUpdateFactory.zoomTo(4));


//        // 绘制一个乌鲁木齐到哈尔滨的线

        //绘制一条广州到乌鲁木齐的大地曲线
//        aMap.addPolyline((new PolylineOptions()).add(
//                new LatLng(23.15, 113.26), new LatLng(43.828, 87.621)).color(
//                Color.RED)).setGeodesic(false);


//        aMap.addPolyline((new PolylineOptions()).add(
//                readLatLngs()).color(
//                Color.RED)).setGeodesic(false);
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
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
                                    mDoubles.add(Double.valueOf(split[0]));
                                    mDoubles.add(Double.valueOf(split[1]));
                                } else if (data.size() > 1) {
                                    for (int i = 0; i < data.size(); i++) {
                                        String[] split = data.get(i).replace("[", "").replace("]", "").split(",");
                                        mDoubles.add(Double.valueOf(split[0]));
                                        mDoubles.add(Double.valueOf(split[1]));
                                    }
                                }
                                readLatLngs();

                            }

                        }

                    }
                });
    }

    private void readLatLngs() {
        List<LatLng> points = new ArrayList<LatLng>();
//        for (int i = 0; i < mDoubles.size()/20; i += 2) {
//            points.add(new LatLng(mDoubles.get(i), mDoubles.get(i + 1)));
//        }


        List<LatLng> locations=new ArrayList<LatLng> ();
        locations.add(new LatLng(43.828, 87.621));
        locations.add(new LatLng(45.808, 126.55));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(4));
        aMap.addPolyline((new PolylineOptions()).addAll(
                locations).color(
                Color.RED));
    }
}
