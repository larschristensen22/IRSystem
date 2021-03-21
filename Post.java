public class Post{
    
    private String docID;
    private int docFrequency;
    
    public Post (String docID) {
        this.docID = docID;
        this.docFrequency = 1;
    }

    public String toString() {
        String result = "DOC ID: " + this.docID;
        return result; 
    }
}
