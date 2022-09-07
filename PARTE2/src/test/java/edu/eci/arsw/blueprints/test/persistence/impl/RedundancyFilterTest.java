package edu.eci.arsw.blueprints.test.persistence.impl;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.Filter;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.persistence.impl.Redundancy;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RedundancyFilterTest extends TestCase {

    @Test
    public void testFilterRedundancy() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new Redundancy();
        //Blue print
        Point[] pts=new Point[]{new Point(10, 10),new Point(10, 10), new Point(11, 10), new Point(10, 10)};
        Blueprint bp=new Blueprint("camilo", "thepaint",pts);
        ibpp.saveBlueprint(bp);
        //Get
        Blueprint blueprintRes = ibpp.getBlueprint("camilo", "thepaint");
        blueprintRes = bpf.filterPoints(blueprintRes);
        Point[] ptsRes =new Point[]{new Point(10, 10)};
        //testing results
        System.out.println(blueprintRes.getPoints());
        System.out.println(ptsRes);
        assertNotNull("Loading a points stored blueprint returned not null.", blueprintRes.getPoints());
        assertEquals("Points filtered by Redundancy", blueprintRes.getPoints().size(), 3);
    }

    @Test
    public void testFilterRedundancyPar() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new Redundancy();
        //Blue print
        Point[] pts=new Point[]{new Point(10, 10),new Point(10, 10), new Point(10, 10), new Point(10, 10)};
        Blueprint bp=new Blueprint("camilo", "thepaint",pts);
        ibpp.saveBlueprint(bp);
        //Get
        Blueprint blueprintRes = ibpp.getBlueprint("camilo", "thepaint");
        blueprintRes = bpf.filterPoints(blueprintRes);
        Point[] ptsRes =new Point[]{new Point(10, 10)};
        //testing results
        System.out.println(blueprintRes.getPoints());
        System.out.println(ptsRes);
        assertNotNull("Loading a points stored blueprint returned not null.", blueprintRes.getPoints());
        assertEquals("Points filtered by Redundancy", blueprintRes.getPoints().size(), 1);
    }

    @Test
    public void testFilterRedundancyImPar() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new Redundancy();
        //Blue print
        Point[] pts=new Point[]{new Point(10, 10),new Point(11, 11), new Point(10, 10)};
        Blueprint bp=new Blueprint("camilo", "thepaint",pts);
        ibpp.saveBlueprint(bp);
        //Get
        Blueprint blueprintRes = ibpp.getBlueprint("camilo", "thepaint");
        blueprintRes = bpf.filterPoints(blueprintRes);
        Point[] ptsRes =new Point[]{new Point(10, 10)};
        //testing results
        System.out.println(blueprintRes.getPoints());
        System.out.println(ptsRes);
        assertNotNull("Loading a points stored blueprint returned not null.", blueprintRes.getPoints());
        assertEquals("Points filtered by Redundancy", blueprintRes.getPoints().size(), 3);
    }

}
