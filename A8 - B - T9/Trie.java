import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.util.*;

public class Trie{
    Node root;

    private class Node {
        public Node[] next; //every node next repr. a word, a-ö, each node has 27 children from a-ö
        public boolean valid;
        
        public Node() {
            next = new Node[27];
            valid = false;
        }

        public void add(String word, int indx){ //indx är bokstavens inx i ordet, så indx = 0 är första bokstaven
            int lastindx = word.length();   //sista char av ordet
            if (indx == lastindx) { //träffat en leaf
                //System.out.print(", ");
                valid = true; // Mark the node as a valid node
                return;
            }

            char ch = word.charAt(indx);    //index av char i ordet
            int charindx = code(ch); //ex. indx = 0 för "äpple" returnerar 25 för ä 

            if(next[charindx] == null)  //om luckan är tom, skapa en ny nod
                next[charindx] = new Node();
            
            //System.out.print(ch);

            next[charindx].add(word, indx + 1); //addera rekursivt alla chars av ordet
            
        }

        /*public void collect(ArrayList<String> list, String keyseq, String word){ //"2314"
            if (valid == true) 
                list.add(word);
            if (keyseq.isEmpty()) 
                return;
            
            char curkey = keyseq.charAt(0);
            int keyindx = getindx(curkey);

            int c = keyindx * 3;
            for (int i = c; i <= c + 2; i++) {
                if (next[i] != null) {
                    char add = code(i);
                    next[i].collect(list, keyseq.substring(1), word + add);
                }
            }

        }*/
        
        public void collect(ArrayList<String> list, String keyseq, String word, int indx){ //"2314"
            int lastindx = keyseq.length();    
            if (valid == true) 
                list.add(word);
            if (indx == lastindx)
                return;
            
            char curkey = keyseq.charAt(indx);
            int keyindx = getindx(curkey);

            int c = keyindx * 3;
            for (int i = c; i <= c + 2; i++) {
                if (next[i] != null) {
                    char add = revcode(i);
                    next[i].collect(list, keyseq, word + add, indx + 1);
                }
            }

        }


    }

    public ArrayList<String> decode(String seq){ //takes a key sequence, in a string "2314"
        ArrayList<String> list = new ArrayList<String>();   //innehåller alla ord som kan matcha sequencen 2314 (def-ghi-abc-jkl), 2^4 possibilitie
        String word = "";
        root.collect(list, seq, word,0);
        return list;

    }

    static final int SIZE = 8263;

    static String[] data = new String[SIZE];

    private Trie(String file){
        root = new Node();

        try (BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split("\n");
                data[i++] = new String(row[0]);
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }

    }

    private void addrec(String word){
        root.add(word, 0);
    }

    private static int code(char w) {
        switch (w) {
            case 'a' :
                return 0;
            case 'b' :
                return 1;
            case 'c' :
                return 2;
            case 'd' :
                return 3;
            case 'e' :
                return 4;
            case 'f' :
                return 5;
            case 'g' :
                return 6;
            case 'h' :
                return 7;
            case 'i' :
                return 8;
            case 'j' :
                return 9;
            case 'k' :
                return 10;
            case 'l' :
                return 11;
            case 'm' :
                return 12;
            case 'n' :
                return 13;
            case 'o' :
                return 14;
            case 'p' :
                return 15;   //ingen q
            case 'r' :
                return 16;
            case 's' :
                return 17;
            case 't' :
                return 18;
            case 'u' :
                return 19;
            case 'v' :
                return 20;  //ingen w
            case 'x' :
                return 21;
            case 'y' :
                return 22;
            case 'z' :
                return 23;
            case 'å' :
                return 24;
            case 'ä' :
                return 25;
            case 'ö' :
                return 26;

        }
        return -1;
    }

    private static char revcode(int nr) {
        switch (nr) {
            case 0 :
                return 'a';
            case 1 :
                return 'b';
            case 2 :
                return 'c';
            case 3 :
                return 'd';
            case 4 :
                return 'e';
            case 5 :
                return 'f';
            case 6 :
                return 'g';
            case 7 :
                return 'h';
            case 8 :
                return 'i';
            case 9 :
                return 'j';
            case 10 :
                return 'k';
            case 11 :
                return 'l';
            case 12 :
                return 'm';
            case 13 :
                return 'n';
            case 14 :
                return 'o';
            case 15 :
                return 'p';
            case 16 :        //ingen q
                return 'r';
            case 17 :
                return 's';
            case 18 :
                return 't';
            case 19 :
                return 'u';
            case 20 :
                return 'v';
            case 21 :        //ingen w
                return 'x';
            case 22 :
                return 'y';
            case 23 :
                return 'z';
            case 24 :
                return 'å';
            case 25 :
                return 'ä';
            case 26 :
                return 'ö';
        
        }
        return '!';
    }

    private static int getcharindx(char key){
        int i = 0;
        int j = 2;
        int indx = 1;  //key, a char

        while(j <= 26){
            if(i <= code(key) && code(key) <= j)
                return indx;
            else{
                i += 3;
                j += 3;
                indx++;
            }
        }
        return -1;
    }

    private String encode(String w){
        String keyseq = "";
        int indx = 0;

        while(w.length() != indx){
            char key = w.charAt(indx);
            int k = 1;
            for(int i = 0; i <= 24 ; i+=3){
                if(i <= code(key) && code(key) <= i+2){
                    char c = (char) (k + '0');
                    keyseq = keyseq + c;
                }
                k++;
            }
            indx++;
        }
        
        return keyseq;

    }


    //omvandlar key (char) till key men int (index)
    private static int getindx(char key){         
        int a = Integer.parseInt(String.valueOf(key));        //får användas?????
        return a-1; 
    }


    public static void main(String[] args){
        Trie trie = new Trie("kelly.csv");

        /*System.out.println("gets char, returns code: " + trie.code('ä'));
        System.out.println("gets code, returns char: " + trie.code(25));
        System.out.println("gets key, returns index: " + trie.getcharindx('n') + trie.getcharindx('p') + trie.getcharindx('a'));
        System.out.println("gets key, returns index: " + trie.getindx('9'));*/

        /*trie.addrec("andra");
                trie.addrec("andrass");
                trie.addrec("andrasosos");*/

        String[] encodedkeys = new String[SIZE];

        for(int i = 0; i < SIZE; i++){
            trie.addrec(data[i]);
            encodedkeys[i] = trie.encode(data[i]);
            //System.out.println(data[i] + ", encoded: "  + encodedkeys[i]+ ", decoded: " + trie.decode(encodedkeys[i]));
            //System.out.print(data[i] + ": "  + trie.converter(data[i]) + ", ");
        }

        String s = "trött";
        System.out.println(s + ", encoded: "  + trie.encode(s) + ", decoded: " + trie.decode(trie.encode(s)));



        //System.out.println(data[567] + ": encoded: "  + encodedkeys[567]+ ", decoded: " + trie.decode(encodedkeys[567]));

        System.out.println();

        //String s = "särskilt";
        //System.out.println(s + ": encoded: "  + trie.converter(s) + ", decoded: " + trie.decode(trie.converter(s)));




    }
}

