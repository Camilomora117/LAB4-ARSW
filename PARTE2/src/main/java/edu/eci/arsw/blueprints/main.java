package edu.eci.arsw.blueprints;


import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main{

    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException {
        //Instanciamos
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices serviceBluePrint = appContext.getBean(BlueprintsServices.class);

        //Add
        serviceBluePrint.addNewBlueprint(new Blueprint("Santiago","blueprintSantiago"));
        System.out.println(serviceBluePrint.getBlueprintsByAuthor("Santiago"));
        //
        Point[] points= new Point[] {new Point(1,2),new Point(3,4),new Point(1,2)};
        serviceBluePrint.addNewBlueprint(new Blueprint("Santiago","bp3",points));
        System.out.println(serviceBluePrint.getAllBlueprints());

    }

}
