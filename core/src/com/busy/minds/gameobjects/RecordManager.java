package com.busy.minds.gameobjects;

import java.io.*;

/**
 * Created by Jan on 02.01.2018.
 */
public class RecordManager {

    public RecordManager() {
        File recordFile = new File(filename);

        try(FileReader reader = new FileReader(recordFile)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            record = Integer.parseInt(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SetRecord(int points) {
        record = points;

        File recordFile = new File(filename);

        try(FileWriter writer = new FileWriter(recordFile)) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(String.valueOf(record));
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int GetRecord(){
        return record;
    }

    private final String filename = "record_file";
    private int record;
}
