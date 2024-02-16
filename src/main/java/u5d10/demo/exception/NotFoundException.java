package u5d10.demo.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id){
        super("l'id "+ id + " non è stato trovato");
    }
}
