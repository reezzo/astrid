package com.todoroo.astrid.service;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.view.WindowManager;

import com.timsu.astrid.R;
import com.todoroo.andlib.service.ContextManager;
import com.todoroo.andlib.utility.AndroidUtilities;
import com.todoroo.andlib.utility.Preferences;

@SuppressWarnings("nls")
public class ThemeService {

    public static final String THEME_WHITE = "white";
    public static final String THEME_WHITE_RED = "white-red";
    public static final String THEME_BLACK = "black";
    public static final String THEME_TRANSPARENT = "transparent";
    public static final String THEME_TRANSPARENT_WHITE = "transparent-white";

    public static final int FLAG_FORCE_DARK = 1;
    public static final int FLAG_FORCE_LIGHT = 2;
    public static final int FLAG_INVERT = 3;

    private static int currentTheme;

    public static void applyTheme(Activity activity) {
        currentTheme = getTheme();
        activity.setTheme(currentTheme);

        activity.getWindow().setFormat(PixelFormat.RGBA_8888);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
    }

    public static int getTheme() {
        String preference = Preferences.getStringValue(R.string.p_theme);
        if(THEME_BLACK.equals(preference))
            return R.style.Theme;
        else if(THEME_TRANSPARENT.equals(preference))
            return R.style.Theme_Transparent;
        else if(THEME_TRANSPARENT_WHITE.equals(preference))
            return R.style.Theme_TransparentWhite;
        else if (THEME_WHITE_RED.equals(preference))
            return R.style.Theme_White;
        else
            return R.style.Theme_White_Blue;
    }

    public static int getEditDialogTheme() {
        int themeSetting = ThemeService.getTheme();
        int theme;
        if (themeSetting == R.style.Theme || themeSetting == R.style.Theme_Transparent) {
            theme = R.style.TEA_Dialog;
        } else {
            theme = R.style.TEA_Dialog_White;
        }
        return theme;
    }

    public static int getDialogTheme() {
        int themeSetting = ThemeService.getTheme();
        int theme;
        if (themeSetting == R.style.Theme || themeSetting == R.style.Theme_Transparent) {
            theme = R.style.Theme_Dialog;
        } else {
            theme = R.style.Theme_Dialog_White;
        }
        return theme;
    }

    public static String getDialogTextColor() {
        return (AndroidUtilities.getSdkVersion() >= 11 ? "black" : "white");
    }

    public static int getDrawable(int lightDrawable) {
        return getDrawable(lightDrawable, 0);
    }

    public static int getDrawable(int lightDrawable, int alter) {
        boolean darkTheme = currentTheme == R.style.Theme || currentTheme == R.style.Theme_Transparent;
        switch(alter) {
        case FLAG_FORCE_DARK:
            darkTheme = true;
            break;
        case FLAG_FORCE_LIGHT:
            darkTheme = false;
            break;
        case FLAG_INVERT:
            darkTheme = !darkTheme;
            break;
        default:
            break;
        }

        if(!darkTheme)
            return lightDrawable;

        switch(lightDrawable) {
        case R.drawable.icn_menu_refresh:
            return R.drawable.icn_menu_refresh_dark;
        case R.drawable.icn_menu_filters:
            return R.drawable.icn_menu_filters_dark;
        case R.drawable.icn_menu_sort_by_size:
            return R.drawable.icn_menu_sort_by_size_dark;
        case R.drawable.icn_menu_search:
            return R.drawable.icn_menu_search_dark;
        case R.drawable.icn_menu_lists:
            return R.drawable.icn_menu_lists_dark;
        case R.drawable.icn_menu_plugins:
            return R.drawable.icn_menu_plugins_dark;
        case R.drawable.icn_menu_settings:
            return R.drawable.icn_menu_settings_dark;
        case R.drawable.icn_menu_support:
            return R.drawable.icn_menu_support_dark;
        case R.drawable.icn_menu_tutorial:
            return R.drawable.icn_menu_tutorial_dark;
        case R.drawable.filter_assigned:
            return R.drawable.filter_assigned_dark;
        case R.drawable.filter_calendar:
            return R.drawable.filter_calendar_dark;
        case R.drawable.filter_inbox:
            return R.drawable.filter_inbox_dark;
        case R.drawable.filter_pencil:
            return R.drawable.filter_pencil_dark;
        case R.drawable.filter_sliders:
            return R.drawable.filter_sliders_dark;
        case R.drawable.gl_lists:
            return R.drawable.gl_lists_dark;
        }

        throw new RuntimeException("No theme drawable found for " +
                ContextManager.getResources().getResourceName(lightDrawable));
    }

    public static void forceTheme(int theme) {
        currentTheme = theme;
    }

}
