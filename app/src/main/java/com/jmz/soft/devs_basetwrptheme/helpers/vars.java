package com.jmz.soft.devs_basetwrptheme.helpers;

import com.jmz.soft.devs_basetwrptheme.objects.Theme;

/**
 * Created by mazwoz on 21-Nov-16.
 */

public class vars {

    public static final String path = "/sdcard/TWRP/theme/";
    public static final String name = "ui.zip";
    public static final String info = "ui.xml";

    public static Theme currentTheme;

    public static void SetTheme(Theme theme) {
        currentTheme = theme;
    }
}
