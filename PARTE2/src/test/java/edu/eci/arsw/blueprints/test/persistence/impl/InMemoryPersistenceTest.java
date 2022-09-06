/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            System.out.println(ex.getMessage());
        }
    }

    //---------------------------------- CREATE TEST

    @Test
    public void getBluePrintNotNullAndEquals() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts1=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp1=new Blueprint("john", "thepaint1", pts1);
        ibpp.saveBlueprint(bp1);

        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint2",pts2);
        ibpp.saveBlueprint(bp2);

        assertNotNull("No deberia ser nullo, ya que existe este elemento.",ibpp.getBlueprint(bp1.getAuthor(),bp1.getName()));
        assertNotNull("No deberia ser nullo, ya que existe este elemento.",ibpp.getBlueprint(bp2.getAuthor(),bp2.getName()));

        assertNotEquals("No deberia ser iguales porque son dos Blueprint diferentes.", ibpp.getBlueprint(bp1.getAuthor(),bp1.getName()), bp2);

        assertEquals("Deben ser Iguales.", ibpp.getBlueprint(bp1.getAuthor(),bp1.getName()), bp1);
        assertEquals("Deben ser Iguales.", ibpp.getBlueprint(bp2.getAuthor(),bp2.getName()), bp2);
    }
    @Test
    public void getBluePrintShouldBeNull() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts1=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp1=new Blueprint("john", "thepaint#1", pts1);
        ibpp.saveBlueprint(bp1);

        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("Lenny", "thepaint#2",pts2);
        ibpp.saveBlueprint(bp2);

        assertNull("Deben ser Nullo, no existe ese Blueprint",ibpp.getBlueprint(bp1.getAuthor(),bp2.getName()));
        assertNull("Deben ser Nullo, no existe ese Blueprint",ibpp.getBlueprint(bp2.getAuthor(),bp1.getName()));
    }

    @Test
    public void getBluePrintByAuthorShouldReturnBluePrintSet() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Set<Blueprint> setBpOriginal =  new HashSet<>();
        //Author 1
        Point[] pts1 = new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp1=new Blueprint("john", "thepaint#11", pts1);
        Blueprint bp2=new Blueprint("john", "thepaint#12", pts1);
        Blueprint bp3=new Blueprint("john", "thepaint#13", pts1);

        setBpOriginal.add(bp1);
        setBpOriginal.add(bp2);
        setBpOriginal.add(bp3);

        ibpp.saveBlueprint(bp1);
        ibpp.saveBlueprint(bp2);
        ibpp.saveBlueprint(bp3);

        Set<Blueprint> setBluePrintAuthor = ibpp.getBlueprintsByAuthor(bp1.getAuthor());
        assertNotNull("No Deben ser Nullo",setBluePrintAuthor);
        assertEquals("Deben ser Iguales.", setBluePrintAuthor,setBpOriginal);
    }

    @Test
    public void getBluePrintByAuthorNotEquals() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Set<Blueprint> setBpOriginal =  new HashSet<>();
        //Author 1
        Point[] pts1 = new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp1=new Blueprint("john", "thepaint#11", pts1);
        Blueprint bp2=new Blueprint("john", "thepaint#12", pts1);
        Blueprint bp3=new Blueprint("john", "thepaint#13", pts1);

        setBpOriginal.add(bp1);
        setBpOriginal.add(bp2);
        setBpOriginal.add(bp3);

        ibpp.saveBlueprint(bp1);
        ibpp.saveBlueprint(bp2);
        ibpp.saveBlueprint(bp3);

        //Author 2
        Point[] pts1A2 = new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp1A2=new Blueprint("felipe", "thepaint#11", pts1A2);
        setBpOriginal.add(bp1A2);
        ibpp.saveBlueprint(bp1A2);

        Set<Blueprint> setBluePrintAuthor = ibpp.getBlueprintsByAuthor(bp1.getAuthor());
        assertNotNull("No Deben ser Nullo",setBluePrintAuthor);
        assertNotEquals("No Deben ser Iguales.", setBluePrintAuthor,setBpOriginal);
    }
}
