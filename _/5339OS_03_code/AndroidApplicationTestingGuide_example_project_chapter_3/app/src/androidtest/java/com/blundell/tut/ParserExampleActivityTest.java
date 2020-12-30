package com.blundell.tut;

import android.test.AndroidTestCase;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParserExampleActivityTest extends AndroidTestCase {

    public void testParseXml() throws IOException {
        InputStream assetsXml = getContext().getAssets().open("my_document.xml");

        String result = parseXml(assetsXml);

        assertNotNull(result);
    }

    private String parseXml(InputStream xml) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(xml));

            int eventType = parser.getEventType();
            StringBuilder sb = new StringBuilder();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.TEXT) {
                    sb.append(parser.getText());
                }
                eventType = parser.next();
            }
            return sb.toString();
        } catch (Exception e) {
            Log.e("TAG", "Error - don't ever ignore an error like this", e);
        }
        return null;
    }
}
