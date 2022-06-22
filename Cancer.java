package simbarashe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MAZHANDU
 */
public class Cancer {
    static  String[] cancerCausers;
    static String[] cancerFighter;
    static Random r=new Random();
    
    public void showCancer()throws IOException{
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
        BufferedReader cancerCausing = new BufferedReader(new FileReader(path+"\\cancercausing.csv"));
        BufferedReader cancerFighting = new BufferedReader(new FileReader(path+"\\cancerfighting.csv"));

        String line;
        ArrayList<String> bufferList=new ArrayList<String>();

        while ((line = cancerCausing.readLine()) != null){
            bufferList.add(line);
        }
        cancerCausers=bufferList.toArray(new String[0]);

        bufferList=new ArrayList<String>();
        while ((line = cancerFighting.readLine()) != null){
            bufferList.add(line);
        }
        cancerFighter=bufferList.toArray(new String[0]);

        System.out.println("Hi, welcome to cancer diet recommender. Do you need my assistance yes/no?");
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
        ArrayList<String> cancer_list = new ArrayList<String>();

        for (String input:inputs
        ) {
            boolean is_cancer =false;
            int pos=0;
            for (String r:cancerCausers
            ) {
                if (r.toLowerCase().contains(input.toLowerCase())||input.toLowerCase().contains(r.toLowerCase())) {
                    is_cancer = true;
                    break;
                }else
                    pos++;
            }

            if (is_cancer)
                cancer_list.add(cancerCausers[pos]);
        }

        double p_cancer = (double) cancer_list.size()/inputs.size();

        double baye_eve =evaluate_bayes(p_cancer,p_cancer==1?1:1-p_cancer);
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
        System.out.println( "Probability is : "+p_A+" .You have a " + chance +" chance of getting cancer.");
        return  p_A;
    }

    static  String [] createCounter(double baye_eve,int input_lenght){

        String [] counters = null;



        int b =  (int) (input_lenght*(baye_eve/0.3));

        String recomendations ="";
        for (int j = 0; j <= b; j++) {
            int i = r.nextInt(cancerFighter.length-1);
            recomendations+= cancerFighter[i]+ (j<b-1?",":",");
        }

        System.out.println(recomendations.equals("")? "Your meal seems ok enjoy":"We recommend that you add " +recomendations);
        System.out.println("**************************************");
        System.out.println(" ");
        return  counters;
    }
    
     
    }
    

