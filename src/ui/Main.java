package ui;

import model.*;
import java.util.Scanner;
import static java.lang.System.*;

/**
 * Declaration of Main class.
 * This class is encharged of the interaction with the user.
 * Requests information of the user and uses the validation provided by the controller class. 
 * @author Sebastián García Acosta
 * @version 1.2
 */

public class Main {
    private static MaximaSuperior controller;
    private static Scanner sc_str, sc_num;

    public Main(){
        controller = new MaximaSuperior(); 
        sc_str = new Scanner(System.in);
        sc_num = new Scanner(System.in);
    }
    
    public static void main(String[] args){
        Main program = new Main();
        out.println("WELCOME"); 
        out.print("Type the number of Archangels you want to add: ");
        int num_of_archs = sc_num.nextInt();
        program.setController(new MaximaSuperior(num_of_archs));
        int election = 1;
        while(election != 8){
            out.println("[----------------- MENU -----------------]");
            out.print(  "1). Add archangels"+ 
                        "\n2). Count added archangels"+
                        "\n3). Find archangel by its name"+
                        "\n4). Find archangel by its power"+
                        "\n5). Find all celebrations by month"+
                        "\n6). Show all celebrations"+
                        "\n7). Create power."+
                        "\n8). Exit "+
                        "\n[Response]: ");
            election = sc_num.nextInt();
            switch(election){
                case 1: 
                    program.requestInfoAndAddArchangel();
                    break;
                case 2: 
                    out.println("There are " + program.controller.countArchangels() + " archangels registered.");
                    break;
                case 3: 
                    program.findByName();
                    break;
                case 4: 
                    program.findByPowers();
                    break;
                case 5: 
                    program.findByMonth();
                    break;
                case 6: 
                    out.println(controller.showAllCelebs());
                    break;
                case 7:
                    program.createPowers();
                    break;
                case 8: 
                    out.println("Goodbye!");
                    break;
                default:
                    out.println("Type a valid answer");
                    break;                
            }
        }
    }

    /**
     * Requests powers to the user and validate them.
     * <b>post: </b> all the powers returned will meet the conditions specified in the controller class.
     * @return array of string containing the valid powers that user typed. 
     */
    public String[] requestPowers(){
        String[] powers = new String[0];
        boolean valid = false;
        while(!valid){
            out.print("Powers [Example: health, thriveness]: ");
            powers = sc_str.nextLine().split(",");
            if(controller.powersAreValid(powers)){
                valid = true;
            }
            else{
                out.println("RESPONSE: " + controller.message + "Try again:");   
            }
        }
        return powers;
    }

    /**
     * Request name to the user and validate it.
     * <b>post: </b> all the names registered will meet the conditions specified in the controller class.
     * @return string containing the valid name that user typed. 
     */
    public String requestName(){
        String name = "";
        boolean valid = false;
        while(!valid){
            out.print("Name [Example: Gabriel]: ");
            name = sc_str.nextLine();
            if(controller.nameIsValid(name)){
                valid = true;
            }
            else {
                out.println("RESPONSE: " + controller.message + "Try again:");        
            }
        }
        return name;
    }

// RF1
    /**
     * Requests the information needed to add a new archangel, displays information about the state of the operation. 
     *<b>post: </b> all the fields typed meet the conditions specified in the controller class.
     */
    public void requestInfoAndAddArchangel(){
//      Archangels attributes
        String name, prayer;
        String[] powers;
        int celeb_day, celeb_month;

//      Candle's Attributes
        String color, essence, size = "";
        double brightness_degree = -1;

        boolean isValidInfo = false;

        int position = controller.getNumArchangels() - controller.numOfAvailablePositions();

        out.println("YOU CAN ADD " + controller.numOfAvailablePositions() + " MORE ARCHANGELS.");
        if(controller.numOfAvailablePositions() > 0){
                out.println("ARCHANGEL NUMBER " + (position + 1));
                
                name = requestName();
                
                powers = requestPowers();

                out.print("Prayer: ");
                prayer = sc_str.nextLine();

                while(true){
                    out.print("Celebration day [Example: 8]: ");
                    celeb_day = sc_num.nextInt();
                    out.print("Celebration month [Example (december): 12]: ");
                    celeb_month = sc_num.nextInt();
                    if(controller.dateIsValid(celeb_day, celeb_month)){
                        break;
                    }
                    else{
                        out.println("RESPONSE: " + controller.message + "Try again: ");
                    }
                }
                   
                out.print("### Candle ###\n");
                out.print("Color: ");
                color = sc_str.nextLine();
                out.print("Essence: ");
                essence = sc_str.nextLine();
                
                boolean isValidSize = false;
                String election;
                char choice;
                while(!isValidSize){
                    out.print("Size [b: BIG/ s: small/ m: medium]: ");
                    election = sc_str.nextLine();
                    if(election.length()==1){
                        choice = election.charAt(0);
                        switch(choice){
                            case 'b':
                                size = SpecialCandle.BIG;
                                 isValidSize = true;   
                                break;
                            case 'm':
                                size = SpecialCandle.MEDIUM;
                                isValidSize = true;
                                break;
                            case 's':
                                size = SpecialCandle.SMALL;
                                isValidSize = true;
                                break;
                            default:
                                out.print("RESPONSE: Invalid choice. Try again: ");
                                break;
                        }
                    } else{
                        out.println("RESPONSE: Type 'b' for BIG, 'm' for MEDIUM or 's' for SMALL. Try again: ");
                    }
                }
                
                boolean brightnessDegIsValid = false;
                while (!brightnessDegIsValid) {
                    out.print("Brightness degree [Percentage from 0 to 100]: ");
                    brightness_degree = sc_num.nextDouble();
                    brightnessDegIsValid = (brightness_degree >= 0.0 && brightness_degree <= 100.0);
                    out.println( (brightnessDegIsValid) ? "" : "Invalid answer. Try again:");
                }

                controller.addArchangel(position, name, powers, prayer, celeb_day, celeb_month, color, size, essence, brightness_degree);
                out.println("RESPONSE: " + controller.message);
        } 
    }

// RF2 
    /**
     * Request the name of the archangel to be searched and invokes the controller's class function to do it.
     * <b>post: </b> user will have a report about the result of this operation. 
     * i.e. if the name of Archangel typed by the user was found, displays the results. Otherwise, prints a message.
     */
    public void findByName(){
        out.print("Write the name you want to search by: ");
        String name = sc_str.nextLine();
        int idx_of_archangel = controller.searchIdxByName(name);
        String res = "RESPONSE: " + ((idx_of_archangel == -1) ? "Not found." : controller.getArchangels()[idx_of_archangel].toString());
        out.println(res);
    }

    /**
     * Request the powers of the archangel to be searched and invokes the controller class function to do it.
     * <b>post: </b> user will have a report about the result of this operation. 
     * i.e. if the powers typed by the user were found, displays the results. Otherwise, prints a message.
     */
    public void findByPowers(){
        out.print("Write the powers you want to search by (comma separated): ");
        String[] powers = sc_str.nextLine().split(",");
        String res = "RESPONSE " + ((controller.searchByPowers(powers) == null) ? "Not found" : controller.searchByPowers(powers).toString()); 
        out.println(res);
    }

    /**
     * Request the number of the month of the archangel to be searched and invokes the controller class function to do it.
     * <b>post: </b> user will have a report about the result of this operation. 
     * i.e. if the number of the month typed by the user was found in some archangel, displays the results. Otherwise, prints a message.
     */
    public void findByMonth(){
        out.print("Type the number of the month you want to search by: ");
        int month = sc_num.nextInt();
        String response = controller.searchCelebsByMonth(month);
        String res = "RESPONSE: " + ((response.equals("")) ? "Not found" : response);
        out.println(res);
    }

    /**
     * Adds a new power to the archangel specified.
     * <b>post: </b> there will not exist duplicates powers, since before append it, the information typed by the user is validated.
     */
    public void createPowers(){
        boolean valid = false;
        String name = "";
        while(!valid){
            out.print("Type the name of the archangel you want to append powers to: ");
            name = sc_str.nextLine();
            if(controller.nameAlreadyExists(name))
                valid = true;
            else    
                out.println("There is no Archangel with that name. Try again:");
        }
        String[] powers = requestPowers();
        controller.appendPowers(name, powers);
        out.println(controller.message);
    }

    public MaximaSuperior getController() {
        return controller;
    }

    public void setController(MaximaSuperior controller) {
        this.controller = controller;
    }
}