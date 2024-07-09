import java.io.Serializable;

public class Student implements Serializable {
    String name;
    int seat;

    Student(String m_name,int m_seat ){
        this.name = m_name;
        this.seat = m_seat;
    }

    public String getSeat() {
        return Integer.toString(seat);
    }

    public String getName() {
        return name;
    }
}
