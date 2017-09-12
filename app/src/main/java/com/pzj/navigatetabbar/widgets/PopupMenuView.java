package com.pzj.navigatetabbar.widgets;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.pzj.navigatetabbar.R;

/**
 * PopupMenuView
 *
 * @author PengZhenjin
 * @date 2017-9-11
 */
public class PopupMenuView {

    private static final String TAG = "PopupMenuView";

    public static PopupMenuView getInstance() {
        return PopupMenuViewHolder.INSTANCE;
    }

    private static class PopupMenuViewHolder {
        public static PopupMenuView INSTANCE = new PopupMenuView();
    }

    private View        mRootVew;
    private PopupWindow mPopupWindow;

    private RelativeLayout mCloseLayout;
    private ImageView      mCloseIv;
    private LinearLayout   mTest1Layout, mTest2Layout, mTest3Layout, mTest4Layout, mTest5Layout, mTest6Layout, mTest7Layout, mTest8Layout;

    /**
     * 动画执行的 属性值数组
     */
    private float mAnimatorProperty[] = null;

    /**
     * 第一排图 距离屏幕底部的距离
     */
    private int mTop = 0;

    /**
     * 第二排图 距离屏幕底部的距离
     */
    private int mBottom = 0;

    /**
     * 创建PopupWindow
     *
     * @param context
     */
    private void createView(final Context context) {
        this.mRootVew = LayoutInflater.from(context).inflate(R.layout.view_popup_menu, null);
        this.mPopupWindow = new PopupWindow(this.mRootVew, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        this.mPopupWindow.setFocusable(false); // 设置为失去焦点 方便监听返回键的监听
        //mPopupWindow.setClippingEnabled(false); // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        this.mPopupWindow.setOutsideTouchable(false);

        if (this.mAnimatorProperty == null) {
            this.mTop = dip2px(context, 310);
            this.mBottom = dip2px(context, 210);
            this.mAnimatorProperty = new float[] { this.mBottom, 60, -30, -20 - 10, 0 };
        }

        this.initLayout(context);
    }

    /**
     * dp转化为px
     *
     * @param context  context
     * @param dipValue dp value
     *
     * @return 转换之后的px值
     */
    private static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 初始化 view
     */
    private void initLayout(Context context) {
        this.mCloseLayout = (RelativeLayout) this.mRootVew.findViewById(R.id.close_layout);
        this.mCloseIv = (ImageView) this.mRootVew.findViewById(R.id.close_iv);
        this.mTest1Layout = (LinearLayout) this.mRootVew.findViewById(R.id.test1_layout);
        this.mTest2Layout = (LinearLayout) this.mRootVew.findViewById(R.id.test2_layout);
        this.mTest3Layout = (LinearLayout) this.mRootVew.findViewById(R.id.test3_layout);
        this.mTest4Layout = (LinearLayout) this.mRootVew.findViewById(R.id.test4_layout);
        this.mTest5Layout = (LinearLayout) this.mRootVew.findViewById(R.id.test5_layout);
        this.mTest6Layout = (LinearLayout) this.mRootVew.findViewById(R.id.test6_layout);
        this.mTest7Layout = (LinearLayout) this.mRootVew.findViewById(R.id.test7_layout);
        this.mTest8Layout = (LinearLayout) this.mRootVew.findViewById(R.id.test8_layout);

        this.mCloseLayout.setOnClickListener(new ItemClick(0, context));

        this.mTest1Layout.setOnClickListener(new ItemClick(1, context));
        this.mTest2Layout.setOnClickListener(new ItemClick(2, context));
        this.mTest3Layout.setOnClickListener(new ItemClick(3, context));
        this.mTest4Layout.setOnClickListener(new ItemClick(4, context));
        this.mTest5Layout.setOnClickListener(new ItemClick(5, context));
        this.mTest6Layout.setOnClickListener(new ItemClick(6, context));
        this.mTest7Layout.setOnClickListener(new ItemClick(7, context));
        this.mTest8Layout.setOnClickListener(new ItemClick(8, context));
    }

    /**
     * Item点击事件
     */
    private class ItemClick implements View.OnClickListener {

        private int     index;
        private Context context;

        public ItemClick(int index, Context context) {
            this.index = index;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            if (index == 0) {   // 关闭按钮
                closePopupWindowAction();
            }
            else {
                Toast.makeText(context, "index：" + index, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 打开popupWindow执行的动画
     */
    private void openPopupWindowAction() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mCloseIv, "rotation", 0f, 135f);
        objectAnimator.setDuration(200);
        objectAnimator.start();

        startAnimation(this.mTest1Layout, 500, this.mAnimatorProperty);
        startAnimation(this.mTest2Layout, 430, this.mAnimatorProperty);
        startAnimation(this.mTest3Layout, 430, this.mAnimatorProperty);
        startAnimation(this.mTest4Layout, 500, this.mAnimatorProperty);

        startAnimation(this.mTest5Layout, 500, this.mAnimatorProperty);
        startAnimation(this.mTest6Layout, 430, this.mAnimatorProperty);
        startAnimation(this.mTest7Layout, 430, this.mAnimatorProperty);
        startAnimation(this.mTest8Layout, 500, this.mAnimatorProperty);
    }

    /**
     * 关闭popupWindow执行的动画
     */
    public void closePopupWindowAction() {
        if (this.mCloseIv != null && this.mCloseLayout != null) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this.mCloseIv, "rotation", 135f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.start();

            closeAnimation(this.mTest1Layout, 300, this.mTop);
            closeAnimation(this.mTest2Layout, 200, this.mTop);
            closeAnimation(this.mTest3Layout, 200, this.mTop);
            closeAnimation(this.mTest4Layout, 300, this.mTop);
            closeAnimation(this.mTest5Layout, 300, this.mBottom);
            closeAnimation(this.mTest6Layout, 200, this.mBottom);
            closeAnimation(this.mTest7Layout, 200, this.mBottom);
            closeAnimation(this.mTest8Layout, 300, this.mBottom);

            this.mCloseLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    close();
                }
            }, 300);
        }
    }

    /**
     * 显示PopupWindow
     *
     * @param context context
     * @param parent  parent
     */
    public void show(Context context, View parent) {
        createView(context);
        if (this.mPopupWindow != null && !this.mPopupWindow.isShowing()) {
            this.mPopupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
            openPopupWindowAction();
        }
    }

    /**
     * 关闭popupWindow
     */

    public void close() {
        if (this.mPopupWindow != null && this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
            this.mPopupWindow = null;
        }
    }

    /**
     * PopupWindow是否显示了
     *
     * @return
     */
    public boolean isShowing() {
        return this.mPopupWindow != null && this.mPopupWindow.isShowing();
    }

    /**
     * 启动PopupWindow动画
     *
     * @param view     view
     * @param duration 执行时长
     * @param distance 执行的轨迹数组
     */
    private void startAnimation(View view, int duration, float[] distance) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
        anim.setDuration(duration);
        anim.start();
    }

    /**
     * 关闭PopupWindow动画
     *
     * @param view     view
     * @param duration 动画执行时长
     * @param next     平移量
     */
    private void closeAnimation(View view, int duration, int next) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 0f, next);
        anim.setDuration(duration);
        anim.start();
    }
}
