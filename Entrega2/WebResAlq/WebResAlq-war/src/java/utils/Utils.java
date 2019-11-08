/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prene
 */
public class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());
    
    public static Date parseDate(String date){
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
        try {       
            return dateParser.parse(date);
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
