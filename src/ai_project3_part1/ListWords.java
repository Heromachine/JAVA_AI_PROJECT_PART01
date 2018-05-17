package ai_project3_part1;

import java.util.*;
import java.util.Map;
import java.io.Reader;
import java.util.Stack;
import java.lang.Object;
import java.util.Vector;
import java.util.HashMap;
import org.apache.lucene.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.StringReader;
import org.apache.lucene.analysis.*;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.util.AttributeSource;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

public class ListWords
{
    //CLASS INTERNAL TYPES
    Set<String> stopWords = new HashSet<String>();    
    Map<String, HashMap<String, Integer>> WORDZ =  new HashMap(); 
    
    Set<String> docs = new HashSet<String>();  

    public Set<String> getDocs() {
        return docs;
    }

    public void setDocs(Set<String> docs) {
        this.docs = docs;
    }


    public Vector<String> getWords() {
        return words;
    }

    public void setWords(Vector<String> words) {
        this.words = words;
    }
    Vector<String> words = new Vector<String>();
    
    //CLASS FUCNTIONS 
    public ListWords(){
        populateStopWords();
    }
    public void populateStopWords(){
        stopWords.add("a");stopWords.add("about");stopWords.add("above");stopWords.add("after");stopWords.add("again");stopWords.add("against");stopWords.add("all");
        stopWords.add("am");stopWords.add("an");stopWords.add("and");stopWords.add("any");stopWords.add("are");stopWords.add("aren't");stopWords.add("as");stopWords.add("at");stopWords.add("be");
        stopWords.add("because");stopWords.add("been");stopWords.add("before");stopWords.add("being");stopWords.add("below");stopWords.add("between");stopWords.add("both");
        stopWords.add("but");stopWords.add("by");stopWords.add("can't");stopWords.add("cannot");stopWords.add("could");stopWords.add("couldn't");stopWords.add("did");stopWords.add("didn't");
        stopWords.add("do");stopWords.add("does");stopWords.add("doesn't");stopWords.add("doing");stopWords.add("don't");stopWords.add("down");stopWords.add("during");stopWords.add("each");
        stopWords.add("few");stopWords.add("for");stopWords.add("from");stopWords.add("further");stopWords.add("had");stopWords.add("hadn't");stopWords.add("has");stopWords.add("hasn't");stopWords.add("have");
        stopWords.add("haven't");stopWords.add("having");stopWords.add("he");stopWords.add("he'd");stopWords.add("he'll");stopWords.add("he's");stopWords.add("her");stopWords.add("here");stopWords.add("here's");
        stopWords.add("her's");stopWords.add("herself");stopWords.add("him");stopWords.add("himself");stopWords.add("his");stopWords.add("how");stopWords.add("how's");
        stopWords.add("i");stopWords.add("i'd");stopWords.add("i'll");stopWords.add("i'm");stopWords.add("i've");stopWords.add("if");stopWords.add("in");stopWords.add("into");
        stopWords.add("is");stopWords.add("isn't");stopWords.add("it");stopWords.add("it's");stopWords.add("its");stopWords.add("itself");stopWords.add("let's");stopWords.add("me");stopWords.add("more");stopWords.add("most");stopWords.add("mustn't");
        stopWords.add("my");stopWords.add("myself");stopWords.add("no");stopWords.add("nor");stopWords.add("not");stopWords.add("of");stopWords.add("off");stopWords.add("on");stopWords.add("once");stopWords.add("only");
        stopWords.add("or");stopWords.add("other");stopWords.add("ought");stopWords.add("our");stopWords.add("ours");stopWords.add("ourselves");stopWords.add("out");stopWords.add("over");stopWords.add("own");
        stopWords.add("same");stopWords.add("shan't");stopWords.add("she");stopWords.add("she'd");stopWords.add("she'll");stopWords.add("she's");stopWords.add("should");stopWords.add("shouldn't");stopWords.add("so");stopWords.add("some");
        stopWords.add("such");stopWords.add("than");stopWords.add("that");stopWords.add("that's");stopWords.add("the");stopWords.add("their");stopWords.add("their's");stopWords.add("them");stopWords.add("themselves");
        stopWords.add("then");stopWords.add("there");stopWords.add("there's");stopWords.add("these");stopWords.add("they");stopWords.add("they'd");stopWords.add("they're");stopWords.add("they'll");stopWords.add("they've");
        stopWords.add("this");stopWords.add("those");stopWords.add("though");stopWords.add("to");stopWords.add("too");stopWords.add("under");stopWords.add("until");stopWords.add("up");stopWords.add("very");stopWords.add("was");
        stopWords.add("wasn't");stopWords.add("we");stopWords.add("we'd");stopWords.add("we'll");stopWords.add("we're");stopWords.add("we've");stopWords.add("were");stopWords.add("weren't");stopWords.add("what");stopWords.add("what's");
        stopWords.add("when");stopWords.add("when's");stopWords.add("where");stopWords.add("where's");stopWords.add("which");stopWords.add("while");stopWords.add("who");stopWords.add("who's");stopWords.add("whom");stopWords.add("why");
        stopWords.add("why's");stopWords.add("with");stopWords.add("won't");stopWords.add("would");stopWords.add("wouldn't");stopWords.add("you");stopWords.add("you'd");stopWords.add("you'll");stopWords.add("you're");stopWords.add("you've");
        stopWords.add("your");stopWords.add("yours");stopWords.add("yourself");stopWords.add("yourselves");
    }
    public void addWord02(String DocID, String tempWord, int preIn){      
        if (!WORDZ.containsKey(tempWord))
        {
            words.add(tempWord);
            docs.add(DocID);
            HashMap<String, Integer> temp = new HashMap();
            temp.put(DocID, 1);
            WORDZ.put(tempWord, temp);         
        }        
        else 
        {
           if (WORDZ.get(tempWord).containsKey(DocID))
           {               
               HashMap<String, Integer> temp = new HashMap();
               int x =  WORDZ.get(tempWord).get(DocID).intValue() + 1 ;
               ((Map)WORDZ.get(tempWord)).replace(DocID, x); 
               
               
           }
           else 
           if(!WORDZ.get(tempWord).containsKey(DocID))
           {
               //docs.add(DocID);
               
               HashMap<String, Integer> temp = new HashMap();
               temp.put(DocID, 1);
               ((Map)WORDZ.get(tempWord)).put(DocID, 1);   
               
               //System.out.println("NUMBER:"+WORDZ.get(tempWord).get(DocID).intValue());
              
           } 
        }       
    }      
    public void parseDoc(String content, String DocID) throws IOException {
        
        String AppendCont = content + " Appended";
        AppendCont = AppendCont.toLowerCase();
        StringBuilder buildcont = new StringBuilder();
        buildcont.append(AppendCont);        
        
        TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_30, new StringReader(buildcont.toString()));
        tokenStream = new StopFilter(true, tokenStream, stopWords);
        tokenStream = new PorterStemFilter(tokenStream);

        StringBuilder sb = new StringBuilder();
        TermAttribute termAttr = tokenStream.getAttribute(TermAttribute.class);
        try {
            while (tokenStream.incrementToken()){
                if (sb.length() > 0){
                    sb.append(" ");
                }
                sb.append(termAttr.term());
            }
        } catch(IOException e){
            //nothing
        }
        
        //StringBuilder sb = new StringBuilder();
        //sb.append(buildcont);
        
        int preIn = 0;
        String tempWord = "";
        
        //remove puntuation
        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) == ' ' && sb.charAt(i+1) == ' '){
                sb.deleteCharAt(i);
                i--;
            }
            else if(sb.charAt(i) == '.' || sb.charAt(i) == ',' || sb.charAt(i) == ':' || 
                    sb.charAt(i) == ';' || sb.charAt(i) == '?' || sb.charAt(i) == '\'' || 
                    sb.charAt(i) == '\n' || sb.charAt(i) == '&' || sb.charAt(i) == '!' ||
                    sb.charAt(i) == '(' || sb.charAt(i) == ')' || sb.charAt(i) == '\"' ||
                    sb.charAt(i) == '_' || sb.charAt(i) == '~' || sb.charAt(i) == '`' ||
                    sb.charAt(i) == '<' || sb.charAt(i) == '>'){
                sb.deleteCharAt(i);
                sb.insert(i, ' ');
                //i--;
            }
        }
        
        
        for(int i=preIn; i<sb.length(); i++){
            if(sb.charAt(i) != ' '){
                tempWord += sb.charAt(i);
            }
            else 
            {
//                if(!Words.containsKey(tempWord)){
//                    addWord02(DocID, tempWord, preIn);
//                    tempWord = "";
//                    preIn = i+1;
//                }
//                else {
//                    appendWord(DocID, tempWord, preIn);
//                    
//                    tempWord = "";
//                    preIn = i+1;
//                }
                                   
                    addWord02(DocID, tempWord, preIn);
                    tempWord = "";
                    preIn = i+1;

               
            }
   
        }
    }   
}