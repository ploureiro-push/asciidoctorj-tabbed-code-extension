package com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public final class FileUtils {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private FileUtils() {}

    public static String readFileContentsFromClasspath(String path) {
        URL url = getURLForResource(path);
        return readText(url);
    }

    public static String readFileContentsFromPath(String path) {
        try {
            URL url = new File(path).toURI().toURL();
            return readText(url);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getURLForResource(String path) {
        return FileUtils.class.getResource(path);
    }

    private static String readText(URL url) {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                text.append(currentLine);
                text.append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        return text.toString();
    }
}
