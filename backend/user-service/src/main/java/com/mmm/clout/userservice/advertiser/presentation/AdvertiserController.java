package com.mmm.clout.userservice.advertiser.presentation;

import com.mmm.clout.userservice.advertiser.application.facade.AdvertiserFacade;
import com.mmm.clout.userservice.advertiser.presentation.request.CreateAdrRequest;
import com.mmm.clout.userservice.advertiser.presentation.request.UpdateAdrRequest;
import com.mmm.clout.userservice.advertiser.presentation.response.CreateAdrResponse;
import com.mmm.clout.userservice.advertiser.presentation.response.SelectAdrResponse;
import com.mmm.clout.userservice.advertiser.presentation.response.UpdateAdrResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/advertisers")
@RequiredArgsConstructor
public class AdvertiserController {

    private final AdvertiserFacade advertiserFacade;

    @PostMapping
    public ResponseEntity<CreateAdrResponse> create(
        @RequestBody @Valid CreateAdrRequest createAdrRequest
    ) {
        CreateAdrResponse result = CreateAdrResponse.from(
            advertiserFacade.create(createAdrRequest.toCommand())
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{advertiserId}")
    public ResponseEntity<UpdateAdrResponse> update(
            @PathVariable("advertiserId") Long advertiserId,
            @RequestBody @Valid UpdateAdrRequest updateAdrRequest
    ) {
        UpdateAdrResponse result = UpdateAdrResponse.from(
                advertiserFacade.update(updateAdrRequest.toCommand(advertiserId))
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{advertiserId}")
    public ResponseEntity<SelectAdrResponse> select(
            @PathVariable("advertiserId") Long advertiserId
    ) {
        SelectAdrResponse result = SelectAdrResponse.from(advertiserFacade.select(advertiserId));
        return new ResponseEntity<SelectAdrResponse>(result, HttpStatus.OK);
    }
}