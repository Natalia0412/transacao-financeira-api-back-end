package com.tgid.teste.junior.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondErrorMsg {
    private Instant timestamp;
    private Integer status;
    private List<ErrorMsg> errors;
    private String path;
}
