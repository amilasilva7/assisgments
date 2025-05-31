package org.ostech.tng.services;

import org.ostech.tng.DTO.responseDTOs.TrackingNumberResponse.TrackingNumberResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TrackingNumberService {

    private final Set<String> generatedTrackingNumbers = ConcurrentHashMap.newKeySet();

    public TrackingNumberResponse generateTrackingNumber(String originCountryId, String destinationCountryId, BigDecimal weight,
                                                         String createdAt, String customerId, String customerName, String customerSlug) {

        String trackingNumber;
        do {
            trackingNumber = generateUniqueTrackingNumber(originCountryId, destinationCountryId, weight, createdAt, customerId, customerName, customerSlug);
        } while (generatedTrackingNumbers.contains(trackingNumber));

        generatedTrackingNumbers.add(trackingNumber);

        TrackingNumberResponse response = new TrackingNumberResponse();
        response.setTrackingNumber(trackingNumber);
        response.setCreatedAt(Instant.now().toString());
        return response;
    }

    private String generateUniqueTrackingNumber(String originCountryId, String destinationCountryId, BigDecimal weight,
                                                String createdAt, String customerId, String customerName, String customerSlug) {

        String base = originCountryId + destinationCountryId + weight.toPlainString() + createdAt + customerId + customerSlug;
        String randomSuffix = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return base.substring(0, Math.min(base.length(), 8)) + randomSuffix;
    }
}

