package org.ostech.tng.controllers;

import org.ostech.tng.DTO.responseDTOs.TrackingNumberResponse.TrackingNumberResponse;
import org.ostech.tng.constants.Constants;
import org.ostech.tng.services.TrackingNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(Constants.URLs.NEXT_TRACKING_NUMBER_V1)
public class TrackingNumberController {

    //TODO:: API authentication and authorization has to be implemented
    //TODO:: API request data validations should be handled.
    //TODO:: Custom errors should be handled.

    private final TrackingNumberService trackingNumberService;

    @Autowired
    public TrackingNumberController(TrackingNumberService trackingNumberService) {
        this.trackingNumberService = trackingNumberService;
    }

    @GetMapping()
    public ResponseEntity<TrackingNumberResponse> generateTrackingNumber(
        @RequestParam String originCountryId,
        @RequestParam String destinationCountryId,
        @RequestParam BigDecimal weight,
        @RequestParam String createdAt,
        @RequestParam String customerId,
        @RequestParam String customerName,
        @RequestParam String customerSlug) {

        TrackingNumberResponse response = trackingNumberService.generateTrackingNumber(
            originCountryId, destinationCountryId, weight, createdAt, customerId, customerName, customerSlug);

        return ResponseEntity.ok(response);
    }

}
