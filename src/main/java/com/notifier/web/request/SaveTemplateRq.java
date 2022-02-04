package com.notifier.web.request;

import com.notifier.model.Event;
import com.notifier.web.request.validation.One;
import com.notifier.web.request.validation.Two;
import com.notifier.web.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SaveTemplateRq {

    @Size(min = 2, max = 20, message = Constants.INVALID, groups = Two.class)
    @NotNull(message = Constants.NOT_NULL , groups = One.class)
    private String name;
}
