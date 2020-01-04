package main.com.co.jp.netwisdom.service;

import java.util.List;

import main.com.co.jp.netwisdom.dao.NoteDutyDtoDao;
import main.com.co.jp.netwisdom.dto.NoteDutyDto;
import main.com.co.jp.netwisdom.interfaces.Query;

public class NoteDutyDtoService implements Query {

    private NoteDutyDtoDao noteDutyDtoDao = new NoteDutyDtoDao();

    @Override
    public List<NoteDutyDto> query(int year, int month) {

        return this.noteDutyDtoDao.query(year, month);
    }

}
