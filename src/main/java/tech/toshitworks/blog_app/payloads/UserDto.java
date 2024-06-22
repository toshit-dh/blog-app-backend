package tech.toshitworks.blog_app.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.toshitworks.blog_app.utils.Constants.DtoConstraintsErrorMessage.UserDtoConstraintsError;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotEmpty(message = UserDtoConstraintsError.NAME_NOT_EMPTY)
    @Size(min = 5, max = 15, message = UserDtoConstraintsError.NAME_SIZE)
    private String name;

    @Email(message = UserDtoConstraintsError.EMAIL_NOT_VALID)
    private String email;

    @NotEmpty(message = UserDtoConstraintsError.PASSWORD_NOT_EMPTY)
    @Size(min = 8, max = 16, message = UserDtoConstraintsError.PASSWORD_SIZE)
    private String password;

    @NotEmpty(message = UserDtoConstraintsError.ABOUT_NOT_EMPTY)
    @Size(min = 20, max = 100, message = UserDtoConstraintsError.ABOUT_SIZE)
    private String about;
}
