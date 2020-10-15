package com.cg.batch.WriterProcessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.cg.batch.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Processor implements ItemProcessor<Employee,Employee> {

	@Override
	public Employee process(Employee employee) throws Exception {
		log.info("Processor"+employee);
		return employee;
	}

}
