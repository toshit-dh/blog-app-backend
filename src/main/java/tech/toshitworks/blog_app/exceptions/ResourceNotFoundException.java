package tech.toshitworks.blog_app.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource,String field,String value){
        super(resource+" resource not found for "+field+" = "+value);
    }
}
