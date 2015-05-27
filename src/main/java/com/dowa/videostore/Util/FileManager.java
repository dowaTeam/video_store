package com.dowa.videostore.Util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.primefaces.model.UploadedFile;

/**
 * Created by Andres on 27/05/2015.
 */
public class FileManager {
    private static final int BUFFER_SIZE = 6124;
    private static final String folderToUpload = "//resources//images//movies//";

    private FileManager(){
    }

    public static void handleFileUpload(UploadedFile uploadedFile){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        File result = new File(externalContext.getRealPath(folderToUpload+uploadedFile.getFileName()));
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte [BUFFER_SIZE];
            int bulk;
            InputStream inputStream = uploadedFile.getInputstream();
            while(true){
                bulk = inputStream.read(buffer);
                if (bulk < 0){
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            System.out.println("Archivo guardado con exito");
        }catch (IOException e){
            System.err.println("IOException: " + e.getMessage());
        }
    }

    public static void deleteFile(String fileName){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try{
            File file = new File(externalContext.getRealPath(folderToUpload + fileName));
            if (file.exists()){
                file.delete();
            }
        }catch(Exception e){
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
