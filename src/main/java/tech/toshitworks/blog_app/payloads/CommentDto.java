package tech.toshitworks.blog_app.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.toshitworks.blog_app.entity.Post;
import tech.toshitworks.blog_app.entity.User;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

        private Integer id;

        private String content;

        private Date date;

        private PostDto post;

        private UserDto user;

}
