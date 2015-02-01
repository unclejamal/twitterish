package pduda.twitter.domain;

import java.time.Instant;

public class Message {
    private final AccountName accountName;
    private final String content;
    private final Instant publicationDate;

    public Message(AccountName accountName, String content, Instant publicationDate) {
        this.accountName = accountName;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    public boolean hasBeenPostedBy(AccountName accountName) {
        return accountName.equals(this.accountName);
    }

    public AccountName getAccountName() {
        return accountName;
    }

    public String getContent() {
        return content;
    }

    public Instant getPublicationDate() {
        return publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (publicationDate != null ? !publicationDate.equals(message.publicationDate) : message.publicationDate != null) return false;
        if (accountName != null ? !accountName.equals(message.accountName) : message.accountName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountName != null ? accountName.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "socialNetworker=" + accountName +
                ", content='" + content + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
