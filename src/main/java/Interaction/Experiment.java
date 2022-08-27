package Interaction;

import Generator.IncompletePreference;
import MatchingModels.MaxMatching.MaxMatching;
import MatchingModels.MaxPopularity.MainMP;
import MatchingModels.SMI.SMI;
import Structure.BasicStructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;

public class Experiment {
    static BasicStructure[] students, lecturers;
    static Stack<BasicStructure> studentStack;
    static int matchingSize, blockingSize, costA, costB, costAll;
    static int[] profileA, profileB, profileAll, newProfileA, newProfileB, newProfileAll;

    public static void main(String[] args) throws IOException {
        Experiment.experiment();
    }

    public static void experiment() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet_SMI = workbook.createSheet("SMI");
        XSSFSheet spreadsheet_SMI_profileA = workbook.createSheet("SMI_Profile_Men");
        XSSFSheet spreadsheet_SMI_profileB = workbook.createSheet("SMI_Profile_Women");
        XSSFSheet spreadsheet_SMI_profileAll = workbook.createSheet("SMI_Overall_Profile");
        XSSFSheet spreadsheet_PM = workbook.createSheet("PM");
        XSSFSheet spreadsheet_PM_profileA = workbook.createSheet("PM_Profile_Men");
        XSSFSheet spreadsheet_PM_profileB = workbook.createSheet("PM_Profile_Women");
        XSSFSheet spreadsheet_PM_profileAll = workbook.createSheet("PM_Overall_Profile");
        XSSFSheet spreadsheet_MM = workbook.createSheet("MM");
        XSSFSheet spreadsheet_MM_profileA = workbook.createSheet("MM_Profile_Men");
        XSSFSheet spreadsheet_MM_profileB = workbook.createSheet("MM_Profile_Women");
        XSSFSheet spreadsheet_MM_profileAll = workbook.createSheet("MM_Overall_Profile");

        int N = 1000;
        int min = 1;
        int max = 30;
        profileA = new int[N];
        profileB = new int[N];
        profileAll = new int[N];
        initiateSheet(spreadsheet_SMI, spreadsheet_SMI_profileA, spreadsheet_SMI_profileB, spreadsheet_SMI_profileAll, max);
        initiateSheet(spreadsheet_PM, spreadsheet_PM_profileA, spreadsheet_PM_profileB, spreadsheet_PM_profileAll, max);
        initiateSheet(spreadsheet_MM, spreadsheet_MM_profileA, spreadsheet_MM_profileB, spreadsheet_MM_profileAll, max);
        for (int i = 0; i<1000; i++) {
            IncompletePreference rg5 = new IncompletePreference(N, min, max);
            rg5.generateData();
            students = rg5.students;
            lecturers = rg5.lecturers;
            studentStack = rg5.studentStack;

            SMI.match(studentStack);
            statistics();
            writeToSheet(i+1, spreadsheet_SMI, spreadsheet_SMI_profileA, spreadsheet_SMI_profileB, spreadsheet_SMI_profileAll, N);

            MainMP.purePM(students, lecturers, studentStack);
            statistics();
            writeToSheet(i+1, spreadsheet_PM, spreadsheet_PM_profileA, spreadsheet_PM_profileB, spreadsheet_PM_profileAll, N);

            reset();
            MaxMatching maxMatching5 = new MaxMatching(students, lecturers, studentStack);
            maxMatching5.pureMM();
            statistics();
            writeToSheet(i+1, spreadsheet_MM, spreadsheet_MM_profileA, spreadsheet_MM_profileB, spreadsheet_MM_profileAll, N);
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

    public static void initiateSheet(XSSFSheet sheet_stat, XSSFSheet sheet_pa, XSSFSheet sheet_pb, XSSFSheet sheet_pc, int n) {
        Row row = sheet_stat.createRow(0);
        row.createCell(0).setCellValue("No.");
        row.createCell(1).setCellValue("Matching Size");
        row.createCell(2).setCellValue("Matching Rate(%)");
        row.createCell(3).setCellValue("Blocking Size");
        row.createCell(4).setCellValue("Cost of Men");
        row.createCell(5).setCellValue("Cost of Women");
        row.createCell(6).setCellValue("Overall Cost");
        row.createCell(7).setCellValue("Cost of Men / Matching Size");
        row.createCell(8).setCellValue("Cost of Women / Matching Size");
        row.createCell(9).setCellValue("Overall Cost / Matching Size / 2");
        row.createCell(10).setCellValue("Profile Length Men");
        row.createCell(11).setCellValue("Profile Length Women");
        row.createCell(12).setCellValue("Profile Length Overall");
        Row row1 = sheet_pa.createRow(0);
        row1.createCell(0).setCellValue("No.");
        for (int k=1; k<=n; k++) {
            row1.createCell(k).setCellValue("Rank " + k);
        }
        Row row2 = sheet_pb.createRow(0);
        row2.createCell(0).setCellValue("No.");
        for (int k=1; k <= n; k++) {
            row2.createCell(k).setCellValue("Rank " + k);
        }
        Row row3 = sheet_pc.createRow(0);
        row3.createCell(0).setCellValue("No.");
        for (int k=1; k <= n; k++) {
            row3.createCell(k).setCellValue("Rank " + k);
        }
    }

    public static void writeToSheet(int i, XSSFSheet sheet_stat, XSSFSheet sheet_pa, XSSFSheet sheet_pb, XSSFSheet sheet_pc, int N) {
        Row row = sheet_stat.createRow(i);
        row.createCell(0).setCellValue(String.valueOf(i));
        row.createCell(1).setCellValue(matchingSize);
        row.createCell(2).setCellValue((float)matchingSize*100/N);
        row.createCell(3).setCellValue(blockingSize);
        row.createCell(4).setCellValue(costA);
        row.createCell(5).setCellValue(costB);
        row.createCell(6).setCellValue(costAll);
        row.createCell(7).setCellValue((float) costA/matchingSize);
        row.createCell(8).setCellValue((float) costB/matchingSize);
        row.createCell(9).setCellValue((float) costAll/matchingSize/2);
        row.createCell(10).setCellValue(newProfileA.length);
        row.createCell(11).setCellValue(newProfileB.length);
        row.createCell(12).setCellValue(newProfileAll.length);
        Row row1 = sheet_pa.createRow(i);
        row1.createCell(0).setCellValue(String.valueOf(i));
        for (int k=0; k< newProfileA.length; k++) {
            row1.createCell(k+1).setCellValue(newProfileA[k]);
        }
        Row row2 = sheet_pb.createRow(i);
        row2.createCell(0).setCellValue(String.valueOf(i));
        for (int k=0; k< newProfileB.length; k++) {
            row2.createCell(k+1).setCellValue(newProfileB[k]);
        }
        Row row3 = sheet_pc.createRow(i);
        row3.createCell(0).setCellValue(String.valueOf(i));
        for (int k=0; k< newProfileAll.length; k++) {
            row3.createCell(k+1).setCellValue(newProfileAll[k]);
        }
    }

    public static void statistics() {
        matchingSize = 0;
        blockingSize = 0;
        costA = 0;
        costB = 0;
        costAll = 0;
        Arrays.fill(profileA, 0);
        Arrays.fill(profileB, 0);
        Arrays.fill(profileAll, 0);

        for (BasicStructure s: students) {
            if (!s.getFree()) { // s is matched
                matchingSize++;
                costA += s.getPreferencePointer()+1;
                profileA[s.getPreferencePointer()]++;
                for (BasicStructure l: s.getPreferenceList()) {
                    if (s.getRankingList()[l.getID()-1] < (s.getPreferencePointer()+1)) {
                        if (l.getRankingList()[s.getID()-1] < (l.getPreferencePointer()+1)) {
                            blockingSize++; // (s,l) forms a blocking pair when they prefer each other to their partners
                        }
                    }
                }
            } else {   // s is unmatched,
                // if l is unmatched or l is matched but prefer s to his partner,
                // then (s,l) form a blocking pair
                 for (BasicStructure l: s.getPreferenceList()) {
                    if (l.getFree()){
                        System.out.println("Current matching is not stable or maximum");
                        blockingSize++; // This shall not happen because it can add to the matching size
                    } else if (l.getRankingList()[s.getID()-1] < (l.getPreferencePointer()+1)){
                        blockingSize++;
                    }
                }
            }
        }
        int length = profileA.length;
        for (int i = 0; i< profileA.length; i++){
            if (profileA[length-1] == 0) {
                length -= 1;
            } else {
                break;
            }
        }
        newProfileA = Arrays.copyOfRange(profileA, 0, length);
        
        for(BasicStructure l:lecturers) {
            if(!l.getFree()){
                costB += l.getPreferencePointer()+1;
                profileB[l.getPreferencePointer()]++;
            }
        }
        length = profileB.length;
        for (int i = 0; i< profileB.length; i++){
            if (profileB[length-1] == 0) {
                length -= 1;
            } else {
                break;
            }
        }
        newProfileB = Arrays.copyOfRange(profileB, 0, length);

        costAll = costA + costB;
        length = Math.max(newProfileA.length, newProfileB.length);
        for(int i=0; i<length; i++){
            profileAll[i] = profileA[i] + profileB[i];
        }
        newProfileAll = Arrays.copyOfRange(profileAll, 0, length);
    }

    public static void reset() {
        for (BasicStructure s: students) {
            s.setPartner(null);
            s.setFree(true);
            s.setPreferencePointer(0);
        }
        for (BasicStructure l: lecturers) {
            l.setPartner(null);
            l.setFree(true);
            l.setPreferencePointer(0);
        }
    }
}
