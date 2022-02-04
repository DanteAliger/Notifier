package com.notifier.web.request;

import com.notifier.web.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SaveTemplateRq {

    @Size(min = 2, max = 20, message = Constants.INVALID)
    @NotNull(message = Constants.NOT_NULL)
    private String name;
    private List<SaveEventRq> events;


}
