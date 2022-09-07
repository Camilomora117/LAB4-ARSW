package edu.eci.arsw.blueprints;


import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class main{

    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException {
        //Instanciamos
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices serviceBluePrint = appContext.getBean(BlueprintsServices.class);

        //Add
        Point[] ptsbp = new Point[]{new Point(0, 0), new Point(10, 10)};
        Blueprint blueprint = new Blueprint("Santiago", "thepaint#1", ptsbp);
        serviceBluePrint.addNewBlueprint(blueprint);
        System.out.println("Metodo ADD NEW BLUE PRINT");
        System.out.println(serviceBluePrint.getBlueprintsByAuthor("Santiago"));

        //All Blue Prints
        serviceBluePrint.addNewBlueprint(new Blueprint("Camilo", "thepaint#2", ptsbp));
        serviceBluePrint.addNewBlueprint(new Blueprint("Enrique", "thepaint#3", ptsbp));
        System.out.println("Metodo GET ALL BLUE PRINTS");
        System.out.println(serviceBluePrint.getAllBlueprints());

        //Get Blue Print
        serviceBluePrint.addNewBlueprint(new Blueprint("Diego", "thepaint#4", ptsbp));
        System.out.println("Metodo GET BLUE PRINT");
        System.out.println(serviceBluePrint.getBlueprint("Diego","thepaint#4"));

        //Get Blue Prints by Author
        serviceBluePrint.addNewBlueprint(new Blueprint("Yesid", "thepaint#5", ptsbp));
        serviceBluePrint.addNewBlueprint(new Blueprint("Yesid", "thepaint#6", ptsbp));
        serviceBluePrint.addNewBlueprint(new Blueprint("Yesid", "thepaint#7", ptsbp));
        System.out.println("Metodo GET BLUE PRINTS BY AUTHOR");
        Set<Blueprint> authorBP = serviceBluePrint.getBlueprintsByAuthor("Yesid");
        System.out.println("get Por Autor: Yesid");
        for (Blueprint b: authorBP) {
            System.out.println(b);
        }

        //Get Blue print con filter Redundancy
        System.out.println("Metodo GET BLUE PRINT CON FILTER REDUNDANCY");
        Point[] ptsFilter = new Point[]{new Point(10, 10), new Point(10, 10), new Point(10,10), new Point(10,10)};
        serviceBluePrint.addNewBlueprint(new Blueprint("Edgar", "thepaint#8", ptsFilter));
        Blueprint blueprint1 = serviceBluePrint.getBlueprint("Edgar","thepaint#8");
        for (Point p: blueprint1.getPoints()) {
            System.out.println("Punto: " + p);
        }

        //Get Blue prins by author con filter Redundancy
        System.out.println("Metodo GET BLUE PRINTS BY AUTHOR CON FILTER REDUNDANCY");
        Point[] ptsFilter1 = new Point[]{new Point(10, 11), new Point(11, 10), new Point(11,10)};
        Point[] ptsFilter2 = new Point[]{new Point(11, 10), new Point(11, 10), new Point(10,10)};
        serviceBluePrint.addNewBlueprint(new Blueprint("Rozo", "thepaint#9", ptsFilter1));
        serviceBluePrint.addNewBlueprint(new Blueprint("Rozo", "thepaint#10", ptsFilter2));
        Set<Blueprint> setbp = serviceBluePrint.getBlueprintsByAuthor("Rozo");
        for (Blueprint bp: setbp) {
            System.out.println("Blue Print " + bp.getName());
            for (Point p: bp.getPoints()) {
                System.out.println("Punto: " + p);
            }
        }

        //Get Blue print con filter UnderSampling
        System.out.println("Metodo GET BLUE PRINT CON FILTER UnderSampling");
        Point[] ptsFilterUS = new Point[]{new Point(12, 11), new Point(131, 10), new Point(14,10), new Point(10,10)};
        serviceBluePrint.addNewBlueprint(new Blueprint("EdgarUS", "thepaintUS#8", ptsFilterUS));
        Blueprint blueprint1US = serviceBluePrint.getBlueprint("EdgarUS","thepaintUS#8");
        for (Point p: blueprint1US.getPoints()) {
            System.out.println("Punto: " + p);
        }

        //Get Blue prins by author con filter UnderSampling
        System.out.println("Metodo GET BLUE PRINTS BY AUTHOR CON FILTER UnderSampling");
        Point[] ptsFilter1US = new Point[]{new Point(10, 11)};
        Point[] ptsFilter2US = new Point[]{new Point(11, 10), new Point(10,10), new Point(10,11)};
        serviceBluePrint.addNewBlueprint(new Blueprint("RozoUS", "thepaintUS#9", ptsFilter1US));
        serviceBluePrint.addNewBlueprint(new Blueprint("RozoUS", "thepaintUS#10", ptsFilter2US));
        Set<Blueprint> setbpUS = serviceBluePrint.getBlueprintsByAuthor("RozoUS");
        for (Blueprint bp: setbpUS) {
            System.out.println("Blue Print " + bp.getName());
            for (Point p: bp.getPoints()) {
                System.out.println("Punto: " + p);
            }
        }
    }

}


