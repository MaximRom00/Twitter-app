package by.rom.xapp.exception;

public class ExpiredRefreshToken extends RuntimeException{
    public ExpiredRefreshToken(String message) {
        super(message);
    }
}
