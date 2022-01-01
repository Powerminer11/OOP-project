package employees;

import facade.Facade;

public class Employees {
    private final String employeeID;
    private String name;
    protected double finalGrossSalary;
    private double netSalary;
    private double rawSalary;

    //employee constructor
    public Employees(String employeeID, String name, double grossSalary){
        this.employeeID = employeeID;
        this.name = name;
        this.rawSalary = grossSalary;
        this.netSalary = grossSalary - (grossSalary * 0.1);
        this.finalGrossSalary = Facade.truncate(grossSalary,2);
    }

    //getters and setters
    public String getEmployeeID() {return this.employeeID;}

    public String getName() {return this.name;}

    public double getGrossSalary() {return this.finalGrossSalary;}

    public double getNetSalary() {return this.netSalary;}

    public double getRawSalary() {return rawSalary;}

    public void setName(String name) {this.name = name;}


    public void setRawSalary(double rawSalary) {this.rawSalary = rawSalary;}

    //to string method
    @Override
    public String toString() {
        return this.name + "'s gross salary is " + String.format("%.2f",this.rawSalary) + " SEK per month.";
    }
}
