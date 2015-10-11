/*
 * The remote file in the SLIT-Common are used to connect the server side (SLIT-ejb) and 
 * the client side (SLIT-client). 
 */
package slit.ejb.plan;

import javax.ejb.Remote;

/**
 *
 * @author Jorgen
 */
@Remote
public interface PlanBeanRemote {
    
    public String testTheEJB(String input);
     
   public void testDataSource();
    
}
