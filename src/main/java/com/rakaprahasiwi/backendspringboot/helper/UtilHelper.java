package com.rakaprahasiwi.backendspringboot.helper;

import java.util.HashMap;
import java.util.Map;

public class UtilHelper {
    public Map setOutputData(int count, String message, Object data) {
        Map output = new HashMap();

        output.put("count", count);
        output.put("message", message);
        output.put("data", data);

        return output;
    }
}
