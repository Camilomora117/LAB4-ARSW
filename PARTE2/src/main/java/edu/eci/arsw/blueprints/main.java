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
        System.out.println(serviceBluePrint.getBlueprint("Yesid","thepaint#4"));

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
    }

}


