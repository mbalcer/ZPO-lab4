package table;

public class TableMethods {
    private int no;
    private String method;

    public TableMethods(int no, String method) {
        this.no = no;
        this.method = method;
    }

    public TableMethods() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
