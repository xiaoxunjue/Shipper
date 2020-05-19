package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.PersonalCommentAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.InviteCode;
import com.revenant.shipper.bean.PersonalCommentShowBean;
import com.revenant.shipper.bean.ShipperBean.PersonalDetailBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Comment_PerSonal_Show;
import static com.revenant.shipper.utils.Constants.Get_personal_info;
import static com.revenant.shipper.utils.Constants.SelectInvite;
import static com.revenant.shipper.utils.Constants.UpdateInvite;

public class PersonalActivity extends BaseActivity {
    @BindView(R.id.upload_phote)
    ImageView uploadPhote;

    String imageurl = "";
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.qiye)
    RelativeLayout qiye;
    @BindView(R.id.jiaoyi)
    TextView jiaoyi;
    @BindView(R.id.jiehuo)
    TextView jiehuo;
    @BindView(R.id.pingjia)
    TextView pingjia;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout smartrefresh;
    private Context context;
    private int status = 0;
    PersonalCommentAdapter personalCommentAdapter;
    private int pagernum = 1;
    @BindView(R.id.invite_code)
    EditText inviteCodeEdit;

    String inviteCodeText="";

    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_personal;
    }

    @Override
    public void initView() {

        inviteCodeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 4) {
                    updateInvite(s.toString());
                }

            }
        });


        Bundle bundle = getIntent().getExtras();
        getpseronal();
        selectInvite();
        setcenterTitle("个人信息");
        personalCommentAdapter = new PersonalCommentAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(personalCommentAdapter);
        smartrefresh.autoRefresh();
        smartrefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagernum++;
                getScore();
                smartrefresh.finishLoadMore(true);//加载完成
            }
        });
        //刷新
        smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                personalCommentAdapter.setNewData(null);
                pagernum = 1;
                getScore();
                smartrefresh.finishRefresh(true);//刷新完成
            }
        });

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
//        startActivity(MineDriverActivity.class);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.upload_phote, R.id.qiye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload_phote:
                startActivity(UploadPhotoActivity.class);
                break;
            case R.id.qiye:
                switch (status) {
                    case 0:
                        break;
                    case 1:
                        startActivity(UploadCertificatePhotoThreeActivity.class);
                        break;
                    case 2:
                        showToast("企业认证待审核");
                        break;
                    case 3:
                        showToast("企业认证通过");
                        break;
                    case 4:
                        showToast("企业认证不通过");
                        startActivity(UploadCertificatePhotoThreeActivity.class);
                        break;

                }

                break;
        }

    }

    private void getpseronal() {

        OkGo.<String>get(Get_personal_info)
                .params("id", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("AAAA" + response.body());
                        PersonalDetailBean g = GsonUtil.parseJsonWithGson(response.body(), PersonalDetailBean.class);
                        name.setText(g.getData().getUsername());
                        phone.setText(g.getData().getMobile());
                        Glide.with(context).load(g.getData().getPhoto()).placeholder(R.mipmap.picture).centerCrop().
                                into(uploadPhote);
                        status = Integer.valueOf(g.getData().getStatus());
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getpseronal();
    }

    private void getScore() {
        OkGo.<String>get(Comment_PerSonal_Show)

                .params("id", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            PersonalCommentShowBean personal = GsonUtil.parseJsonWithGson(response.body(), PersonalCommentShowBean.class);
                            if (Integer.valueOf(personal.getCode()) == 0) {

                                jiaoyi.setText(String.valueOf(personal.getData().getOrderCount()));
                                pingjia.setText(String.valueOf(personal.getData().getEvaluateCount()));
                                jiehuo.setText(personal.getData().getRate());
                                personalCommentAdapter.setNewData(personal.getData().getList());
                                smartrefresh.finishRefresh();

                            }

                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.message());
                    }
                });
    }

    private void selectInvite() {
        OkGo.<String>get(SelectInvite)

                .params("accountId", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            InviteCode personal = GsonUtil.parseJsonWithGson(response.body(), InviteCode.class);
                            if (Integer.valueOf(personal.getCode()) == 0) {


                            }

                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.message());
                    }
                });
    }


    private void updateInvite(String inviteCode) {
        OkGo.<String>get(UpdateInvite)

                .params("accountId", SPUtils.getAccounId(context))
                .params("inviteCode",inviteCode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            InviteCode personal = GsonUtil.parseJsonWithGson(response.body(), InviteCode.class);
                            if (Integer.valueOf(personal.getCode()) == 0) {
                                showToast(personal.getMsg());

                            } else {
                                showToast(personal.getMsg());
                                inviteCodeEdit.setText("");
                            }

                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.message());
                    }
                });
    }


}
