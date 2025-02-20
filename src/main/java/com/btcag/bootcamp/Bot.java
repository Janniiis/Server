package com.btcag.bootcamp;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "Robots")
public class Bot {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "health", nullable = false)
    private BigDecimal health;
    @Column(name = "attackDamage", nullable = false)
    private BigDecimal attackDamage;
    @Column(name = "attackRange", nullable = false)
    private BigDecimal attackRange;
    @Column(name = "movementRate", nullable = false)
    private BigDecimal movementRate;

    public Bot() {}

    public Bot(String name, BigDecimal health, BigDecimal attackDamage, BigDecimal attackRange, BigDecimal movementRate) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.movementRate = movementRate;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(BigDecimal health) {
        this.health = health;
    }

    public BigDecimal getHealth() {
        return health;
    }

    public BigDecimal getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(BigDecimal attackDamage) {
        this.attackDamage = attackDamage;
    }

    public BigDecimal getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(BigDecimal attackRange) {
        this.attackRange = attackRange;
    }

    public BigDecimal getMovementRate() {
        return movementRate;
    }

    public void setMovementRate(BigDecimal movementRate) {
        this.movementRate = movementRate;
    }
}
