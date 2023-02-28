package com.study.jpa.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = Shape.OBJECT)
public enum ResultCodeImpl implements ResultCode{
    
    //Error
    FAIL("E000", "   !", false),
    
    INVALID_KEY("E001", "유효하지 않은 key 값입니다.", false),
    
    NULLPOINTER("E002", "null 값에 접근하였습니다.", false),
    
    RESOURCE_NOT_FOUND("E003", "값이 존재하지 않습니다.", false),
    
    DIFFRENT_KEY("E004", "key 값이 일치하지 않습니다.", false),
    
    DISCONNECTED("E005", "DataBase와 연결이 끊겼습니다.", false),
    
    //Success
    SUCCESS("OK", "   !", true);
    
    private String code;
    
    private String message;
    
    private boolean success;
    
    ResultCodeImpl(String code, String message, boolean success){
        this.code = code;
        this.message = message;
        this.success = success;
    }

    @Override
    public String getValue ()
    {
        return null;
    }

    @Override
    public String getKey ()
    {
        return null;
    }
    

}