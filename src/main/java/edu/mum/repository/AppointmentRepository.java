package edu.mum.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer>{

	List<Appointment> findByDescription(String description);

}
