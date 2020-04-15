package xyz.jguru.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import xyz.jguru.hibernate.demo.entity.Student;

import java.util.Set;

public class CreateStudentImagesSetDemo
{
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .addAnnotatedClass(Student.class)
                                                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            //create object
            //Student tempStudent = new Student("Paul","Wall","paul@jguru.xyz");
            Student tempStudent = new Student("John","Doe","john@jguru.xyz");
            Set<String> theimages = tempStudent.getImages();

            theimages.add("photo1.jpg");
            theimages.add("photo2.jpg");
            theimages.add("photo3.jpg");
            theimages.add("photo4.jpg");
            theimages.add("photo4.jpg"); // Duplicated
            theimages.add("photo5.jpg");
            theimages.add("photo5.jpg"); // Duplicated


            //start a transaction
            session.beginTransaction();

            //save the object
            System.out.println("Saving the student and images ...");
            session.persist(tempStudent);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!!");


        } finally {
            //cleanup code
            session.close();
            factory.close();

        }
    }
}
