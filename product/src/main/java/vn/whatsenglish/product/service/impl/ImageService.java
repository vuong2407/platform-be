package vn.whatsenglish.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.whatsenglish.product.constant.Messages;
import vn.whatsenglish.product.entity.Image;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.exception.BadRequestException;
import vn.whatsenglish.product.repository.ImageRepository;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> saveAllImages(List<String> imageUrls, Product product) {
        if (imageUrls.isEmpty()) {
            throw new BadRequestException(Messages.IMAGE_URL_LIST_NOT_EMPTY);
        }
        List<Image> images = imageUrls.stream().map(url -> new Image(url, product)).toList();
        return imageRepository.saveAll(images);
    }
}
