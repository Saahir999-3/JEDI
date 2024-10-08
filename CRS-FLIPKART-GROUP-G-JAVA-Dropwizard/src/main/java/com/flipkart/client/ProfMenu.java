package com.flipkart.client;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.flipkart.bean.Professor;
import com.flipkart.business.ProfessorBusiness;
import com.flipkart.business.ProfessorInterface;

public class ProfMenu {
	ProfessorInterface profService = new ProfessorBusiness();

	/**
	 * Displays the professor menu and processes user selections for various professor operations.
	 * @param Professor The professor user performing the operations.
	 * @param username The username of the professor.
	 */
	public void professorMenu(Professor prof, String username) {
//		System.out.print("\033[H\033[2J");
		System.out.println("*****************************************************");
		System.out.println("*****************************************************");
		System.out.flush();
		System.out.println("Welcome to CRS " + "\033[3m"+prof.getName()+" ("+username+")"+"\033[0m");
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("LOGIN TIME: " + localDateTime);
		
		Scanner s = new Scanner(System.in);
		int in = 0;
		
		while (in != 4) {
			System.out.println("*****************************************************");
			System.out.flush();
			System.out.println("Professor Menu:");
			System.out.println("1. Offer Course\n2. View Registered Students\n3. Submit Grades\n4. Exit");
		
			in = s.nextInt();
			
			switch (in) {
				case 1:
					offerCourse(prof);
					break;
				case 2:
					viewRegisteredStudent(prof);
					break;
				case 3:
					submitGrade(prof);
					break;
				case 4:
					continue;
				default:
					System.out.println("Invalid choice");
			}
		}
	}

	/**
	 * Allows the professor to offer a course.
	 * @param Professor The professor user performing the operation.
	 */
	private void offerCourse(Professor prof) {
		System.out.println("Enter Course ID from the following:");
		System.out.println(profService.viewCourses());
		Scanner s = new Scanner(System.in);
		String courseID = s.next();
		System.out.println(profService.offerCourse(courseID, prof));
	}

	/**
	 * Allows the professor to view registered students for a specific course.
	 * @param Professor The professor user performing the operation.
	 */
	private void viewRegisteredStudent(Professor prof) {
		System.out.println("Select Course ID from the following:");
		System.out.println(profService.viewCourseOffering(prof));
		Scanner s = new Scanner(System.in);
		String courseID = s.next();
		String studentList = profService.getStudents(courseID, prof);
		System.out.println(studentList);
	}

	/**
	 * Allows the professor to submit grades for a student in a specific course.
	 * @param Professor The professor user performing the operation.
	 */
	private void submitGrade(Professor prof) {
		System.out.println("Select Course ID from the following:");
		System.out.println(profService.viewCourseOffering(prof));
		Scanner s = new Scanner(System.in);
		String courseID = s.next();
		System.out.println("Enter Student ID from the following:");
		System.out.println(profService.getStudents(courseID, prof));
		String studentID = s.next();
		System.out.println("Grade:");
		String grade = s.next();
		System.out.println(profService.giveGrade(courseID, studentID, grade, prof));
	}
}
