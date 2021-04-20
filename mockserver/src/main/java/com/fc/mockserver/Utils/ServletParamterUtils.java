package com.fc.mockserver.Utils;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServletParamterUtils {

    public static Map<String, String> getParam(Map<String, String[]> parameterMap) {
        return parameterMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> handleParamForArray(entry.getValue())));
    }

    private static String handleParamForArray(String[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            return "";
        }
        return args[0];
    }
}
