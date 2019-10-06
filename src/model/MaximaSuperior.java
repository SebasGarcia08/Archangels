package model;

import java.util.Arrays;
import java.util.Calendar;

/**
* This class contains all the bussines-logic of the problem to be tackled; 
* this is the controller class that provides all the functionalities to be used in the ui package.
* In the problem tackled, this class contains the array of archangels and the methods for navigating through it.
*/

public class MaximaSuperior{
    // Delcare the array of archangels as static and private, since it ensures that only this class will have direct access to it. 
    private static Archangel[] archangels;
    private int num_of_archs; // number of archangels
    public static String message = ""; // "global" variable that will be updated with every change. It can be accessed by the Main class in order to check the reports and desplay them.

    public static final int february_num_days = (Calendar.getInstance().get(Calendar.YEAR) % 4 == 0) ? 29 : 28;
    public static final String[] months = {"januray", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
    public static final int[] num_days_per_month = {31, february_num_days, 30, 31, 30, 31, 30, 31, 30, 31, 30, 31};
    
    // Constructor
    public MaximaSuperior(int num_of_archs) {
        archangels = new Archangel[num_of_archs];
    }

    // Overloaded constructor
    public MaximaSuperior(){
        archangels = new Archangel[0];
    }

    /**
     * Answers if the passed name is already registered for another Archangel in this legion.
     * @param name String, could be null or empty.
     * @return true if name was found, otherwise false.
     */
    public boolean nameAlreadyExists(String name){
        boolean nameAlreadyExists = true;
        if(searchIdxByName(name) == -1){
            nameAlreadyExists = false;
        } 
        return nameAlreadyExists;
    }

    /**
     * Answers if the name requested meet the conditions to being added.
     * <b>post: </b> after doing the validation, the message of reports will be updated with the results of this procedure.  
     * @param name String, could be empty, but CANNOT be null.
     * @return true if name ends with "el" and hasn't been registered yet. Otherwise, return false.
     */
    public boolean nameIsValid(String name){
        boolean nameIsCompatible = false;
        // name.substring(name.length()-2, name.length()).equals(Archangel.NAME_SUFFIX)
        if(name.endsWith(Archangel.NAME_SUFFIX)){
            nameIsCompatible =  true;
            if(nameAlreadyExists(name)){
                message = "Archangel with name " + name + " has been already registered.\n";
                nameIsCompatible = false;
            }
        } 
        else {
            message = "Name is not compatible, its two last characters must be 'el'.\n";
        }
        return nameIsCompatible;
    }

    /**
     * Validates if the requested powers meet the conditions to being added.
     * <b>post: </b> after doing the validation, the message of reports will be updated with the results of this procedure.
     * @param powers String[], array of powers to validate.
     * @return true if NONE of the powers exists already, otherwise returns false.
     */
    public boolean powersAreValid(String[] powers){
        boolean powersAlreadyExist = true;
        if(searchByPowers(powers) == null) {
            powersAlreadyExist =  false;
        }  else {
            message = searchByPowers(powers).getName() + " already has " + Arrays.toString(searchByPowers(powers).getPowers()) + " powers.";
        }
        return (powersAlreadyExist) ? false : true;
    }

    /**
     * Converts a number day and month into its equivalent in words.
     * <b>pre: </b> day and month should be coherent, i.e. should correspond to each other. 
     * Example: if month is 2 (february), day CANNOT be greater than 29; otherwise, an error will occur.  
     * @param day integer. CANNOT BE greater than 31 or less than 1.
     * @param month integer. CANNOT BE grater than 12, or less than 1.
     * @return the String month plus the number day. e.g. "january 12".
     */
    public String date2String(int day, int month){
        int february_num_days = (Calendar.getInstance().get(Calendar.YEAR) % 4 == 0) ? 29 : 28;
        String[] months = {"januray", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
        return months[month-1] + " " + day;
    }

    /**
     * Validates if the date requested meets the conditions to be added.
     * <b>pre: </b> 
     * @param celebration_day integer, CANNOT BE NULL.
     * @param celebration_month integer, CANNOT BE NULL.
     * @return true if the celebration_day is coherent with the celebration_month and viceversa. Otherwise, returns false.  
     */
    public boolean dateIsValid(int celebration_day, int celebration_month){
        boolean is_valid = true;
        
        if ((celebration_month > 12 || celebration_month < 0) || (celebration_day > 31 || celebration_day < 0)){
            is_valid = false;
            message = "INVALID DATE: wrong number of months or days.\n";
        } else if(celebration_day > num_days_per_month[celebration_month-1]){
            message = "INVALID DATE: " + months[celebration_month-1] + " has " + num_days_per_month[celebration_month-1] + " days.\n";
            is_valid = false;
        }
        return is_valid;
    }

    /**
     * Answers if the desired attributes of a potential new Archangel are valid.
     * @param name  String, name of the potential new Archangel to be added.
     * @param powers String[], list of the potential new Archangel to be added.
     * @param celebration_day integer, number of the day of celebration of the potential new Archangel to be added.
     * @param celebration_month integer, number of the month of celebration of the potential new Archangel to be added.
     * @return true if the attributes of the potential new Archangel meets the conditions to be added. Otherwise, returns false.
     */
    public boolean registrationIsAllowed(String name, String[] powers, int celebration_day, int celebration_month){
        boolean isAllowed = false;
        if(nameIsValid(name) && !powersAreValid(powers) && dateIsValid(celebration_day, celebration_month)){
            isAllowed =  true; 
        }
        return isAllowed;
    }

    /**
     * Add a new Archangel to the list of Archangels of this legion.
     * <b>pre: </b> ALL the attributes passed as parameters meets the conditions to be added and the array of Archangel has at least one position available.
     * <b>post: </b> a new Archangel with the attributes passed as parameters will be added in the position specified.
     * @param position integer, is a position available in the array of Archangels. CANNOT BE negative.
     * @param name String, name of the Archangel to be added.
     * @param powers String[], list of powers of the Archangel to be added. 
     * @param prayer String, prayer of the Archangel to be added.
     * @param celeb_day int, number of the month of celebration of the Archangel to be added.
     * @param celeb_month int, number of the day of celebration of the Archangel to be added.
     * @param color String, color of the candle to the Archangel to be added.
     * @param size String, size of the candle assigned to the Archangel to be added. 
     * @param essence Stirng, essence of the candle assigned to the Archangel to be added.
     * @param brightness_degree double, brightness degree of the candle assigned to the Archangel to be added.
     */
    public void addArchangel(int position, String name, String[] powers, String prayer, int celeb_day, int celeb_month, String color, String size, String essence, double brightness_degree){
            archangels[position] = new Archangel(name, powers, prayer, celeb_day, celeb_month, new SpecialCandle(color, size, essence, brightness_degree));
            message = "Archangel added succesfully.";
    }

    /**
     * Answers how many positions for archangels are available in this legion.
     * @return the number of avaialable positions in the array of Archangel of this legion.
     */
    public int numOfAvailablePositions(){
        return archangels.length - countArchangels();
    }

// RF1
    /**
     * Answers how many archangels are registered.
     * <b>pre: </b> the positions in the array of archangels are NULL if none archangel has been registered in it.
     * @return the number of Archangels registered.
     */
    public int countArchangels(){
        int total_number_of_archangels = 0;
        for(int i=0; i<  archangels.length; i++){
            if( archangels[i] != null)
                total_number_of_archangels ++;
        }
        message = "There are " + total_number_of_archangels + " archangels registered.";
        return total_number_of_archangels;
    }

// RF3
    /**
     * Iterates through the array of archangels of this legion; if finds an Archangel that matches the name requested, returns it.
     * @param name String, name of the archangel searched.
     * @return Archangel that matches the name passed through parameter if any. Otherwise returns null.   
     */
    // public Archangel searchByName(String name){
    //     boolean found = false;
    //     Archangel archangel_found = null;
    //     for(int i = 0; i<  archangels.length && !found; i++){
    //         if( archangels[i] != null &&  archangels[i].getName().equals(name)){
    //             found = true;    
    //             archangel_found =  archangels[i];
    //         }
    //     }
    //     return archangel_found;

    // RF4
    /**
     * Iterates over every Archangel and then search if has any of the powers passed as parameter. 
     * If find an archangel whose list of powers include any of the powers passed as parameter, returns it.
     * @param powers String[] powers to search by.
     * @return Archangel if any of the powers passed match with any of its powers. Otherwise, returns null.
     */
    public Archangel searchByPowers(String[] powers){
        boolean found = false;
        Archangel angel_found = null;
        String power_found = null;
        for(int i = 0; i <  archangels.length && !found; i++){
            for(int j = 0; j < powers.length && archangels[i] != null; j++){
                power_found =  archangels[i].searchPower(powers[j]); 
                if(power_found != null){
                    found = true;
                    angel_found =  archangels[i];
                }
            }
        }
        return angel_found;
    }

// RF5
     /**
     * Iterates through the array of archangels of this legion; if finds an Archangel whose celebration month matches with the passed as paramter, returns it.
     * @param month int, number of the celebration month of the archangel searched.
     * @return Archangel that matches the celebration month passed through parameter if any. Otherwise returns null.   
     */
    public String searchCelebsByMonth(int month){
        String response = "";
        for(Archangel archangel : archangels){
            if(archangel != null && archangel.getCelebrationMonth() == month)
                response += archangel.toString() + "\n";
        }
        return response;
    }

// RF6
    /**
     * Sends a message containing all the archangel's celebrations and name.
     * <b>post: </b> message will be updated containing the response of this operation.  
     */
    public String showAllCelebs(){
        String response = "There are no celebrations yet.";
        if(countArchangels() > 0){
            response = "";
            for(Archangel archangel : archangels){
                if(archangel != null)
                    response += archangel.getName() + ": " + date2String(archangel.getCelebrationDay(), archangel.getCelebrationMonth()) + "\n";
            }    
        }
        return response;
    } 

     /**
     * Iterates through the array of archangels of this legion; if finds an Archangel that matches the name requested, returns it.
     * @param name String, name of the archangel searched.
     * @return position of the Archangel that matches the name passed through parameter if any. Otherwise returns null.   
     */

    public int searchIdxByName(String name){
        boolean found = false;
        int idx = -1;
        for(int i = 0; i<  archangels.length && !found; i++){
            if(archangels[i] != null &&  archangels[i].getName().equals(name)){
                found = true;    
                idx =  i;
            }
        }
        return idx;
    }

// RF7
    public void appendPowers(String name, String[] powers){
        int idx_of_archangel = searchIdxByName(name);
        String[] old_powers = archangels[idx_of_archangel].getPowers();
        String[] new_powers = new String[old_powers.length + powers.length];
        for(int i = 0; i < old_powers.length; i++){
            new_powers[i] = old_powers[i];
        }
        for(int i = 0; i < powers.length; i++){
            new_powers[old_powers.length + i] = powers[i]; 
        }
        archangels[idx_of_archangel].setPowers(new_powers);
        message = "Powers created and added succesfully.";
    }

    // Getters and Setters needed.
    public Archangel[] getArchangels() {
        return archangels;
    }

    public void setArchangels(Archangel[] archangels) {
        archangels = archangels;
    }

    public int getNumArchangels(){
        return archangels.length;
    }

    public void setNumOfArchangels(int num_of_archs){
        this.num_of_archs = num_of_archs;
    }
}