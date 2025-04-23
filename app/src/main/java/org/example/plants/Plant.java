package org.example.plants;

import org.example.engine.ThreadManager;

public abstract class Plant implements Runnable {
    private int health;
    private int damage;
    private int cost;
    private int cooldown;
    private final Thread thread;
    private boolean isAlive;

    public Plant() {
        this.health = 100;
        this.damage = 10;
        this.cost = 0;
        this.cooldown = 0;
        this.isAlive = true;
        this.thread = new Thread(this);
        ThreadManager.registerThread(thread); // Registrar el hilo en el ThreadManager
        thread.start();
    }

    public Plant(int health, int damage, int cost, int cooldown) {
        this.health = health;
        this.damage = damage;
        this.cost = cost;
        this.cooldown = cooldown;
        this.isAlive = true;
        this.thread = new Thread(this);
        thread.start();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.err.println(
                getClass().getSimpleName() + " ha recibido " + damage + " de daño. Salud restante: " + this.health);
        if (this.health <= 0) {
            this.isAlive = false;
            onDeath();
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void onDeath() {
        isAlive = false;
        stopThread();
        System.out.println(getClass().getSimpleName() + " ha muerto.");

    }

    private void stopThread() {
        thread.interrupt();
    }

    @Override
    public abstract void run();

}
