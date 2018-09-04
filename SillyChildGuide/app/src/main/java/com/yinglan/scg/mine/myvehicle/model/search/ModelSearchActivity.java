package com.yinglan.scg.mine.myvehicle.model.search;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.entity.BaseResult;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.myvehicle.model.search.RecentSearchTagAdapter;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.RecentSearchBean;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.RecentSearchBean.DataBean;
import com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea.search.dialog.ClearSearchDialog;
import com.yinglan.scg.utils.SoftKeyboardUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 车型搜索
 */
public class ModelSearchActivity extends BaseActivity implements TagFlowLayout.OnTagClickListener {

    /**
     * 搜索
     */
    @BindView(id = R.id.et_search)
    private EditText et_search;

    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;

    /**
     * 最近搜索
     */
    @BindView(id = R.id.ll_recentSearch)
    private LinearLayout ll_recentSearch;

    @BindView(id = R.id.img_delete, click = true)
    private ImageView img_delete;

    @BindView(id = R.id.tfl_recentSearch)
    private TagFlowLayout tfl_recentSearch;

    private List<DataBean> recentSearchList = null;

    private RecentSearchTagAdapter recentSearchTagAdapter = null;

    private ClearSearchDialog clearSearchDialog = null;

    private int type = 0;//1.接机2.送机。3包车4.私人定制5.线路


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_areasearch);
    }

    @Override
    public void initData() {
        super.initData();
        recentSearchList = new ArrayList<DataBean>();
        type = getIntent().getIntExtra("type", 0);
        recentSearchTagAdapter = new RecentSearchTagAdapter(this, recentSearchList);
        initClearSearchDialog();
    }

    /**
     * 弹框
     */
    public void initClearSearchDialog() {
        clearSearchDialog = new ClearSearchDialog(this, getString(R.string.clearSearch)) {
            @Override
            public void deleteCollectionDo(int addressId) {
                if (type == 1) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchAirportPickupHistory", null);
                } else if (type == 2) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchAirportDropOffHistory", null);
                } else if (type == 3) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchByTheDayCharterHistory", null);
                } else if (type == 4) {

                } else if (type == 5) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchBoutiqueLineHistory", null);
                }
                ll_recentSearch.setVisibility(View.GONE);
                tfl_recentSearch.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tfl_recentSearch.setAdapter(recentSearchTagAdapter);
        tfl_recentSearch.setOnTagClickListener(this);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (StringUtils.isEmpty(textView.getText().toString().trim())) {
                        ViewInject.toast(getString(R.string.enterSearchContent));
                        handled = true;
                        return handled;
                    }
                    SoftKeyboardUtils.packUpKeyboard(aty);
                    saveRecentSearchHistory(textView.getText().toString().trim(), type);
                    Intent beautyCareIntent = new Intent();
                    if (getIntent().getIntExtra("tag", 0) == 1) {
                        beautyCareIntent.putExtra("name", textView.getText().toString().trim());
                        setResult(RESULT_OK, beautyCareIntent);
                    } else {
                        beautyCareIntent.setClass(aty, ModelSearchListActivity.class);
                        beautyCareIntent.putExtra("name", textView.getText().toString().trim());
                        beautyCareIntent.putExtra("type", type);
                        showActivity(aty, beautyCareIntent);
                    }
                    finish();
                    handled = true;
                }
                return handled;
            }
        });
        readRecentSearchHistory(type);
    }


    /**
     * 保存历史
     */
    private void saveRecentSearchHistory(String name, int type) {
        if (recentSearchList.size() > 0) {
            for (int i = 0; i < recentSearchList.size(); i++) {
                if (recentSearchList.get(i).getName().equals(name)) {
                    recentSearchList.remove(i);
                }
            }
        }
        DataBean dataBean = new DataBean();
        dataBean.setName(name);
        dataBean.setType(type);
        recentSearchList.add(dataBean);
        BaseResult<List<DataBean>> baseResult = new BaseResult<>();
        baseResult.setMessage("");
        baseResult.setResult(1);
        Collections.reverse(recentSearchList);
        baseResult.setData(recentSearchList);
        if (type == 1) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchAirportPickupHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
        } else if (type == 2) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchAirportDropOffHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
        } else if (type == 3) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchByTheDayCharterHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
        } else if (type == 4) {

        } else if (type == 5) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "recentSearchBoutiqueLineHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
        }
    }

    /**
     * 读取历史
     */
    private void readRecentSearchHistory(int type) {
        String recentSearch = "";
        if (type == 1) {
            recentSearch = PreferenceHelper.readString(aty, StringConstants.FILENAME, "recentSearchAirportPickupHistory", "");
        } else if (type == 2) {
            recentSearch = PreferenceHelper.readString(aty, StringConstants.FILENAME, "recentSearchAirportDropOffHistory", "");
        } else if (type == 3) {
            recentSearch = PreferenceHelper.readString(aty, StringConstants.FILENAME, "recentSearchByTheDayCharterHistory", "");
        } else if (type == 4) {

        } else if (type == 5) {
            recentSearch = PreferenceHelper.readString(aty, StringConstants.FILENAME, "recentSearchBoutiqueLineHistory", "");
        }
        if (StringUtils.isEmpty(recentSearch)) {
            ll_recentSearch.setVisibility(View.GONE);
            tfl_recentSearch.setVisibility(View.GONE);
            return;
        }
        ll_recentSearch.setVisibility(View.VISIBLE);
        tfl_recentSearch.setVisibility(View.VISIBLE);
        RecentSearchBean recentSearchBean = (RecentSearchBean) JsonUtil.json2Obj(recentSearch, RecentSearchBean.class);
        if (recentSearchBean == null || recentSearchBean.getData() == null || recentSearchBean.getData().size() <= 0) {
            ll_recentSearch.setVisibility(View.GONE);
            tfl_recentSearch.setVisibility(View.GONE);
            return;
        }
        recentSearchTagAdapter.clear();
        recentSearchTagAdapter.setData(recentSearchBean.getData());
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.img_delete:
                if (clearSearchDialog == null) {
                    initClearSearchDialog();
                }
                if (clearSearchDialog != null && !clearSearchDialog.isShowing()) {
                    clearSearchDialog.show();
                }
                break;
        }
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        if (parent.getId() == R.id.tfl_recentSearch) {
            Intent beautyCareIntent = new Intent();
            if (getIntent().getIntExtra("tag", 0) == 1) {
                beautyCareIntent.putExtra("name", recentSearchTagAdapter.getItem(position).getName());
                beautyCareIntent.putExtra("type", recentSearchTagAdapter.getItem(position).getType());
                setResult(RESULT_OK, beautyCareIntent);
            } else {
                beautyCareIntent.setClass(aty, ModelSearchListActivity.class);
                beautyCareIntent.putExtra("name", recentSearchTagAdapter.getItem(position).getName());
                beautyCareIntent.putExtra("type", recentSearchTagAdapter.getItem(position).getType());
                showActivity(aty, beautyCareIntent);
            }
            finish();
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (clearSearchDialog != null) {
            clearSearchDialog.cancel();
        }
        clearSearchDialog = null;
        recentSearchTagAdapter.clear();
        recentSearchTagAdapter = null;
    }

}
