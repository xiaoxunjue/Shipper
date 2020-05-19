package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.NesListDetailBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.revenant.shipper.utils.Constants.News_Detail;

public class MessageDetailActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.time)
    TextView time;
    private int id;
    private int newstype=0;
    private List<String> newslist= Arrays.asList("系统消息","私人消息");
    @Override
    public int getContentViewResId() {
        return R.layout.item_news_detail;
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("newsid");
        newstype = bundle.getInt("newstype");
        setcenterTitle(newslist.get(newstype));

    }

    @Override
    public void initData() {


        getmesagedetail(id);


    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void getmesagedetail(int id) {
        OkGo.<String>get(News_Detail)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        NesListDetailBean listDetailBean = GsonUtil.parseJsonWithGson(response.body(), NesListDetailBean.class);
                        if (listDetailBean.getCode().equals("0")) {

                            time.setText(listDetailBean.getData().getCreateTime());

                            title.setText(listDetailBean.getData().getTitle());
                            content.setText(listDetailBean.getData().getMsg());
                        }
                    }
                });
    }
}
