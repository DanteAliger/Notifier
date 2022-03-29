package com.notifier.web.request;

import lombok.Data;

@Data // создаёт коструктор() required argument, get set toString equals HashCode
public class SavePersonRq {

//    @NotNull(message = Constants.NOT_NULL , groups = One.class)
//    @Size(min = 2, max = 20, message = Constants.INVALID , groups = Two.class)
    private String name;

//    @Size(min = 1, max = 20, message = Constants.INVALID, groups = Two.class)
    private String surname;

//    @NotNull(message = Constants.NOT_NULL, groups = One.class)
//    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$" , message = Constants.INVALID, groups = Two.class)
    private String phone;

//    @Email(message = Constants.INVALID, groups = Two.class)
    private String email;

//  @NotNull(message = Constants.NOT_NULL, groups = One.class)
    private Long idTelegram;

}
