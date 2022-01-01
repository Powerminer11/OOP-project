package company;

import java.util.ArrayList;
import java.util.List;

public class Reviews {

    private int grade;
    private String comment;
    // Review constructor
    public Reviews(int grade, String comment){
        this.grade = grade;
        this.comment = comment;
    }


    //getters and setters
    public int getGrade() {return this.grade;}

    public String getComment() {return this.comment;}

    @Override
    public String toString() {
        return "Grade: " + getGrade() + "." + getComment() ;
    }
}