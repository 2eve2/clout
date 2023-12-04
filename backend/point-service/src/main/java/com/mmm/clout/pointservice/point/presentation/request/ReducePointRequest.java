package com.mmm.clout.pointservice.point.presentation.request;

import com.mmm.clout.pointservice.point.application.command.ReduceCommand;
import com.mmm.clout.pointservice.point.domain.PointCategory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReducePointRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private Long reducingPoint;

    @NotBlank
    private String pointCategory;

    public ReduceCommand toCommand() {
        return new ReduceCommand(
            memberId,
            reducingPoint,
            PointCategory.valueOf(pointCategory)
        );
    }
}
