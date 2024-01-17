package solvd.laba.itcompany.patterns;

import solvd.laba.itcompany.domain.Meeting;

public interface Observer {
    void notify(Meeting meeting);
}
