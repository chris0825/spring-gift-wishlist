package gift.product.service;

import gift.product.dao.WishListDao;
import gift.product.model.WishProduct;
import gift.product.util.CertifyUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import gift.product.model.WishProduct2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private final WishListDao wishListDao;
    private final CertifyUtil certifyUtil;

    @Autowired
    public WishListService(WishListDao wishListDao, CertifyUtil certifyUtil) {
        this.wishListDao = wishListDao;
        this.certifyUtil = certifyUtil;
        wishListDao.createWishListTable();
    }

    public Collection<WishProduct2> getAllProducts(HttpServletRequest request) {
        String token = certifyUtil.checkAuthorization(request.getHeader("Authorization"));
        return wishListDao.getAllProducts(certifyUtil.getEmailByToken(token));
    }

    public void registerWishProduct(WishProduct wProduct) {
        wishListDao.registerWishProduct(wProduct);
    }

    public void updateCountWishProduct(Long pId, int count, String email) {
        wishListDao.updateCountWishProduct(pId, count, email);
    }

    public void deleteWishProduct(Long pId, String email) {
        wishListDao.deleteWishProduct(pId, email);
    }

    public boolean existsByPId(Long pId, String email) {
        return wishListDao.existsByPId(pId, email);
    }

    @Service
    public static class TokenService {
        private final ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();

        public void logIn(String email, String token) {
            tokenMap.put(email, token);
        }

        public void logOut(String email) {
            tokenMap.remove(email);
        }

        public String getToken(String email) {
            return tokenMap.get(email);
        }
    }
}
