package com.revenant.shipper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.ShuCheMemberAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.PersonData;
import com.revenant.shipper.bean.ShipperBean.ShuCheZhiPaiBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.ui.activity.MineShuCheActivity.ShuCheRESULT_CODE;
import static com.revenant.shipper.utils.Constants.Delete_ShuChe;
import static com.revenant.shipper.utils.Constants.Look_ShuChe_Group_Member;
import static com.revenant.shipper.utils.Constants.Save_ShuChe;
import static com.revenant.shipper.utils.Constants.Update_ShuChe;

public class GroupActivity extends BaseActivity {
    @BindView(R.id.group_name)
    EditText groupName;
    @BindView(R.id.add_number)
    ImageView addNumber;
    @BindView(R.id.group_recyclerview)
    RecyclerView groupRecyclerview;
    @BindView(R.id.show_delete_group)
    Button showDeleteGroup;
    private ShuCheMemberAdapter mShuCheMemberAdapter;
    private final static int ShuCheREQUEST_CODE = 101;
    private String groupType;
    private String groupid;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_group;
    }

    @Override
    public void initView() {
        setcenterTitle("群组");
        showrighttext(true);
        Bundle bundleExtra = getIntent().getExtras();
        groupType = bundleExtra.getString("group_type");
        groupid = bundleExtra.getString("group_id");

        if (groupType.equals("0")) {
            setrightTitle("保存");
        } else {
            setrightTitle("修改");
            get_shuche_zhipai(groupid);
            show_delete_group();

        }


        mShuCheMemberAdapter = new ShuCheMemberAdapter();
        groupRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        groupRecyclerview.setAdapter(mShuCheMemberAdapter);
        mShuCheMemberAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.member_delete:
                        mShuCheMemberAdapter.remove(position);
//                        show_delete_group();
                        break;
                }
            }
        });
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void rightbarclick() {
        super.rightbarclick();
        if (mShuCheMemberAdapter.getData().size() < 1) {
            showToast("请选择熟车");
        } else {
            String member = "";
            for (int i = 0; i < mShuCheMemberAdapter.getData().size(); i++) {
                if (i == 0) {
                    member = mShuCheMemberAdapter.getData().get(i).getId();
                } else {
                    member = member + "," + mShuCheMemberAdapter.getData().get(i).getId();
                }
            }
            if (groupType.equals("0")) {

                save_shu_che_group(groupName.getText().toString(), mShuCheMemberAdapter.getData().size(), member);
            } else {
                update_shu_che_group(groupid, groupName.getText().toString(), mShuCheMemberAdapter.getData().size(), member);
            }
        }

    }

    private void save_shu_che_group(String name, int num, String ids) {
        OkGo.<String>post(Save_ShuChe)
                .params("accountId", SPUtils.getAccounId(this))
                .params("name", name)
                .params("num", num)
                .params("member", ids)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShuCheZhiPaiBean groupBean = GsonUtil.parseJsonWithGson(response.body(), ShuCheZhiPaiBean.class);
                        if (groupBean.getCode().equals("0")) {
                            showToast(groupBean.getMsg());
                            finish();
                        }

                    }
                });
    }

    private void update_shu_che_group(String groupid, String name, int num, String ids) {
        OkGo.<String>post(Update_ShuChe)
                .params("accountId", SPUtils.getAccounId(this))
                .params("name", name)
                .params("id", groupid)
                .params("num", num)
                .params("member", ids)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShuCheZhiPaiBean groupBean = GsonUtil.parseJsonWithGson(response.body(), ShuCheZhiPaiBean.class);
                        if (groupBean.getCode().equals("0")) {
                            if (groupBean.getCode().equals("0")) {
                                showToast(groupBean.getMsg());
                                finish();
                            }
                        }

                    }
                });
    }

    private void delete_shu_che_group(String id) {
        OkGo.<String>post(Delete_ShuChe)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShuCheZhiPaiBean groupBean = GsonUtil.parseJsonWithGson(response.body(), ShuCheZhiPaiBean.class);
                        if (groupBean.getCode().equals("0")) {
                            showToast(groupBean.getMsg());
                            finish();
                        }

                    }
                });
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

    @OnClick({R.id.add_number, R.id.show_delete_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_number:
                Intent intent = new Intent(this, MineShuCheActivity.class);
                startActivityForResult(intent, ShuCheREQUEST_CODE);
                break;
            case R.id.show_delete_group:
                delete_shu_che_group(groupid);
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        show_delete_group();
    }

    private void show_delete_group() {
        showDeleteGroup.setVisibility(View.VISIBLE);
    }


    private void get_shuche_zhipai(String groupid) {


        OkGo.<String>get(Look_ShuChe_Group_Member)

                .params("id", groupid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        ShuCheZhiPaiBean groupBean = GsonUtil.parseJsonWithGson(response.body(), ShuCheZhiPaiBean.class);

                        if (groupBean.getCode().equals("0")) {
                            if (groupBean.getData() != null) {

                                groupName.setText(groupBean.getData().getName());

                                List<PersonData> personList = new ArrayList<>();
                                for (int i = 0; i < groupBean.getData().getList().size(); i++) {
                                    PersonData personData = new PersonData();
                                    personData.setPhotourl(groupBean.getData().getList().get(i).getPhoto());
                                    personData.setTel(groupBean.getData().getList().get(i).getMobile());
                                    personData.setName(groupBean.getData().getList().get(i).getUsername());
                                    personData.setId(String.valueOf(groupBean.getData().getList().get(i).getId()));
                                    personList.add(personData);

                                }
                                mShuCheMemberAdapter.setNewData(personList);

                            }
                        }


                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ShuCheREQUEST_CODE) {

            if (resultCode == ShuCheRESULT_CODE) {
                List<PersonData> personDataList = (List<PersonData>) data.getSerializableExtra("ShuCheList");
                mShuCheMemberAdapter.setNewData(personDataList);
            }
        }
    }


}