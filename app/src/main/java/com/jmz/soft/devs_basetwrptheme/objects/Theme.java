package com.jmz.soft.devs_basetwrptheme.objects;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mazwoz on 21-Nov-16.
 */

public class Theme{

    private String currentTheme;
    private String currentAuthor;
    private String themeVersion;
    private String themeDescription;
    private Bitmap themePreview;

    public String getCurrentTheme() {
        return currentTheme;
    }

    public String getCurrentAuthor() {
        return currentAuthor;
    }

    public String getThemeVersion() {
        return themeVersion;
    }

    public String getThemeDescription() {
        return themeDescription;
    }

    public Bitmap getThemePreview() {
        return themePreview;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
    }

    public void setThemeAuthor(String currentAuthor) {
        this.currentAuthor = currentAuthor;
    }

    public void setThemeVersion(String themeVersion) {
        this.themeVersion = themeVersion;
    }

    public void setThemeDescription(String themeDescription) {
        this.themeDescription = themeDescription;
    }

    public void setThemePreview(Bitmap themePreview) {
        this.themePreview = themePreview;
    }
}
