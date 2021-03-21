//Класс объекта для вывода в консоль
public class OutputJson {

    long id;
    double amount;
    String currency;
    String comment;
    String filename;
    long line;
//    String result;

    public OutputJson() {

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getLine() {
        return line;
    }
    public void setLine(long line) {
        this.line = line;
    }
//
//    public String getResult() {
//        return result;
//    }
//    public void setResult(String result) {
//        this.result = result;
//    }


//    @Override
//    public String toString() {
//        return "id="+id+", amount="+amount+", comment="+comment+", filename="+filename+", line="+line;
//    }
}
