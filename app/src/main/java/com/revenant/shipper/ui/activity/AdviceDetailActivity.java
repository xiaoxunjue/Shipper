package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.AdviceDetailBean;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdviceDetailActivity extends BaseActivity {

    @BindView(R.id.title_time)
    TextView titleTime;
    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.reply_content)
    TextView replyContent;
    @BindView(R.id.reply_time)
    TextView replyTime;
    @BindView(R.id.left_b_bar)
    ImageView leftBBar;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.righ_bar_image)
    ImageView righBarImage;
    @BindView(R.id.showtitle)
    TextView showtitle;
    private TextView centerview;
    private ImageView rightiImageView;
    private ImageView leftImageView;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_advice_detail;
    }

    @Override
    public void initView() {
        centerview = findViewById(R.id.center_title);
        rightiImageView = findViewById(R.id.righ_bar_image);
        leftImageView = findViewById(R.id.left_b_bar);
        centerview.setText("意见反馈");
        rightiImageView.setImageResource(R.mipmap.kefu);
        leftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("AAAAAAAAAAAAA");
                finish();
            }
        });

        rightiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.callPhone(AdviceDetailActivity.this, "0412-8882888");

            }
        });
    }

    @Override
    public void initData() {
        String adviceid = getIntent().getStringExtra("adviceid");
        Map<String, String> map = new HashMap<>();
        map.put("id", adviceid);
        JSONObject jsonObject = new JSONObject(map);
        complaintdetail(adviceid);

    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    private void complaintdetail(String id) {
        OkGo.<String>post(Constants.Complaint_FindById + "/" + id)
                .headers("Content-Type", "application/json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AdviceDetailBean bean = GsonUtil.parseJsonWithGson(response.body(), AdviceDetailBean.class);
                        if (Integer.valueOf(bean.getCode()) == 0) {
                            titleTime.setText(bean.getData().getSysComplaint().getCtime());
                            titleContent.setText(bean.getData().getSysComplaint().getContent());
                            if (bean.getData().getReply().getContent()==null) {
                                    showtitle.setVisibility(View.INVISIBLE);
                            }else {
                                replyTime.setText(bean.getData().getReply().getCtime());
                                replyContent.setText(bean.getData().getReply().getContent());
                            }


                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.getException().getMessage());
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
