package employees;

import employees.Employees;

public class Interns extends Employees {
    private int gpa;
    //intern constructor
    public Interns(String employeeID, String name, double grossSalary, int gpa) {
        super(employeeID, name, grossSalary);
        this.gpa = gpa;
    }

    //getters and setters
    @Override
    public double getGrossSalary() {
        this.finalGrossSalary = 0;
        if (this.gpa>5&&this.gpa<8){
            this.finalGrossSalary = super.getRawSalary();
        }else if(this.gpa>8){
            this.finalGrossSalary = super.getRawSalary()+1000;
        }
        return this.finalGrossSalary;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
        getGrossSalary();
    }

    @Override
    public double getNetSalary() {return getGrossSalary();}

    //to string method
    @Override
    public String toString() {
        return getName() + "'s gross salary is " + String.format("%.2f",getGrossSalary()) + " SEK per month. GPA: " + this.gpa;}
}
