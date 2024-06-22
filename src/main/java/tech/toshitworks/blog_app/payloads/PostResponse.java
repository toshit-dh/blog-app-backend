package tech.toshitworks.blog_app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> content;

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalPages;

    private Integer totalElements;

    private Boolean lastPage;

}
