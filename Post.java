public class Post{
    
    private String docID;
    
    public Post (String docID) {
        this.docID = docID;
    }

    public String toString() {
        String result = "DOC ID: " + this.docID;
        return result; 
    }
}
