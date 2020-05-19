package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.BaseBean.BaseTestBean;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.EventBusUtil;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Utils;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Advice_Add_Success;


public class AddAdvicesActivity extends BaseActivity {
    private TextView centerview;
    private ImageView rightiImageView;
    private ImageView leftImageView;
    private Context context;
    private EditText editText;
    private TextView editcount;
    private Button buttonbtn;
    private int MaxNumber = 150;
    private int MinNumber = 10;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_add_advices;
    }

    @Override
    public void initView() {
        context = this;

        centerview = findViewById(R.id.center_title);
        rightiImageView = findViewById(R.id.righ_bar_image);
        leftImageView = findViewById(R.id.left_b_bar);
        editText = findViewById(R.id.editcount);
        editcount = findViewById(R.id.edittextcount);
        buttonbtn = findViewById(R.id.advices_add);
        centerview.setText("意见反馈");
        rightiImageView.setImageResource(R.mipmap.kefu);
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
                    editcount.setText(String.valueOf(MaxNumber-length));
                }


            }
        });
        leftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rightiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.callPhone(AddAdvicesActivity.this, "0412-8882888");
            }
        });

        buttonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = editText.getText().length();
                if (length < MinNumber) {
                    ToastUtils.showShort("输入内容过少");
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("accountId", String.valueOf(SPUtils.getAccounId(context)));
                    map.put("content", editText.getText().toString());
                    JSONObject jsonObject = new JSONObject(map);
                    complaintinsert(jsonObject);
                }

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

    private void complaintinsert(JSONObject json) {
        OkGo.<String>post(Constants.Complaint_Insert)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseTestBean bean = GsonUtil.parseJsonWithGson(response.body(), BaseTestBean.class);
                        if (Integer.valueOf(bean.getCode()) == 0) {
                            finish();
                            MessageEvent event20 = new MessageEvent(Advice_Add_Success);
                            EventBusUtil.sendStickyEvent(event20);
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
