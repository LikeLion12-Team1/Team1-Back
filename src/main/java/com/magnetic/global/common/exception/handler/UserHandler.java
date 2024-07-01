package com.magnetic.global.common.exception.handler;

import com.magnetic.global.common.code.BaseErrorCode;
import com.magnetic.global.common.exception.GeneralException;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode code) {
        super(code);
    }
}
