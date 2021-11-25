package com.example.castaticanalyzer.code.gateways;

import com.example.castaticanalyzer.code.DTO.CodeModel;

import java.io.IOException;
import java.util.Map;

public interface CodeDataGateway {
    CodeModel getCodeModel(Map<String, Object> requestBody) throws IOException;
}
