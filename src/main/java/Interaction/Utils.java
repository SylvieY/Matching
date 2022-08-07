package Interaction;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static Interaction.MainClass.*;

public class Utils {

    public static void createAgents(int N) {
        students = new BasicStructure[N];
        lecturers = new BasicStructure[N];
        studentStack = new Stack<>();
        for (int i=0; i<N; i++) {
            students[i] = new Student(i+1,N);
            lecturers[i] = new Lecturer(i+1,N);
            studentStack.push(students[i]);
        }
        List<String> data;
        scanner.nextLine();
        System.out.println("Please get your bipartite data ready and paste here: ");
        for (int i=0; i<N; i++) {
            data = List.of(scanner.nextLine().split("[:,]"));
            BasicStructure s = students[i];
            for (int k=1; k<data.size(); k++) {
                int lid = Integer.parseInt(data.get(k));
                BasicStructure l = lecturers[lid-1];
                s.setPreference(l);
                s.setRank(lid-1, k);
            }
            s.setPreferenceList(s.getPreferenceAsArray());
        }
        for (int i=N; i<N*2; i++) {
            data = List.of(scanner.nextLine().split("[:,]"));
            BasicStructure l = lecturers[i-N];
            for (int k=1; k<data.size(); k++) {
                int sid = Integer.parseInt(data.get(k));
                BasicStructure s = students[sid-1];
                l.setPreference(s);
                l.setRank(sid-1, k);
            }
            l.setPreferenceList(l.getPreferenceAsArray());
        }
        scanner.close();
    }


    public static File createFile(String d) throws IOException {
        File dir = new File("./src/main/java/data/");
        if (!dir.exists()){
            dir.mkdir();
        }
        File directory = new File("./src/main/java/data/"+d);
        String path = null;
        try {
            path = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert path != null;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    public static void outputData() throws IOException {
        fw = new FileWriter(file, true);
        System.out.println("---------------Data---------------");
        fw.write("---------------Data---------------\n");
        for (BasicStructure s: students) {
            System.out.println(s + " preference : "  + s.preferenceListToString());
            System.out.println(s + " rank list : "  + s.rankingListToString());
            fw.write(s + " preference : "  + s.preferenceListToString() + "\n");
            fw.write(s + " rank list : "  + s.rankingListToString() + "\n");
        }
        for (BasicStructure l: lecturers) {
            System.out.println(l + " preference : "  + l.preferenceListToString());
            System.out.println(l + " rank list : "  + l.rankingListToString());
            fw.write(l + " preference : "  + l.preferenceListToString() + "\n");
            fw.write(l + " rank list : "  + l.rankingListToString() + "\n");
        }
        fw.close();
    }

    public static void outputMatching(String algorithmName) throws IOException {
        matchingSize = 0;
        cost = 0;
        Arrays.fill(profile, 0);
        fw = new FileWriter(file, true);
        System.out.println("---------------" + algorithmName + "---------------");
        fw.write("---------------" + algorithmName + "---------------\n");
        for (BasicStructure s: students) {
            if (!s.getFree()) {
                System.out.println(s + " : " + s.getPartner());
                fw.write(s + " : " + s.getPartner() + "\n");
                matchingSize++;
                cost += s.getPreferencePointer()+1;
                profile[s.getPreferencePointer()]++;
            }
        }
        tabelCell.add(new String[]{algorithmName, String.valueOf(matchingSize), String.valueOf(cost), Arrays.toString(profile)});
        for (BasicStructure s: students) {
            if (s.getFree()) {
                System.out.println(s + " : Unmatched");
                fw.write(s + " : Unmatched \n");
            }
        }
        for (BasicStructure l: lecturers) {
            if (l.getFree()) {
                System.out.println(l + " : Unmatched");
                fw.write(l + " : Unmatched \n");
            }
        }
        System.out.println("---------------Over---------------");
        fw.write("---------------Over---------------\n");
        fw.close();
    }

    public static void outputChecking(String type, Boolean check) throws IOException {
        System.out.println("---------------" + type + " Check: " + check + "---------------");
        fw = new FileWriter(file, true);
        fw.write("---------------" + type + " Check: " + check + "---------------\n");
        fw.close();
    }

    public static void outputAnalysis() throws IOException {
        fw = new FileWriter(file, true);
        System.out.println("Matching Size: " + matchingSize);
        System.out.println("Cost: " + cost);
        System.out.println("Profile: " + Arrays.toString(profile));
        fw.write("Matching Size: " + matchingSize + "\n");
        fw.write("Cost: " + cost + "\n");
        fw.write("Profile: " + Arrays.toString(profile) + "\n");
        fw.close();
    }

    public static void tableGeneration() {
        int fontSize = 15;
        int rowCount = tabelCell.size() + 2;
        int colCount = 4;
        int startHeight = 10;
        int startWidth = 10;
        int colWidth = 400;
        int imgWidth = colWidth * 4 + 20;
        int rowHeight = 50;
        int imgHeight = rowHeight * rowCount + 20;

        BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = img.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,imgWidth, imgHeight);

        for(int i=0; i<rowCount; i++) {
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth, startHeight + (i+1)*rowHeight, startWidth+colWidth*colCount, startHeight+(i+1)*rowHeight);
        }
        for (int j=0; j<colCount; j++) {
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth+j*colWidth, startHeight+rowHeight, startWidth+j*colWidth, startHeight+rowHeight*rowCount);
        }

        Font font = new Font("Computer Modern Roman", Font.BOLD, fontSize);
        graphics.setFont(font);
        String title = "Data Table";
        graphics.drawString(title, startWidth, startHeight+rowHeight-10);
        graphics.drawString("Size", startWidth+colWidth+5, startHeight+rowHeight+20);
        graphics.drawString("Cost", startWidth+colWidth*2+5, startHeight+rowHeight+20);
        graphics.drawString("Profile", startWidth+colWidth*3+5, startHeight+rowHeight+20);
        for (int n=0; n<tabelCell.size(); n++) {
            String[] cell = tabelCell.get(n);
            for (int k=0; k<cell.length; k++) {
                graphics.drawString(cell[k], startWidth+colWidth*k+5,startHeight+rowHeight*(n+2)+20);
            }
        }
        createImage(img, "DataComparison.jpg");
    }

    public static void createImage(BufferedImage img, String d){
        File dir = new File("./src/main/java/data/" + d);
        String path = null;
        try {
            path = dir.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert path != null;
        try{
            ImageIO.write(img, "jpg", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
