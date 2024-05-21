package com.casebtn.casebtn.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseData<T> {

    private Boolean status;
    private List<String> messages = new ArrayList<>();
    private T payload;
}
