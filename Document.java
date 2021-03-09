public class Document {
    private String docID;
    private String text;
    private String author;
    private String title;
    private String publisher;
    private String description;

    public Document (String docID, String text) {
        this.docID = docID;
        this.text = text;
    }

    public Document(String docID, String text, String author, String title, String publisher, String description) {
        this.docID = docID;
        this.text = text;
        this.author = title;
        this.publisher = publisher;
        this.description = description;
    }

    public String getDocID() {
        return this.docID;
    }

    public String getText() {
        return this.text;
    }
}