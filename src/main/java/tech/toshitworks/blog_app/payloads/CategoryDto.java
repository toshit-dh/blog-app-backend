package tech.toshitworks.blog_app.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.toshitworks.blog_app.utils.Constants.DtoConstraintsErrorMessage.CategoryDtoConstraintsError;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;

    @NotEmpty(message = CategoryDtoConstraintsError.TITLE_NOT_EMPTY)
    @Size(min = 5, max = 15, message = CategoryDtoConstraintsError.TITLE_SIZE)
    private String title;

    @NotEmpty(message = CategoryDtoConstraintsError.DESCRIPTION_NOT_EMPTY)
    @Size(min = 15, max = 100, message = CategoryDtoConstraintsError.DESCRIPTION_SIZE)
    private String description;
}
