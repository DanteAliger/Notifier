package com.notifier.web.request;

import com.notifier.web.utils.Constants;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data // создаёт коструктор() required argument, get set toString equals HashCode
public class SavePersonRq {
    @Size(min = 2, max = 20, message = Constants.INVALID)
    @NotNull(message = Constants.NOT_NULL)
    private String name;

    @Size(min = 2, max = 20, message = Constants.INVALID)
    @NotNull(message = Constants.NOT_NULL)
    private String surname;

    @NotNull(message = Constants.NOT_NULL)
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$" , message = Constants.INVALID)
    private String phone;

    @NotNull(message = Constants.NOT_NULL)
    @Email(message = Constants.INVALID)
    private String email;
}
