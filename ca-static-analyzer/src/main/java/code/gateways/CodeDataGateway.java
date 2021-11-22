package code.gateways;

import code.DTO.CodeModel;

import java.io.IOException;
import java.util.Map;

public interface CodeDataGateway {
    CodeModel getCodeModel(Map<String, Object> requestBody) throws IOException;
}
