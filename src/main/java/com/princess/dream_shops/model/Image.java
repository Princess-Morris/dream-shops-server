package com.princess.dream_shops.model;

import java.sql.Blob;
import java.util.List;

public class Image {
    private Long id;
    private String fileName;
    private String fileType;
    private Blob image;
    private String downloadUrl;

    private List<Product> products;
}
