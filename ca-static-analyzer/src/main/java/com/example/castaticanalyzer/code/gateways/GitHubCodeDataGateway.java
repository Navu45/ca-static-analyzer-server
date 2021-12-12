package com.example.castaticanalyzer.code.gateways;

import com.example.castaticanalyzer.code.DTO.Code;
import com.example.castaticanalyzer.code.DTO.GithubRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class GitHubCodeDataGateway implements CodeDataGateway{

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

    private String sendHTTPSRequest(URL url) throws IOException {
        System.err.println(url.toString());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        return sendHTTPSRequest(connection);
    }

    private String getSourceDirTreeURL(GithubRepo repo) {
        URL url;
        String result = "";
        try {
            url = new URL(repo.getSourceDirURL());
            String json = sendHTTPSRequest(url);
            result = new ObjectMapper().readTree(json).get(0).get("git_url").asText() + "?recursive=true";
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
            result = sendHTTPSRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String[] getAllSourceCodeFilePaths(GithubRepo repo) {
        String json = getWholeProjectTree(repo);
        String sourceDir = repo.getSourceDir();
        List<String> sourceCodeFilePathList = new ArrayList<>();
        try {
            JsonNode sourceCodeTree = new ObjectMapper().readTree(json).get("tree");
            for (int i = 0; i < sourceCodeTree.size(); i++) {
                JsonNode node = sourceCodeTree.get(i);
                if (Objects.equals(node.get("type").asText(), "blob")) {
                    sourceCodeFilePathList.add(sourceDir + "/java/" + node.get("path").asText());
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return sourceCodeFilePathList.toArray(new String[0]);
    }

    public List<Code> getSourceCode(GithubRepo repo) throws IOException {
        String[] filePaths = getAllSourceCodeFilePaths(repo);
        List<Code> sourceCode = new ArrayList<>(filePaths.length);
        for (String path :
                filePaths) {
            if (path.contains(".java")) {
                String codeData = sendHTTPSRequest(new URL(repo.getFileRawContentURL(path)));
                sourceCode.add(new Code(path, codeData));
            }
        }
        return sourceCode;
    }
}
