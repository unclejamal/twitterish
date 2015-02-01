package pduda.twitter.domain;

import java.time.Instant;

public class Message {
    private final AccountName author;
    private final String content;
    private final Instant publicationDate;

    public Message(AccountName author, String content, Instant publicationDate) {
        this.author = author;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    public boolean hasBeenPostedBy(AccountName accountName) {
        return accountName.equals(this.author);
    }

    public AccountName getAuthor() {
        return author;
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
        if (author != null ? !author.equals(message.author) : message.author != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "author=" + author +
                ", content='" + content + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
