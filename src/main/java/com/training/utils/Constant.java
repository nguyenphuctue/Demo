package com.training.utils;

public class Constant {
	public static final String UPLOAD_FOLDER = "D:/temp/";

	public static final String[] ADMIN_ROLES = { "/employee/addEmployee", "/employee/updateEmployee",
			"/employee/deleteEmployee", "/employee/deleteMulti", "/injectionSchedule/detailInjectionSchedule/**",
			"/injectionSchedule/addInjectionSchedule", "/injectionSchedule/updateInjectionSchedule",
			"/vaccineType/addVaccineType", "/vaccineType/makeActiveVaccineType", "/vaccineType/updateVaccineType/**",
			"/vaccineType/saveUpdateVaccineType", "/employee/detailEmployee/**", "/vaccineType/detailVaccineType/**",
			"/vaccine/addVaccine", "/vaccine/updateVaccine/**", "/vaccine/saveUpdateVaccine",
			"/vaccine/detailVaccine/**", "/vaccine/changeStatus", "/vaccine/importVaccine", "/vaccine/saveImport",
			"/vaccine/deleteVaccine" };

	public static final String[] USER_ROLES = { "/employee/listEmployee/**",
			"/injectionSchedule/listInjectionSchedule/**", "/report/**", "/vaccineType/listVaccineType/**",
			"/vaccine/listVaccine/**" };

	// Customer management
	public static final String CUSTOMER_REGISTER_CUSTOMER = "add-customer";
	public static final String CUSTOMER_LIST_CUSTOMER = "list-customer";
	public static final String CUSTOMER_DETAIL_CUSTOMER = "detail-customer";
	public static final String CUSTOMER_CHANGE_PASSWORD = "password-customer";
	public static final String CUSTOMER_REDIRECT_LIST_CUSTOMER = "redirect:list-customer";
	// Employee management
	public static final String EMPLOYEE_ADD_EMPLOYEE = "add-employee";
	public static final String EMPLOYEE_LIST_EMPLOYEE = "list-employee";
	public static final String EMPLOYEE_DETAIL_EMPLOYEE = "detail-employee";
	public static final String EMPLOYEE_REDIRECT_LIST_EMPLOYEE = "redirect:listEmployee";
	// Injection result management
	public static final String IRM_ADD_INJECTION_RESULT = "add-injection-result";
	public static final String IRM_LIST_INJECTION_RESULT = "list-injection-result";
	public static final String IRM_REDIRECT_LIST_INJECTION_RESULT = "redirect:list";
	// Injection schedule management
	public static final String ISM_ADD_INJECTION_SCHEDULE = "add-injection-schedule";
	public static final String ISM_LIST_INJECTION_SCHEDULE = "list-injection-schedule";
	public static final String ISM_DETAIL_INJECTION_SCHEDULE = "detail-injection-schedule";
	public static final String ISM_REDIRECT_LIST_INJECTION_SCHEDULE = "redirect:listInjectionSchedule";
	// Login
	public static final String LOGIN = "login";
	public static final String HOME = "home-page";
	// News management
	public static final String NEWS_ADD_NEWS = "add-news";
	public static final String NEWS_LIST_NEWS = "list-news";
	public static final String NEWS_REDIRECT_LIST_NEWS = "redirect:listNews";
	// Report management
	// Vaccine management
	public static final String VACCINE_ADD_VACCINE = "add-vaccine";
	public static final String VACCINE_LIST_VACCINE = "list-vaccine";
	public static final String VACCINE_DETAIL_VACCINE = "detail-vaccine";
	public static final String VACCINE_UPDATE_VACCINE = "update-vaccine";
	public static final String VACCINE_IMPORT_VACCINE = "import-vaccine";
	public static final String VACCINE_REDIRECT_ADD_VACCINE = "redirect:addVaccine";
	public static final String VACCINE_REDIRECT_LIST_VACCINE = "redirect:listVaccine";
	// Vaccine Type management
	public static final String VT_ADD_VACCINE_TYPE = "add-vaccine-type";
	public static final String VT_LIST_VACCINE_TYPE = "list-vaccine-type";
	public static final String VT_UPDATE_VACCINE_TYPE = "update-vaccine-type";
	public static final String VT_DETAIL_VACCINE_TYPE = "detail-vaccine-type";
	public static final String VT_REDIRECT_ADD_VACCINE_TYPE = "redirect:addVaccineType";
	public static final String VT_REDIRECT_LIST_VACCINE_TYPE = "redirect:listVaccineType";
}
