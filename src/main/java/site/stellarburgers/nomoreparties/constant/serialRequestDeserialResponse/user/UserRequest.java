package site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user;

import java.util.Random;

import static site.stellarburgers.nomoreparties.constant.service.UriUserData.ALPHABET;

public class UserRequest {
    private String email;
    private String password;
    private String name;

    public UserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static UserRequest userGenerator() {
        return new UserRequest(getRandomString() + "@ya.ru", getRandomString(), getRandomString());
    }

    private static String getRandomString() {
        String alphabet = new String(ALPHABET.toCharArray());
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int rndCharAt = random.nextInt(alphabet.length());
            char rndChar = alphabet.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
