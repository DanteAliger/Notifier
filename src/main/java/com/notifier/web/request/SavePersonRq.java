package com.notifier.web.request;

import com.notifier.web.request.validation.One;
import com.notifier.web.request.validation.Two;
import com.notifier.web.utils.Constants;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data // создаёт коструктор() required argument, get set toString equals HashCode
public class SavePersonRq {

    @NotNull(message = Constants.NOT_NULL , groups = One.class)
    @Size(min = 2, max = 20, message = Constants.INVALID , groups = Two.class)
    private String name;

    @NotNull(message = Constants.NOT_NULL, groups = One.class)
    @Size(min = 2, max = 20, message = Constants.INVALID, groups = Two.class)
    private String surname;

    @NotNull(message = Constants.NOT_NULL, groups = One.class)
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$" , message = Constants.INVALID, groups = Two.class)
    private String phone;

    @NotNull(message = Constants.NOT_NULL, groups = One.class)
    @Email(message = Constants.INVALID, groups = Two.class)
    private String email;
}
