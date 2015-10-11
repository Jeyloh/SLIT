/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import slit.ejb.plan.PlanBeanRemote;

/**
 *
 * @author Jorgen
 */
public class MainWindow extends Application {
    
    //For the client to connect with EJB we import ejb.EJB and the PlanBeanRemote
    //from SLIT-common (see line 28 in this file).
    @EJB
    //We create a variable called planBean that is of the type PlanBeanRemote
    private static PlanBeanRemote planBean;
    public static String EJB_JNDI_NAME = "java:global/SLIT/SLIT-ejb/PlanBean!slit.ejb.plan.PlanBeanRemote";
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SLIT");
                
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        //Lambda expression used instead of anonymous inner class
        btn.setOnAction((ActionEvent e) -> {
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setText("Sign in button pressed");
        });
      
        
        //connecting to the bean? idk
        if (planBean == null) {
            try {
                planBean = (PlanBeanRemote) new InitialContext().lookup(EJB_JNDI_NAME);                       
            }
            catch (NamingException ne) {
                ne.printStackTrace();
            }
        }
        
        System.out.println(planBean.testTheEJB("Hei"));
        planBean.testDataSource();
        
        
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
        
        
        Scene scene = new Scene(grid, 1600, 900);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
