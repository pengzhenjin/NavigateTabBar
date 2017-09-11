package com.pzj.navigatetabbar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.pzj.navigatetabbar.R;
import com.pzj.navigatetabbar.fragment.CityFragment;
import com.pzj.navigatetabbar.fragment.HomeFragment;
import com.pzj.navigatetabbar.fragment.MessageFragment;
import com.pzj.navigatetabbar.fragment.PersonFragment;
import com.pzj.navigatetabbar.widgets.NavigateTabBar;
import com.pzj.navigatetabbar.widgets.PopupMenuView;

/**
 * MainActivity
 *
 * @author PengZhenjin
 * @date 2017-9-11
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG_PAGE_HOME    = "首页";
    private static final String TAG_PAGE_CITY    = "同城";
    private static final String TAG_PAGE_MORE    = "更多";
    private static final String TAG_PAGE_MESSAGE = "消息";
    private static final String TAG_PAGE_PERSON  = "我的";

    private NavigateTabBar mNavigateTabBar;
    private ImageView      mTabMoreIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        this.mNavigateTabBar = (NavigateTabBar) findViewById(R.id.main_tabbar);
        this.mTabMoreIv = (ImageView) findViewById(R.id.tab_more_iv);

        this.mNavigateTabBar.onRestoreInstanceState(savedInstanceState);

        this.mNavigateTabBar.addTab(HomeFragment.class, new NavigateTabBar.TabParam(R.mipmap.navigate_tab_home, R.mipmap.navigate_tab_home_selected, TAG_PAGE_HOME));
        this.mNavigateTabBar.addTab(CityFragment.class, new NavigateTabBar.TabParam(R.mipmap.navigate_tab_city, R.mipmap.navigate_tab_city_selected, TAG_PAGE_CITY));
        this.mNavigateTabBar.addTab(null, new NavigateTabBar.TabParam(0, 0, TAG_PAGE_MORE));
        this.mNavigateTabBar.addTab(MessageFragment.class, new NavigateTabBar.TabParam(R.mipmap.navigate_tab_message, R.mipmap.navigate_tab_message_selected, TAG_PAGE_MESSAGE));
        this.mNavigateTabBar.addTab(PersonFragment.class, new NavigateTabBar.TabParam(R.mipmap.navigate_tab_person, R.mipmap.navigate_tab_person_selected, TAG_PAGE_PERSON));

        this.mTabMoreIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "更多", Toast.LENGTH_LONG).show();
                PopupMenuView.getInstance().show(MainActivity.this, mTabMoreIv);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
        if (PopupMenuView.getInstance().isShowing()) {
            PopupMenuView.getInstance().closePopupWindowAction();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mNavigateTabBar.onSaveInstanceState(outState);
    }
}
