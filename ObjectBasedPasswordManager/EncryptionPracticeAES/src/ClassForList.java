import java.io.Serializable;

public class ClassForList implements Serializable {
    public String name;
    public String class_;
    public int age;

    public ClassForList(String m_name, String m_class, int m_age){
        this.name = m_name;
        this.class_ = m_class;
        this.age = m_age;
    }
}
