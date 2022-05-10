package core.utils;

import lombok.Getter;

import static core.utils.DataFaker.*;

@Getter
public class User extends DataFaker{
    static String userFirstName;
    static String userLastName;
    static String userEmail;
    static String userPassword;
    static int userStatus;
    static String userPhone;
    static String userName;
    static int userId;

    public User() {
        this.userFirstName = getUserFirstName();
        this.userLastName = getUserLastName();
        this.userEmail = getUserEmail();
        this.userPassword = getUserPassword();
        this.userStatus = getUserStatus();
        this.userPhone = getUserPhone();
        this.userName = getUserName();
        this.userId = getUserId();
    }
}
