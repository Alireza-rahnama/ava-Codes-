package assgn_2_group_26;

import java.util.ArrayList;
import java.util.*;

/**
 * This class stores student registrations for a university/college
 * and allows for easy filtering of the data with the methods provided
 * 
 * @author (Alireza Rahnama Maharlooei)
 * @author (Muhammad Bilal Naseer)
 * @author (Sean Walsh)
 * @version (October 12, 2021.)
 */
public class CollegeRegistrar
{
    private ArrayList<Registration> Registrations;
    private Registration newRegistration;
    

    /**
     * Constructor for objects of class CollegeRegistrar
     */
    public CollegeRegistrar()
    {
         Registrations = new ArrayList<Registration>();           
    }

    
    public void addRegistration(Registration newRegistration)
    {
        // adds new registrations to the list
        Registrations.add(newRegistration);
        
    }
    
    public void printAllRegistrations()
    {
        // prints for all registrations the corresponding info
        // registration records are separated by an empty line
        Registrations.forEach((newRegistration) ->{
            newRegistration.printInfo();
        });
    }
    
    public void printClassesForStudent(String student)
    {
        // prints all classes (course number and professor) in which the 
        // student is enrolled
        // one class per line
        
        Registrations.stream().filter(s -> student.equals(s.getStudent())).forEach(s -> 
        
            System.out.println("Course Number: "+s.getCourseNumber() + " Professor: "+s.getProfessor()));
    }
    
    public int getClassCountForStudent(String student)
    {
        // number of classes in which the student is enrolled
        long counts = Registrations.stream().filter(s->student.equals(s.getStudent())).count();
            int i = Math.toIntExact(counts);
            return i;
    }
    
    public void printClassesForStudentByProfessor(String student, String professor)
    {
        // prints all classes (course number and section) 
        // tought by professor and taken by student
        // one class per line
        Registrations.stream().filter(s ->
        student.equals(s.getStudent()) && professor.equals(s.getProfessor())).forEach(s ->
        System.out.println("Course Number: "+s.getCourseNumber()+" Section: "+s.getSection())); 
    }
    
    public void printClassesForStudentInSlot(String student, int timeSlot)
    {
        // prints all classes (course number and section)
        // taken by student in timeSlot
        // one class per line
        Registrations.stream().filter(s -> student.equals(s.getStudent()) &&
        timeSlot == s.getSlot()).forEach(s -> System.out.println(
        "Course Number: "+s.getCourseNumber()+" Section: "+s.getSection()));
    }
    
    public int getRegistrationsInClass(String classNumber)
    {
        // get the number of students registered in classNumber
        long counts = Registrations.stream().filter(s -> classNumber.equals(s.getCourseNumber())).count();
            int i = Math.toIntExact(counts);
            return i;
    }
    
    public int getRegistrationsInClass(String classNumber, int timeSlot)
    {
        // get the number of students enrolled in classNumber and slot
        long counts = Registrations.stream().filter(s->classNumber.equals(s.getCourseNumber())&&timeSlot==s.getSlot()).count();
            int i = Math.toIntExact(counts);
            return i;
    }
    
    public int studentsTakingClassesInSlot(int timeSlot)
    {
        // get the number of students taking classes in timeSlot
        long counts = Registrations.stream().filter(s->timeSlot == s.getSlot()).count();
            int i = Math.toIntExact(counts);
            return i;
    }
    
    public void dropClassForStudent(String classNumber, String student)
    {
        // drops class classNumber for student
        Registrations.removeIf(s -> 
            student.equals(s.getStudent()) && classNumber.equals(s.getCourseNumber()));
    }
    
    public void dropAllClassesForStudent(String student)
    {
        // drops all classes for student
        Registrations.removeIf(s ->
            student.equals(s.getStudent()));
    }
    
    public void populateRegistrations() throws Exception
    {
        //This method creates 30 arbitrary registrations for testing
        List<String> List1 = new ArrayList<String>();
        Collections.addAll(List1,"COMP 1501", "ENGL 2201", "CHEM 3060", "PHYS 2344", "ENGL 2005", "COMP 2704", "PHYS 2377", "CHEM 4927", "COMP 3444");
        int index=0;
        while(index<30){
            String classNo = List1.get(index%List1.size());
            int slot = index%9 + 12; // Time slot must be between 12 and 25.
            int section = index%3; // Section must be 0, 1, or 2.
            String student = "student"+index%10; // 10 students.
            String professor = "professor"+index%3; //3 professors.
            Registrations.add(new Registration(classNo,section,slot,student,professor));
            index++;
        }
    }
}
