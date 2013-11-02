package formo.domain;

public class Type {

    private int id;
    private String name;
    private int authorId;
    private String oraw;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getOraw() {
        return this.oraw;
    }

    public void setOraw(String oraw) {
        this.oraw = oraw;
    }

    public String toString() {
        return "{ type : { \"id\" : \"" + this.id + "\", \"name : \" : \"" + this.name + "\"}";
    }

}