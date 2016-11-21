package com.jmz.soft.devs_basetwrptheme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmz.soft.devs_basetwrptheme.helpers.vars;
import com.jmz.soft.devs_basetwrptheme.objects.Theme;

public class ThemeView extends Activity {

    private ImageView imageView;
    private TextView currentName, currentAuthor, currentVersion, currentDescription;
    private Theme currentTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_view);

        currentTheme = vars.currentTheme;

        imageView = (ImageView) findViewById(R.id.currentPreview);
        currentName = (TextView) findViewById(R.id.currentTitle);
        currentAuthor = (TextView) findViewById(R.id.currentAuthor);
        currentVersion = (TextView) findViewById(R.id.currentVersion);
        currentDescription = (TextView) findViewById(R.id.currentDescription);

        if (currentTheme != null) {
            if (currentTheme.getThemePreview() != null) {
                imageView = (ImageView) findViewById(R.id.currentPreview);
                imageView.setImageBitmap(currentTheme.getThemePreview());
            } else {
                imageView = (ImageView) findViewById(R.id.currentPreview);
                imageView.setImageResource(R.drawable.preview);
            }

            currentName.setText(currentTheme.getCurrentTheme());
            currentAuthor.setText(currentTheme.getCurrentAuthor());
            currentDescription.setText(currentTheme.getThemeDescription());
            currentVersion.setText(currentTheme.getThemeVersion());
        }
    }
}
