package edu.mum.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.google.gson.Gson;

import edu.mum.domain.Appointment;
import edu.mum.service.AppointmentService;


@Controller
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping("/")
	public String index(Model model) {
		Appointment appointment = new Appointment();
		List<Appointment>appointments = appointmentService.findAll();
		model.addAttribute("appointments",appointments);
		
		model.addAttribute(appointment);
		return "index";
	}
	
	@PostMapping("/saveAppointment")
	public String saveAppointment(Appointment appointment) {
		appointmentService.saveAppointment(appointment);
		
		return "redirect:/";
		
	}
	
	/*@GetMapping("/searchAppointment")
	public String searchAppointment( @RequestParam("description") String description, Model model) {
		
		if(description.trim().equals("") || description == null) {
			return "redirect:/";
		}
		else {
			List<Appointment>appointments = appointmentService.findByDescription(description);
			model.addAttribute("appointments",appointments);
			model.addAttribute("appointment", new Appointment());
		}
		
		return "index";
		
	}*/
	
	@GetMapping("/getAppointments")
	public void searchAppointment(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam("desc") String desc){	
		List<Appointment> appointments = new ArrayList<>();
		try{ 
			if(desc.equals("")|| desc==null)
			appointments =appointmentService.findAll();	
			else
				appointments = appointmentService.findByDescription(desc);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try {
			String json =  "" ; 
			json =new Gson().toJson(appointments);
			response.getWriter().write("{ \"data\":"   + json + " }");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
