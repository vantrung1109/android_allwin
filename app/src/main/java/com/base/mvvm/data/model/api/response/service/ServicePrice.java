package com.base.mvvm.data.model.api.response.service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicePrice {

    StartPrice startPrice;
    List<PriceRange> prices;
    Double nextPrices;

    public static double calculatePrice(double distance, ServicePrice servicePrice){
        double totalPrice = 0;
        // Case 1: if distance is less than the start price distance
        if(distance < servicePrice.getStartPrice().getDistance()){
            totalPrice = servicePrice.getStartPrice().getPrice();
        }
        // case 2: if have the next price
        else if(servicePrice.getNextPrices() != null && distance > servicePrice.getStartPrice().getDistance()){
            totalPrice = servicePrice.getStartPrice().getPrice();
            totalPrice += (distance - servicePrice.getStartPrice().getDistance())/1000 * servicePrice.getNextPrices();
        }
        else
        {
            totalPrice += servicePrice.getStartPrice().getPrice();
            for (int i = 0; i < servicePrice.getPrices().size(); i++) {
                PriceRange priceRange = servicePrice.getPrices().get(i);

                // Giá trị lớn nhất
                if (i == servicePrice.getPrices().size() - 1 &&
                        priceRange.getTo() == null &&
                        distance >= priceRange.getFrom()){
                    totalPrice += (distance - priceRange.getFrom())/1000 * priceRange.getPrice();
                    break;
                }

                // Case 3: if distance is in the range of the price range
                if (distance >= priceRange.getFrom() && distance <= priceRange.getTo()) {
                    totalPrice += (distance - priceRange.getFrom())/1000 * priceRange.getPrice();
                }
                // Case 4: if distance is greater than the range of the price range
                else if (priceRange.getTo() != null && distance > priceRange.getTo() ) {
                    totalPrice += (double) (priceRange.getTo() - priceRange.getFrom()) /1000 * priceRange.getPrice();
                }
            }
        }
        return totalPrice;
    }
}

