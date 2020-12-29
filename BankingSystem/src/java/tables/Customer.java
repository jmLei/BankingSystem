package tables ;
import java.io.Serializable;

/**
 *
 * @author Jiemei Lei
 */
public class Customer implements Serializable {

    private String id = "";
    private String name = "";
    private String gender = "";
    private String age = "";
    private String pin = "";
    
     public String getId() {
        return id;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
}
