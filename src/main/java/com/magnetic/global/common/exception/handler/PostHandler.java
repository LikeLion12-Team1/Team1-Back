package com.magnetic.global.common.exception.handler;

import com.magnetic.global.common.code.BaseErrorCode;
import com.magnetic.global.common.exception.GeneralException;

public class PostHandler extends GeneralException {
    public PostHandler(BaseErrorCode code) {
        super(code);
    }
}
