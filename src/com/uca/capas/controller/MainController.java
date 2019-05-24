package com.uca.capas.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.StudentDAO;
import com.uca.capas.domain.Student;
//labo
@Controller
public class MainController {
	
	static Logger log = Logger.getLogger(MainController.class.getName());
	
	@Autowired
	private StudentDAO studentDao;
	
	@RequestMapping("/")
	public ModelAndView initMain(){
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
		
		 students = studentDao.findAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("students",students);
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView insert() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("student", new Student());
		mav.setViewName("form");
		return mav;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value="student")String name) {
		ModelAndView mav = new ModelAndView();
		List<Student> s= null;
		try {
			studentDao.delete(name);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		s = studentDao.findAll();
		mav.addObject("students",s);
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping("/updateForm")
	public ModelAndView update(@RequestParam(value="code")Integer code){
		ModelAndView mav = new ModelAndView();
		Student student = null;
		try {
		student = studentDao.findOne(code);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("student",student);
		mav.setViewName("updateForm");
		return mav;
	}
	@RequestMapping(value="/formData")
	public ModelAndView save(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
			log.info("Agrego un nuevo usuario");
			studentDao.save(s, 1);
		}catch(Exception e) {
			log.info("Error:"+e.toString());
		}
		students = studentDao.findAll();
		log.info(students.get(0).getlName());
		mav.addObject("students", students);
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping(value="/updateData")
	public ModelAndView saveUpdate(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
			log.info("Actualizo un usuario");
			studentDao.save(s, 0);
		}catch(Exception e) {
			log.info("Error:"+e.toString());
		}
		students = studentDao.findAll();
		log.info(students.get(0).getlName());
		mav.addObject("students", students);
		mav.setViewName("main");
		return mav;
	}
	
}