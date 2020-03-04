package UtilFunctions;

import java.io.*;

public class compareFiles {

    public void comparingTextFiles(String f1, String f2) throws IOException {
        File file1 = new File(f1);
        File file2 = new File(f2);

        FileReader fr1 = new FileReader(file1);
        FileReader fr2 = new FileReader(file2);

        BufferedReader br1 = new BufferedReader(fr1);
        BufferedReader br2 = new BufferedReader(fr2);

        String Line1 = br1.readLine();
        String Line2 = br2.readLine();

        boolean areEqual = true;
        int linenum = 1;

        while (Line1 != null || Line2 != null)
        {
            if(Line1 == null || Line2 == null)
            {
                areEqual = false;
                break;
            }
            else if(! Line1.equalsIgnoreCase(Line2))
            {
                areEqual= false;
                break;
            }

            Line1 = br1.readLine();
            Line2 = br2.readLine();
            linenum++;
        }

        if(areEqual)
        {
            System.out.println("Two files have same content.");
        }
        else
        {
            System.out.println("Two files have different content. They differ at line "+linenum);

            System.out.println("File1 has "+Line1+" and File2 has "+Line2+" at line "+linenum);
        }

        br1.close();
        br2.close();
    }
}
