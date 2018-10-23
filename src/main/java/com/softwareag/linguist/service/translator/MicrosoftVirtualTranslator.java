package com.softwareag.linguist.service.translator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softwareag.linguist.service.translator.entity.TranslationResult;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by anki on 10/19/2018.
 */
@Component
public class MicrosoftVirtualTranslator {

    static String subscriptionKey = "d1a1960be4c64025ab76eb5a8358631a";
    static String host = "https://api.cognitive.microsofttranslator.com";
    static String path = "/translate?api-version=3.0";

    public static class RequestBody {
        String Text;

        public RequestBody(String text) {
            this.Text = text;
        }
    }

    public String Post (URL url, String content) throws Exception {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Content-Length", content.length() + "");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        connection.setRequestProperty("X-ClientTraceId", java.util.UUID.randomUUID().toString());
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        byte[] encoded_content = content.getBytes("UTF-8");
        wr.write(encoded_content, 0, encoded_content.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }

    public List<TranslationResult> translate (String text, List<Locale> languages) throws Exception {
        StringBuffer params = new StringBuffer();
        for(Locale locale : languages){
            params.append("&");
            params.append("to="+locale.getLanguage());
        }
        URL url = new URL (host + path + params.toString());

        List<RequestBody> objList = new ArrayList<RequestBody>();
        objList.add(new RequestBody(text));
        String content = new Gson().toJson(objList);

        return convert(Post(url, content));
    }

    public List<TranslationResult> translate (String text, Locale language) throws Exception {
        StringBuffer params = new StringBuffer();
        params.append("&");
        params.append("to="+language.getLanguage());
        URL url = new URL (host + path + params.toString());

        List<RequestBody> objList = new ArrayList<RequestBody>();
        objList.add(new RequestBody(text));
        String content = new Gson().toJson(objList);

        return convert(Post(url, content));
    }

    private List<TranslationResult> convert(String json_text) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<TranslationResult>>() {}.getType();
        List<TranslationResult> translationResult = gson.fromJson(json_text, listType);
        return translationResult;
    }

}
