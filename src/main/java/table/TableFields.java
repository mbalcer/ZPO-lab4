package table;

public class TableFields {

    private int no;
    private String field;
    private String value;

    public TableFields(int no, String field, String value) {
        this.no = no;
        this.field = field;
        this.value = value;
    }

    public TableFields() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
