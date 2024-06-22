package tech.toshitworks.blog_app.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 5,max = 15,message = "Title should contain minimum of 5 characters and maximum of 15 characters")
    private String title;

    @NotEmpty(message = "Description should not be empty")
    @Size(min = 15,max = 100,message = "Description should contain minimum of 15 characters and maximum of 100 characters")
    private String description;


}
