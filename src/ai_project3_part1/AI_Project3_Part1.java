package ai_project3_part1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Jessie Reyna
 */
public class AI_Project3_Part1 
{
    static int pass = 12;
    public static void main(String[] args) 
    {        
        FileReadParser FRP = new FileReadParser(); 
        Path directory =  Paths.get("cata");   
        
        ListWords MyList = new ListWords();
        MyList.populateStopWords();
        
        recrusiveFileFind(FRP, MyList, directory.getFileName().toString(), null);
        
        
        System.out.println(MyList.WORDZ.size());   
        System.out.println(MyList.getWords().size());
        System.out.println(MyList.getDocs().size());
        
        for (String S: MyList.getWords())            
        {
             System.out.println("WORD: " + S);
        }
        for (String D: MyList.getDocs())
        {    
            System.out.println("DOC: " + D);
        }
        for (String S: MyList.getWords())            
        {
            
            if (MyList.WORDZ.containsKey(S))
            {  
                System.out.println("\n[WORD]=================================================" + S);                
                
                for (String D: MyList.getDocs())
                {                        
                    if (MyList.WORDZ.get(S).containsKey(D))
                    {
                        System.out.println("\t[DOC]: " + D); 
                        System.out.format("\t\t[FRQUENCY]: "+MyList.WORDZ.get(S).get(D).intValue()+ "\n\n");
                    }
                 
                }                
            }
            System.out.println("=================================================\n");
        }

    }
    

    
    static public void recrusiveFileFind(FileReadParser FRP, ListWords MyList, String directory, String parentPath)
    {   

        //If FILE Is Directory
        if (FRP.isExistingDirectory(directory))
        {   
            //System.out.println("DIRECTORY DISCOVERD: "+ directory);
            File file = new File(directory);            
            String[] files = file.list();
            
            for (String string : files)
            {  
                //System.out.println("\t\tSUBDIRECTORY: "+ string);
                String FuturePath = directory + "/" + string;                                
                recrusiveFileFind(FRP, MyList, FuturePath, directory);
                
                if (pass == 1)
                {
                System.out.println("\n\n=================DIRECTORY KEYSET: "+ directory);
                System.out.println(MyList.WORDZ.size());
                System.out.println(MyList.WORDZ.keySet());
                System.out.println("=================DIRECTORY KEYSET: "+ directory + "\n\n");
                    pass = 4; 
                }
                
                
            }
        }
        else 
        if (FRP.isExistingFile(directory))
        { 
            //System.out.println("FILE: " + directory);
            try 
            {   
                MyList.populateStopWords();                       
                String ps = FRP.parserDoc02(directory);
                MyList.parseDoc(ps, directory);                             
            }
            catch (IOException ex) 
            {}  
            //System.out.println("\tFILE: " + directory + "COMPLETE");
            
        }
        else 
        {
           // System.out.println("ERROR!!!!!!!!!!!!");
        
        }
        
        //System.out.println("===================================\n\n");
    }
}
