package tables ;
import java.io.Serializable;
/**
 *
 * @author Jiemei Lei
 */
public class Account implements Serializable{
    private String id;
    private String balance;
    private String type;
    private String status;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatusn(String status) {
        this.status = status;
    }
}
