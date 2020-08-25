package com.kgovt.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kgovt.models.ApplicationDetailes;
import com.kgovt.models.PaymentDetails;
import com.kgovt.repositories.ApplicationDetailesRepository;
import com.kgovt.utils.AppConstants;
import com.kgovt.utils.AppUtilities;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApplicationDetailesService extends AppConstants{

	@Autowired
	private ApplicationDetailesRepository applicationDetailesRepository;
		
	public ApplicationDetailes saveApplicationDetailes(ApplicationDetailes applicationDetailes)  {
		applicationDetailes = applicationDetailesRepository.save(applicationDetailes);
		return applicationDetailes;
	}
	
	public ApplicationDetailes findByApplicantNumber(String applicantNumber) {
		return applicationDetailesRepository.findByApplicantNumber(applicantNumber);
	}
	
	public Long countByMobile(Long mobile) {
		return applicationDetailesRepository.countByMobile(mobile);
	}
	
	public Long max() {
		return applicationDetailesRepository.max();
	}
	
	
	public ApplicationDetailes saveApplicationAction(ApplicationDetailes applicationDetailes,MultipartFile sslcFile,
			MultipartFile pucFile, MultipartFile ugFile, MultipartFile pgFile, MultipartFile photoFile, MultipartFile addressFile,
			MultipartFile certificateFile) {
		try {
			if(!sslcFile.isEmpty()) {
				String sslcFilePath = fileUploadAndReturn(sslcFile, String.valueOf(applicationDetailes.getMobile()), "SSLC");
				if(AppUtilities.isNotNullAndNotEmpty(sslcFilePath)) {
					applicationDetailes.setSslcFileName(sslcFile.getName());
					System.out.print(sslcFilePath);
				}	
			}
			
			if(!pucFile.isEmpty()) {
				String filePath = fileUploadAndReturn(sslcFile, String.valueOf(applicationDetailes.getMobile()), "PUC");
				if(AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setPucFileName(pucFile.getName());
					System.out.print(filePath);
				}	
			}
			
			if(!ugFile.isEmpty()) {
				String filePath = fileUploadAndReturn(sslcFile, String.valueOf(applicationDetailes.getMobile()), "UG");
				if(AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setUgFileName(ugFile.getName());
					System.out.print(filePath);
				}	
			}
			
			if(!pgFile.isEmpty()) {
				String filePath = fileUploadAndReturn(sslcFile, String.valueOf(applicationDetailes.getMobile()), "PG");
				if(AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setPgFileName(pgFile.getName());
					System.out.print(filePath);
				}	
			}
			
			if(!photoFile.isEmpty()) {
				String filePath = fileUploadAndReturn(sslcFile, String.valueOf(applicationDetailes.getMobile()), "PHOTO");
				if(AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setPhotoFileName(photoFile.getName());
					System.out.print(filePath);
				}	
			}
			
			if(!addressFile.isEmpty()) {
				String filePath = fileUploadAndReturn(sslcFile, String.valueOf(applicationDetailes.getMobile()), "ADDRESS");
				if(AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setAddressFileName(addressFile.getName());
					System.out.print(filePath);
				}	
			}
			
			if(!certificateFile.isEmpty()) {
				String filePath = fileUploadAndReturn(sslcFile, String.valueOf(applicationDetailes.getMobile()), "CERTIFICATE");
				if(AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setServiceCertFileName(certificateFile.getName());
					System.out.print(filePath);
				}	
			}
			
			Long applicantNumber = max();
			if(null == applicantNumber) {
				applicantNumber = 100001l;
				applicationDetailes.setApplicantNumber(applicantNumber);
				System.out.print(applicantNumber);
			}else {
				applicationDetailes.setApplicantNumber(applicantNumber+1);
				System.out.print(applicantNumber+1);
			}
			
			System.out.print(applicationDetailes.getDob());
			applicationDetailes.setAddressProofType("PAN");
			applicationDetailes.setApplicationStatus("1");
			applicationDetailes.setCreationDate(new Date());
			applicationDetailes = saveApplicationDetailes(applicationDetailes);
		}catch(Exception e) {
			log.error("Exeption while saving application", e);
		}
		return applicationDetailes;
	}
	
	public PaymentDetails proceedForPayment(PaymentDetails paymentDetails) {
		try {
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", paymentDetails.getAmount()); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", paymentDetails.getReceiptNo());
			orderRequest.put("payment_capture", true);
			try {
				RazorpayClient razorpay = new RazorpayClient(KEY, SECRET);
				Order order = razorpay.Orders.create(orderRequest);
				JSONObject jsonObj = new JSONObject(order.toString());
				paymentDetails.setOrderId(jsonObj.getString("id"));
				paymentDetails.setKey(KEY);
				return paymentDetails;
			} catch (RazorpayException e) {
				// TODO Auto-generated catch block
				log.error("Exeption while saving application", e);
				return null;
			}
		}catch(Exception e) {
			log.error("Exeption while saving application", e);
			return null;
		}
	}
	
	private String fileUploadAndReturn(MultipartFile file, String mobile, String fileFoler) {
		String path = null;
		try {
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + mobile+ File.separator + fileFoler);
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + file.getName());
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					log.info("Server File Location="
							+ serverFile.getAbsolutePath());
					path = serverFile.getAbsolutePath();
					return path;
				} catch (Exception e) {
					return null;
				}
			} else {
				return null;
			}
		}catch(Exception e) {
			
		}
		return path;
	}
	
	
}
