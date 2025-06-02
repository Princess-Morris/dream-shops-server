package com.princess.dream_shops.controller;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.princess.dream_shops.dto.ImageDto;
import com.princess.dream_shops.model.Image;
import com.princess.dream_shops.response.ApiResponse;
import com.princess.dream_shops.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;



@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId){
         try{
             List<ImageDto> imageDtos = imageService.saveImages(files, productId);
             return ResponseEntity.ok(new ApiResponse("upload success!", imageDtos));
         } catch (Exception e){
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("upload failed!", e.getMessage()));
         }
    }

    
    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@RequestParam Long productId, @RequestParam Long imageId){
        // added the try-catch block to remove error
        try{
            Image image = imageService.getImageById(imageId);
            ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +image.getFileName() + "\"")
            .body(resource);
        } catch(Exception e){
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(null);
        }
        
    }


}
