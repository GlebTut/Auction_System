package com.Services;

import com.DAO.SellerDAO;
import com.Entities.Seller;
import java.sql.SQLException;

public class SellerService {

    public static void registerSeller(Seller seller) throws SQLException {
        // Ensure that admin privileges is not null or empty 
        if (seller.getEarnings() < 0) {
            throw new IllegalArgumentException("Earning can't be negative.");
        }
        SellerDAO.createSeller(seller);
    }

}
