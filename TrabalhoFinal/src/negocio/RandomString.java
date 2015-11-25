/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.Random;

/**
 *
 * @author feliperiffel
 */
public class RandomString {

    private static final char[] symbols;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        for (char ch = 'A'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }   

    public static String nextString(int length) {
      Random random = new Random();
      char[] buf = new char[length];

      for (int idx = 0; idx < buf.length; ++idx) 
          buf[idx] = symbols[random.nextInt(symbols.length)];

      return new String(buf);
    }
}