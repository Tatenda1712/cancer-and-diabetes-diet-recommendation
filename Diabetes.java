/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simbarashe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author MAZHANDU
 */
public class Diabetes {
    static  String[] diabetesCausers;
    static String[] diabetesFighter;
    static Random r=new Random();
    
        public void showDiabetes()throws IOException{
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String user_input;
        String path=null;
        try {
            path =new File(Simbarashe.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath()+"\\";
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            System.out.println(decodedPath);
        }
        catch (Exception ex){

        }
        BufferedReader diabetesCausing = new BufferedReader(new FileReader(path+"\\diabetescausing.csv"));
        BufferedReader diabetesFighting = new BufferedReader(new FileReader(path+"\\diabetesfighting.csv"));

        String line;
        ArrayList<String> bufferList=new ArrayList<String>();

        while ((line = diabetesCausing.readLine()) != null){
            bufferList.add(line);
        }
        diabetesCausers=bufferList.toArray(new String[0]);

        bufferList=new ArrayList<String>();
        while ((line = diabetesFighting.readLine()) != null){
            bufferList.add(line);
        }
        diabetesFighter=bufferList.toArray(new String[0]);

        System.out.println("Hi, welcome to diabetes diet recommender. Do you need my assistance yes/no?");
        user_input = myObj.nextLine();  // Read user input

        if (user_input.toLowerCase().equals("yes")){

            while (!user_input.toLowerCase().equals("no")){
                System.out.println("Enter food making up your meal separated by commas like milk,beef,etc");
                user_input = myObj.nextLine();  // Read user input

                String [] input = user_input.split(",");
                ArrayList<String> input_array_string = new ArrayList();

                for (int i =0;i<input.length;i++){
                    input_array_string.add(input[i].trim().toLowerCase());
                }
                noprobBaye(input_array_string);
                System.out.println("Do you need my assistance yes/no?");
                user_input = myObj.nextLine();  // Read user input
            }
            System.out.println("Thank you.");

        }
        else {
            System.out.println("Thank you.");
        }


    }

    static  boolean noprobBaye(ArrayList<String> inputs){
        boolean result = false;
        ArrayList<String> diabetes_list = new ArrayList<String>();

        for (String input:inputs
        ) {
            boolean is_diabetes =false;
            int pos=0;
            for (String r:diabetesCausers
            ) {
                if (r.toLowerCase().contains(input.toLowerCase())||input.toLowerCase().contains(r.toLowerCase())) {
                    is_diabetes = true;
                    break;
                }else
                    pos++;
            }
            if (is_diabetes)
                diabetes_list.add(diabetesCausers[pos]);
        }

        double p_diabetes = (double) diabetes_list.size()/inputs.size();

        double baye_eve =evaluate_bayes(p_diabetes,p_diabetes==1?1:1-p_diabetes);
        if (baye_eve>0.1) {
            result = true;
            createCounter(baye_eve,inputs.size());

        }
        else
        {
            System.out.println("Your meal seems ok enjoy.");
        }

        return  result;

    }

    static  double evaluate_bayes(double A, double B){

        double p_A=0;

        p_A = (B*A/B)*0.3;

        String chance="";

        if (p_A<=0.1){
            chance="low";
        }
        else if (p_A<=0.2 &&  p_A>0.1){
            chance="medium";
        }else {
            chance="high";
        }

        System.out.println("**************************************");
        System.out.println( "Probability is : "+p_A+" .You have a " + chance +" chance of getting diabetes.");
        return  p_A;
    }

    static  String [] createCounter(double baye_eve,int input_lenght){

        String [] counters = null;



        int b =  (int) (input_lenght*(baye_eve/0.3));

        String recomendations ="";
        for (int j = 0; j <= b; j++) {
            int i = r.nextInt(diabetesFighter.length-1);
            recomendations+= diabetesFighter[i]+ (j<b-1?",":",");
        }

        System.out.println(recomendations.equals("")? "Your meal seems ok enjoy":"We recommend that you add " +recomendations);
        System.out.println("**************************************");
        System.out.println(" ");
        return  counters;
    }
    
    
}
