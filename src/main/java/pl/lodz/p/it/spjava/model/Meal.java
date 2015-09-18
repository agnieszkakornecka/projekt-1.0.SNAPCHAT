package pl.lodz.p.it.spjava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MEAL", schema = "APP")
public class Meal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    private MealType mealType;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "MEAL_POSITION", nullable = false)
    private String mealPosition;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(final MealType mealType) {
        this.mealType = mealType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealPosition() {
        return mealPosition;
    }

    public void setMealPosition(String mealPosition) {
        this.mealPosition = mealPosition;
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", typeMeal : ").append(getMealType());
        strBuff.append(", name : ").append(getName());
        return strBuff.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;

        Meal meal = (Meal) o;

        if (id != null ? !id.equals(meal.id) : meal.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
