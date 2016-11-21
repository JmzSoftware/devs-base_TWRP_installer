package com.jmz.soft.devs_basetwrptheme.zip;

import com.jmz.soft.devs_basetwrptheme.helpers.vars;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by mazwoz on 21-Nov-16.
 */

public class ReadZip {

    private static final String xml = vars.path + vars.info;

    public static File getXML() {
        File xmlFile = null;
        try {
            OutputStream out = new FileOutputStream(xml);
            FileInputStream fin = new FileInputStream(vars.path + vars.name);
            BufferedInputStream bin = new BufferedInputStream(fin);
            ZipInputStream zin = new ZipInputStream(bin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.getName().equals(vars.info)) {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = zin.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    xmlFile = new File(xml);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (xmlFile.exists()) {
            return xmlFile;
        } else {
            return null;
        }
    }

    public static File getPreview() {
        File imageFile = null;
        try {
            OutputStream out = new FileOutputStream(vars.path + "preview.png");
            FileInputStream fin = new FileInputStream(vars.path + vars.name);
            BufferedInputStream bin = new BufferedInputStream(fin);
            ZipInputStream zin = new ZipInputStream(bin);
            boolean found = false;
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.getName().equals("images/preview.png")) {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = zin.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    imageFile = new File(vars.path + "preview.png");
                    found = true;
                    break;
                }
            }
            if (!found) {
                while ((ze = zin.getNextEntry()) != null) {
                    if (ze.getName().equals("images/logo.png")) {
                        byte[] buffer = new byte[4096];
                        int len;
                        while ((len = zin.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }
                        out.close();
                        imageFile = new File(vars.path + "preview.png");
                        found = true;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (imageFile != null) {
            if (imageFile.exists()) {
                return imageFile;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
