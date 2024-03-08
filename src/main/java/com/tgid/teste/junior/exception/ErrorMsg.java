package com.tgid.teste.junior.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ErrorMsg {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;
    private String message;
}
