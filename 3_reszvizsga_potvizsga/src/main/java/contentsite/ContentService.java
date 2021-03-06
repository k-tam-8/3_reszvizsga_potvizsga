package contentsite;

import java.util.HashSet;
import java.util.Set;

public class ContentService {
    private Set<User> users = new HashSet<>();
    private Set<Content> contents = new HashSet<>();

    public void registerUser(String name, String password) {
        if (!users.stream().anyMatch(u -> u.getUserName().equals(name))) {
            users.add(new User(name, password));
        } else throw new IllegalArgumentException("Username is already taken: " + name);
    }

    public void addContent(Content content) {
        if (!contents.stream().anyMatch(c -> c.getTitle().equals(content.getTitle()))) {
            contents.add(content);
        } else throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
    }

    public void logIn(String username, String password) {
        String pass = username + password;
        int generatedPassword = pass.hashCode();

        if (!users.stream().anyMatch(u -> u.getUserName().equals(username))) {
            throw new IllegalArgumentException("Username is wrong!");
        }
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                if (u.getPassword() != generatedPassword) {
                    throw new IllegalArgumentException("Password is Invalid!");
                } else u.setLogIn(true);
            }
        }
    }

    public void clickOnContent(User user, Content content) {

        if (user.isLogIn()) {
            if (content.isPremiumContent()) {
                if (user.isPremiumMember()) {
                    content.click(user);
                } else throw new IllegalStateException("Upgrade for Premium to watch this content!");
            } else content.click(user);

        } else throw new IllegalStateException("Log in to watch this content!");
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(users);
    }

    public Set<Content> getAllContent() {
        return new HashSet<>(contents);
    }
}
