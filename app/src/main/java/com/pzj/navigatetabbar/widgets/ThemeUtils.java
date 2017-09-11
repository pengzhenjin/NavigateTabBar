package com.pzj.navigatetabbar.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import com.pzj.navigatetabbar.R;

/**
 * ThemeUtils
 *
 * @author PengZhenjin
 * @date 2017-9-11
 */
public class ThemeUtils {

    private static final int[] APPCOMPAT_CHECK_ATTRS = { R.attr.colorPrimary };

    public static void checkAppCompatTheme(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        boolean failed = !a.hasValue(0);
        a.recycle();
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme " + "(or descendant) with the design library.");
        }
    }
}
