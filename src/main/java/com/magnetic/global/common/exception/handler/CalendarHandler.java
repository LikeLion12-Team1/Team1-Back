package com.magnetic.global.common.exception.handler;

import com.magnetic.global.common.code.BaseErrorCode;
import com.magnetic.global.common.exception.GeneralException;

public class CalendarHandler extends GeneralException {
    public CalendarHandler(BaseErrorCode code) {
        super(code);
    }
}
