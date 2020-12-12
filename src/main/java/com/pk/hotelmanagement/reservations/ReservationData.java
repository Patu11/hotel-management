package com.pk.hotelmanagement.reservations;

import com.pk.hotelmanagement.users.vo.Interval;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ReservationData {
    @NotNull
    private List<Integer> roomIds;
    @NotNull
    private Interval interval;
}
