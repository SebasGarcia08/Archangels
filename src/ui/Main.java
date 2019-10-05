package ui;

import model.*;
import java.util.Scanner;
import static java.lang.System.*;


public class Main {
    private static MaximaSuperior maxSuperior;
    private static Scanner sc_str, sc_num;

    public Main(){
        this.maxSuperior = new MaximaSuperior(); 
        sc_str = new Scanner(System.in);
        sc_num = new Scanner(System.in);
    }
    
    public static void main(String[] args){
        Main program = new Main();
        out.println("WELCOME"); 
        out.print("Type the number of Archangels you want to add: ");
        int num_of_archs = sc_num.nextInt();
        program.setMaxSuperior(new MaximaSuperior(num_of_archs));
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
                    program.reqInfoAndaddArchangel();
                    break;
                case 2: 
                    out.println("There are " + program.maxSuperior.countArchangels() + " archangels registered.");
                    break;
                case 3: 
                    program.findByName();
                    break;
                case 4: 
                    program.findByPowers();
                    break;
                case 5: 
                    program.finByMonth();
                    break;
                case 6: 
                    out.println(maxSuperior.showAllCelebs());
                    break;
                case 7:
                    program.createPower();
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

    public String[] reqPowers(){
        String[] powers = new String[0];
        boolean valid = false;
        while(!valid){
            out.print("Powers [Example: health, thriveness]: ");
            powers = sc_str.nextLine().split(",");
            if(maxSuperior.powersAreValid(powers)){
                valid = true;
            }
            else{
                out.println("RESPONSE: " + maxSuperior.message + "Try again:");   
            }
        }
        return powers;
    }

    public String reqName(){
        String name = "";
        boolean valid = false;
        while(!valid){
            out.print("Name (Example: Gabriel):");
            name = sc_str.nextLine();
            if(maxSuperior.nameIsValid(name)){
                valid = true;
            }
            else {
                out.println("RESPONSE: " + maxSuperior.message + "Try again:");        
            }
        }
        return name;
    }

// RF1
    public void reqInfoAndaddArchangel(){
//      Archangels attributes
        String name, prayer;
        String[] powers;
        int celeb_day, celeb_month;

//      Candle's Attributes
        String color, essence, size;
        double brightness_degree;

        boolean isValidInfo = false;

        out.println("YOU CAN ADD " + maxSuperior.numOfAvailablePositions() + " MORE ARCHANGELS.");
        int position = maxSuperior.getNumArchangels() - maxSuperior.numOfAvailablePositions();
        
        if(maxSuperior.numOfAvailablePositions() > 0){
                out.println("ARCHANGEL NUMBER " + (position + 1));
                
                name = reqName();
                
                powers = reqPowers();

                out.print("Prayer: ");
                prayer = sc_str.nextLine();

                while(true){
                    out.print("Celebration day [Example: 8]: ");
                    celeb_day = sc_num.nextInt();
                    out.print("Celebration month [Example (december): 12]: ");
                    celeb_month = sc_num.nextInt();
                    if(maxSuperior.dateIsValid(celeb_day, celeb_month)){
                        break;
                    }
                    else{
                        out.println("RESPONSE: " + maxSuperior.message + "Try again:");
                    }
                }
                   
                out.print("### Candle ###\n");
                out.print("Color: ");
                color = sc_str.nextLine();
                out.print("Essence: ");
                essence = sc_str.nextLine();
                out.print("Size: ");
                size = sc_str.nextLine();
                out.print("Brightness degree: ");
                brightness_degree = sc_num.nextDouble();
    
                maxSuperior.addArchangel(position, name, powers, prayer, celeb_day, celeb_month, color, size, essence, brightness_degree);
                out.println("RESPONSE: " + maxSuperior.message);
        } 
    }

// RF2 
    public void findByName(){
        out.print("Write the name you want to search by: ");
        String name = sc_str.nextLine();
        int idx_of_archangel = maxSuperior.searchIdxByName(name);
        String res = "RESPONSE: " + ((idx_of_archangel == -1) ? "Not found." : maxSuperior.getArchangels()[idx_of_archangel].toString());
        out.println(res);
    }

    public void findByPowers(){
        out.print("Write the powers you want to search by (comma separated): ");
        String[] powers = sc_str.nextLine().split(",");
        String res = "RESPONSE " + ((maxSuperior.searchByPowers(powers) == null) ? "Not found" : maxSuperior.searchByPowers(powers).toString()); 
        out.println(res);
    }

    public void finByMonth(){
        out.print("Type the number of the month you want to search by: ");
        int month = sc_num.nextInt();
        String response = maxSuperior.searchCelebsByMonth(month);
        String res = "RESPONSE: " + ((response.equals("")) ? "Not found" : response);
        out.println(res);
    }

    public void createPower(){
        boolean valid = false;
        String name = "";
        while(!valid){
            out.print("Type the name of the archangel you want to append powers to: ");
            name = sc_str.nextLine();
            if(maxSuperior.nameAlreadyExists(name))
                valid = true;
            else    
                out.println("There is no Archangel with that name. Try again:");
        }
        String[] powers = reqPowers();
        maxSuperior.appendPowers(name, powers);
        out.println(maxSuperior.message);
    }

    public MaximaSuperior getMaxSuperior() {
        return maxSuperior;
    }

    public void setMaxSuperior(MaximaSuperior maxSuperior) {
        this.maxSuperior = maxSuperior;
    }
}