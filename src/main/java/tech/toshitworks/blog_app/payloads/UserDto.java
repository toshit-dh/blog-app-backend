package tech.toshitworks.blog_app.payloads;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 5,max = 15,message = "Name should contain minimum of 5 characters and maximum of 15 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8,max = 16,message = "Password should contain minimum of 8 characters and maximum of 16 characters")
    private String password;

    @NotEmpty(message = "About should not be empty")
    @Size(min = 20,max = 100,message = "About should contain minimum of 20 characters and maximum of 100 characters")
    private String about;
}
