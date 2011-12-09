package com.vokal.test.exp.network;

import java.util.HashMap;

public class TestNetworkImpl extends AbstractNetworkImpl {

    public String get(String aUrl, HashMap<String, String> aParams) throws Exception {
        if (aUrl.equals("http://192.168.1.119:3000/api/users/")) {
                return "THIS IS TEST DATA HAHAH I WIN";
        }
        return "";
    }

    public String post(String aUrl, HashMap<String, String> aParams) throws Exception {
        return "";
    }

    public String put(String aUrl, HashMap<String, String> aParams) throws Exception {
        return "";
    }

    public String delete(String aUrl) throws Exception {
        return "";
    }


}
