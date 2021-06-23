package com.exam.cbt.azure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.models.BlobProperties;

@Service
public class AzureBlobAdapter {

    @Autowired
    BlobClientBuilder client;

    
    public List<String> upload(List<MultipartFile> files, String prefixName) {
    	
    	List<String> fileNames = new ArrayList<>();
       for (MultipartFile file:files) {
    	   if(file != null && file.getSize() > 0) {
               try {
                   //implement your own file name logic.
                   String fileName = prefixName+ file.getOriginalFilename();
                   BlobClient str = client.blobName(fileName).buildClient();
                   if (!str.exists()) {
                	   client.blobName(fileName).buildClient().upload(file.getInputStream(),file.getSize());
                   }
                  
                   fileNames.add(fileName);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
    	   
       }
    	
        return fileNames;
    }

    public byte[] getFile(String name) {
        try {
            File temp = new File("/temp/"+name);
            BlobProperties properties = client.blobName(name).buildClient().downloadToFile(temp.getPath());
            byte[] content = Files.readAllBytes(Paths.get(temp.getPath()));
            temp.delete();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteFile(String name) {
        try {
            client.blobName(name).buildClient().delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}