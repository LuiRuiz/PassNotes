package com.example.comsbetweencontrollers;

import java.io.Serializable;
import java.util.*;

public class NewPassword extends Password implements Serializable {
    private  String smybols = "$!_@?*&";
    private String password = "";
    boolean[] inclusions;
    ArrayList<Integer> IncludedTypes = new ArrayList<Integer>();



    public NewPassword (int passLength,String name ){
        super(passLength,name);

        for (Integer i : getPasswordArray(passLength)) {

            // turn int of getPasswordArray to char then add char

            password += getCharFromNum(i);
        }
        pass = password;
    }
    public NewPassword(int passLength, String name, boolean[] myInclusions, String mySymbols){
        super(passLength,name);
        System.out.println("using Symbols consructor");
        inclusions = myInclusions;
        for (boolean item: inclusions){
            System.out.println(item);
        }
        if (inclusions[2]){
            smybols = mySymbols;
            System.out.println("Symbols: "+ inclusions[2]);
        }
        //enables letters
        IncludedTypes.add(0);
        int boolCounter = 1;
        for (boolean bool : inclusions){
            if (bool){
                IncludedTypes.add(boolCounter);
            }
            boolCounter++;
        }
        for (Integer i : getPasswordArray(passLength)) {

            // turn int of getPasswordArray to char then add char

            password += getCharFromNum(i);
        }
        pass = password;


    }
    public NewPassword(int passLength, String name, boolean[] myInclusions){
        super(passLength,name);
        System.out.println("using controctor without symbols");
        inclusions = myInclusions;

        for (boolean item: inclusions){
            System.out.println(item);
        }

        //enables letters
        IncludedTypes.add(0);
        int boolCounter = 1;
        for (boolean bool : inclusions){
            if (bool){
                IncludedTypes.add(boolCounter);
            }
            boolCounter++;
        }
        System.out.println("From constructor \nInclude types ="
                );
        for (int items: IncludedTypes){
            System.out.println(items);
        }
        for (Integer i : getPasswordArray(passLength)) {

            // turn int of getPasswordArray to char then add char

            password += getCharFromNum(i);
        }
        pass = password;


    }


    public Integer[] getPasswordArray(int length){
        Random rand = new Random();
        Integer[] passwordArray =  new Integer[length];
        Arrays.fill(passwordArray, 0);
        for (Integer i = 0; i < passwordArray.length; i++){
            if (i < IncludedTypes.size()){
                passwordArray[i]= IncludedTypes.get(i);
            }else{
                passwordArray[i]= IncludedTypes.get(rand.nextInt(IncludedTypes.size()));
            }

        }
        for (Integer item : passwordArray) {
            System.out.print(item);

        }
        System.out.println("");

        List<Integer> intList = Arrays.asList(passwordArray);

        Collections.shuffle(intList);

        intList.toArray(passwordArray);

        for (Integer item : passwordArray) {
            System.out.print(item);

        }
        System.out.println("");

        return passwordArray;
    }

    public char getCharFromNum(int i){
        Random rand = new Random();
        int min, max;

        char genChar = ' ';
        switch(i){
            case 3:
                //sybols
                min = 0;
                max = 0;
                genChar =  smybols.charAt(rand.nextInt(smybols.length()));
                break;
            case 0:
                //lowercase
                max =122;
                min = 98;
                genChar = (char) (rand.nextInt(max - min) + min);
                break;
            case 2:
                max = 90;
                min = 63;
                //upper cass
                genChar = (char) (rand.nextInt(max - min) + min);
                break;
            case 1:
                //
                max = 57;
                min = 48;
                genChar = (char) (rand.nextInt(max - min) + min);
                break;



        }
        return genChar;

    }
}
