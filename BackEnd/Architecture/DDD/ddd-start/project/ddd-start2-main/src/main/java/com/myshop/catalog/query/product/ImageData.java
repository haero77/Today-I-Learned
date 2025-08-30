package com.myshop.catalog.query.product;

import java.time.LocalDateTime;
import javax.persistence.Column;

public class ImageData {
  @Column(name = "image_path")
  private String path;

  private LocalDateTime uploadTime;
}
