package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Meeting;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.MeetingRepository;
import solvd.laba.itcompany.persistence.impl.MeetingJdbsRepository;
import solvd.laba.itcompany.persistence.impl.MeetingMyBatisRepository;
import solvd.laba.itcompany.service.MeetingService;

public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingServiceImpl() {
        meetingRepository = new MeetingMyBatisRepository();
    }

    @Override
    public void create(Meeting meeting) {
        try {
            meetingRepository.create(meeting);
        } catch (Exception e) {
            throw new ServiceException("Failed to create meeting", e);
        }
    }
}
