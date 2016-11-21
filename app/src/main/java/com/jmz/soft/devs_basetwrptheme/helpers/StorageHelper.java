package com.jmz.soft.devs_basetwrptheme.helpers;

import android.content.Context;
import android.content.res.AssetManager;

import com.jmz.soft.devs_basetwrptheme.objects.Theme;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mazwoz on 21-Nov-16.
 */

public class StorageHelper {

    public static boolean isThemed() {
        if (pathExists()) {
            File theme = new File(vars.path + vars.name);
            if(theme.exists()) {
                return true;
            } else {
                return false;
            }
        } else {
            makePath();
            return false;
        }
    }

    private static void makePath() {
        File path = new File(vars.path);
        path.mkdirs();
    }

    private static boolean pathExists () {
        File path = new File(vars.path);
        if(path.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOurTheme(Theme currentTheme) {
        return currentTheme.getCurrentTheme().equals("Devs-Base");
    }

    public static boolean ExtractTheme(Context context) {
        boolean extracted = false;
        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open("ui.zip");
            File outFile = new File(vars.path + vars.name);
            out = new FileOutputStream(outFile);
            extracted = copyFile(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return extracted;
    }

    private static boolean copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        File theme = new File(vars.path + vars.name);
        if (theme.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
