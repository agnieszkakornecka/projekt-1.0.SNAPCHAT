package pl.lodz.p.it.spjava.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "WEEK", schema = "APP")
public class Week implements Serializable, Comparable<Week> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "WEEK_NUMBER", nullable = false)
    private int weekNumber;

    @Column(name = "DATE_FROM", nullable = false)
    private Date dateFrom;

    @Column(name = "DATE_TO", nullable = false)
    private Date dateTo;

    @OneToMany(mappedBy = "week", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Day> days = new ArrayList<Day>();

    public void addDay(Day day) {
        day.setWeek(this);
        days.add(day);
    }

    public List<Day> getDays() {
        return days;
    }

    public Day getMonday() {
        return days.get(0);
    }

    public Day getTuesaday() {
        return days.get(1);
    }

    public Day getWednesday() {
        return days.get(2);
    }

    public Day getThursday() {
        return days.get(3);
    }

    public Day getFriday() {
        return days.get(4);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", weekNumber : ").append(getWeekNumber());
        strBuff.append(", dateFrom : ").append(getDateFrom());
        strBuff.append(", dateTo : ").append(getDateTo());
        return strBuff.toString();
    }

    @Override
    public int compareTo(Week that) {
        if (that == null) {
            return 1;
        }
        if (this.weekNumber > that.weekNumber) {
            return 1;
        }
        if (this.weekNumber < that.weekNumber) {
            return -1;
        }
        return 0;
    }
}
