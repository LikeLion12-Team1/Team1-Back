package com.magnetic.global.common.exception.handler;

import com.magnetic.global.common.code.BaseErrorCode;
import com.magnetic.global.common.exception.GeneralException;

public class ChallengeHandler extends GeneralException {
    public ChallengeHandler(BaseErrorCode code) {
        super(code);
    }
}
