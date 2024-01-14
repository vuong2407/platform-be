package vn.whatsenglish.orchestrator.utils;

import retrofit2.Response;
import vn.whatsenglish.orchestrator.commons.ResponseTemplate;

import java.io.IOException;

public class ValidateResponseRetrofit {
    public static ResponseTemplate validate(Response<?> response) throws IOException {
        ResponseTemplate responseTemplate = ResponseTemplate.builder()
                .code(200)
                .errorContent(null)
                .build();
        if (!response.isSuccessful()) {
            assert response.errorBody() != null;
            responseTemplate.setCode(response.raw().code());
            responseTemplate.setErrorContent(response.errorBody().string());
        }
        return responseTemplate;
    }
}
