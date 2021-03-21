public class Document {
    private String docID;
    private String text;
    private String author;
    private String publisher;
    private String description;
    private String date;

    public Document (String docID, String text) {
        this.docID = docID;
        this.text = text;
    }

    public Document(String docID, String text, String author, String date, String publisher, String description) {
        this.docID = docID;
        this.text = text;
        this.author = author;
        this.date = date;
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