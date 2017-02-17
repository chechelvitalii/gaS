package com.gas.station;

import com.google.common.io.Resources;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class TestUtils {

    public String getHtmlByPath(String path) throws IOException {
        URL url = TestUtils.class.getResource(path);
        return Resources.toString(url, UTF_8);
    }}
