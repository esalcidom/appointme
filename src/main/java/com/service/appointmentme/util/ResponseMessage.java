package com.service.appointmentme.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {

    private String message;
    private String object;
}
