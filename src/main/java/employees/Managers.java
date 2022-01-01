package employees;

import facade.Facade;

public class Managers extends Employees {
    private String degrees;

    //manager constructor
    public Managers(String employeeID, String name, double grossSalary, String degree) {
        super(employeeID, name, grossSalary);
        this.degrees = degree;
    }

    // was private final Object degrees
    @Override
    public double getGrossSalary() {
        String x = this.degrees;
        double grossSalary = 0;
        if (x.equals("BSc")) {
            grossSalary = super.getRawSalary() * 1.10;

        } else if (x.equals("MSc")) {
            grossSalary = super.getRawSalary() * 1.20;

        } else if (x.equals("PhD")) {
            grossSalary = super.getRawSalary() * 1.35;

        }
        return Facade.truncate(grossSalary,2);}


    public String getDegrees() {
        return this.degrees;
    }

    public void setDegrees(String degrees) {this.degrees = degrees;}

    @Override
    public double getNetSalary() {
        return getGrossSalary() - (getGrossSalary() * 0.1);
    }

    //to string method
    @Override
    public String toString() {
        return getDegrees() +" "+ super.getName() + "'s gross salary is " + String.format("%.2f",getGrossSalary()) + " SEK per month.";
    }
}
