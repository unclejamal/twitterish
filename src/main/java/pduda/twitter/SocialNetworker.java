package pduda.twitter;

public class SocialNetworker {
    private String username;

    public SocialNetworker(String username) {
        this.username = username;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialNetworker that = (SocialNetworker) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SocialNetworker{" +
                "username='" + username + '\'' +
                '}';
    }
}
