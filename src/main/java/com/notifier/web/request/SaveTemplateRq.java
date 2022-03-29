package com.notifier.web.request;

import com.notifier.web.request.validation.One;
import com.notifier.web.request.validation.Two;
import com.notifier.web.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveTemplateRq {

    @Size(min = 1, max = 50, message = Constants.INVALID, groups = Two.class)
    @NotNull(message = Constants.NOT_NULL , groups = One.class)
    private String name;
}
