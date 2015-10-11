/*
 * This is the server side java file. We implement PlanBeanRemote here to 
 * communicate with the SLIT-common which then again communicates with
 * SLIT-client, where all our GUI and java files are in. 
 */
package slit.ejb.plan;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import slit.entities.plan.Plan;

/**
 *
 * @author Jorgen
 */
@Stateless
public class PlanBean implements PlanBeanRemote {
    
    //Retrieving data from db to ejb
    //Add EntityManager field in an ejb, add @PersistenceContext annotation
    @PersistenceContext
    private EntityManager em;
    
    
    //These methods are overriden from PlanBeanRemote with sigs.
    @Override
    public String testTheEJB(String input) {
        return "The input was: " + input;
    }
    
    //Something we did with Even to test our code
    @Override
    public void testDataSource() {
        TypedQuery<Plan> query 
                = em.createNamedQuery("select p from Plan p", 
                        Plan.class);
        List<Plan> plans = query.getResultList();
        System.out.println(""+plans.size()+ " hits");
        for (Plan p : plans){
            System.out.println(p);
        }
    }
}
