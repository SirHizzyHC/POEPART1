/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POE;

/**
 *
 * @author Student
 */
import java.util.regex.Pattern;

public class Login {
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellPhone;

    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        boolean hasEightChars = password.length() >= 8;
        boolean hasCapitalLetter = !password.equals(password.toLowerCase());
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecialChar = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        return hasEightChars && hasCapitalLetter && hasNumber && hasSpecialChar;
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        /* ATTRIBUTION: Regex pattern structure adapted from standard international E.164 phone validation rules 
           sourced from Oracle Java Documentation (https://docs.oracle.com/). */
        String regex = "^\\+\\d{1,3}\\d{7,10}$";
        return Pattern.matches(regex, phoneNumber);
    }

    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.";
        }
        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredCellPhone = phoneNumber;
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername == null || enteredPassword == null) return false;
        return enteredUsername.equals(registeredUsername) && enteredPassword.equals(registeredPassword);
    }

    public String returnLoginStatus(boolean isLoggedIn, String firstName, String lastName) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}