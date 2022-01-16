package ieeemsb.utils;

public class Encryptor {
	public static String encrypt(String plainString){
        StringBuilder encrypted = new StringBuilder();
        int alphaCount = 0;
        int digitCount = 0;
        char character ;
        StringBuilder plain = new StringBuilder(plainString);
        StringBuilder lowerKey = new StringBuilder("happyencryption");
        StringBuilder upperKey = new StringBuilder("HAPPYENCRYPTION");
        StringBuilder digitKey = new StringBuilder("893423674");
        for (int i = 0 ; i < plain.length() ; i++) {
            if (alphaCount == lowerKey.length())
                alphaCount = 0 ;
            if (digitCount == digitKey.length())
                digitCount = 0 ;
            if(Character.isLowerCase(plain.charAt(i))){
                character = (char) (plain.charAt(i) + (lowerKey.charAt(alphaCount)-'a'));
                if (character > 'z')
                    character -= 26;
                encrypted.append((char)character);
                alphaCount++;
            }else if(Character.isUpperCase(plain.charAt(i))){
                character = (char) (plain.charAt(i) + (upperKey.charAt(alphaCount)-'A'));
                if (character > 'Z')
                    character -= 26;
                encrypted.append((char)character);
                alphaCount++;
            }else if(Character.isDigit(plain.charAt(i))){
                character = (char) (plain.charAt(i) + (digitKey.charAt(digitCount) - '0'));
                if (character > '9')
                    character -= 10;
                encrypted.append((char)character);
                digitCount++;
            }else{
                encrypted.append(plain.charAt(i));
            }
        }
        String returnedString = encrypted.toString();
        return returnedString;
    }
}
