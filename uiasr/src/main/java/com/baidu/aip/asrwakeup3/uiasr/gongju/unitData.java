package com.baidu.aip.asrwakeup3.uiasr.gongju;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class unitData {

    JSONObject jb = new JSONObject();
    JSONObject jb2 = new JSONObject();
    JSONObject jb21 = new JSONObject();
    JSONObject jb211 = new JSONObject();
    List[] lt212, lt31;
    JSONObject jb3 = new JSONObject();

    public String data(String query, String user_id, String log_id, String session) {
        try {

            jb3.put("asr_candidates", lt31);
            jb3.put("source", "KEYBOARD");
            jb3.put("type", "TEXT");
            jb21.put("client_results", jb211);
            jb21.put("candidate_options", lt212);


            jb2.put("bernard_level", 0);
            jb2.put("query", query);
            jb2.put("updates", "");
            jb2.put("user_id", user_id);
            jb2.put("client_session", "{\"client_results\":\"\", \"candidate_options\":[]}");
            jb2.put("query_info", jb3);


            jb.put("version", "2.0");
            jb.put("bot_id", "12574");
            jb.put("log_id", log_id);
            jb.put("bot_session", session);
            jb.put("request", jb2);

            return jb.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}

