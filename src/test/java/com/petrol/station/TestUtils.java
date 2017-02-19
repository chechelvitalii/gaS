package com.petrol.station;

import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class TestUtils {

    public static String getHtmlByPath(String path) throws IOException {
        URL url = TestUtils.class.getResource(path);
        return Resources.toString(url, UTF_8);
    }
}
