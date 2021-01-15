package com.pk.hotelmanagement.hotel.room;

import com.pk.hotelmanagement.users.vo.Interval;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AvailableRoomData {
    private int hotelId;
    private Interval interval;
}
