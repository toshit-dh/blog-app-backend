package tech.toshitworks.blog_app.utils;

public class Constants {
    public static class Pagination {
        public static class DefaultValue {
            public static final String PAGE_NUMBER = "0";
            public static final String PAGE_SIZE = "10";
            public static final String SORT_BY = "date";
            public static final String ASCENDING = "true";
        }
        public static class Value {
            public static final String PAGE_NUMBER = "pageNo";
            public static final String PAGE_SIZE = "pageSize";
            public static final String SORT_BY = "sortBy";
            public static final String ASCENDING = "ascending";
        }
    }

    public static class DtoConstraintsErrorMessage {
        public static class PostDtoConstraintsError {
            public static final String TITLE_NOT_EMPTY = "Title cannot be empty";
            public static final String TITLE_SIZE = "Title can contain a minimum of 10 characters and a maximum of 100 characters";
            public static final String CONTENT_NOT_EMPTY = "Content cannot be empty";
            public static final String CONTENT_SIZE = "Content can contain a minimum of 100 characters and a maximum of 10000 characters";
        }

        public static class UserDtoConstraintsError {
            public static final String NAME_NOT_EMPTY = "Name should not be empty";
            public static final String NAME_SIZE = "Name should contain a minimum of 5 characters and a maximum of 15 characters";
            public static final String EMAIL_NOT_VALID = "Email address is not valid";
            public static final String PASSWORD_NOT_EMPTY = "Password should not be empty";
            public static final String PASSWORD_SIZE = "Password should contain a minimum of 8 characters and a maximum of 16 characters";
            public static final String ABOUT_NOT_EMPTY = "About should not be empty";
            public static final String ABOUT_SIZE = "About should contain a minimum of 20 characters and a maximum of 100 characters";
        }

        public static class CategoryDtoConstraintsError {
            public static final String TITLE_NOT_EMPTY = "Title should not be empty";
            public static final String TITLE_SIZE = "Title should contain a minimum of 5 characters and a maximum of 15 characters";
            public static final String DESCRIPTION_NOT_EMPTY = "Description should not be empty";
            public static final String DESCRIPTION_SIZE = "Description should contain a minimum of 15 characters and a maximum of 100 characters";
        }
    }

    public static class ApiRoutes {
        public static class PostRoutes {
            public static final String BASE = "/post";
            public static final String CREATE = "/{userId}/{categoryId}";
            public static final String GET_BY_ID = "/{id}";
            public static final String GET_BY_USER = "/user/{id}";
            public static final String GET_BY_CATEGORY = "/category/{id}";
            public static final String GET_BY_TITLE = "/title/{title}";
            public static final String UPDATE = "/{id}";
            public static final String DELETE = "/{id}";
            public static final String GET_ALL = "";
            public static final String SEARCH = "search/{keyword}";
            public static final String POST_DELETED = "Post Successfully Deleted";

            public static final String REQUEST_IMAGE = "image";
        }

        public static class UserRoutes {
            public static final String BASE = "/user";
            public static final String CREATE = "";
            public static final String GET_BY_ID = "/id/{id}";
            public static final String GET_BY_NAME = "/name/{name}";
            public static final String UPDATE = "/{id}";
            public static final String DELETE = "/{id}";
            public static final String GET_ALL = "";
        }

        public static class CommentRoutes{

            public static final String BASE = "/comment";

            public static final String GET_BY_USER = "/user/{id}";

            public static final String GET_BY_POST = "/post/{id}";

            public static final String CREATE = "/{postId}";

            public static final String UPDATE = "/{id}";

            public static final String DELETE = "/{id}";
            public static final String COMMENT_DELETED = "Comment Deleted";
        }

        public static class AuthRoutes{
            public static final String BASE = "/auth";
            public static final String LOGIN = "/login";
            public static final String VERIFY = "/verify";
        }

        public static class CategoryRoutes {
            public static final String BASE = "/category";
            public static final String CREATE = "";
            public static final String GET_BY_ID = "/{id}";
            public static final String GET_BY_TITLE = "/title/{title}";
            public static final String UPDATE = "/{id}";
            public static final String DELETE = "/{id}";
            public static final String GET_ALL = "";
        }
    }
}
