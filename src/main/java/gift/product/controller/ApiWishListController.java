package gift.product.controller;

import gift.product.model.WishProduct2;
import gift.product.service.WishListService;
import gift.product.util.CertifyUtil;
import gift.product.validation.WishListValidation;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class ApiWishListController {

    private final WishListService wishListService;
    private final CertifyUtil certifyUtil;
    private final WishListValidation wishListValidation;

    @Autowired
    public ApiWishListController(WishListService wishListService, CertifyUtil certifyUtil, WishListValidation wishListValidation) {
        this.wishListService = wishListService;
        this.certifyUtil = certifyUtil;
        this.wishListValidation = wishListValidation;
    }

    @GetMapping()
    public ResponseEntity<List<WishProduct2>> showProductList(HttpServletRequest request) {
        System.out.println("[ApiWishListController] showProductList()");
        List<WishProduct2> productList = new ArrayList<>(wishListService.getAllProducts(request));
        return ResponseEntity.ok(productList);
    }

    @PostMapping()
    public ResponseEntity<String> registerWishProduct(HttpServletRequest request, @RequestBody Map<String, Long> requestBody) {
        System.out.println("[ApiWishListController] registerWishProduct()");

        return wishListService.registerWishProduct(request, requestBody);
    }

    @PutMapping()
    public ResponseEntity<String> updateCountWishProduct(HttpServletRequest request, @RequestBody Map<String, Long> requestBody) {
        System.out.println("[ApiWishListController] updateCountWishProduct()");

        if(requestBody.get("count") == 0)
            return deleteWishProduct(request, requestBody);

        return wishListService.updateCountWishProduct(request, requestBody);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteWishProduct(HttpServletRequest request, @RequestBody Map<String, Long> requestBody) {
        System.out.println("[ApiWishListController] deleteWishProduct()");

        return wishListService.deleteWishProduct(request, requestBody);
    }
}
