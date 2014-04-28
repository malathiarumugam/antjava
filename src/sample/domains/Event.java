package sample.domains;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic
    private String title;

    @Basic
    @Column(name = "datevalue", nullable = false)
    private Date date;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}