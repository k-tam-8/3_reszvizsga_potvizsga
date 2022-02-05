package contentsite;

import java.util.ArrayList;
import java.util.List;

public class Video implements Content {

    private double length;
    private String title;
    private List<User> users = new ArrayList<>();

    private static final int PREMIUM_LENGTH = 15;

    public Video(String title, double length) {
        this.length = length;
        this.title = title;
    }

    @Override
    public boolean isPremiumContent() {
        return (this.length > PREMIUM_LENGTH);
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(users);
    }

    @Override
    public void click(User user) {
        users.add(user);
    }
}
