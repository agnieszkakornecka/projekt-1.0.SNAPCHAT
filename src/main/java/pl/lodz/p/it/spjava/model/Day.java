package pl.lodz.p.it.spjava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DAY", schema = "APP")
public class Day implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "FIRST", nullable = false)
	private String first;

	@Column(name = "SECOND", nullable = false)
	private String second;

	@Column(name = "DESSERT", nullable = false)
	private String dessert;

	@ManyToOne
	@JoinColumn(name="WEEK_ID")
	private Week week;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public void setFirst(String first) {
		this.first = first;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public void setDessert(String dessert) {
		this.dessert = dessert;
	}

	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}

	public String getDessert() {
		return dessert;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id : ").append(getId());
		strBuff.append(", first : ").append(getFirst());
		strBuff.append(", second : ").append(getSecond());
		strBuff.append(", dessert : ").append(getDessert());
		return strBuff.toString();
	}

}
