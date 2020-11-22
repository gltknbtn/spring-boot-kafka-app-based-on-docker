package com.gltknbtn.request;

import com.gltknbtn.common.InOutCommon;
import lombok.Data;

@Data
public class SavePersonRequest extends InOutCommon {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
}
