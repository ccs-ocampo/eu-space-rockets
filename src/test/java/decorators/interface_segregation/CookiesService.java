package decorators.interface_segregation;

import org.openqa.selenium.Cookie;

import java.util.List;

public interface CookiesService {
    void addCookie(Cookie cookie);
    void deleteAllCookies();
    void deleteCookie(String cookieName);
    List<Cookie> getAllCookies();
    String getCookie(String cookieName);
}
