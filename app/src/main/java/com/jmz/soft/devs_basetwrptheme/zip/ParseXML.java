package com.jmz.soft.devs_basetwrptheme.zip;

/**
 * Created by mazwoz on 21-Nov-16.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import com.jmz.soft.devs_basetwrptheme.helpers.vars;
import com.jmz.soft.devs_basetwrptheme.objects.Theme;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class ParseXML {

    public static Theme getCurrentThemeInfo(File inputFile) throws ParserConfigurationException, IOException, SAXException {
        Theme currentTheme = new Theme();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("details");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                currentTheme.setCurrentTheme(element.getElementsByTagName("title").item(0).getTextContent());

                currentTheme.setThemeAuthor(element.getElementsByTagName("author").item(0).getTextContent());
                currentTheme.setThemeVersion(element.getElementsByTagName("themeversion").item(0).getTextContent());
                currentTheme.setThemeDescription(element.getElementsByTagName("description").item(0).getTextContent());
                currentTheme.setThemePreview(GetBitmap(vars.path + element.getElementsByTagName("preview").item(0).getTextContent()));
            }
        }

        return currentTheme;
    }

    private static Bitmap GetBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(path, options);
    }
}
