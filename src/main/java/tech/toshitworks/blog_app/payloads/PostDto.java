package tech.toshitworks.blog_app.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.toshitworks.blog_app.utils.Constants.DtoConstraintsErrorMessage.PostDtoConstraintsError;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer id;

    private Date date;

    @NotEmpty(message = PostDtoConstraintsError.TITLE_NOT_EMPTY)
    @Size(min = 10,max = 100,message = PostDtoConstraintsError.TITLE_SIZE)
    private String title;

    @NotEmpty(message = PostDtoConstraintsError.CONTENT_NOT_EMPTY)
    @Size(min = 100,max = 10000,message = PostDtoConstraintsError.CONTENT_SIZE)
    private String content;

    private CategoryDto category;

    private UserDto user;

}
