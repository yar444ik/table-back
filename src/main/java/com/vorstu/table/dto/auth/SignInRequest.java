package com.vorstu.table.dto.auth;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
public class SignInRequest {

    private String username;
    private String pwd;

}
