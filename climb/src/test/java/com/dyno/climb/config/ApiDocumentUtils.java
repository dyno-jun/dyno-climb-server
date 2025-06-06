package com.dyno.climb.config;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public interface ApiDocumentUtils {

  static OperationRequestPreprocessor getDocumentRequest() {
    return preprocessRequest(
        modifyUris() // (1)
            .scheme("https")
            .host("climb.dyno.co.kr")
            .removePort(),
        prettyPrint()); // (2)
  }

  static OperationResponsePreprocessor getDocumentResponse() {
    return preprocessResponse(prettyPrint()); // (3)
  }
}
