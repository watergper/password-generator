package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Password {
    private static final String NUMBER = "1234567890";
    private static final String SPECIAL = "!@#$%^&*()";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";

    private ArrayList<Character> numbList, specialList, upperList, lowerList;

    private int characterLimit, totalCount;
    private boolean includesNumbers, includesSpecialCharecters, includesUpperCase, includesLowerCase;
    String password;
    private List<Character> enabledCharList; 
    private HashMap<String, ArrayList<Character>> charList;


    public Password(int limit, boolean number, boolean special, boolean upper, boolean lower) {

        charList = new HashMap<String, ArrayList<Character>>();
        
        numbList = new ArrayList<Character>();
        for (char c : NUMBER.toCharArray()) {
          numbList.add(c);
        }

        specialList = new ArrayList<Character>();
        for (char c : SPECIAL.toCharArray()) {
          specialList.add(c);
        }

        upperList = new ArrayList<Character>();
        for (char c : UPPER.toCharArray()) {
          upperList.add(c);
        }

        lowerList = new ArrayList<Character>();
        for (char c : LOWER.toCharArray()) {
          lowerList.add(c);
        }

        this.characterLimit = limit;
        

        this.includesNumbers = number;
        setNumbers(number);
        this.includesSpecialCharecters = special;
        setSpecial(special);
        this.includesUpperCase = upper;
        setUpper(upper);
        this.includesLowerCase = lower;
        setLower(lower);

        
        setCharList();

        this.password = "";
        this.totalCount = enabledCharList.size();
    }

    public int getLimit() {
        return characterLimit;
    }

    public boolean hasNumber() {
        return includesNumbers;
    }

    public boolean hasSpecial() {
        return includesSpecialCharecters;
    }

    public boolean hasUpper() {
        return includesUpperCase;
    }

    public boolean hasLower() {
        return includesLowerCase;
    }

    public String getPassword() {
        return password;
    }


    public void setNumbers(boolean i) {
        if (i) {
            charList.put("includesNumbers", numbList);
        } else {
            charList.remove("includesNumbers");
        }
    }

    public void setSpecial(boolean i) {
        if (i) {
            charList.put("includesSpecialCharecters", specialList);
        } else {
            charList.remove("includesSpecialCharecters");
        }
    }

    public void setUpper(boolean i) {
        if (i) {
        charList.put("includesUpperCase", upperList);
        } else {
            charList.remove("includesUpperCase");
        }
    }

    public void setLower(boolean i) {
        if (i) {
            charList.put("includesLowerCase", lowerList);
        } else {
            charList.remove("includesLowerCase");
        }
    }

    private void setPassword(String new_password) {
        password = new_password;
    }


    public void setCharList() {
        this.enabledCharList = new ArrayList<Character>();
        for (List<Character> i : charList.values()) {
            enabledCharList = Stream.concat(enabledCharList.stream(), i.stream()).toList();
        }
    }

    public String getCharList() {
        return enabledCharList.toString();
    }



    public void generate() {
        setPassword("");
        for (int i = 0; i < this.characterLimit; i++) {
            password += GenerateRandom();
        }
    }

    private char GenerateRandom() {
        Random random = new Random();
        int index = random.nextInt(totalCount);
        return enabledCharList.get(index);
    }




}
