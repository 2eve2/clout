package com.mmm.clout.userservice.clouter.presentation;

import com.mmm.clout.userservice.clouter.application.facade.ClouterFacade;
import com.mmm.clout.userservice.clouter.presentation.request.CreateClrRequest;
import com.mmm.clout.userservice.clouter.presentation.request.UpdateClrRequest;
import com.mmm.clout.userservice.clouter.presentation.response.CreateClrResponse;
import com.mmm.clout.userservice.clouter.presentation.response.SelectClrResponse;
import com.mmm.clout.userservice.clouter.presentation.response.UpdateClrResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clouters")
@RequiredArgsConstructor
public class ClouterController {

    private final ClouterFacade clouterFacade;

    @PostMapping
    public ResponseEntity<CreateClrResponse> create(
            @RequestBody CreateClrRequest createClrRequest
            ) {
        CreateClrResponse result = CreateClrResponse.from(
                clouterFacade.create(createClrRequest.toCommand())
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{clouterId}")
    public ResponseEntity<UpdateClrResponse> update(
            @PathVariable("clouterId") Long clouterId,
            @RequestBody UpdateClrRequest updateClrRequest
    ) {
        UpdateClrResponse result = UpdateClrResponse.from(
                clouterFacade.update(updateClrRequest.toCommand(clouterId))
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{clouterId}")
    public ResponseEntity<SelectClrResponse> select(
            @PathVariable("clouterId") Long clouterId
    ) {
        SelectClrResponse result = SelectClrResponse.from(
                clouterFacade.select(clouterId)
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}