package com.kaishengit.pojo;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "t_card")
public class Card {

    @Id
    @GenericGenerator(name = "FK",strategy = "foreign",parameters = {
           @Parameter(name = "property",value = "person")
    })
    @GeneratedValue(generator = "FK")
    private Integer id;
    private String cardname;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
}
