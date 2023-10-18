package com.bin.api.controller.param;

import com.bin.webase.core.model.IParam;
import lombok.Data;

import java.util.List;

@Data
public class TimeTemplateParam implements IParam {
    private String name;
    private String description;
    private List<TTimeParam> times;

    @Override
    public void validate() {

    }
}
