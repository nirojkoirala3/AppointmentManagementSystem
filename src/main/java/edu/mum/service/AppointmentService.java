package edu.mum.service;


import java.util.List;

import edu.mum.domain.Appointment;


public interface AppointmentService {
	
	
	void saveAppointment(Appointment appointment);

	List<Appointment> findAll();

	List<Appointment> findByDescription(String description);
}
