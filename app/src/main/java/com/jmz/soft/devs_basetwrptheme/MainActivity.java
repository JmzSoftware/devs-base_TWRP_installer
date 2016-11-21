package com.jmz.soft.devs_basetwrptheme;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jmz.soft.devs_basetwrptheme.helpers.StorageHelper;
import com.jmz.soft.devs_basetwrptheme.helpers.vars;
import com.jmz.soft.devs_basetwrptheme.objects.Theme;
import com.jmz.soft.devs_basetwrptheme.zip.ParseXML;
import com.jmz.soft.devs_basetwrptheme.zip.ReadZip;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Theme currentTheme;
    private LinearLayout currentLayout;
    private ImageButton preview;
    private final int MY_PERMISSIONS_REQUEST_READ_STORAGE = 0;
    private final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1;
    private boolean isOurs = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RequestPermissions();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    private void loadInterface() {
        if (StorageHelper.isThemed()) {
            File xmlFile = ReadZip.getXML();
            ReadZip.getPreview();
            if (xmlFile != null) {
                try {
                    currentTheme = ParseXML.getCurrentThemeInfo(xmlFile);
                    vars.SetTheme(currentTheme);
                    currentLayout = (LinearLayout) findViewById(R.id.currentTheme);
                    currentLayout.setVisibility(RelativeLayout.VISIBLE);
                    if (StorageHelper.isOurTheme(currentTheme)) {
                        isOurs = true;
                        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                        fab.setImageResource(android.R.drawable.ic_delete);
                    }
                    if (currentTheme.getThemePreview() != null) {
                        preview = (ImageButton) findViewById(R.id.currentPreview);
                        preview.setImageBitmap(currentTheme.getThemePreview());
                    } else {
                        preview = (ImageButton) findViewById(R.id.currentPreview);
                        preview.setImageResource(R.drawable.preview);
                    }
                    preview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent ThemeView = new Intent(getApplication(), com.jmz.soft.devs_basetwrptheme.ThemeView.class);
                            startActivity(ThemeView);
                        }
                    });
                    TextView currentName = (TextView) findViewById(R.id.currentTitle);
                    currentName.setText(currentTheme.getCurrentTheme());
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void RequestPermissions() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
            }
        } else {
            loadInterface();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    loadInterface();

                } else {
                    NoPermissions();
                }
                return;
            }

            case MY_PERMISSIONS_REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadInterface();
                } else {
                    NoPermissions();
                }
                return;
            }
        }


    }

    private void NoPermissions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissions");
        builder.setIcon(android.R.drawable.stat_notify_error);
        builder.setMessage("You did not allow the permissions. \n\nPlease accept the permissions to access the internal storage.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RequestPermissions();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        if (isOurs) {
            File theme = new File(vars.path + vars.name);
            if (theme.exists()) {
                theme.delete();
                Snackbar.make(view, "The theme has been removed!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        } else {
            if (StorageHelper.ExtractTheme(this)) {
                Snackbar.make(view, "The theme has been applied!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                Snackbar.make(view, "The theme did not apply, please contact the developers with a log.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }
}
