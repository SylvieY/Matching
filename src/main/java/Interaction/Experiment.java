package Interaction;

import Checking.CheckStable;
import Generator.IncompletePreference;
import MatchingModels.MaxMatching.MaxMatching;
import MatchingModels.MaxPopularity.MainMP;
import MatchingModels.SMI.SMI;
import structure.BasicStructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import static Interaction.MainClass.*;
import static Interaction.MainClass.profile;
import static Interaction.Utils.*;
import static Interaction.Utils.tableGeneration;

public class Experiment {
    static BasicStructure[] students, lecturers;
    static Stack<BasicStructure> studentStack;
    static int matchingSize, cost;
    static int[] profile;

    public static void main(String[] args) throws IOException {
        Experiment.experiment();
    }

    public static void experiment() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet_SMI = workbook.createSheet("SMI");
        XSSFSheet spreadsheet_PM = workbook.createSheet("PM");
        XSSFSheet spreadsheet_MM = workbook.createSheet("MM");
        Map<Integer, int[]> smi_stat = new TreeMap<>();
        Map<Integer, int[]> smi_stat_profile = new TreeMap<>();
        Map<Integer, int[]> pm_stat = new TreeMap<>();
        Map<Integer, int[]> pm_stat_profile = new TreeMap<>();
        Map<Integer, int[]> mm_stat = new TreeMap<>();
        Map<Integer, int[]> mm_stat_profile = new TreeMap<>();
        int N = 100;
        int min = 1;
        int max = 5;
        profile = new int[N];
        for (int i = 0; i<100; i++) {
            matchingSize = 0;
            cost = 0;
            Arrays.fill(profile, 0);
            IncompletePreference rg5 = new IncompletePreference(N, min, max);
            rg5.generateData();
            students = rg5.students;
            lecturers = rg5.lecturers;
            studentStack = rg5.studentStack;
            SMI.match(studentStack);
            int[] newProfile = statistics();
            smi_stat.put(i, new int[]{matchingSize, cost});
            smi_stat_profile.put(i, newProfile);
            MainMP.purePM(students, lecturers, studentStack);
            newProfile = statistics();
            pm_stat.put(i, new int[]{matchingSize, cost});
            pm_stat_profile.put(i, newProfile);
            MaxMatching maxMatching5 = new MaxMatching(students, lecturers, studentStack);
            maxMatching5.pureMM();
            newProfile = statistics();
            mm_stat.put(i, new int[]{matchingSize, cost});
            mm_stat_profile.put(i, newProfile);
        }
        int rowNo = 0;
        for (int i=0; i<N; i++) {
            Row row1 = spreadsheet_SMI.createRow(rowNo);
            row1.createCell(0).setCellValue(smi_stat.get(i)[0]);
            row1.createCell(1).setCellValue(smi_stat.get(i)[1]);
            row1.createCell(2).setCellValue(Arrays.toString(smi_stat_profile.get(i)));
            Row row2 = spreadsheet_PM.createRow(rowNo);
            row2.createCell(0).setCellValue(pm_stat.get(i)[0]);
            row2.createCell(1).setCellValue(pm_stat.get(i)[1]);
            row2.createCell(2).setCellValue(Arrays.toString(pm_stat_profile.get(i)));
            Row row3 = spreadsheet_MM.createRow(rowNo);
            row3.createCell(0).setCellValue(mm_stat.get(i)[0]);
            row3.createCell(1).setCellValue(mm_stat.get(i)[1]);
            row3.createCell(2).setCellValue(Arrays.toString(mm_stat_profile.get(i)));
            rowNo++;
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("Statistics.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Statistics.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] statistics() {
        for (BasicStructure s: students) {
            if (!s.getFree()) {
                matchingSize++;
                cost += s.getPreferencePointer()+1;
                profile[s.getPreferencePointer()]++;
            }
        }
        int length = profile.length;
        for (int i = 0; i< profile.length; i++){
            if (profile[length-1] == 0) {
                length -= 1;
            } else {
                break;
            }
        }
        int[] newProfile = Arrays.copyOfRange(profile, 0, length);
        return newProfile;
    }
}
