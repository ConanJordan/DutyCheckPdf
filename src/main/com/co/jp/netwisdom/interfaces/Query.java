package main.com.co.jp.netwisdom.interfaces;

import java.util.List;

import main.com.co.jp.netwisdom.dto.NoteDutyDto;

public interface Query {
	public List<NoteDutyDto> query(int year, int month);
}
