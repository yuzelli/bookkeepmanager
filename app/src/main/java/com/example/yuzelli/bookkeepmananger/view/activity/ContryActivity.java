	package com.example.yuzelli.bookkeepmananger.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.view.activity.contry.CharacterParser;
import com.example.yuzelli.bookkeepmananger.view.activity.contry.ClearEditText;
import com.example.yuzelli.bookkeepmananger.view.activity.contry.ContryAdapter;
import com.example.yuzelli.bookkeepmananger.view.activity.contry.ContryModel;
import com.example.yuzelli.bookkeepmananger.view.activity.contry.PinyinComparator;
import com.example.yuzelli.bookkeepmananger.view.activity.contry.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContryActivity extends BaseActivity {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private ContryAdapter adapter;
	private ClearEditText mClearEditText;

	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<ContryModel> SourceDateList;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;


	@Override
	protected int layoutInit() {
		return R.layout.activity_listview_contry_main;
	}

	@Override
	protected void binEvent() {
		findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		initView();
	}

	@Override
	protected void fillData() {

	}

	public void setData(String contry_code, String contry_en_name, String contry_zh_name){
		Intent intent = new Intent();
		intent.putExtra("contry_code",contry_code);
		intent.putExtra("contry_en_name",contry_en_name);
		intent.putExtra("contry_zh_name",contry_zh_name);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}

	public void initView() {
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				ContryModel cm = adapter.getItem(position);
				setData(cm.getContryCode(),cm.getEnglishName(), cm.getChineseName());
			}
		});

		SourceDateList = filledData(getResources().getStringArray(R.array.contry_date));

		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new ContryAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	private List<ContryModel> filledData(String[] date) {
		List<ContryModel> mSortList = new ArrayList<ContryModel>();

		for (int i = 0; i < date.length; i++) {
			ContryModel sortModel = new ContryModel();
			String allStr = date[i];
			String[] items = allStr.split("-");
			sortModel.setEnglishName(items[0]);
			sortModel.setChineseName(items[1]);
			sortModel.setContryCode(items[2]);
			//sortModel.setEnglishName(date[i]);
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<ContryModel> filterDateList = new ArrayList<ContryModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (ContryModel sortModel : SourceDateList) {
				String name = sortModel.getEnglishName();
				String nameChinese = sortModel.getChineseName();

				if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
					filterDateList.add(sortModel);
				}
				if (nameChinese.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(nameChinese).startsWith(filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

}
