package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.bean.BookKeepBean;
import com.example.yuzelli.bookkeepmananger.bean.TypeBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.utils.DateTimePickDialogUtil;
import com.example.yuzelli.bookkeepmananger.utils.DateUtils;
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaveAccountActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_type)
    ImageView imgType;
    @BindView(R.id.tv_type_title)
    TextView tvTypeTitle;
    @BindView(R.id.tv_zhicu)
    TextView tvZhicu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_comment)
    EditText etComment;
    private TypeBean typeBean;
    private boolean isZhifu;

    @Override
    protected int layoutInit() {
        return R.layout.activity_save_account;
    }

    @Override
    protected void binEvent() {
        Intent intent = getIntent();
        typeBean = (TypeBean) intent.getSerializableExtra("type");
        isZhifu = intent.getBooleanExtra("iszhichu", true);
        DateUtils dateUtils = new DateUtils();
        tvTime.setText(dateUtils.getSummaryEndDate());
        tvTitle.setText("添加记录");
        tvTypeTitle.setText(typeBean.getName());
        imgType.setImageResource(typeBean.getTypeRESID());
        if (isZhifu){
            tvZhicu.setText("支出");
        }else {
            tvZhicu.setText("收入");
        }
    }

    @Override
    protected void fillData() {

    }

    public static void startAction(Context context, TypeBean type, boolean iszhichu) {
        Intent intent = new Intent(context, SaveAccountActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("iszhichu", iszhichu);
        context.startActivity(intent);
    }

    @OnClick({R.id.img_back, R.id.rl_time, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_time:
                showDataPicker(tvTime);
                break;
            case R.id.btn_save:
                if (tvTime.equals("")){
                    showToast("请填写日期");
                    return;
                }
                saveBookKeep();
                break;
        }
    }

    private void saveBookKeep() {
        ArrayList<BookKeepBean> list;
        list = (ArrayList<BookKeepBean>) SharePreferencesUtil.readObject(this, ConstantsUtils.Bookkeep_INFO);
        if (list == null){
            list = new ArrayList<>();
        }
        BookKeepBean book = new BookKeepBean();
        book.setId(0);
        if (isZhifu){
            book.setIsZhiCu(1);
        }else {
            book.setIsZhiCu(0);
        }
        book.setComment(etComment.getText().toString().trim());
        String time = tvTime.getText().toString().trim();
        String year = time.substring(0,4);
        String month = time.substring(5,7);
        String day = time.substring(8,10);
        String hour = time.substring(11,13);
        String min = time.substring(14,16);
        book.setYear(year);
        book.setMonth(month);
        book.setDay(day);
        book.setHour(hour);
        book.setMin(min);
        book.setType_id(typeBean.getTypeID());
        list.add(book);
        SharePreferencesUtil.saveObject(this,ConstantsUtils.Bookkeep_INFO,list);
        finish();
    }

    /**
     * 时间选择器
     */
    private void showDataPicker(final TextView tv_time) {
        DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
               this, tv_time.getText().toString().trim());
        dateTimePicKDialog.dateTimePicKDialog(tv_time);
    }
}
