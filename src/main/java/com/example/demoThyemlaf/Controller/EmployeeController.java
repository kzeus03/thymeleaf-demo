package com.example.demoThyemlaf.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoThyemlaf.entity.Employee;
import com.example.demoThyemlaf.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping({"/showEmployees","/","/list"})
	public ModelAndView showEmployees() {
		ModelAndView view=new ModelAndView("listOfEmployees");
		List<Employee> list=employeeRepository.findAll();
		view.addObject("employees",list);
		return view;
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView AddEmployee() {
		ModelAndView view=new ModelAndView("AddEmployeePage");
		Employee newemployee = new Employee();
		view.addObject("employee",newemployee);
		return view;
	}
	
	@PostMapping("/saveEmployee")
	public String Save(@ModelAttribute Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/list";
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView Update(@RequestParam Long employeeId) {
		ModelAndView view=new ModelAndView("AddEmployeePage");
		Employee employee=employeeRepository.findById(employeeId).get();
		view.addObject("employee",employee);
		return view;
	}
	
	@GetMapping("/deleteEmployee")
	public String Delete(@RequestParam Long employeeId) {
		employeeRepository.deleteById(employeeId);
		return "redirect:/list";
	}
	
}
