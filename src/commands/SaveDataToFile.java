package commands;

import GUI.Storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import old.school.People;
import old.school.Botherable;
import old.school.Chatable;
import old.school.InterfaceAdapter;
import old.school.Missable;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by slavik on 11.04.17.
 */
public final class SaveDataToFile implements Runnable {

    @Override
    public void run() {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Missable.class, new InterfaceAdapter<People>());
        builder.registerTypeAdapter(Chatable.class, new InterfaceAdapter<People>());
        builder.registerTypeAdapter(Botherable.class, new InterfaceAdapter<People>());

        Gson gson = builder.create();

        try (PrintWriter printWriter = new PrintWriter("objects")) {
            printWriter.println(gson.toJson(Storage.getInstanceOf().getFamily()));
        }catch (FileNotFoundException ex ){
            try(PrintWriter writeLog = new PrintWriter("../out/log")) {
                writeLog.write(ex.getMessage()+"\n");
            } catch (FileNotFoundException e) {
                //doNothing();
            }
        }
    }
}
