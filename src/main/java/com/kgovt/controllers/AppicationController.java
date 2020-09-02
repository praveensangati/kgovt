package com.kgovt.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kgovt.models.AcceptedCandidates;
import com.kgovt.models.Admin;
import com.kgovt.models.ApplicationDetailes;
import com.kgovt.models.PaymentDetails;
import com.kgovt.models.Status;
import com.kgovt.services.AcceptedService;
import com.kgovt.services.AdminService;
import com.kgovt.services.ApplicationDetailesService;
import com.kgovt.services.PaymentDetailsService;
import com.kgovt.services.StatusService;
import com.kgovt.utils.AppConstants;
import com.kgovt.utils.AppUtilities;

@Controller
public class AppicationController extends AppConstants {

	private static final Logger logger = LoggerFactory.getLogger(AppicationController.class);

	@Autowired
	private ApplicationDetailesService appicationService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AcceptedService acceptedService;

	@Autowired
	private PaymentDetailsService paymentDetailsService;

	@Autowired
	private StatusService statusService;
	
	@Autowired
    private ServletContext servletContext;

	private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

	@GetMapping(SEPERATOR)
	public String getApplicationHome(Model model) {
		return "index";
	}

	@GetMapping("/contact")
	public String contactPage() {
		return "contact";
	}

	@PostMapping("/adminLogin")
	public String adminView(Model model,Admin admin) {
		//Admin obj =  adminService.findByAdminName(admin.getAdminName());
		Admin obj= adminService.findByRegion(admin.getRegion());
		if(null!=obj && obj.getPassword().equalsIgnoreCase(admin.getPassword())) {
			model.addAttribute("region", admin.getRegion());
		}else {
			model.addAttribute("error", "1");
			return "adminlogin";
		}
		return "application_list";
	}
	
	
	@GetMapping("/saveAdmin")
	public String saveAdmin() {
		Admin admin =new Admin();
		admin.setPassword("Arun");
		admin.setRegion("Dharwad");
		adminService.saveAdmin(admin);
		return "";
	}
	
	@GetMapping("/list")
	public String applicationlistPage() {
		return "application_list";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("admin", new Admin());
		return "adminlogin";
	}

	@GetMapping("/viewAppData")
	public String viewAppData(Model model, @RequestParam String applicantNumber) {
		try {
			ApplicationDetailes appDetails = appicationService.findByApplicantNumber(Long.valueOf(applicantNumber));
			model.addAttribute("applicationDetailes", appDetails);
		} catch (Exception e) {
		}
		return "applicationview";
	}

	@GetMapping("/offline")
	public String offlinePage() {
		return "offline";
	}

	@GetMapping("/status")
	public String status() {
		return "status";
	}

	@GetMapping(SEPERATOR + COMMON_NEW)
	public String applicationNew(Model model) {
		ApplicationDetailes appDetails = new ApplicationDetailes();
		model.addAttribute("applicationDetailes", appDetails);
		return "form";
	}

	@GetMapping("/redirectIndex")
	public String redirectIndex(Model model, PaymentDetails paymentDetails) {
		try {
			model.addAttribute("failure", "Payment Failed");
			appicationService.removeApplicationDetailes(paymentDetails.getApplicantNumber());
		} catch (Exception e) {
			logger.error("Exception while saving aapplication", e);
		}
		return "redirect:/indexFailure";
	}

	@GetMapping("/indexFailure")
	public String indexPage(Model model) {
		model.addAttribute("failure", "Payment Failed");
		return "index";
	}

	@PostMapping("/validateMobile")
	@ResponseBody
	public String checkMobileExiastes(@RequestParam String mobileNumber) {
		Long mobileCount = appicationService.countByMobile(Long.valueOf(mobileNumber));
		if (mobileCount > 0) {
			return "1";
		} else {
			return "0";
		}
	}

	@PostMapping(value = "/acceptViewData", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map appectedData(@RequestParam Long adminId, @RequestParam Long applicantNo,@RequestParam String status,@RequestParam String comment) {
		HashMap<String, Object> returnData = new HashMap<>();
		returnData.put("ERROR", "0");
		returnData.put("TYPE", status);
		try {
			// update aap details
			ApplicationDetailes appDetails = appicationService.findByApplicantNumber(applicantNo);
			appDetails.setApplicationStatus(status);
			appicationService.saveApplicationDetailes(appDetails);
			//update status
			Status stat=statusService.findByApplicantNumber(applicantNo);
			stat.setStatus(status);
			stat.setComment(comment);
			statusService.saveStatus(stat);
			// update accept
			if(status.equals("A")) {
				AcceptedCandidates acceptedCandidated = new AcceptedCandidates();
				acceptedCandidated.setAdminId(adminId);
				acceptedCandidated.setApplicantNumber(applicantNo);
				acceptedCandidated.setPassword(AppUtilities.generateCommonLangPassword());
				acceptedService.saveAcceptedCandidates(acceptedCandidated);
				returnData.put("ERROR", "0");
			}			
		}catch(Exception e) {
			returnData.put("ERROR", e);
		}
		return returnData;
	}
	
	@PostMapping(value = "/checkStatus", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map checkStatus(@RequestParam String mobileNumber, @RequestParam String password) {
		HashMap<String, Object> returnData = new HashMap<>();
		returnData.put("ERROR", "0");
		try {
			Status status = statusService.findByMobile(Long.valueOf(mobileNumber));
			if (null != status) {
				if (AppUtilities.isNotNullAndNotEmpty(password)) {
					ApplicationDetailes appDetails = appicationService
							.findByApplicantNumber(status.getApplicantNumber());
					String mobile = String.valueOf(appDetails.getMobile()); // 9743875023
					String last4digits = mobile.substring(6, 10);
					DateFormat dateFormat = new SimpleDateFormat("yyyy");
					String strDate = dateFormat.format(appDetails.getDob());
					String concatInputs = last4digits + strDate.replace("-", "");
					if (concatInputs.equals(password)) {
						returnData.put("Status", status);
						returnData.put("ERROR", "1");
					} else {
						returnData.put("MESSAGE", "In correct credentials,Please try again");
					}
				}
			} else {
				returnData.put("MESSAGE", "Mobile number not exits");
			}
		} catch (Exception e) {
			logger.error("ERROR WHILE fetching mobile number", e.getMessage());
		}
		return returnData;
	}

	@PostMapping(SEPERATOR + COMMON_SAVE)
	public String saveApplication(Model model, ApplicationDetailes applicationDetailes, HttpServletRequest request,
			@RequestParam("sslcFile") MultipartFile sslcFile, @RequestParam("pucFile") MultipartFile pucFile,
			@RequestParam("ugFile") MultipartFile ugFile, @RequestParam("pgFile") MultipartFile pgFile,
			@RequestParam("photoFile") MultipartFile photoFile, @RequestParam("addressFile") MultipartFile addressFile,
			@RequestParam("certificateFile") MultipartFile certificateFile,
			@RequestParam("nocFile") MultipartFile nocFile,
			@RequestParam("signatureFile") MultipartFile signatureFile) {
		try {
			applicationDetailes = appicationService.saveApplicationAction(applicationDetailes, sslcFile, pucFile,
					ugFile, pgFile, photoFile, addressFile, certificateFile, nocFile, signatureFile);
			if (null == applicationDetailes) {
				model.addAttribute("errorMessage",
						"Ooops Unexpected Error occured while saving Application, Please contact System Administrator !");
				return "error";
			} else {
				PaymentDetails paymentDetails = new PaymentDetails();
				paymentDetails.setAmount(PAYMENT1);
				paymentDetails.setMobile(applicationDetailes.getMobile());
				paymentDetails.setEmail(applicationDetailes.getEmail());
				paymentDetails.setAddress(applicationDetailes.getResAddress());
				paymentDetails.setReceiptNo(AppUtilities.generateReceptNo(applicationDetailes));
				paymentDetails.setDescription("A Payment for Application Submission");
				paymentDetails.setApplicantNumber(applicationDetailes.getApplicantNumber());
				paymentDetails = appicationService.proceedForPayment(paymentDetails);
				if (null == paymentDetails) {
					model.addAttribute("errorMessage",
							"Ooops Unexpected Error occured while submitting Payment, Please contact System Administrator !");
					return "error";
				} else {
					model.addAttribute("paymentDetails", paymentDetails);
					return "payment";
				}
			}
		} catch (Exception e) {
			logger.error("Exception while saving aapplication", e);
		}
		return "success";
	}

	@PostMapping(SEPERATOR + COMMON_FINAL)
	public String finalFlow(Model model, PaymentDetails paymentDetails) {
		String status = null;
		try {
			String data = paymentDetails.getRazorpayOrderId() + "|" + paymentDetails.getRazorpayPaymentId();
			status = calculateRFC2104HMAC(data, SECRET);
		} catch (SignatureException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			status = null;
		}
		if (null != status && paymentDetails.getRazorpaySignature().equals(status)) {
			try {
				logger.info("Exception of Payement save started");
				paymentDetails.setCreatedDate(new Date());
				paymentDetails.setStatus("Success");
				paymentDetailsService.savePaymentDetails(paymentDetails);

				logger.info("Exception of Payement save started");
				Status appStatus = new Status();
				appStatus.setApplicantNumber(paymentDetails.getApplicantNumber());
				appStatus.setStatus("I");
				appStatus.setMobile(paymentDetails.getMobile());
				appStatus.setAppliedDate(new Date());
				appStatus.setComment("created by System");
				statusService.saveStatus(appStatus);

			} catch (Exception e) {
				logger.error("Exception while saving aapplication", e);
			}
		} else {
			model.addAttribute("paymentDetails", paymentDetails);
			model.addAttribute("successMessage", "Wrong Details");
		}

		return "success";
	}

	public static String calculateRFC2104HMAC(String data, String secret) throws java.security.SignatureException {
		String result;
		try {

			// get an hmac_sha256 key from the raw secret bytes
			SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM);

			// get an hmac_sha256 Mac instance and initialize with the signing key
			Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
			mac.init(signingKey);

			// compute the hmac on input data bytes
			byte[] rawHmac = mac.doFinal(data.getBytes());

			// base64-encode the hmac
			result = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();

		} catch (Exception e) {
			throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public  ResponseEntity<InputStreamResource>  downloadFile(@Param(value = "photoFile") String photoFile)throws IOException {
		if (AppUtilities.isNotNullAndNotEmpty(photoFile)) {
			String[] fileNames = photoFile.split("_");
			String path = fileNames[0];
			String rootPath = System.getProperty("user.home");
			String folderStored = rootPath + File.separator +"Uploads"+ File.separator+ path + File.separator + photoFile;
			 File downloadFile= new File(folderStored); 
			 //return new FileSystemResource(new File(folderStored));
			 
			 InputStreamResource resource = new InputStreamResource(new FileInputStream(downloadFile));
			 MediaType mediaType = AppUtilities.getMediaTypeForFileName(this.servletContext, downloadFile.getName());
		        return ResponseEntity.ok()
		                // Content-Disposition
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + downloadFile.getName())
		                // Content-Type
		                .contentType(mediaType)
		                // Contet-Length
		                .contentLength(downloadFile.length()) //
		                .body(resource);
		} else {
			return null;
		}

	}

}
