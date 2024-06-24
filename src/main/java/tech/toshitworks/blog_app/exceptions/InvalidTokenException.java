package tech.toshitworks.blog_app.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String e){
        super(e);
    }
}
