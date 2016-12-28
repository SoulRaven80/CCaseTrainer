package com.soulraven.ccase.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.awt.Point;

@Entity
@Table(name = "clues")
public class Clue {

    @Id
    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "clue_id", nullable=false)
    private long id;
    @Column(name = "level", nullable=false)
    private int level;
    @Column(name = "text", nullable=false)
    private String text;
    @Column(name = "location", nullable=true)
    private Point location;

    public Clue(int level, String text) {
        this.level = level;
        this.text = text;
    }

    public Clue(int level, String text, Point location) {
        this.level = level;
        this.text = text;
        this.location = location;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clue)) return false;
        Clue clue = (Clue) o;
        return new EqualsBuilder()
                .append(level, clue.level)
                .append(text, clue.text)
                .append(location, clue.location)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(level)
                .append(text)
                .append(location)
                .toHashCode();
    }

    @Override
    public String toString() {
        return text + ", " + ((location == null) ? "? : ?" : location.x + " : " + location.y);
    }
}
