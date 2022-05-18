/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanresources;

/**
 *
 * @author Rodrigo Santos
 * @lastmod 2022-05-06
 */
public class Manager extends Employee {

    private double bonus;

    public Manager(String name, int code, Date entryDate) {
        super(name, code, entryDate, "Gestor");
        this.bonus = 0.15;
    }

    public double getBonus() {
        return this.bonus;
    }
}