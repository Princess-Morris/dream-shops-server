package com.princess.dream_shops.service.image;

import org.springframework.web.multipart.MultipartFile;

import com.princess.dream_shops.model.Image;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    Image saveImage(MultipartFile file, Long productId);
    void updateImage(MultipartFile file, Long productId);
}
