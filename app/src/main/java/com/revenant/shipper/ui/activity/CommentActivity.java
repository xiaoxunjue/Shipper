package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import com.revenant.shipper.R;
import com.revenant.shipper.adapter.CommentListItemAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.CommentListIteamBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Order_list;


public class CommentActivity extends BaseActivity {
    @BindView(R.id.left_b_bar)
    ImageView leftBBar;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.righ_bar_image)
    ImageView righBarImage;
    @BindView(R.id.shownodata)
    ImageView shownodata;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout smartrefresh;
    private CommentListItemAdapter commentListItemAdapter;
    private Context context;
    private int pagernum = 1;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView() {
        context = this;
        centerTitle.setText("评价");
        righBarImage.setImageResource(R.mipmap.kefu);
        commentListItemAdapter = new CommentListItemAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(commentListItemAdapter);
        smartrefresh.autoRefresh();
        smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        commentListItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("orderid", String.valueOf(commentListItemAdapter.getData().get(position).getId()));
                bundle.putString("orderno", String.valueOf(commentListItemAdapter.getData().get(position).getOrderNo()));
                bundle.putString("orderstart", String.valueOf(commentListItemAdapter.getData().get(position).getLoading()));
                bundle.putString("orderend", String.valueOf(commentListItemAdapter.getData().get(position).getUnload()));
                bundle.putString("orderprice", String.valueOf(commentListItemAdapter.getData().get(position).getAmount()));
                bundle.putString("ordercar", String.valueOf(commentListItemAdapter.getData().get(position).getGoodsInfo()));
                bundle.putString("ordertype", String.valueOf(commentListItemAdapter.getData().get(position).getVehicleNumber()));
                bundle.putString("ordername", String.valueOf(commentListItemAdapter.getData().get(position).getDriverName()));
                bundle.putString("orderphoto", String.valueOf(commentListItemAdapter.getData().get(position).getDriverPhoto()));
                startActivity(CommentDetailActivity.class, bundle);
            }
        });

        commentListItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.comment_order_submit:
                        Bundle bundle = new Bundle();
                        bundle.putString("orderid", String.valueOf(commentListItemAdapter.getData().get(position).getId()));
                        bundle.putString("orderno", String.valueOf(commentListItemAdapter.getData().get(position).getOrderNo()));
                        bundle.putString("orderstart", String.valueOf(commentListItemAdapter.getData().get(position).getLoading()));
                        bundle.putString("orderend", String.valueOf(commentListItemAdapter.getData().get(position).getUnload()));
                        bundle.putString("orderprice", String.valueOf(commentListItemAdapter.getData().get(position).getAmount()));
                        bundle.putString("ordercar", String.valueOf(commentListItemAdapter.getData().get(position).getGoodsInfo()));
                        bundle.putString("ordertype", String.valueOf(commentListItemAdapter.getData().get(position).getVehicleNumber()));
                        bundle.putString("ordername", String.valueOf(commentListItemAdapter.getData().get(position).getDriverName()));
                        bundle.putString("orderphoto", String.valueOf(commentListItemAdapter.getData().get(position).getDriverPhoto()));
                        startActivity(AddCommentActivity.class, bundle);
                        break;
                }
            }
        });

    }

    @Override
    public void initData() {
        smartrefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagernum++;
                indata();
                smartrefresh.finishLoadMore(true);//加载完成
            }
        });
        //刷新
        smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                commentListItemAdapter.setNewData(null);
                pagernum = 1;
                indata();
                smartrefresh.finishRefresh(true);//刷新完成
            }
        });
    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_b_bar, R.id.righ_bar_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_b_bar:
                finish();
                break;
            case R.id.righ_bar_image:
                Utils.callPhone(CommentActivity.this, "0412-8882888");
                break;
        }
    }

    private void indata() {
        OkGo.<String>get(Order_list)
                .params("accountId", SPUtils.getAccounId(context))
                .params("shipperEvaluate", "0")
                .params("status", "5")
                .params("pageNum", pagernum).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.code() == 200) {
                    CommentListIteamBean commentListIteamBean = GsonUtil.parseJsonWithGson(response.body(), CommentListIteamBean.class);
                    if (commentListIteamBean.getCode().equals("0")) {
                        int pageNum = commentListIteamBean.getData().getPageNum();
                        int pages = commentListIteamBean.getData().getPages();
                        if (pageNum == 1) {
                            shownodata.setVisibility(View.GONE);
                            commentListItemAdapter.setNewData(commentListIteamBean.getData().getResult());
                        } else if (pageNum <= pages) {
                            commentListItemAdapter.addData(commentListIteamBean.getData().getResult());
                        } else {
                            commentListItemAdapter.loadMoreEnd();
                        }
//

                    } else {
                        commentListItemAdapter.loadMoreFail();
                    }
                }

            }
        });
    }
}
