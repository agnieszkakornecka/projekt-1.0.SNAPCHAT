package pl.lodz.p.it.spjava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MEAL_TYPE", schema = "APP")
public class MealType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TYPE", unique = true, nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("type_id : ").append(getId());
        strBuff.append(", type : ").append(getType());
        return strBuff.toString();
    }
}
