//package io.pivotal.citi.gemfire.test;
//
//import static org.junit.Assert.assertNotNull;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.citi.announcement.Announcement;
//import com.citi.announcement.Option;
//import com.citi.announcement.Payout;
//import com.citi.announcement.RateValue;
//import com.citi.entitlement.Entitlement;
//import com.citi.entitlement.EntitlementType;
//import com.citi.position.Holding;
//import com.citi.position.HoldingRate;
//import com.citi.repository.AnnouncementRepo;
//import com.citi.repository.EntitlementRepo;
//import com.citi.repository.PositionRepo;
//
//
//@ContextConfiguration("/cache-config.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
//public class RepositoriesUnitTest {
//
//
//	@Autowired
//	private AnnouncementRepo announcementRepo;
//
//
//	@Autowired
//	private EntitlementRepo entitlementRepo;
//
//	@Autowired
//	private PositionRepo positionRepo;
//
//
//	// Generate Rate Values test data
//	private RateValue generateRateValues(int id){
//
//		Long rateId = new Long(id);
//
//		RateValue rv = new RateValue(new BigDecimal(rateId + rateId/10).setScale(2, BigDecimal.ROUND_HALF_UP), rateId);
//		return rv;
//	}
//
//	// Generate Payout test data
//	private Payout generatePayout(int id){
//
//		List<RateValue> rateList = new ArrayList<RateValue>();
//		Long payoutId = new Long(id);
//		String currency = "USD";
//
//		rateList.add(generateRateValues(100000));
//		rateList.add(generateRateValues(100001));
//
//		Payout payout = new Payout(rateList, payoutId, currency);
//
//		return payout;
//
//	}
//
//	// Generate Option test data
//	private Option generateOption(int id, boolean deaultInd){
//
//
//		List<Payout> payoutList = new ArrayList<Payout>();
//		Long optionId = new Long(id);
//		Long optionNumber = new Long(1);
//
//		payoutList.add(generatePayout(100000));
//		payoutList.add(generatePayout(100001));
//
//		Option option = new Option(payoutList, optionId, optionNumber, deaultInd);
//
//		return option;
//
//	}
//
//	// Generate Announcement test data
//	private void generateAnnouncement() {
//
//		List<Option> optionList1 = new ArrayList<Option>();
//		Long announcementId1 = new Long(100000);
//		Long securityId1 = new Long(200000);
//
//
//		List<Option> optionList2 = new ArrayList<Option>();
//		Long announcementId2 = new Long(100001);
//		Long securityId2 = new Long(200001);
//
//
//
//		optionList1.add(generateOption(100000, true));
//		optionList1.add(generateOption(100001, false));
//
//		optionList2.add(generateOption(100002, true));
//		optionList2.add(generateOption(100003, false));
//
//		Announcement announc1 = new Announcement(optionList1, announcementId1, securityId1, 1);
//		Announcement announc2 = new Announcement(optionList2, announcementId2, securityId2, 2);
//
//		announcementRepo.save(announc1);
//		announcementRepo.save(announc2);
//
//	}
//
//	// Positive Test for Announcement repository
//	@Test
//	public void testAnnouncementRepo(){
//
//		generateAnnouncement();
//
//		assertNotNull( announcementRepo.findOne(new Long(100000)));
//		assertNotNull(announcementRepo.findOptionByID(new Long(100000)));
//
//		// Testing custom query: findOptionByID
//		Collection<Option> results = announcementRepo.findOptionByID(new Long(100000));
//		Iterator<Option> itr = results.iterator();
//		while(itr.hasNext()){
//			Option temp = itr.next();
//			System.out.println("Id: "+temp.getId());
//			System.out.println("Option Number: "+temp.getOptionNumber());
//		}
//
//		// Testing custom query: findRateByAnnouncementID
//		Collection<RateValue> results1 = announcementRepo.findRateByAnnouncementID(new Long(100000));
//		Iterator<RateValue> itr1 = results1.iterator();
//		while(itr1.hasNext()){
//			RateValue temp = itr1.next();
//			System.out.println("Id: "+temp.getId());
//			System.out.println("Value: "+temp.getRate());
//		}
//
//	}
//
//	// Positive test for Entitlement repository
//	@Test
//	public void testEntitlementRepo(){
//
//		Long entitlementId = new Long(200000);
//		Long positionId = new Long(300000);
//		Long announecementId = new Long(100000);
//		double amount = 1234.00;
//
//		Long entitlementTypeId1 = new Long(400000);
//		String type1 = "abc";
//		Long entitlementTypeId2 = new Long(400001);
//		String type2 = "xyz";
//
//		EntitlementType etype1 = new EntitlementType(entitlementTypeId1, type1);
//		EntitlementType etype2 = new EntitlementType(entitlementTypeId2, type2);
//
//		List<EntitlementType> eList = new ArrayList<EntitlementType>();
//		eList.add(etype1);
//		eList.add(etype2);
//
//		Entitlement entitlement = new Entitlement(eList,entitlementId, positionId,
//				announecementId, amount);
//		entitlementRepo.save(entitlement);
//
//		assertNotNull(entitlementRepo.findOne(entitlementId));
//
//	}
//
//	// Positive test for Position repository
//	@Test
//	public void testPositionRepo(){
//
//		Long positionId = new Long(300000);
//		Long announecementId = new Long(100000);
//		int quantity = 10;
//		String procesingUnit = "NYC";
//
//		Long rateId1 = new Long(500000);
//		Long rateId2 = new Long(500001);
//
//		double rate1 = 0.9;
//		double rate2 = 0.8;
//
//		HoldingRate holdingRate1 = new HoldingRate(rateId1, rate1);
//		HoldingRate holdingRate2 = new HoldingRate(rateId2, rate2);
//
//		List<HoldingRate> rateList = new ArrayList<HoldingRate>();
//		rateList.add(holdingRate1);
//		rateList.add(holdingRate2);
//
//		Holding pos = new Holding(rateList, positionId, announecementId, quantity, procesingUnit);
//		positionRepo.save(pos);
//
//		assertNotNull( positionRepo.findOne(positionId));
//
//	}
//
//}
