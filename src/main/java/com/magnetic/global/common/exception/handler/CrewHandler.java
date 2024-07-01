package com.magnetic.global.common.exception.handler;

import com.magnetic.global.common.code.BaseErrorCode;
import com.magnetic.global.common.exception.GeneralException;

public class CrewHandler extends GeneralException {
    public CrewHandler(BaseErrorCode code) {
        super(code);
    }
}
