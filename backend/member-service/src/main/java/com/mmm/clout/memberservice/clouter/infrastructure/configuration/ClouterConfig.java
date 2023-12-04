package com.mmm.clout.memberservice.clouter.infrastructure.configuration;

import com.mmm.clout.memberservice.clouter.application.CreateClouterProcessor;
import com.mmm.clout.memberservice.clouter.application.SelectClouterProcessor;
import com.mmm.clout.memberservice.clouter.application.SelectTop10ClouterProcessor;
import com.mmm.clout.memberservice.clouter.application.UpdateClouterProcessor;
import com.mmm.clout.memberservice.clouter.domain.repository.ClouterRepository;
import com.mmm.clout.memberservice.member.domain.repository.MemberRepository;
import com.mmm.clout.memberservice.star.domain.repository.StarRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ClouterConfig {

    @Bean
    public SelectTop10ClouterProcessor selectTop10ClouterProcessor(
        ClouterRepository clouterRepository
    ) {
        return new SelectTop10ClouterProcessor(clouterRepository);
    }

    @Bean
    public CreateClouterProcessor createClouterProcessor(
            ClouterRepository clouterRepository,
            MemberRepository memberRepository,
            BCryptPasswordEncoder encoder,
            StarRepository starRepository
    ) {
        return new CreateClouterProcessor(clouterRepository, memberRepository, encoder, starRepository);
    }

    @Bean
    public UpdateClouterProcessor updateClouterProcessor(
            ClouterRepository clouterRepository, BCryptPasswordEncoder encoder
    ) {
        return new UpdateClouterProcessor(clouterRepository, encoder);
    }

    @Bean
    public SelectClouterProcessor selectClouterProcessor(
            ClouterRepository clouterRepository
    ) {
        return new SelectClouterProcessor(clouterRepository);
    }
}
