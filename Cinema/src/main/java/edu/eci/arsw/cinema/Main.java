package edu.eci.arsw.cinema;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {           

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CinemaServices cs = ac.getBean(CinemaServices.class);                        

        String functionDate = "2018-12-18 15:30";
        String functionDate2 = "2018-12-18 15:00";
        List<CinemaFunction> functions= new ArrayList<>();
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        CinemaFunction funct3 = new CinemaFunction(new Movie("The last laugh","Comedy"),functionDate);
        CinemaFunction funct4 = new CinemaFunction(new Movie("Friends with benefits","Romance"),functionDate);
        CinemaFunction funct5 = new CinemaFunction(new Movie("Raiders of the Lost Ark","Adventure"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        functions.add(funct3);
        functions.add(funct4);
        functions.add(funct5);
        CinemaFunction funct11 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate2);
        CinemaFunction funct21 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate2);
        CinemaFunction funct31 = new CinemaFunction(new Movie("The last laugh","Comedy"),functionDate2);
        functions2.add(funct11);
        functions2.add(funct21);
        functions2.add(funct31);
        
        Cinema c1=new Cinema("Movies Bogotá",functions);
        Cinema c2=new Cinema("Movies Medellin", functions2);
        
        try {
            /*-------- Register --------*/
            cs.addNewCinema(c1);
            System.out.println("Cinema added");
            /*-------- Consult --------*/
            System.out.println("Cinema name: " + cs.getCinemaByName(c1.getName()).getName());
            for (CinemaFunction cinemaFunction : cs.getFunctionsbyCinemaAndDate(c1.getName(), functionDate)) {
                System.out.println("Movie: " + cinemaFunction.getMovie().getName() +" Genre "+ cinemaFunction.getMovie().getGenre());
            }            
            /*-------- Buy / Book tickets --------*/
            cs.buyTicket(0, 0, c1.getName(), functionDate, "Friends with benefits");            
            cs.buyTicket(1, 1, c1.getName(), functionDate, "Friends with benefits");
            for (CinemaFunction cinemaFunction : cs.getFunctionsbyCinemaAndDate(c1.getName(), functionDate)) {
                if (cinemaFunction.getMovie().getName().equals("Friends with benefits")){
                    int x = 0;
                    for (List<Boolean> seat : cinemaFunction.getSeats()) { 
                        int y = 0;
                        for (Boolean boolean1 : seat) {                            
                            System.out.println("x,y: "+ x +","+ y +" "+ boolean1);
                            y++;
                        }
                        x++;
                    }
                }
            }
            Map<String, Cinema> temp = cs.getAllCinemas();
            for (Map.Entry<String, Cinema> entry : temp.entrySet()) {
                String key = entry.getKey();
                Cinema value = entry.getValue();
                System.out.println("key: "+ key + " value" + value);
            }
        } catch (CinemaPersistenceException e) {
            System.out.println("Fail to add a cinema");
        } catch (CinemaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            /*-------- Register --------*/
            cs.addNewCinema(c2);
            System.out.println("Cinema added");
            /*-------- Consult --------*/
            System.out.println("Cinema name: " + cs.getCinemaByName(c2.getName()).getName());
            for (CinemaFunction cinemaFunction : cs.getFunctionsbyCinemaAndDate(c2.getName(), functionDate2)) {
                System.out.println("Movie: " + cinemaFunction.getMovie().getName() +" Genre"+ cinemaFunction.getMovie().getGenre());
            }            
            /*-------- Buy / Book tickets --------*/
            cs.buyTicket(5, 7, c2.getName(), functionDate2, "The last laugh");            
            cs.buyTicket(3, 6, c2.getName(), functionDate2, "The last laugh");
            for (CinemaFunction cinemaFunction : cs.getFunctionsbyCinemaAndDate(c2.getName(), functionDate2)) {
                if (cinemaFunction.getMovie().getName().equals("The last laugh")){
                    int x = 0;
                    for (List<Boolean> seat : cinemaFunction.getSeats()) { 
                        int y = 0;
                        for (Boolean boolean1 : seat) {                            
                            System.out.println("x,y: "+ x +","+ y +" "+ boolean1);
                            y++;
                        }
                        x++;
                    }
                }
            }
            Map<String, Cinema> temp = cs.getAllCinemas();
            for (Map.Entry<String, Cinema> entry : temp.entrySet()) {
                String key = entry.getKey();
                Cinema value = entry.getValue();
                System.out.println("key: "+ key + " value " + value);
            }
        } catch (CinemaPersistenceException e) {
            System.out.println("Fail to add a cinema");
        } catch (CinemaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }                        
    }        
}
