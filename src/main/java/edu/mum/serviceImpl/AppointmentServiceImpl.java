package edu.mum.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Appointment;
import edu.mum.repository.AppointmentRepository;
import edu.mum.service.AppointmentService;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	AppointmentRepository appointmentRepository;
	@Override
	public void saveAppointment(Appointment appointment) {
		String d = appointment.getAppointmentDate()+" "+appointment.getAppointmentTime();
		appointment.setAppointmentDateTime(d);
		appointmentRepository.save(appointment);
		
	}
	@Override
	public List<Appointment> findAll() {
		
		for(Appointment a: appointmentRepository.findAll()) {
			String [] dateTimeArray = a.getAppointmentDateTime().split(" ");
			a.setAppointmentDate(dateTimeArray[0]);
			a.setAppointmentTime(dateTimeArray[1] + dateTimeArray[2]);
						
		}
		return (List<Appointment>) appointmentRepository.findAll();
	}
	@Override
	public List<Appointment> findByDescription(String description) {
		for(Appointment a: appointmentRepository.findByDescription(description)) {
			String [] dateTimeArray = a.getAppointmentDateTime().split(" ");
			a.setAppointmentDate(dateTimeArray[0]);
			a.setAppointmentTime(dateTimeArray[1] + dateTimeArray[2]);
						
		}
		return (List<Appointment>) appointmentRepository.findByDescription(description);
	}

}
