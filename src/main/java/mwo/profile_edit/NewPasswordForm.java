package mwo.profile_edit;

import java.io.Serializable;

public class NewPasswordForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String username;

    private String oldPassword;

    private String newPassword;

    public NewPasswordForm(){
        username = "";
        oldPassword = "";
        newPassword = "";
    }

    public String getUsername(){
        return username;
    }

    public String getOldPassword(){
        return oldPassword;
    }

    public String getNewPassword(){
        return newPassword;
    }
}