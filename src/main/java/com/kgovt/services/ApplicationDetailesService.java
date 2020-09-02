package com.kgovt.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kgovt.datatable.ApplicationDetailesComparators;
import com.kgovt.datatable.paging.Column;
import com.kgovt.datatable.paging.Page;
import com.kgovt.datatable.paging.PagingRequest;
import com.kgovt.models.ApplicationDetailes;
import com.kgovt.models.PaymentDetails;
import com.kgovt.repositories.ApplicationDetailesRepository;
import com.kgovt.utils.AppConstants;
import com.kgovt.utils.AppUtilities;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApplicationDetailesService extends AppConstants {

	@Autowired
	private ApplicationDetailesRepository applicationDetailesRepository;

	public ApplicationDetailes saveApplicationDetailes(ApplicationDetailes applicationDetailes) {
		applicationDetailes = applicationDetailesRepository.save(applicationDetailes);
		return applicationDetailes;
	}

	public ApplicationDetailes findByApplicantNumber(Long applicantNumber) {
		return applicationDetailesRepository.findByApplicantNumber(applicantNumber);
	}

	public Long countByMobile(Long mobile) {
		return applicationDetailesRepository.countByMobile(mobile);
	}

	public Long max() {
		return applicationDetailesRepository.max();
	}

	public ApplicationDetailes saveApplicationAction(ApplicationDetailes applicationDetailes, MultipartFile sslcFile,
			MultipartFile pucFile, MultipartFile ugFile, MultipartFile pgFile, MultipartFile photoFile,
			MultipartFile addressFile, MultipartFile certificateFile, MultipartFile nocFile,
			MultipartFile signatureFile) {
		try {
			if (!sslcFile.isEmpty()) {
				String sslcFilePath = fileUploadAndReturn(sslcFile, String.valueOf(applicationDetailes.getMobile()),
						"SSLC");
				if (AppUtilities.isNotNullAndNotEmpty(sslcFilePath)) {
					applicationDetailes.setSslcFileName(sslcFilePath);
				}
			}

			if (!pucFile.isEmpty()) {
				String filePath = fileUploadAndReturn(pucFile, String.valueOf(applicationDetailes.getMobile()), "PUC");
				if (AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setPucFileName(filePath);
				}
			}

			if (!ugFile.isEmpty()) {
				String filePath = fileUploadAndReturn(ugFile, String.valueOf(applicationDetailes.getMobile()), "UG");
				if (AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setUgFileName(filePath);
				}
			}

			if (!pgFile.isEmpty()) {
				String filePath = fileUploadAndReturn(pgFile, String.valueOf(applicationDetailes.getMobile()), "PG");
				if (AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setPgFileName(filePath);
				}
			}

			if (!photoFile.isEmpty()) {
				String filePath = fileUploadAndReturn(photoFile, String.valueOf(applicationDetailes.getMobile()),
						"PHOTO");
				if (AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setPhotoFileName(filePath);
				}
			}

			if (!addressFile.isEmpty()) {
				String filePath = fileUploadAndReturn(addressFile, String.valueOf(applicationDetailes.getMobile()),
						"ADDRESS");
				if (AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setAddressFileName(filePath);
				}
			}

			if (!certificateFile.isEmpty()) {
				String filePath = fileUploadAndReturn(certificateFile, String.valueOf(applicationDetailes.getMobile()),
						"CERTIFICATE");
				if (AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setServiceCertFileName(filePath);
				}
			}

			if (!nocFile.isEmpty()) {
				String filePath = fileUploadAndReturn(nocFile, String.valueOf(applicationDetailes.getMobile()), "Noc");
				if (AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setServiceCertFileName(filePath);
				}
			}

			if (!signatureFile.isEmpty()) {
				String filePath = fileUploadAndReturn(signatureFile, String.valueOf(applicationDetailes.getMobile()),
						"Signature");
				if (AppUtilities.isNotNullAndNotEmpty(filePath)) {
					applicationDetailes.setServiceCertFileName(filePath);
				}
			}

			Long applicantNumber = max();
			if (null == applicantNumber) {
				applicantNumber = 100001l;
				applicationDetailes.setApplicantNumber(applicantNumber);
			} else {
				applicationDetailes.setApplicantNumber(applicantNumber + 1);
			}

			System.out.print(applicationDetailes.getDob());
			applicationDetailes.setApplicationStatus("I");
			applicationDetailes.setCreationDate(new Date());
			applicationDetailes = saveApplicationDetailes(applicationDetailes);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exeption while saving application", e);
		}
		return applicationDetailes;
	}

	public void removeApplicationDetailes(Long applicationNo) {
		applicationDetailesRepository.deleteByApplicantNumber(applicationNo);
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
				com.razorpay.Order order = razorpay.Orders.create(orderRequest);
				JSONObject jsonObj = new JSONObject(order.toString());
				paymentDetails.setOrderId(jsonObj.getString("id"));
				paymentDetails.setKey(KEY);
				return paymentDetails;
			} catch (RazorpayException e) {
				// TODO Auto-generated catch block
				log.error("Exeption while saving application", e);
				return null;
			}
		} catch (Exception e) {
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
					String rootPath = System.getProperty("user.home");
					// File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator +
					// mobile+ File.separator + fileFoler);
					String folder2Store = rootPath + File.separator + "Uploads"+ File.separator+fileFoler + File.separator;
					String extension = FilenameUtils.getExtension(file.getOriginalFilename());
					String fileName = fileFoler + "_" + mobile+"."+extension;
					File dir = new File(folder2Store);
					if (!dir.exists())
						dir.mkdirs();
					
					
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					log.info("Server File Location=" + serverFile.getAbsolutePath());
					path = serverFile.getAbsolutePath();
					return fileName;
				} catch (Exception e) {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {

		}
		return null;
	}

	private static final Comparator<ApplicationDetailes> EMPTY_COMPARATOR = (e1, e2) -> 0;

	public Page<ApplicationDetailes> getApplicationDetailess(PagingRequest pagingRequest,String region, String applicationStatus) {
		try {
			List<ApplicationDetailes> applicationDetailess = new ArrayList<>();
			if(AppUtilities.isNotNullAndNotEmpty(applicationStatus)) {
				applicationDetailess = applicationDetailesRepository.getByNames(region, applicationStatus);
			}else {
				applicationDetailess = applicationDetailesRepository.findByPreOfCenter(region);
			}
			return getPage(applicationDetailess, pagingRequest);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return new Page<>();
	}

	private Page<ApplicationDetailes> getPage(List<ApplicationDetailes> applicationDetailess,
			PagingRequest pagingRequest) {
		List<ApplicationDetailes> filtered = applicationDetailess.stream()
				.sorted(sortApplicationDetailess(pagingRequest)).filter(filterApplicationDetailess(pagingRequest))
				.skip(pagingRequest.getStart()).limit(pagingRequest.getLength()).collect(Collectors.toList());

		long count = applicationDetailess.stream().filter(filterApplicationDetailess(pagingRequest)).count();

		Page<ApplicationDetailes> page = new Page<>(filtered);
		page.setRecordsFiltered((int) count);
		page.setRecordsTotal((int) count);
		page.setDraw(pagingRequest.getDraw());

		return page;
	}

	private Predicate<ApplicationDetailes> filterApplicationDetailess(PagingRequest pagingRequest) {
		if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch().getValue())) {
			return applicationDetailes -> true;
		}

		String value = pagingRequest.getSearch().getValue();

		return applicationDetailes -> String.valueOf(applicationDetailes.getApplicantNumber()).toLowerCase()
				.contains(value);

	}

	private Comparator<ApplicationDetailes> sortApplicationDetailess(PagingRequest pagingRequest) {
		if (pagingRequest.getOrder() == null) {
			return EMPTY_COMPARATOR;
		}

		try {
			com.kgovt.datatable.paging.Order order = pagingRequest.getOrder().get(0);

			int columnIndex = order.getColumn();
			Column column = pagingRequest.getColumns().get(columnIndex);

			Comparator<ApplicationDetailes> comparator = ApplicationDetailesComparators.getComparator(column.getData(),
					order.getDir());
			if (comparator == null) {
				return EMPTY_COMPARATOR;
			}

			return comparator;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return EMPTY_COMPARATOR;
	}

}
