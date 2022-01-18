package assgn_2_group_26;

import java.util.ArrayList;
import javax.swing.Spring;
import java.util.*;

/**
 * This class stores the information of a students registration to 
 * a course in a university/college
 *
 * @author (Alireza Rahnama Maharlooei)
 * @author (Muhammad Bilal Naseer)
 * @author (Sean Walsh)
 * @version (October 12, 2021.)
 */
public class Registration
{
    // instance variables - replace the example below with your own
    private String courseNumber;
    private int section;
    private int timeSlot;
    private String student;
    private String professor;
    private ArrayList<String> List1;
    private boolean A;

    /**
     * Constructor for objects of class Registration
     */
    public Registration(String classNumber, int courseSection, int slot, 
                      String studentName,String professorName) throws Exception
    {
        // initialise instance variables, throws exception if the course number
        // slot or section are entered incorrectly
        if(verifyCourseNumber(classNumber) && verifySection(courseSection) && verifySlot(slot)) {
            courseNumber=classNumber;
            section=courseSection;
            timeSlot=slot;
            student=studentName;
            professor=professorName;
        } else {
            if(!verifyCourseNumber(classNumber))
            throw new Exception("Course number must be valid.");
            if(!verifySection(courseSection))
            throw new Exception("Section must be 0,1 or 2");
            if(!verifySlot(slot))
            throw new Exception("Slot must be greater than 11 and less than 26");
        }
    }
    
    
    public String getCourseNumber(){
        return courseNumber;
    }
    
    public String getStudent(){
        return student;
    }
    
    public String getProfessor(){
        return professor;
    }
    
    public int getSection(){
        return section;
    }
    
    public int getSlot(){
        return timeSlot;
    }
    
    public boolean verifyCourseNumber(String courseNumber)
    {
        List<String> List1 = new ArrayList<String>();
        Collections.addAll(List1,"COMP 1501", "ENGL 2201", "CHEM 3060", "PHYS 2344", "ENGL 2005", "COMP 2704", "PHYS 2377", "CHEM 4927", "COMP 3444");
        int index=0;
        while(index<List1.size()){
            if(courseNumber.equals(List1.get(index)))
            { 
                A= true;
                return A;
            }else{
                A= false;
            }
            index++; 
        }
            return A;
        
    }
    
      public boolean verifySection(int section)
    {
        if(section>=0 && section<=2){
        return true;
    }else{
        return false;
    } 
    }
    
    public boolean verifySlot(int slot)
    {
        if(slot>=12 && slot<=25){
        A = true;
    }else{
        A=false;
    } 
    return A;
    
    }

    public void printInfo()
    {
       // print all the information about the enrollment record
       // each field on a new line
       System.out.println("Student: "+getStudent());
       System.out.println("Course Number: "+getCourseNumber());
       System.out.println("Professor: "+getProfessor());
       System.out.println("Section: "+getSection());
       System.out.println("Time slot: "+getSlot());
    }
    
}
