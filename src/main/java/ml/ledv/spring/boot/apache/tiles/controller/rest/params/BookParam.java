package ml.ledv.library.rest.params;

public class BookParam {

    private String id;

    private String name;

    public BookParam() {
    }

    public BookParam(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }
}
