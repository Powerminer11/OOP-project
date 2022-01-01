package employees;

import facade.Facade;

public class Directors extends Managers {
    private String department;

    //director constructor
    public Directors(String employeeID, String name, double grossSalary, String degree, String department) {
        super(employeeID, name, grossSalary, degree);
        this.department = department;
    }

    //getters and setters
    @Override
    public double getGrossSalary() {
        return super.getGrossSalary()+5000;
    }

    @Override
    public double getNetSalary() {
        double newNetSalary = 0;
        if (getGrossSalary()<30000){
            newNetSalary = getGrossSalary()*0.90;
        }else if (getGrossSalary()>=30000 && getGrossSalary()<50000){
            newNetSalary = getGrossSalary()*0.80;
        }else {
            double holder = (getGrossSalary()-30000)*0.60;
            newNetSalary = holder+(30000*0.80);
        }
        return Facade.truncate(newNetSalary,2);
    }

    public void setDepartment(String department) {this.department = department;}

    public String getDepartment() {return this.department;}

    //to string method
    @Override
    public String toString() {
        return getDegrees() + " " + getName() +"'s gross salary is " + String.format("%.2f",getGrossSalary()) +" SEK per month. Dept: " + this.department;
    }
}
