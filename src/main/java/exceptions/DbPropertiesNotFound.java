package exceptions;

public class DbPropertiesNotFound extends RuntimeException {
    public DbPropertiesNotFound() {
        super("db.properties not found!");
    }
}