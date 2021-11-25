package com.example.castaticanalyzer.code.gateways;

import com.example.castaticanalyzer.code.DTO.CodeModel;
import com.example.castaticanalyzer.code.DTO.GithubRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Repository
public class GithubCodeDataGateway implements CodeDataGateway{
    @Override
    public CodeModel getCodeModel(Map<String, Object> requestBody) {
        return null;
    }

    private String sendHTTPSRequest(HttpsURLConnection connection) throws IOException {
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            throw new IllegalArgumentException();
        }

        InputStreamReader stream = new InputStreamReader(connection.getInputStream());
        BufferedReader in = new BufferedReader(stream);
        String input_line;

        StringBuilder response = new StringBuilder();
        while ((input_line = in.readLine()) != null) {
            response.append(input_line);
        }
        in.close();
        return response.toString();
    }

    private String sendHTTPSRequest(URL url, Map<String, String> properties) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        for (String key :
                properties.keySet()) {
            connection.setRequestProperty(key, properties.get(key));
        }
        return sendHTTPSRequest(connection);
    }

    private String sendHTTPSRequest(URL url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        return sendHTTPSRequest(connection);
    }

    private String getSourceDirTreeURL(GithubRepo repo) {
        URL url;
        String result = "";
        try {
            url = new URL(repo.getSourceDirURL());
            String json = sendHTTPSRequest(url);
            result = new ObjectMapper().readTree(json).get(0).get("git_url").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getWholeProjectTree(GithubRepo repo) {
        URL url;
        String result = "";
        try {
            url = new URL(getSourceDirTreeURL(repo));
            Map<String, String> properties = new Hashtable<>();
            properties.put("recursive", "true");
            result = sendHTTPSRequest(url, properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String[] getAllSourceCodeFilePaths(GithubRepo repo) {
        String json = getWholeProjectTree(repo);

        List<String> sourceCodeFilePathList = new ArrayList<>();
        try {
            JsonNode sourceCodeTree = new ObjectMapper().readTree(json).get("tree");
            for (int i = 0; i < sourceCodeTree.size(); i++) {
                JsonNode node = sourceCodeTree.get(i);
                if (Objects.equals(node.get("type").asText(), "blob")) {
                    sourceCodeFilePathList.add(node.get("path").asText());
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return sourceCodeFilePathList.toArray(new String[0]);
    }
}
