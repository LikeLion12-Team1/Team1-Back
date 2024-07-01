package com.magnetic.global.common.exception.handler;

import com.magnetic.global.common.code.BaseErrorCode;
import com.magnetic.global.common.exception.GeneralException;

public class PlantHandler extends GeneralException {
    public PlantHandler(BaseErrorCode code) {
        super(code);
    }
}
