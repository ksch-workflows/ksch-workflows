package io.github.kschworkflows.services.staff;

import java.util.HashMap;
import java.util.Map;

public class LoginService
{
    private Map<String, User> registeredUsers = new HashMap<>();

    {
        registeredUsers.put("jdoe", new User("John Doe", "jdoe", "1234", "NURSE"));
        registeredUsers.put("ttester", new User("Tony Tester", "ttester", "testIng4dev", "CLERCK"));
    }

    public void addUser(User user)
    {
        registeredUsers.put(user.getLogin(), user);
    }

    public User getUser(String login)
    {
        return registeredUsers.containsKey(login) ? registeredUsers.get(login) : null;
    }

    public boolean isLoginAllowed(String login, String password)
    {
        return isUserPresent(login) && isCorrectPasswordProvided(login, password);
    }

    private boolean isUserPresent(String login)
    {
        return registeredUsers.containsKey(login);
    }

    private boolean isCorrectPasswordProvided(String login, String password)
    {
        User registeredUser = getUser(login);
        return registeredUser.getPassword().equals(password);
    }
}
