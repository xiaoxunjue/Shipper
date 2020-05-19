package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.BaseBean.BaseTestBean;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCommentActivity extends BaseActivity {


    @BindView(R.id.left_b_bar)
    ImageView leftBBar;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.righ_bar_image)
    ImageView righBarImage;
    @BindView(R.id.editcount)
    EditText editText;
    @BindView(R.id.edittextcount)
    TextView editcount;
    @BindView(R.id.advices_add)
    Button advicesAdd;
    @BindView(R.id.comment_one)
    AppCompatRatingBar commentOne;
    @BindView(R.id.comment_order_no)
    TextView commentOrderNo;
    @BindView(R.id.comment_order_start)
    TextView commentOrderStart;
    @BindView(R.id.comment_order_price)
    TextView commentOrderPrice;
    @BindView(R.id.comment_order_end)
    TextView commentOrderEnd;
    @BindView(R.id.comment_order_car)
    TextView commentOrderCar;
    @BindView(R.id.comment_order_type)
    TextView commentOrderType;
    @BindView(R.id.comment_head)
    ImageView commentHead;
    @BindView(R.id.comment_name)
    TextView commentName;
    @BindView(R.id.comment_two)
    AppCompatRatingBar commentTwo;
    private int MaxNumber = 150;
    private Context context;
    private String orderid;
    private int DefaultScore = 5;
    private int DefaultScoreTwo = 5;
    private String DefaultContent = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_add_comment;
    }

    @Override
    public void initView() {
        context = this;
        centerTitle.setText("发表评价");
        righBarImage.setImageResource(R.mipmap.kefu);
        orderid = getIntent().getStringExtra("orderid");
        commentOrderNo.setText(getIntent().getStringExtra("orderno"));
        commentOrderStart.setText(getIntent().getStringExtra("orderstart"));
        commentOrderEnd.setText(getIntent().getStringExtra("orderend"));
        commentOrderPrice.setText(getIntent().getStringExtra("orderprice"));
        commentOrderCar.setText(getIntent().getStringExtra("ordercar"));
        commentOrderType.setText(getIntent().getStringExtra("ordertype"));
        commentName.setText(getIntent().getStringExtra("ordername"));
        Glide.with(context).load(getIntent().getStringExtra("orderphoto")).into(commentHead);

        editText.addTextChangedListener(new TextWatcher() {
            private CharSequence wordNum;//记录输入的字数
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length > MaxNumber) {
                    CharSequence charSequence = s.subSequence(0, MaxNumber);
                    editText.setText(charSequence);
                    editText.setSelection(MaxNumber);
                    showToast("只能输入" + MaxNumber + "个字");
                } else {
                    editcount.setText(String.valueOf(MaxNumber - length));
                }


            }
        });

        commentOne.setProgress(DefaultScore);
        commentOne.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                DefaultScore = Integer.valueOf((int) rating);
            }
        });

        commentTwo.setProgress(DefaultScoreTwo);
        commentTwo.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                DefaultScoreTwo = Integer.valueOf((int) rating);
            }
        });
    }

    @Override
    public void initData() {

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

    @OnClick({R.id.left_b_bar, R.id.righ_bar_image, R.id.advices_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_b_bar:
                finish();
                break;
            case R.id.righ_bar_image:
                Utils.callPhone(AddCommentActivity.this, "0412-8882888");

                break;
            case R.id.advices_add:
                DefaultContent = editText.getText().toString().trim();
                addComment(String.valueOf(DefaultScore+DefaultScoreTwo), DefaultContent);
                break;
        }
    }

    private void addComment(String score, String comment) {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderid);
        map.put("status", "5");
        map.put("driverScore", score);
        map.put("driverContent", comment);
        JSONObject jsonObject = new JSONObject(map);
        complaintinsert(jsonObject);
    }

    private void complaintinsert(JSONObject json) {
        OkGo.<String>post(Constants.Comment_Add)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseTestBean bean = GsonUtil.parseJsonWithGson(response.body(), BaseTestBean.class);
                        if (Integer.valueOf(bean.getCode()) == 0) {
                            finish();
                            startActivity(CommentAddSuccessActivity.class);
                        }else {
                            showToast(bean.getMsg());
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.getException().getMessage());
                    }
                });

    }
}

