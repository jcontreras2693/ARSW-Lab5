package edu.eci.arsw.blueprints;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Set;


@SpringBootApplication
public class Main implements CommandLineRunner{

    @Autowired
    BlueprintsServices bps;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String ... args) throws BlueprintNotFoundException{
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = appContext.getBean(BlueprintsServices.class);
        Point[] points = new Point[] { new Point(0, 0), new Point(0, 0), new Point(10, 10), new Point(10, 10), new Point(20, 20), new Point(0, 20), new Point(20, 0), };

        Blueprint currentBlueprint = new Blueprint("Juan", "house1", points);
        bs.addNewBlueprint(currentBlueprint);
        bs.addNewBlueprint(new Blueprint("David", "house2"));
        bs.addNewBlueprint(new Blueprint("Contreras", "house3"));

        Set<Blueprint> author = bs.getBlueprintsByAuthor(currentBlueprint.getAuthor());

        System.out.println("getCurrentBluePrintByAuthor");
        for (Blueprint b : author) {
            System.out.println(b);
        }

    }

}
