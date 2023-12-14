package vn.whatsenglish.product.service;

import vn.whatsenglish.product.entity.Image;
import vn.whatsenglish.product.entity.Product;

import java.util.List;

public interface IImageService {
    List<Image> saveAllImages(List<String> imageUrls, Product product);
}
