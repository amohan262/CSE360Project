import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JScrollPane;

public class project1main extends JPanel implements ActionListener {
static String[] words;
static String str;
   static JButton button;
   static JButton OK;
   static JRadioButton Left;
   static JRadioButton Right;
   static JRadioButton Full;
   static JRadioButton SingleSpace;
   static JRadioButton DoubleSpace;
   static int charlength = 80;

   JLabel  InfoLabel;
   JFileChooser chooser;
   String choosertitle;
   static JTextArea text1 = new JTextArea(8,50);
   JScrollPane scrollPane = new JScrollPane(text1);
 static JTextArea text2 =new JTextArea(1,1);
 static JTextArea text3 =new JTextArea(1,1);
 static JTextArea text4 =new JTextArea(1,1);
 static JTextArea text5 =new JTextArea(1,1);
 static JTextArea text6 =new JTextArea(1,1);
 static JTextArea text7 =new JTextArea(1,1);
  static JTextField linelength = new JTextField(3);
 static PrintWriter writer2;
 static int lines=0;


  public project1main() {
   button = new JButton("Select file ");
   button.addActionListener(this);
   add(button);
   Right = new JRadioButton("Right");
   Full = new JRadioButton("Full");
   Left = new JRadioButton("Left");
   SingleSpace= new JRadioButton("Single Space");
   DoubleSpace = new JRadioButton("DoubleSpace");



   OK = new JButton("OK");
   OK.addActionListener(this);
   add(OK);



   add(text1);
   final String FileWriter = null;
String input = "";
String outforthis = "";
   button.addActionListener(new ActionListener() {
      @Override

      public void actionPerformed(ActionEvent e) {
         if (chooser == null) {
               chooser = new JFileChooser();
               chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               chooser.setAcceptAllFileFilterUsed(false);
               chooser.addChoosableFileFilter(new FileFilter() {
                  @Override
                  public boolean accept(File f) {
                    // input = f.getName();
                     return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                  }

                  @Override
                  public String getDescription() {
                     return "Text Files (*.txt)";
                  }
               });
         }

         switch (chooser.showOpenDialog(project1main.this)) {
               case JFileChooser.APPROVE_OPTION:
                  try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                     text1.setText(null);
                     String text = null;
                     while ((text = br.readLine()) != null) {
                           text1.append(text);
                           text1.append("\n");
                     }
                     text1.setCaretPosition(0);
                  } catch (IOException exp) {
                     exp.printStackTrace();
                     JOptionPane.showMessageDialog(project1main.this, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
                  }
                  break;
         }



      }
   });


   OK.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e)
   {
      Scanner scanner;
      PrintWriter writer;
      Scanner scanner2;
      StringWriter w;

      int countblank=0;
      int countlines=0;
      int counttotal =0;
      int countWord=0;
      int ch;
      int charsCount = 0;
      int countSpaces = 0;
      try
      {

         scanner = new Scanner(chooser.getSelectedFile());
         writer = new PrintWriter("intermediate.txt");

         while(scanner.hasNext())
         {

               String line = scanner.nextLine();
               if (!line.isEmpty()) {
                  writer.write(line);
                  writer.write(" ");
                  countlines++;
               }
               counttotal++;
              charsCount +=line.length();


                words = line.split("\\s+");
                String str = Arrays.toString( words );


         }
        int  blanklines= counttotal -countlines;
        text2.append(Integer.toString(blanklines));
         scanner.close();
         writer.close();
      } catch (FileNotFoundException e1)
      {

         e1.printStackTrace();
      }
      try
      {

         if(!linelength.getText().equals(""))
         {
            String length = linelength.getText();
            charlength = Integer.parseInt(length);
         }






           FileReader in = new FileReader("intermediate.txt");
           BufferedReader br = new BufferedReader(in);
           scanner2 = new Scanner(br);

           w = new StringWriter();
           writer2 = new PrintWriter(w);

           StringBuilder buildertext = new StringBuilder(charlength);

           scanner2.useDelimiter("");


           if(Full.isSelected())

           {
               scanner2 = new Scanner(chooser.getSelectedFile());
               writer = new PrintWriter("intermediate.txt");

               while(scanner2.hasNext())
               {

                     String line = scanner2.nextLine();
                     if (!line.isEmpty()) {
                        writer.write(line);
                        writer.write(" ");
                        countlines++;
                     }
                     counttotal++;

               }
          //    text4.append(Integer.toString(lines));
              String text = text1.getText().toString();

              System.out.println("Original: " + text);
              String[] textNoLine = text.split("\n");

              String text2 = "";

              for(int i = 0; i < textNoLine.length; i++)
              {
                 text2 += textNoLine[i];
              }
//              int words = 0; //WORDS IS USED AN AS ARRAY IN JUSTIFY FUNCTION
//              for(String s:textNoLine) {
//                  s = s.trim().replaceAll(" +", " ");
//                  if(!s.isEmpty()){
//                     words += s.split(" ").length;
//                  }
//              }

//              text3.append(Integer.toString(words));


         int[] analysis = justifyText(text2);
countWord = analysis[0];
countSpaces = analysis[1];
lines = analysis[2];
             double averageWords= (double)(countWord)/lines;
             text3.append(Integer.toString(countWord));
             text4.append(Integer.toString(lines));
             text5.append(Double.toString(averageWords));
            text6.append(Double.toString(charlength));
             text7.append(Integer.toString(countSpaces));




            JFileChooser chooser = new JFileChooser();

// Comment out because it’s being calculated in justifyText
         //   String out = w.toString();
            // for (int i = 0; i < out.length(); i++) {
              //  if (out.charAt(i) == ' ') {
                //   countSpaces++;
                //}
             //}
             chooser.setCurrentDirectory(new File("/home/me/Documents"));
             int retrival = chooser.showSaveDialog(null);
             if (retrival == JFileChooser.APPROVE_OPTION) {
                 try {

                    if(input == outforthis)
                    {}

                    FileWriter f = new FileWriter(chooser.getSelectedFile()+".txt");

                    f.write(out);
                     f.close();
                 }
                 catch (Exception ex) {
                     ex.printStackTrace();
                 }
             }









           }
           else {


              while(scanner2.hasNext())

           {


               //
              Scanner newScan = new Scanner(scanner2.nextLine());

              while (newScan.hasNext()) {
                 countWord++;

                  String after = newScan.next();

                  if ( (buildertext.length()+ after.length() +1) > charlength)
                  {
                    if(DoubleSpace.isSelected()) {
                        buildertext.append('\n');
                        lines++;
                      }
                      if(Right.isSelected())
                      {
                         buildertext.append('\n');
                        // toString();
                        writer2.write(String.format("%120s",buildertext.toString() ));
                         buildertext = new StringBuilder(after);
                         lines++;
                      }


                      else
                      {
                         buildertext.append('\n');
                         writer2.write(buildertext.toString());
                         buildertext = new StringBuilder(after);

                         lines++;

                      }
                  }
                  else
                  {
                     buildertext.append((buildertext.length() == 0 ? "" :" ") +after);
                  }


              }


             if (Right.isSelected())
             {
                writer2.write(String.format("%120s", buildertext.toString()));
             }
             else {
               writer2.write(buildertext.toString());
             }
             int lines1= lines  ;
             double linelength= (double)(charsCount)/lines1;
             double averageWords= (double)(countWord)/lines1;


             text3.append(Integer.toString(countWord));
             text4.append(Integer.toString(lines1));
             text5.append(Double.toString(averageWords));
             text6.append(Double.toString(linelength));
             text7.append(Integer.toString(countSpaces));
                  System.out.println (averageWords);
              newScan.close();

           }
              JFileChooser chooser = new JFileChooser();
              //String sb = buildertext.toString();
              //writer2.write(buildertext.toString() + "\n");
               String out = w.toString();
               for (int i = 0; i < out.length(); i++) {
                  if (out.charAt(i) == ' ') {
                     countSpaces++;
                  }
               }
               chooser.setCurrentDirectory(new File("/home/me/Documents"));
               int retrival = chooser.showSaveDialog(null);
               if (retrival == JFileChooser.APPROVE_OPTION) {
                   try {

                      if(input == outforthis)
                      {}


                      FileWriter f = new FileWriter(chooser.getSelectedFile()+".txt");
                      //String sb = writer2.write(buildertext.toString()) ;
                      f.write(out);
                       f.close();
                   }
                   catch (Exception ex) {
                       ex.printStackTrace();
                   }
               }


      }



writer2.close();



      }
       catch (FileNotFoundException a)
       {
          a.printStackTrace();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }

   }
  });

}

  public Dimension getPreferredSize(){
   return new Dimension(850, 350);
   }



  public static void main(String s[]) {








   JLabel  InfoLabel;
   JLabel  Analysis;
   JLabel OutputLabel;

   JLabel OutputLabel2;
   JLabel OutputLabel3;
   JLabel OutputLabel4;
   JLabel OutputLabel5;
   JLabel OutputLabel6;


      JFrame frame = new JFrame("Project 1");

   JPanel panel1 = new JPanel();

   JPanel panel2 = new JPanel();

   JPanel panel3 = new JPanel();

   JPanel panel4 = new JPanel();

   JPanel panel5 = new JPanel();
   JPanel panel6 = new JPanel();
   JPanel panel7 = new JPanel();
   JPanel panel8 = new JPanel();




   InfoLabel = new JLabel("Please select a text file");
   Analysis = new JLabel("Input File :");
   OutputLabel = new JLabel( " #blank lines removed:");
   OutputLabel2 = new JLabel( " # lines:");
   OutputLabel3 = new JLabel( " # words processed:");
   OutputLabel4 = new JLabel( " average  words/line:");
   OutputLabel5 = new JLabel( " average line length:");
   OutputLabel6 = new JLabel( " spaces added:");
   //..Create buttons






   project1main   panel = new project1main();

   ButtonGroup justifyGroup = new ButtonGroup();
   ButtonGroup spacingGroup = new ButtonGroup();

   panel5.add(InfoLabel);
   panel4.add(Analysis);
   panel3.add(panel6);
   panel3.add(panel7);
   panel3.setLayout(new BorderLayout());
   panel3.add(panel8,BorderLayout.NORTH);
   panel3.add(panel7, BorderLayout.CENTER);
   panel3.add(panel6, BorderLayout.SOUTH);
   panel6.add(Left);
   panel6.add(Full);
   panel6.add(Right);
   panel7.add(SingleSpace);
   panel7.add(DoubleSpace);
   panel8.add(new JLabel("Line Length (If no line length inputted, default: 100):"));
   panel8.add(linelength);

   justifyGroup.add(Left);
   justifyGroup.add(Right);
   justifyGroup.add(Full);
   spacingGroup.add(SingleSpace);
   spacingGroup.add(DoubleSpace);

   Left.setSelected(true);
   SingleSpace.setSelected(true);

   panel6.add(OK);
   panel5.add(button);

   JScrollPane n = new JScrollPane(text1);
  // n.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  // n.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);


   //panel4.add(text1);
   panel4.add(n);
   //panel1.setVisible(true);
   //panel3.add(Save);


   frame.add(panel);
   panel.add(panel1);
   panel.add(panel2);
   panel.add(panel3);
   panel.add(panel4);
   panel.add(panel5);


   panel.setLayout(new BorderLayout());
   panel.add(panel2, BorderLayout.NORTH);
   panel.add(panel3, BorderLayout.EAST);
   panel.add(panel4, BorderLayout.CENTER);
   panel.add(panel5, BorderLayout.WEST);
   panel.add(panel1, BorderLayout.SOUTH);


   panel1.setLayout( new GridLayout(12,12));
   panel1.add(OutputLabel);
   panel1.add(text2);

   panel1.add(OutputLabel2);
   panel1.add(text4);
   panel1.add(OutputLabel3);
   panel1.add(text3);
   panel1.add(OutputLabel4);
   panel1.add(text5);
   panel1.add(OutputLabel5);
   panel1.add(text6);
   panel1.add(OutputLabel6);
   panel1.add(text7);





  // panel5.setLayout(new GridLayout(4,1));

   frame.addWindowListener(
   new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
         System.exit(0);
         }
      }
   );
   frame.getContentPane().add(panel,"Center");
   frame.setSize(panel.getPreferredSize());
   frame.setVisible(true);
   }

@Override
public void actionPerformed(ActionEvent e) {
   // TODO Auto-generated method stub

}





















 public static int[] justifyText (String text) {

    int end = charlength, extraSpacesPerWord=0, spillOverSpace=0;
    String[] words;
int[] returnValues = {0, 0, 0};

    System.out.println("Original Text: \n" + text);
    System.out.println("Justified Text: ");

    while(end < text.length()) {


        end = text.lastIndexOf(" ", charlength);

        words = text.substring(0, end).split(" ");
        extraSpacesPerWord = (charlength - end + 1) / words.length;

        spillOverSpace = charlength - end + (extraSpacesPerWord * words.length);
returnValues[0] += words.length; //countWord
    returnValues[1] += (extraSpacesPerWord * words.length) + words.length + spillOverSpace; // countSpaces
       // text6.append(Double.toString(spillOverSpace));
        for(String word: words) {

            //System.out.print(word + " ");
            //System.out.print((extraSpacesPerWord-- > 0) ? " ": "");
           // System.out.print((spillOverSpace-- > 0) ? " ": "");
            writer2.write(word + " ");
            writer2.write((extraSpacesPerWord-- > 0) ? " ": "");
            writer2.write((spillOverSpace-- > 0) ? " ": "");

        }

       // System.out.print("\n");
        writer2.write("\n");
returnValues[2]++; // lines

        if(DoubleSpace.isSelected()) {
           writer2.write("\n");
           returnValues[2]++; // lines
         }
        text = text.substring(end+1);

        if(text.length() < charlength)
        {
           writer2.write(text);
         //  System.out.println(text);
        }
       // text3.append(Integer.toString(words.length));
        //text7.append(Integer.toString(extraSpacesPerWord));
    }
 return returnValues;
   //return text;
}


}











