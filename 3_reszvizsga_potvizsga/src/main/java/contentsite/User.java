package contentsite;

public class User {
    private String userName;
    private int password;
    private boolean logIn;
    private boolean premiumMember;

    public User(String name, String passwordString) {
        this.userName = name;
        String pass= name+passwordString;
        this.password = (pass.hashCode());
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isLogIn() {
        return logIn;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void upgradeForPremium() {
        this.premiumMember = true;
    }

    public void setLogIn(boolean value) {
        this.logIn = value;
    }
}
