package com.mmm.clout.memberservice.clouter.application;

import com.mmm.clout.memberservice.clouter.application.command.UpdateClrCommand;
import com.mmm.clout.memberservice.clouter.domain.Clouter;
import com.mmm.clout.memberservice.clouter.domain.repository.ClouterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UpdateClouterProcessor {

    private final ClouterRepository clouterRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Clouter execute(UpdateClrCommand command) {
        Clouter clouter = clouterRepository.findById(command.getClouterId());
        clouter.update(
            command.getPwd(),
            command.getNickName(),
            command.getName(),
            command.getBirthday(),
            command.getAge(),
            command.getPhoneNumber(),
            command.getChannelList().stream().map(v -> v.toValueType()).collect(Collectors.toList()),
            command.getHopeCost().toValueType(),
            command.isNegoable(),
            command.getCategoryList(),
            command.getRegionList(),
            command.getAddress().toValueType()
        );
        clouter.changePwd(encoder.encode(clouter.getPwd()));
        return clouter;
    }

}
