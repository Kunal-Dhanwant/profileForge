package com.profileForge.service.ServiceImpl;

import com.profileForge.exception.ImageBadApiRequest;
import com.profileForge.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl  implements FileService{


    @Override
    public String uploadImage(MultipartFile file, String path) throws IOException {
        // TODO Auto-generated method stub

        String originalFilename = file.getOriginalFilename();

        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filenamewithextension = filename+extension;

        String fullpathwithFileName = path+filenamewithextension;
        log.info("file path name  {} " + fullpathwithFileName );



        if(extension.equalsIgnoreCase(".png")
                || extension.equalsIgnoreCase(".jpg")|| extension.equalsIgnoreCase(".jpeg")
        || extension.equalsIgnoreCase(".pdf")) {

            log.info("file extension is{]  "+ extension);
            File folder = new File(path);
            if(!folder.exists()) {
                // create the folder
                folder.mkdirs();
            }

            // upload

            Files.copy(file.getInputStream(), Paths.get(fullpathwithFileName));
        }else {
            throw new ImageBadApiRequest("File with this " + extension + " is not allowed");
        }

        return filenamewithextension;
    }

    @Override
    public InputStream getResources(String path, String name) throws FileNotFoundException {
        // TODO Auto-generated method stub

        String   fullpath = path + File.separator+ name;
        InputStream inputStream = new FileInputStream(fullpath);

        return inputStream;
    }


}
