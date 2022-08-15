package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    @PostMapping(value="/upload")
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file,
                                                 Model model){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(Files.newOutputStream(Paths.get(name)));
                stream.write(bytes);
                stream.close();
                return "File uploaded";
            } catch (Exception e) {
                return "File did not upload\nMessage: " + e.getLocalizedMessage();
            }
        } else {
            return "File was empty";
        }
    }
}
