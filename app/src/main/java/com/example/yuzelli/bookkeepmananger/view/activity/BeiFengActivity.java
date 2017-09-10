package com.example.yuzelli.bookkeepmananger.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.bean.BookKeepBean;
import com.example.yuzelli.bookkeepmananger.bean.UserBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.https.OkHttpClientManager;
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

import static com.example.yuzelli.bookkeepmananger.app.MyApplication.context;

public class BeiFengActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int layoutInit() {
        return R.layout.activity_bei_feng;
    }

    @Override
    protected void binEvent() {

    }

    @Override
    protected void fillData() {

    }

    @OnClick({R.id.img_back, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.button:
                doBeifengshuju();
                break;
        }
    }

    private void doBeifengshuju() {

        OkHttpClientManager manager = OkHttpClientManager.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("type", "beifeng");
        ArrayList<BookKeepBean> books = (ArrayList<BookKeepBean>) SharePreferencesUtil.readObject(BeiFengActivity.this, ConstantsUtils.Bookkeep_INFO);
        JSONArray arr = new JSONArray();
        for (BookKeepBean b : books) {
            JSONObject json = new JSONObject();
            try {
                json.put("year",b.getYear());
                json.put("month",b.getMonth());
                json.put("day",b.getDay());
                json.put("hour",b.getHour());
                json.put("min",b.getMin());
                json.put("week",b.getWeek());
                json.put("type_id",b.getType_id());
                json.put("isZhiCu",b.getIsZhiCu());
                json.put("comment",b.getComment());
                json.put("money",b.getMoney());
                json.put("time",b.getTime());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            arr.put(json);
        }
        map.put("data", arr.toString());

        String url = OkHttpClientManager.attachHttpGetParams(ConstantsUtils.LOCTION_ADDRESS + ConstantsUtils.CopyService, map);
        manager.getAsync(url, new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(context, "请求失败！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                JSONObject object = new JSONObject(result);
                String flag = object.getString("error");
                if (flag.equals("ok")) {

                } else {

                }
            }
        });

    }
}
