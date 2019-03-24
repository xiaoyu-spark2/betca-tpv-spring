package es.upm.miw.business_controllers;

import es.upm.miw.documents.TimeClock;
import es.upm.miw.documents.User;
import es.upm.miw.dtos.input.TimeClockSearchInputDto;
import es.upm.miw.dtos.output.TimeClockOutputDto;
import es.upm.miw.repositories.TimeClockRepository;
import es.upm.miw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TimeClockController {
    @Autowired
    private TimeClockRepository timeClockRepository;

    @Autowired
    private UserRepository userRepository;

    public TimeClockOutputDto[] readAll() {
        return convertResultDomainModelToDto(this.timeClockRepository.findAll());
    }

    public TimeClockOutputDto[] searchByDateRangeAndUserMobile(TimeClockSearchInputDto timeClockSearchInputDto) {
        User user = this.userRepository.findByMobile(timeClockSearchInputDto.getUserMobile()).orElse(null);
        return convertResultDomainModelToDto(this.timeClockRepository.findAll());
    }

    private TimeClockOutputDto[] convertResultDomainModelToDto(List<TimeClock> all) {
        TimeClockOutputDto[] timeClockOutputDtos = new TimeClockOutputDto[all.size()];
        int i = 0;
        for (TimeClock timeClock : all) {
            timeClockOutputDtos[i] = new TimeClockOutputDto(timeClock);
            i++;
        }
        return timeClockOutputDtos;
    }
}
