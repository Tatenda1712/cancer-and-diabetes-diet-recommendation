/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simbarashe;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author MAZHANDU
 */
public class Simbarashe {

   
    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) throws IOException {
	// write your code here
         Scanner scan = new Scanner(System.in);
            int options;
            System.out.println("Main Menu\n\n");
            System.out.println("1. Cancer Recommandation");
            System.out.println("2. Diabetes Recommandation");
            options=scan.nextInt();
            if(options==1){
                Cancer cancer=new Cancer();
                cancer.showCancer();
            }
            else if(options==2){
                Diabetes diabetes = new Diabetes();
                diabetes.showDiabetes();
            }
            else{
                System.out.println("You have entered an incorrect option");
            }
        } 
}
