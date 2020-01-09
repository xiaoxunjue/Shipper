package com.revenant.shipper.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.PersonData;

import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.adapter
 * @ClassName: MineBigItemAdapter
 * @Description: java类作用描述
 * @Author: revenant
 * @CreateDate: 2019-12-30 14:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-12-30 14:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DesignatePhoneItemAdapter extends BaseQuickAdapter<PersonData, BaseViewHolder> implements SectionIndexer {
    private List<PersonData> list;

    public DesignatePhoneItemAdapter(List<PersonData> list) {
        super(R.layout.item_publish_goods_designate);
        this.list = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonData item) {
        helper.setText(R.id.designate_nickname, item.getName());
        helper.setText(R.id.designate_telephone, item.getTel());
        TextView tvLetter = helper.getView(R.id.type_tv);
        RadioButton designateRadion = helper.getView(R.id.designate_radion);
        if (item.isIsselect()) {
            designateRadion.setChecked(true);
        } else {
            designateRadion.setChecked(false);
        }
        helper.addOnClickListener(R.id.designate_radion);
        int position = helper.getLayoutPosition();
        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            tvLetter.setVisibility(View.VISIBLE);
            String letterFirst = item.getLetters();
            if (!TextUtils.isEmpty(letterFirst)) {
                if (!isLetterDigitOrChinese(letterFirst)) {
                    letterFirst = "#";
                } else {
                    letterFirst = String.valueOf(letterFirst.toUpperCase().charAt(0));
                }
            }
            tvLetter.setText(letterFirst);
        } else {
            tvLetter.setVisibility(View.GONE);
        }
    }

    private boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getLetters();
            char firstChar = sortStr.charAt(0);
            if (Character.toUpperCase(firstChar) == Character.toUpperCase(sectionIndex)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getLetters().charAt(0);
    }

    public int getCount() {
        if (list != null) return list.size();
        return 0;
    }
}
